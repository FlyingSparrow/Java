package com.sparrow.ibe.bookingservice.airbookmodify.dao;

import com.sparrow.app.config.IBEConfig;
import com.sparrow.app.init.IBEApi;
import com.sparrow.app.init.IBEApiConfig;
import com.sparrow.ibe.bookingservice.airbookmodify.builder.AirBookModifyRequestBuilder;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AirBookModifyRequest;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AirBookModifyResponse;
import com.sparrow.ibe.enums.IBEError;
import com.sparrow.ibe.enums.IBEInterface;
import com.sparrow.ibe.model.DefaultError;
import com.sparrow.ibe.model.DefaultWarning;
import com.sparrow.integration.dao.IntegrationDao;
import com.sparrow.integration.exception.IntegrationException;
import com.sparrow.integration.handler.ValidationHandler;
import com.sparrow.utils.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 接口类别：预订服务
 * 接口名称：自动修改服务
 * 接口ID：JP012
 * 自动修改服务的数据访问类，处理请求和解析响应结果
 * @author wangjc
 * @date 2014-7-3
 */
@Component
public class AirBookModifyDao implements IntegrationDao<AirBookModifyRequest> {

	private static final Logger logger = LoggerFactory.getLogger(AirBookModifyDao.class);

	@Autowired
	private ValidationHandler<AirBookModifyRequest> validationHandler;
	@Autowired
	private AirBookModifyRequestBuilder airBookModifyRequestBuilder;
	@Autowired
	private IBEApiConfig ibeApiConfig;
	@Autowired
	private IBEConfig ibeConfig;
	
	@Override
	public Serializable execute(String userId, String password, AirBookModifyRequest request) {
		if(validationHandler.validate(userId, password, request)){
			return parseResponseResult(request);
		}else{
			return validationHandler.getValidationResult();
		}
	}
	
	private AirBookModifyResponse parseResponseResult(
			AirBookModifyRequest request) {
		AirBookModifyResponse result = null;
		Document document = XMLUtils.getInstance().readXmlFile(getResponseResult(request));
		if(document != null){
			result = buildResponseObject(document.getRootElement());
		}else{
			result = new AirBookModifyResponse();
			List<DefaultError> errorList = new ArrayList<DefaultError>();
			DefaultError error = new DefaultError();
			IBEError errorEnum = IBEError.SYSTEM_ERROR_001;
			error.setCode(errorEnum.getCode());
			error.setShortText(errorEnum.getEnMessage());
			error.setCnMessage(errorEnum.getCnMessage());
			
			errorList.add(error);
			result.setErrorList(errorList);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private List<DefaultError> buildErrorObject(Element rootElement){
		List<DefaultError> result = new ArrayList<DefaultError>();
		List<Node> errorList = rootElement.selectNodes("Errors/Error");
		errorList.forEach(item ->{
			Element element = (Element) item;
			DefaultError error = new DefaultError();
			String code = element.attributeValue("Code");
			error.setCode(code);
			error.setType(element.attributeValue("Type"));
			error.setShortText(element.attributeValue("ShortText"));
			error.setCnMessage(StringUtils.ibeMessage(code));

			result.add(error);
		});
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private List<DefaultWarning> buildWarningObject(Element rootElement){
		List<DefaultWarning> result = new ArrayList<DefaultWarning>();
		List<Node> warningList = rootElement.selectNodes("Warnings/Warning");
		warningList.forEach(item ->{
			Element element = (Element) item;
			DefaultWarning warning = new DefaultWarning();
			String code = element.attributeValue("Code");
			warning.setCode(code);
			warning.setType(element.attributeValue("Type"));
			warning.setShortText(element.attributeValue("ShortText"));
			warning.setCnMessage(StringUtils.ibeMessage(code));

			result.add(warning);
		});
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private AirBookModifyResponse buildResponseObject(Element rootElement){
		AirBookModifyResponse result = new AirBookModifyResponse();
		
		result.setErrorList(buildErrorObject(rootElement));
		result.setWarningList(buildWarningObject(rootElement));
		Element brIDElement = (Element) rootElement.selectSingleNode("/OTA_AirBookRS/AirReservation/BookingReferenceID");
		if(brIDElement != null){
			result.setBookingReferenceID(brIDElement.attributeValue("ID"));
			result.setBookingReferenceIDContext(brIDElement.attributeValue("ID_Context"));
		}
		
		List<Node> arList = rootElement.selectNodes("/OTA_AirBookRS/AirReservation/Comment");
		List<String> commentList = new ArrayList<String>();
		arList.forEach(item ->{
			Element element = (Element) item;
			commentList.add(element.getText());
		});
		result.setCommentList(commentList);
		
		return result;
	}
	
	private String getResponseResult(AirBookModifyRequest request) {
		String responseResult = "";
		String requestXml = "";
		String requestFilePath = null;
		String responseFilePath = null;
		try {
			requestXml = airBookModifyRequestBuilder.buildRequestXml(request);
			IBEApi airBook = ibeApiConfig.getIbeApi(IBEInterface.JP012.getId());
			String currentDateTime = DateUtils.formatDate(DateUtils.currentDate(), DateUtils.DATE_SECOND_FORMAT_2);

			String uuid = StringUtils.randomUUID(10);
			StringBuilder requestFileName = new StringBuilder();
			requestFileName.append(airBook.getId()).append("_")
					.append(airBook.getDescription()).append("_RQ_")
					.append(currentDateTime).append(uuid).append(".xml");

			StringBuilder responseFileName = new StringBuilder();
			responseFileName.append(airBook.getId()).append("_")
					.append(airBook.getDescription()).append("_RS_")
					.append(currentDateTime).append(uuid).append(".xml");

			String filePath = ibeConfig.getUatDir();
			requestFilePath = filePath + "/" + requestFileName;
			responseFilePath = filePath + "/" + responseFileName;

			responseResult = HttpClientUtils.getInstance().httpPost(ibeConfig.getUsername(),
					ibeConfig.getPassword(), airBook.getUrl(), requestXml);
		} catch (IntegrationException e) {
			throw e;
		} finally {
			FileUtils.writeFile(requestFilePath, XMLUtils.getInstance().formatXml(requestXml));
			FileUtils.writeFile(responseFilePath, XMLUtils.getInstance().formatXml(responseResult));
		}
		
		return responseResult;
	}
	
}

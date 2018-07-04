package com.sparrow.ibe.bookingservice.airbook.dao;

import com.sparrow.ibe.bookingservice.airbook.model.AirBookRequest;
import com.sparrow.ibe.bookingservice.airbook.model.AirBookResponse;
import com.sparrow.integration.dao.IntegrationDao;
import com.sparrow.integration.exception.IntegrationException;
import com.sparrow.integration.handler.ValidationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 接口类别：预订服务
 * 接口名称：自动预订服务
 * 接口ID：JP011
 * 自动预订服务的数据访问类，用于处理请求，并解析接口的返回结果
 * 
 * @author wangjc
 * @date 2014-7-3
 */
@Component
public class AirBookDao implements IntegrationDao<AirBookRequest> {

	@Autowired
	@Qualifier("airBookValidationHandler")
	private ValidationHandler<AirBookRequest> validationHandler;

	@Override
	public Serializable execute(String userId, String password, AirBookRequest request)
			throws IntegrationException {
		if (validationHandler.validate(userId, password, request)) {
			return parseResponseResult(request);
		} else {
			return validationHandler.getValidationResult();
		}
	}

	private AirBookResponse parseResponseResult(AirBookRequest request)
			throws IntegrationException {
		AirBookResponse result = null;
		/*Document document = XMLUtil.getInstance().readXMLFile(
				getResponseResult(request));
		if (document != null) {
			result = buildResponseObject(document.getRootElement());
		} else {
			result = new AirBookResponse();
			List<DefaultError> errorList = new ArrayList<DefaultError>();
			DefaultError error = new DefaultError();
			CommonErrorEnum errorEnum = CommonErrorEnum.ERROR_001;
			error.setCode(errorEnum.getErrorCode());
			error.setShortText(errorEnum.getEnMessage());
			error.setCnMessage(errorEnum.getCnMessage());
			
			errorList.add(error);
			result.setErrorList(errorList);
		}*/
		return result;
	}

	/*private List<DefaultError> buildErrorObject(Element rootElement) {
		List<DefaultError> result = new ArrayList<DefaultError>();
		List<Element> errorList = rootElement.selectNodes("Errors/Error");
		for (Element element : errorList) {
			DefaultError error = new DefaultError();
			String code = element.attributeValue("Code");
			error.setCode(code);
			error.setType(element.attributeValue("Type"));
			error.setShortText(element.attributeValue("ShortText"));
			error.setCnMessage(StringUtil.ibeMessage(code));

			result.add(error);
		}

		return result;
	}

	private List<DefaultWarning> buildWarningObject(Element rootElement) {
		List<DefaultWarning> result = new ArrayList<DefaultWarning>();
		List<Element> warningList = rootElement.selectNodes("Warnings/Warning");
		for (Element element : warningList) {
			DefaultWarning error = new DefaultWarning();
			String code = element.attributeValue("Code");
			error.setCode(code);
			error.setType(element.attributeValue("Type"));
			error.setShortText(element.attributeValue("ShortText"));
			error.setCnMessage(StringUtil.ibeMessage(code));

			result.add(error);
		}

		return result;
	}

	private AirBookResponse buildResponseObject(Element rootElement) {
		AirBookResponse result = new AirBookResponse();

		List<AirReservation> airRerservationList = new ArrayList<AirReservation>();
		List<Element> arList = rootElement.selectNodes("/OTA_AirBookRS/AirReservation");
		for (Element element : arList) {
			AirReservation airReservation = new AirReservation();
			airReservation.setFlightSegmentList(buildFlightSegmentObject(element));
			airReservation.setTravelerNameList(getTravelerNameList(element));
			airReservation.setBookingReferenceIDList(buildBookingReferenceIDObject(element));
			airReservation.setCommentList(buildCommentObject(element));
			airRerservationList.add(airReservation);
		}
		result.setAirReservationList(airRerservationList);
		result.setErrorList(buildErrorObject(rootElement));
		result.setWarningList(buildWarningObject(rootElement));

		return result;
	}

	private List<String> buildCommentObject(Element arElement) {
		List<String> commentList = new ArrayList<String>();
		List<Element> commentElements = arElement.elements("Comment");
		for (Element e : commentElements) {
			commentList.add(e.getText());
		}
		return commentList;
	}

	private List<FlightSegment> buildFlightSegmentObject(Element arElement) {
		List<FlightSegment> result = new ArrayList<FlightSegment>();
		List<Element> fsList = arElement
				.selectNodes("AirItinerary/FlightSegments/FlightSegment");
		for (Element element : fsList) {
			FlightSegment fs = new FlightSegment();
			fs
					.setDepartureDateTime(element
							.attributeValue("DepartureDateTime"));
			fs.setFlightNumber(element.attributeValue("FlightNumber"));
			String numberInParty = element.attributeValue("NumberInParty");
			if (StringUtil.isNotEmpty(numberInParty)) {
				fs.setNumberInParty(numberInParty);
			}
			fs.setArrivalDateTime(element.attributeValue("ArrivalDateTime"));
			fs.setCodeshareInd(element.attributeValue("CodeshareInd"));
			fs.setStatus(element.attributeValue("Status"));
			fs.setSegmentType(element.attributeValue("SegmentType"));
			
			Element operatingAirlineEle = element.element("OperatingAirline");
			if (operatingAirlineEle != null) {
				fs.setOperatingAirline(operatingAirlineEle.attributeValue("Code"));
				fs.setFlightNumberOfOperatingAirline(operatingAirlineEle.attributeValue("FlightNumber"));
			}
			
			Element departureAirportEle = element.element("DepartureAirport");
			if(departureAirportEle != null){
				fs.setDepartureAirport(departureAirportEle
						.attributeValue("LocationCode"));
			}
			
			Element arrivalAirportEle = element.element("ArrivalAirport");
			if(arrivalAirportEle != null){
				fs.setArrivalAirport(arrivalAirportEle
						.attributeValue("LocationCode"));
			}

			Element marketingAirlineEle = element.element("MarketingAirline");
			if(marketingAirlineEle != null){
				fs.setMarketingAirline(marketingAirlineEle.attributeValue("Code"));
			}

			Element bookingClassAvailEle = element.element("BookingClassAvail");
			if(bookingClassAvailEle != null){
				fs.setResBookDesigCode(bookingClassAvailEle
						.attributeValue("ResBookDesigCode"));
			}

			result.add(fs);
		}

		return result;
	}

	private List<String> getTravelerNameList(Element arElement) {
		List<String> result = new ArrayList<String>();
		List<Element> fsList = arElement
				.selectNodes("TravelerInfo/AirTraveler");
		for (Element element : fsList) {
			String travelerName = element
					.selectSingleNode("PersonName/Surname").getText();
			result.add(travelerName);
		}
		return result;
	}

	private List<BookingReferenceID> buildBookingReferenceIDObject(
			Element arElement) {
		List<BookingReferenceID> result = new ArrayList<BookingReferenceID>();
		List<Element> fsList = arElement.selectNodes("BookingReferenceID");
		for (Element element : fsList) {
			BookingReferenceID br = new BookingReferenceID();
			br.setId(element.attributeValue("ID"));
			br.setIdContext(element.attributeValue("ID_Context"));
			result.add(br);
		}
		return result;
	}*/
	
//	private String getResponseResult(AirBookRequest request)
//			throws IntegrationException {
//		String requestXML = AirBookRequestBuilder.getInstance().buildRequestXML(request);
//		String serviceUrl = URLConstants.IBE_AIR_BOOK;
//		
//		String currentDateTime = DateUtil.getDateFormat(new Date(), "yyyyMMddHHmmss");
//		FileUtilForTestJP.saveIBEFileForUAT(IBEInterfaceEnum.JP021, serviceUrl, requestXML, 
//				null, currentDateTime);
//		
//		String responseResult = CommonHttpClientUtil.getInstance().executeHttpRequest(
//				IBEUtil.getUserName(), IBEUtil.getPassword(), serviceUrl, requestXML);
//		
//		FileUtilForTestJP.saveIBEFileForUAT(IBEInterfaceEnum.JP021, serviceUrl, null, 
//				responseResult, currentDateTime);
//
//		return responseResult;
//	}
	
	/*private String getResponseResult(AirBookRequest request)
			throws IntegrationException {
		String responseResult = "";
		String requestXML = "";
		File requestFile = null;
		File responseFile = null;
		try {
			requestXML = AirBookRequestBuilder.getInstance().buildRequestXML(request);
			String serviceUrl = URLConstants.IBE_AIR_BOOK;
			String currentDateTime = DateUtil.getDateFormat(new Date(), "yyyyMMddHHmmss");
			IBEInterfaceEnum interfaceEnum = IBEInterfaceEnum.JP021;
			
			StringBuilder requestFileName = new StringBuilder();
			requestFileName.append(interfaceEnum.getInterfaceID()).append("_")
				.append(interfaceEnum.getInterfaceName()).append("_RQ_")
				.append(currentDateTime).append(".xml");
			
			StringBuilder responseFileName = new StringBuilder();
			responseFileName.append(interfaceEnum.getInterfaceID()).append("_")
				.append(interfaceEnum.getInterfaceName()).append("_RS_")
				.append(currentDateTime).append(".xml");
			
			String filePath = StringUtil.getIBEUATFilePath();
			String requestFilePath = filePath+requestFileName;
			String responseFilePath = filePath+responseFileName;
			
			requestFile = new File(requestFilePath);
			responseFile = new File(responseFilePath);
			try {
				if(requestFile.exists()){
					String uuid = StringUtil.randomUUID();
					String newRequestFilePath = requestFilePath.substring(0, requestFilePath.lastIndexOf("."))+"_"+uuid+".xml";
					requestFile = new File(newRequestFilePath);
					
					String newResponseFilePath = responseFilePath.substring(0, responseFilePath.lastIndexOf("."))+"_"+uuid+".xml";
					responseFile = new File(newResponseFilePath);
				}
				
				if(!requestFile.getParentFile().exists()){
					requestFile.getParentFile().mkdirs();
				}
				requestFile.createNewFile();
				responseFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
			responseResult = CommonHttpClientUtil.getInstance().executeHttpRequest(
					IBEUtil.getUserName(), IBEUtil.getPassword(), serviceUrl, requestXML);
		} catch (IntegrationException e) {
			throw e;
		} finally {
			FileUtilForTestJP.saveFile(requestFile, FileUtilForTestJP.formatXml(requestXML));
			FileUtilForTestJP.saveFile(responseFile, FileUtilForTestJP.formatXml(responseResult));
		}
		
		return responseResult;
	}*/

}

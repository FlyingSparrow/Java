package com.sparrow.ibe.bookingservice.airbook.dao;

import com.sparrow.app.config.IBEConfig;
import com.sparrow.app.init.IBEApi;
import com.sparrow.app.init.IBEApiConfig;
import com.sparrow.ibe.bookingservice.airbook.model.*;
import com.sparrow.ibe.bookingservice.airbook.builder.AirBookRequestBuilder;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    private static final Logger logger = LoggerFactory.getLogger(AirBookDao.class);

    @Autowired
    @Qualifier("airBookValidationHandler")
    private ValidationHandler<AirBookRequest> validationHandler;
    @Autowired
    private AirBookRequestBuilder airBookRequestBuilder;
    @Autowired
    private IBEApiConfig ibeApiConfig;
    @Autowired
    private IBEConfig ibeConfig;

    @Override
    public Serializable execute(String userId, String password, AirBookRequest request) {
        if (validationHandler.validate(userId, password, request)) {
            return parseResponseResult(request);
        } else {
            return validationHandler.getValidationResult();
        }
    }

    private AirBookResponse parseResponseResult(AirBookRequest request) {
        AirBookResponse result = null;
        Document document = XMLUtils.getInstance().readXmlFile(getResponseResult(request));
        if (document != null) {
            result = buildResponseObject(document.getRootElement());
        } else {
            result = new AirBookResponse();
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

    private List<DefaultError> buildErrorObject(Element rootElement) {
        List<DefaultError> result = new ArrayList<DefaultError>();
        List<Element> errorList = rootElement.selectNodes("Errors/Error");
        for (Element element : errorList) {
            DefaultError error = new DefaultError();
            String code = element.attributeValue("Code");
            error.setCode(code);
            error.setType(element.attributeValue("Type"));
            error.setShortText(element.attributeValue("ShortText"));
            error.setCnMessage(StringUtils.ibeMessage(code));

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
            error.setCnMessage(StringUtils.ibeMessage(code));

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

    private List<FlightSegment> buildFlightSegmentObject(Element arElement) {
        List<FlightSegment> result = new ArrayList<FlightSegment>();

        List<Element> fsList = arElement.selectNodes("AirItinerary/FlightSegments/FlightSegment");
        for (Element element : fsList) {
            FlightSegment fs = new FlightSegment();
            fs.setDepartureDateTime(element.attributeValue("DepartureDateTime"));
            fs.setFlightNumber(element.attributeValue("FlightNumber"));
            String numberInParty = element.attributeValue("NumberInParty");
            if (StringUtils.isNotEmpty(numberInParty)) {
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
            if (departureAirportEle != null) {
                fs.setDepartureAirport(departureAirportEle.attributeValue("LocationCode"));
            }

            Element arrivalAirportEle = element.element("ArrivalAirport");
            if (arrivalAirportEle != null) {
                fs.setArrivalAirport(arrivalAirportEle.attributeValue("LocationCode"));
            }

            Element marketingAirlineEle = element.element("MarketingAirline");
            if (marketingAirlineEle != null) {
                fs.setMarketingAirline(marketingAirlineEle.attributeValue("Code"));
            }

            Element bookingClassAvailEle = element.element("BookingClassAvail");
            if (bookingClassAvailEle != null) {
                fs.setResBookDesigCode(bookingClassAvailEle.attributeValue("ResBookDesigCode"));
            }

            result.add(fs);
        }

        return result;
    }

    private List<String> getTravelerNameList(Element arElement) {
        List<String> result = new ArrayList<String>();
        List<Element> fsList = arElement.selectNodes("TravelerInfo/AirTraveler");
        for (Element element : fsList) {
            String travelerName = element.selectSingleNode("PersonName/Surname").getText();
            result.add(travelerName);
        }
        return result;
    }

    private List<BookingReferenceID> buildBookingReferenceIDObject(Element arElement) {
        List<BookingReferenceID> result = new ArrayList<BookingReferenceID>();
        List<Element> fsList = arElement.selectNodes("BookingReferenceID");
        for (Element element : fsList) {
            BookingReferenceID br = new BookingReferenceID();
            br.setId(element.attributeValue("ID"));
            br.setIdContext(element.attributeValue("ID_Context"));
            result.add(br);
        }
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

    private String getResponseResult(AirBookRequest request) {
        String responseResult = "";
        String requestXml = "";
        String requestFilePath = null;
        String responseFilePath = null;
        try {
            requestXml = airBookRequestBuilder.buildRequestXml(request);
            IBEApi airBook = ibeApiConfig.getIbeApi(IBEInterface.JP011.getId());
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
            logger.error("IntegrationException", e);
            throw e;
        } finally {
            FileUtils.writeFile(requestFilePath, XMLUtils.getInstance().formatXml(requestXml));
            FileUtils.writeFile(responseFilePath, XMLUtils.getInstance().formatXml(responseResult));
        }

        return responseResult;
    }

}

package com.sparrow.ibe.bookingservice.airbookmodify.builder;

import com.sparrow.ibe.bookingservice.airbook.model.*;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AbmAirReservation;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AirBookModifyRQ;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AirBookModifyRequest;
import com.sparrow.ibe.bookingservice.airbookmodify.model.TravelerInfo;
import com.sparrow.integration.builder.RequestBuilder;
import com.sparrow.integration.exception.IntegrationException;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 接口类别：预订服务
 * 接口名称：自动修改服务
 * 接口ID：JP012
 * 自动修改服务的请求处理类，处理请求，解析接口的返回结果
 *
 * @author wangjc
 * @date 2014-7-3
 */
@Component
public class AirBookModifyRequestBuilder implements RequestBuilder<AirBookModifyRequest> {

    /**
     * 功能：构造请求的xml
     *
     * @author wangjc
     * @date 2014-7-11
     */
    @Override
    public String buildRequestXml(AirBookModifyRequest request) {
        if (request != null) {
            Document document = DocumentHelper.createDocument();
            Element rootElement = document.addElement("OTA_AirBookModifyRQ");

            Element posElement = rootElement.addElement("POS");
            Element sourceElement = posElement.addElement("Source");
            sourceElement.addAttribute("PseudoCityCode", request.getPseudoCityCode());

            //修改后的预定信息
            buildAirBookModifyRQElement(request.getAirBookModifyRQ(), rootElement);

            //修改前的预定信息
            buildAirReservationElement(request.getAirReservation(), rootElement);

            return document.asXML();
        } else {
            //the request is null
            throw new IntegrationException("the request is null in the class" + getClass().getCanonicalName() + ", please check your request");
        }
    }

    private void buildAirBookModifyRQElement(AirBookModifyRQ airBookModifyRQ, Element rootElement) {
        if (airBookModifyRQ != null) {
            String modificationType = airBookModifyRQ.getModificationType();
            String modificationInfo = airBookModifyRQ.getModificationInfo();

            Element abmElement = rootElement.addElement("AirBookModifyRQ");
            if (StringUtils.isNotEmpty(modificationType)) {
                abmElement.addAttribute("ModificationType", modificationType);
            }
            if (StringUtils.isNotEmpty(modificationInfo)) {
                abmElement.addAttribute("ModificationInfo", modificationInfo);
            }
            buildAbmAirReservationElement(airBookModifyRQ.getAbmAirReservationList(), abmElement);
        }
    }

    private void buildAbmAirReservationElement(List<AbmAirReservation> list, Element abmElement) {
        if (list != null) {
            for (AbmAirReservation item : list) {
                Element arElement = abmElement.addElement("AirReservation");
                buildTicketingElement(item, arElement);
                buildBookingReferenceIDElement(item.getBookingReferenceIDList(), arElement);
                buildTravelerInfoElement(item.getTravelerInfoList(), arElement);
                buildPriceInfoElement(item.getTourCode(), arElement);
                buildAirItineraryElement(item.getOriginDestinationList(), arElement);
            }
        }
    }

    private void buildTicketingElement(AbmAirReservation item, Element arElement) {
        StringBuilder sd = new StringBuilder();
        String endorsement = item.getEndorsement();
        String exchangeInfo = item.getExchangeInfo();

        if (StringUtils.isNotEmpty(endorsement) || StringUtils.isNotEmpty(exchangeInfo)) {
            Element ticketingElement = arElement.addElement("Ticketing");
            Element tiiElement = ticketingElement.addElement("TicketItemInfo");

            sd.append("<Ticketing><TicketItemInfo");
            if (StringUtils.isNotEmpty(endorsement)) {
                tiiElement.addAttribute("Endorsement", endorsement);
            }
            if (StringUtils.isNotEmpty(exchangeInfo)) {
                tiiElement.addAttribute("ExchangeInfo", exchangeInfo);
            }
        }
    }

    private void buildBookingReferenceIDElement(List<BookingReferenceID> list, Element arElement) {
        if (list != null) {
            for (BookingReferenceID item : list) {
                String id = item.getId();
                String idContext = item.getIdContext();

                Element brElement = arElement.addElement("BookingReferenceID");
                if (StringUtils.isNotEmpty(id)) {
                    brElement.addAttribute("ID", id);
                }
                if (StringUtils.isNotEmpty(idContext)) {
                    brElement.addAttribute("ID_Context", idContext);
                }
            }
        }
    }

    private void buildTravelerInfoElement(List<TravelerInfo> list, Element arElement) {
        if (list != null) {
            for (TravelerInfo item : list) {
                Element tiElement = arElement.addElement("TravelerInfo");
                buildAirTravelerElement(item.getAirTravelerList(), tiElement);
                buildSpecialReqDetailsElement(item, tiElement);
            }
        }
    }

    private void buildAirTravelerElement(List<AirTraveler> list, Element tiElement) {
        if (list != null && list.size() > 0) {
            for (AirTraveler item : list) {
                String rph = item.getRph();
                String passengerTypeCode = item.getPassengerTypeCode();
                String accompaniedByInfant = item.getAccompaniedByInfant();

                Element atElement = tiElement.addElement("AirTraveler");
                if (StringUtils.isNotEmpty(rph)) {
                    atElement.addAttribute("RPH", rph);
                }
                if (StringUtils.isNotEmpty(passengerTypeCode)) {
                    atElement.addAttribute("PassengerTypeCode", passengerTypeCode);
                }
                if (StringUtils.isNotEmpty(accompaniedByInfant)) {
                    atElement.addAttribute("AccompaniedByInfant", accompaniedByInfant);
                }
                buildPersonNameElement(item.getPersonNameList(), atElement);
                buildDocumentElement(item.getDocumentList(), atElement);
                buildTravelerRefNumberElement(item.getTravelerRefNumberList(), atElement);
            }
        }
    }

    private void buildSpecialReqDetailsElement(TravelerInfo item, Element tiElement) {
        List<OtherServiceInformation> osiList = item.getOsiList();
        List<SpecialServiceRequest> ssrList = item.getSsrList();
        List<SpecialRemark> remarkList = item.getRemarkList();

        if ((osiList != null && osiList.size() > 0)
                || (ssrList != null && ssrList.size() > 0)
                || (remarkList != null && remarkList.size() > 0)) {
            Element srdsElement = tiElement.addElement("SpecialReqDetails");
            buildOtherServiceInformationsElement(osiList, srdsElement);
            buildSpecialRemarksElement(remarkList, srdsElement);
            buildSpecialServiceRequestsElement(ssrList, srdsElement);
        }
    }

    private void buildPersonNameElement(List<PersonName> personNameList, Element atElement) {
        if (personNameList != null) {
            for (PersonName pnItem : personNameList) {
                String languageType = pnItem.getLanguageType();
                String surname = pnItem.getSurname();

                Element pnElement = atElement.addElement("PersonName");
                if (StringUtils.isNotEmpty(languageType)) {
                    pnElement.addAttribute("LanguageType", languageType);
                }
                if (StringUtils.isNotEmpty(surname)) {
                    Element surnameElement = pnElement.addElement("Surname");
                    surnameElement.addText(surname);
                }
            }
        }
    }

    private void buildDocumentElement(List<com.sparrow.ibe.bookingservice.airbook.model.Document> list, Element atElement) {
        if (list != null) {
            for (com.sparrow.ibe.bookingservice.airbook.model.Document item : list) {
                String docType = item.getDocType();
                String docTypeDetail = item.getDocTypeDetail();
                String docId = item.getDocId();
                String docIssueCountry = item.getDocIssueCountry();
                String docHolderNationality = item.getDocHolderNationality();
                String birthDate = item.getBirthDate();
                String gender = item.getGender();
                String expireDate = item.getExpireDate();
                String rph = item.getRph();

                String docHolderGivenName = item.getDocHolderGivenName();
                String docHolderSurname = item.getDocHolderSurname();
                String docHolderMiddleName = item.getDocHolderMiddleName();

                Element documentElement = atElement.addElement("Document");
                if (StringUtils.isNotEmpty(docType)) {
                    documentElement.addAttribute("DocType", docType);
                }
                if (StringUtils.isNotEmpty(docTypeDetail)) {
                    documentElement.addAttribute("DocTypeDetail", docTypeDetail);
                }
                if (StringUtils.isNotEmpty(docId)) {
                    documentElement.addAttribute("DocID", docId);
                }
                if (StringUtils.isNotEmpty(docIssueCountry)) {
                    documentElement.addAttribute("DocIssueCountry", docIssueCountry);
                }
                if (StringUtils.isNotEmpty(docHolderNationality)) {
                    documentElement.addAttribute("DocHolderNationality", docHolderNationality);
                }
                if (StringUtils.isNotEmpty(birthDate)) {
                    documentElement.addAttribute("BirthDate", birthDate);
                }
                if (StringUtils.isNotEmpty(gender)) {
                    documentElement.addAttribute("Gender", gender);
                }
                if (StringUtils.isNotEmpty(expireDate)) {
                    documentElement.addAttribute("ExpireDate", expireDate);
                }
                if (StringUtils.isNotEmpty(rph)) {
                    documentElement.addAttribute("RPH", rph);
                }

                if (StringUtils.isNotEmpty(docHolderGivenName)
                        || StringUtils.isNotEmpty(docHolderSurname)
                        || StringUtils.isNotEmpty(docHolderMiddleName)) {
                    Element dhfnElement = documentElement.addElement("DocHolderFormattedName");

                    if (StringUtils.isNotEmpty(docHolderGivenName)) {
                        Element gnElement = dhfnElement.addElement("GivenName");
                        gnElement.addText(docHolderGivenName);
                    }
                    if (StringUtils.isNotEmpty(docHolderSurname)) {
                        Element surnameElement = dhfnElement.addElement("Surname");
                        surnameElement.addText(docHolderSurname);
                    }
                    if (StringUtils.isNotEmpty(docHolderMiddleName)) {
                        Element mnElement = dhfnElement.addElement("MiddleName");
                        mnElement.addText(docHolderMiddleName);
                    }
                }
            }
        }
    }

    private void buildTravelerRefNumberElement(List<TravelerRefNumber> list, Element atElement) {
        if (list != null) {
            for (TravelerRefNumber item : list) {
                Element trnElement = atElement.addElement("TravelerRefNumber");
                trnElement.addAttribute("RPH", item.getRph());
            }
        }
    }

    private void buildOtherServiceInformationsElement(
            List<OtherServiceInformation> osiList, Element srdsElement) {
        if (osiList != null && osiList.size() > 0) {
            Element osisElement = srdsElement.addElement("OtherServiceInformations");
            for (OtherServiceInformation item : osiList) {
                String osiCode = item.getOsiCode();
                String rph = item.getRph();
                String trnRph = item.getTravelerRefNumberRPH();
                String airlineCode = item.getAirlineCode();
                String text = item.getText();

                Element osiElement = osisElement.addElement("OtherServiceInformation");
                if (StringUtils.isNotEmpty(osiCode)) {
                    osiElement.addAttribute("Code", osiCode);
                }
                if (StringUtils.isNotEmpty(rph)) {
                    osiElement.addAttribute("RPH", rph);
                }
                if (StringUtils.isNotEmpty(trnRph)) {
                    Element trnElement = osiElement.addElement("TravelerRefNumber");
                    trnElement.addAttribute("RPH", trnRph);
                }
                if (StringUtils.isNotEmpty(airlineCode)) {
                    Element airlineElement = osiElement.addElement("Airline");
                    airlineElement.addAttribute("Code", airlineCode);
                }
                if (StringUtils.isNotEmpty(text)) {
                    Element textElement = osiElement.addElement("Text");
                    textElement.addText(text);
                }
            }
        }
    }

    private void buildSpecialRemarksElement(List<SpecialRemark> remarkList, Element srdsElement) {
        if (remarkList != null && remarkList.size() > 0) {
            Element srsElement = srdsElement.addElement("SpecialRemarks");
            for (SpecialRemark item : remarkList) {
                String trnRph = item.getTravelerRefNumberRPH();
                String frnRph = item.getFlightRefNumberRPH();
                String text = item.getText();

                Element srElement = srsElement.addElement("SpecialRemark");
                if (StringUtils.isNotEmpty(trnRph)) {
                    Element trnElement = srElement.addElement("TravelerRefNumber");
                    trnElement.addAttribute("RPH", trnRph);
                }
                if (StringUtils.isNotEmpty(frnRph)) {
                    Element frnElement = srElement.addElement("FlightRefNumber");
                    frnElement.addAttribute("RPH", frnRph);
                }
                if (StringUtils.isNotEmpty(text)) {
                    Element textElement = srElement.addElement("Text");
                    textElement.addText(text);
                }
            }
        }
    }

    private void buildSpecialServiceRequestsElement(List<SpecialServiceRequest> ssrList, Element srdsElement) {
        if (ssrList != null && ssrList.size() > 0) {
            Element ssrsElement = srdsElement.addElement("SpecialServiceRequests");
            for (SpecialServiceRequest item : ssrList) {
                String ssrCode = item.getSsrCode();
                String serviceQuantity = item.getServiceQuantity();
                String status = item.getStatus();
                String rph = item.getRph();
                String airlineCode = item.getAirlineCode();
                String text = item.getText();
                String frnRph = item.getFlightRefNumberRPH();
                String trnRph = item.getTravelerRefNumberRPH();

                Element ssrElement = ssrsElement.addElement("SpecialServiceRequest");
                if (StringUtils.isNotEmpty(ssrCode)) {
                    ssrElement.addAttribute("SSRCode", ssrCode);
                }
                if (StringUtils.isNotEmpty(serviceQuantity)) {
                    ssrElement.addAttribute("ServiceQuantity", serviceQuantity);
                }
                if (StringUtils.isNotEmpty(status)) {
                    ssrElement.addAttribute("Status", status);
                }
                if (StringUtils.isNotEmpty(rph)) {
                    ssrElement.addAttribute("RPH", rph);
                }

                if (StringUtils.isNotEmpty(airlineCode)) {
                    Element airlineElement = ssrElement.addElement("Airline");
                    airlineElement.addAttribute("Code", airlineCode);
                }
                if (StringUtils.isNotEmpty(text)) {
                    Element textElement = ssrElement.addElement("Text");
                    textElement.addText(text);
                }
                if (StringUtils.isNotEmpty(frnRph)) {
                    Element frnElement = ssrElement.addElement("FlightRefNumber");
                    frnElement.addAttribute("RPH", frnRph);
                }
                if (StringUtils.isNotEmpty(trnRph)) {
                    Element trnElement = ssrElement.addElement("TravelerRefNumber");
                    trnElement.addAttribute("RPH", trnRph);
                }
            }
        }
    }

    private void buildPriceInfoElement(String tourCode, Element arElement) {
        if (StringUtils.isNotEmpty(tourCode)) {
            Element piElement = arElement.addElement("PriceInfo");
            Element itfElement = piElement.addElement("ItinTotalFare");
            Element tcElement = itfElement.addElement("TourCode");
            tcElement.addText(tourCode);
        }
    }

    private void buildAirItineraryElement(List<OriginDestinationOption> list, Element arElement) {
        if (list != null && list.size() > 0) {
            Element aiElement = arElement.addElement("AirItinerary");
            Element odosElement = aiElement.addElement("OriginDestinationOptions");
            for (OriginDestinationOption item : list) {
                Element odoElement = odosElement.addElement("OriginDestinationOption");
                List<FlightSegment> fsList = item.getFlightSegmentList();
                if (fsList != null) {
                    for (FlightSegment fsItem : fsList) {
                        String rph = fsItem.getRph();
                        String departureDateTime = fsItem.getDepartureDateTime();
                        String arrivalDateTime = fsItem.getArrivalDateTime();
                        String flightNumber = fsItem.getFlightNumber();
                        String numberInParty = fsItem.getNumberInParty();
                        String status = fsItem.getStatus();
                        String segmentType = fsItem.getSegmentType();
                        String operatingAirline = fsItem.getOperatingAirline();
                        String flightNumberOfOperatingAirline = fsItem.getFlightNumberOfOperatingAirline();
                        String departureAirport = fsItem.getDepartureAirport();
                        String arrivalAirport = fsItem.getArrivalAirport();
                        String marketingAirline = fsItem.getMarketingAirline();
                        String resBookDesigCode = fsItem.getResBookDesigCode();
                        String codeshareInd = fsItem.getCodeshareInd();
                        String airEquipType = fsItem.getAirEquipType();

                        Element fsElement = odoElement.addElement("FlightSegment");
                        if (StringUtils.isNotEmpty(rph)) {
                            fsElement.addAttribute("RPH", rph);
                        }
                        if (StringUtils.isNotEmpty(departureDateTime)) {
                            fsElement.addAttribute("DepartureDateTime", departureDateTime);
                        }
                        if (StringUtils.isNotEmpty(arrivalDateTime)) {
                            fsElement.addAttribute("ArrivalDateTime", arrivalDateTime);
                        }
                        if (StringUtils.isNotEmpty(flightNumber)) {
                            fsElement.addAttribute("FlightNumber", flightNumber);
                        }
                        if (StringUtils.isNotEmpty(numberInParty)) {
                            fsElement.addAttribute("NumberInParty", numberInParty + "");
                        }
                        if (StringUtils.isNotEmpty(status)) {
                            fsElement.addAttribute("Status", status);
                        }
                        if (StringUtils.isNotEmpty(segmentType)) {
                            fsElement.addAttribute("SegmentType", segmentType);
                        }
                        if (StringUtils.isNotEmpty(codeshareInd)) {
                            fsElement.addAttribute("CodeshareInd", codeshareInd);
                        }
                        if (StringUtils.isNotEmpty(airEquipType)) {
                            fsElement.addAttribute("AirEquipType", airEquipType);
                        }

                        if (StringUtils.isNotEmpty(operatingAirline)
                                || StringUtils.isNotEmpty(flightNumberOfOperatingAirline)) {
                            Element oaElement = fsElement.addElement("OperatingAirline");
                            if (StringUtils.isNotEmpty(operatingAirline)) {
                                oaElement.addAttribute("Code", operatingAirline);
                            }
                            if (StringUtils.isNotEmpty(flightNumberOfOperatingAirline)) {
                                oaElement.addAttribute("FlightNumber", flightNumberOfOperatingAirline);
                            }
                        }
                        if (StringUtils.isNotEmpty(departureAirport)) {
                            Element daElement = fsElement.addElement("DepartureAirport");
                            daElement.addAttribute("LocationCode", departureAirport);
                        }
                        if (StringUtils.isNotEmpty(arrivalAirport)) {
                            Element aaElement = fsElement.addElement("ArrivalAirport");
                            aaElement.addAttribute("LocationCode", arrivalAirport);
                        }
                        if (StringUtils.isNotEmpty(marketingAirline)) {
                            Element maElement = fsElement.addElement("MarketingAirline");
                            maElement.addAttribute("Code", marketingAirline);
                        }
                        if (StringUtils.isNotEmpty(resBookDesigCode)) {
                            Element bcaElement = fsElement.addElement("BookingClassAvail");
                            bcaElement.addAttribute("ResBookDesigCode", resBookDesigCode);
                        }
                    }
                }
            }
        }
    }

    private void buildAirReservationElement(AirReservation airReservation, Element rootElement) {
        if (airReservation != null) {
            Element arElement = rootElement.addElement("AirReservation");
            buildAirItineraryElement(airReservation.getOriginDestinationList(), arElement);
            buildTravelerInfoElement(airReservation.getTravelerInfoList(), arElement);
            buildBookingReferenceIDElement(airReservation.getBookingReferenceIDList(), arElement);
        }
    }

    private void buildFlightSegmentElement(List<FlightSegment> flightSegmentList, Element arElement) {

    }

}

package com.sparrow.ibe.bookingservice.airbook.builder;

import com.sparrow.ibe.bookingservice.airbook.model.*;
import com.sparrow.integration.builder.RequestBuilder;
import com.sparrow.integration.exception.IntegrationException;
import com.sparrow.utils.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 接口类别：预订服务
 * 接口名称：自动预订服务
 * 接口ID：JP011
 * 自动预订服务的请求构造类，用于生成请求的XML
 *
 * @author wangjc
 * @date 2014-7-7
 */
@Component
public class AirBookRequestBuilder implements RequestBuilder<AirBookRequest> {

    /**
     * 功能：构造请求的xml
     *
     * @author wangjc2
     * @date 2014-7-11
     */
    @Override
    public String buildRequestXML(AirBookRequest request)
            throws IntegrationException {
        if (request == null) {
            throw new IntegrationException("the request is null in the class" + getClass().getCanonicalName()
                    + ", please check your request");
        }

        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("OTA_AirBookRQ");
        String displayResInd = request.getDisplayResInd();
        if (StringUtils.isNotEmpty(displayResInd)) {
            rootElement.addAttribute("DisplayResInd", displayResInd);
        }

        String ptcBindInd = request.getPtcBindInd();
        if (StringUtils.isNotEmpty(ptcBindInd)) {
            rootElement.addAttribute("PTCBindInd", ptcBindInd);
        }


        String segmentCheckInd = request.getSegmentCheckInd();
        if (StringUtils.isNotEmpty(segmentCheckInd)) {
            rootElement.addAttribute("SegmentCheckInd", segmentCheckInd);
        }

        String autoARNKInd = request.getAutoARNKInd();
        if (StringUtils.isNotEmpty(autoARNKInd)) {
            rootElement.addAttribute("AutoARNKInd", autoARNKInd);
        }

        Element posElement = rootElement.addElement("POS");
        Element sourceElement = posElement.addElement("Source");
        sourceElement.addAttribute("PseudoCityCode", request.getPseudoCityCode());

        buildAirItineraryElement(request.getOriginDestinationOptionList(), rootElement);
        buildTravelerInfoElement(request, rootElement);

        String ticketTimeLimit = request.getTicketTimeLimit();
        if (StringUtils.isNotEmpty(ticketTimeLimit)) {
            Element ticketingElement = rootElement.addElement("Ticketing");
            ticketingElement.addAttribute("TicketTimeLimit", ticketTimeLimit);
        }
        buildTPAExtensionsElement(request, rootElement);

        return document.asXML();
    }

    private void buildAirItineraryElement(List<OriginDestinationOption> list, Element rootElement) {
        if (list != null) {
            Element aiElement = rootElement.addElement("AirItinerary");
            Element odosElement = aiElement.addElement("OriginDestinationOptions");
            for (OriginDestinationOption item : list) {
                Element odoElement = odosElement.addElement("OriginDestinationOption");
                buildFlightSegmentElement(item.getFlightSegmentList(), odoElement);
            }
        }
    }

    private void buildFlightSegmentElement(List<FlightSegment> list, Element odoElement) {
        if (list != null) {
            for (FlightSegment fs : list) {
                String rph = fs.getRph();
                String departureDateTime = fs.getDepartureDateTime();
                String arrivalDateTime = fs.getArrivalDateTime();
                String codeshareInd = fs.getCodeshareInd();
                String flightNumber = fs.getFlightNumber();
                String status = fs.getStatus();
                String segmentType = fs.getSegmentType();
                String numberInParty = fs.getNumberInParty();

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
                if (StringUtils.isNotEmpty(codeshareInd)) {
                    fsElement.addAttribute("CodeshareInd", codeshareInd);
                }
                if (StringUtils.isNotEmpty(flightNumber)) {
                    fsElement.addAttribute("FlightNumber", flightNumber);
                }
                if (StringUtils.isNotEmpty(status)) {
                    fsElement.addAttribute("Status", status);
                }
                if (StringUtils.isNotEmpty(numberInParty)) {
                    fsElement.addAttribute("NumberInParty", numberInParty);
                }
                if (StringUtils.isNotEmpty(segmentType)) {
                    fsElement.addAttribute("SegmentType", segmentType);
                }

                String operatingAirline = fs.getOperatingAirline();
                String flightNumberOfOperatingAirline = fs.getFlightNumberOfOperatingAirline();
                String departureAirport = fs.getDepartureAirport();
                String arrivalAirport = fs.getArrivalAirport();
                String airEquipType = fs.getAirEquipType();
                String marketingAirline = fs.getMarketingAirline();
                String flightNumberOfMarketingAirline = fs.getFlightNumberOfMarketingAirline();
                String resBookDesigCode = fs.getResBookDesigCode();

                if (StringUtils.isNotEmpty(operatingAirline) || StringUtils.isNotEmpty(flightNumberOfOperatingAirline)) {
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
                if (StringUtils.isNotEmpty(airEquipType)) {
                    Element equipmentElement = fsElement.addElement("Equipment");
                    equipmentElement.addAttribute("AirEquipType", airEquipType);
                }
                if (StringUtils.isNotEmpty(marketingAirline)) {
                    Element maElement = fsElement.addElement("MarketingAirline");
                    maElement.addAttribute("Code", marketingAirline);

                    if (StringUtils.isNotEmpty(flightNumberOfMarketingAirline)) {
                        maElement.addAttribute("FlightNumber", flightNumberOfMarketingAirline);
                    }
                }
                if (StringUtils.isNotEmpty(resBookDesigCode)) {
                    Element bcaElement = fsElement.addElement("BookingClassAvail");
                    bcaElement.addAttribute("ResBookDesigCode", resBookDesigCode);
                }
            }
        }
    }

    private void buildTravelerInfoElement(AirBookRequest request, Element rootElement) {
        Element tiElement = rootElement.addElement("TravelerInfo");
        buildAirTravelerElement(request.getAirTravelerList(), tiElement);
        buildSpecialReqDetailsElement(request, tiElement);
    }

    private void buildAirTravelerElement(List<AirTraveler> list, Element tiElement) {
        if (list != null && list.size() > 0) {
            for (AirTraveler item : list) {
                String gender = item.getGender();
                String passengerTypeCode = item.getPassengerTypeCode();
                List<String> flightSegmentRPHList = item.getFlightSegmentRPHList();
                String comment = item.getComment();
                String birthDate = item.getBirthDate();

                Element atElement = tiElement.addElement("AirTraveler");
                if (StringUtils.isNotEmpty(birthDate)) {
                    atElement.addAttribute("BirthDate", birthDate);
                }
                if (StringUtils.isNotEmpty(gender)) {
                    atElement.addAttribute("Gender", gender);
                }
                if (StringUtils.isNotEmpty(passengerTypeCode)) {
                    atElement.addAttribute("PassengerTypeCode", passengerTypeCode);
                }

                buildPersonNameElement(item.getPersonNameList(), atElement);
                buildDocumentElement(item.getDocumentList(), atElement);
                PassengerTypeQuantity ptq = item.getPassengerTypeQuantity();
                if (ptq != null) {
                    String age = ptq.getAge();
                    if (StringUtils.isNotEmpty(age)) {
                        Element ptqElement = atElement.addElement("PassengerTypeQuantity");
                        ptqElement.addAttribute("Age", age);
                    }
                } else {
                    atElement.addElement("PassengerTypeQuantity");
                }

                buildTravelerRefNumberElement(item.getTravelerRefNumberList(), atElement);

                String rphOfTravelerRefNumber = item.getRphOfTravelerRefNumber();
                if (StringUtils.isNotEmpty(rphOfTravelerRefNumber)) {
                    Element travelerRefNumber = atElement.addElement("TravelerRefNumber");
                    travelerRefNumber.addAttribute("RPH", rphOfTravelerRefNumber);
                }

                if (flightSegmentRPHList != null && flightSegmentRPHList.size() > 0) {
                    Element fssElement = atElement.addElement("FlightSegmentRPHs");
                    for (String fsRph : flightSegmentRPHList) {
                        Element fsElement = fssElement.addElement("FlightSegmentRPH");
                        fsElement.addText(fsRph);
                    }
                }
                buildDocumentFlightBindingElement(item.getDocumentFlightBinding(), atElement);
                buildAddressFlightBindingElement(item.getAddressFlightBindingList(), atElement);
                buildAddressElement(item.getAddressList(), atElement);
                if (StringUtils.isNotEmpty(comment)) {
                    Element commentElement = atElement.addElement("Comment");
                    commentElement.addText(comment);
                }
            }
        }
    }

    private void buildDocumentElement(List<com.sparrow.ibe.bookingservice.airbook.model.Document> list, Element tiElement) {
        if (list != null) {
            for (com.sparrow.ibe.bookingservice.airbook.model.Document item : list) {
                String docType = item.getDocType();
                String docId = item.getDocId();
                String docTypeDetail = item.getDocTypeDetail();
                String docHolderInd = item.getDocHolderInd();
                String docIssueCountry = item.getDocIssueCountry();
                String docHolderNationality = item.getDocHolderNationality();
                String birthDate = item.getBirthDate();
                String gender = item.getGender();
                String expireDate = item.getExpireDate();
                String rph = item.getRph();
                String givenName = item.getDocHolderGivenName();
                String surname = item.getDocHolderSurname();
                String middleName = item.getDocHolderMiddleName();

                Element documentElement = tiElement.addElement("Document");
                if (StringUtils.isNotEmpty(rph)) {
                    documentElement.addAttribute("RPH", rph);
                }
                if (StringUtils.isNotEmpty(docType)) {
                    documentElement.addAttribute("DocType", docType);
                }
                if (StringUtils.isNotEmpty(docId)) {
                    documentElement.addAttribute("DocID", docId);
                }
                if (StringUtils.isNotEmpty(docTypeDetail)) {
                    documentElement.addAttribute("DocTypeDetail", docTypeDetail);
                }
                if (StringUtils.isNotEmpty(docHolderInd)) {
                    documentElement.addAttribute("DocHolderInd", docHolderInd);
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
                if (StringUtils.isNotEmpty(givenName)
                        || StringUtils.isNotEmpty(surname)
                        || StringUtils.isNotEmpty(middleName)) {
                    Element dhfnElement = documentElement.addElement("DocHolderFormattedName");
                    if (StringUtils.isNotEmpty(givenName)) {
                        Element gnElement = dhfnElement.addElement("GivenName");
                        gnElement.addText(givenName);
                    }
                    if (StringUtils.isNotEmpty(middleName)) {
                        Element mnElement = dhfnElement.addElement("MiddleName");
                        mnElement.addText(middleName);
                    } else {
                        dhfnElement.addElement("MiddleName");
                    }
                    if (StringUtils.isNotEmpty(surname)) {
                        Element surnameElement = dhfnElement.addElement("Surname");
                        surnameElement.addText(surname);
                    }
                }
            }
        }
    }

    private void buildPersonNameElement(List<PersonName> personNameList, Element tiElement) {
        if (personNameList != null) {
            for (PersonName personName : personNameList) {
                String languageType = personName.getLanguageType();
                String surname = personName.getSurname();

                Element pnElement = tiElement.addElement("PersonName");

                if (StringUtils.isNotEmpty(languageType)) {
                    pnElement.addAttribute("LanguageType", languageType);
                } else {
                    pnElement.addAttribute("LanguageType", "ZH");
                }
                if (StringUtils.isNotEmpty(surname)) {
                    Element surnameElement = pnElement.addElement("Surname");
                    surnameElement.addText(surname);
                }
            }
        }
    }

    private void buildTravelerRefNumberElement(List<TravelerRefNumber> travelerRefNumberList, Element tiElement) {
        if (travelerRefNumberList != null) {
            for (TravelerRefNumber trnItem : travelerRefNumberList) {
                String trnRph = trnItem.getRph();
                String trnInfantTravelerRPH = trnItem.getInfantTravelerRPH();

                Element trnElement = tiElement.addElement("TravelerRefNumber");
                if (StringUtils.isNotEmpty(trnRph)) {
                    trnElement.addAttribute("RPH", trnRph);
                }
                if (StringUtils.isNotEmpty(trnInfantTravelerRPH)) {
                    trnElement.addAttribute("InfantTravelerRPH", trnInfantTravelerRPH);
                }
            }
        }
    }

    private void buildDocumentFlightBindingElement(DocumentFlightBinding dfb, Element atElement) {
        if (dfb != null) {
            String documentRPH = dfb.getDocumentRPH();
            String flightSegmentRPH = dfb.getFlightSegmentRPH();

            if (StringUtils.isNotEmpty(documentRPH) || StringUtils.isNotEmpty(flightSegmentRPH)) {
                Element dfbElement = atElement.addElement("DocumentFlightBinding");

                if (StringUtils.isNotEmpty(documentRPH)) {
                    Element documentRphElement = dfbElement.addElement("DocumentRPH");
                    documentRphElement.addText(documentRPH);
                }
                if (StringUtils.isNotEmpty(flightSegmentRPH)) {
                    Element fsRphElement = dfbElement.addElement("FlightSegmentRPH");
                    fsRphElement.addText(flightSegmentRPH);
                }
            }
        }
    }

    private void buildAddressFlightBindingElement(
            List<AddressFlightBinding> addressFlightBindingList,
            Element atElement) {
        if (addressFlightBindingList != null) {
            for (AddressFlightBinding item : addressFlightBindingList) {
                Element afbElement = atElement.addElement("AddressFlightBinding");
                String addressRPH = item.getAddressRPH();
                String flightSegmentRPH = item.getFlightSegmentRPH();
                if (StringUtils.isNotEmpty(addressRPH)) {
                    Element arElement = afbElement.addElement("AddressRPH");
                    arElement.addText(addressRPH);
                }
                if (StringUtils.isNotEmpty(flightSegmentRPH)) {
                    Element fsrElement = afbElement.addElement("FlightSegmentRPH");
                    fsrElement.addText(flightSegmentRPH);
                }
            }
        }
    }

    private void buildAddressElement(List<Address> addressList,
                                     Element atElement) {
        if (addressList != null) {
            for (Address item : addressList) {
                Element addressElement = atElement.addElement("Address");
                String rph = item.getRph();
                String type = item.getType();
                String countryName = item.getCountryName();
                String stateProv = item.getStateProv();
                String cityName = item.getCityName();
                String addressLine = item.getAddressLine();
                String postalCode = item.getPostalCode();
                if (StringUtils.isNotEmpty(rph)) {
                    addressElement.addAttribute("RPH", rph);
                }
                if (StringUtils.isNotEmpty(type)) {
                    addressElement.addAttribute("Type", type);
                }
                if (StringUtils.isNotEmpty(countryName)) {
                    Element cnElement = addressElement.addElement("CountryName");
                    cnElement.addAttribute("Code", countryName);
                }
                if (StringUtils.isNotEmpty(stateProv)) {
                    Element spElement = addressElement.addElement("StateProv");
                    spElement.addAttribute("StateCode", stateProv);
                }
                if (StringUtils.isNotEmpty(cityName)) {
                    Element cnElement = addressElement.addElement("CityName");
                    cnElement.addText(cityName);
                }
                if (StringUtils.isNotEmpty(addressLine)) {
                    Element alElement = addressElement.addElement("AddressLine");
                    alElement.addText(addressLine);
                }
                if (StringUtils.isNotEmpty(postalCode)) {
                    Element pcElement = addressElement.addElement("PostalCode");
                    pcElement.addText(postalCode);
                }
            }
        }
    }

    private void buildSpecialReqDetailsElement(AirBookRequest request, Element tiElement) {
        List<SpecialServiceRequest> ssrList = request.getSsrList();
        List<OtherServiceInformation> osiList = request.getOsiList();
        List<SpecialRemark> rmkList = request.getRmkList();

        if (isHaveSpecialRequirement(ssrList, osiList, rmkList)) {
            Element srdsElement = tiElement.addElement("SpecialReqDetails");
            buildSpecialServiceRequestsElement(ssrList, srdsElement);
            buildOtherServiceInformationsElement(osiList, srdsElement);
            buildSpecialRemarksElement(rmkList, srdsElement);
        }
    }

    /**
     * 是否有特殊需求
     * @param ssrList
     * @param osiList
     * @param rmkList
     * @return
     */
    private boolean isHaveSpecialRequirement(List<SpecialServiceRequest> ssrList,
                                             List<OtherServiceInformation> osiList,
                                             List<SpecialRemark> rmkList) {
        return (ssrList != null && ssrList.size() > 0)
                || (osiList != null && osiList.size() > 0)
                || (rmkList != null && rmkList.size() > 0);
    }

    private void buildSpecialServiceRequestsElement(List<SpecialServiceRequest> ssrList, Element srdsElement) {
        if (ssrList != null && ssrList.size() > 0) {
            Element ssrsElement = srdsElement.addElement("SpecialServiceRequests");

            for (SpecialServiceRequest item : ssrList) {
                String ssrCode = item.getSsrCode();
                String status = item.getStatus();
                String airlineCode = item.getAirlineCode();
                String frnRph = item.getFlightRefNumberRPH();
                String trnRph = item.getTravelerRefNumberRPH();
                String text = item.getText();
                String serviceQuantity = item.getServiceQuantity();
                String ssrRph = item.getRph();
                Element ssrElement = ssrsElement.addElement("SpecialServiceRequest");

                if (StringUtils.isNotEmpty(ssrCode)) {
                    ssrElement.addAttribute("SSRCode", ssrCode);
                }

                if (StringUtils.isNotEmpty(serviceQuantity)) {
                    ssrElement.addAttribute("ServiceQuantity", serviceQuantity);
                }

                if (StringUtils.isNotEmpty(ssrRph)) {
                    ssrElement.addAttribute("RPH", ssrRph);
                }

                if (StringUtils.isNotEmpty(status)) {
                    ssrElement.addAttribute("Status", status);
                }

                if (StringUtils.isNotEmpty(airlineCode)) {
                    Element airlineElement = ssrElement.addElement("Airline");
                    airlineElement.addAttribute("Code", airlineCode);
                }
                if (StringUtils.isNotEmpty(frnRph)) {
                    Element frnElement = ssrElement.addElement("FlightRefNumber");
                    frnElement.addAttribute("RPH", frnRph);
                }
                if (StringUtils.isNotEmpty(trnRph)) {
                    Element trnElement = ssrElement.addElement("TravelerRefNumber");
                    trnElement.addAttribute("RPH", trnRph);
                }
                if (StringUtils.isNotEmpty(text)) {
                    Element textElement = ssrElement.addElement("Text");
                    textElement.addText(text);
                }
            }
        }
    }

    private void buildOtherServiceInformationsElement(List<OtherServiceInformation> osiList, Element srdsElement) {
        if (osiList != null && osiList.size() > 0) {
            Element osisElement = srdsElement.addElement("OtherServiceInformations");

            for (OtherServiceInformation item : osiList) {
                String osiCode = item.getOsiCode();
                String airlineCode = item.getAirlineCode();
                String text = item.getText();
                String trnRph = item.getTravelerRefNumberRPH();

                Element osiElement = osisElement.addElement("OtherServiceInformation");
                if (StringUtils.isNotEmpty(osiCode)) {
                    osiElement.addAttribute("Code", osiCode);
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

    private void buildSpecialRemarksElement(List<SpecialRemark> rmkList, Element srdsElement) {
        if (rmkList != null && rmkList.size() > 0) {
            Element srsElement = srdsElement.addElement("SpecialRemarks");

            for (SpecialRemark item : rmkList) {
                String frnRph = item.getFlightRefNumberRPH();
                String trnRph = item.getTravelerRefNumberRPH();
                String text = item.getText();
                String airlineCode = item.getAirlineCode();

                Element srElement = srsElement.addElement("SpecialRemark");
                if (StringUtils.isNotEmpty(frnRph)) {
                    Element frnElement = srElement.addElement("FlightRefNumber");
                    frnElement.addAttribute("RPH", frnRph);
                }
                if (StringUtils.isNotEmpty(trnRph)) {
                    Element trnElement = srElement.addElement("TravelerRefNumber");
                    trnElement.addAttribute("RPH", trnRph);
                }
                if (StringUtils.isNotEmpty(text)) {
                    Element textElement = srElement.addElement("Text");
                    textElement.addText(text);
                }
                if (StringUtils.isNotEmpty(airlineCode)) {
                    Element airlineElement = srElement.addElement("Airline");
                    airlineElement.addAttribute("Code", airlineCode);
                }
            }
        }
    }

    private void buildTPAExtensionsElement(AirBookRequest request, Element rootElement) {
        Element teElement = rootElement.addElement("TPA_Extensions");
        List<String> contactInfoList = request.getContactInfoList();
        String envelopType = request.getEnvelopType();
        String envelopDelay = request.getEnvelopDelay();

        if (contactInfoList != null) {
            for (String contactInfo : contactInfoList) {
                Element ciElement = teElement.addElement("ContactInfo");
                ciElement.addText(contactInfo);
            }
        }
        if (StringUtils.isNotEmpty(envelopType)) {
            Element etElement = teElement.addElement("EnvelopType");
            etElement.addText(envelopType);
        }
        if (StringUtils.isNotEmpty(envelopDelay)) {
            Element edElement = teElement.addElement("EnvelopDelay");
            edElement.addText(envelopDelay);
        }
    }

}

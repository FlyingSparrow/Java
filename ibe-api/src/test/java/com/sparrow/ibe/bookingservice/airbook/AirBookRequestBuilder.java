package com.sparrow.ibe.bookingservice.airbook;

import com.sparrow.ibe.bookingservice.airbook.model.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 接口类别：预订服务
 * 接口名称：自动预订服务
 * 接口ID：JP011
 * 自动预订服务的单元测试的请求构造类
 *
 * @author wangjianchun
 * @create 2018/7/4
 */
public class AirBookRequestBuilder {

    /**
     * 功能：构造请求参数
     *
     * @author wanglw
     * @date 2014-8-14
     */
    public AirBookRequest buildRequest(int type) {
        AirBookRequest request = new AirBookRequest();
        //输入参数结构：2大信息组
        //1.POS信息    2.出票请求信息（PNR号信息     票面信息    打票机信息 ）
        if (type == 1) {
//            request = buildRequest01();    //场景1
        } else if (type == 2) {
//            request = buildRequest02();    //场景2
        } else if (type == 3) {
//            request = buildRequest03();    //场景3
        } else if (type == 4) {
//            request = buildRequest04();    //场景4
        } else if (type == 5) {
//            request = buildRequest05();    //场景5
        } else if (type == 6) {
            request = buildRequest06();    //场景6
        } else if (type == 7) {
//            request = buildRequest07();    //场景7
        } else if (type == 8) {
//            request = buildRequest08();    //场景8
        } else if (type == 9) {
//            request = buildRequest09();    //场景9
        } else if (type == 10) {
            request = buildRequest10();    //场景10
        } else if (type == 11) {
//            request = buildRequest11();    //场景11
        } else if (type == 12) {
//            request = buildRequest12();    //场景12
        } else if (type == 13) {
//            request = buildRequest13();    //场景13
        } else if (type == 14) {
            request = buildRequest14();   //场景14
        } else if (type == 15) {
//            request = buildRequest15();    //场景15
        } else if (type == 16) {
//            request = buildRequest16();    //场景16
        } else if (type == 101) {
            request = buildRequest101();    //场景16
        }
        return request;
    }

    /**
     * 场景06：成人+儿童+单程（是否同PNR，否/是均支持）
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest06() {
        //输入参数结构(7大信息)
        //1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
        //3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）

        AirBookRequest request = new AirBookRequest();
        request.setSegmentCheckInd("false");  //是否验证航段状态
        request.setPtcBindInd("false");        //是否同类型旅客预定在同一 PNR
        request.setDisplayResInd("false");    // 是否作 RT

        //1.POS信息
        request.setPseudoCityCode("sparrow");

        //2.行程信息
        List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
        OriginDestinationOption od = new OriginDestinationOption();
        List<FlightSegment> fsList = new ArrayList<FlightSegment>();
        FlightSegment fs = new FlightSegment();
        fs.setRph("1");
        fs.setDepartureDateTime("2014-09-29T07:30:00");
        fs.setArrivalDateTime("2014-09-29T09:40:00");
        fs.setCodeshareInd("false");
        fs.setFlightNumber("1831");
        fs.setStatus("NN");
        fs.setSegmentType("NORMAL");
        fs.setDepartureAirport("PEK");
        fs.setArrivalAirport("SHA");    //SHA HKG
        fs.setAirEquipType("333");
        fs.setMarketingAirline("CA");
        fs.setResBookDesigCode("Y");
        //fs.setDepartureDateTime("2014-08-29T07:00:00");
        //fs.setArrivalDateTime("2014-08-29T09:10:00");
        //fs.setFlightNumber("MU5138");   //MU5138
        //fs.setAirEquipType("733");
        //fs.setMarketingAirline("MU");
        fsList.add(fs);
        od.setFlightSegmentList(fsList);
        odList.add(od);
        request.setOriginDestinationOptionList(odList);

        //3.1旅客信息
        List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
        AirTraveler airTraveler = new AirTraveler();
        airTraveler.setGender("MALE");
        airTraveler.setPassengerTypeCode("ADT");        //成人

        List<PersonName> personNameList = new ArrayList<PersonName>();
        PersonName personName = new PersonName();
        personName.setLanguageType("ZH");
        personName.setSurname("高明");
        personNameList.add(personName);

        List<Document> documentList = new ArrayList<Document>();
        Document document = new Document();
        document.setDocType("NI");
        document.setDocId("120221197001011150");
        documentList.add(document);

        List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
        TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
        travelerRefNumber.setRph("1");
        travelerRefNumberList.add(travelerRefNumber);

        airTraveler.setPersonNameList(personNameList);
        airTraveler.setDocumentList(documentList);
        airTraveler.setRph("1");
        airTraveler.setComment("HK");
        airTraveler.setTravelerRefNumberList(travelerRefNumberList);
        airTravelerList.add(airTraveler);

        AirTraveler airTraveler1 = new AirTraveler();
        airTraveler1.setGender("MALE");
        airTraveler1.setPassengerTypeCode("ADT");            //儿童

        List<PersonName> personNameList1 = new ArrayList<PersonName>();
        PersonName personName1 = new PersonName();
        personName1.setLanguageType("ZH");
        personName1.setSurname("高小明");
        personNameList1.add(personName1);

        List<Document> documentList1 = new ArrayList<Document>();
        Document document1 = new Document();
        document1.setDocType("NI");
        document1.setDocId("29506004");
        documentList1.add(document1);

        List<TravelerRefNumber> travelerRefNumberList1 = new ArrayList<TravelerRefNumber>();
        TravelerRefNumber travelerRefNumber1 = new TravelerRefNumber();
        travelerRefNumber1.setRph("2");
        travelerRefNumberList1.add(travelerRefNumber1);

        airTraveler1.setPersonNameList(personNameList1);
        airTraveler1.setDocumentList(documentList1);
        airTraveler1.setRph("2");
        airTraveler1.setComment("HK");
        airTraveler1.setTravelerRefNumberList(travelerRefNumberList1);
        airTravelerList.add(airTraveler1);
        request.setAirTravelerList(airTravelerList);

        //旅客预定的服务信息 OSI
        List<OtherServiceInformation> osiList = new ArrayList<OtherServiceInformation>();
        OtherServiceInformation osi_01 = new OtherServiceInformation();
        osi_01.setOsiCode("OTHS");
        //osi_01.setAirlineCode("MU");
        osi_01.setAirlineCode("CA");
        osi_01.setText("CTCT13666666666");
        OtherServiceInformation osi_02 = new OtherServiceInformation();
        osi_02.setOsiCode("OTHS");
        //osi_02.setAirlineCode("MU");
        osi_02.setAirlineCode("CA");
        osi_02.setText("CTCM1366666666");
        osi_02.setTravelerRefNumberRPH("1");
        osiList.add(osi_01);
        osiList.add(osi_02);
        request.setOsiList(osiList);

        //4.客票信息(出票时间)
        request.setTicketTimeLimit("2014-09-27T00:01:00");

        //5.扩展信息
        List<String> ctList = new ArrayList<String>();
        ctList.add("010-12345678");
        ctList.add("023-57651234");
        ctList.add("022-29681234");
        request.setContactInfoList(ctList);
        //封口信息
        request.setEnvelopType("KI");

        return request;
    }

    /**
     * 场景10：成人+婴儿+往返程
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest10() {
        //输入参数结构(7大信息)
        //1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
        //3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）

        AirBookRequest request = new AirBookRequest();

        //2.行程信息
        List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
        OriginDestinationOption od = new OriginDestinationOption();
        List<FlightSegment> fsList = new ArrayList<FlightSegment>();
        FlightSegment fs = new FlightSegment();
        fs.setRph("1");
        fs.setDepartureDateTime("2014-08-29T07:00:00");
        fs.setArrivalDateTime("2014-08-29T09:10:00");
        fs.setCodeshareInd("false");
        fs.setFlightNumber("5138");   //MU5138
        fs.setStatus("NN");
        fs.setSegmentType("NORMAL");
        fs.setDepartureAirport("PEK");
        fs.setArrivalAirport("SHA");    //SHA HKG
        fs.setAirEquipType("733");
        fs.setMarketingAirline("MU");
        fs.setResBookDesigCode("Y");
        fsList.add(fs);
        od.setFlightSegmentList(fsList);
        odList.add(od);
        OriginDestinationOption od1 = new OriginDestinationOption();
        List<FlightSegment> fsList1 = new ArrayList<FlightSegment>();
        FlightSegment fs1 = new FlightSegment();
        fs1.setRph("2");
        fs1.setDepartureDateTime("2014-08-30T11:30:00");
        fs1.setArrivalDateTime("2014-08-30T14:05:00");
        fs1.setCodeshareInd("false");
        fs1.setFlightNumber("5139");   //MU5138
        fs1.setStatus("NN");
        fs1.setSegmentType("NORMAL");
        fs1.setDepartureAirport("SHA");
        fs1.setArrivalAirport("PEK");    //SHA HKG
        fs1.setAirEquipType("733");
        fs1.setMarketingAirline("MU");
        fs1.setResBookDesigCode("Y");
        fsList1.add(fs1);
        od1.setFlightSegmentList(fsList1);
        odList.add(od1);
        request.setOriginDestinationOptionList(odList);

        //3.1旅客信息
        List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
        /*****成人*****/
        AirTraveler airTraveler = new AirTraveler();
        airTraveler.setGender("MALE");
        airTraveler.setPassengerTypeCode("ADT");        //成人

        List<PersonName> personNameList = new ArrayList<PersonName>();
        PersonName personName = new PersonName();
        personName.setLanguageType("ZH");
        personName.setSurname("高明");
        personNameList.add(personName);

        List<Document> documentList = new ArrayList<Document>();
        Document document = new Document();
        document.setDocType("NI");
        document.setDocId("120221197001011150");
        documentList.add(document);

        List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
        TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
        travelerRefNumber.setRph("1");
        travelerRefNumberList.add(travelerRefNumber);

        airTraveler.setPersonNameList(personNameList);
        airTraveler.setDocumentList(documentList);
        airTraveler.setRph("1");
        airTraveler.setComment("HK");
        airTraveler.setTravelerRefNumberList(travelerRefNumberList);
        airTravelerList.add(airTraveler);

        /*****婴儿*****/
        AirTraveler airTraveler1 = new AirTraveler();
        airTraveler1.setGender("MALE");
        airTraveler1.setPassengerTypeCode("INF");            //婴儿

        List<PersonName> personNameList1 = new ArrayList<PersonName>();
        PersonName personName1 = new PersonName();
        personName1.setLanguageType("ZH");
        personName1.setSurname("高一");
        personNameList1.add(personName1);

        List<Document> documentList1 = new ArrayList<Document>();
        Document document1 = new Document();
        document1.setDocType("NI");
        document1.setDocId("120221197001011150");
        documentList1.add(document1);

        List<TravelerRefNumber> travelerRefNumberList1 = new ArrayList<TravelerRefNumber>();
        TravelerRefNumber travelerRefNumber1 = new TravelerRefNumber();
        travelerRefNumber1.setRph("2");
        travelerRefNumberList1.add(travelerRefNumber1);

        airTraveler1.setPersonNameList(personNameList1);
        airTraveler1.setDocumentList(documentList1);
        airTraveler1.setRph("1");
        airTraveler1.setComment("HK");
        airTraveler1.setTravelerRefNumberList(travelerRefNumberList1);
        airTravelerList.add(airTraveler1);
        request.setAirTravelerList(airTravelerList);

        //旅客预定的服务信息 OSI
        List<OtherServiceInformation> osiList = new ArrayList<OtherServiceInformation>();
        OtherServiceInformation osi_01 = new OtherServiceInformation();
        osi_01.setOsiCode("OTHS");
        osi_01.setAirlineCode("MU");
        //osi_01.setAirlineCode("CA");
        osi_01.setText("CTCT13666666666");
        OtherServiceInformation osi_02 = new OtherServiceInformation();
        osi_02.setOsiCode("OTHS");
        osi_02.setAirlineCode("MU");
        //osi_02.setAirlineCode("CA");
        osi_02.setText("CTCM1366666666");
        osi_02.setTravelerRefNumberRPH("1");
        osiList.add(osi_01);
        osiList.add(osi_02);
        request.setOsiList(osiList);

        //4.客票信息(出票时间)
        request.setTicketTimeLimit("2014-08-26T00:01:00");

        //5.扩展信息
        List<String> ctList = new ArrayList<String>();
        ctList.add("010-12345678");
        ctList.add("023-57651234");
        ctList.add("022-29681234");
        request.setContactInfoList(ctList);
        //封口信息
        request.setEnvelopType("KI");

        return request;
    }

    /**
     * 场景14：子舱位预订
     *
     * @author wanglw
     * @date 2014-7-15
     */
    private static AirBookRequest buildRequest14() {
        //输入参数结构(7大信息)
        //1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
        //3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）

        AirBookRequest request = new AirBookRequest();
        request.setPseudoCityCode("sparrow");

        //2.行程信息
        List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
        OriginDestinationOption od = new OriginDestinationOption();
        List<FlightSegment> fsList = new ArrayList<FlightSegment>();
        FlightSegment fs = new FlightSegment();
        fs.setRph("1");
        fs.setDepartureDateTime("2014-08-29T07:00:00");
        fs.setArrivalDateTime("2014-08-29T09:10:00");
        fs.setCodeshareInd("false");
        fs.setFlightNumber("MU5138");   //MU5138
        fs.setStatus("NN");
        fs.setSegmentType("NORMAL");
        fs.setDepartureAirport("PEK");
        fs.setArrivalAirport("SHA");    //SHA HKG
        fs.setAirEquipType("733");
        fs.setMarketingAirline("MU");
        fs.setResBookDesigCode("M");
        fsList.add(fs);
        od.setFlightSegmentList(fsList);
        odList.add(od);

        //3.旅客信息
        List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
        AirTraveler airTraveler = new AirTraveler();
        airTraveler.setGender("MALE");
        airTraveler.setPassengerTypeCode("ADT");
        airTraveler.setRph("1");
        airTraveler.setComment("HK");
        /***姓名***/
        List<PersonName> personNameList = new ArrayList<PersonName>();
        PersonName personName = new PersonName();
        personName.setLanguageType("ZH");
        personName.setSurname("高明");
        personNameList.add(personName);
        airTraveler.setPersonNameList(personNameList);
        /***证件信息***/
        List<Document> documentList = new ArrayList<Document>();
        Document document = new Document();
        document.setDocType("NI");
        //身份证生日符合儿童规则时，主机会自动加入SSR CHLD，否则需要对预订航段每个航空公司手工写入SSR CHLD
        document.setDocId("31010420080101573X");
        documentList.add(document);
        airTraveler.setDocumentList(documentList);
        /***旅客序号***/
        List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
        TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
        travelerRefNumber.setRph("1");
        travelerRefNumberList.add(travelerRefNumber);
        airTraveler.setTravelerRefNumberList(travelerRefNumberList);
        airTravelerList.add(airTraveler);

        //旅客预定的服务信息
        List<OtherServiceInformation> osiList = new ArrayList<OtherServiceInformation>();
        OtherServiceInformation osi_01 = new OtherServiceInformation();
        osi_01.setOsiCode("OTHS");
        osi_01.setAirlineCode("MU");
        osi_01.setText("CTCT13666666666");
        osiList.add(osi_01);
        OtherServiceInformation osi_02 = new OtherServiceInformation();
        osi_02.setOsiCode("OTHS");
        osi_02.setAirlineCode("MU");
        osi_02.setText("CTCM1366666666");
        osi_02.setTravelerRefNumberRPH("1");
        osiList.add(osi_02);

        //4.出票信息
        String ticketTimeLimit = "2014-08-26T00:01:00";

        //5.设置扩展信息
        List<String> ctList = new ArrayList<String>();
        ctList.add("010-12345678");
        ctList.add("023-57651234");
        ctList.add("022-29681234");
        String envelopType = "KI";

        request.setOriginDestinationOptionList(odList);
        request.setAirTravelerList(airTravelerList);
        request.setOsiList(osiList);
        request.setTicketTimeLimit(ticketTimeLimit);
        request.setContactInfoList(ctList);
        request.setEnvelopType(envelopType);
        return request;
    }

    /*
     * 场景101：2 成人+单程
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest101() {
        //输入参数结构(7大信息)
        //1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
        //3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）

        AirBookRequest request = new AirBookRequest();
        request.setSegmentCheckInd("false");  //是否验证航段状态

        //1.POS信息
        request.setPseudoCityCode("sparrow");

        //2.行程信息
        List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
        OriginDestinationOption od = new OriginDestinationOption();
        List<FlightSegment> fsList = new ArrayList<FlightSegment>();
        FlightSegment fs = new FlightSegment();
        fs.setRph("1");
        fs.setDepartureDateTime("2014-09-29T07:30:00");
        fs.setArrivalDateTime("2014-09-29T09:40:00");
        fs.setCodeshareInd("false");
        fs.setFlightNumber("1831");   //MU5138
        fs.setStatus("NN");
        fs.setSegmentType("NORMAL");
        fs.setDepartureAirport("PEK");
        fs.setArrivalAirport("SHA");    //SHA HKG
        fs.setAirEquipType("333");
        fs.setMarketingAirline("CA");
        fs.setResBookDesigCode("Y");
        fsList.add(fs);
        od.setFlightSegmentList(fsList);
        odList.add(od);
        request.setOriginDestinationOptionList(odList);

        //3.1旅客信息
        List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
        /*****成人*****/
        AirTraveler airTraveler = new AirTraveler();
        airTraveler.setGender("MALE");
        airTraveler.setPassengerTypeCode("ADT");        //成人

        List<PersonName> personNameList = new ArrayList<PersonName>();
        PersonName personName = new PersonName();
        personName.setLanguageType("ZH");
        personName.setSurname("高明");
        personNameList.add(personName);

        List<Document> documentList = new ArrayList<Document>();
        Document document = new Document();
        document.setDocType("NI");
        document.setDocId("120221197001011150");
        documentList.add(document);

        List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
        TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
        travelerRefNumber.setRph("1");
        travelerRefNumberList.add(travelerRefNumber);

        airTraveler.setPersonNameList(personNameList);
        airTraveler.setDocumentList(documentList);
        airTraveler.setRph("1");
        airTraveler.setComment("HK");
        airTraveler.setTravelerRefNumberList(travelerRefNumberList);
        airTravelerList.add(airTraveler);

        /*****成人2*****/
        AirTraveler airTraveler1 = new AirTraveler();
        airTraveler1.setGender("MALE");
        airTraveler1.setPassengerTypeCode("ADT");            //成人

        List<PersonName> personNameList1 = new ArrayList<PersonName>();
        PersonName personName1 = new PersonName();
        personName1.setLanguageType("ZH");
        personName1.setSurname("高一");
        personNameList1.add(personName1);

        List<Document> documentList1 = new ArrayList<Document>();
        Document document1 = new Document();
        document1.setDocType("NI");
        document1.setDocId("120221200805011150");
        documentList1.add(document1);

        List<TravelerRefNumber> travelerRefNumberList1 = new ArrayList<TravelerRefNumber>();
        TravelerRefNumber travelerRefNumber1 = new TravelerRefNumber();
        travelerRefNumber1.setRph("2");
        travelerRefNumberList1.add(travelerRefNumber1);

        airTraveler1.setPersonNameList(personNameList1);
        airTraveler1.setDocumentList(documentList1);
        airTraveler1.setRph("1");
        airTraveler1.setComment("HK");
        airTraveler1.setTravelerRefNumberList(travelerRefNumberList1);
        airTravelerList.add(airTraveler1);
        request.setAirTravelerList(airTravelerList);

        //旅客预定的服务信息 OSI
        List<OtherServiceInformation> osiList = new ArrayList<OtherServiceInformation>();
        OtherServiceInformation osi_01 = new OtherServiceInformation();
        osi_01.setOsiCode("OTHS");
        osi_01.setAirlineCode("CA");
        osi_01.setText("CTCT13666666666");
        OtherServiceInformation osi_02 = new OtherServiceInformation();
        osi_02.setOsiCode("OTHS");
        osi_02.setAirlineCode("CA");
        osi_02.setText("CTCM1366666666");
        osi_02.setTravelerRefNumberRPH("1");
        osiList.add(osi_01);
        osiList.add(osi_02);
        request.setOsiList(osiList);

        //4.客票信息(出票时间)
        request.setTicketTimeLimit("2014-09-26T00:01:00");

        //5.扩展信息
        List<String> ctList = new ArrayList<String>();
        ctList.add("010-12345678");
        request.setContactInfoList(ctList);
        //封口信息
        request.setEnvelopType("KI");

        return request;
    }


}
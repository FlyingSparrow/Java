package com.sparrow.ibe.bookingservice.airbook;

import com.sparrow.ibe.bookingservice.airbook.model.*;
import com.sparrow.ibe.constants.IBEConst;

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
            request = buildRequest01();    //场景1
        } else if (type == 2) {
            request = buildRequest02();    //场景2
        } else if (type == 3) {
            request = buildRequest03();    //场景3
        } else if (type == 4) {
            request = buildRequest04();    //场景4
        } else if (type == 5) {
            request = buildRequest05();    //场景5
        } else if (type == 6) {
            request = buildRequest06();    //场景6
        } else if (type == 7) {
            request = buildRequest07();    //场景7
        } else if (type == 8) {
            request = buildRequest08();    //场景8
        } else if (type == 9) {
            request = buildRequest09();    //场景9
        } else if (type == 10) {
            request = buildRequest10();    //场景10
        } else if (type == 11) {
            request = buildRequest11();    //场景11
        } else if (type == 12) {
            request = buildRequest12();    //场景12
        } else if (type == 13) {
            request = buildRequest13();    //场景13
        } else if (type == 14) {
            request = buildRequest14();   //场景14
        } else if (type == 15) {
            request = buildRequest15();    //场景15
        } else if (type == 16) {
            request = buildRequest16();    //场景16
        } else if (type == 101) {
            request = buildRequest101();    //场景16
        }
        return request;
    }

    /**
     * 场景：国内航班+单个成人+单程
     * 说明：
     * 必填信息：1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
     * 旅客信息说明：3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest01() {
        AirBookRequest request = new AirBookRequest();

        //1.POS信息
        request.setPseudoCityCode("sparrow");

        //2.行程信息
        List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
        OriginDestinationOption od = new OriginDestinationOption();
        List<FlightSegment> fsList = new ArrayList<FlightSegment>();
        FlightSegment fs = new FlightSegment();
        fs.setRph("1");
        fs.setDepartureDateTime("2015-12-17T07:00:00");
        fs.setArrivalDateTime("2015-12-18T09:10:00");
        fs.setCodeshareInd("false");
        fs.setFlightNumber("5138");
        fs.setStatus("NN");
        fs.setSegmentType("NORMAL");
        fs.setDepartureAirport("PEK");
        fs.setArrivalAirport("SHA");
        fs.setAirEquipType("733");
        fs.setMarketingAirline("MU");
        fs.setResBookDesigCode("Y");
        fsList.add(fs);
        od.setFlightSegmentList(fsList);
        odList.add(od);
        request.setOriginDestinationOptionList(odList);

        //3.1旅客信息
        List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
        AirTraveler airTraveler = new AirTraveler();
        airTraveler.setGender(IBEConst.Gender.MALE.getCode());
        airTraveler.setPassengerTypeCode(IBEConst.PassengerType.ADULT.getCode());

        List<PersonName> personNameList = new ArrayList<PersonName>();
        PersonName personName = new PersonName();
        personName.setLanguageType(IBEConst.LanguageType.ZH.getCode());
        personName.setSurname("高明");
        personNameList.add(personName);

        List<Document> documentList = new ArrayList<Document>();
        Document document = new Document();
        document.setDocType(IBEConst.DocumentType.ID.getCode());
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
        request.setAirTravelerList(airTravelerList);

        //旅客预定的服务信息 OSI
        List<OtherServiceInformation> osiList = new ArrayList<OtherServiceInformation>();
        OtherServiceInformation osi_01 = new OtherServiceInformation();
        osi_01.setOsiCode("OTHS");
        osi_01.setAirlineCode("MU");
        osi_01.setText("CTCT13666666666");
        OtherServiceInformation osi_02 = new OtherServiceInformation();
        osi_02.setOsiCode("OTHS");
        osi_02.setAirlineCode("MU");
        osi_02.setText("CTCM1366666666");
        osi_02.setTravelerRefNumberRPH("1");
        osiList.add(osi_01);
        osiList.add(osi_02);
        request.setOsiList(osiList);

        //4.客票信息(出票时间)
        request.setTicketTimeLimit("2015-12-16T00:01:00");

        //5.扩展信息
        List<String> ctList = new ArrayList<String>();
        ctList.add("010-12345678");
        request.setContactInfoList(ctList);
        //封口信息
        request.setEnvelopType("KI");

        return request;
    }

    /**
     * 场景02：成人+单程+SSR
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest02() {
        //输入参数结构(7大信息)
        //1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
        //3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）

        AirBookRequest request = new AirBookRequest();

        //1.POS信息
        request.setPseudoCityCode("sparrow");

        //2.行程信息
        List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
        OriginDestinationOption od = new OriginDestinationOption();
        List<FlightSegment> fsList = new ArrayList<FlightSegment>();
        FlightSegment fs = new FlightSegment();
        fs.setRph("1");
        //fs.setDepartureDateTime("2014-08-29T07:00:00");
        //fs.setArrivalDateTime("2014-08-29T09:10:00");
        fs.setDepartureDateTime("2015-12-29T07:30:00");
        fs.setArrivalDateTime("2015-12-29T09:40:00");
        fs.setCodeshareInd("false");
        //fs.setFlightNumber("MU5138");   //MU5138
        fs.setFlightNumber("CA1831");   //MU5138
        fs.setStatus("NN");
        fs.setSegmentType("NORMAL");
        fs.setDepartureAirport("PEK");
        fs.setArrivalAirport("SHA");    //SHA HKG
        //fs.setAirEquipType("733");
        fs.setAirEquipType("333");
        //fs.setMarketingAirline("MU");
        fs.setMarketingAirline("CA");
        fs.setResBookDesigCode("Y");
        fsList.add(fs);
        od.setFlightSegmentList(fsList);
        odList.add(od);
        request.setOriginDestinationOptionList(odList);

        //3.1旅客信息
        List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
        AirTraveler airTraveler = new AirTraveler();
        airTraveler.setGender("MALE");
        airTraveler.setPassengerTypeCode("ADT");

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
        request.setAirTravelerList(airTravelerList);

        //3.2 旅客预定的服务信息SSR
        List<SpecialServiceRequest> ssrList = new ArrayList<SpecialServiceRequest>();
        SpecialServiceRequest ssr1 = new SpecialServiceRequest();
        ssr1.setSsrCode("OTHS");                    //服务代码类别，例如FQTV
        ssr1.setAirlineCode("CA");                    //航空公司两字码，特殊服务需要指定航空公司
        ssr1.setText("ADULT PNR IS HAHAHA");        //备注信息
        ssr1.setTravelerRefNumberRPH("1");            //旅客引用序号，必须填写，否则不能添加到主机
        ssrList.add(ssr1);
        SpecialServiceRequest ssr2 = new SpecialServiceRequest();
        ssr2.setSsrCode("SPML");                    //服务代码类别，例如FQTV
        ssr2.setStatus("NN");                        //行动代码，例如：HK
        ssr2.setAirlineCode("CA");                    //航空公司两字码，特殊服务需要指定航空公司
        ssr2.setText("NOSOLT");                        //备注信息
        ssr2.setFlightRefNumberRPH("1");            //特殊备注所在航段，FQTV必须填写，否则不能添加到主机
        ssr2.setTravelerRefNumberRPH("1");            //旅客引用序号，必须填写，否则不能添加到主机
        ssrList.add(ssr2);
        request.setSsrList(ssrList);

        //4.客票信息(出票时间)
        request.setTicketTimeLimit("2015-12-26T00:01:00");

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
     * 场景03：成人+单程+OSI
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest03() {
        //输入参数结构(7大信息)
        //1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
        //3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）

        AirBookRequest request = new AirBookRequest();

        //1.POS信息
        request.setPseudoCityCode("sparrow");

        //2.行程信息
        List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
        OriginDestinationOption od = new OriginDestinationOption();
        List<FlightSegment> fsList = new ArrayList<FlightSegment>();
        FlightSegment fs = new FlightSegment();
        fs.setRph("1");
        fs.setDepartureDateTime("2014-08-29T07:30:00");
        fs.setArrivalDateTime("2014-08-29T09:40:00");
        fs.setCodeshareInd("false");
        fs.setFlightNumber("CA1831");   //MU5138
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
        AirTraveler airTraveler = new AirTraveler();
        airTraveler.setGender("MALE");
        airTraveler.setPassengerTypeCode("ADT");

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
        request.setAirTravelerList(airTravelerList);

        //旅客预定的服务信息 OSI
        List<OtherServiceInformation> osiList = new ArrayList<OtherServiceInformation>();
        OtherServiceInformation osi_01 = new OtherServiceInformation();
        osi_01.setOsiCode("OTHS");
        osi_01.setAirlineCode("MU");
        osi_01.setText("CTCT13666666666");
        OtherServiceInformation osi_02 = new OtherServiceInformation();
        osi_02.setOsiCode("OTHS");
        osi_02.setAirlineCode("MU");
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
     * 场景04：成人+单程+RMK
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest04() {
        //输入参数结构(7大信息)
        //1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
        //3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）

        AirBookRequest request = new AirBookRequest();

        //1.POS信息
        request.setPseudoCityCode("sparrow");

        //2.行程信息
        List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
        OriginDestinationOption od = new OriginDestinationOption();
        List<FlightSegment> fsList = new ArrayList<FlightSegment>();
        FlightSegment fs = new FlightSegment();
        fs.setRph("1");
        fs.setDepartureDateTime("2014-08-29T07:30:00");
        fs.setArrivalDateTime("2014-08-29T09:40:00");
        fs.setCodeshareInd("false");
        fs.setFlightNumber("CA1831");   //MU5138
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
        AirTraveler airTraveler = new AirTraveler();
        airTraveler.setGender("MALE");
        airTraveler.setPassengerTypeCode("ADT");

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
        request.setAirTravelerList(airTravelerList);

        //旅客预定的服务信息 RMK
        List<SpecialRemark> rmkList = new ArrayList<SpecialRemark>();
        SpecialRemark rmk = new SpecialRemark();
        rmk.setText("特殊备注组信息");
        rmkList.add(rmk);
        request.setRmkList(rmkList);

        //旅客预定的服务信息 OSI
        List<OtherServiceInformation> osiList = new ArrayList<OtherServiceInformation>();
        OtherServiceInformation osi_01 = new OtherServiceInformation();
        osi_01.setOsiCode("OTHS");
        osi_01.setAirlineCode("MU");
        osi_01.setText("CTCT13666666666");
        OtherServiceInformation osi_02 = new OtherServiceInformation();
        osi_02.setOsiCode("OTHS");
        osi_02.setAirlineCode("MU");
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
     * 场景05：成人+往返
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest05() {
        //输入参数结构(7大信息)
        //1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
        //3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）

        AirBookRequest request = new AirBookRequest();

        //1.POS信息
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
        AirTraveler airTraveler = new AirTraveler();
        airTraveler.setGender("MALE");
        airTraveler.setPassengerTypeCode("ADT");

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
     * 场景07：成人+儿童+往返
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest07() {
        //输入参数结构(7大信息)
        //1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
        //3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）

        AirBookRequest request = new AirBookRequest();

        //1.POS信息
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
        document1.setDocId("120221197001011150");
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
     * 场景08：1 成人+2 儿童+单程
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest08() {
        //输入参数结构(7大信息)
        //1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
        //3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）

        AirBookRequest request = new AirBookRequest();

        //1.POS信息
        request.setPseudoCityCode("sparrow");

        //2.行程信息
        List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
        OriginDestinationOption od = new OriginDestinationOption();
        List<FlightSegment> fsList = new ArrayList<FlightSegment>();
        FlightSegment fs = new FlightSegment();
        fs.setRph("1");
        //fs.setDepartureDateTime("2014-08-29T07:00:00");
        //fs.setArrivalDateTime("2014-08-29T09:10:00");
        fs.setDepartureDateTime("2014-08-29T07:30:00");
        fs.setArrivalDateTime("2014-08-29T09:40:00");
        fs.setCodeshareInd("false");
        //fs.setFlightNumber("MU5138");   //MU5138
        fs.setFlightNumber("CA1831");   //MU5138
        fs.setStatus("NN");
        fs.setSegmentType("NORMAL");
        fs.setDepartureAirport("PEK");
        fs.setArrivalAirport("SHA");    //SHA HKG
        //fs.setAirEquipType("733");
        fs.setAirEquipType("333");
        //fs.setMarketingAirline("MU");
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

        /*****儿童1*****/
        AirTraveler airTraveler1 = new AirTraveler();
        airTraveler1.setGender("MALE");
        airTraveler1.setPassengerTypeCode("CHD");            //儿童
        airTraveler1.setBirthDate("01MAY08");

        List<PersonName> personNameList1 = new ArrayList<PersonName>();
        PersonName personName1 = new PersonName();
        personName1.setLanguageType("ZH");
        personName1.setSurname("高一");
        personNameList1.add(personName1);

        List<Document> documentList1 = new ArrayList<Document>();
        Document document1 = new Document();
        document1.setDocType("NI");
        document1.setDocId("120221200805011150");
        document1.setBirthDate("2008-05-01");
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

        /*****儿童2*****/
        AirTraveler airTraveler2 = new AirTraveler();
        airTraveler2.setGender("MALE");
        airTraveler2.setPassengerTypeCode("CHD");            //儿童

        List<PersonName> personNameList2 = new ArrayList<PersonName>();
        PersonName personName2 = new PersonName();
        personName2.setLanguageType("ZH");
        personName2.setSurname("高二");
        personNameList2.add(personName2);

        List<Document> documentList2 = new ArrayList<Document>();
        Document document2 = new Document();
        document2.setDocType("NI");
        document2.setDocId("120221197001011150");
        documentList2.add(document2);

        List<TravelerRefNumber> travelerRefNumberList2 = new ArrayList<TravelerRefNumber>();
        TravelerRefNumber travelerRefNumber2 = new TravelerRefNumber();
        travelerRefNumber2.setRph("3");
        travelerRefNumberList2.add(travelerRefNumber2);

        airTraveler2.setPersonNameList(personNameList2);
        airTraveler2.setDocumentList(documentList2);
        airTraveler2.setRph("1");
        airTraveler2.setComment("HK");
        airTraveler2.setTravelerRefNumberList(travelerRefNumberList2);

        airTravelerList.add(airTraveler2);
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
     * 场景09：2成人+1 儿童+单程
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest09() {
        //输入参数结构(7大信息)
        //1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
        //3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）

        AirBookRequest request = new AirBookRequest();

        //1.POS信息
        request.setPseudoCityCode("sparrow");

        //2.行程信息
        List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
        OriginDestinationOption od = new OriginDestinationOption();
        List<FlightSegment> fsList = new ArrayList<FlightSegment>();
        FlightSegment fs = new FlightSegment();
        fs.setRph("1");
        //fs.setDepartureDateTime("2014-08-29T07:00:00");
        //fs.setArrivalDateTime("2014-08-29T09:10:00");
        fs.setDepartureDateTime("2014-08-29T07:30:00");
        fs.setArrivalDateTime("2014-08-29T09:40:00");
        fs.setCodeshareInd("false");
        //fs.setFlightNumber("MU5138");   //MU5138
        fs.setFlightNumber("CA1831");   //MU5138
        fs.setStatus("NN");
        fs.setSegmentType("NORMAL");
        fs.setDepartureAirport("PEK");
        fs.setArrivalAirport("SHA");    //SHA HKG
        //fs.setAirEquipType("733");
        fs.setAirEquipType("333");
        //fs.setMarketingAirline("MU");
        fs.setMarketingAirline("CA");
        fs.setResBookDesigCode("Y");
        fsList.add(fs);
        od.setFlightSegmentList(fsList);
        odList.add(od);
        request.setOriginDestinationOptionList(odList);

        //3.1旅客信息
        List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
        /*****成人1*****/
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
        personName1.setSurname("张三");
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

        /*****儿童1*****/
        AirTraveler airTraveler2 = new AirTraveler();
        airTraveler2.setGender("MALE");
        airTraveler2.setPassengerTypeCode("CHD");            //儿童

        List<PersonName> personNameList2 = new ArrayList<PersonName>();
        PersonName personName2 = new PersonName();
        personName2.setLanguageType("ZH");
        personName2.setSurname("高一");
        personNameList2.add(personName2);

        List<Document> documentList2 = new ArrayList<Document>();
        Document document2 = new Document();
        document2.setDocType("NI");
        document2.setDocId("120221197001011150");
        documentList2.add(document2);

        List<TravelerRefNumber> travelerRefNumberList2 = new ArrayList<TravelerRefNumber>();
        TravelerRefNumber travelerRefNumber2 = new TravelerRefNumber();
        travelerRefNumber2.setRph("3");
        travelerRefNumberList2.add(travelerRefNumber2);

        airTraveler2.setPersonNameList(personNameList2);
        airTraveler2.setDocumentList(documentList2);
        airTraveler2.setRph("1");
        airTraveler2.setComment("HK");
        airTraveler2.setTravelerRefNumberList(travelerRefNumberList2);
        airTravelerList.add(airTraveler2);
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
     * 场景11:成人+单程+SSR+DOCS
     *
     * @author wanglw
     * @date 2014-7-15
     */
    private static AirBookRequest buildRequest11() {
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
        fs.setDepartureDateTime("2014-09-15T07:00:00");
        fs.setArrivalDateTime("2014-09-15T09:10:00");
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

        //3.旅客信息
        List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
        AirTraveler airTraveler = new AirTraveler();
        airTraveler.setGender("MALE");
        airTraveler.setPassengerTypeCode("ADT");
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
        document.setDocType("PP");                        //证件类型，PP - 护照；NI - 身份证
        document.setDocTypeDetail("P");                    //证件类型描述，在证件类型为PP时，提供具体护照类型，如：F、P、AC等
        document.setDocId("G446164");                    //证件号
        document.setDocHolderNationality("CN");            //证件持有人国籍
        document.setDocIssueCountry("CN");                //发证国家
        document.setBirthDate("2004-09-04");            //出生日期
        document.setGender("MALE");                        //证件持有人性别，证件类型为PP时，需要指定性别
        document.setExpireDate("2031-12-19");            //证件有效期截止日期，证件类型为PP时，需要指定到期时间
        document.setRph("1");                            //证件编号，若多个证件编号，输入不同编号，都写入主机
        document.setDocHolderGivenName("DANA");            //证件持有人姓名的名，若填写护照，需要填写此项，姓名为zhang/san时，这里是san
        document.setDocHolderSurname("WANG");            //证件持有人姓名的姓，若填写护照，需要填写此项，姓名为zhang/san时，这里是zhang
        documentList.add(document);
        airTraveler.setDocumentList(documentList);

        /***旅客年龄***/
            /*	PassengerTypeQuantity ptq = new PassengerTypeQuantity();
                ptq.setAge("10");
				airTraveler.setPassengerTypeQuantity(ptq);*/

        /***旅客序号***/
        List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
        TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
        travelerRefNumber.setRph("1");
        travelerRefNumberList.add(travelerRefNumber);
        airTraveler.setTravelerRefNumberList(travelerRefNumberList);
        /***航段序号***/
        List<String> flightSegmentRPHList = new ArrayList<String>();
        flightSegmentRPHList.add("1");
        airTraveler.setFlightSegmentRPHList(flightSegmentRPHList);
        /***证件号航段序号关联***/
        DocumentFlightBinding dfbinging = new DocumentFlightBinding();
        dfbinging.setDocumentRPH("1");
        dfbinging.setFlightSegmentRPH("1");
        airTraveler.setDocumentFlightBinding(dfbinging);
        airTravelerList.add(airTraveler);

        //旅客预定的服务信息:SSR
        List<SpecialServiceRequest> ssrList = new ArrayList<SpecialServiceRequest>();
        SpecialServiceRequest ssr1 = new SpecialServiceRequest();
        ssr1.setSsrCode("DOCS");                    //服务代码类别，例如FQTV
        ssr1.setServiceQuantity("1");
        ssr1.setStatus("HK");                        //只能输入HK？
        ssr1.setFlightRefNumberRPH("1");
        ssr1.setRph("1");
        ssr1.setAirlineCode("MU");                    //航空公司两字码，特殊服务需要指定航空公司
        ssr1.setTravelerRefNumberRPH("1");            //旅客引用序号，必须填写，否则不能添加到主机
        ssrList.add(ssr1);

        //旅客预定的服务信息 OSI
        List<OtherServiceInformation> osiList = new ArrayList<OtherServiceInformation>();
        OtherServiceInformation osi_01 = new OtherServiceInformation();
        osi_01.setOsiCode("OTHS");
        osi_01.setAirlineCode("MU");
        osi_01.setText("CTCT13666666666");
        OtherServiceInformation osi_02 = new OtherServiceInformation();
        osi_02.setOsiCode("OTHS");
        osi_02.setAirlineCode("MU");
        osi_02.setText("CTCM1366666666");
        osi_02.setTravelerRefNumberRPH("1");
        osiList.add(osi_01);
        osiList.add(osi_02);
        request.setOsiList(osiList);

        //4.出票信息
        String ticketTimeLimit = "2014-09-15T00:01:00";

        //5.设置扩展信息（必填）
        List<String> ctList = new ArrayList<String>();
        ctList.add("010-12345678");
        ctList.add("023-57651234");
        ctList.add("022-29681234");
        String envelopType = "KI";
        String envelopDelay = "false";

        request.setOriginDestinationOptionList(odList);
        request.setAirTravelerList(airTravelerList);
        request.setSsrList(ssrList);
        request.setTicketTimeLimit(ticketTimeLimit);
        request.setContactInfoList(ctList);
        request.setEnvelopType(envelopType);
        request.setEnvelopDelay(envelopDelay);
        return request;
    }

    /**
     * 场景12：儿童+单程+SSR+CHLD
     *
     * @author wanglw
     * @date 2014-7-15
     */
    private static AirBookRequest buildRequest12() {
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
        fs.setDepartureDateTime("2014-09-29T07:00:00");
        fs.setArrivalDateTime("2014-09-29T09:10:00");
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

        //3.旅客信息
        List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
        AirTraveler airTraveler = new AirTraveler();
        airTraveler.setGender("MALE");
        airTraveler.setPassengerTypeCode("CHD");
        airTraveler.setRph("1");
        airTraveler.setComment("HK");
        airTraveler.setBirthDate("2008-01-01");
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
        document.setBirthDate("2008-01-01");
        documentList.add(document);
        airTraveler.setDocumentList(documentList);
        /***旅客序号***/
        List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
        TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
        travelerRefNumber.setRph("1");
        travelerRefNumberList.add(travelerRefNumber);
        airTraveler.setTravelerRefNumberList(travelerRefNumberList);
        airTravelerList.add(airTraveler);

        //旅客预定的服务信息:SSR
        List<SpecialServiceRequest> ssrList = new ArrayList<SpecialServiceRequest>();
        SpecialServiceRequest ssr1 = new SpecialServiceRequest();
        ssr1.setSsrCode("CHLD");                    //服务代码类别，例如FQTV
        ssr1.setText("01JAN08");                    //关键点！！！！！！！！！！
        ssr1.setStatus("HK");                        //行动代码，例如：HK
        ssr1.setFlightRefNumberRPH("1");            //航班引用序号
        ssr1.setTravelerRefNumberRPH("1");            //旅客引用序号，必须填写，否则不能添加到主机
        ssrList.add(ssr1);

        //旅客预定的服务信息：OSI
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
        OtherServiceInformation osi_03 = new OtherServiceInformation();
        osi_03.setOsiCode("OTHS");
        osi_03.setAirlineCode("MU");
        osi_03.setText("CHLD 01JAN08");
        osi_03.setTravelerRefNumberRPH("1");
        osiList.add(osi_03);

        //4.出票信息
        String ticketTimeLimit = "2014-09-28T00:01:00";

        //5.设置扩展信息
        List<String> ctList = new ArrayList<String>();
        ctList.add("010-12345678");
        ctList.add("023-57651234");
        ctList.add("022-29681234");
        String envelopType = "KI";

        request.setOriginDestinationOptionList(odList);
        request.setAirTravelerList(airTravelerList);
        request.setSsrList(ssrList);
        request.setOsiList(osiList);
        request.setTicketTimeLimit(ticketTimeLimit);
        request.setContactInfoList(ctList);
        request.setEnvelopType(envelopType);
        return request;
    }

    /**
     * 场景13:自动添加地面航段
     *
     * @author wanglw
     * @date 2014-7-15
     */
    private static AirBookRequest buildRequest13() {
        //输入参数结构(7大信息)
        //1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
        //3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）

        AirBookRequest request = new AirBookRequest();
        request.setPseudoCityCode("sparrow");
        request.setAutoARNKInd("true");

        //2.行程信息
        List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
        OriginDestinationOption od = new OriginDestinationOption();
        List<FlightSegment> fsList = new ArrayList<FlightSegment>();
        FlightSegment fs = new FlightSegment();
        fs.setRph("1");
        fs.setDepartureDateTime("2014-08-28T07:00:00");
        fs.setArrivalDateTime("2014-08-28T09:10:00");
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
        //od.setFlightSegmentList(fsList);
        //List<FlightSegment> fsList1 = new ArrayList<FlightSegment>();
        FlightSegment fs1 = new FlightSegment();
        fs1.setRph("2");
        fs1.setDepartureDateTime("2014-08-29T07:00:00");
        fs1.setArrivalDateTime("2014-08-29T09:10:00");
        fs1.setCodeshareInd("false");
        fs1.setFlightNumber("5138");   //MU5138
        fs1.setStatus("NN");
        fs1.setSegmentType("NORMAL");
        fs1.setDepartureAirport("PEK");
        fs1.setArrivalAirport("SHA");    //SHA HKG
        fs1.setAirEquipType("733");
        fs1.setMarketingAirline("MU");
        fs1.setResBookDesigCode("Y");
        //fsList1.add(fs1);
        fsList.add(fs1);
        od.setFlightSegmentList(fsList);
        odList.add(od);

        //3.旅客信息
        List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
        AirTraveler airTraveler = new AirTraveler();
        airTraveler.setGender("MALE");
        airTraveler.setPassengerTypeCode("ADT");
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
        document.setDocType("PP");                        //证件类型，PP - 护照；NI - 身份证
        document.setDocTypeDetail("P");                    //证件类型描述，在证件类型为PP时，提供具体护照类型，如：F、P、AC等
        document.setDocId("G446164");                    //证件号
        document.setDocHolderNationality("CN");            //证件持有人国籍
        document.setDocIssueCountry("CN");                //发证国家
        document.setBirthDate("1984-09-04");            //出生日期
        document.setGender("MALE");                        //证件持有人性别，证件类型为PP时，需要指定性别
        document.setExpireDate("2031-12-19");            //证件有效期截止日期，证件类型为PP时，需要指定到期时间
        document.setRph("1");                            //证件编号，若多个证件编号，输入不同编号，都写入主机
        document.setDocHolderGivenName("DANA");            //证件持有人姓名的名，若填写护照，需要填写此项，姓名为zhang/san时，这里是san
        document.setDocHolderSurname("WANG");            //证件持有人姓名的姓，若填写护照，需要填写此项，姓名为zhang/san时，这里是zhang
        documentList.add(document);
        airTraveler.setDocumentList(documentList);
        /***旅客年龄***/
        PassengerTypeQuantity ptq = new PassengerTypeQuantity();
        ptq.setAge("30");
        airTraveler.setPassengerTypeQuantity(ptq);

        /***旅客序号***/
        List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
        TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
        travelerRefNumber.setRph("1");
        travelerRefNumberList.add(travelerRefNumber);
        airTraveler.setTravelerRefNumberList(travelerRefNumberList);
        /***航段序号***/
        List<String> flightSegmentRPHList = new ArrayList<String>();
        flightSegmentRPHList.add("1");
        flightSegmentRPHList.add("2");
        airTraveler.setFlightSegmentRPHList(flightSegmentRPHList);
        /***证件号航段序号关联***/
        DocumentFlightBinding dfbinging = new DocumentFlightBinding();
        dfbinging.setDocumentRPH("1");
        dfbinging.setFlightSegmentRPH("1");
        airTraveler.setDocumentFlightBinding(dfbinging);
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

        //5.设置扩展信息（必填）
        List<String> ctList = new ArrayList<String>();
        ctList.add("010-12345678");
        ctList.add("023-57651234");
        ctList.add("022-29681234");
        String envelopType = "KI";
        String envelopDelay = "false";

        request.setOriginDestinationOptionList(odList);
        request.setAirTravelerList(airTravelerList);
        request.setOsiList(osiList);
        request.setTicketTimeLimit(ticketTimeLimit);
        request.setContactInfoList(ctList);
        request.setEnvelopType(envelopType);
        request.setEnvelopDelay(envelopDelay);
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

    /**
     * 场景15：国际预订
     *
     * @author wanglw
     * @date 2014-8-29
     */
    private static AirBookRequest buildRequest15() {
        //输入参数结构(7大信息)
        //1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
        //3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）

        AirBookRequest request = new AirBookRequest();

        //1.POS信息
        request.setPseudoCityCode("sparrow");

        //2.行程信息
        List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
        OriginDestinationOption od = new OriginDestinationOption();
        List<FlightSegment> fsList = new ArrayList<FlightSegment>();
        FlightSegment fs = new FlightSegment();
        fs.setRph("1");
        fs.setDepartureDateTime("2014-09-18T14:00:00");
        fs.setArrivalDateTime("2014-09-18T11:00:00");
        fs.setCodeshareInd("false");
        fs.setFlightNumber("987");
        fs.setStatus("NN");
        fs.setSegmentType("NORMAL");
        fs.setDepartureAirport("PEK");
        fs.setArrivalAirport("LAX");    //SHA HKG
        fs.setAirEquipType("773");
        fs.setMarketingAirline("CA");
        fs.setResBookDesigCode("Y");
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
        osi_01.setAirlineCode("CA");
        osi_01.setText("CTCT13666666666");
        osiList.add(osi_01);
        OtherServiceInformation osi_02 = new OtherServiceInformation();
        osi_02.setOsiCode("OTHS");
        osi_02.setAirlineCode("CA");
        osi_02.setText("CTCM1366666666");
        osi_02.setTravelerRefNumberRPH("1");
        osiList.add(osi_02);

        //4.出票信息
        String ticketTimeLimit = "2014-09-15T00:01:00";

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

    /**
     * 场景16：国际预订：PNR+1个成人+1个婴儿
     *
     * @author wanglw
     * @date 2014-8-29
     */
    private static AirBookRequest buildRequest16() {
        //输入参数结构(7大信息)
        //1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
        //3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）

        AirBookRequest request = new AirBookRequest();

        //1.POS信息
        request.setPseudoCityCode("sparrow");

        //2.行程信息
        List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
        OriginDestinationOption od = new OriginDestinationOption();
        List<FlightSegment> fsList = new ArrayList<FlightSegment>();
        FlightSegment fs = new FlightSegment();
        fs.setRph("1");
        fs.setDepartureDateTime("2014-09-30T07:00:00");
        fs.setArrivalDateTime("2014-09-30T09:10:00");
        fs.setCodeshareInd("false");
        fs.setFlightNumber("101");
        fs.setStatus("NN");
        fs.setSegmentType("NORMAL");
        fs.setDepartureAirport("PEK");
        fs.setArrivalAirport("HKG");    //SHA HKG
        fs.setAirEquipType("321");
        fs.setMarketingAirline("CA");
        fs.setResBookDesigCode("Y");
        fsList.add(fs);
        od.setFlightSegmentList(fsList);
        odList.add(od);

        //3.旅客信息
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

        //旅客预定的服务信息
        List<OtherServiceInformation> osiList = new ArrayList<OtherServiceInformation>();
        OtherServiceInformation osi_01 = new OtherServiceInformation();
        osi_01.setOsiCode("OTHS");
        osi_01.setAirlineCode("CA");
        osi_01.setText("CTCT13666666666");
        osiList.add(osi_01);
        OtherServiceInformation osi_02 = new OtherServiceInformation();
        osi_02.setOsiCode("OTHS");
        osi_02.setAirlineCode("CA");
        osi_02.setText("CTCM1366666666");
        osi_02.setTravelerRefNumberRPH("1");
        osiList.add(osi_02);

        //4.出票信息
        String ticketTimeLimit = "2014-09-30T00:01:00";

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
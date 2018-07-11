package com.sparrow.ibe.bookingservice.airbook;

import com.sparrow.ibe.bookingservice.airbook.model.*;
import com.sparrow.ibe.constants.IBEConst;
import com.sparrow.utils.DateUtils;
import com.sparrow.utils.StringUtils;
import org.assertj.core.util.Lists;

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
public class AirBookRequestBuilderV2 {

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
            //场景1
            request = buildRequest01();
        }else if(type == 2){
            request = buildRequest02();
        }else if(type == 3){
            request = buildRequest03();
        }else if(type == 4){
            request = buildRequest04();
        }else if(type == 5){
            request = buildRequest05();
        }
        return request;
    }

    /**
     * 场景1：国内航班+单个成人+单程
     * 说明：
     * 必填信息：1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
     * 旅客信息说明：3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest01() {
        AirBookVO airBookVO = new AirBookVO();

        //行程信息
        airBookVO.setDepartureDateTime("2015-12-17T07:00:00");
        airBookVO.setArrivalDateTime("2015-12-18T09:10:00");
        airBookVO.setFlightNumber("5138");
        airBookVO.setDepartureAirport("PEK");
        airBookVO.setArrivalAirport("SHA");
        airBookVO.setCodeShareInd("false");
        airBookVO.setMarketingAirline("MU");
        if (StringUtils.isEmpty(airBookVO.getResBookDesigCode())) {
            //如果用户没有设置舱位等级，那么默认为经济舱
            airBookVO.setResBookDesigCode(IBEConst.CabinClass.ECONOMY.getCode());
        }

        //旅客信息
        List<AirTravelerVO> airTravelerList = Lists.newArrayList();
        AirTravelerVO airTravelerVO = new AirTravelerVO();
        airTravelerVO.setGender(IBEConst.Gender.MALE.getCode());
        airTravelerVO.setPassengerTypeCode(IBEConst.PassengerType.ADULT.getCode());

        List<PersonNameVO> personNameVOList = new ArrayList<>();
        PersonNameVO personNameVO = new PersonNameVO();
        personNameVO.setLanguageType(IBEConst.LanguageType.ZH.getCode());
        personNameVO.setSurname("高明");
        personNameVOList.add(personNameVO);
        airTravelerVO.setPersonNameList(personNameVOList);

        airTravelerVO.setDocType(IBEConst.DocumentType.ID.getCode());
        airTravelerVO.setDocId("120221197001011150");
        airTravelerVO.setComment("HK");
        airTravelerList.add(airTravelerVO);
        airBookVO.setAirTravelerList(airTravelerList);

        airBookVO.setContactNumber("010-12345678");
        airBookVO.setTicketTimeLimit("2015-12-16T00:01:00");

        return buildAirBookRequest(airBookVO);
    }

    /**
     * 场景2：国内航班+单个成人+单程+OSI
     * 说明：
     * 必填信息：1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
     * 旅客信息说明：3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest02() {
        AirBookVO airBookVO = new AirBookVO();

        //行程信息
        airBookVO.setDepartureDateTime("2015-12-17T07:00:00");
        airBookVO.setArrivalDateTime("2015-12-18T09:10:00");
        airBookVO.setFlightNumber("5138");
        airBookVO.setDepartureAirport("PEK");
        airBookVO.setArrivalAirport("SHA");
        airBookVO.setCodeShareInd("false");
        airBookVO.setMarketingAirline("MU");
        if (StringUtils.isEmpty(airBookVO.getResBookDesigCode())) {
            //如果用户没有设置舱位等级，那么默认为经济舱
            airBookVO.setResBookDesigCode(IBEConst.CabinClass.ECONOMY.getCode());
        }

        //旅客信息
        List<AirTravelerVO> airTravelerList = Lists.newArrayList();
        AirTravelerVO airTravelerVO = new AirTravelerVO();
        airTravelerVO.setGender(IBEConst.Gender.MALE.getCode());
        airTravelerVO.setPassengerTypeCode(IBEConst.PassengerType.ADULT.getCode());

        List<PersonNameVO> personNameVOList = new ArrayList<>();
        PersonNameVO personNameVO = new PersonNameVO();
        personNameVO.setLanguageType(IBEConst.LanguageType.ZH.getCode());
        personNameVO.setSurname("高明");
        personNameVOList.add(personNameVO);
        airTravelerVO.setPersonNameList(personNameVOList);

        airTravelerVO.setDocType(IBEConst.DocumentType.ID.getCode());
        airTravelerVO.setDocId("120221197001011150");
        airTravelerVO.setComment("HK");
        airTravelerList.add(airTravelerVO);
        airBookVO.setAirTravelerList(airTravelerList);

        //OSI（其他服务信息）信息
        List<String> osiList = new ArrayList<>();
        osiList.add("CTCT13666666666");
        osiList.add("CTCM1366666666");
        airBookVO.setOsiList(osiList);

        airBookVO.setContactNumber("010-12345678");
        airBookVO.setTicketTimeLimit("2015-12-16T00:01:00");

        return buildAirBookRequest(airBookVO);
    }

    /**
     * 场景3：国内航班+单个成人+单程+OSI+RMK
     * 说明：
     * 必填信息：1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
     * 旅客信息说明：3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest03() {
        AirBookVO airBookVO = new AirBookVO();

        //行程信息
        airBookVO.setDepartureDateTime("2015-12-17T07:00:00");
        airBookVO.setArrivalDateTime("2015-12-18T09:10:00");
        airBookVO.setFlightNumber("5138");
        airBookVO.setDepartureAirport("PEK");
        airBookVO.setArrivalAirport("SHA");
        airBookVO.setCodeShareInd("false");
        airBookVO.setMarketingAirline("MU");
        if (StringUtils.isEmpty(airBookVO.getResBookDesigCode())) {
            //如果用户没有设置舱位等级，那么默认为经济舱
            airBookVO.setResBookDesigCode(IBEConst.CabinClass.ECONOMY.getCode());
        }

        //旅客信息
        List<AirTravelerVO> airTravelerList = Lists.newArrayList();
        AirTravelerVO airTravelerVO = new AirTravelerVO();
        airTravelerVO.setGender(IBEConst.Gender.MALE.getCode());
        airTravelerVO.setPassengerTypeCode(IBEConst.PassengerType.ADULT.getCode());

        List<PersonNameVO> personNameVOList = new ArrayList<>();
        PersonNameVO personNameVO = new PersonNameVO();
        personNameVO.setLanguageType(IBEConst.LanguageType.ZH.getCode());
        personNameVO.setSurname("高明");
        personNameVOList.add(personNameVO);
        airTravelerVO.setPersonNameList(personNameVOList);

        airTravelerVO.setDocType(IBEConst.DocumentType.ID.getCode());
        airTravelerVO.setDocId("120221197001011150");
        airTravelerVO.setComment("HK");
        airTravelerList.add(airTravelerVO);
        airBookVO.setAirTravelerList(airTravelerList);

        //OSI（其他服务信息）信息
        List<String> osiList = new ArrayList<>();
        osiList.add("CTCT13666666666");
        osiList.add("CTCM1366666666");
        airBookVO.setOsiList(osiList);

        //备注信息
        List<String> remarkList = new ArrayList<>();
        remarkList.add("特殊备注组信息");
        remarkList.add("特殊备注组信息2");
        airBookVO.setRemarkList(remarkList);

        airBookVO.setContactNumber("010-12345678");
        airBookVO.setTicketTimeLimit("2015-12-16T00:01:00");

        return buildAirBookRequest(airBookVO);
    }

    /**
     * 场景4：国内航班+单个成人+单程+OSI+SSR
     * 说明：
     * 必填信息：1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
     * 旅客信息说明：3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest04() {
        AirBookVO airBookVO = new AirBookVO();

        //行程信息
        airBookVO.setDepartureDateTime("2015-12-17T07:00:00");
        airBookVO.setArrivalDateTime("2015-12-18T09:10:00");
        airBookVO.setFlightNumber("5138");
        airBookVO.setDepartureAirport("PEK");
        airBookVO.setArrivalAirport("SHA");
        airBookVO.setCodeShareInd("false");
        airBookVO.setMarketingAirline("MU");
        if (StringUtils.isEmpty(airBookVO.getResBookDesigCode())) {
            //如果用户没有设置舱位等级，那么默认为经济舱
            airBookVO.setResBookDesigCode(IBEConst.CabinClass.ECONOMY.getCode());
        }

        //旅客信息
        List<AirTravelerVO> airTravelerList = Lists.newArrayList();
        AirTravelerVO airTravelerVO = new AirTravelerVO();
        airTravelerVO.setGender(IBEConst.Gender.MALE.getCode());
        airTravelerVO.setPassengerTypeCode(IBEConst.PassengerType.ADULT.getCode());

        List<PersonNameVO> personNameVOList = new ArrayList<>();
        PersonNameVO personNameVO = new PersonNameVO();
        personNameVO.setLanguageType(IBEConst.LanguageType.ZH.getCode());
        personNameVO.setSurname("高明");
        personNameVOList.add(personNameVO);
        airTravelerVO.setPersonNameList(personNameVOList);

        airTravelerVO.setDocType(IBEConst.DocumentType.ID.getCode());
        airTravelerVO.setDocId("120221197001011150");
        airTravelerVO.setComment("HK");
        airTravelerList.add(airTravelerVO);
        airBookVO.setAirTravelerList(airTravelerList);

        //OSI（其他服务信息）信息
        List<String> osiList = new ArrayList<>();
        osiList.add("CTCT13666666666");
        osiList.add("CTCM1366666666");
        airBookVO.setOsiList(osiList);

        //特殊服务请求
        List<SpecialServiceRequestVO> ssrList = new ArrayList<>();
        SpecialServiceRequestVO ssr = new SpecialServiceRequestVO();
        ssr.setSsrCode("OTHS");
        ssr.setAirline("MU");
        ssr.setTravelerRefNumber("1");
        ssr.setText("ADULT PNR IS HAHAHA");
        ssrList.add(ssr);

        ssr = new SpecialServiceRequestVO();
        //特殊餐食申请
        ssr.setSsrCode("SPML");
        ssr.setStatus("NN");
        ssr.setAirline("MU");
        ssr.setFlightRefNumber("1");
        ssr.setTravelerRefNumber("1");
        ssr.setText("NOSOLT");
        ssrList.add(ssr);

        airBookVO.setSsrList(ssrList);

        airBookVO.setContactNumber("010-12345678");
        airBookVO.setTicketTimeLimit("2015-12-16T00:01:00");

        return buildAirBookRequest(airBookVO);
    }

    /**
     * 场景5：国内航班+单个成人+单程+婴儿
     * 说明：
     * 必填信息：1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
     * 旅客信息说明：3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest05() {
        AirBookVO airBookVO = new AirBookVO();

        //行程信息
        airBookVO.setDepartureDateTime("2013-11-26T07:30:00");
        airBookVO.setArrivalDateTime("2013-11-26T09:40:00");
        airBookVO.setFlightNumber("1831");
        airBookVO.setDepartureAirport("PEK");
        airBookVO.setArrivalAirport("SHA");
        airBookVO.setMarketingAirline("CA");
        if (StringUtils.isEmpty(airBookVO.getResBookDesigCode())) {
            //如果用户没有设置舱位等级，那么默认为经济舱
            airBookVO.setResBookDesigCode(IBEConst.CabinClass.ECONOMY.getCode());
        }

        //旅客信息
        String birthDate = "1985-05-09";
        List<AirTravelerVO> airTravelerList = Lists.newArrayList();
        AirTravelerVO airTravelerVO = new AirTravelerVO();
        airTravelerVO.setGender(IBEConst.Gender.MALE.getCode());
        airTravelerVO.setPassengerTypeCode(IBEConst.PassengerType.ADULT.getCode());
        airTravelerVO.setAccompaniedByInfant("true");

        List<PersonNameVO> personNameVOList = new ArrayList<>();
        PersonNameVO personNameVO = new PersonNameVO();
        personNameVO.setLanguageType(IBEConst.LanguageType.ZH.getCode());
        personNameVO.setSurname("李雷");
        personNameVOList.add(personNameVO);
        airTravelerVO.setPersonNameList(personNameVOList);

        airTravelerVO.setDocType(IBEConst.DocumentType.ID.getCode());
        airTravelerVO.setDocId("1234567890");
        airTravelerVO.setDocHolderNationality("CN");
        airTravelerVO.setBirthDate(birthDate);
        airTravelerVO.setExpireDate("2031-01-01");
        airTravelerVO.setDocHolderSurname("李雷");
        airTravelerVO.setAge(StringUtils.calculateAge(DateUtils.parseDate(birthDate, DateUtils.DATE_FORMAT))+"");

        //旅客证件信息
        airTravelerVO.setDocType(IBEConst.DocumentType.ID.getCode());
        airTravelerVO.setDocId("1234567890");
        airTravelerVO.setDocHolderNationality("CN");
        airTravelerVO.setBirthDate("1985-05-09");
        airTravelerVO.setGender("MALE");
        airTravelerVO.setExpireDate("2031-01-01");
        airTravelerVO.setDocHolderSurname("李雷");

        airTravelerList.add(airTravelerVO);

        //婴儿的出生日期
        String birthDateOfInfant = "2012-02-08";
        airTravelerVO = new AirTravelerVO();
        airTravelerVO.setPassengerTypeCode(IBEConst.PassengerType.INFANT.getCode());
        airTravelerVO.setBirthDate(birthDateOfInfant);

        personNameVOList = new ArrayList<>();
        PersonNameVO personNameVO2 = new PersonNameVO();
        personNameVO2.setLanguageType(IBEConst.LanguageType.ZH.getCode());
        personNameVO2.setSurname("李小雷");
        personNameVOList.add(personNameVO2);

        personNameVO2 = new PersonNameVO();
        personNameVO2.setLanguageType(IBEConst.LanguageType.EN.getCode());
        personNameVO2.setSurname("LI/XIAOLEI");
        personNameVOList.add(personNameVO2);
        airTravelerVO.setAge(StringUtils.calculateAge(DateUtils.parseDate(birthDateOfInfant, DateUtils.DATE_FORMAT))+"");
        airTravelerVO.setPersonNameList(personNameVOList);
        airTravelerList.add(airTravelerVO);

        airBookVO.setAirTravelerList(airTravelerList);

        //OSI（其他服务信息）信息
        List<String> osiList = new ArrayList<>();
        osiList.add("CTCT13801035768");
        osiList.add("CTCM13801035768/P1");
        airBookVO.setOsiList(osiList);

        //备注信息
        List<String> remarkList = new ArrayList<>();
        remarkList.add("TJ AUTH SHA255");
        airBookVO.setRemarkList(remarkList);

        airBookVO.setContactNumber("0512-82274023");
        airBookVO.setTicketTimeLimit("2013-11-26T05:32:20");
        airBookVO.setEnvelopDelay("false");

        return buildAirBookRequest(airBookVO);
    }

    /**
     * 根据用户的预定请求构造自动预订服务的请求对象
     *
     * @param airBookVO
     * @return
     */
    private AirBookRequest buildAirBookRequest(AirBookVO airBookVO) {
        AirBookRequest request = new AirBookRequest();
        //1.POS信息
        request.setPseudoCityCode("sparrow");

        //2.行程信息
        fillItinerary(request, airBookVO);

        //3.1旅客信息
        fillAirTraveler(request, airBookVO);

        //4.客票信息(出票时间)
        request.setTicketTimeLimit(airBookVO.getTicketTimeLimit());

        //填充 OSI 信息
        fillOtherServiceInformation(request, airBookVO);
        //填充特殊备注信息
        fillSpecialRemark(request, airBookVO);
        //填充特殊服务请求信息
        fillSpecialServiceRequest(request, airBookVO.getSsrList());

        //5.扩展信息
        List<String> ctList = new ArrayList<String>();
        ctList.add(airBookVO.getContactNumber());
        request.setContactInfoList(ctList);
        //封口信息
        request.setEnvelopType("KI");
        request.setEnvelopDelay(airBookVO.getEnvelopDelay());

        return request;
    }

    /**
     * 填充行程信息
     *
     * @param airBookVO
     * @return
     */
    private void fillItinerary(AirBookRequest request, AirBookVO airBookVO) {
        List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
        OriginDestinationOption od = new OriginDestinationOption();
        List<FlightSegment> fsList = new ArrayList<FlightSegment>();
        FlightSegment fs = new FlightSegment();
        fs.setRph("1");
        fs.setDepartureDateTime(airBookVO.getDepartureDateTime());
        fs.setArrivalDateTime(airBookVO.getArrivalDateTime());

        if(StringUtils.isNotEmpty(airBookVO.getCodeShareInd())){
            fs.setCodeshareInd(airBookVO.getCodeShareInd());
        }

        fs.setFlightNumber(airBookVO.getFlightNumber());
        fs.setStatus("NN");
        fs.setSegmentType("NORMAL");
        fs.setDepartureAirport(airBookVO.getDepartureAirport());
        fs.setArrivalAirport(airBookVO.getArrivalAirport());
        fs.setMarketingAirline(airBookVO.getMarketingAirline());
        fs.setResBookDesigCode(airBookVO.getResBookDesigCode());
        fsList.add(fs);
        od.setFlightSegmentList(fsList);
        odList.add(od);

        request.setOriginDestinationOptionList(odList);
    }

    /**
     * 填充旅客信息
     *
     * @param request
     * @param airBookVO
     */
    private void fillAirTraveler(AirBookRequest request, AirBookVO airBookVO) {
        int rph = 0;
        List<AirTravelerVO> airTravelerVOList = airBookVO.getAirTravelerList();
        if(airTravelerVOList != null && airTravelerVOList.size() > 0){
            List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
            for (AirTravelerVO item : airTravelerVOList) {
                rph++;
                String rphStr = rph + "";

                AirTraveler airTraveler = new AirTraveler();
                if(IBEConst.PassengerType.INFANT.getCode().equals(item.getPassengerTypeCode())){
                    //如果旅客类型为婴儿，那么设置出生日期
                    airTraveler.setBirthDate(item.getBirthDate());
                }

                airTraveler.setGender(item.getGender());
                airTraveler.setPassengerTypeCode(item.getPassengerTypeCode());
                if(StringUtils.isNotEmpty(item.getAccompaniedByInfant())){
                    airTraveler.setAccompaniedByInfant(item.getAccompaniedByInfant());
                }

                List<PersonName> personNameList = new ArrayList<PersonName>();
                List<PersonNameVO> personNameVOList = item.getPersonNameList();
                if(personNameVOList != null && personNameVOList.size() > 0){
                    for(PersonNameVO personNameVO : personNameVOList){
                        PersonName personName = new PersonName();
                        personName.setLanguageType(personNameVO.getLanguageType());
                        personName.setSurname(personNameVO.getSurname());
                        personNameList.add(personName);
                    }
                }

                //旅客证件信息
                if(!IBEConst.PassengerType.INFANT.getCode().equals(item.getPassengerTypeCode())){
                    //说明：婴儿不需要填写证件信息，如果旅客类型不是婴儿，那么需要填写证件信息
                    List<Document> documentList = new ArrayList<Document>();
                    Document document = new Document();
                    document.setDocType(item.getDocType());
                    document.setDocId(item.getDocId());
                    if(StringUtils.isNotEmpty(item.getDocHolderNationality())){
                        document.setDocHolderNationality(item.getDocHolderNationality());
                    }
                    if(StringUtils.isNotEmpty(item.getBirthDate())){
                        document.setBirthDate(item.getBirthDate());
                    }
                    if(StringUtils.isNotEmpty(item.getGender())){
                        document.setGender(item.getGender());
                    }
                    if(StringUtils.isNotEmpty(item.getExpireDate())){
                        document.setExpireDate(item.getExpireDate());
                    }
                    documentList.add(document);
                    airTraveler.setDocumentList(documentList);
                }

                if(StringUtils.isNotEmpty(item.getAge())){
                    PassengerTypeQuantity ptq = new PassengerTypeQuantity();
                    ptq.setAge(item.getAge());
                    airTraveler.setPassengerTypeQuantity(ptq);
                }

                List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
                TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
                travelerRefNumber.setRph(rphStr);
                if(IBEConst.PassengerType.ADULT.getCode().equals(item.getPassengerTypeCode())){
                    //如果大人携带了婴儿，那么需要在大人的 travelerRefNumber 中添加婴儿的 travelerRefNumber 中的 rph
                    String rphOfInfant = findRphOfInfant(airTravelerVOList);
                    if(StringUtils.isNotEmpty(rphOfInfant)){
                        travelerRefNumber.setInfantTravelerRPH(rphOfInfant);
                    }
                }
                travelerRefNumberList.add(travelerRefNumber);

                List<String> flightSegmentRphList = new ArrayList<>();
                flightSegmentRphList.add("1");
                airTraveler.setFlightSegmentRPHList(flightSegmentRphList);

                airTraveler.setPersonNameList(personNameList);
                airTraveler.setRph(rphStr);
                if(StringUtils.isNotEmpty(item.getComment())){
                    airTraveler.setComment(item.getComment());
                }
                airTraveler.setTravelerRefNumberList(travelerRefNumberList);
                airTravelerList.add(airTraveler);
            }
            request.setAirTravelerList(airTravelerList);
        }
    }

    /**
     * 查找婴儿的rph
     * @param airTravelerVOList
     * @return
     */
    private String findRphOfInfant(List<AirTravelerVO> airTravelerVOList){
        String result = "";

        int rph = 0;
        for(AirTravelerVO item : airTravelerVOList){
            rph++;
            if(IBEConst.PassengerType.INFANT.getCode().equals(item.getPassengerTypeCode())){
                result = rph+"";
                break;
            }
        }

        return result;
    }

    /**
     * 填充旅客预定的服务信息 OSI
     *
     * @param request
     * @param airBookVO
     */
    private void fillOtherServiceInformation(AirBookRequest request, AirBookVO airBookVO) {
        List<String> list = airBookVO.getOsiList();
        if(list != null && list.size() > 0){
            List<OtherServiceInformation> otherServiceInformationList = new ArrayList<OtherServiceInformation>();
            for(String item : list){
                OtherServiceInformation osi = new OtherServiceInformation();
                osi.setOsiCode("OTHS");
                osi.setAirlineCode(airBookVO.getMarketingAirline());
                if(item.startsWith("CTCM")){
                    osi.setTravelerRefNumberRPH("1");
                }
                osi.setText(item);
                otherServiceInformationList.add(osi);
            }
            request.setOsiList(otherServiceInformationList);
        }
    }

    /**
     * 填充特殊备注信息
     *
     * @param request
     * @param airBookVO
     */
    private void fillSpecialRemark(AirBookRequest request, AirBookVO airBookVO) {
        List<String> list = airBookVO.getRemarkList();
        if(list != null && list.size() > 0){
            List<SpecialRemark> specialRemarkList = new ArrayList<SpecialRemark>();
            for(String item : list){
                SpecialRemark remark = new SpecialRemark();
                remark.setText(item);
                specialRemarkList.add(remark);
            }
            request.setRmkList(specialRemarkList);
        }
    }

    /**
     * 填充特殊服务请求信息
     *  @param request
     * @param list
     */
    private void fillSpecialServiceRequest(AirBookRequest request, List<SpecialServiceRequestVO> list) {
        if(list != null && list.size() > 0){
            List<SpecialServiceRequest> ssrList = new ArrayList<SpecialServiceRequest>();
            for(SpecialServiceRequestVO item : list){
                SpecialServiceRequest ssr = new SpecialServiceRequest();
                ssr.setSsrCode(item.getSsrCode());
                ssr.setStatus(item.getStatus());
                ssr.setAirlineCode(item.getAirline());
                ssr.setFlightRefNumberRPH(item.getFlightRefNumber());
                ssr.setTravelerRefNumberRPH(item.getTravelerRefNumber());
                ssr.setText(item.getText());
                ssrList.add(ssr);
            }
            request.setSsrList(ssrList);
        }
    }

}
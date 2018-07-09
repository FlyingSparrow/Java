package com.sparrow.ibe.bookingservice.airbook;

import com.sparrow.ibe.bookingservice.airbook.model.*;
import com.sparrow.ibe.constants.IBEConst;
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
            request = buildRequest04();
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
        AirBookVO airBookVO = new AirBookVO();
        airBookVO.setDepartureDateTime("2015-12-17T07:00:00");
        airBookVO.setArrivalDateTime("2015-12-18T09:10:00");
        airBookVO.setFlightNumber("5138");
        airBookVO.setDepartureAirport("PEK");
        airBookVO.setArrivalAirport("SHA");
        airBookVO.setMarketingAirline("MU");
        if (StringUtils.isEmpty(airBookVO.getResBookDesigCode())) {
            //如果用户没有设置舱位等级，那么默认为经济舱
            airBookVO.setResBookDesigCode(IBEConst.CabinClass.ECONOMY.getCode());
        }
        airBookVO.setContactNumber("010-12345678");
        airBookVO.setTicketTimeLimit("2015-12-16T00:01:00");

        List<AirTravelerVO> airTravelerList = Lists.newArrayList();
        AirTravelerVO airTravelerVO = new AirTravelerVO();
        airTravelerVO.setGender(IBEConst.Gender.MALE.getCode());
        airTravelerVO.setPassengerTypeCode(IBEConst.PassengerType.ADULT.getCode());
        airTravelerVO.setLanguageType(IBEConst.LanguageType.ZH.getCode());
        airTravelerVO.setSurname("高明");
        airTravelerVO.setDocType(IBEConst.DocumentType.ID.getCode());
        airTravelerVO.setDocId("120221197001011150");
        airTravelerList.add(airTravelerVO);
        airBookVO.setAirTravelerList(airTravelerList);

        return buildAirBookRequest(airBookVO);
    }

    /**
     * 场景：国内航班+单个成人+单程+OSI
     * 说明：
     * 必填信息：1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
     * 旅客信息说明：3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest02() {
        AirBookVO airBookVO = new AirBookVO();
        airBookVO.setDepartureDateTime("2015-12-17T07:00:00");
        airBookVO.setArrivalDateTime("2015-12-18T09:10:00");
        airBookVO.setFlightNumber("5138");
        airBookVO.setDepartureAirport("PEK");
        airBookVO.setArrivalAirport("SHA");
        airBookVO.setMarketingAirline("MU");
        if (StringUtils.isEmpty(airBookVO.getResBookDesigCode())) {
            //如果用户没有设置舱位等级，那么默认为经济舱
            airBookVO.setResBookDesigCode(IBEConst.CabinClass.ECONOMY.getCode());
        }
        airBookVO.setContactNumber("010-12345678");
        airBookVO.setTicketTimeLimit("2015-12-16T00:01:00");

        List<AirTravelerVO> airTravelerList = Lists.newArrayList();
        AirTravelerVO airTravelerVO = new AirTravelerVO();
        airTravelerVO.setGender(IBEConst.Gender.MALE.getCode());
        airTravelerVO.setPassengerTypeCode(IBEConst.PassengerType.ADULT.getCode());
        airTravelerVO.setLanguageType(IBEConst.LanguageType.ZH.getCode());
        airTravelerVO.setSurname("高明");
        airTravelerVO.setDocType(IBEConst.DocumentType.ID.getCode());
        airTravelerVO.setDocId("120221197001011150");
        airTravelerList.add(airTravelerVO);
        airBookVO.setAirTravelerList(airTravelerList);

        List<String> osiList = new ArrayList<>();
        osiList.add("CTCT13666666666");
        osiList.add("CTCM1366666666");
        airBookVO.setOsiList(osiList);

        return buildAirBookRequest(airBookVO);
    }

    /**
     * 场景：国内航班+单个成人+单程+OSI+RMK
     * 说明：
     * 必填信息：1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
     * 旅客信息说明：3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest03() {
        AirBookVO airBookVO = new AirBookVO();
        airBookVO.setDepartureDateTime("2015-12-17T07:00:00");
        airBookVO.setArrivalDateTime("2015-12-18T09:10:00");
        airBookVO.setFlightNumber("5138");
        airBookVO.setDepartureAirport("PEK");
        airBookVO.setArrivalAirport("SHA");
        airBookVO.setMarketingAirline("MU");
        if (StringUtils.isEmpty(airBookVO.getResBookDesigCode())) {
            //如果用户没有设置舱位等级，那么默认为经济舱
            airBookVO.setResBookDesigCode(IBEConst.CabinClass.ECONOMY.getCode());
        }
        airBookVO.setContactNumber("010-12345678");
        airBookVO.setTicketTimeLimit("2015-12-16T00:01:00");

        List<AirTravelerVO> airTravelerList = Lists.newArrayList();
        AirTravelerVO airTravelerVO = new AirTravelerVO();
        airTravelerVO.setGender(IBEConst.Gender.MALE.getCode());
        airTravelerVO.setPassengerTypeCode(IBEConst.PassengerType.ADULT.getCode());
        airTravelerVO.setLanguageType(IBEConst.LanguageType.ZH.getCode());
        airTravelerVO.setSurname("高明");
        airTravelerVO.setDocType(IBEConst.DocumentType.ID.getCode());
        airTravelerVO.setDocId("120221197001011150");
        airTravelerList.add(airTravelerVO);
        airBookVO.setAirTravelerList(airTravelerList);

        List<String> osiList = new ArrayList<>();
        osiList.add("CTCT13666666666");
        osiList.add("CTCM1366666666");
        airBookVO.setOsiList(osiList);

        List<String> remarkList = new ArrayList<>();
        remarkList.add("特殊备注组信息");
        remarkList.add("特殊备注组信息2");
        airBookVO.setRemarkList(remarkList);

        return buildAirBookRequest(airBookVO);
    }

    /**
     * 场景：国内航班+单个成人+单程+OSI+SSR
     * 说明：
     * 必填信息：1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
     * 旅客信息说明：3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）
     *
     * @author wanglw
     * @date 2014-8-29
     */
    public AirBookRequest buildRequest04() {
        AirBookVO airBookVO = new AirBookVO();
        airBookVO.setDepartureDateTime("2015-12-17T07:00:00");
        airBookVO.setArrivalDateTime("2015-12-18T09:10:00");
        airBookVO.setFlightNumber("5138");
        airBookVO.setDepartureAirport("PEK");
        airBookVO.setArrivalAirport("SHA");
        airBookVO.setMarketingAirline("MU");
        if (StringUtils.isEmpty(airBookVO.getResBookDesigCode())) {
            //如果用户没有设置舱位等级，那么默认为经济舱
            airBookVO.setResBookDesigCode(IBEConst.CabinClass.ECONOMY.getCode());
        }
        airBookVO.setContactNumber("010-12345678");
        airBookVO.setTicketTimeLimit("2015-12-16T00:01:00");

        List<AirTravelerVO> airTravelerList = Lists.newArrayList();
        AirTravelerVO airTravelerVO = new AirTravelerVO();
        airTravelerVO.setGender(IBEConst.Gender.MALE.getCode());
        airTravelerVO.setPassengerTypeCode(IBEConst.PassengerType.ADULT.getCode());
        airTravelerVO.setLanguageType(IBEConst.LanguageType.ZH.getCode());
        airTravelerVO.setSurname("高明");
        airTravelerVO.setDocType(IBEConst.DocumentType.ID.getCode());
        airTravelerVO.setDocId("120221197001011150");
        airTravelerList.add(airTravelerVO);
        airBookVO.setAirTravelerList(airTravelerList);

        List<String> osiList = new ArrayList<>();
        osiList.add("CTCT13666666666");
        osiList.add("CTCM1366666666");
        airBookVO.setOsiList(osiList);

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
        fillAirTraveler(request, airBookVO.getAirTravelerList());

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
        fs.setCodeshareInd("false");
        fs.setFlightNumber(airBookVO.getFlightNumber());
        fs.setStatus("NN");
        fs.setSegmentType("NORMAL");
        fs.setDepartureAirport(airBookVO.getDepartureAirport());
        fs.setArrivalAirport(airBookVO.getArrivalAirport());
//        fs.setAirEquipType("733");
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
     * @param airTravelerVOList
     */
    private void fillAirTraveler(AirBookRequest request, List<AirTravelerVO> airTravelerVOList) {
        int rph = 0;
        for (AirTravelerVO item : airTravelerVOList) {
            rph++;
            String rphStr = rph + "";
            List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
            AirTraveler airTraveler = new AirTraveler();
            airTraveler.setGender(item.getGender());
            airTraveler.setPassengerTypeCode(item.getPassengerTypeCode());

            List<PersonName> personNameList = new ArrayList<PersonName>();
            PersonName personName = new PersonName();
            personName.setLanguageType(item.getLanguageType());
            personName.setSurname(item.getSurname());
            personNameList.add(personName);

            List<Document> documentList = new ArrayList<Document>();
            Document document = new Document();
            document.setDocType(item.getDocType());
            document.setDocId(item.getDocId());
            documentList.add(document);

            List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
            TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
            travelerRefNumber.setRph(rphStr);
            travelerRefNumberList.add(travelerRefNumber);

            airTraveler.setPersonNameList(personNameList);
            airTraveler.setDocumentList(documentList);
            airTraveler.setRph(rphStr);
            airTraveler.setComment("HK");
            airTraveler.setTravelerRefNumberList(travelerRefNumberList);
            airTravelerList.add(airTraveler);
            request.setAirTravelerList(airTravelerList);
        }
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
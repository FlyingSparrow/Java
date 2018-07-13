package com.sparrow.ibe.bookingservice.airbook.stage.impl;

import com.sparrow.ibe.bookingservice.airbook.model.AirBookRequest;
import com.sparrow.ibe.bookingservice.airbook.stage.AirBookStage;
import com.sparrow.ibe.bookingservice.airbook.transformer.AirBookRequestTransformer;
import com.sparrow.ibe.bookingservice.airbook.vo.*;
import com.sparrow.ibe.constants.IBEConst;
import com.sparrow.utils.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 场景4：国内航班+单个成人+单程+OSI+SSR
 * 说明：
 * 必填信息：1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
 * 旅客信息说明：3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）
 *
 * @author wangjianchun
 * @create 2018/7/11
 */
@Component("airBookStage04")
public class AirBookStage04 implements AirBookStage {

    @Autowired
    private AirBookRequestTransformer airBookRequestTransformer;

    @Override
    public AirBookRequest buildRequest() {
        AirBookVO airBookVO = new AirBookVO();

        //行程信息
        fillItinerary(airBookVO);

        //旅客信息
        fillAirTraveler(airBookVO);

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

        List<String> contactInfoList = Lists.newArrayList();
        contactInfoList.add("010-12345678");
        airBookVO.setContactInfoList(contactInfoList);
        airBookVO.setTicketTimeLimit("2015-12-16T00:01:00");

        return airBookRequestTransformer.transform(airBookVO);
    }

    /**
     * 填充行程信息
     *
     * @param airBookVO
     */
    private void fillItinerary(AirBookVO airBookVO) {
        List<FlightSegmentVO> flightSegmentList = Lists.newArrayList();
        FlightSegmentVO flightSegmentVO = new FlightSegmentVO();
        flightSegmentVO.setDepartureDateTime("2015-12-17T07:00:00");
        flightSegmentVO.setArrivalDateTime("2015-12-18T09:10:00");
        flightSegmentVO.setFlightNumber("5138");
        flightSegmentVO.setDepartureAirport("PEK");
        flightSegmentVO.setArrivalAirport("SHA");
        flightSegmentVO.setAirEquipType("733");
        flightSegmentVO.setCodeShareInd("false");
        flightSegmentVO.setMarketingAirline("MU");
        if (StringUtils.isEmpty(flightSegmentVO.getResBookDesigCode())) {
            //如果用户没有设置舱位等级，那么默认为经济舱
            flightSegmentVO.setResBookDesigCode(IBEConst.CabinClass.ECONOMY.getCode());
        }
        flightSegmentList.add(flightSegmentVO);
        airBookVO.setFlightSegmentList(flightSegmentList);
    }

    /**
     * 填充旅客信息
     *
     * @param airBookVO
     */
    private void fillAirTraveler(AirBookVO airBookVO) {
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
    }

}

package com.sparrow.ibe.bookingservice.airbook.stage.impl;

import com.sparrow.ibe.bookingservice.airbook.model.AirBookRequest;
import com.sparrow.ibe.bookingservice.airbook.stage.AirBookStage;
import com.sparrow.ibe.bookingservice.airbook.transformer.AirBookRequestTransformer;
import com.sparrow.ibe.bookingservice.airbook.vo.AirBookRequestVO;
import com.sparrow.ibe.bookingservice.airbook.vo.AirTravelerVO;
import com.sparrow.ibe.bookingservice.airbook.vo.FlightSegmentVO;
import com.sparrow.ibe.bookingservice.airbook.vo.PersonNameVO;
import com.sparrow.ibe.constants.IBEConst;
import com.sparrow.utils.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 场景2：国内航班+单个成人+单程+OSI
 * 说明：
 * 必填信息：1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
 * 旅客信息说明：3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）
 *
 * @author wangjianchun
 * @create 2018/7/11
 */
@Component("airBookStage02")
public class AirBookStage02 implements AirBookStage {

    @Autowired
    private AirBookRequestTransformer airBookRequestTransformer;

    @Override
    public AirBookRequest buildRequest() {
        AirBookRequestVO airBookVO = new AirBookRequestVO();

        //行程信息
        fillItinerary(airBookVO);

        //旅客信息
        fillAirTraveler(airBookVO);

        //OSI（其他服务信息）信息
        List<String> osiList = new ArrayList<>();
        osiList.add("CTCT13666666666");
        osiList.add("CTCM1366666666");
        airBookVO.setOsiList(osiList);

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
    private void fillItinerary(AirBookRequestVO airBookVO) {
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
    private void fillAirTraveler(AirBookRequestVO airBookVO) {
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

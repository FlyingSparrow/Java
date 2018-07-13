package com.sparrow.ibe.bookingservice.airbook.stage.impl;

import com.sparrow.ibe.bookingservice.airbook.model.AirBookRequest;
import com.sparrow.ibe.bookingservice.airbook.stage.AirBookStage;
import com.sparrow.ibe.bookingservice.airbook.transformer.AirBookRequestTransformer;
import com.sparrow.ibe.bookingservice.airbook.vo.*;
import com.sparrow.ibe.constants.IBEConst;
import com.sparrow.utils.DateUtils;
import com.sparrow.utils.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 场景10：国内航班+儿童+单程+SSR
 * 说明：
 * 必填信息：1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
 * 旅客信息说明：3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）
 *
 * @author wangjianchun
 * @create 2018/7/11
 */
@Component("airBookStage10")
public class AirBookStage10 implements AirBookStage {

    @Autowired
    private AirBookRequestTransformer airBookRequestTransformer;

    @Override
    public AirBookRequest buildRequest() {
        AirBookRequestVO airBookVO = new AirBookRequestVO();

        //行程信息
        fillItinerary(airBookVO);

        //旅客信息
        fillAirTraveler(airBookVO);

        //SSR（特殊服务请求）
        fillSpecialServiceRequest(airBookVO);

        //OSI（其他服务信息）信息
        List<String> osiList = Lists.newArrayList();
        osiList.add("CTCT13666666666");
        osiList.add("CTCM1366666666");
        osiList.add("CHLD 01JAN08");
        airBookVO.setOsiList(osiList);

        List<String> contactInfoList = Lists.newArrayList();
        contactInfoList.add("023-57651234");
        airBookVO.setContactInfoList(contactInfoList);
        airBookVO.setTicketTimeLimit("2014-05-29T00:01:00");

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
        flightSegmentVO.setDepartureDateTime("2014-09-29T07:00:00");
        flightSegmentVO.setArrivalDateTime("2014-09-29T09:10:00");
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
        String birthDate = "2008-01-01";
        AirTravelerVO airTravelerVO = new AirTravelerVO();
        airTravelerVO.setGender(IBEConst.Gender.MALE.getCode());
        airTravelerVO.setPassengerTypeCode(IBEConst.PassengerType.CHILD.getCode());
        airTravelerVO.setBirthDate(birthDate);

        List<PersonNameVO> personNameVOList = new ArrayList<>();
        PersonNameVO personNameVO = new PersonNameVO();
        personNameVO.setLanguageType(IBEConst.LanguageType.ZH.getCode());
        personNameVO.setSurname("张学友");
        personNameVOList.add(personNameVO);
        airTravelerVO.setPersonNameList(personNameVOList);

        airTravelerVO.setDocType(IBEConst.DocumentType.ID.getCode());
        airTravelerVO.setDocId("31010420080101573X");
        airTravelerVO.setBirthDate("2008-01-01");
        airTravelerVO.setComment("HK");
        airTravelerList.add(airTravelerVO);
        airBookVO.setAirTravelerList(airTravelerList);
    }

    private void fillSpecialServiceRequest(AirBookRequestVO airBookVO){
        List<SpecialServiceRequestVO> ssrList = new ArrayList<SpecialServiceRequestVO>();
        SpecialServiceRequestVO ssr = new SpecialServiceRequestVO();
        //服务代码类别，例如FQTV
        ssr.setSsrCode("CHLD");
        //说明：如果 SSRCode 是 CHLD，那么必须设置 TravelerRefNumber 和 Airline
        ssr.setTravelerRefNumber("1");
        ssr.setAirline(airBookVO.getFlightSegmentList().get(0).getMarketingAirline());
        //关键点！！！！！！！！！！
        String birthDate = airBookVO.getAirTravelerList().get(0).getBirthDate();
        Date tempBirthDate = DateUtils.parseDate(birthDate, DateUtils.DATE_FORMAT);
        String formattedBirthDate = DateUtils.formatDate(tempBirthDate, DateUtils.DATE_FORMAT_2, Locale.ENGLISH);
        ssr.setText(formattedBirthDate.toUpperCase());
        //行动代码，例如：HK
        ssr.setStatus("HK");
        ssrList.add(ssr);
        airBookVO.setSsrList(ssrList);
    }

}

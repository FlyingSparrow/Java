package com.sparrow.ibe.bookingservice.airbook.stage.impl;

import com.sparrow.ibe.bookingservice.airbook.model.AirBookRequest;
import com.sparrow.ibe.bookingservice.airbook.stage.AirBookStage;
import com.sparrow.ibe.bookingservice.airbook.transformer.AirBookRequestTransformer;
import com.sparrow.ibe.bookingservice.airbook.vo.AirBookRequestVO;
import com.sparrow.ibe.bookingservice.airbook.vo.AirTravelerVO;
import com.sparrow.ibe.bookingservice.airbook.vo.FlightSegmentVO;
import com.sparrow.ibe.bookingservice.airbook.vo.PersonNameVO;
import com.sparrow.ibe.constants.IBEConst;
import com.sparrow.utils.DateUtils;
import com.sparrow.utils.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 场景15：国际航班+单个成人+单程+婴儿
 * 说明：
 * 必填信息：1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
 * 旅客信息说明：3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）
 *
 * @author wangjianchun
 * @create 2018/7/11
 */
@Component("airBookStage15")
public class AirBookStage15 implements AirBookStage {

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
        osiList.add("CTCT13444444444");
        osiList.add("CTCM13444444444/P1");
        airBookVO.setOsiList(osiList);

        //备注信息
        List<String> remarkList = Lists.newArrayList();
        remarkList.add("TJ AUTH SZV122");
        airBookVO.setRemarkList(remarkList);

        List<String> contactInfoList = Lists.newArrayList();
        contactInfoList.add("0512-82274045TCBOOK");
        airBookVO.setContactInfoList(contactInfoList);
        airBookVO.setTicketTimeLimit("2014-12-30T07:00:00");
        airBookVO.setEnvelopDelay("false");

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
        flightSegmentVO.setDepartureDateTime("2014-12-30T09:00:00");
        flightSegmentVO.setArrivalDateTime("2014-12-30T11:30:00");
        flightSegmentVO.setFlightNumber("501");
        flightSegmentVO.setDepartureAirport("PVG");
        flightSegmentVO.setArrivalAirport("HKG");
        flightSegmentVO.setAirEquipType("330");
        flightSegmentVO.setCodeShareInd("false");
        flightSegmentVO.setMarketingAirline("MU");
        flightSegmentVO.setNumberInParty("2");
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

        //第一个旅客的信息
        AirTravelerVO airTravelerVO = new AirTravelerVO();
        airTravelerVO.setGender(IBEConst.Gender.MALE.getCode());
        airTravelerVO.setPassengerTypeCode(IBEConst.PassengerType.ADULT.getCode());
        airTravelerVO.setAccompaniedByInfant("true");

        List<PersonNameVO> personNameVOList = new ArrayList<>();
        PersonNameVO personNameVO = new PersonNameVO();
        personNameVO.setLanguageType(IBEConst.LanguageType.EN.getCode());
        personNameVO.setSurname("RUAN/WENCHAO MR");
        personNameVOList.add(personNameVO);
        airTravelerVO.setPersonNameList(personNameVOList);

        String birthDate = "1999-09-04";
        airTravelerVO.setDocType(IBEConst.DocumentType.PASSPORT.getCode());
        airTravelerVO.setDocTypeDetail("P");
        airTravelerVO.setDocId("G446164");
        airTravelerVO.setDocHolderNationality("CN");
        airTravelerVO.setDocIssueCountry("CN");
        airTravelerVO.setBirthDate(birthDate);
        airTravelerVO.setGender(IBEConst.Gender.MALE.getCode());
        airTravelerVO.setExpireDate("2031-05-19");
        airTravelerVO.setDocHolderGivenName("WENCHAO");
        airTravelerVO.setDocHolderSurname("RUAN");
        airTravelerVO.setAge(StringUtils.calculateAge(DateUtils.parseDate(birthDate, DateUtils.DATE_FORMAT))+"");
        airTravelerList.add(airTravelerVO);
        airBookVO.setAirTravelerList(airTravelerList);


        //第二个旅客的信息
        String birthDateOfInfant = "2012-03-04";
        airTravelerVO = new AirTravelerVO();
        airTravelerVO.setGender(IBEConst.Gender.MALE.getCode());
        airTravelerVO.setPassengerTypeCode(IBEConst.PassengerType.INFANT.getCode());
        airTravelerVO.setBirthDate(birthDateOfInfant);

        personNameVOList = new ArrayList<>();
        PersonNameVO personNameVO2 = new PersonNameVO();
        personNameVO2.setLanguageType(IBEConst.LanguageType.EN.getCode());
        personNameVO2.setSurname("RUAN/XIAOPANG");
        personNameVOList.add(personNameVO2);
        airTravelerVO.setPersonNameList(personNameVOList);

        airTravelerVO.setAge(StringUtils.calculateAge(DateUtils.parseDate(birthDateOfInfant, DateUtils.DATE_FORMAT))+"");
        airTravelerList.add(airTravelerVO);
        airBookVO.setAirTravelerList(airTravelerList);
    }

}

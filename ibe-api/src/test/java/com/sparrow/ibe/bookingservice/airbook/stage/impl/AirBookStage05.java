package com.sparrow.ibe.bookingservice.airbook.stage.impl;

import com.sparrow.ibe.bookingservice.airbook.model.AirBookRequest;
import com.sparrow.ibe.bookingservice.airbook.stage.AirBookStage;
import com.sparrow.ibe.bookingservice.airbook.transformer.AirBookRequestTransformer;
import com.sparrow.ibe.bookingservice.airbook.vo.AirBookVO;
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
 * 场景5：国内航班+单个成人+单程+婴儿
 * 说明：
 * 必填信息：1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
 * 旅客信息说明：3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）
 *
 * @author wangjianchun
 * @create 2018/7/11
 */
@Component("airBookStage05")
public class AirBookStage05 implements AirBookStage {

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
        osiList.add("CTCT13801035768");
        osiList.add("CTCM13801035768/P1");
        airBookVO.setOsiList(osiList);

        //备注信息
        List<String> remarkList = new ArrayList<>();
        remarkList.add("TJ AUTH SHA255");
        airBookVO.setRemarkList(remarkList);

        List<String> contactInfoList = Lists.newArrayList();
        contactInfoList.add("0512-82274023");
        airBookVO.setContactInfoList(contactInfoList);
        airBookVO.setTicketTimeLimit("2013-11-26T05:32:20");
        airBookVO.setEnvelopDelay("false");

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
        flightSegmentVO.setDepartureDateTime("2013-11-26T07:30:00");
        flightSegmentVO.setArrivalDateTime("2013-11-26T09:40:00");
        flightSegmentVO.setFlightNumber("1831");
        flightSegmentVO.setDepartureAirport("PEK");
        flightSegmentVO.setArrivalAirport("SHA");
        flightSegmentVO.setMarketingAirline("CA");
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

        //旅客证件信息
        airTravelerVO.setDocType(IBEConst.DocumentType.ID.getCode());
        airTravelerVO.setDocId("1234567890");
        airTravelerVO.setDocHolderNationality("CN");
        airTravelerVO.setBirthDate(birthDate);
        airTravelerVO.setGender("MALE");
        airTravelerVO.setExpireDate("2031-01-01");
        airTravelerVO.setDocHolderSurname("李雷");
        airTravelerVO.setAge(StringUtils.calculateAge(DateUtils.parseDate(birthDate, DateUtils.DATE_FORMAT)) + "");
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
        airTravelerVO.setAge(StringUtils.calculateAge(DateUtils.parseDate(birthDateOfInfant, DateUtils.DATE_FORMAT)) + "");
        airTravelerVO.setPersonNameList(personNameVOList);
        airTravelerList.add(airTravelerVO);

        airBookVO.setAirTravelerList(airTravelerList);
    }

}

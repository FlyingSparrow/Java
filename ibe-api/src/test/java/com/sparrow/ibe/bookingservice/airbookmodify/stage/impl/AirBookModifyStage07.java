package com.sparrow.ibe.bookingservice.airbookmodify.stage.impl;

import com.sparrow.ibe.bookingservice.airbook.model.AirTraveler;
import com.sparrow.ibe.bookingservice.airbook.model.PersonName;
import com.sparrow.ibe.bookingservice.airbook.model.SpecialServiceRequest;
import com.sparrow.ibe.bookingservice.airbook.model.TravelerRefNumber;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AbmAirReservation;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AirBookModifyRequest;
import com.sparrow.ibe.bookingservice.airbookmodify.model.TravelerInfo;
import com.sparrow.ibe.bookingservice.airbookmodify.stage.AirBookModifyStage;
import com.sparrow.ibe.bookingservice.airbookmodify.transformer.AirBookModifyRequestTransformer;
import com.sparrow.ibe.bookingservice.airbookmodify.vo.AbmRequestVO;
import com.sparrow.ibe.bookingservice.airbookmodify.vo.AirBookModifyRequestVO;
import com.sparrow.ibe.bookingservice.airbookmodify.vo.AirReservationVO;
import com.sparrow.ibe.constants.IBEConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 场景7：添加 SSR-FQTV
 * <p>
 * 说明：输入参数结构（3大信息）
 * 1.POS信息  2.修改后的预定信息  3.修改前的预定信息
 *
 * @author wangjianchun
 * @create 2018/7/16
 */
@Component("airBookModifyStage07")
public class AirBookModifyStage07 implements AirBookModifyStage {

    @Autowired
    private AirBookModifyRequestTransformer airBookModifyRequestTransformer;

    @Override
    public AirBookModifyRequest buildRequest() {
        AirBookModifyRequestVO airBookModifyRequestVO = new AirBookModifyRequestVO();

        //修改后的预定信息
        fillAirReservationInfoAfterModify(airBookModifyRequestVO);

        //修改前的预定信息
        fillAirReservationInfoBeforeModify(airBookModifyRequestVO);

        return airBookModifyRequestTransformer.transform(airBookModifyRequestVO);
    }

    /**
     * 填充修改后的预定信息
     *
     * @param airBookModifyRequestVO
     */
    private void fillAirReservationInfoAfterModify(AirBookModifyRequestVO airBookModifyRequestVO){
        AbmRequestVO abmRequestVO = new AbmRequestVO();
        abmRequestVO.setModificationType(IBEConst.ModificationType.SSR_ADD.getCode());

        AbmAirReservation abmAirReservation = new AbmAirReservation();
        List<TravelerInfo> travelerInfoList = new ArrayList<TravelerInfo>();
        TravelerInfo travelerInfo = new TravelerInfo();

        //SSR-FQTV信息
        List<SpecialServiceRequest> ssrList = new ArrayList<SpecialServiceRequest>();
        SpecialServiceRequest ssr = new SpecialServiceRequest();
        ssr.setSsrCode(IBEConst.SSRCode.FQTV.getCode());
        ssr.setStatus("HK");
        ssr.setAirlineCode("CA");
        //常旅客代码
        ssr.setText("CA185336034");
        ssr.setTravelerRefNumberRPH("1");
        ssrList.add(ssr);

        travelerInfo.setSsrList(ssrList);
        travelerInfoList.add(travelerInfo);
        abmAirReservation.setTravelerInfoList(travelerInfoList);
        abmRequestVO.setAbmAirReservation(abmAirReservation);
        airBookModifyRequestVO.setAbmRequestVO(abmRequestVO);
    }

    /**
     * 填充修改前的预定信息
     *
     * @param airBookModifyRequestVO
     */
    private void fillAirReservationInfoBeforeModify(AirBookModifyRequestVO airBookModifyRequestVO){
        AirReservationVO airReservationVO = new AirReservationVO();

        //旅客信息
        List<TravelerInfo> travelerInfoList = new ArrayList<TravelerInfo>();
        TravelerInfo travelerInfo = new TravelerInfo();
        List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
        AirTraveler airTraveler = new AirTraveler();
        airTraveler.setPassengerTypeCode(IBEConst.PassengerType.ADULT.getCode());
        airTraveler.setRph("1");


        //旅客姓名
        fillPersonName(airTraveler);

        //旅客序号
        fillTravelerRefNumber(airTraveler);

        airTravelerList.add(airTraveler);
        travelerInfo.setAirTravelerList(airTravelerList);
        travelerInfoList.add(travelerInfo);
        airReservationVO.setTravelerInfoList(travelerInfoList);

        airReservationVO.setPnr("HPJW2Q");
        airBookModifyRequestVO.setAirReservationVO(airReservationVO);
    }

    /**
     * 填充旅客序号
     *
     * @param airTraveler
     */
    private void fillTravelerRefNumber(AirTraveler airTraveler) {
        List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
        TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
        travelerRefNumber.setRph("1");
        travelerRefNumberList.add(travelerRefNumber);
        airTraveler.setTravelerRefNumberList(travelerRefNumberList);
    }

    /**
     * 填充旅客姓名
     *
     * @param airTraveler
     */
    private void fillPersonName(AirTraveler airTraveler) {
        List<PersonName> personNameList = new ArrayList<PersonName>();
        PersonName personName = new PersonName();
        personName.setLanguageType(IBEConst.LanguageType.ZH.getCode());
        personName.setSurname("高明");
        personNameList.add(personName);
        airTraveler.setPersonNameList(personNameList);
    }

}

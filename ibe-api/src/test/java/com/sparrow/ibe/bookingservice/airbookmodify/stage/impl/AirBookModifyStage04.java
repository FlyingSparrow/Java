package com.sparrow.ibe.bookingservice.airbookmodify.stage.impl;

import com.sparrow.ibe.bookingservice.airbook.model.FlightSegment;
import com.sparrow.ibe.bookingservice.airbook.model.SpecialRemark;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AbmAirReservation;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AirBookModifyRequest;
import com.sparrow.ibe.bookingservice.airbookmodify.model.TravelerInfo;
import com.sparrow.ibe.bookingservice.airbookmodify.stage.AirBookModifyStage;
import com.sparrow.ibe.bookingservice.airbookmodify.transformer.AirBookModifyRequestTransformer;
import com.sparrow.ibe.bookingservice.airbookmodify.vo.AbmRequestVO;
import com.sparrow.ibe.bookingservice.airbookmodify.vo.AirBookModifyRequestVO;
import com.sparrow.ibe.bookingservice.airbookmodify.vo.AirReservationVO;
import com.sparrow.ibe.constants.IBEConst;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 场景4：添加RMK（备注信息）
 * <p>
 * 说明：输入参数结构（3大信息）
 * 1.POS信息  2.修改后的预定信息  3.修改前的预定信息
 *
 * @author wangjianchun
 * @create 2018/7/16
 */
@Component("airBookModifyStage04")
public class AirBookModifyStage04 implements AirBookModifyStage {

    @Autowired
    private AirBookModifyRequestTransformer airBookModifyRequestTransformer;

    @Override
    public AirBookModifyRequest buildRequest() {
        AirBookModifyRequestVO airBookModifyRequestVO = new AirBookModifyRequestVO();

        //需要修改或者添加的信息
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
        AbmAirReservation abmAirReservation = new AbmAirReservation();
        List<TravelerInfo> travelerInfoList = Lists.newArrayList();
        TravelerInfo travelerInfo = new TravelerInfo();
        abmRequestVO.setModificationType(IBEConst.ModificationType.REMARK_ADD.getCode());

        //RMK（备注信息）
        fillRemark(travelerInfo);

        travelerInfoList.add(travelerInfo);
        abmAirReservation.setTravelerInfoList(travelerInfoList);
        abmRequestVO.setAbmAirReservation(abmAirReservation);
        airBookModifyRequestVO.setAbmRequestVO(abmRequestVO);
    }

    /**
     * 填充RMK（备注信息）
     *
     * @param travelerInfo
     */
    private void fillRemark(TravelerInfo travelerInfo) {
        List<SpecialRemark> remarkList = new ArrayList<SpecialRemark>();
        SpecialRemark remark = new SpecialRemark();
        remark.setTravelerRefNumberRPH("1");
        remark.setFlightRefNumberRPH("1");
        remark.setText("测试添加RMK");
        remarkList.add(remark);
        travelerInfo.setRemarkList(remarkList);
    }

    /**
     * 填充修改前的预定信息
     *
     * @param airBookModifyRequestVO
     */
    private void fillAirReservationInfoBeforeModify(AirBookModifyRequestVO airBookModifyRequestVO){
        AirReservationVO airReservationVO = new AirReservationVO();
        //航段信息
        fillFlightSegment(airReservationVO);

        airReservationVO.setPnr("JG6R08");
        airBookModifyRequestVO.setAirReservationVO(airReservationVO);
    }

    /**
     * 填充航段信息
     *
     * @param airReservationVO
     */
    private void fillFlightSegment(AirReservationVO airReservationVO) {
        List<FlightSegment> flightSegmentList = new ArrayList<FlightSegment>();
        FlightSegment flightSegment = new FlightSegment();
        flightSegment.setRph("1");
        flightSegment.setDepartureDateTime("2014-08-26T07:00:00");
        flightSegment.setArrivalDateTime("2014-08-26T09:10:00");
        flightSegment.setCodeshareInd("false");
        flightSegment.setFlightNumber("MU5138");
        flightSegment.setStatus("NN");
        flightSegment.setSegmentType("NORMAL");
        flightSegment.setDepartureAirport("PEK");
        flightSegment.setArrivalAirport("SHA");
        flightSegment.setAirEquipType("733");
        flightSegment.setMarketingAirline("MU");
        flightSegment.setResBookDesigCode("Y");
        flightSegmentList.add(flightSegment);
        airReservationVO.setFlightSegmentList(flightSegmentList);
    }
}

package com.sparrow.ibe.bookingservice.airbookmodify.stage.impl;

import com.sparrow.ibe.bookingservice.airbook.model.*;
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
 * 场景5：添加 SSR-CHLD
 * <p>
 * 说明：输入参数结构（3大信息）
 * 1.POS信息  2.修改后的预定信息  3.修改前的预定信息
 *
 * @author wangjianchun
 * @create 2018/7/16
 */
@Component("airBookModifyStage05")
public class AirBookModifyStage05 implements AirBookModifyStage {

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
    private void fillAirReservationInfoAfterModify(AirBookModifyRequestVO airBookModifyRequestVO) {
        AbmRequestVO abmRequestVO = new AbmRequestVO();
        abmRequestVO.setModificationType(IBEConst.ModificationType.SSR_ADD.getCode());

        AbmAirReservation abmAirReservation = new AbmAirReservation();
        List<TravelerInfo> travelerInfoList = new ArrayList<TravelerInfo>();
        TravelerInfo travelerInfo = new TravelerInfo();
        //SSR（特殊服务请求）信息
        List<SpecialServiceRequest> ssrList = new ArrayList<SpecialServiceRequest>();
        SpecialServiceRequest ssr = new SpecialServiceRequest();
        ssr.setSsrCode("CHLD");
        ssr.setStatus("HK");
        ssr.setFlightRefNumberRPH("1");
        ssr.setTravelerRefNumberRPH("2");
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
    private void fillAirReservationInfoBeforeModify(AirBookModifyRequestVO airBookModifyRequestVO) {
        AirReservationVO airReservationVO = new AirReservationVO();

        //航段信息
        fillFlightSegment(airReservationVO);
        //旅客信息
        fillTravelerInfo(airReservationVO);

        airReservationVO.setPnr("JG6R1Y");
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
        flightSegment.setFlightNumber("MU5138");
        flightSegment.setNumberInParty("1");
        flightSegment.setStatus("NN");
        flightSegment.setSegmentType("NORMAL");
        flightSegment.setOperatingAirline("MU");
        flightSegment.setFlightNumberOfOperatingAirline("MU5138");
        flightSegment.setDepartureAirport("PEK");
        flightSegment.setArrivalAirport("SHA");
        flightSegment.setAirEquipType("733");
        flightSegment.setMarketingAirline("MU");
        flightSegment.setResBookDesigCode("Y");
        flightSegmentList.add(flightSegment);
        airReservationVO.setFlightSegmentList(flightSegmentList);
    }

    /**
     * 填充旅客信息
     *
     * @param airReservationVO
     */
    private void fillTravelerInfo(AirReservationVO airReservationVO){
        List<TravelerInfo> travelerInfoList = new ArrayList<TravelerInfo>();
        TravelerInfo travelerInfo = new TravelerInfo();
        List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
        AirTraveler airTraveler = new AirTraveler();
        airTraveler.setPassengerTypeCode(IBEConst.PassengerType.ADULT.getCode());
        airTraveler.setRph("2");
        airTraveler.setBirthDate("2008-05-01");

        //旅客姓名
        fillPersonName(airTraveler);

        //证件信息
        fillDocument(airTraveler);

        List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
        TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
        travelerRefNumber.setRph("2");
        travelerRefNumberList.add(travelerRefNumber);
        airTraveler.setTravelerRefNumberList(travelerRefNumberList);

        airTravelerList.add(airTraveler);
        travelerInfo.setAirTravelerList(airTravelerList);
        travelerInfoList.add(travelerInfo);
        airReservationVO.setTravelerInfoList(travelerInfoList);
    }

    /**
     * 填充旅客姓名信息
     *
     * @param airTraveler
     */
    private void fillPersonName(AirTraveler airTraveler) {
        List<PersonName> personNameList = new ArrayList<PersonName>();
        PersonName personName = new PersonName();
        personName.setLanguageType(IBEConst.LanguageType.ZH.getCode());
        personName.setSurname("高一");
        personNameList.add(personName);
        airTraveler.setPersonNameList(personNameList);
    }

    /**
     * 填充证件信息
     *
     * @param airTraveler
     */
    private void fillDocument(AirTraveler airTraveler) {
        List<Document> documentList = new ArrayList<Document>();
        Document document = new Document();
        document.setDocType(IBEConst.DocumentType.ID.getCode());
        document.setDocId("120221200805011150");
        document.setBirthDate("2008-05-01");
        documentList.add(document);
        airTraveler.setDocumentList(documentList);
    }

}

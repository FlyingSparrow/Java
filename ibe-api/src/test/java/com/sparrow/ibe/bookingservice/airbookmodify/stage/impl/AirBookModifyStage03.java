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
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 场景3：添加 OSI（其它服务信息）
 * <p>
 * 说明：输入参数结构（3大信息）
 * 1.POS信息  2.修改后的预定信息  3.修改前的预定信息
 *
 * @author wangjianchun
 * @create 2018/7/16
 */
@Component("airBookModifyStage03")
public class AirBookModifyStage03 implements AirBookModifyStage {

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
        abmRequestVO.setModificationType(IBEConst.ModificationType.OSI_ADD.getCode());

        AbmAirReservation abmAirReservation = new AbmAirReservation();
        List<TravelerInfo> travelerInfoList = Lists.newArrayList();
        TravelerInfo travelerInfo = new TravelerInfo();
        //OSI信息
        List<OtherServiceInformation> osiList = new ArrayList<OtherServiceInformation>();
        OtherServiceInformation osi = new OtherServiceInformation();
        osi.setTravelerRefNumberRPH("1");
        osi.setAirlineCode("MU");
        osi.setText("OSI");
        osiList.add(osi);
        travelerInfo.setOsiList(osiList);
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
        //旅客信息
        AirReservationVO airReservationVO = new AirReservationVO();
        List<TravelerInfo> travelerInfoList = Lists.newArrayList();
        TravelerInfo travelerInfo = new TravelerInfo();

        List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
        AirTraveler airTraveler = new AirTraveler();
        airTraveler.setPassengerTypeCode(IBEConst.PassengerType.ADULT.getCode());
        airTraveler.setRph("1");

        //旅客姓名
        fillPersonName(airTraveler);

        //旅客证件信息
        fillDocument(airTraveler);

        //旅客序号
        fillTravelerRefNumber(airTraveler);

        airTravelerList.add(airTraveler);
        travelerInfo.setAirTravelerList(airTravelerList);
        travelerInfoList.add(travelerInfo);
        airReservationVO.setTravelerInfoList(travelerInfoList);


        airReservationVO.setPnr("HTTD1H");

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
     * 填充证件信息
     *
     * @param airTraveler
     */
    private void fillDocument(AirTraveler airTraveler) {
        List<Document> documentList = new ArrayList<Document>();
        Document document = new Document();
        document.setDocType(IBEConst.DocumentType.ID.getCode());
        document.setDocId("120221197001011150");
        documentList.add(document);
        airTraveler.setDocumentList(documentList);
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

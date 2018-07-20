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
 * 场景6：添加SSR-DOCS
 * <p>
 * 说明：输入参数结构（3大信息）
 * 1.POS信息  2.修改后的预定信息  3.修改前的预定信息
 *
 * @author wangjianchun
 * @create 2018/7/16
 */
@Component("airBookModifyStage06")
public class AirBookModifyStage06 implements AirBookModifyStage {

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

        //SSR-DOCS信息
        List<SpecialServiceRequest> ssrList = new ArrayList<SpecialServiceRequest>();
        SpecialServiceRequest ssr = new SpecialServiceRequest();
        ssr.setSsrCode(IBEConst.SSRCode.DOCS.getCode());
        ssr.setServiceQuantity("1");
        ssr.setStatus("NN");
        ssr.setRph("1");
        ssr.setAirlineCode("CA");
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
    private void fillAirReservationInfoBeforeModify(AirBookModifyRequestVO airBookModifyRequestVO) {
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

        //证件信息
        fillDocument(airTraveler);

        //旅客序号
        fillTravelerRefNumber(airTraveler);

        airTravelerList.add(airTraveler);
        travelerInfo.setAirTravelerList(airTravelerList);
        travelerInfoList.add(travelerInfo);
        airReservationVO.setTravelerInfoList(travelerInfoList);

        airReservationVO.setPnr("JG6R08");
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

    /**
     * 填充证件信息
     *
     * @param airTraveler
     */
    private void fillDocument(AirTraveler airTraveler) {
        List<Document> documentList = new ArrayList<Document>();
        Document document = new Document();
        //证件类型，PP - 护照；NI - 身份证
        document.setDocType(IBEConst.DocumentType.PASSPORT.getCode());
        //证件类型描述，在证件类型为PP时，提供具体护照类型，如：F、P、AC等
        document.setDocTypeDetail("P");
        //证件号
        document.setDocId("G446164");
        //证件持有人国籍
        document.setDocHolderNationality("CN");
        //发证国家
        document.setDocIssueCountry("CN");
        //出生日期
        document.setBirthDate("1984-09-04");
        //证件持有人性别，证件类型为PP时，需要指定性别
        document.setGender(IBEConst.Gender.MALE.getCode());
        //证件有效期截止日期，证件类型为PP时，需要指定到期时间
        document.setExpireDate("2031-12-19");
        //证件编号，若多个证件编号，输入不同编号，都写入主机
        document.setRph("1");
        //证件持有人姓名的名，若填写护照，需要填写此项，姓名为zhang/san时，这里是san
        document.setDocHolderGivenName("DANA");
        //证件持有人姓名的姓，若填写护照，需要填写此项，姓名为zhang/san时，这里是zhang
        document.setDocHolderSurname("WANG");
        documentList.add(document);
        airTraveler.setDocumentList(documentList);
    }

}

package com.sparrow.ibe.bookingservice.airbook.transformer;

import com.google.common.collect.Lists;
import com.sparrow.ibe.bookingservice.airbook.model.*;
import com.sparrow.ibe.bookingservice.airbook.vo.*;
import com.sparrow.ibe.constants.IBEConst;
import com.sparrow.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 接口类别：预订服务
 * 接口名称：自动预订服务
 * 接口ID：JP011
 * 自动预订服务请求的转换器，用于将用户的预定请求转换为自动预订服务的请求对象
 *
 * 说明：
 * 必填信息：1.POS信息（必填） 2.行程信息AirItinerary（必填）  3.旅客信息（必填） 4.客票信息 5.扩展信息（必填） 6.预定信息  7.价格信息
 * 旅客信息说明：3.旅客信息（3.1 旅客基本信息  3.2  旅客其他请求信息）
 *
 * @author wangjianchun
 * @create 2018/7/11
 */
@Component
public class AirBookRequestTransformer {

    /**
     * 将用户的预定请求转换为自动预订服务的请求对象
     *
     * @param requestVO
     * @return
     */
    public AirBookRequest transform(AirBookRequestVO requestVO) {
        AirBookRequest request = new AirBookRequest();

        request.setRequestId(StringUtils.randomUUID());
        request.setCreatedTime(System.currentTimeMillis());
        if(StringUtils.isNotEmpty(requestVO.getSegmentCheckInd())){
            request.setSegmentCheckInd(requestVO.getSegmentCheckInd());
        }
        if(StringUtils.isNotEmpty(requestVO.getPtcBindInd())){
            request.setPtcBindInd(requestVO.getPtcBindInd());
        }
        if(StringUtils.isNotEmpty(requestVO.getDisplayResInd())){
            request.setDisplayResInd(requestVO.getDisplayResInd());
        }
        if(StringUtils.isNotEmpty(requestVO.getAutoARNKInd())){
            request.setAutoARNKInd(requestVO.getAutoARNKInd());
        }

        //1.POS信息
        request.setPseudoCityCode("sparrow");

        //2.行程信息
        fillItinerary(request, requestVO);

        //3.1旅客信息
        fillAirTraveler(request, requestVO);

        //4.客票信息(出票时间)
        request.setTicketTimeLimit(requestVO.getTicketTimeLimit());

        //填充 OSI 信息
        fillOtherServiceInformation(request, requestVO);
        //填充特殊备注信息
        fillSpecialRemark(request, requestVO);
        //填充特殊服务请求信息
        fillSpecialServiceRequest(request, requestVO.getSsrList());

        //5.扩展信息
        List<String> contactInfoList = requestVO.getContactInfoList();
        if(contactInfoList != null && contactInfoList.size() > 0){
            request.setContactInfoList(contactInfoList);
        }
        //封口信息
        request.setEnvelopType("KI");
        if(StringUtils.isNotEmpty(requestVO.getEnvelopDelay())){
            request.setEnvelopDelay(requestVO.getEnvelopDelay());
        }

        return request;
    }

    /**
     * 填充行程信息
     *
     * @param airBookVO
     * @return
     */
    private void fillItinerary(AirBookRequest request, AirBookRequestVO airBookVO) {
        List<OriginDestinationOption> odList = new ArrayList<OriginDestinationOption>();
        OriginDestinationOption od = new OriginDestinationOption();
        List<FlightSegment> fsList = new ArrayList<FlightSegment>();
        List<FlightSegmentVO> flightSegmentVOList = airBookVO.getFlightSegmentList();
        int rph = 0;
        for(FlightSegmentVO item : flightSegmentVOList){
            rph++;
            FlightSegment fs = new FlightSegment();
            fs.setRph(rph+"");
            fs.setDepartureDateTime(item.getDepartureDateTime());
            fs.setArrivalDateTime(item.getArrivalDateTime());

            if (StringUtils.isNotEmpty(item.getCodeShareInd())) {
                fs.setCodeshareInd(item.getCodeShareInd());
            }

            fs.setFlightNumber(item.getFlightNumber());
            fs.setStatus("NN");
            fs.setSegmentType("NORMAL");
            fs.setDepartureAirport(item.getDepartureAirport());
            fs.setArrivalAirport(item.getArrivalAirport());
            fs.setAirEquipType(item.getAirEquipType());
            fs.setMarketingAirline(item.getMarketingAirline());
            fs.setResBookDesigCode(item.getResBookDesigCode());
            fsList.add(fs);
        }

        od.setFlightSegmentList(fsList);
        odList.add(od);

        request.setOriginDestinationOptionList(odList);
    }

    /**
     * 填充旅客信息
     *
     * @param request
     * @param airBookVO
     */
    private void fillAirTraveler(AirBookRequest request, AirBookRequestVO airBookVO) {
        int rph = 0;
        List<AirTravelerVO> airTravelerVOList = airBookVO.getAirTravelerList();
        List<String> flightSegmentRphList = Lists.newArrayList();
        List<FlightSegmentVO> flightSegmentVOList = airBookVO.getFlightSegmentList();
        int fsRph = 0;
        for(int i=0; i<flightSegmentVOList.size(); i++){
            fsRph++;
            flightSegmentRphList.add(fsRph+"");
        }
        if (airTravelerVOList != null && airTravelerVOList.size() > 0) {
            List<AirTraveler> airTravelerList = new ArrayList<AirTraveler>();
            for (AirTravelerVO item : airTravelerVOList) {
                rph++;
                String rphStr = rph + "";

                AirTraveler airTraveler = new AirTraveler();
                if (IBEConst.PassengerType.INFANT.getCode().equals(item.getPassengerTypeCode())
                        || IBEConst.PassengerType.CHILD.getCode().equals(item.getPassengerTypeCode())) {
                    //如果旅客类型为婴儿或者儿童，那么设置出生日期
                    airTraveler.setBirthDate(item.getBirthDate());
                }

                airTraveler.setGender(item.getGender());
                airTraveler.setPassengerTypeCode(item.getPassengerTypeCode());
                if (StringUtils.isNotEmpty(item.getAccompaniedByInfant())) {
                    airTraveler.setAccompaniedByInfant(item.getAccompaniedByInfant());
                }

                //旅客姓名信息
                fillPersonName(item, airTraveler);

                //旅客证件信息
                fillDocument(item, airTraveler, rphStr);

                if (StringUtils.isNotEmpty(item.getAge())) {
                    PassengerTypeQuantity ptq = new PassengerTypeQuantity();
                    ptq.setAge(item.getAge());
                    airTraveler.setPassengerTypeQuantity(ptq);
                }

                List<TravelerRefNumber> travelerRefNumberList = new ArrayList<TravelerRefNumber>();
                TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
                travelerRefNumber.setRph(rphStr);

                String rphOfInfant = findRphOfInfant(airTravelerVOList);
                if (IBEConst.PassengerType.ADULT.getCode().equals(item.getPassengerTypeCode())) {
                    //如果大人携带了婴儿，那么需要在大人的 travelerRefNumber 中添加婴儿的 travelerRefNumber 中的 rph
                    if (StringUtils.isNotEmpty(rphOfInfant)) {
                        travelerRefNumber.setInfantTravelerRPH(rphOfInfant);
                    }
                }else if(IBEConst.PassengerType.INFANT.getCode().equals(item.getPassengerTypeCode())){
                    if (StringUtils.isNotEmpty(rphOfInfant)) {
                        travelerRefNumber.setRph(rphOfInfant);
                    }
                }
                travelerRefNumberList.add(travelerRefNumber);

                if (isAccompaniedByInfant(airTravelerVOList)
                        || flightSegmentRphList.size() > 1) {
                    airTraveler.setFlightSegmentRPHList(flightSegmentRphList);
                }

                airTraveler.setRph(rphStr);
                if (StringUtils.isNotEmpty(item.getComment())) {
                    airTraveler.setComment(item.getComment());
                }
                airTraveler.setTravelerRefNumberList(travelerRefNumberList);


                if(isPassport(item)){
                    DocumentFlightBinding documentFlightBinding = new DocumentFlightBinding();
                    documentFlightBinding.setDocumentRPH(rphStr);
                    documentFlightBinding.setFlightSegmentRPH("1");
                    airTraveler.setDocumentFlightBinding(documentFlightBinding);
                }

                airTravelerList.add(airTraveler);
            }
            request.setAirTravelerList(airTravelerList);
        }
    }

    /**
     * 填充旅客姓名信息
     *
     * @param item
     * @param airTraveler
     */
    private void fillPersonName(AirTravelerVO item, AirTraveler airTraveler) {
        List<PersonName> personNameList = new ArrayList<PersonName>();
        List<PersonNameVO> personNameVOList = item.getPersonNameList();
        if (personNameVOList != null && personNameVOList.size() > 0) {
            for (PersonNameVO personNameVO : personNameVOList) {
                PersonName personName = new PersonName();
                personName.setLanguageType(personNameVO.getLanguageType());
                personName.setSurname(personNameVO.getSurname());
                personNameList.add(personName);
            }
        }
        airTraveler.setPersonNameList(personNameList);
    }

    /**
     * 填充旅客证件信息
     *  @param item
     * @param airTraveler
     * @param rphStr
     */
    private void fillDocument(AirTravelerVO item, AirTraveler airTraveler, String rphStr) {
        if(StringUtils.isEmpty(item.getDocId())){
            return;
        }

        List<Document> documentList = new ArrayList<Document>();
        Document document = new Document();
        document.setRph(rphStr);
        if (StringUtils.isNotEmpty(item.getDocType())) {
            document.setDocType(item.getDocType());
        }
        if (StringUtils.isNotEmpty(item.getDocTypeDetail())) {
            document.setDocTypeDetail(item.getDocTypeDetail());
        }
        if (StringUtils.isNotEmpty(item.getDocId())) {
            document.setDocId(item.getDocId());
        }
        if (StringUtils.isNotEmpty(item.getDocHolderNationality())) {
            document.setDocHolderNationality(item.getDocHolderNationality());
        }
        if (StringUtils.isNotEmpty(item.getDocIssueCountry())) {
            document.setDocIssueCountry(item.getDocIssueCountry());
        }
        if (StringUtils.isNotEmpty(item.getBirthDate())
                && !IBEConst.PassengerType.INFANT.getCode().equals(item.getPassengerTypeCode())) {
            //如果旅客类型不是婴儿，并且出生日期不为空，那么设置出生日期
            document.setBirthDate(item.getBirthDate());
        }
        if (StringUtils.isNotEmpty(item.getGender())
                && IBEConst.DocumentType.PASSPORT.getCode().equals(item.getDocType())) {
            //如果证件类型为护照，需要设置性别
            document.setGender(item.getGender());
        }
        if (StringUtils.isNotEmpty(item.getExpireDate())) {
            document.setExpireDate(item.getExpireDate());
        }
        documentList.add(document);
        airTraveler.setDocumentList(documentList);
    }

    /**
     * 查找婴儿的rph
     *
     * @param airTravelerVOList
     * @return
     */
    private String findRphOfInfant(List<AirTravelerVO> airTravelerVOList) {
        String result = "";

        int rph = 0;
        for (AirTravelerVO item : airTravelerVOList) {
            rph++;
            if (IBEConst.PassengerType.INFANT.getCode().equals(item.getPassengerTypeCode())) {
                if(StringUtils.isNotEmpty(item.getInfantTravelerRph())){
                    result = item.getInfantTravelerRph();
                }else{
                    result = rph + "";
                }
                break;
            }
        }

        return result;
    }

    /**
     * 判断旅客是否携带婴儿
     *
     * @param airTravelerVOList
     * @return
     */
    private boolean isAccompaniedByInfant(List<AirTravelerVO> airTravelerVOList) {
        for (AirTravelerVO item : airTravelerVOList) {
            if (IBEConst.PassengerType.INFANT.getCode().equals(item.getPassengerTypeCode())) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断旅客的证件是否为护照
     *
     * @param airTravelerVO
     * @return
     */
    private boolean isPassport(AirTravelerVO airTravelerVO){
        return (IBEConst.DocumentType.PASSPORT.getCode().equals(airTravelerVO.getDocType()));
    }

    /**
     * 填充旅客预定的服务信息 OSI
     *
     * @param request
     * @param airBookVO
     */
    private void fillOtherServiceInformation(AirBookRequest request, AirBookRequestVO airBookVO) {
        List<String> list = airBookVO.getOsiList();
        if (list != null && list.size() > 0) {
            List<OtherServiceInformation> otherServiceInformationList = new ArrayList<OtherServiceInformation>();
            for (String item : list) {
                OtherServiceInformation osi = new OtherServiceInformation();
                osi.setOsiCode("OTHS");
                osi.setAirlineCode(airBookVO.getFlightSegmentList().get(0).getMarketingAirline());
                if (item.startsWith("CTCM") || item.startsWith("CHLD")) {
                    //CTCM必须关联旅客，CTCM后数字部分最多30位；CHLD表示儿童
                    osi.setTravelerRefNumberRPH("1");
                }
                osi.setText(item);
                otherServiceInformationList.add(osi);
            }
            request.setOsiList(otherServiceInformationList);
        }
    }

    /**
     * 填充特殊备注信息
     *
     * @param request
     * @param airBookVO
     */
    private void fillSpecialRemark(AirBookRequest request, AirBookRequestVO airBookVO) {
        List<String> list = airBookVO.getRemarkList();
        if (list != null && list.size() > 0) {
            List<SpecialRemark> specialRemarkList = new ArrayList<SpecialRemark>();
            for (String item : list) {
                SpecialRemark remark = new SpecialRemark();
                remark.setText(item);
                specialRemarkList.add(remark);
            }
            request.setRmkList(specialRemarkList);
        }
    }

    /**
     * 填充特殊服务请求信息
     *
     * @param request
     * @param list
     */
    private void fillSpecialServiceRequest(AirBookRequest request, List<SpecialServiceRequestVO> list) {
        if (list != null && list.size() > 0) {
            List<SpecialServiceRequest> ssrList = new ArrayList<SpecialServiceRequest>();
            for (SpecialServiceRequestVO item : list) {
                SpecialServiceRequest ssr = new SpecialServiceRequest();
                ssr.setSsrCode(item.getSsrCode());
                ssr.setStatus(item.getStatus());
                ssr.setAirlineCode(item.getAirline());
                if("CHLD".equals(item.getSsrCode())){
                    ssr.setServiceQuantity("1");
                }
                ssr.setFlightRefNumberRPH(item.getFlightRefNumber());
                ssr.setTravelerRefNumberRPH(item.getTravelerRefNumber());
                ssr.setText(item.getText());
                ssrList.add(ssr);
            }
            request.setSsrList(ssrList);
        }
    }

}

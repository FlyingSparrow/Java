package com.sparrow.ibe.bookingservice.airbookmodify.transformer;

import com.google.common.collect.Lists;
import com.sparrow.ibe.bookingservice.airbook.model.AirReservation;
import com.sparrow.ibe.bookingservice.airbook.model.BookingReferenceID;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AbmAirReservation;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AirBookModifyRQ;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AirBookModifyRequest;
import com.sparrow.ibe.bookingservice.airbookmodify.model.TravelerInfo;
import com.sparrow.ibe.bookingservice.airbookmodify.vo.AbmRequestVO;
import com.sparrow.ibe.bookingservice.airbookmodify.vo.AirBookModifyRequestVO;
import com.sparrow.ibe.bookingservice.airbookmodify.vo.AirReservationVO;
import com.sparrow.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 接口类别：预订服务
 * 接口名称：自动修改服务
 * 接口ID：JP012
 * 自动修改服务请求的转换器，用于将用户的预定请求转换为自动预订服务的请求对象
 *
 * 说明：输入参数结构（3大信息）
 * 1.POS信息  2.修改后的预定信息  3.修改前的预定信息
 *
 * @author wangjianchun
 * @create 2018/7/11
 */
@Component
public class AirBookModifyRequestTransformer {

    /**
     * 将用户的预定请求转换为自动预订服务的请求对象
     *
     * @param requestVO
     * @return
     */
    public AirBookModifyRequest transform(AirBookModifyRequestVO requestVO) {
        AirBookModifyRequest request = new AirBookModifyRequest();

        //1.POS信息
        request.setPseudoCityCode("sparrow");

        //2.修改后的预定信息
        fillAirReservationInfoAfterModify(request, requestVO);

        //3.修改前的预定信息
        fillAirReservationInfoBeforeModify(request, requestVO);

        return request;
    }

    /**
     * 填充修改后的预定信息
     *
     * @param request
     * @param requestVO
     */
    private void fillAirReservationInfoAfterModify(AirBookModifyRequest request, AirBookModifyRequestVO requestVO){
        AirBookModifyRQ airBookModifyRQ = new AirBookModifyRQ();
        AbmRequestVO abmRequestVO = requestVO.getAbmRequestVO();
        airBookModifyRQ.setModificationType(abmRequestVO.getModificationType());
        if(StringUtils.isNotEmpty(abmRequestVO.getModificationInfo())){
            airBookModifyRQ.setModificationInfo(abmRequestVO.getModificationInfo());
        }

        List<AbmAirReservation> abmAirReservationList = Lists.newArrayList();
        AbmAirReservation abmAirReservation = abmRequestVO.getAbmAirReservation();
        if(abmAirReservation != null){
            abmAirReservationList.add(abmAirReservation);
            airBookModifyRQ.setAbmAirReservationList(abmAirReservationList);
        }
        request.setAirBookModifyRQ(airBookModifyRQ);
    }

    /**
     * 填充修改前的预定信息
     *
     * @param request
     * @param requestVO
     */
    private void fillAirReservationInfoBeforeModify(AirBookModifyRequest request, AirBookModifyRequestVO requestVO){
        AirReservation airReservation = new AirReservation();
        AirReservationVO airReservationVO = requestVO.getAirReservationVO();
        //PNR号信息
        List<BookingReferenceID> bookingReferenceIDList = new ArrayList<BookingReferenceID>();
        BookingReferenceID bookID = new BookingReferenceID();
        bookID.setId(airReservationVO.getPnr());
        bookingReferenceIDList.add(bookID);
        airReservation.setBookingReferenceIDList(bookingReferenceIDList);

        //旅客信息
        List<TravelerInfo> travelerInfoList = airReservationVO.getTravelerInfoList();
        if(travelerInfoList != null && travelerInfoList.size() > 0){
            airReservation.setTravelerInfoList(travelerInfoList);
        }

        request.setAirReservation(airReservation);
    }

}

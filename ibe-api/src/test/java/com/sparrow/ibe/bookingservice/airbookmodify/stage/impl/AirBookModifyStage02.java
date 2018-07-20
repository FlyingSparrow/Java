package com.sparrow.ibe.bookingservice.airbookmodify.stage.impl;

import com.sparrow.ibe.bookingservice.airbookmodify.model.AbmAirReservation;
import com.sparrow.ibe.bookingservice.airbookmodify.model.AirBookModifyRequest;
import com.sparrow.ibe.bookingservice.airbookmodify.stage.AirBookModifyStage;
import com.sparrow.ibe.bookingservice.airbookmodify.transformer.AirBookModifyRequestTransformer;
import com.sparrow.ibe.bookingservice.airbookmodify.vo.AbmRequestVO;
import com.sparrow.ibe.bookingservice.airbookmodify.vo.AirBookModifyRequestVO;
import com.sparrow.ibe.bookingservice.airbookmodify.vo.AirReservationVO;
import com.sparrow.ibe.constants.IBEConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 场景2：添加 EI
 * <p>
 * 说明：输入参数结构（3大信息）
 * 1.POS信息  2.修改后的预定信息  3.修改前的预定信息
 *
 * @author wangjianchun
 * @create 2018/7/16
 */
@Component("airBookModifyStage02")
public class AirBookModifyStage02 implements AirBookModifyStage {

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
        abmRequestVO.setModificationType(IBEConst.ModificationType.EI_ADD.getCode());
        AbmAirReservation abmAirReservation = new AbmAirReservation();
        abmAirReservation.setEndorsement("不得签转");
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
        airReservationVO.setPnr("HTTD1H");
        airBookModifyRequestVO.setAirReservationVO(airReservationVO);
    }
}

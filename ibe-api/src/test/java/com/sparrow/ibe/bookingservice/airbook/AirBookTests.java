package com.sparrow.ibe.bookingservice.airbook;

import com.sparrow.BaseTests;
import com.sparrow.app.config.IBEConfig;
import com.sparrow.ibe.bookingservice.airbook.dao.AirBookDao;
import com.sparrow.ibe.bookingservice.airbook.model.AirBookRequest;
import com.sparrow.ibe.bookingservice.airbook.model.AirBookResponse;
import com.sparrow.ibe.bookingservice.airbook.model.AirReservation;
import com.sparrow.ibe.model.DefaultError;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 接口类别：预订服务
 * 接口名称：自动预订服务
 * 接口ID：JP011
 * 自动预订服务的单元测试类
 *
 * @author wangjianchun
 * @create 2018/7/4
 */
public class AirBookTests extends BaseTests {

    @Autowired
    private IBEConfig ibeConfig;

    /**
     * 测试场景：国内航班+单个成人+单程
     */
    @Test
    public void testAirBook1() {
        int type = 1;
        AirBookRequest request = new AirBookRequestBuilder().buildRequest(type);
        AirBookDao dao = new AirBookDao();
        AirBookResponse response = (AirBookResponse) dao.execute(ibeConfig.getUsername(), ibeConfig.getPassword(), request);

        Assert.assertNotNull(response);

        logger.info("response.getAirReservationList().size(): {}", response.getAirReservationList().size());

        List<AirReservation> airReservationList = response.getAirReservationList();
        for (AirReservation ar : airReservationList) {
            logger.info("CommentList:" + ar.getCommentList());
        }
        List<DefaultError> errorList = response.getErrorList();
        logger.info("errorList.size(): {}", errorList.size());
        for (DefaultError error : errorList) {
            logger.info("errorCode: {}", error.getCode());
            logger.info("error.getCnMessage(): {}", error.getCnMessage());
        }
    }

}

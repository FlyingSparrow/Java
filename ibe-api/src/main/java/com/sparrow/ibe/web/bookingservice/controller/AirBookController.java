package com.sparrow.ibe.web.bookingservice.controller;

import com.sparrow.base.bean.AjaxResult;
import com.sparrow.base.controller.BaseController;
import com.sparrow.ibe.bookingservice.airbook.model.AirBookRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: AirBookController</p>
 * <p>Description: 自动预订服务 Controller</p>
 *
 * 接口类别：预订服务
 * 接口名称：自动预订服务
 * 接口ID：JP011
 *
 * @author zhangtong
 * @date 2017年7月6日
 */
@Controller
@RequestMapping("/airBook")
public class AirBookController extends BaseController {

    /**
     * <p>Description: airBook</p>
     *
     * @param entity
     * @param request
     * @return
     * @author zhangtong
     * @date 2017年6月26日
     */
    @RequestMapping(value = "airBook", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult airBook(@RequestBody AirBookRequest entity, HttpServletRequest request) {

        return success("预定成功");
    }

}

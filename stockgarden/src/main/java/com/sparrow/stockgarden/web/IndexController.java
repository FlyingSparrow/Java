package com.sparrow.stockgarden.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("collector", "");
        JSONObject userInfo = new JSONObject();
        userInfo.put("userName", "admin");
        model.addAttribute("user", userInfo);

        return "index";
    }

}
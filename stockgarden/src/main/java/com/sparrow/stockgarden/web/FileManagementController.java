package com.sparrow.stockgarden.web;


import com.alibaba.fastjson.JSONObject;
import com.sparrow.base.controller.BaseController;
import com.sparrow.config.CustomConfig;
import com.sparrow.stockgarden.mysql.model.BriefingCustomizedFile;
import com.sparrow.stockgarden.mysql.model.User;
import com.sparrow.stockgarden.mysql.service.BriefingCustomizedFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>Title: FileManagementController</p>
 * <p>Description: </p>
 *
 * @author wjc
 * @date 2018/12/6
 */
@Controller
@RequestMapping("/fileManagement")
public class FileManagementController extends BaseController {

    @Autowired
    private BriefingCustomizedFileService briefingCustomizedFileService;
    @Autowired
    private CustomConfig customConfig;

    @RequestMapping(value = "/getFileList", method = RequestMethod.POST)
    public String getFileList(User user, Model model) {
        Long userId = getUserId();
        List<BriefingCustomizedFile> fileList = briefingCustomizedFileService.findAllByCreatorIdOrderByCreatedDateDesc(userId);
        JSONObject favorites = new JSONObject();
        favorites.put("name", "未读列表");
        favorites.put("count", 0);
        favorites.put("publicCount", 0);

        JSONObject otherPeople = new JSONObject();
        otherPeople.put("username", "sparrow");
        model.addAttribute("collects", fileList);
        model.addAttribute("favorites", favorites);
        model.addAttribute("otherPeople", otherPeople);
        model.addAttribute("userId", userId);
        model.addAttribute("size", fileList.size());
        logger.info("standard end :"+ userId);

        return "collect/fileList";
    }

}

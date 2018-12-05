package com.sparrow.stockgarden.web;

import com.sparrow.base.controller.BaseController;
import com.sparrow.constants.SysConst;
import com.sparrow.stockgarden.mysql.model.User;
import com.sparrow.stockgarden.mysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("collector", "");
        User user = super.getUser();
        if (null != user) {
            model.addAttribute("user", user);
        }
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        User user = new User();
        user.setId(1L);
        user.setUsername("Sparrow");

//		long size= collectRepository.countByUserIdAndIsDelete(getUserId(),IsDelete.NO);
//		Config config = configRepository.findByUserId(getUserId());
//		Favorites favorites = favoritesRepository.findById(Long.parseLong(config.getDefaultFavorties()));
//		List<String> followList = followRepository.findByUserId(getUserId());
//		model.addAttribute("config",config);
//		model.addAttribute("favorites",favorites);
//		model.addAttribute("size",size);
//		model.addAttribute("followList",followList);
        model.addAttribute("user", user);
//		model.addAttribute("newAtMeCount",noticeRepository.countByUserIdAndTypeAndReaded(getUserId(), "at", "unread"));
//		model.addAttribute("newCommentMeCount",noticeRepository.countByUserIdAndTypeAndReaded(getUserId(), "comment", "unread"));
//		model.addAttribute("newPraiseMeCount",noticeRepository.countPraiseByUserIdAndReaded(getUserId(), "unread"));
//		logger.info("collect size="+size+" userID="+getUserId());
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String regist() {
        return "register";
    }

    @RequestMapping(value = "/tool")
    public String tool(Model model) {
        String path = "javascript:(function()%7Bvar%20description;var%20desString=%22%22;var%20metas=document.getElementsByTagName('meta');for(var%20x=0,y=metas.length;x%3Cy;x++)%7Bif(metas%5Bx%5D.name.toLowerCase()==%22description%22)%7Bdescription=metas%5Bx%5D;%7D%7Dif(description)%7BdesString=%22&amp;description=%22+encodeURIComponent(description.content);%7Dvar%20win=window.open(%22"
                + SysConst.BASE_PATH
                + "collect?from=webtool&url=%22+encodeURIComponent(document.URL)+desString+%22&title=%22+encodeURIComponent(document.title)+%22&charset=%22+document.charset,'_blank');win.focus();%7D)();";
        model.addAttribute("path", path);
        return "tool";
    }

    @RequestMapping(value = "/feedback")
    public String feedback(Model model) {
        User user = userService.findById(getUserId());
        model.addAttribute("user", user);
        return "favorites/feedback";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletResponse response, Model model) {
        getSession().removeAttribute(SysConst.LOGIN_SESSION_KEY);
        getSession().removeAttribute(SysConst.LAST_REFERER);
        Cookie cookie = new Cookie(SysConst.LOGIN_SESSION_KEY, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
//		IndexCollectorView indexCollectorView = collectorService.getCollectors();
//		model.addAttribute("collector",indexCollectorView);
        return "index";
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public String forgotPassword() {
        return "user/forgotpassword";
    }

    @RequestMapping(value = "/newPassword", method = RequestMethod.GET)
    public String newPassword(String email) {
        return "user/newpassword";
    }

    @RequestMapping(value = "/uploadHeadPortrait")
    public String uploadHeadPortrait() {
        return "user/uploadheadportrait";
    }

    @RequestMapping(value = "/uploadBackground")
    public String uploadBackground() {
        return "user/uploadbackground";
    }

}
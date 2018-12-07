package com.sparrow.stockgarden.web;


import com.sparrow.base.bean.AjaxResult;
import com.sparrow.base.controller.BaseController;
import com.sparrow.config.CustomConfig;
import com.sparrow.constants.SysConst;
import com.sparrow.stockgarden.mysql.model.User;
import com.sparrow.stockgarden.mysql.service.UserService;
import com.sparrow.utils.DateUtil;
import com.sparrow.utils.FileUtil;
import com.sparrow.utils.Md5Util;
import com.sparrow.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: UserController</p>
 * <p>Description: </p>
 *
 * @author wjc
 * @date 2018/12/6
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomConfig customConfig;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AjaxResult login(User user, HttpServletResponse response) {
        User loginUser = userService.findByUsernameOrEmail(user.getUsername(), user.getUsername());
        if (loginUser == null) {
            return failure("该用户未注册");
        } else if (!loginUser.getPassword().equals(getPwd(user.getPassword()))) {
            return failure("用户名或者密码错误！");
        }
        Cookie cookie = new Cookie(SysConst.LOGIN_SESSION_KEY, cookieSign(loginUser.getId().toString()));
        cookie.setMaxAge(SysConst.COOKIE_TIMEOUT);
        cookie.setPath("/");
        response.addCookie(cookie);
        getSession().setAttribute(SysConst.LOGIN_SESSION_KEY, loginUser);
        String preUrl = "/";
        if (null != getSession().getAttribute(SysConst.LAST_REFERER)) {
            preUrl = String.valueOf(getSession().getAttribute(SysConst.LAST_REFERER));
            if (preUrl.indexOf("/collect?") < 0 && preUrl.indexOf("/lookAround/standard/") < 0
                    && preUrl.indexOf("/lookAround/simple/") < 0) {
                preUrl = "/";
            }
        }
        if (preUrl.indexOf("/lookAround/standard/") >= 0) {
            preUrl = "/lookAround/standard/ALL";
        }
        if (preUrl.indexOf("/lookAround/simple/") >= 0) {
            preUrl = "/lookAround/simple/ALL";
        }
        return success(preUrl);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public AjaxResult create(User user) {
        User registerUser = userService.findByEmail(user.getEmail());
        if (null != registerUser) {
            return failure("该邮箱已被注册");
        }
        User userNameUser = userService.findByUsername(user.getUsername());
        if (null != userNameUser) {
            return failure("该登录名称已存在");
        }
        user.setPassword(getPwd(user.getPassword()));
        user.setCreatedDate(DateUtil.currentDate());
        user.setModifiedDate(DateUtil.currentDate());
        user.setProfilePicture("img/favicon.png");
        user.setStatus(SysConst.UserStatus.NORMAL.getCode());
        userService.save(user);
        getSession().setAttribute(SysConst.LOGIN_SESSION_KEY, user);

        return success();
    }

	/*@RequestMapping(value = "/getFavorites", method = RequestMethod.POST)
    @LoggerManage(description="获取收藏夹")
	public List<Favorites> getFavorites() {
		List<Favorites> favorites = null;
		try {
			favorites = favoritesRepository.findByUserIdOrderByLastModifyTimeDesc(getUserId());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("getFavorites failed, ", e);
		}
		return favorites;
	}
	
	*//**
     * 获取属性设置
     * @return
     *//*
	@RequestMapping(value = "/getConfig", method = RequestMethod.POST)
	@LoggerManage(description="获取属性设置")
	public Config getConfig(){
		Config config = new Config();
		try {
			config = configRepository.findByUserId(getUserId());
		} catch (Exception e) {
			logger.error("异常：",e);
		}
		return config;
	}
	
	*//**
     * 属性修改
     * @param id
     * @param type
     * @return
     *//*
	@RequestMapping(value = "/updateConfig", method = RequestMethod.POST)
	@LoggerManage(description="属性修改")
	public Response updateConfig(Long id, String type,String defaultFavorites){
		if(null  != id && StringUtils.isNotBlank(type)){
			try {
				configService.updateConfig(id, type,defaultFavorites);
			} catch (Exception e) {
				logger.error("属性修改异常：",e);
			}
		}
		return result();
	}
	
	@RequestMapping(value="/getFollows")
	@LoggerManage(description="获取关注列表")
	public List<String> getFollows() {
		List<String> followList = followRepository.findByUserId(getUserId());
		return followList;
	}
	
	*//**
     * 忘记密码-发送重置邮件
     * @param email
     * @return
     *//*
	@RequestMapping(value = "/sendForgotPasswordEmail", method = RequestMethod.POST)
	@LoggerManage(description="发送忘记密码邮件")
	public Response sendForgotPasswordEmail(String email) {
		try {
			User registUser = userRepository.findByEmail(email);
			if (null == registUser) {
				return result(ExceptionMsg.EmailNotRegister);
			}	
			String secretKey = UUID.randomUUID().toString(); // 密钥
            Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);// 30分钟后过期
            long date = outDate.getTime() / 1000 * 1000;
            userRepository.setOutDateAndValidataCode(outDate+"", secretKey, email);
            String key =email + "$" + date + "$" + secretKey;
            String digitalSignature = MD5Util.encrypt(key);// 数字签名
//            String basePath = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":" + this.getRequest().getServerPort() + this.getRequest().getContextPath() + "/newPassword";
            String resetPassHref = forgotpasswordUrl + "?sid="
                    + digitalSignature +"&email="+email;
            String emailContent = MessageUtil.getMessage(mailContent, resetPassHref);
	        MimeMessage mimeMessage = mailSender.createMimeMessage();	        
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	        helper.setFrom(mailFrom);
	        helper.setTo(email);
	        helper.setSubject(mailSubject);
	        helper.setText(emailContent, true);
	        mailSender.send(mimeMessage);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("sendForgotPasswordEmail failed, ", e);
			return result(ExceptionMsg.FAILED);
		}
		return result();
	}
	
	*/

    /**
     * 忘记密码-设置新密码
     *
     * @param newpwd
     * @param email
     * @param sid
     * @return
     */
    @RequestMapping(value = "/setNewPassword", method = RequestMethod.POST)
    public AjaxResult setNewPassword(String newpwd, String email, String sid) {
        User user = userService.findByEmail(email);
        long outDateMillis = user.getOutDate().getTime();
        if (outDateMillis <= System.currentTimeMillis()) {
            //表示已经过期
            return failure("该链接已过期，请重新请求");
        }
        //数字签名
        String key = user.getEmail() + "$" + outDateMillis / 1000 * 1000 + "$" + user.getVerificationCode();
        String digitalSignature = Md5Util.encrypt(key);
        if (!digitalSignature.equals(sid)) {
            return failure("该链接已过期，请重新请求");
        }
        userService.setNewPassword(getPwd(newpwd), email);

        return success();
    }

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public AjaxResult updatePassword(String oldPassword, String newPassword) {
        User user = getUser();
        String password = user.getPassword();
        String newpwd = getPwd(newPassword);
        if (password.equals(getPwd(oldPassword))) {
            userService.setNewPassword(newpwd, user.getEmail());
            user.setPassword(newpwd);
            getSession().setAttribute(SysConst.LOGIN_SESSION_KEY, user);

            return success();
        } else {
            return failure("密码输入错误");
        }
    }

    /**
     * 修改个人简介
     * @param introduction
     * @return
     *//*
	@RequestMapping(value = "/updateIntroduction", method = RequestMethod.POST)
	@LoggerManage(description="修改个人简介")
	public ResponseData updateIntroduction(String introduction) {
		try {
			User user = getUser();
			userRepository.setIntroduction(introduction, user.getEmail());
			user.setIntroduction(introduction);
			getSession().setAttribute(SysConst.LOGIN_SESSION_KEY, user);
			return new ResponseData(ExceptionMsg.SUCCESS, introduction);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("updateIntroduction failed, ", e);
			return new ResponseData(ExceptionMsg.FAILED);
		}
	}
	
	*//**
     * 修改昵称
     * @param userName
     * @return
     *//*
	@RequestMapping(value = "/updateUserName", method = RequestMethod.POST)
	@LoggerManage(description="修改昵称")
	public ResponseData updateUserName(String userName) {
		try {
			User loginUser = getUser();
			if(userName.equals(loginUser.getUserName())){
				return new ResponseData(ExceptionMsg.UserNameSame);
			}
			User user = userRepository.findByUserName(userName);
			if(null != user && user.getUserName().equals(userName)){
				return new ResponseData(ExceptionMsg.UserNameUsed);
			}
			if(userName.length()>12){
				return new ResponseData(ExceptionMsg.UserNameLengthLimit);
			}
			userRepository.setUserName(userName, loginUser.getEmail());
			loginUser.setUserName(userName);
			getSession().setAttribute(SysConst.LOGIN_SESSION_KEY, loginUser);
			return new ResponseData(ExceptionMsg.SUCCESS, userName);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("updateUserName failed, ", e);
			return new ResponseData(ExceptionMsg.FAILED);
		}
	}
	
	*/

    /**
     * 上传头像
     *
     * @param dataUrl
     * @return
     */
    @RequestMapping(value = "/uploadHeadPortrait", method = RequestMethod.POST)
    public AjaxResult uploadHeadPortrait(String dataUrl) {
        logger.info("执行 上传头像 开始");

        String filePath = customConfig.getStaticUrl() + customConfig.getProfilePicturesUrl();
        String fileName = StringUtil.randomUUID() + ".png";
        String savePath = customConfig.getProfilePicturesUrl() + fileName;
        String image = dataUrl;
        String header = "data:image";
        String[] imageArray = image.split(",");
        if (imageArray[0].contains(header)) {
            image = imageArray[1];
            byte[] decodedBytes = StringUtil.decode(image);
            FileUtil.uploadFile(decodedBytes, filePath, fileName);
            User user = getUser();
            userService.setProfilePicture(savePath, user.getId());
            user.setProfilePicture(savePath);
            getSession().setAttribute(SysConst.LOGIN_SESSION_KEY, user);
        }
        logger.info("头像地址：" + savePath);
        logger.info("执行 上传头像 结束");

        return success(savePath);
    }

    /**
     * 上传背景
     * @param dataUrl
     * @return
     *//*
	@RequestMapping(value = "/uploadBackground", method = RequestMethod.POST)
	@LoggerManage(description="上传背景")
	public ResponseData uploadBackground(String dataUrl){
		try {
			String filePath=staticUrl+fileBackgroundpicturesUrl;
			String fileName=UUID.randomUUID().toString()+".png";
			String savePath = fileBackgroundpicturesUrl+fileName;
			String image = dataUrl;
			String header ="data:image";
			String[] imageArr=image.split(",");
			if(imageArr[0].contains(header)){
				image=imageArr[1];
				Base64.Decoder decoder = Base64.getDecoder();
				byte[] decodedBytes = decoder.decode(image);
				FileUtil.uploadFile(decodedBytes, filePath, fileName);
				User user = getUser();
				userRepository.setBackgroundPicture(savePath, user.getId());
				user.setBackgroundPicture(savePath);
				getSession().setAttribute(SysConst.LOGIN_SESSION_KEY, user);
			}
			logger.info("背景地址：" + savePath);
			return new ResponseData(ExceptionMsg.SUCCESS, savePath);
		} catch (Exception e) {
			logger.error("upload background picture failed, ", e);
			return new ResponseData(ExceptionMsg.FAILED);
		}
	}*/

}

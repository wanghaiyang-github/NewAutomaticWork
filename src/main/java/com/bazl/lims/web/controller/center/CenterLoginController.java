package com.bazl.lims.web.controller.center;

import com.bazl.lims.common.Constants;
import com.bazl.lims.manager.model.po.LabPermission;
import com.bazl.lims.manager.model.po.LabRole;
import com.bazl.lims.manager.model.po.LabUser;
import com.bazl.lims.manager.services.common.LabPermissionService;
import com.bazl.lims.manager.services.common.LabRoleService;
import com.bazl.lims.manager.services.common.LabUserService;
import com.bazl.lims.utils.EncryptUtils;
import com.bazl.lims.utils.InitializationData;
import com.bazl.lims.utils.SystemUtil;
import com.bazl.lims.web.controller.BaseController;
import com.bazl.lims.web.security.LimsSecurityUtils;
import com.bazl.lims.web.security.LimsUsernamePasswordToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2016/12/28.
 */
@Controller
@RequestMapping(value = "/center")
public class CenterLoginController extends BaseController {

    @Autowired
    LabPermissionService labPermissionService;
    @Autowired
    LabRoleService labRoleService;
    @Autowired
    LabUserService labUserService;
    @Autowired
    InitializationData initializationData;

    @RequestMapping(value = "/loginForm.html")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loginTitleName", SystemUtil.getSystemConfig().getProperty("loginTitleName"));
        modelAndView.setViewName("center/loginForm");
        return modelAndView;
    }

    @RequestMapping(value="/loginHome.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> loginHome(HttpServletRequest request) throws ParseException {
        Map<String, Object> result = new HashMap<>();

        try {
            result.put("success",true);
        }catch (Exception e) {
            result.put("success",false);
            logger.error("loginHome error",e);
        }

        return result;
    }

    @RequestMapping(value = "/login.html")
    public ModelAndView login(HttpServletRequest request, String loginname, String password, String rememberMe) {
        ModelAndView modelAndView = new ModelAndView();
        String message ="";

        if (loginname == null || loginname.isEmpty()) {
            message = "用户名不能为空！";
        } else {
            // Subject就是代表当前的用户
            Subject currentUser = SecurityUtils.getSubject();
            // 申请令牌
            LimsUsernamePasswordToken token = new LimsUsernamePasswordToken(loginname, EncryptUtils.encryptMD5(password), rememberMe != null);
            token.setLoginType(Constants.LOGIN_TYPE_CENTER);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException e) {
                logger.error(loginname + ":" + password + "登录失败！错误如下：" + e);
                message = "用户名不存在！";
            } catch (AuthenticationException e) {
                logger.error(loginname + ":" + password + "登录失败！错误如下：" + e);
                if (e.getCause() != null && e.getCause() instanceof TransactionException) {
                    message = "数据库连接失败！";
                }
                else {
                    message = "用户名或密码错误！";
                }
            } catch (Exception e) {
                logger.error(loginname + ":" + password + "登录失败！错误如下：" + e);
                message = "未知错误！";
            }

            // 验证是否通过
            if (currentUser.isAuthenticated()) {
                SecurityUtils.getSubject().getSession().setTimeout(14400000);         //设置4个小时
                modelAndView = initializationData.initData(Constants.MENU_TYPE);

                modelAndView.setViewName("center/home");
                return modelAndView;
            } else {
                message = "用户名或密码错误！";
            }
        }

        modelAndView.setViewName("forward:/center/loginForm.html");
        modelAndView.addObject(MESSAGE, message);
        return modelAndView;
    }

    // 注销
    @RequestMapping(value = "/logout.html")
    public ModelAndView logout(HttpServletRequest request, String rebackUrl) {
        ModelAndView modelAndView = new ModelAndView();
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.logout();
        } catch (AuthenticationException e) {
            logger.error("注销失败", e);
        }
        modelAndView.setViewName("redirect:/center/loginForm.html");
        return modelAndView;
    }

    @RequestMapping(value = "/modifyPassword.html")
    public ModelAndView modifyPassword(HttpServletRequest request) {
        ModelAndView modelAndView = initializationData.initData(Constants.MENU_TYPE);
        modelAndView.setViewName("center/modifyPassword");
        return modelAndView;
    }

}

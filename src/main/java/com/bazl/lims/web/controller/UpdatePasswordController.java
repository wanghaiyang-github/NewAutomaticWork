package com.bazl.lims.web.controller;

import com.bazl.lims.common.Constants;
import com.bazl.lims.manager.model.po.LabUser;
import com.bazl.lims.manager.services.common.LabUserService;
import com.bazl.lims.utils.EncryptUtils;
import com.bazl.lims.web.security.LimsUsernamePasswordToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/3/13.
 */
@Controller
public class UpdatePasswordController extends BaseController {

    @Autowired
    LabUserService labUserService;

    @RequestMapping("/center/updatePassword.html")
    @ResponseBody
    public String updateCenterPassword(HttpServletRequest request, HttpServletResponse response,
                                       Integer loginId, String loginType,
                                       String oldPassword, String newPassword) {
        Subject currentUser = SecurityUtils.getSubject();
        Object object = currentUser.getPrincipal();
        if(object instanceof LabUser){
            LabUser labUser = (LabUser) object;
            if(!labUser.getLoginPassword().equals(EncryptUtils.encryptMD5(oldPassword))){
                return "1";
            }

            try {
                labUser.setLoginPassword(EncryptUtils.encryptMD5(newPassword));
                labUserService.updatePassword(labUser);

                LimsUsernamePasswordToken token = new LimsUsernamePasswordToken(labUser.getLoginName(), labUser.getLoginPassword(), true);
                token.setLoginType(Constants.LOGIN_TYPE_CENTER);

                currentUser.login(token);
            }catch(Exception ex){
                logger.error("鉴定用户修改密码错误！", ex);
                return "2";
            }
        }

        return "0";
    }

}

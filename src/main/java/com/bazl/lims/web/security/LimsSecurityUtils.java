package com.bazl.lims.web.security;

import com.bazl.lims.manager.model.po.LabUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by Administrator on 2017/1/6.
 */
public class LimsSecurityUtils {

    public static String getLoginName() {
        Subject currentUser = SecurityUtils.getSubject();
        Object obj = currentUser.getPrincipal();
        if(obj instanceof LabUser) {
            LabUser employee = (LabUser) obj;
            return employee.getLoginName();
        }

        return null;
    }


    public static LabUser getLoginLabUser() {
        Subject currentUser = SecurityUtils.getSubject();

        try {
            LabUser employee = (LabUser) currentUser.getPrincipal();
            return employee;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

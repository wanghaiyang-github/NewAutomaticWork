package com.bazl.lims.web.controller.center;

import com.bazl.lims.common.Constants;
import com.bazl.lims.manager.model.po.*;
import com.bazl.lims.manager.services.common.*;
import com.bazl.lims.utils.InitializationData;
import com.bazl.lims.utils.MathUtils;
import com.bazl.lims.utils.SystemUtil;
import com.bazl.lims.web.controller.BaseController;
import com.bazl.lims.web.security.LimsSecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2017/1/3.
 */
@Controller
@RequestMapping("/center")
public class IndexController extends BaseController {

    @Autowired
    LabPermissionService labPermissionService;
    @Autowired
    LabRoleService labRoleService;
    @Autowired
    LabUserService labUserService;
    @Autowired
    DictService dictService;
    @Autowired
    InitializationData initializationData;


    @RequestMapping("/top.html")
    public ModelAndView top(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("loginTitleName", SystemUtil.getSystemConfig().getProperty("loginTitleName"));
        modelAndView.setViewName("center/top");
        return modelAndView;
    }


    @RequestMapping("/left.html")
    public ModelAndView left(HttpServletRequest request) {
        ModelAndView modelAndView = initializationData.initData(Constants.MENU_TYPE);

        modelAndView.setViewName("center/left");
        return modelAndView;
    }

}

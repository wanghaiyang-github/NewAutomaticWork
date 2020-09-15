package com.bazl.lims.utils;

import com.bazl.lims.common.Constants;
import com.bazl.lims.manager.model.po.*;
import com.bazl.lims.manager.model.vo.SampleInfoVo;
import com.bazl.lims.manager.services.common.*;
import com.bazl.lims.web.controller.BaseController;
import com.bazl.lims.web.security.LimsSecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author wanghaiyang
 * @date 2020/2/5.
 */
@Component
public class InitializationData extends BaseController {

    @Autowired
    DictService dictService;
    @Autowired
    LabPermissionService labPermissionService;
    @Autowired
    LabRoleService labRoleService;
    @Autowired
    ProgramRecordService programRecordService;
    @Autowired
    SampleInfoService sampleInfoService;

    /**
     * 初始化字典项
     * @return
     */
    public ModelAndView initData(String type) {
        ModelAndView modelAndView = new ModelAndView();

        //字典
        if (type.equals(Constants.DICR_TYPE)) {
            //默认洗脱体积
            List<DictItem> elutionDefaultList = dictService.selectByParentDictTypeCode(Constants.DICT_TPYE_ELUTION_DEFAULT);
            modelAndView.addObject("elutionDefaultList",elutionDefaultList);
            //检材性质
            List<DictItem> samplePropertyList = dictService.selectByParentDictTypeCode(Constants.DICT_TPYE_SAMPLE_PROPERTY);
            modelAndView.addObject("samplePropertyList",samplePropertyList);
            //设备信息
            List<ProgramRecord> programRecordList = programRecordService.selectAll();
            modelAndView.addObject("programRecordList", programRecordList);
            //设备类型
            List<DictItem> deviceTypeList = dictService.selectByParentDictTypeCode(Constants.DICT_TPYE_DEVICE_TYPE);
            modelAndView.addObject("deviceTypeList", deviceTypeList);
            //确证方法
            List<DictItem> confirmatoryMethodList = dictService.selectByParentDictTypeCode(Constants.DICT_TPYE_CONFIRMATORY_METHOD);
            modelAndView.addObject("confirmatoryMethodList", confirmatoryMethodList);
            //预实验方法
            List<DictItem> preExperimentalMethodList = dictService.selectByParentDictTypeCode(Constants.DICT_TPYE_PRE_EXPERIMENTAL_METHOD);
            modelAndView.addObject("preExperimentalMethodList", preExperimentalMethodList);
            //检材转移方法
            List<DictItem> sampleTransferMethodList = dictService.selectByParentDictTypeCode(Constants.DICT_TPYE_SAMPLE_TRANSFER_METHOD);
            modelAndView.addObject("sampleTransferMethodList", sampleTransferMethodList);
            //检材转移方法
            List<DictItem> programNameList = dictService.selectByParentDictTypeCode(Constants.DICT_TPYE_PROGRAM_NAME);
            modelAndView.addObject("programNameList", programNameList);
        }

        //菜单

        LabUser labUser = LimsSecurityUtils.getLoginLabUser();
        List<LabRole> userRoleList = labRoleService.selectByLabUserId(labUser.getId());
        List<LabPermission> labPermissionList = new ArrayList<>();
        Set<Integer> permissionIds = new HashSet<>();
        List<LabPermission> rolePermissions = null;
        for (LabRole role : userRoleList) {
            rolePermissions = labPermissionService.selectListByRoleId(role.getId());
            for (LabPermission lp : rolePermissions) {
                if (!permissionIds.contains(lp.getId())) {
                    permissionIds.add(lp.getId());
                    labPermissionList.add(lp);
                }
            }
        }
        Collections.sort(labPermissionList);

        List<LabPermission> rootPermissionList = new ArrayList<LabPermission>();
        List<LabPermission> childPermissionList = new ArrayList<LabPermission>();
        for (LabPermission lp : labPermissionList) {
            if (lp.getRootFlag().equals(Constants.FLAG_TRUE)) {
                rootPermissionList.add(lp);
            } else {
                childPermissionList.add(lp);
            }
        }

        modelAndView.addObject("rootPermissionList", rootPermissionList);
        modelAndView.addObject("childPermissionList", childPermissionList);
        modelAndView.addObject("loginTitleName", SystemUtil.getSystemConfig().getProperty("loginTitleName"));

        return modelAndView;
    }


    public void updateSampleInfo(List<SampleInfoVo> sampleInfoVoList, String stage){
        if (ListUtils.isNotNullAndEmptyList(sampleInfoVoList)) {
            for (int i = 0;i < sampleInfoVoList.size();i++) {
                SampleInfoVo sampleInfoVo = sampleInfoVoList.get(i);
                if (sampleInfoVo != null) {
                    if (StringUtils.isNotBlank(sampleInfoVo.getEntity().getId())) {
                        SampleInfo sampleInfo = sampleInfoService.selectByPrimaryKey(sampleInfoVo.getEntity().getId());

                        try {
                            if (Constants.STAGE_TQ.equals(stage)) {
                                sampleInfo.setExtractPlateLocation(sampleInfoVo.getEntity().getExtractPlateLocation());
                                sampleInfo.setExtractLocationSort(sampleInfoVo.getEntity().getExtractLocationSort());
                                sampleInfo.setExtractPlateSort(sampleInfoVo.getEntity().getExtractPlateSort());
                            }else if (Constants.STAGE_KZ.equals(stage)) {
                                sampleInfo.setPcrPlateLocation(sampleInfoVo.getEntity().getPcrPlateLocation());
                                sampleInfo.setPcrLocationSort(sampleInfoVo.getEntity().getPcrLocationSort());
                                sampleInfo.setPcrPlateSort(sampleInfoVo.getEntity().getPcrPlateSort());
                            }else if (Constants.STAGE_SY.equals(stage)) {
                                sampleInfo.setSyPlateLocation(sampleInfoVo.getEntity().getSyPlateLocation());
                                sampleInfo.setSyLocationSort(sampleInfoVo.getEntity().getSyLocationSort());
                                sampleInfo.setSyPlateSort(sampleInfoVo.getEntity().getSyPlateSort());
                            }
                            sampleInfoService.updateByPrimaryKey(sampleInfo);
                        }catch (Exception e) {
                            logger.error("updateSampleInfo error",e);
                            e.getStackTrace();
                        }
                    }
                }
            }
        }
    }
}

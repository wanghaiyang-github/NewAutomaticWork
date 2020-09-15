package com.bazl.lims.web.controller.center.sample;

import com.bazl.lims.common.Constants;
import com.bazl.lims.manager.PageInfo;
import com.bazl.lims.manager.model.po.ExtractPlate;
import com.bazl.lims.manager.model.po.ServerInfo;
import com.bazl.lims.manager.model.po.SyPlate;
import com.bazl.lims.manager.model.po.TestRecord;
import com.bazl.lims.manager.model.vo.SampleInfoVo;
import com.bazl.lims.manager.model.vo.SyPlateVo;
import com.bazl.lims.manager.model.vo.TestRecordVo;
import com.bazl.lims.manager.services.common.*;
import com.bazl.lims.utils.DateUtils;
import com.bazl.lims.utils.InitializationData;
import com.bazl.lims.utils.ListUtils;
import com.bazl.lims.web.controller.BaseController;
import com.bazl.lims.web.security.LimsSecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * @author wanghaiyang
 * @date 2020/2/5.
 */
@Controller
@RequestMapping("/center/sample")
public class TestRecordController extends BaseController {

    @Autowired
    TestRecordService testRecordService;
    @Autowired
    InitializationData initializationData;
    @Autowired
    SyPlateService syPlateService;
    @Autowired
    ServerInfoService serverInfoService;
    @Autowired
    ExtractPlateService extractPlateService;
    @Autowired
    SeqNoGenerateService seqNoGenerateService;
    @Autowired
    SampleInfoService sampleInfoService;

    /**
     * 电泳列表
     * @param request
     * @param query
     * @param pageInfo
     * @return
     * @throws ParseException
     */
    @RequestMapping("/testRecordList.html")
    public ModelAndView testRecordList(HttpServletRequest request, TestRecordVo query, PageInfo pageInfo) throws ParseException {
        ModelAndView modelAndView = initializationData.initData(Constants.DICR_TYPE);

        query.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

        List<TestRecordVo> testRecordVoList = testRecordService.selectVoList(query, pageInfo);
        if (ListUtils.isNotNullAndEmptyList(testRecordVoList)) {
            for (TestRecordVo trv : testRecordVoList) {
                SampleInfoVo sampleInfoVo = new SampleInfoVo();
                sampleInfoVo.getEntity().setSyPlateId(trv.getEntity().getSyPlateId());
                List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
                trv.setCount(sampleInfoVoList.size());
            }
        }
        int totalCnt = testRecordService.selectVOCount(query);

        SyPlateVo syPlateVo = new SyPlateVo();
        syPlateVo.getEntity().setIsCreate(Constants.IS_CREATE_1);
        List<SyPlateVo> syPlateVoList = syPlateService.selectVoListBySyPlateVo(syPlateVo);
        List<ServerInfo> serverInfoList = serverInfoService.selectAll();

        modelAndView.addObject("query", query);
        modelAndView.addObject("testRecordVoList", testRecordVoList);
        modelAndView.addObject("pageInfo", pageInfo.updatePageInfo(totalCnt));
        modelAndView.addObject("syPlateVoList", syPlateVoList);
        modelAndView.addObject("serverInfoList", serverInfoList);
        modelAndView.setViewName("center/testRecord/testRecordList");
        return modelAndView;
    }


    @RequestMapping("/getTestRecord.html")
    @ResponseBody
    public Map<String, Object> getTestRecord(HttpServletRequest request, String syPlateId) {

        List<SyPlateVo> syPlateVoList = syPlateService.selectSampleTableBySyPlateId(syPlateId);
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            TestRecordVo testRecordVo = new TestRecordVo();
            if (ListUtils.isNotNullAndEmptyList(syPlateVoList)) {
                SyPlateVo syPlateVo = syPlateVoList.get(0);
                if (syPlateVo != null) {
                    ExtractPlate extractPlate = extractPlateService.selectByPrimaryKey(syPlateVo.getExtractPlateId());
                    if (extractPlate != null) {
                        testRecordVo.setDeviceType(extractPlate.getDeviceType());
                        testRecordVo.setExtractPlateId(syPlateVo.getExtractPlateId());
                        testRecordVo.setExtractPlateName(syPlateVo.getExtractPlateName());
                    }
                }
            }

            result.put("testRecordVo", testRecordVo);
            result.put("success", true);
        }catch (Exception e) {
            result.put("success", false);
            e.getStackTrace();
        }

        return result;
    }

    /**
     * 查看实验信息
     * @param request
     * @param id
     * @return
     * @throws ParseException
     */
    @RequestMapping("/viewTestRecord.html")
    public ModelAndView viewTestRecord(HttpServletRequest request, String id) throws ParseException {
        ModelAndView modelAndView = initializationData.initData(Constants.MENU_TYPE);

        TestRecord testRecord = testRecordService.selectByPrimaryKey(id);

        modelAndView.addObject("testRecord",testRecord);
        modelAndView.setViewName("center/testRecord/viewTestRecord");
        return modelAndView;
    }

    /**
     * 保存或更新实验信息
     * @param request
     * @param testRecordList
     * @return
     */
    @RequestMapping(value="/saveTestRecord.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> saveTestRecord(HttpServletRequest request, @RequestBody List<TestRecord> testRecordList){
        Map<String,Object> result = new HashMap<>();

        try{
            if (ListUtils.isNotNullAndEmptyList(testRecordList)) {
                for (TestRecord testRecord : testRecordList) {
                    testRecord = resetTestRecord(testRecord);
                    String loginName = LimsSecurityUtils.getLoginName();
                    if(StringUtils.isBlank(testRecord.getId())){
                        testRecord.setCreateDatetime(new Date());
                        String testNo = seqNoGenerateService.getNextTestNoVal(DateUtils.getCurrentYear());
                        testRecord.setTestNo(testNo);
                        testRecord.setId(UUID.randomUUID().toString());
                        testRecordService.insert(testRecord);
                    }else{
                        TestRecord testRecordInfo = testRecordService.selectByPrimaryKey(testRecord.getId());
                        if (testRecordInfo != null) {
                            testRecordInfo.setBoardNoName(testRecord.getBoardNoName());
                            testRecordInfo.setDeviceType(testRecord.getDeviceType());
                            testRecordInfo.setExtractPlateId(testRecord.getExtractPlateId());
                            testRecordInfo.setProgramId(testRecord.getProgramId());
                            testRecordInfo.setProgramName(testRecord.getProgramName());
                            testRecordInfo.setServerId(testRecord.getServerId());
                            testRecordInfo.setCreatePerson(testRecord.getCreatePerson());
                            testRecordInfo.setCreateDatetime(testRecord.getCreateDatetime());
                            testRecordInfo.setOperationPerson(loginName);
                            testRecordInfo.setOperationDatetime(new Date());
                            testRecordService.updateByPrimaryKey(testRecordInfo);
                        }
                    }
                    //如果已经创建实验，更新电泳状态
                    if (StringUtils.isNotBlank(testRecord.getSyPlateId())) {
                        SyPlate syPlate = syPlateService.selectByPrimaryKey(testRecord.getSyPlateId());
                        syPlate.setIsCreate(Constants.IS_CREATE_1);
                        syPlateService.updateByPrimaryKey(syPlate);
                    }
                }
                result.put("success",true);
            }else {
                result.put("success","no");
            }
        }catch (Exception e){
            result.put("success",false);
            logger.error("saveTestRecord error",e);
        }

        return result;
    }

    /**
     * 删除实验信息
     * @param request
     * @param testRecordList
     * @return
     */
    @RequestMapping(value="/deleteTestRecord.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> deleteTestRecord(HttpServletRequest request,@RequestBody List<TestRecord> testRecordList){
        Map<String,Object> result = new HashMap<>();

        try{
            if (ListUtils.isNotNullAndEmptyList(testRecordList)) {
                for (TestRecord testRecord : testRecordList) {
                    testRecordService.deleteFlagById(testRecord.getId());
                }
                result.put("success",true);
            }else {
                result.put("success","no");
            }
        }catch (Exception e){
            result.put("success",false);
            logger.error("deleteTestRecord error",e);
        }

        return result;
    }

    public TestRecord resetTestRecord(TestRecord testRecord){
        if(StringUtils.isBlank(testRecord.getBoardNoName())){
            testRecord.setBoardNoName(null);
        }else {
            testRecord.setBoardNoName(testRecord.getBoardNoName().trim());
        }

        if(StringUtils.isBlank(testRecord.getDeviceType())){
            testRecord.setDeviceType(null);
        }else {
            testRecord.setDeviceType(testRecord.getDeviceType());
        }

        if(testRecord.getExtractPlateId() == null){
            testRecord.setExtractPlateId(null);
        }else {
            testRecord.setExtractPlateId(testRecord.getExtractPlateId());
        }

        if(testRecord.getProgramId() == null){
            testRecord.setProgramId(null);
        }else {
            testRecord.setProgramId(testRecord.getProgramId());
        }

        if(testRecord.getServerId() == null){
            testRecord.setServerId(null);
        }else {
            testRecord.setServerId(testRecord.getServerId());
        }

        if(StringUtils.isBlank(testRecord.getCreatePerson())){
            testRecord.setCreatePerson(LimsSecurityUtils.getLoginName());
        }else {
            testRecord.setCreatePerson(testRecord.getCreatePerson().trim());
        }

        return testRecord;
    }
}

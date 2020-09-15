package com.bazl.lims.web.controller.center.sample;

import com.alibaba.fastjson.JSONArray;
import com.bazl.lims.common.Constants;
import com.bazl.lims.manager.PageInfo;
import com.bazl.lims.manager.model.po.SampleInfo;
import com.bazl.lims.manager.model.po.SampleTable;
import com.bazl.lims.manager.model.vo.SampleInfoVo;
import com.bazl.lims.manager.services.common.SampleInfoService;
import com.bazl.lims.manager.services.common.SampleTableService;
import com.bazl.lims.utils.DownloadFileUtils;
import com.bazl.lims.utils.InitializationData;
import com.bazl.lims.utils.ListUtils;
import com.bazl.lims.utils.TestProcessUtils;
import com.bazl.lims.web.controller.BaseController;
import com.bazl.lims.web.security.LimsSecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.*;

/**
 * @author wanghaiyang
 * @date 2020/2/5.
 */
@Controller
@RequestMapping("/center/sample")
public class SampleInfoController extends BaseController {

    @Autowired
    SampleInfoService sampleInfoService;
    @Autowired
    InitializationData initializationData;
    @Autowired
    DownloadFileUtils downloadFileUtils;
    @Autowired
    SampleTableService sampleTableService;

    /**
     * 检材列表
     * @param request
     * @param query
     * @param pageInfo
     * @return
     * @throws ParseException
     */
    @RequestMapping("/sampleInfoList.html")
    public ModelAndView sampleInfoList(HttpServletRequest request, SampleInfoVo query, PageInfo pageInfo) throws ParseException {
        ModelAndView modelAndView = initializationData.initData(Constants.DICR_TYPE);

        query = resetSampleInfoVo(query);

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoList(query, pageInfo);
        int totalCnt = sampleInfoService.selectVOCount(query);

        modelAndView.addObject("query", query);
        modelAndView.addObject("sampleInfoVoList", sampleInfoVoList);
        modelAndView.addObject("pageInfo", pageInfo.updatePageInfo(totalCnt));
        modelAndView.setViewName("center/sampleInfo/sampleInfoList");
        return modelAndView;
    }

    /**
     * 添加检材页面
     * @param request
     * @param sampleInfoVo
     * @return
     */
    @RequestMapping("/addSample.html")
    public ModelAndView addSample(HttpServletRequest request, SampleInfoVo sampleInfoVo) {
        ModelAndView modelAndView = initializationData.initData(Constants.DICR_TYPE);

        sampleInfoVo.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);

        SampleTable sampleTable = sampleTableService.selectByPrimaryKey(sampleInfoVo.getEntity().getSampleTableId());

        List<Map<String, Object>> tempList24 = new ArrayList<>();
        List<Map<String, Object>> tempList96 = new ArrayList<>();
        if (StringUtils.isNotBlank(sampleInfoVo.getHoleNum())) {
            if (sampleInfoVo.getHoleNum().equals(Constants.HOLE_NUM_24)) {
                tempList24 = TestProcessUtils.boardSort(sampleInfoVoList, Integer.parseInt(Constants.HOLE_NUM_24), sampleInfoVo, Constants.STAGE_QT);
                modelAndView.setViewName("center/sampleInfo/addSampleInfo24");
            }else {
                tempList96 = TestProcessUtils.boardSort(sampleInfoVoList, Integer.parseInt(Constants.HOLE_NUM_96), sampleInfoVo, Constants.STAGE_QT);
                modelAndView.setViewName("center/sampleInfo/addSampleInfo96");
            }
        }

        modelAndView.addObject("sampleTable", sampleTable);
        modelAndView.addObject("sampleInfoVo", sampleInfoVo);
        modelAndView.addObject("tempList24", tempList24);
        modelAndView.addObject("tempList96", tempList96);
        modelAndView.addObject("sampleInfoVoList", sampleInfoVoList);
        return modelAndView;
    }

    /**
     * 查看样本板孔位信息
     * @param request
     * @param sampleInfoVo
     * @return
     */
    @RequestMapping("/viewSampleBoard.html")
    public ModelAndView viewSampleBoard(HttpServletRequest request, SampleInfoVo sampleInfoVo, String stage) {
        ModelAndView modelAndView = initializationData.initData(Constants.DICR_TYPE);

        sampleInfoVo.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
        sampleInfoVo.getEntity().setBoardNo(sampleInfoVo.getBoardNoName());

        SampleTable sampleTable = new SampleTable();

        List<Map<String, Object>> tempList24 = new ArrayList<>();
        List<Map<String, Object>> tempList96 = new ArrayList<>();
        if (StringUtils.isNotBlank(sampleInfoVo.getHoleNum())) {
            if (sampleInfoVo.getHoleNum().equals(Constants.HOLE_NUM_24)) {
                tempList24 = TestProcessUtils.boardSort(sampleInfoVoList, Integer.parseInt(Constants.HOLE_NUM_24), sampleInfoVo, stage);
                modelAndView.setViewName("center/sampleInfo/addSampleInfo24");
            }else {
                tempList96 = TestProcessUtils.boardSort(sampleInfoVoList, Integer.parseInt(Constants.HOLE_NUM_96), sampleInfoVo, stage);
                modelAndView.setViewName("center/sampleInfo/addSampleInfo96");
            }
        }

        modelAndView.addObject("sampleTable", sampleTable);
        modelAndView.addObject("sampleInfoVo", sampleInfoVo);
        modelAndView.addObject("tempList24", tempList24);
        modelAndView.addObject("tempList96", tempList96);
        modelAndView.addObject("sampleInfoVoList", sampleInfoVoList);
        return modelAndView;
    }

    /**
     * 得到样本列表信息
     * @param request
     * @param sampleInfoVo
     * @param stage
     * @return
     */
    @RequestMapping(value = "/getSampleInfoList.html", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> getSampleInfoList(HttpServletRequest request, @RequestBody SampleInfoVo sampleInfoVo, String stage) {
        Map<String,Object> result = new HashMap<>();

        try {
            sampleInfoVo.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

            List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
            if (ListUtils.isNotNullAndEmptyList(sampleInfoVoList) && StringUtils.isNotBlank(stage)) {
                sampleInfoVoList = TestProcessUtils.holeSort(sampleInfoVoList, stage);
            }
            SampleTable sampleTable = sampleTableService.selectByPrimaryKey(sampleInfoVo.getEntity().getSampleTableId());
            if (sampleTable == null) {
                sampleTable = new SampleTable();
                sampleTable.setIsCreate(Constants.IS_CREATE_1);
            }
            result.put("sampleInfoVoList",sampleInfoVoList);
            result.put("sampleTable",sampleTable);
            result.put("success",true);
        }catch (Exception e) {
            result.put("success",false);
            logger.error("getSampleInfoList error",e);
        }

        return result;
    }

    /**
     * 得到整板信息
     * @param request
     * @param sampleInfoVo
     * @param stage
     * @return
     */
    @RequestMapping(value = "/getAllBoardList.html", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> getAllBoardList(HttpServletRequest request, @RequestBody SampleInfoVo sampleInfoVo, String stage) {
        Map<String,Object> result = new HashMap<>();

        try {
            sampleInfoVo.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());
            List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
            List<Map<String, Object>> allTempList = TestProcessUtils.boardSortAll(sampleInfoVoList, Integer.parseInt(Constants.HOLE_NUM_96), sampleInfoVo, stage);
            result.put("allTempList",allTempList);
            result.put("success",true);
        }catch (Exception e) {
            result.put("success",false);
            logger.error("getSampleInfoList error",e);
        }

        return result;
    }

    /**
     * 导出24孔样本表
     * @param request
     * @param response
     * @param sampleInfoVo
     */
    @RequestMapping("/exportSampleTable.html")
    public void exportSampleTable(HttpServletRequest request, HttpServletResponse response, SampleInfoVo sampleInfoVo) {

        sampleInfoVo.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
        SampleTable sampleTable = sampleTableService.selectByPrimaryKey(sampleInfoVo.getEntity().getSampleTableId());

        downloadFileUtils.exportSampleTable(request, response, sampleInfoVoList, sampleTable);

    }

    /**
     * 导出检材信息
     * @param request
     * @param response
     * @param sampleInfoList
     * @return
     */
    @RequestMapping(value="/exportSampleInfoRecord.html")
    public void exportSampleInfoRecord(HttpServletRequest request, HttpServletResponse response, @RequestParam("sampleInfoList") String sampleInfoList){

        try{
            List<SampleInfo> sampleInfos = JSONArray.parseArray(sampleInfoList, SampleInfo.class);
            if (ListUtils.isNotNullAndEmptyList(sampleInfos)) {
                List<SampleInfoVo> sampleInfoVoList = new ArrayList<>();
                for (SampleInfo sampleInfo : sampleInfos) {
                    SampleInfoVo sampleInfoVo = new SampleInfoVo();
                    sampleInfoVo.getEntity().setId(sampleInfo.getId());
                    sampleInfoVo.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

                    List<SampleInfoVo> sampleInfoVos = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
                    sampleInfoVoList.addAll(sampleInfoVos);
                }
                downloadFileUtils.exportSampleInfoRecord(request, response, sampleInfoVoList);
            }
        }catch (Exception e){
            logger.error("exportSampleInfoRecord error",e);
        }

    }


    /**
     * 保存或更新样本信息
     * @param request
     * @param sampleInfoList
     * @return
     */
    @RequestMapping(value="/saveSampleInfo.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> saveSampleInfo(HttpServletRequest request, @RequestBody List<SampleInfo> sampleInfoList, String stage){
        Map<String,Object> result = new HashMap<>();

        try{
            if (ListUtils.isNotNullAndEmptyList(sampleInfoList)) {
                String sampleNo = null;
                String id = null;
                String elutionName = null;
                SampleInfo si = new SampleInfo();
                for (SampleInfo sampleInfo : sampleInfoList) {
                    sampleInfo = resetSampleInfo(sampleInfo);

                    String loginName = LimsSecurityUtils.getLoginName();
                    if(StringUtils.isBlank(sampleInfo.getId())){
                        sampleInfo.setId(UUID.randomUUID().toString());
                        sampleInfo = assignment(sampleInfo, stage);
                        sampleInfoService.insert(sampleInfo);
                    }else{
                        SampleInfo sample = sampleInfoService.selectByPrimaryKey(sampleInfo.getId());
                        if (sample != null) {
                            sample.setBoardNo(sampleInfo.getBoardNo());
                            sample.setSampleNo(sampleInfo.getSampleNo());
                            sample.setElution(sampleInfo.getElution());
                            sample.setSamplePlateSort(sampleInfo.getSamplePlateSort());
                            sample.setPreExperimentalMethod(sampleInfo.getPreExperimentalMethod());
                            sample.setConfirmatoryMethod(sampleInfo.getConfirmatoryMethod());
                            sample.setSampleTransferMethod(sampleInfo.getSampleTransferMethod());
                            sample.setSampleProperty(sampleInfo.getSampleProperty());
                            sample.setOperationPerson(loginName);
                            sample.setOperationDatetime(new Date());
                            sample = assignment(sampleInfo, stage);
                            sampleInfoService.updateByPrimaryKey(sample);
                        }
                    }

                    si = sampleInfo;
                }
                result.put("success",true);
                result.put("sampleInfo", si);
            }else {
                result.put("success","no");
            }
        }catch (Exception e){
            result.put("success",false);
            logger.error("saveSampleInfo error",e);
        }

        return result;
    }

    /**
     * 删除样本信息
     * @param request
     * @param sampleInfoList
     * @return
     */
    @RequestMapping(value="/deleteSampleInfo.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> deleteSampleInfo(HttpServletRequest request,@RequestBody List<SampleInfo> sampleInfoList){
        Map<String,Object> result = new HashMap<>();

        try{
            if (ListUtils.isNotNullAndEmptyList(sampleInfoList)) {
                for (SampleInfo sampleInfo : sampleInfoList) {
                    sampleInfoService.deleteFlagById(sampleInfo.getId());
                }
                result.put("success",true);
            }else {
                result.put("success","no");
            }
        }catch (Exception e){
            result.put("success",false);
            logger.error("deleteSampleInfo error",e);
        }

        return result;
    }

    public SampleInfoVo resetSampleInfoVo(SampleInfoVo query) {

        if(StringUtils.isBlank(query.getEntity().getBoardNo())){
            query.getEntity().setBoardNo(null);
        }else {
            query.getEntity().setBoardNo(query.getEntity().getBoardNo().trim());
        }

        if(StringUtils.isBlank(query.getEntity().getSampleNo())){
            query.getEntity().setSampleNo(null);
        }else {
            query.getEntity().setSampleNo(query.getEntity().getSampleNo().trim());
        }

        return query;
    }

    public SampleInfo resetSampleInfo(SampleInfo sampleInfo){
        if(StringUtils.isBlank(sampleInfo.getBoardNo())){
            sampleInfo.setBoardNo(null);
        }else {
            sampleInfo.setBoardNo(sampleInfo.getBoardNo().trim());
        }

        if(StringUtils.isBlank(sampleInfo.getSampleNo())){
            sampleInfo.setSampleNo(null);
        }else {
            sampleInfo.setSampleNo(sampleInfo.getSampleNo().trim());
        }

        if(StringUtils.isBlank(sampleInfo.getElution())){
            sampleInfo.setElution(null);
        }else {
            sampleInfo.setElution(sampleInfo.getElution().trim());
        }

        if(StringUtils.isBlank(sampleInfo.getSamplePlateSort())){
            sampleInfo.setSamplePlateSort(null);
        }else {
            sampleInfo.setSamplePlateSort(sampleInfo.getSamplePlateSort().trim());
        }

        if(StringUtils.isBlank(sampleInfo.getPreExperimentalMethod())){
            sampleInfo.setPreExperimentalMethod(null);
        }else {
            sampleInfo.setPreExperimentalMethod(sampleInfo.getPreExperimentalMethod().trim());
        }

        if(StringUtils.isBlank(sampleInfo.getConfirmatoryMethod())){
            sampleInfo.setConfirmatoryMethod(null);
        }else {
            sampleInfo.setConfirmatoryMethod(sampleInfo.getConfirmatoryMethod().trim());
        }

        if(StringUtils.isBlank(sampleInfo.getSampleTransferMethod())){
            sampleInfo.setSampleTransferMethod(null);
        }else {
            sampleInfo.setSampleTransferMethod(sampleInfo.getSampleTransferMethod().trim());
        }

        if(StringUtils.isBlank(sampleInfo.getSampleProperty())){
            sampleInfo.setSampleProperty(null);
        }else {
            sampleInfo.setSampleProperty(sampleInfo.getSampleProperty().trim());
        }

        if(sampleInfo.getSamplePlateLocation() == null){
            sampleInfo.setSamplePlateLocation(null);
        }else {
            sampleInfo.setSamplePlateLocation(sampleInfo.getSamplePlateLocation());
        }

        if(StringUtils.isBlank(sampleInfo.getCreatePerson())){
            sampleInfo.setCreatePerson(LimsSecurityUtils.getLoginName());
        }else {
            sampleInfo.setCreatePerson(sampleInfo.getCreatePerson().trim());
        }

        if (sampleInfo.getCreateDatetime() == null) {
            sampleInfo.setCreateDatetime(new Date());
        }else {
            sampleInfo.setCreateDatetime(sampleInfo.getCreateDatetime());
        }

        return sampleInfo;
    }

    public SampleInfo assignment(SampleInfo sampleInfo, String stage) {
        if (Constants.STAGE_TQ.equals(stage)) {
            sampleInfo.setExtractPlateId(sampleInfo.getExtractPlateId());
            sampleInfo.setExtractLocationSort(Constants.SYTABLE_POSTION_ARR_VER_NUM[sampleInfo.getExtractLocationSort()-1]);
            sampleInfo.setExtractPlateLocation(Constants.SYTABLE_POSTION_ARR_VER[sampleInfo.getExtractLocationSort()-1]);
            /*sampleInfo.setSamplePlateLocation(null);
            sampleInfo.setSamplePlateSort(null);*/
        }else if(Constants.STAGE_KZ.equals(stage)) {
            sampleInfo.setPcrPlateId(sampleInfo.getPcrPlateId());
            sampleInfo.setPcrLocationSort(Constants.SYTABLE_POSTION_ARR_VER_NUM[sampleInfo.getPcrLocationSort()-1]);
            sampleInfo.setPcrPlateLocation(Constants.SYTABLE_POSTION_ARR_VER[sampleInfo.getPcrLocationSort()-1]);
            /*sampleInfo.setSamplePlateLocation(null);
            sampleInfo.setSamplePlateSort(null);*/
        }else if(Constants.STAGE_SY.equals(stage)) {
            sampleInfo.setSyPlateId(sampleInfo.getSyPlateId());
            sampleInfo.setSyLocationSort(Constants.SYTABLE_POSTION_ARR_VER_NUM[sampleInfo.getSyLocationSort()-1]);
            sampleInfo.setSyPlateLocation(Constants.SYTABLE_POSTION_ARR_VER[sampleInfo.getSyLocationSort()-1]);
            /*sampleInfo.setSamplePlateLocation(null);
            sampleInfo.setSamplePlateSort(null);*/
        }else {
            sampleInfo.setSampleTableId(sampleInfo.getSampleTableId());
            sampleInfo.setSampleLocationSort(Constants.SYTABLE_POSTION_ARR_VER_NUM[sampleInfo.getSampleLocationSort()-1]);
            sampleInfo.setSamplePlateLocation(Constants.SYTABLE_POSTION_ARR_VER[sampleInfo.getSampleLocationSort()-1]);
        }
        return sampleInfo;
    }
}

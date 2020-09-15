package com.bazl.lims.web.controller.center.sample;

import com.bazl.lims.common.Constants;
import com.bazl.lims.manager.PageInfo;
import com.bazl.lims.manager.model.po.ExtractPlate;
import com.bazl.lims.manager.model.po.LabUser;
import com.bazl.lims.manager.model.po.SampleInfo;
import com.bazl.lims.manager.model.po.SampleTable;
import com.bazl.lims.manager.model.vo.ExtractPlateVo;
import com.bazl.lims.manager.model.vo.SampleInfoVo;
import com.bazl.lims.manager.services.common.ExtractPlateService;
import com.bazl.lims.manager.services.common.SampleInfoService;
import com.bazl.lims.manager.services.common.SampleTableService;
import com.bazl.lims.manager.services.common.SeqNoGenerateService;
import com.bazl.lims.utils.*;
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
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.*;

/**
 * @author wanghaiyang
 * @date 2020/2/5.
 */
@Controller
@RequestMapping("/center/sample")
public class ExtractPlateController extends BaseController {

    @Autowired
    ExtractPlateService extractPlateService;
    @Autowired
    InitializationData initializationData;
    @Autowired
    SampleTableService sampleTableService;
    @Autowired
    SampleInfoService sampleInfoService;
    @Autowired
    DownloadFileUtils downloadFileUtils;
    @Autowired
    SeqNoGenerateService seqNoGenerateService;

    /**
     * 提取列表
     * @param request
     * @param query
     * @param pageInfo
     * @return
     * @throws ParseException
     */
    @RequestMapping(value="/extractPlateList.html")
    public ModelAndView extractPlateList(HttpServletRequest request, ExtractPlateVo query, PageInfo pageInfo) throws ParseException {
        ModelAndView modelAndView = initializationData.initData(Constants.DICR_TYPE);

        query.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

        List<ExtractPlateVo> extractPlateVoList = extractPlateService.selectVoList(query, pageInfo);
        int totalCnt = extractPlateService.selectVOCount(query);

        modelAndView.addObject("extractPlateVoList", extractPlateVoList);
        modelAndView.addObject("pageInfo", pageInfo.updatePageInfo(totalCnt));
        modelAndView.setViewName("center/extractPlate/extractPlateList");
        return modelAndView;
    }

    //获取提取列表数据
    @RequestMapping(value = "/getExtractPlateList.html", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> getExtractPlateList(HttpServletRequest request, @RequestBody ExtractPlateVo query, PageInfo pageInfo) {
        Map<String,Object> result = new HashMap<>();

        try {

            query.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

            List<ExtractPlateVo> extractPlateVoList = extractPlateService.selectVoList(query, pageInfo);
            int totalCnt = extractPlateService.selectVOCount(query);

            result.put("sampleList", extractPlateVoList);
            result.put("pageInfo", pageInfo.updatePageInfo(totalCnt));
            result.put("success",true);
        }catch (Exception e) {
            result.put("success",false);
            logger.error("getExtractPlateList error",e);
        }

        return result;
    }

    /**
     * 创建或拼板创建提取列表
     * @param request
     * @param extractPlateVoList
     * @param isSpell
     * @return
     * @throws ParseException
     */
    @RequestMapping(value="/spellOrCreateExtractList.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> spellOrCreateExtractList(HttpServletRequest request, @RequestBody List<ExtractPlateVo> extractPlateVoList, String isSpell) throws ParseException {
        Map<String,Object> result = new HashMap<>();
        boolean flag = false;
        try{
            if (ListUtils.isNotNullAndEmptyList(extractPlateVoList)) {
                //不拼板
                if (isSpell.equals(Constants.IS_SPELL_NO)) {
                    for (ExtractPlateVo extractPlateVo : extractPlateVoList) {
                        SampleTable sampleTable = sampleTableService.selectByPrimaryKey(extractPlateVo.getSampleTableId());

                        if (sampleTable == null) {
                            continue;
                        }
                        //更新提取样本孔位
                        SampleInfoVo sampleInfoVo = new SampleInfoVo();
                        sampleInfoVo.getEntity().setSampleTableId(sampleTable.getId());
                        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
                        sampleInfoVoList = TestProcessUtils.getLocation(sampleInfoVoList, Constants.STAGE_TQ);
                        initializationData.updateSampleInfo(sampleInfoVoList, Constants.STAGE_TQ);

                        ExtractPlate extractPlate = initExtractPlate();
                        ExtractPlateVo plateVo = extractPlateVoList.get(0);
                        if (plateVo != null) {
                            extractPlate.setDeviceType(plateVo.getEntity().getDeviceType());
                            extractPlate.setKitName(plateVo.getEntity().getKitName());
                        }
                        extractPlate.setId(UUID.randomUUID().toString());
                        extractPlate.setHoleNum(sampleTable.getHoleNum());
                        extractPlateService.insert(extractPlate);
                        //更新样本板信息
                        sampleTable.setExtractPlateId(extractPlate.getId());
                        sampleTable.setExtractPlateName(extractPlate.getBoardNo());
                        sampleTable.setIsCreate(Constants.IS_CREATE_1);
                        flag = updateSampleTable(sampleTable);
                    }
                }else if (isSpell.equals(Constants.IS_SPELL_YES)) {
                    //拼板
                    //拼板首先创建提取记录信息
                    int holeNum = 0;
                    List<SampleInfoVo> allSampleInfoVoList = new ArrayList<>();
                    //列表展示时是倒序排列，在拼板时进行倒序循环
                    int plateSort = 1;
                    for (int i = extractPlateVoList.size() -1; i >= 0; i--) {
                        ExtractPlateVo extractPlateVo = extractPlateVoList.get(i);
                        if (extractPlateVo != null) {
                            SampleTable sampleTable = sampleTableService.selectByPrimaryKey(extractPlateVo.getSampleTableId());
                            if (sampleTable == null) {
                                continue;
                            }
                            if (StringUtils.isNotBlank(sampleTable.getHoleNum())) {
                                holeNum += Integer.parseInt(sampleTable.getHoleNum());
                            }

                            SampleInfoVo sampleInfoVo = new SampleInfoVo();
                            sampleInfoVo.getEntity().setSampleTableId(sampleTable.getId());
                            List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
                            if (ListUtils.isNotNullAndEmptyList(sampleInfoVoList)) {
                                //按样本板孔位序号排序
                                sampleInfoVoList = TestProcessUtils.holeSort(sampleInfoVoList, Constants.STAGE_QT);
                                //去除重复样本表信息
                                List<SampleInfoVo> removeDuplicateList = sampleInfoService.selectListVo(sampleInfoVo);
                                for (SampleInfoVo siVo : removeDuplicateList) {
                                    allSampleInfoVoList.addAll(TestProcessUtils.tranferInfo(sampleInfoVoList, plateSort++, Constants.STAGE_TQ, siVo));
                                }
                            }
                        }
                    }

                    //更新检材提取孔位
//                    allSampleInfoVoList = TestProcessUtils.boardAgain(allSampleInfoVoList, Constants.STAGE_TQ);
                    initializationData.updateSampleInfo(allSampleInfoVoList, Constants.STAGE_TQ);

                    ExtractPlate extractPlate = initExtractPlate();
                    ExtractPlateVo plateVo = extractPlateVoList.get(0);
                    if (plateVo != null) {
                        extractPlate.setDeviceType(plateVo.getEntity().getDeviceType());
                        extractPlate.setKitName(plateVo.getEntity().getKitName());
                    }
                    extractPlate.setId(UUID.randomUUID().toString());
                    extractPlate.setHoleNum(String.valueOf(holeNum));
                    extractPlateService.insert(extractPlate);
                    int count = 1;
                    //列表展示时是倒序排列，在拼板时进行倒序循环
                    for (int i = extractPlateVoList.size() -1; i >= 0; i--) {
                        ExtractPlateVo extractPlateVo = extractPlateVoList.get(i);
                        if (extractPlateVo != null) {
                            SampleTable sampleTable = sampleTableService.selectByPrimaryKey(extractPlateVo.getSampleTableId());
                            //更新样本板信息
                            sampleTable.setExtractPlateId(extractPlate.getId());
                            sampleTable.setExtractPlateName(extractPlate.getBoardNo() + "["+(count++)+"]");
                            sampleTable.setIsCreate(Constants.IS_CREATE_1);
                            flag = updateSampleTable(sampleTable);
                        }
                    }
                }
            }
        }catch (Exception e){
            logger.error("spellOrCreateExtractList error",e);
        }

        if (flag) {
            result.put("success",true);
        }else {
            result.put("success",false);
        }
        return result;
    }

    /**
     * 更新样本板信息
     * @param sampleTable
     */
    public boolean updateSampleTable(SampleTable sampleTable) {

        try {
            sampleTableService.updateByPrimaryKey(sampleTable);
            //更新样本信息
            SampleInfo sampleInfo = new SampleInfo();
            sampleInfo.setSampleTableId(sampleTable.getId());
            sampleInfo.setExtractPlateId(sampleTable.getExtractPlateId());
            sampleInfoService.updateBySampleTableId(sampleInfo);
            return true;
        }catch (Exception e) {
            logger.error("updateSampleTable error" + "更新样本板信息错误",e);
            return false;
        }
    }

    /**
     * 删除提取信息
     * @param request
     * @param extractPlateList
     * @return
     */
    @RequestMapping(value="/deleteExtractPlate.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> deleteExtractPlate(HttpServletRequest request,@RequestBody List<ExtractPlate> extractPlateList){
        Map<String,Object> result = new HashMap<>();

        try{
            if (ListUtils.isNotNullAndEmptyList(extractPlateList)) {
                for (ExtractPlate extractPlate : extractPlateList) {
                    extractPlateService.deleteFlagById(extractPlate.getId());
                }
                result.put("success",true);
            }else {
                result.put("success","no");
            }
        }catch (Exception e){
            result.put("success",false);
            logger.error("deleteExtractPlate error",e);
        }

        return result;
    }

    /**
     * 导出csv文件
     * @param request
     * @param response
     * @param sampleInfoVo
     */
    @RequestMapping("/exportCSVFile.html")
    public void exportCSVFile(HttpServletRequest request, HttpServletResponse response, SampleInfoVo sampleInfoVo) {

        sampleInfoVo.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
        sampleInfoVoList = TestProcessUtils.boardSortAgain(sampleInfoVoList, Constants.STAGE_TQ);
        ExtractPlate extractPlate = extractPlateService.selectByPrimaryKey(sampleInfoVo.getEntity().getExtractPlateId());

        downloadFileUtils.exportCSVFile(request, response, sampleInfoVoList, extractPlate);

    }

    /**
     * 导出上样文件
     * @param request
     * @param response
     * @param sampleInfoVo
     */
    @RequestMapping("/exportSampleFile.html")
    public void exportSampleFile(HttpServletRequest request, HttpServletResponse response, SampleInfoVo sampleInfoVo) {

        sampleInfoVo.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
        ExtractPlate extractPlate = extractPlateService.selectByPrimaryKey(sampleInfoVo.getEntity().getExtractPlateId());

        downloadFileUtils.exportSampleFile(request, response, sampleInfoVoList, extractPlate);

    }

    /**
     * 导出提取样本表
     * @param request
     * @param response
     * @param sampleInfoVo
     */
    @RequestMapping("/exportExtractSampleTable.html")
    public void exportExtractSampleTable(HttpServletRequest request, HttpServletResponse response, SampleInfoVo sampleInfoVo) {

        sampleInfoVo.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
        sampleInfoVoList = TestProcessUtils.boardSortAgain(sampleInfoVoList, Constants.STAGE_TQ);
        ExtractPlate extractPlate = extractPlateService.selectByPrimaryKey(sampleInfoVo.getEntity().getExtractPlateId());

        downloadFileUtils.exportExtractSampleTable(request, response, sampleInfoVoList, extractPlate);

    }

    /**
     * 导出提取样本记录表
     * @param request
     * @param response
     * @param sampleInfoVo
     */
    @RequestMapping("/exportExtractSampleRecord.html")
    public void exportExtractSampleRecord(HttpServletRequest request, HttpServletResponse response, SampleInfoVo sampleInfoVo) {

        sampleInfoVo.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
        sampleInfoVoList = TestProcessUtils.holeSort(sampleInfoVoList, Constants.STAGE_TQ);
        ExtractPlate extractPlate = extractPlateService.selectByPrimaryKey(sampleInfoVo.getEntity().getExtractPlateId());

        downloadFileUtils.exportExtractSampleRecord(request, response, sampleInfoVoList, extractPlate);

    }

    public ExtractPlate initExtractPlate() {

        String loginName = LimsSecurityUtils.getLoginName();
        ExtractPlate extractPlate = new ExtractPlate();
        String boardNo = seqNoGenerateService.getNextBoardNoVal(DateUtils.dateToString(new Date(), DateUtils.DATE) + "-" + loginName);
        extractPlate.setBoardNo(boardNo);
        extractPlate.setCreatePerson(loginName);
        extractPlate.setCreateDatetime(new Date());
        extractPlate.setExtractMethod("磁珠法");
        extractPlate.setExtractDatetime(new Date());

        return extractPlate;
    }
}

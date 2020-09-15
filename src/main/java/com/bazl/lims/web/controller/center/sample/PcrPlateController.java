package com.bazl.lims.web.controller.center.sample;

import com.bazl.lims.common.Constants;
import com.bazl.lims.manager.PageInfo;
import com.bazl.lims.manager.model.po.ExtractPlate;
import com.bazl.lims.manager.model.po.LabUser;
import com.bazl.lims.manager.model.po.PcrPlate;
import com.bazl.lims.manager.model.po.SampleInfo;
import com.bazl.lims.manager.model.vo.ExtractPlateVo;
import com.bazl.lims.manager.model.vo.PcrPlateVo;
import com.bazl.lims.manager.model.vo.SampleInfoVo;
import com.bazl.lims.manager.services.common.ExtractPlateService;
import com.bazl.lims.manager.services.common.PcrPlateService;
import com.bazl.lims.manager.services.common.SampleInfoService;
import com.bazl.lims.manager.services.common.SeqNoGenerateService;
import com.bazl.lims.utils.*;
import com.bazl.lims.web.controller.BaseController;
import com.bazl.lims.web.datamodel.SampleInfoModel;
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
public class PcrPlateController extends BaseController {

    @Autowired
    PcrPlateService pcrPlateService;
    @Autowired
    InitializationData initializationData;
    @Autowired
    ExtractPlateService extractPlateService;
    @Autowired
    SampleInfoService sampleInfoService;
    @Autowired
    DownloadFileUtils downloadFileUtils;
    @Autowired
    SeqNoGenerateService seqNoGenerateService;

    /**
     * 扩增列表
     * @param request
     * @param query
     * @param pageInfo
     * @return
     * @throws ParseException
     */
    @RequestMapping("/pcrPlateList.html")
    public ModelAndView pcrPlateList(HttpServletRequest request, PcrPlateVo query, PageInfo pageInfo) throws ParseException {
        ModelAndView modelAndView = initializationData.initData(Constants.DICR_TYPE);

        query.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

        List<PcrPlateVo> pcrPlateVoList = pcrPlateService.selectVoList(query, pageInfo);
        int totalCnt = pcrPlateService.selectVOCount(query);

        modelAndView.addObject("pcrPlateVoList", pcrPlateVoList);
        modelAndView.addObject("pageInfo", pageInfo.updatePageInfo(totalCnt));
        modelAndView.setViewName("center/pcrPlate/pcrPlateList");
        return modelAndView;
    }

    //获取扩增列表数据
    @RequestMapping(value = "/getPcrPlateList.html", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> getPcrPlateList(HttpServletRequest request, @RequestBody PcrPlateVo query, PageInfo pageInfo) {
        Map<String,Object> result = new HashMap<>();

        try {

            query.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

            List<PcrPlateVo> pcrPlateVoList = pcrPlateService.selectVoList(query, pageInfo);
            int totalCnt = pcrPlateService.selectVOCount(query);

            result.put("sampleList", pcrPlateVoList);
            result.put("pageInfo", pageInfo.updatePageInfo(totalCnt));
            result.put("success",true);
        }catch (Exception e) {
            result.put("success",false);
            logger.error("getPcrPlateList error",e);
        }

        return result;
    }

    /**
     * 导出PCR扩增样本表
     * @param request
     * @param response
     * @param sampleInfoVo
     */
    @RequestMapping("/exportPcrSampleRecord.html")
    public void exportPcrSampleRecord(HttpServletRequest request, HttpServletResponse response, SampleInfoVo sampleInfoVo) {

        sampleInfoVo.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
        sampleInfoVoList = TestProcessUtils.boardSortAgain(sampleInfoVoList, Constants.STAGE_KZ);
        PcrPlate pcrPlate = pcrPlateService.selectByPrimaryKey(sampleInfoVo.getEntity().getPcrPlateId());

        downloadFileUtils.exportPcrSampleRecord(request, response, sampleInfoVoList, pcrPlate);

    }

    /**
     * 导出DNA检验记录扩增作业单
     * @param request
     * @param response
     * @param sampleInfoVo
     */
    @RequestMapping("/exportWorkFile.html")
    public void exportWorkFile(HttpServletRequest request, HttpServletResponse response, SampleInfoVo sampleInfoVo) {

        sampleInfoVo.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
        List<SampleInfoModel> resultList = TestProcessUtils.boardSortAgainPcr(sampleInfoVoList);
        PcrPlate pcrPlate = pcrPlateService.selectByPrimaryKey(sampleInfoVo.getEntity().getPcrPlateId());

        downloadFileUtils.exportWorkFile(request, response, resultList, pcrPlate);

    }

    /** 创建或拼板创建扩增列表
     * @param request
     * @param pcrPlateVoList
     * @param isSpell
     * @return
     * @throws ParseException
     */
    @RequestMapping(value="/spellOrCreatePcrList.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> spellOrCreatePcrList(HttpServletRequest request, @RequestBody List<PcrPlateVo> pcrPlateVoList, String isSpell) throws ParseException {
        Map<String,Object> result = new HashMap<>();
        boolean flag = false;
        try{
            if (ListUtils.isNotNullAndEmptyList(pcrPlateVoList)) {
                //不拼板
                if (isSpell.equals(Constants.IS_SPELL_NO)) {
                    for (PcrPlateVo pcrPlateVo : pcrPlateVoList) {
                        ExtractPlate extractPlate = extractPlateService.selectByPrimaryKey(pcrPlateVo.getExtractPlateId());
                        if (extractPlate == null) {
                            continue;
                        }
                        //更新扩增样本孔位
                        SampleInfoVo sampleInfoVo = new SampleInfoVo();
                        sampleInfoVo.getEntity().setExtractPlateId(extractPlate.getId());
                        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
                        sampleInfoVoList = TestProcessUtils.getLocation(sampleInfoVoList, Constants.STAGE_KZ);
                        initializationData.updateSampleInfo(sampleInfoVoList, Constants.STAGE_KZ);

                        PcrPlate pcrPlate = initPcrPlate();
                        pcrPlate.setBoardNo(extractPlate.getBoardNo());
                        pcrPlate.setId(UUID.randomUUID().toString());
                        pcrPlate.setHoleNum(extractPlate.getHoleNum());
                        pcrPlateService.insert(pcrPlate);
                        //更新样本板信息
                        extractPlate.setPcrPlateId(pcrPlate.getId());
                        extractPlate.setPcrPlateName(pcrPlate.getBoardNo());
                        extractPlate.setIsCreate(Constants.IS_CREATE_1);
                        flag = updateExtractPlate(extractPlate);
                    }
                }else if (isSpell.equals(Constants.IS_SPELL_YES)) {
                    //拼板
                    //拼板首先创建扩增记录信息
                    int holeNum = 0;
                    List<SampleInfoVo> allSampleInfoVoList = new ArrayList<>();
                    //列表展示时是倒序排列，在拼板时进行倒序循环
                    int plateSort = 1;
                    for (int i = pcrPlateVoList.size() -1; i >= 0; i--) {
                        PcrPlateVo pcrPlateVo = pcrPlateVoList.get(i);
                        if (pcrPlateVo != null) {
                            ExtractPlate extractPlate = extractPlateService.selectByPrimaryKey(pcrPlateVo.getExtractPlateId());

                            if (extractPlate == null) {
                                continue;
                            }

                            if (StringUtils.isNotBlank(extractPlate.getHoleNum())) {
                                holeNum += Integer.parseInt(extractPlate.getHoleNum());
                            }

                            SampleInfoVo sampleInfoVo = new SampleInfoVo();
                            sampleInfoVo.getEntity().setExtractPlateId(extractPlate.getId());
                            List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
                            if (ListUtils.isNotNullAndEmptyList(sampleInfoVoList)) {
                                //按提取板孔位序号排序
                                sampleInfoVoList = TestProcessUtils.holeSort(sampleInfoVoList, Constants.STAGE_TQ);
                                //去除重复样本表信息
                                List<SampleInfoVo> removeDuplicateList = sampleInfoService.selectListVo(sampleInfoVo);
                                for (SampleInfoVo siVo : removeDuplicateList) {
                                    allSampleInfoVoList.addAll(TestProcessUtils.tranferInfo(sampleInfoVoList, plateSort++, Constants.STAGE_KZ, siVo));
                                }
                            }
                        }
                    }
                    //更新检材扩增孔位
//                    allSampleInfoVoList = TestProcessUtils.boardAgain(allSampleInfoVoList, Constants.STAGE_KZ);
                    initializationData.updateSampleInfo(allSampleInfoVoList, Constants.STAGE_KZ);

                    PcrPlate pcrPlate = initPcrPlate();
                    String boardNo = seqNoGenerateService.getNextKZBoardNoVal(DateUtils.dateToString(new Date(), DateUtils.DATE) + "-" + LimsSecurityUtils.getLoginName() + "-" + Constants.STAGE_KZ);
                    pcrPlate.setBoardNo(boardNo);
                    pcrPlate.setHoleNum(String.valueOf(holeNum));
                    pcrPlate.setId(UUID.randomUUID().toString());
                    pcrPlateService.insert(pcrPlate);
                    int count = 1;
                    for (int i = pcrPlateVoList.size() -1; i >= 0; i--) {
                        PcrPlateVo pcrPlateVo = pcrPlateVoList.get(i);
                        if (pcrPlateVo != null) {
                            ExtractPlate extractPlate = extractPlateService.selectByPrimaryKey(pcrPlateVo.getExtractPlateId());

                            //更新样本板信息
                            extractPlate.setPcrPlateId(pcrPlate.getId());
                            extractPlate.setPcrPlateName(pcrPlate.getBoardNo() + "["+(count++)+"]");
                            extractPlate.setIsCreate(Constants.IS_CREATE_1);
                            flag = updateExtractPlate(extractPlate);
                        }
                    }
                }
            }
        }catch (Exception e){
            logger.error("spellOrCreatePcrList error",e);
        }

        if (flag) {
            result.put("success",true);
        }else {
            result.put("success",false);
        }
        return result;
    }

    /**
     * 更新提取信息
     * @param extractPlate
     */
    public boolean updateExtractPlate(ExtractPlate extractPlate) {
        try {
            extractPlateService.updateByPrimaryKey(extractPlate);
            //更新样本信息
            SampleInfo sampleInfo = new SampleInfo();
            sampleInfo.setExtractPlateId(extractPlate.getId());
            sampleInfo.setPcrPlateId(extractPlate.getPcrPlateId());
            sampleInfoService.updateByExtractPlateId(sampleInfo);
            return true;
        }catch (Exception e) {
            logger.error("updateExtractPlate error" + "更新提取信息错误",e);
            return false;
        }
    }

    /**
     * 删除扩增信息
     * @param request
     * @param pcrPlateList
     * @return
     */
    @RequestMapping(value="/deletePcrPlate.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> deletePcrPlate(HttpServletRequest request,@RequestBody List<PcrPlate> pcrPlateList){
        Map<String,Object> result = new HashMap<>();

        try{
            if (ListUtils.isNotNullAndEmptyList(pcrPlateList)) {
                for (PcrPlate pcrPlate : pcrPlateList) {
                    pcrPlateService.deleteFlagById(pcrPlate.getId());
                }
                result.put("success",true);
            }else {
                result.put("success","no");
            }
        }catch (Exception e){
            result.put("success",false);
            logger.error("deletePcrPlate error",e);
        }

        return result;
    }

    public PcrPlate initPcrPlate() {

        String loginName = LimsSecurityUtils.getLoginName();
        PcrPlate pcrPlate = new PcrPlate();
        pcrPlate.setTestSystem("Identifiler Plus");
        pcrPlate.setPcrSystemTrace("4ul Master Mix + 2ul Primer + 0ul H2o + 4ul DNA模板");
        pcrPlate.setPcrSystemConstant("4ul Master Mix + 2ul Primer + 3ul H2o + 1ul DNA模板");
        pcrPlate.setPcrRunNum("28");
        pcrPlate.setCreatePerson(loginName);
        pcrPlate.setCreateDatetime(new Date());
        pcrPlate.setPcrDatetime(new Date());

        return pcrPlate;
    }
}

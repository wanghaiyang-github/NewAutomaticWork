package com.bazl.lims.web.controller.center.sample;

import com.bazl.lims.common.Constants;
import com.bazl.lims.manager.PageInfo;
import com.bazl.lims.manager.model.po.LabUser;
import com.bazl.lims.manager.model.po.PcrPlate;
import com.bazl.lims.manager.model.po.SampleInfo;
import com.bazl.lims.manager.model.po.SyPlate;
import com.bazl.lims.manager.model.vo.PcrPlateVo;
import com.bazl.lims.manager.model.vo.SampleInfoVo;
import com.bazl.lims.manager.model.vo.SyPlateVo;
import com.bazl.lims.manager.services.common.PcrPlateService;
import com.bazl.lims.manager.services.common.SampleInfoService;
import com.bazl.lims.manager.services.common.SeqNoGenerateService;
import com.bazl.lims.manager.services.common.SyPlateService;
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
public class SyPlateController extends BaseController {

    @Autowired
    SyPlateService syPlateService;
    @Autowired
    InitializationData initializationData;
    @Autowired
    PcrPlateService pcrPlateService;
    @Autowired
    SampleInfoService sampleInfoService;
    @Autowired
    DownloadFileUtils downloadFileUtils;
    @Autowired
    SeqNoGenerateService seqNoGenerateService;

    /**
     * 电泳列表
     * @param request
     * @param query
     * @param pageInfo
     * @return
     * @throws ParseException
     */
    @RequestMapping("/syPlateList.html")
    public ModelAndView syPlateList(HttpServletRequest request, SyPlateVo query, PageInfo pageInfo) throws ParseException {
        ModelAndView modelAndView = initializationData.initData(Constants.DICR_TYPE);

        query.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

        List<SyPlateVo> syPlateVoList = syPlateService.selectVoList(query, pageInfo);
        int totalCnt = syPlateService.selectVOCount(query);

        modelAndView.addObject("syPlateVoList", syPlateVoList);
        modelAndView.addObject("pageInfo", pageInfo.updatePageInfo(totalCnt));
        modelAndView.setViewName("center/syPlate/syPlateList");
        return modelAndView;
    }

    //获取电泳列表数据
    @RequestMapping(value = "/getSyPlateList.html", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> getSyPlateList(HttpServletRequest request, @RequestBody SyPlateVo query, PageInfo pageInfo) {
        Map<String,Object> result = new HashMap<>();

        try {

            query.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

            List<SyPlateVo> syPlateVoList = syPlateService.selectVoList(query, pageInfo);
            int totalCnt = syPlateService.selectVOCount(query);

            result.put("sampleList", syPlateVoList);
            result.put("pageInfo", pageInfo.updatePageInfo(totalCnt));
            result.put("success",true);
        }catch (Exception e) {
            result.put("success",false);
            logger.error("getSyPlateList error",e);
        }

        return result;
    }

    /**
     * 导出STR电泳样本表
     * @param request
     * @param response
     * @param sampleInfoVo
     */
    @RequestMapping("/exportSySampleRecord.html")
    public void exportSySampleRecord(HttpServletRequest request, HttpServletResponse response, SampleInfoVo sampleInfoVo) {

        sampleInfoVo.getEntity().setCreatePerson(LimsSecurityUtils.getLoginName());

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
        sampleInfoVoList = TestProcessUtils.boardSortAgain(sampleInfoVoList, Constants.STAGE_SY);
        SyPlate syPlate = syPlateService.selectByPrimaryKey(sampleInfoVo.getEntity().getSyPlateId());

        downloadFileUtils.exportSySampleRecord(request, response, sampleInfoVoList, syPlate);

    }

    /** 创建或拼板创建电泳列表
     * @param request
     * @param syPlateVoList
     * @return
     * @throws ParseException
     */
    @RequestMapping(value="/spellOrCreateSyList.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> spellOrCreateSyList(HttpServletRequest request, @RequestBody List<SyPlateVo> syPlateVoList, String isSpell) throws ParseException {
        Map<String,Object> result = new HashMap<>();
        boolean flag = false;
        try{
            if (ListUtils.isNotNullAndEmptyList(syPlateVoList)) {
                SyPlate syPlate = initSyPlate();
                //不拼板
                if (isSpell.equals(Constants.IS_SPELL_NO)) {
                    for (SyPlateVo syPlateVo : syPlateVoList) {
                        PcrPlate pcrPlate = pcrPlateService.selectByPrimaryKey(syPlateVo.getPcrPlateId());

                        //更新电泳样本孔位
                        SampleInfoVo sampleInfoVo = new SampleInfoVo();
                        sampleInfoVo.getEntity().setPcrPlateId(pcrPlate.getId());
                        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
                        sampleInfoVoList = TestProcessUtils.getLocation(sampleInfoVoList, Constants.STAGE_SY);
                        initializationData.updateSampleInfo(sampleInfoVoList, Constants.STAGE_SY);

                        syPlate.setBoardNo(pcrPlate.getBoardNo());
                        syPlate.setHoleNum(pcrPlate.getHoleNum());
                        syPlate.setId(UUID.randomUUID().toString());
                        syPlateService.insert(syPlate);
                        //更新样本板信息
                        pcrPlate.setSyPlateId(syPlate.getId());
                        pcrPlate.setIsCreate(Constants.IS_CREATE_1);
                        flag = updatePcrPlate(pcrPlate);
                    }
                }else if (isSpell.equals(Constants.IS_SPELL_YES)) {
                    //拼板
                    //拼板首先创建扩增记录信息
                    int holeNum = 0;
                    List<SampleInfoVo> allSampleInfoVoList = new ArrayList<>();
                    //列表展示时是倒序排列，在拼板时进行倒序循环
                    int plateSort = 1;
                    for (int i = syPlateVoList.size() -1; i >= 0; i--) {
                        SyPlateVo syPlateVo = syPlateVoList.get(i);
                        if (syPlateVo != null) {
                            PcrPlate pcrPlate = pcrPlateService.selectByPrimaryKey(syPlateVo.getPcrPlateId());

                            if (pcrPlate == null) {
                                continue;
                            }

                            if (StringUtils.isNotBlank(pcrPlate.getHoleNum())) {
                                holeNum += Integer.parseInt(pcrPlate.getHoleNum());
                            }

                            SampleInfoVo sampleInfoVo = new SampleInfoVo();
                            sampleInfoVo.getEntity().setPcrPlateId(pcrPlate.getId());
                            List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
                            if (ListUtils.isNotNullAndEmptyList(sampleInfoVoList)) {
                                //按扩增板孔位序号排序
                                sampleInfoVoList = TestProcessUtils.holeSort(sampleInfoVoList, Constants.STAGE_KZ);
                                //去除重复样本表信息
                                List<SampleInfoVo> removeDuplicateList = sampleInfoService.selectListVo(sampleInfoVo);
                                for (SampleInfoVo siVo : removeDuplicateList) {
                                    allSampleInfoVoList.addAll(TestProcessUtils.tranferInfo(sampleInfoVoList, plateSort++, Constants.STAGE_SY, siVo));
                                }
                            }
                        }
                    }
                    //更新检材扩增孔位
//                    allSampleInfoVoList = TestProcessUtils.boardAgain(allSampleInfoVoList, Constants.STAGE_SY);
                    initializationData.updateSampleInfo(allSampleInfoVoList, Constants.STAGE_SY);

                    String boardNo = seqNoGenerateService.getNextSYBoardNoVal(DateUtils.dateToString(new Date(), DateUtils.DATE) + "-" + LimsSecurityUtils.getLoginName() + "-" + Constants.STAGE_SY);
                    syPlate.setBoardNo(boardNo);
                    syPlate.setHoleNum(String.valueOf(holeNum));
                    syPlate.setId(UUID.randomUUID().toString());
                    syPlateService.insert(syPlate);
                    int count = 1;
                    for (int i = syPlateVoList.size() -1; i >= 0; i--) {
                        SyPlateVo syPlateVo = syPlateVoList.get(i);
                        if (syPlateVo != null) {
                            PcrPlate pcrPlate = pcrPlateService.selectByPrimaryKey(syPlateVo.getPcrPlateId());

                            //更新样本板信息
                            pcrPlate.setSyPlateId(syPlate.getId());
                            pcrPlate.setSyPlateName(syPlate.getBoardNo() + "["+(count++)+"]");
                            pcrPlate.setIsCreate(Constants.IS_CREATE_1);
                            flag = updatePcrPlate(pcrPlate);
                        }
                    }
                }
            }
        }catch (Exception e){
            logger.error("spellOrCreateSyList error",e);
        }

        if (flag) {
            result.put("success",true);
        }else {
            result.put("success",false);
        }
        return result;
    }


    /**
     * 保存或更新电泳信息
     * @param request
     * @param syPlateList
     * @return
     */
    @RequestMapping(value="/saveSyPlate.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> saveSyPlate(HttpServletRequest request, @RequestBody List<SyPlate> syPlateList){
        Map<String,Object> result = new HashMap<>();

        try{
            if (ListUtils.isNotNullAndEmptyList(syPlateList)) {
                for (SyPlate syPlate : syPlateList) {
                    String loginName = LimsSecurityUtils.getLoginName();
                    if(StringUtils.isBlank(syPlate.getId())){
                        syPlate.setId(UUID.randomUUID().toString());
                        syPlateService.insert(syPlate);
                    }else{
                        SyPlate plate = syPlateService.selectByPrimaryKey(syPlate.getId());
                        if (plate != null) {
                            plate.setIsCreate(Constants.IS_CREATE_1);
                            plate.setOperationPerson(loginName);
                            plate.setOperationDatetime(new Date());
                            syPlateService.updateByPrimaryKey(plate);
                        }
                    }

                }
                result.put("success",true);
            }else {
                result.put("success","no");
            }
        }catch (Exception e){
            result.put("success",false);
            logger.error("saveSyPlate error",e);
        }

        return result;
    }

    /**
     * 更新扩增信息
     * @param pcrPlate
     */
    public boolean updatePcrPlate(PcrPlate pcrPlate) {

        try {
            pcrPlateService.updateByPrimaryKey(pcrPlate);
            //更新样本信息
            SampleInfo sampleInfo = new SampleInfo();
            sampleInfo.setPcrPlateId(pcrPlate.getId());
            sampleInfo.setSyPlateId(pcrPlate.getSyPlateId());
            sampleInfoService.updateByPcrPlateId(sampleInfo);
            return true;
        }catch (Exception e) {
            logger.error("updatePcrPlate error" + "更新扩增信息",e);
            return false;
        }
    }

    /**
     * 删除电泳信息
     * @param request
     * @param syPlateList
     * @return
     */
    @RequestMapping(value="/deleteSyPlate.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> deleteSyPlate(HttpServletRequest request,@RequestBody List<SyPlate> syPlateList){
        Map<String,Object> result = new HashMap<>();

        try{
            if (ListUtils.isNotNullAndEmptyList(syPlateList)) {
                for (SyPlate syPlate : syPlateList) {
                    syPlateService.deleteFlagById(syPlate.getId());
                }
                result.put("success",true);
            }else {
                result.put("success","no");
            }
        }catch (Exception e){
            result.put("success",false);
            logger.error("deleteSyPlate error",e);
        }

        return result;
    }

    public SyPlate initSyPlate() {

        String loginName = LimsSecurityUtils.getLoginName();
        SyPlate syPlate = new SyPlate();
        syPlate.setFirstInstrumentNum("3130");
        syPlate.setMolecularWeightMarker("GS-500LIZ");
        syPlate.setMixingRatio("ul HiDiJ甲酰胺+ul分子量标记");
        syPlate.setSySystem("ul Mix+ul PCR产物");
        syPlate.setEnvironmentTemperature("°C，%");
        syPlate.setDenaturationCondition("95°c，3分钟，0°c，3分钟");
        syPlate.setCreatePerson(loginName);
        syPlate.setCreateDatetime(new Date());
        syPlate.setTestDatetime(new Date());

        return syPlate;
    }
}

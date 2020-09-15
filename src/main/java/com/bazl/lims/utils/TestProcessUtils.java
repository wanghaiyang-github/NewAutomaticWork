package com.bazl.lims.utils;

import com.bazl.lims.common.Constants;
import com.bazl.lims.manager.model.po.SampleInfo;
import com.bazl.lims.manager.model.vo.SampleInfoVo;
import com.bazl.lims.web.datamodel.SampleInfoModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author wanghaiyang
 * @date 2019/3/31.
 */
public class TestProcessUtils {

    /**
     * 选取板
     * @param sampleInfoVoList
     * @param holeNum
     * @return
     */
    public static List<Map<String, Object>> boardSort(List<SampleInfoVo> sampleInfoVoList, int holeNum, SampleInfoVo infoVo, String stage) {
        List<Map<String, Object>> tempList = new ArrayList<>();

        if (ListUtils.isNullOrEmptyList(sampleInfoVoList)) {
            sampleInfoVoList = new ArrayList<>();
        }

        List<SampleInfoVo> labSampleInfoVoList = new ArrayList<>();
        for(int i = 0; i < holeNum; i++) {
            String position = Constants.POSTION_ARR[i];
            SampleInfoVo sampleInfoVo = new SampleInfoVo();

            sampleInfoVo.setHoleNum(String.valueOf(holeNum));
            sampleInfoVo.setElutionName(infoVo.getElutionName());
            sampleInfoVo.getEntity().setSamplePlateLocation(position);
            sampleInfoVo.getEntity().setSampleLocationSort(Constants.POSTION_HORIZONTAL_NUMBER[i]);

            labSampleInfoVoList.add(sampleInfoVo);
        }

        tempList = getTempList(sampleInfoVoList, labSampleInfoVoList, holeNum, stage);

        return tempList;
    }

    public static List<Map<String, Object>> getTempList(List<SampleInfoVo> sampleInfoVoList, List<SampleInfoVo> labSampleInfoVoList, int holeNum, String stage) {
        List<Map<String, Object>> tempList = new ArrayList<>();

        for (SampleInfoVo siVo : sampleInfoVoList) {
            for (SampleInfoVo sampleInfoVo : labSampleInfoVoList) {
                if (Constants.STAGE_TQ.equals(stage)) {
                    if (siVo.getEntity().getExtractLocationSort() != null &&
                            siVo.getEntity().getExtractLocationSort().equals(sampleInfoVo.getEntity().getSampleLocationSort())) {
                        siVo.getEntity().setSampleLocationSort(sampleInfoVo.getEntity().getSampleLocationSort());
                        siVo.getEntity().setSamplePlateLocation(sampleInfoVo.getEntity().getSamplePlateLocation());
                        LocalBeanUtils.copyPropertiesIgnoreNull(siVo, sampleInfoVo);
                    }
                }else if (Constants.STAGE_KZ.equals(stage)){
                    if (siVo.getEntity().getPcrLocationSort() != null &&
                            siVo.getEntity().getPcrLocationSort().equals(sampleInfoVo.getEntity().getSampleLocationSort())) {
                        siVo.getEntity().setSampleLocationSort(sampleInfoVo.getEntity().getSampleLocationSort());
                        siVo.getEntity().setSamplePlateLocation(sampleInfoVo.getEntity().getSamplePlateLocation());
                        LocalBeanUtils.copyPropertiesIgnoreNull(siVo, sampleInfoVo);
                    }
                }else if (Constants.STAGE_SY.equals(stage)) {
                    if (siVo.getEntity().getSyLocationSort() != null &&
                            siVo.getEntity().getSyLocationSort().equals(sampleInfoVo.getEntity().getSampleLocationSort())) {
                        siVo.getEntity().setSampleLocationSort(sampleInfoVo.getEntity().getSampleLocationSort());
                        siVo.getEntity().setSamplePlateLocation(sampleInfoVo.getEntity().getSamplePlateLocation());
                        LocalBeanUtils.copyPropertiesIgnoreNull(siVo, sampleInfoVo);
                    }
                }else {
                    if (siVo.getEntity().getSampleLocationSort() != null &&
                            siVo.getEntity().getSampleLocationSort().equals(sampleInfoVo.getEntity().getSampleLocationSort())) {
                        siVo.getEntity().setSampleLocationSort(sampleInfoVo.getEntity().getSampleLocationSort());
                        siVo.getEntity().setSamplePlateLocation(sampleInfoVo.getEntity().getSamplePlateLocation());
                        LocalBeanUtils.copyPropertiesIgnoreNull(siVo, sampleInfoVo);
                    }
                }
            }
        }

        int count = holeNum/24;

        for(int i = 0; i < count; i++) {
            Map<String, Object> map = new HashMap<>();
            List<SampleInfoVo> list = new ArrayList<>();
            for (int j = i*24; j < (i+1)*24; j++) {
                list.add(labSampleInfoVoList.get(j));
            }
            map.put("list", list);
            tempList.add(map);
        }

        return tempList;
    }

    /**
     * 整板孔位
     * @param sampleInfoVoList
     * @param holeNum
     * @return
     */
    public static List<Map<String, Object>> boardSortAll(List<SampleInfoVo> sampleInfoVoList, int holeNum, SampleInfoVo infoVo, String stage) {
        List<Map<String, Object>> tempList = new ArrayList<>();

        if (ListUtils.isNullOrEmptyList(sampleInfoVoList)) {
            sampleInfoVoList = new ArrayList<>();
        }

        List<SampleInfoVo> labSampleInfoVoList = new ArrayList<>();
        for(int i = 0; i < holeNum; i++) {
            String position = Constants.POSTION_ARR_ALL[i];
            SampleInfoVo sampleInfoVo = new SampleInfoVo();

            sampleInfoVo.setHoleNum(String.valueOf(holeNum));
            sampleInfoVo.setElutionName(infoVo.getElutionName());
            sampleInfoVo.getEntity().setSamplePlateLocation(position);
            sampleInfoVo.getEntity().setSampleLocationSort(Constants.POSTION_HORIZONTAL_NUMBER_ALL[i]);

            labSampleInfoVoList.add(sampleInfoVo);
        }

        tempList = getTempList(sampleInfoVoList, labSampleInfoVoList, holeNum, stage);

        return tempList;
    }

    /**
     * 孔位排序
     * @param sampleInfoVoList
     * @param stage
     * @return
     */
    public static List<SampleInfoVo> holeSort(List<SampleInfoVo> sampleInfoVoList, String stage) {
        Collections.sort(sampleInfoVoList, new Comparator<SampleInfoVo>() {
            public int compare(SampleInfoVo o1, SampleInfoVo o2) {
                if (Constants.STAGE_TQ.equals(stage)) {
                    return o1.getEntity().getExtractLocationSort().compareTo(
                            o2.getEntity().getExtractLocationSort());
                }else if (Constants.STAGE_KZ.equals(stage)){
                    return o1.getEntity().getPcrLocationSort().compareTo(
                            o2.getEntity().getPcrLocationSort());
                }else if (Constants.STAGE_SY.equals(stage)) {
                    return o1.getEntity().getSyLocationSort().compareTo(
                            o2.getEntity().getSyLocationSort());
                }else {
                    return o1.getEntity().getSampleLocationSort().compareTo(
                            o2.getEntity().getSampleLocationSort());
                }
            }
        });

        return sampleInfoVoList;
    }


    /**
     * 板孔排序
     * @param sampleInfoVoList
     * @return
     */
    public static List<SampleInfoVo> boardSortAgain(List<SampleInfoVo> sampleInfoVoList, String stage) {
        if (ListUtils.isNullOrEmptyList(sampleInfoVoList)) {
            sampleInfoVoList = new ArrayList<>();
        }

        List<SampleInfoVo> labSampleInfoVoList = new ArrayList<>();
        for(int i = 0; i < Constants.SYTABLE_POSTION_ARR.length; i++) {
            String position = Constants.SYTABLE_POSTION_ARR[i];
            SampleInfoVo sampleInfoVo = new SampleInfoVo();
            sampleInfoVo.getEntity().setSamplePlateLocation(position);
            sampleInfoVo.getEntity().setSampleNo("");

            labSampleInfoVoList.add(sampleInfoVo);
        }

        for (SampleInfoVo siVo : sampleInfoVoList) {
            for (SampleInfoVo sampleInfoVo : labSampleInfoVoList) {
                if (Constants.STAGE_TQ.equals(stage)) {
                    if (StringUtils.isNotBlank(siVo.getEntity().getExtractPlateLocation()) &&
                            siVo.getEntity().getExtractPlateLocation().equals(sampleInfoVo.getEntity().getSamplePlateLocation())) {
                        LocalBeanUtils.copyPropertiesIgnoreNull(siVo, sampleInfoVo);
                    }
                }else if (Constants.STAGE_KZ.equals(stage)){
                    if (StringUtils.isNotBlank(siVo.getEntity().getPcrPlateLocation()) &&
                            siVo.getEntity().getPcrPlateLocation().equals(sampleInfoVo.getEntity().getSamplePlateLocation())) {
                        LocalBeanUtils.copyPropertiesIgnoreNull(siVo, sampleInfoVo);
                    }
                }else if (Constants.STAGE_SY.equals(stage)) {
                    if (StringUtils.isNotBlank(siVo.getEntity().getSyPlateLocation()) &&
                            siVo.getEntity().getSyPlateLocation().equals(sampleInfoVo.getEntity().getSamplePlateLocation())) {
                        LocalBeanUtils.copyPropertiesIgnoreNull(siVo, sampleInfoVo);
                    }
                }else {
                    if (StringUtils.isNotBlank(siVo.getEntity().getSamplePlateLocation()) &&
                            siVo.getEntity().getSamplePlateLocation().equals(sampleInfoVo.getEntity().getSamplePlateLocation())) {
                        LocalBeanUtils.copyPropertiesIgnoreNull(siVo, sampleInfoVo);
                    }
                }
            }
        }

        return labSampleInfoVoList;
    }

    /**
     * 扩增作业单板孔排序
     * @param sampleInfoVoList
     * @return
     */
    public static List<SampleInfoModel> boardSortAgainPcr(List<SampleInfoVo> sampleInfoVoList) {
        if (ListUtils.isNullOrEmptyList(sampleInfoVoList)) {
            sampleInfoVoList = new ArrayList<>();
        }

        List<SampleInfoVo> labSampleInfoVoList = new ArrayList<>();
        for(int i = 0; i < Constants.SYTABLE_POSTION_ARR_VER.length; i++) {
            String position = Constants.SYTABLE_POSTION_ARR_VER[i];
            SampleInfoVo sampleInfoVo = new SampleInfoVo();
            sampleInfoVo.getEntity().setSamplePlateLocation(position);
            sampleInfoVo.getEntity().setSampleNo("");

            labSampleInfoVoList.add(sampleInfoVo);
        }

        for (SampleInfoVo siVo : sampleInfoVoList) {
            for (SampleInfoVo sampleInfoVo : labSampleInfoVoList) {
                if (StringUtils.isNotBlank(siVo.getEntity().getPcrPlateLocation()) &&
                        siVo.getEntity().getPcrPlateLocation().equals(sampleInfoVo.getEntity().getSamplePlateLocation())) {
                    LocalBeanUtils.copyPropertiesIgnoreNull(siVo, sampleInfoVo);
                }
            }
        }

       /* List<SampleInfoVo> newSampleInfoVoList = new ArrayList<>();
        for(int i = 0; i < Constants.PCRTABLE_POSTION_ARR_VER.length; i++) {
            String position = Constants.PCRTABLE_POSTION_ARR_VER[i];
            for (SampleInfoVo siVo : labSampleInfoVoList) {
                if (StringUtils.isNotBlank(siVo.getEntity().getExtractPlateLocation()) &&
                        position.equals(siVo.getEntity().getExtractPlateLocation())) {
                    newSampleInfoVoList.add(siVo);
                }
            }
        }*/

        List<SampleInfoModel> resultList = new ArrayList<>();
        int count = labSampleInfoVoList.size();
        List<SampleInfo> strList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (labSampleInfoVoList.get(i) == null) {
                continue;
            }
            String plateLocation = labSampleInfoVoList.get(i).getEntity().getPcrPlateLocation();
            labSampleInfoVoList.get(i).getEntity().setPcrPlateLocation(tranfer(plateLocation));
            labSampleInfoVoList.get(i).getEntity().setCount(i+1);
            strList.add(labSampleInfoVoList.get(i).getEntity());

            SampleInfoModel sampleInfoModel = new SampleInfoModel();
            sampleInfoModel.setSampleInfoList(strList);
            if ((i + 1) % 24 == 0) {
                resultList.add(sampleInfoModel);
                strList = new ArrayList<>();
            }
        }

        return resultList;
    }

    /**
     * 赋值
     * @param sampleInfoVoList
     * @return
     */
    public static List<SampleInfoVo> getLocation(List<SampleInfoVo> sampleInfoVoList, String stage) {
        if (ListUtils.isNotNullAndEmptyList(sampleInfoVoList)) {
            for (SampleInfoVo siVo : sampleInfoVoList) {
                if (Constants.STAGE_TQ.equals(stage)) {
                    siVo.getEntity().setExtractPlateLocation(siVo.getEntity().getSamplePlateLocation());
                    siVo.getEntity().setExtractLocationSort(siVo.getEntity().getSampleLocationSort());
                    siVo.getEntity().setExtractPlateSort(siVo.getEntity().getSamplePlateSort());
                }else if (Constants.STAGE_KZ.equals(stage)){
                    siVo.getEntity().setPcrPlateLocation(siVo.getEntity().getExtractPlateLocation());
                    siVo.getEntity().setPcrLocationSort(siVo.getEntity().getExtractLocationSort());
                    siVo.getEntity().setPcrPlateSort(siVo.getEntity().getExtractPlateSort());
                }else if (Constants.STAGE_SY.equals(stage)) {
                    siVo.getEntity().setSyPlateLocation(siVo.getEntity().getPcrPlateLocation());
                    siVo.getEntity().setSyLocationSort(siVo.getEntity().getPcrLocationSort());
                    siVo.getEntity().setSyPlateSort(siVo.getEntity().getPcrPlateSort());
                }
            }
        }

        return sampleInfoVoList;
    }

    /**
     * 板孔排序
     * @param sampleInfoVoList
     * @return
     */
    public static List<SampleInfoVo> boardAgain(List<SampleInfoVo> sampleInfoVoList, String stage) {
        if (ListUtils.isNullOrEmptyList(sampleInfoVoList)) {
            sampleInfoVoList = new ArrayList<>();
        }

        for(int i = 0; i < sampleInfoVoList.size(); i++) {
            String position = Constants.SYTABLE_POSTION_ARR_VER[i];
            SampleInfoVo siVo = sampleInfoVoList.get(i);
            if (siVo != null) {
                if (Constants.STAGE_TQ.equals(stage)) {
                    siVo.getEntity().setExtractPlateLocation(position);
                    siVo.getEntity().setExtractLocationSort(Constants.SYTABLE_POSTION_ARR_VER_NUM[i]);
                }else if (Constants.STAGE_KZ.equals(stage)){
                    siVo.getEntity().setPcrPlateLocation(position);
                    siVo.getEntity().setPcrLocationSort(Constants.SYTABLE_POSTION_ARR_VER_NUM[i]);
                }else if (Constants.STAGE_SY.equals(stage)) {
                    siVo.getEntity().setSyPlateLocation(position);
                    siVo.getEntity().setSyLocationSort(Constants.SYTABLE_POSTION_ARR_VER_NUM[i]);
                }
            }
        }

        return sampleInfoVoList;
    }

    /**
     * 增加样本信息
     * @param sampleInfoVoList
     * @return
     */
    public static List<SampleInfoVo> addSampleInfo(List<SampleInfoVo> sampleInfoVoList, int plateSort, String stage, SampleInfoVo siVo) {
        List<SampleInfoVo> newSampleInfoVoList = new ArrayList<>();
        if (ListUtils.isNotNullAndEmptyList(sampleInfoVoList)) {
            for (SampleInfoVo sampleInfoVo : sampleInfoVoList) {
                if (Constants.STAGE_TQ.equals(stage)) {
                    if (StringUtils.isNotBlank(siVo.getEntity().getExtractPlateSort()) &&
                            siVo.getEntity().getExtractPlateSort().equals(sampleInfoVo.getEntity().getExtractPlateSort())) {
                        sampleInfoVo.getEntity().setExtractPlateSort(String.valueOf(plateSort));
                        newSampleInfoVoList.add(sampleInfoVo);
                    }
                }else if (Constants.STAGE_KZ.equals(stage)){
                    if (StringUtils.isNotBlank(siVo.getEntity().getPcrPlateSort()) &&
                            siVo.getEntity().getPcrPlateSort().equals(sampleInfoVo.getEntity().getPcrPlateSort())) {
                        sampleInfoVo.getEntity().setPcrPlateSort(String.valueOf(plateSort));
                        newSampleInfoVoList.add(sampleInfoVo);
                    }
                }else if (Constants.STAGE_SY.equals(stage)) {
                    if (StringUtils.isNotBlank(siVo.getEntity().getSyPlateSort()) &&
                            siVo.getEntity().getSyPlateSort().equals(sampleInfoVo.getEntity().getSyPlateSort())) {
                        sampleInfoVo.getEntity().setSyPlateSort(String.valueOf(plateSort));
                        newSampleInfoVoList.add(sampleInfoVo);
                    }
                }else {
                    if (StringUtils.isNotBlank(siVo.getEntity().getSamplePlateSort()) &&
                            siVo.getEntity().getSamplePlateSort().equals(sampleInfoVo.getEntity().getSamplePlateSort())) {
                        sampleInfoVo.getEntity().setSamplePlateSort(String.valueOf(plateSort));
                        newSampleInfoVoList.add(sampleInfoVo);
                    }
                }
            }
        }

        /*if (newSampleInfoVoList.size() < 24) {
            for (int i = newSampleInfoVoList.size(); i < 24; i++) {
                SampleInfoVo sampleInfoVo = new SampleInfoVo();
                newSampleInfoVoList.add(sampleInfoVo);
            }
        }*/

        //如果不够24补齐,可能存在两个孔位直接又没有值的情况
        List<SampleInfoVo> resultList = new ArrayList<>();
        Integer locationSort = 0;
        for (int i = 24*(plateSort-1); i < 24*plateSort; i++) {
            locationSort = Constants.SYTABLE_POSTION_ARR_VER_NUM[i];
            SampleInfoVo sampleInfoVo = getSampleSort(newSampleInfoVoList, stage, locationSort);
            resultList.add(sampleInfoVo);
        }

        return resultList;
    }

    /**
     * 转换样本信息
     * @param sampleInfoVoList
     * @return
     */
    public static List<SampleInfoVo> tranferInfo(List<SampleInfoVo> sampleInfoVoList, int plateSort, String stage, SampleInfoVo siVo) {
        List<SampleInfoVo> newSampleInfoVoList = new ArrayList<>();
        if (ListUtils.isNotNullAndEmptyList(sampleInfoVoList)) {
            for (SampleInfoVo sampleInfoVo : sampleInfoVoList) {
                if (Constants.STAGE_TQ.equals(stage)) {
                    if (StringUtils.isNotBlank(siVo.getEntity().getSamplePlateSort()) &&
                            siVo.getEntity().getSamplePlateSort().equals(sampleInfoVo.getEntity().getSamplePlateSort())) {
                        sampleInfoVo.getEntity().setExtractPlateSort(String.valueOf(plateSort));
                        int locationSort = 0;
                        if (sampleInfoVo.getEntity().getSampleLocationSort() != null) {
                            locationSort = sampleInfoVo.getEntity().getSampleLocationSort().intValue();
                        }
                        int sort = 24*(plateSort - 1) + locationSort%24;
                        sampleInfoVo.getEntity().setExtractPlateLocation(Constants.SYTABLE_POSTION_ARR_VER[sort-1]);
                        sampleInfoVo.getEntity().setExtractLocationSort(Constants.SYTABLE_POSTION_ARR_VER_NUM[sort-1]);
                        newSampleInfoVoList.add(sampleInfoVo);
                    }
                }else if (Constants.STAGE_KZ.equals(stage)){
                    if (StringUtils.isNotBlank(siVo.getEntity().getExtractPlateSort()) &&
                            siVo.getEntity().getExtractPlateSort().equals(sampleInfoVo.getEntity().getExtractPlateSort())) {
                        sampleInfoVo.getEntity().setPcrPlateSort(String.valueOf(plateSort));
                        int locationSort = 0;
                        if (sampleInfoVo.getEntity().getExtractLocationSort() != null) {
                            locationSort = sampleInfoVo.getEntity().getExtractLocationSort().intValue();
                        }
                        int sort = 24*(plateSort - 1) + locationSort%24;
                        sampleInfoVo.getEntity().setPcrPlateLocation(Constants.SYTABLE_POSTION_ARR_VER[sort-1]);
                        sampleInfoVo.getEntity().setPcrLocationSort(Constants.SYTABLE_POSTION_ARR_VER_NUM[sort-1]);
                        newSampleInfoVoList.add(sampleInfoVo);
                    }
                }else if (Constants.STAGE_SY.equals(stage)) {
                    if (StringUtils.isNotBlank(siVo.getEntity().getPcrPlateSort()) &&
                            siVo.getEntity().getPcrPlateSort().equals(sampleInfoVo.getEntity().getPcrPlateSort())) {
                        sampleInfoVo.getEntity().setSyPlateSort(String.valueOf(plateSort));
                        int locationSort = 0;
                        if (sampleInfoVo.getEntity().getPcrLocationSort() != null) {
                            locationSort = sampleInfoVo.getEntity().getPcrLocationSort().intValue();
                        }
                        int sort = 24*(plateSort - 1) + locationSort%24;
                        sampleInfoVo.getEntity().setSyPlateLocation(Constants.SYTABLE_POSTION_ARR_VER[sort-1]);
                        sampleInfoVo.getEntity().setSyLocationSort(Constants.SYTABLE_POSTION_ARR_VER_NUM[sort-1]);
                        newSampleInfoVoList.add(sampleInfoVo);
                    }
                }
            }
        }


        return newSampleInfoVoList;
    }

    /**
     * 判断检材在什么位置
     * @param sampleInfoVoList
     * @param stage
     * @param locationSort
     * @return
     */
    public static SampleInfoVo getSampleSort(List<SampleInfoVo> sampleInfoVoList, String stage, Integer locationSort) {
        SampleInfoVo infoVo = null;
        boolean flag = false;
        for (SampleInfoVo sampleInfoVo : sampleInfoVoList) {
            if (Constants.STAGE_TQ.equals(stage)) {
                if (locationSort.equals(sampleInfoVo.getEntity().getExtractLocationSort())) {
                    infoVo = sampleInfoVo;
                    flag = true;
                    break;
                }
            }else if (Constants.STAGE_KZ.equals(stage)){
                if (locationSort.equals(sampleInfoVo.getEntity().getPcrLocationSort())) {
                    infoVo = sampleInfoVo;
                    flag = true;
                    break;
                }
            }else if (Constants.STAGE_SY.equals(stage)) {
                if (locationSort.equals(sampleInfoVo.getEntity().getSyLocationSort())) {
                    infoVo = sampleInfoVo;
                    flag = true;
                    break;
                }
            }else {
                if (locationSort.equals(sampleInfoVo.getEntity().getSampleLocationSort())) {
                    infoVo = sampleInfoVo;
                    flag = true;
                    break;
                }
            }
        }

        if (!flag) {
            infoVo = new SampleInfoVo();
        }

        return infoVo;
    }

    public static String tranfer(String plateLocation) {
        if (StringUtils.isBlank(plateLocation)) {
            return "";
        }else {
            char[] c = plateLocation.toCharArray();
            String str = plateLocation.substring(1, 2);
            if ("0".equals(str)) {
                return removeCharAt(plateLocation, 1);
            }else {
                return plateLocation;
            }
        }
    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    public static String getSamplePlateSort(int count) {
        String sort = "";
        if (count % 24 == 0) {
            if (count / 24 == 1) {
                sort = "1";
            }else if (count / 24 == 2) {
                sort = "2";
            }else if (count / 24 == 3) {
                sort = "3";
            }else if (count / 24 == 4) {
                sort = "4";
            }
        }

        return sort;
    }

}

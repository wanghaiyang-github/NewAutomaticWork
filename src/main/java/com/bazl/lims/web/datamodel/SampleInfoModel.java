package com.bazl.lims.web.datamodel;



import com.bazl.lims.manager.model.po.SampleInfo;
import com.bazl.lims.manager.model.po.SampleTable;
import com.bazl.lims.manager.model.vo.SampleInfoVo;

import java.util.List;
/**
 * @author wanghaiyang
 * @date 2020/2/13.
 */
public class SampleInfoModel {

    private List<SampleInfoVo> sampleInfoVoList;

    private List<SampleInfo> sampleInfoList;

    private String parameter;

    private SampleTable sampleTable;

    public List<SampleInfoVo> getSampleInfoVoList() {
        return sampleInfoVoList;
    }

    public void setSampleInfoVoList(List<SampleInfoVo> sampleInfoVoList) {
        this.sampleInfoVoList = sampleInfoVoList;
    }

    public List<SampleInfo> getSampleInfoList() {
        return sampleInfoList;
    }

    public void setSampleInfoList(List<SampleInfo> sampleInfoList) {
        this.sampleInfoList = sampleInfoList;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public SampleTable getSampleTable() {
        return sampleTable;
    }

    public void setSampleTable(SampleTable sampleTable) {
        this.sampleTable = sampleTable;
    }
}

package com.bazl.lims.manager.model.vo;

import com.bazl.lims.manager.model.po.SampleTable;

import java.io.Serializable;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public class SampleTableVo extends AbstractBaseVO<SampleTable> implements Serializable {

    public SampleTableVo() {
        this.entity = new SampleTable();
    }

    public SampleTableVo(SampleTable entity) {
        this.entity = entity;
    }

    private String elutionDefaultName;

    private String sampleNo;

    private String tabValue;

    public String getElutionDefaultName() {
        return elutionDefaultName;
    }

    public void setElutionDefaultName(String elutionDefaultName) {
        this.elutionDefaultName = elutionDefaultName;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getTabValue() {
        return tabValue;
    }

    public void setTabValue(String tabValue) {
        this.tabValue = tabValue;
    }
}

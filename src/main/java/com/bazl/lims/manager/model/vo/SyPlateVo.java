package com.bazl.lims.manager.model.vo;

import com.bazl.lims.manager.model.po.SyPlate;

import java.io.Serializable;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public class SyPlateVo extends AbstractBaseVO<SyPlate> implements Serializable {

    public SyPlateVo() {
        this.entity = new SyPlate();
    }

    public SyPlateVo(SyPlate entity) {
        this.entity = entity;
    }

    private String pcrPlateId;

    private String extractPlateId;

    private String extractPlateName;

    private String sampleNo;

    public String getPcrPlateId() {
        return pcrPlateId;
    }

    public void setPcrPlateId(String pcrPlateId) {
        this.pcrPlateId = pcrPlateId;
    }

    public String getExtractPlateName() {
        return extractPlateName;
    }

    public void setExtractPlateName(String extractPlateName) {
        this.extractPlateName = extractPlateName;
    }

    public String getExtractPlateId() {
        return extractPlateId;
    }

    public void setExtractPlateId(String extractPlateId) {
        this.extractPlateId = extractPlateId;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }
}

package com.bazl.lims.manager.model.vo;

import com.bazl.lims.manager.model.po.PcrPlate;

import java.io.Serializable;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public class PcrPlateVo extends AbstractBaseVO<PcrPlate> implements Serializable {

    public PcrPlateVo() {
        this.entity = new PcrPlate();
    }

    public PcrPlateVo(PcrPlate entity) {
        this.entity = entity;
    }

    private String extractPlateId;

    private String sampleNo;

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

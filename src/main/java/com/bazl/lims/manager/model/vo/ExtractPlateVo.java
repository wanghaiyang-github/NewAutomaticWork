package com.bazl.lims.manager.model.vo;

import com.bazl.lims.manager.model.po.ExtractPlate;

import java.io.Serializable;
import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public class ExtractPlateVo extends AbstractBaseVO<ExtractPlate> implements Serializable {

    public ExtractPlateVo() {
        this.entity = new ExtractPlate();
    }

    public ExtractPlateVo(ExtractPlate entity) {
        this.entity = entity;
    }

    private String pcrPlateName;

    private String sampleTableId;

    private String sampleNo;

    public String getPcrPlateName() {
        return pcrPlateName;
    }

    public void setPcrPlateName(String pcrPlateName) {
        this.pcrPlateName = pcrPlateName;
    }

    public String getSampleTableId() {
        return sampleTableId;
    }

    public void setSampleTableId(String sampleTableId) {
        this.sampleTableId = sampleTableId;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }
}

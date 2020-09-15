package com.bazl.lims.manager.model.vo;

import com.bazl.lims.manager.model.po.ProgramRecord;

import java.io.Serializable;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public class ProgramRecordVo extends AbstractBaseVO<ProgramRecord> implements Serializable {

    public ProgramRecordVo() {
        this.entity = new ProgramRecord();
    }

    public ProgramRecordVo(ProgramRecord entity) {
        this.entity = entity;
    }
}

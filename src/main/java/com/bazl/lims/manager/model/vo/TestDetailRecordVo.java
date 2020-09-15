package com.bazl.lims.manager.model.vo;

import com.bazl.lims.manager.model.po.TestDetailRecord;

import java.io.Serializable;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public class TestDetailRecordVo extends AbstractBaseVO<TestDetailRecord> implements Serializable {

    public TestDetailRecordVo() {
        this.entity = new TestDetailRecord();
    }

    public TestDetailRecordVo(TestDetailRecord entity) {
        this.entity = entity;
    }
}

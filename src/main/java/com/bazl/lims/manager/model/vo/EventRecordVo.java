package com.bazl.lims.manager.model.vo;

import com.bazl.lims.manager.model.po.EventRecord;

import java.io.Serializable;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public class EventRecordVo extends AbstractBaseVO<EventRecord> implements Serializable {

    public EventRecordVo() {
        this.entity = new EventRecord();
    }

    public EventRecordVo(EventRecord entity) {
        this.entity = entity;
    }

}

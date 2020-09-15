package com.bazl.lims.manager.model.po;

import com.bazl.lims.web.helper.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public class TestDetailRecord {

    /** 主键id */
    private String id;

    /** 实验主键ID */
    private String testId;

    /** 事件主键ID */
    private String eventId;

    /** 信息 */
    private String message;

    /** 结果状态 */
    private String resultStatus;

    /** 删除标记，0：未删除；1：已删除 */
    private String deleteFlag;

    /** 删除时间 */
    private Date deleteDatetime;

    /** 创建人 */
    private String createPerson;

    /** 创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date createDatetime;

    /** 操作人 */
    private String operationPerson;

    /** 操作时间 */
    private Date operationDatetime;

    /** 完成时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date finishDatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getDeleteDatetime() {
        return deleteDatetime;
    }

    public void setDeleteDatetime(Date deleteDatetime) {
        this.deleteDatetime = deleteDatetime;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getOperationPerson() {
        return operationPerson;
    }

    public void setOperationPerson(String operationPerson) {
        this.operationPerson = operationPerson;
    }

    public Date getOperationDatetime() {
        return operationDatetime;
    }

    public void setOperationDatetime(Date operationDatetime) {
        this.operationDatetime = operationDatetime;
    }

    public Date getFinishDatetime() {
        return finishDatetime;
    }

    public void setFinishDatetime(Date finishDatetime) {
        this.finishDatetime = finishDatetime;
    }
}
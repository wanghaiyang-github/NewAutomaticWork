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
public class PcrPlate {

    /** 主键id */
    private String id;

    /** 电泳表主键ID */
    private String syPlateId;

    /** 板号 */
    private String boardNo;

    /** 状态，0：未开始，1：已开始，2：已完成 */
    private String state;

    /** 是否创建：0：未创建，1：已创建 */
    private String isCreate;

    /** 扩增时间 */
    private Date pcrDatetime;

    /** 检验系统 */
    private String testSystem;

    /** 扩增体系（微量） */
    private String pcrSystemTrace;

    /** 扩增体系（常量） */
    private String pcrSystemConstant;

    /** 扩增循环数 */
    private String pcrRunNum;

    /** 试剂批次 */
    private String reagentBatch;

    /** 仪器型号 */
    private String pcrInstrumentNum;

    /** 电泳板名称 */
    private String syPlateName;

    /** 孔数量 */
    private String holeNum;;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date operationDatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSyPlateId() {
        return syPlateId;
    }

    public void setSyPlateId(String syPlateId) {
        this.syPlateId = syPlateId;
    }

    public String getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(String boardNo) {
        this.boardNo = boardNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsCreate() {
        return isCreate;
    }

    public void setIsCreate(String isCreate) {
        this.isCreate = isCreate;
    }

    public String getTestSystem() {
        return testSystem;
    }

    public void setTestSystem(String testSystem) {
        this.testSystem = testSystem;
    }

    public Date getPcrDatetime() {
        return pcrDatetime;
    }

    public void setPcrDatetime(Date pcrDatetime) {
        this.pcrDatetime = pcrDatetime;
    }

    public String getPcrSystemTrace() {
        return pcrSystemTrace;
    }

    public void setPcrSystemTrace(String pcrSystemTrace) {
        this.pcrSystemTrace = pcrSystemTrace;
    }

    public String getPcrSystemConstant() {
        return pcrSystemConstant;
    }

    public void setPcrSystemConstant(String pcrSystemConstant) {
        this.pcrSystemConstant = pcrSystemConstant;
    }

    public String getPcrRunNum() {
        return pcrRunNum;
    }

    public void setPcrRunNum(String pcrRunNum) {
        this.pcrRunNum = pcrRunNum;
    }

    public String getReagentBatch() {
        return reagentBatch;
    }

    public void setReagentBatch(String reagentBatch) {
        this.reagentBatch = reagentBatch;
    }

    public String getPcrInstrumentNum() {
        return pcrInstrumentNum;
    }

    public void setPcrInstrumentNum(String pcrInstrumentNum) {
        this.pcrInstrumentNum = pcrInstrumentNum;
    }

    public String getSyPlateName() {
        return syPlateName;
    }

    public void setSyPlateName(String syPlateName) {
        this.syPlateName = syPlateName;
    }

    public String getHoleNum() {
        return holeNum;
    }

    public void setHoleNum(String holeNum) {
        this.holeNum = holeNum;
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
}
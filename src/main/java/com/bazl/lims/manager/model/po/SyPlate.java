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
public class SyPlate {

    /** 主键id */
    private String id;

    /** 板号 */
    private String boardNo;

    /** 状态，0：未开始，1：已开始，2：已完成 */
    private String state;

    /** 是否创建：0：未创建，1：已创建 */
    private String isCreate;

    /** 检验时间 */
    private Date testDatetime;

    /** 首次仪器号 */
    private String firstInstrumentNum;

    /** 分子量标记 */
    private String molecularWeightMarker;

    /** 混合比例 */
    private String mixingRatio;

    /** 上样体系 */
    private String sySystem;

    /** 环境温度 */
    private String environmentTemperature;

    /** 变性条件 */
    private String denaturationCondition;

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

    public Date getTestDatetime() {
        return testDatetime;
    }

    public void setTestDatetime(Date testDatetime) {
        this.testDatetime = testDatetime;
    }

    public String getFirstInstrumentNum() {
        return firstInstrumentNum;
    }

    public void setFirstInstrumentNum(String firstInstrumentNum) {
        this.firstInstrumentNum = firstInstrumentNum;
    }

    public String getMolecularWeightMarker() {
        return molecularWeightMarker;
    }

    public void setMolecularWeightMarker(String molecularWeightMarker) {
        this.molecularWeightMarker = molecularWeightMarker;
    }

    public String getMixingRatio() {
        return mixingRatio;
    }

    public void setMixingRatio(String mixingRatio) {
        this.mixingRatio = mixingRatio;
    }

    public String getSySystem() {
        return sySystem;
    }

    public void setSySystem(String sySystem) {
        this.sySystem = sySystem;
    }

    public String getEnvironmentTemperature() {
        return environmentTemperature;
    }

    public void setEnvironmentTemperature(String environmentTemperature) {
        this.environmentTemperature = environmentTemperature;
    }

    public String getDenaturationCondition() {
        return denaturationCondition;
    }

    public void setDenaturationCondition(String denaturationCondition) {
        this.denaturationCondition = denaturationCondition;
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
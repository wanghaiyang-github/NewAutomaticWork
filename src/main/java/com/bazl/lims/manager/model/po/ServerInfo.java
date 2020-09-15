package com.bazl.lims.manager.model.po;

import com.bazl.lims.web.helper.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author wanghaiyang
 * @date 2020/2/14.
 */
public class ServerInfo {

    /** 主键id */
    private String id;

    /** 中心名称 */
    private String serverName;

    /** 端口 */
    private String port;

    /** 状态，0：开始，1：结束 */
    private String state;

    /** 次数 */
    private String time;

    /** 读取字节数 */
    private String readBufferSize;

    /** 客户端ip */
    private String clientIp;

    /** 客户端端口 */
    private String clientPort;

    /** 设备类型 */
    private String deviceType;

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

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReadBufferSize() {
        return readBufferSize;
    }

    public void setReadBufferSize(String readBufferSize) {
        this.readBufferSize = readBufferSize;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientPort() {
        return clientPort;
    }

    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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
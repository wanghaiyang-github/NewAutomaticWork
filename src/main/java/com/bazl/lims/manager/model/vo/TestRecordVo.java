package com.bazl.lims.manager.model.vo;

import com.bazl.lims.manager.model.po.TestRecord;

import java.io.Serializable;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public class TestRecordVo extends AbstractBaseVO<TestRecord> implements Serializable {

    public TestRecordVo() {
        this.entity = new TestRecord();
    }

    public TestRecordVo(TestRecord entity) {
        this.entity = entity;
    }

    private String extractPlateName;

    private String extractPlateId;

    private String programName;

    private String serverName;

    private String deviceType;

    private String boardNoName;

    private int count;

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

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getBoardNoName() {
        return boardNoName;
    }

    public void setBoardNoName(String boardNoName) {
        this.boardNoName = boardNoName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

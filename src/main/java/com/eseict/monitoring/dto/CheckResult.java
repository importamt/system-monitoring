package com.eseict.monitoring.dto;

import java.sql.Timestamp;

public class CheckResult {
    private String checkId;
    private String sourceId;
    private String targetId;
    private String status;
    Timestamp checkTime;

    public CheckResult(String checkId, String sourceId, String targetId, String status, Timestamp checkTime) {
        this.checkId = checkId;
        this.sourceId = sourceId;
        this.targetId = targetId;
        this.status = status;
        this.checkTime = checkTime;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Timestamp checkTime) {
        this.checkTime = checkTime;
    }
}

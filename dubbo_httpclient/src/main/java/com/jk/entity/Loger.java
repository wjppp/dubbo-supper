package com.jk.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Loger implements Serializable{

    private static final long serialVersionUID = 6960992328940619179L;
    private  String   _id;
    private  Integer  logId;
    private  String   logMsg;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private  Date     createTime;

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String get_id() {
        return _id;
    }

    public Integer getLogId() {
        return logId;
    }

    public String getLogMsg() {
        return logMsg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "Loger{" +
                "_id='" + _id + '\'' +
                ", logId=" + logId +
                ", logMsg='" + logMsg + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
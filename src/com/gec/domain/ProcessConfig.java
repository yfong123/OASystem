package com.gec.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ProcessConfig {
    private String id;

    private String deplomentid;

    private String procdefid;

    private String proccategory;

    private String version;

    private String prockey;

    private String note;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeplomentid() {
        return deplomentid;
    }

    public void setDeplomentid(String deplomentid) {
        this.deplomentid = deplomentid == null ? null : deplomentid.trim();
    }

    public String getProcdefid() {
        return procdefid;
    }

    public void setProcdefid(String procdefid) {
        this.procdefid = procdefid == null ? null : procdefid.trim();
    }

    public String getProccategory() {
        return proccategory;
    }

    public void setProccategory(String proccategory) {
        this.proccategory = proccategory == null ? null : proccategory.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getProckey() {
        return prockey;
    }

    public void setProckey(String prockey) {
        this.prockey = prockey == null ? null : prockey.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}
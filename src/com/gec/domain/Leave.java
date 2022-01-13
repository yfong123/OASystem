package com.gec.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Leave {
    private String procinstid;

    private String procdefid;

    private String taskid;

    private String taskName; 
    
    @JSONField(format="yyyy-MM-dd")
    private Date startdate;

    @JSONField(format="yyyy-MM-dd")
    private Date enddate;

    private String reason;

    private Integer days;

    private String leavetype;

    private String createtime;

    private String finishtime;

    private String initiator;

    private String initiatorName;
    
    private String assignee;

    private String assigneeName;
    
    private String status;

    
    public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getInitiatorName() {
		return initiatorName;
	}

	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

	public String getProcinstid() {
        return procinstid;
    }

    public void setProcinstid(String procinstid) {
        this.procinstid = procinstid == null ? null : procinstid.trim();
    }

    public String getProcdefid() {
        return procdefid;
    }

    public void setProcdefid(String procdefid) {
        this.procdefid = procdefid == null ? null : procdefid.trim();
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid == null ? null : taskid.trim();
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getLeavetype() {
        return leavetype;
    }

    public void setLeavetype(String leavetype) {
        this.leavetype = leavetype == null ? null : leavetype.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime == null ? null : finishtime.trim();
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator == null ? null : initiator.trim();
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee == null ? null : assignee.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	@Override
	public String toString() {
		return "Leave [procinstid=" + procinstid + ", procdefid=" + procdefid + ", taskid=" + taskid + ", taskName="
				+ taskName + ", startdate=" + startdate + ", enddate=" + enddate + ", reason=" + reason + ", days="
				+ days + ", leavetype=" + leavetype + ", createtime=" + createtime + ", finishtime=" + finishtime
				+ ", initiator=" + initiator + ", initiatorName=" + initiatorName + ", assignee=" + assignee
				+ ", assigneeName=" + assigneeName + ", status=" + status + "]";
	}
    
    
}
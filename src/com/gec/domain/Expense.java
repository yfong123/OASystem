package com.gec.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Expense {
    private String procinstid;

    private String procdefid;

    private String taskid;

    private String taskName; 
    
    @JSONField(format="yyyy-MM-dd")
    private Date orderdate;

    @JSONField(format="yyyy-MM-dd")
    private Date expensedate;

    private String orderdesc;

    private Double money;

    private Integer days;

    private String createtime;

    private String finishtime;

    private String initiator;
    
    private String initiatorName;

    private String assignee;
    
    private String assigneeName;

    private String status;

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

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Date getExpensedate() {
        return expensedate;
    }

    public void setExpensedate(Date expensedate) {
        this.expensedate = expensedate;
    }

    public String getOrderdesc() {
        return orderdesc;
    }

    public void setOrderdesc(String orderdesc) {
        this.orderdesc = orderdesc == null ? null : orderdesc.trim();
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
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

	@Override
	public String toString() {
		return "Expense [procinstid=" + procinstid + ", procdefid=" + procdefid + ", taskid=" + taskid + ", orderdate="
				+ orderdate + ", expensedate=" + expensedate + ", orderdesc=" + orderdesc + ", money=" + money
				+ ", days=" + days + ", createtime=" + createtime + ", finishtime=" + finishtime + ", initiator="
				+ initiator + ", assignee=" + assignee + ", status=" + status + "]";
	}
    
}
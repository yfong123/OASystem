package com.gec.domain;

public class Dept {
    private String id;

    private String parentid;

    private String pids;

    private String deptname;

    private String deptdesc;

    private String hassub;

    private String level;

    private String parentName;
    
    
    public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Dept() {
		super();
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids == null ? null : pids.trim();
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    public String getDeptdesc() {
        return deptdesc;
    }

    public void setDeptdesc(String deptdesc) {
        this.deptdesc = deptdesc == null ? null : deptdesc.trim();
    }

    public String getHassub() {
        return hassub;
    }

    public void setHassub(String hassub) {
        this.hassub = hassub == null ? null : hassub.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

	@Override
	public String toString() {
		return "Dept [id=" + id + ", parentid=" + parentid + ", pids=" + pids + ", deptname=" + deptname + ", deptdesc="
				+ deptdesc + ", hassub=" + hassub + ", level=" + level + "]";
	}
    
    
}
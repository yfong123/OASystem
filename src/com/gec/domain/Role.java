package com.gec.domain;

public class Role {
    private String id;

    private String rolename;

    private String roledesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc == null ? null : roledesc.trim();
    }

	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + ", roledesc=" + roledesc + "]";
	}
    
}
package com.cjw.boot.pojo.admin;

import java.io.Serializable;

public class RolePojo implements Serializable {
    private String id;

    private String roleName;

    private String description;

    private String[] premissionS ;

    private String[] userS;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


	public RolePojo(String id, String name) {
		super();
		this.id = id;
		this.roleName = name;
	}

	public RolePojo() {
		super();
	}

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getPremissionS() {
        return premissionS;
    }

    public void setPremissionS(String[] premissionS) {
        this.premissionS = premissionS;
    }

    public String[] getUserS() {
        return userS;
    }

    public void setUserS(String[] userS) {
        this.userS = userS;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
package com.cjw.boot.pojo.admin;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserPojo implements Serializable {
    private String id;

    private String username;

    private String password;

    private String email;

    private String isvalid;

    private String discription;

    private Timestamp lasttime;


    private String roleList;


    public UserPojo() {
    }

    public UserPojo(String username, String password, String email, String isvalid, String discription, String roleList) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isvalid = isvalid;
        this.discription = discription;
        this.roleList = roleList;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public String getDiscription() {
        return discription;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Timestamp getLasttime() {
        return lasttime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setLasttime(Timestamp lasttime) {
        this.lasttime = lasttime;
    }

    public String getRoleList() {
        return roleList;
    }

    public void setRoleList(String roleList) {
        this.roleList = roleList;
    }
}
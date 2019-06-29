package com.cjw.boot.pojo.admin;

import java.util.List;

/**
* Description 树结构类
* @Author junwei
* @Date 9:36 2019/6/12
**/
public class TreePojo {

    private String title;
//    private String href;
//    private Boolean spread;
    private Boolean checked;
//    private Boolean disabled;
    private String parent;
    private String value;

    private List<TreePojo> data;

    public TreePojo() {

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<TreePojo> getData() {
        return data;
    }

    public void setData(List<TreePojo> data) {
        this.data = data;
    }
}

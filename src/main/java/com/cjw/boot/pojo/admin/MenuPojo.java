package com.cjw.boot.pojo.admin;

import java.util.HashMap;
import java.util.List;

public class MenuPojo {

	private String functionCode;
	private String title;
	private String functionParentCode;
	private String icon;
	private String href;
	private String description;
	private int isvalid;
	private int sequence;

	private  String perms;
	private  String permsType;



	private List<MenuPojo> children;

	public MenuPojo() {
		super();
	}

	/**
	 * Description 一级 菜单添加
	 * @param functionCode 权限编码
	 * @param title 权限名称
	 * @param icon 权限图标
	 * @param description 注释
	 * @param isvalid 禁用
	 * @param sequence 排序
	 * @param permsType shiro权限类型
	 * @Author junwei
	 * @Date 11:18 2019/9/17
	 **/
	public MenuPojo(String functionCode, String title, String icon, String description, int isvalid, int sequence,  String permsType) {
		this.functionCode = functionCode;
		this.title = title;
		this.icon = icon;
		this.description = description;
		this.isvalid = isvalid;
		this.sequence = sequence;
		this.permsType = permsType;
	}

	/**
	* Description (二级/三级） 菜单添加
	* @param functionCode 权限编码
	* @param title 权限名称
	 * @param functionParentCode 父级权限编码
	 * @param icon 权限图标
	 * @param href 权限URI指向
	 * @param description 注释
	 * @param isvalid 禁用
	 * @param sequence 排序
	 * @param perms shiro权限
	 * @param permsType shiro权限类型
	* @Author junwei
	* @Date 11:18 2019/9/17
	**/
	public MenuPojo(String functionCode, String title, String functionParentCode, String icon, String href, String description, int isvalid, int sequence, String perms, String permsType) {
		this.functionCode = functionCode;
		this.title = title;
		this.functionParentCode = functionParentCode;
		this.icon = icon;
		this.href = href;
		this.description = description;
		this.isvalid = isvalid;
		this.sequence = sequence;
		this.perms = perms;
		this.permsType = permsType;
	}

	/**
	 * Description 其它功能点 权限
	 * @param functionCode 权限编码
	 * @param title 权限名称
	 * @param functionParentCode 父级权限编码
	 * @param description 注释
	 * @param isvalid 禁用
	 * @param sequence 排序
	 * @param perms shiro权限
	 * @param permsType shiro权限类型
	 * @Author junwei
	 * @Date 11:18 2019/9/17
	 **/
	public MenuPojo(String functionCode, String title, String functionParentCode,  String description, int isvalid, int sequence, String perms, String permsType) {
		this.functionCode = functionCode;
		this.title = title;
		this.functionParentCode = functionParentCode;
		this.description = description;
		this.isvalid = isvalid;
		this.sequence = sequence;
		this.perms = perms;
		this.permsType = permsType;
	}

	public MenuPojo(String functionCode, String title, String functionParentCode,
					String icon, String href, String description, int isvalid,
					int sequence, List<MenuPojo> children) {
		super();
		this.functionCode = functionCode;
		this.title = title;
		this.functionParentCode = functionParentCode;
		this.icon = icon;
		this.href = href;
		this.description = description;
		this.isvalid = isvalid;
		this.sequence = sequence;
		this.children = children;
	}


	/**
	* Description 自定义的权限Map
	* @param map
	* @Author junwei
	* @Date 10:45 2019/6/10
	**/
	public MenuPojo(HashMap<String,Object> map){
		super();
		this.functionCode = map.get("FUNCTION_CODE").toString();
		this.title = map.get("FUNCTION_NAME").toString();
		this.functionParentCode =(map.get("FUNCTION_PARENT_CODE") == null) ? null : map.get("FUNCTION_PARENT_CODE").toString();
		this.icon =(map.get("FUNCTION_ICON") == null) ? null : map.get("FUNCTION_ICON").toString();
		this.href = (map.get("FUNCTION_HREF") == null) ? null : map.get("FUNCTION_HREF").toString();
		this.description =(map.get("DESCRIPTION") == null) ? null : map.get("DESCRIPTION").toString();
		this.isvalid  = Integer.parseInt(map.get("ISVALID").toString());
		this.sequence =  Integer.parseInt(map.get("SEQUENCE").toString());
		this.perms =(map.get("PERMS") == null) ? null : map.get("PERMS").toString();
		this.permsType= (map.get("PERMS_TYPE") == null) ? null : map.get("PERMS_TYPE").toString();
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFunctionParentCode() {
		return functionParentCode;
	}

	public void setFunctionParentCode(String functionParentCode) {
		this.functionParentCode = functionParentCode;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIsValid() {
		return isvalid;
	}

	public void setIsValid(int isvalid) {
		this.isvalid = isvalid;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public List<MenuPojo> getChildren() {
		return children;
	}

	public void setChildren(List<MenuPojo> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "MenuPojo [functionCode=" + functionCode + ", title=" + title
				+ ", functionParentCode=" + functionParentCode + ", icon="
				+ icon + ", href=" + href + ", description=" + description
				+ ", isvalid=" + isvalid + ", sequence=" + sequence
				+ ", children=" + children + "]";
	}


	public String getPerms() {
		return perms;
	}

	public String getPermsType() {
		return permsType;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public void setPermsType(String permsType) {
		this.permsType = permsType;
	}
}

package com.cjw.boot.mapper.admin;


import com.cjw.boot.pojo.admin.RolePojo;
import com.cjw.boot.pojo.admin.TreePojo;

import java.util.List;

/**
 * @ClassName: RoleMapper
 * @author fuce
 * @date 2018年8月25日
 *
 */
public interface RoleMapper {


	/**
	* Description 角色列表
	* @param pojo
	* @Author junwei
	* @Date 9:58 2019/6/11
	**/
	public List<RolePojo> listRole(RolePojo pojo);

	/**
	* Description 添加角色
	* @param pojo
	* @Author junwei
	* @Date 9:59 2019/6/11
	**/
	public Integer addRole(RolePojo pojo);


	/**
	* Description 删除角色
	* @param pojo
	* @Author junwei
	* @Date 9:59 2019/6/11
	**/
	public Integer delRole(RolePojo pojo);


	/**
	* Description 更新角色
	* @param pojo
	* @Author junwei
	* @Date 10:00 2019/6/11
	**/
	public Integer updateRole(RolePojo pojo);



	/**
	* Description 查询角色树形结构
	* @param roleCode
	* @Author junwei
	* @Date 10:10 2019/6/12
	**/
	public  List<TreePojo> listTree(String roleCode);

}

package com.cjw.boot.mapper.admin;

import com.cjw.boot.pojo.admin.MenuPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
	
	/**
	 * 所有查询功能
	 * @return
	 */
	public List<MenuPojo> queryMenuList();
	
	/**
	 * 查询制定用户拥有的功能
	 * @return
	 */
	public List<MenuPojo> queryMenuListCode(@Param("userCode") String userCode);

	
	/**
	* Description 更新权限信息
	* @param menu 权限pojo
	* @Author junwei
	* @Date 14:12 2019/6/10
	**/
	public Integer updatePremission(MenuPojo menu) throws Exception;

	/**
	* Description 删除权限信息
	* @param menu 权限pojo
	* @Author junwei
	* @Date 14:13 2019/6/10
	**/
	public  Integer delPremision(MenuPojo menu)  throws Exception;

	/**
	* Description 添加权限信息
	* @param menu 权限pojo
	* @Author junwei
	* @Date 14:14 2019/6/10
	**/
	public  Integer insertPremission(MenuPojo menu)  throws Exception;

}

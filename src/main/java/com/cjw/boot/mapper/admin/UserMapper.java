package com.cjw.boot.mapper.admin;


import com.cjw.boot.pojo.admin.UserPojo;

import java.util.List;

/**
 * @ClassName: UserMapper
 * @author fuce
 * @date 2018年8月25日
 *
 */
public interface UserMapper {
	/**
	 * 根据用户名字查询用户
	 * @param username
	 * @return
	 */
	public UserPojo queryUserName(String username);


	/**
	* Description 用户列表
	* @param pojo
	* @Author junwei
	* @Date 9:58 2019/6/11
	**/
	public List<UserPojo> listUser(UserPojo pojo);

	/**
	* Description 添加用户
	* @param pojo
	* @Author junwei
	* @Date 9:59 2019/6/11
	**/
	public Integer addUser(UserPojo pojo);


	/**
	* Description 删除用户
	* @param pojo
	* @Author junwei
	* @Date 9:59 2019/6/11
	**/
	public Integer delUser(UserPojo pojo);


	/**
	* Description 更新用户
	* @param pojo
	* @Author junwei
	* @Date 10:00 2019/6/11
	**/
	public Integer updateUser(UserPojo pojo);

}

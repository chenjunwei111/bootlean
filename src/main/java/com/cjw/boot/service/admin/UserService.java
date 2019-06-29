package com.cjw.boot.service.admin;


import com.cjw.boot.mapper.admin.UserMapper;
import com.cjw.boot.pojo.admin.UserPojo;
import com.cjw.boot.service.diy.DiySqlService;
import com.cjw.boot.util.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author fuce
 * @ClassName: UserService
 * @date 2018年8月26日
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    DiySqlService diySqlService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Description 获取所有用户
     *
     * @param pojo 用户对象
     * @Author junwei
     * @Date 14:15 2019/6/10
     **/
    public List<UserPojo> listUser(UserPojo pojo) {
        return userMapper.listUser(pojo);
    }

    /**
     * Description 用户分页
     *
     * @param pojo
     * @param pageNo
     * @param pageSize
     * @Author junwei
     * @Date 10:05 2019/6/11
     **/
    public PageInfo<UserPojo> pageUser(UserPojo pojo, Integer pageNo, Integer pageSize) {
        try {
            PageHelper.startPage(pageNo, pageSize);
            List<UserPojo> list = userMapper.listUser(pojo);
            PageInfo<UserPojo> pageInfo = new PageInfo<>(list);
            return pageInfo;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }


    /**
     * Description 添加用户
     *
     * @param pojo
     * @Author junwei
     * @Date 9:59 2019/6/11
     **/
    @Transactional
    public Integer addUser(UserPojo pojo) {
        try {
            String userId = String.valueOf(System.currentTimeMillis());
            pojo.setId(userId);
            String roleString = pojo.getRoleList();
            pojo.setPassword(MD5Util.encode(pojo.getPassword()));
            String[] roleId = roleString.split(",");

            if (userMapper.addUser(pojo) == 1) {
                for (String role : roleId) {
                    diySqlService.diySqlInsert("insert into T_SYS_ROLE_USER_2 (ID,  SYS_ROLE_ID,SYS_USER_ID)" +
                            "values(" + System.currentTimeMillis() + ",'" + role + "','" + userId + "')");
                }
            }
            return 1;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return 0;
        }


    }


    /**
     * Description 删除用户
     *
     * @param pojo
     * @Author junwei
     * @Date 9:59 2019/6/11
     **/
    @Transactional
    public Integer delUser(UserPojo pojo) {
        try {
            int res1 = userMapper.delUser(pojo);
            int res2 = diySqlService.diySqlDel("delete from T_SYS_role_USER_2 where SYS_USER_ID='" + pojo.getId() + "'");
            return 1;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return 0;
        }
    }


    /**
     * Description 更新用户
     *
     * @param pojo
     * @Author junwei
     * @Date 10:00 2019/6/11
     **/
    @Transactional
    public Integer updateUser(UserPojo pojo) {
        return userMapper.updateUser(pojo);
    }


    /**
     * Description 根据用户查询角色
     *
     * @param
     * @param userId
     * @Author junwei
     * @Date 17:44 2019/6/25
     **/
    public List<LinkedHashMap<String, Object>> getlistRoleUser(String userId) {
        try {
            String sql = "select  r.id,r.role_name,r.DESCRIPTION,ru.SYS_USER_ID from (\n" +
                    "select ID, ROLE_NAME, DESCRIPTION from T_SYS_ROLE_2\n" +
                    ")r left join (select ID, SYS_USER_ID, SYS_ROLE_ID from T_SYS_ROLE_USER_2\n" +
                    "where SYS_USER_ID=" + userId + " ) ru on r.id=ru.SYS_ROLE_ID order by r.id ";
            List<LinkedHashMap<String, Object>> list = diySqlService.diySqlList(sql);
            return list;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }


    /**
     * Description 更新用户角色表
     *
     * @param
     * @param
     * @Author junwei
     * @Date 17:46 2019/6/25
     **/
    @Transactional
    public Integer updateUserRole(Map<String, Object> updateUserRole) {
        try {
            int res=0;
            if (Boolean.parseBoolean(updateUserRole.get("checked").toString())) {
                res=diySqlService.diySqlInsert("insert into T_SYS_ROLE_USER_2 (ID,  SYS_ROLE_ID,SYS_USER_ID)" +
                        "values(" + System.currentTimeMillis() + ",'" + updateUserRole.get("roleId") + "','" + updateUserRole.get("userId") + "')");
            } else {
                res=diySqlService.diySqlDel("delete from T_SYS_ROLE_USER_2 where SYS_USER_ID='" + updateUserRole.get("userId") + "' and  SYS_ROLE_ID='" + updateUserRole.get("roleId") + "'");
            }
            return res;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return 0;
        }
    }
}

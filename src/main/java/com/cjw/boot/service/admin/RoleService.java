package com.cjw.boot.service.admin;


import com.cjw.boot.mapper.admin.RoleMapper;
import com.cjw.boot.pojo.admin.RolePojo;
import com.cjw.boot.pojo.admin.TreePojo;
import com.cjw.boot.service.diy.DiySqlService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 系统角色
 *
 * @author fuce
 * @ClassName: RoleService
 * @date 2018年8月26日
 */
@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    DiySqlService diySqlService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Description 获取所有角色
     *
     * @param pojo 角色对象
     * @Author junwei
     * @Date 14:15 2019/6/10
     **/
    public List<RolePojo> listRole(RolePojo pojo) {
        return roleMapper.listRole(pojo);
    }

    /**
     * Description 角色分页
     *
     * @param pojo
     * @param pageNo
     * @param pageSize
     * @Author junwei
     * @Date 10:05 2019/6/11
     **/
    public PageInfo<RolePojo> pageRole(RolePojo pojo, Integer pageNo, Integer pageSize) {
        try {
            PageHelper.startPage(pageNo, pageSize);
            List<RolePojo> list = roleMapper.listRole(pojo);
            PageInfo<RolePojo> pageInfo = new PageInfo<>(list);
            return pageInfo;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }


    /**
     * Description 添加角色
     *
     * @param pojo
     * @Author junwei
     * @Date 9:59 2019/6/11
     **/
    @Transactional
    public Integer addRole(RolePojo pojo) {
        try {
            String roleId = String.valueOf(System.currentTimeMillis());
            pojo.setId(roleId);
            String pr[] = pojo.getPremissionS();
            if (roleMapper.addRole(pojo) == 1) {
                for (String prCode : pr) {
                    diySqlService.diySqlInsert("insert into T_SYS_PERMISSION_ROLE_2 (ID, ROLE_CODE, PERMISSION_CODE) " +
                            "values(" + System.currentTimeMillis() + ",'" + roleId + "','" + prCode + "')");
                }
            }
            return 1;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return 0;
        }
    }

    /**
     * Description 更新角色权限
     *
     * @param pojo
     * @Author junwei
     * @Date 9:59 2019/6/11
     **/
    @Transactional
    public Integer editRolePremission(RolePojo pojo) {
        try {
            String roleId = pojo.getId();
            int res1 = diySqlService.diySqlDel("delete from T_SYS_PERMISSION_ROLE_2 where ROLE_CODE='" + roleId + "'");

            String pr[] = pojo.getPremissionS();
//            if (roleMapper.addRole(pojo) == 1) {
            for (String prCode : pr) {
                int res2 = diySqlService.diySqlInsert("insert into T_SYS_PERMISSION_ROLE_2 (ID, ROLE_CODE, PERMISSION_CODE) " +
                        "values(" + System.currentTimeMillis() + ",'" + roleId + "','" + prCode + "')");
            }
//            }
            return 1;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return 0;
        }
    }


    /**
     * Description 删除角色
     *
     * @param pojo
     * @Author junwei
     * @Date 9:59 2019/6/11
     **/
    @Transactional
    public Integer delRole(RolePojo pojo) {

        try {
            String roleId = pojo.getId();
            int res1 = roleMapper.delRole(pojo);
            int res2 = diySqlService.diySqlDel("delete from T_SYS_PERMISSION_ROLE_2 where ROLE_CODE='" + roleId + "'");
            int res3 = diySqlService.diySqlDel("delete from T_SYS_role_USER_2 where SYS_ROLE_ID='" + roleId + "'");
            return 1;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }

    }


    /**
     * Description 更新角色
     *
     * @param pojo
     * @Author junwei
     * @Date 10:00 2019/6/11
     **/
    public Integer updateRole(RolePojo pojo) {
        return roleMapper.updateRole(pojo);
    }


    /**
     * Description 树形结构数据
     *
     * @param roleCode
     * @Author junwei
     * @Date 10:12 2019/6/12
     **/
    public List<TreePojo> listTree(String roleCode) {
        return roleMapper.listTree(roleCode);
    }


    /**
     * Description 添加角色用户表
     *
     * @param pojo
     * @Author junwei
     * @Date 18:10 2019/6/12
     **/
    @Transactional
    public Integer insertListRoleUser(RolePojo pojo) {
        try {
            String roleId = pojo.getId();
            String[] userS = pojo.getUserS();
            int res1 = diySqlService.diySqlDel("delete from T_SYS_ROLE_USER_2 where SYS_ROLE_ID ='" + roleId + "'");
//            if (res1 == 1) {
            for (String userCode : userS) {
                int res2 = diySqlService.diySqlInsert("insert into T_SYS_ROLE_USER_2 (ID, SYS_USER_ID, SYS_ROLE_ID) " +
                        "values('" + System.currentTimeMillis() + "','" + userCode + "','" + roleId + "')");
            }
//            }
            return 1;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return 0;
        }
    }

    /**
     * Description 根据角色ID查用户列表
     *
     * @param
     * @param
     * @Author junwei
     * @Date 17:54 2019/6/25
     **/
    public List<LinkedHashMap<String, Object>> getListRoleUser(String roleId) {
        try {
            String sql = "select u.ID as user_code,u.EMAIL, u.USERNAME as user_name, u.DISCRIPTION ,ISVALID ,ru.SYS_ROLE_ID ,\n" +
                    "nvl2(ru.SYS_ROLE_ID,1,0) LAY_CHECKED from T_SYS_USER_2 u\n" +
                    "left join T_SYS_ROLE_USER_2 ru on u.id=ru.SYS_USER_ID and ru.SYS_ROLE_ID='" + roleId + "' \n" +
                    "order by u.ID ";
            List<LinkedHashMap<String, Object>> list = diySqlService.diySqlList(sql);
            return  list;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }

    }

}

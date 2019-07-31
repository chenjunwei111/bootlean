package com.cjw.boot.service.admin;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cjw.boot.mapper.admin.MenuMapper;
import com.cjw.boot.pojo.admin.MenuPojo;
import com.cjw.boot.service.diy.DiySqlService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
* Description 权限管理
* @Author junwei
* @Date 9:45 2019/7/31
**/
@Service
public class MenuService {

    @Resource
    MenuMapper mapper;

    @Autowired
    DiySqlService diySqlService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Description 根据用户获取菜单LIST
     * @param userCode 用户编码
     * @Author junwei
     * @Date 14:15 2019/6/10
     **/
    public List<MenuPojo> queryMenuListCode(String userCode) {
        return mapper.queryMenuListCode(userCode);
    }

    /**
     * Description 更新权限
     *
     * @param pojo
     * @Author junwei
     * @Date 14:15 2019/6/10
     **/
    public Integer updatePremission(MenuPojo pojo) throws Exception {
        return mapper.updatePremission(pojo);
    }

    /**
     * Description 删除权限
     *
     * @param pojo
     * @Author junwei
     * @Date 14:15 2019/6/10
     **/
    @Transactional
    public Integer delPremission(MenuPojo pojo) throws Exception {
        try {
            int res1 = mapper.delPremision(pojo);
            int res = diySqlService.diySqlDel("delete from T_SYS_PERMISSION_ROLE_2 where PERMISSION_CODE='" + pojo.getFunctionCode() + "'");
            return 1;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return 0;
        }

    }

    /**
     * Description 插入权限
     *
     * @param pojo
     * @Author junwei
     * @Date 14:15 2019/6/10
     **/
    public Integer insertPremission(MenuPojo pojo) throws Exception {
        return mapper.insertPremission(pojo);
    }


    /**
     * Description 权限集合查询
     * @param pojo  JSON字符串
     * @param page
     * @param limit
     * @Author junwei
     * @Date 17:40 2019/6/25
     **/
    public PageInfo<LinkedHashMap<String, Object>> getPremissionList(String pojo, Integer page, Integer limit) {
        try {
            JSONObject pojo2 = JSON.parseObject(pojo);
            String preTypeSql = "";
            String preTypeChileSql = "";
            if (pojo != null) {
                if (pojo2.get("preType") != null && !"all".equals(pojo2.get("preType"))) {
                    if ("other".equals(pojo2.get("preType"))) {
                        preTypeSql = " and PERMS_TYPE not in ('edit','add','view','list','del','select') ";
                    } else {
                        preTypeSql = " and PERMS_TYPE='" + pojo2.get("preType").toString() + "' ";
                    }
                }
                if (pojo2.get("preTypeChild") != null && !"all".equals(pojo2.get("preTypeChild"))) {
                    if ("one".equals(pojo2.get("preTypeChild"))) {
                        preTypeChileSql = " and FUNCTION_PARENT_CODE is null ";
                    } else if ("two".equals(pojo2.get("preTypeChild"))) {
                        preTypeChileSql = " and FUNCTION_PARENT_CODE is not null  and INSTR(FUNCTION_CODE,'-C')<=0 ";
                    } else {//三级菜单
                        preTypeChileSql = " and FUNCTION_PARENT_CODE is not null  and INSTR(FUNCTION_CODE,'-C')>0 ";
                    }
                }
            }

            StringBuffer dySql = new StringBuffer();
            dySql.append(" select FUNCTION_CODE, FUNCTION_NAME, FUNCTION_PARENT_CODE, FUNCTION_ICON," +
                    " FUNCTION_HREF, ISVALID, SEQUENCE, DESCRIPTION, PERMS, PERMS_TYPE from T_SYS_PREMISSION_2 where 1=1 ");

            dySql.append(preTypeSql);
            dySql.append(preTypeChileSql);
            dySql.append("order by SEQUENCE ");

            PageInfo<LinkedHashMap<String, Object>> perList = diySqlService.diySqPage(dySql.toString(), page, limit);
            return perList;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }

    }

}

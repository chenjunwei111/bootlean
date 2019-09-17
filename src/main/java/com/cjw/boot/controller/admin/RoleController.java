package com.cjw.boot.controller.admin;


import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.cjw.boot.common.base.BaseController;
import com.cjw.boot.common.log.Log;
import com.cjw.boot.pojo.admin.RolePojo;
import com.cjw.boot.pojo.admin.TreePojo;
import com.cjw.boot.service.admin.RoleService;
import com.cjw.boot.service.diy.DiySqlService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
* Description 角色管理
* @Author junwei
* @Date 9:46 2019/7/31
**/
@Controller
@RequestMapping("RoleController")
@Api(value = "角色管理")
public class RoleController extends BaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    public String prefix = "admin2/role";

    @Autowired
    RoleService roleService;

    @Autowired
    DiySqlService diySqlService;

/*
    @GetMapping("view")
    @RequiresPermissions("system:role:view")
    public String view1(Model model) {
        return prefix + "/roleView";
    }
*/


    /**
    * Description 跳转到添加用户界面
    * @param id 角色ID
    * @Author junwei
    * @Date 9:58 2019/6/13
    **/
    @GetMapping("roleAddUser")
    public String view2(Model model,String id) {
        JSONObject xh=new JSONObject();
        xh.put("id", id);
        model.addAttribute("moRes",xh.toString());
        return prefix + "/roleAddUser";
    }
//

    /**
    * Description 角色集合查询
    * @param page
     * @param limit
    * @Author junwei
    * @Date 17:29 2019/6/11
    **/
    @Log(title = "角色集合查询", action = "角色管理")
    @GetMapping("listRole")
	@RequiresPermissions("system:role:list")
    @ResponseBody
    public Map<String, Object> list(String pojo,Integer page, Integer limit) {
        try {
            RolePojo pojo2= JSON.parseObject(pojo,RolePojo.class);
            PageInfo<RolePojo> pageInfo = roleService.pageRole(pojo2, page, limit);
            return resSuccessMap(pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return resFailMap(e.getCause().toString());
        }
    }

    /**
    * Description 插入角色用户表
    * @param pojo
    * @Author junwei
    * @Date 17:29 2019/6/11
    **/
    @Log(title = "插入角色用户表", action = "角色管理")
    @PostMapping("insertListRoleUser")
    @RequiresPermissions("system:role:editUser")
    @ResponseBody
    public Map<String,Object> insertListRoleUser(@RequestBody RolePojo pojo) {
        Map<String,Object> map=new HashMap<>();
        try {

            Integer res=roleService.insertListRoleUser(pojo);
            if(res==1){
                map.put("msg","更新用户成功");
            }else{
                map.put("msg","更新用户失败");
            }
            return map;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            map.put("msg","更新用户失败");
            map.put("error",e.getMessage());
            return map;
        }
    }


    /**
    * Description 根据角色ID查用户列表
    * @param roleId
    * @Author junwei
    * @Date 10:01 2019/6/13
    **/
    @GetMapping("listRoleUser")
    @ResponseBody
    public List<LinkedHashMap<String, Object>> listRoleUser(String roleId) {
        try {
            List<LinkedHashMap<String, Object>> list = roleService.getListRoleUser(roleId);
            return list;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }



    /**
    * Description 新增角色
    * @param role
    * @Author junwei
    * @Date 17:32 2019/6/11
    **/
    @Log(title = "角色添加", action = "角色管理")
    @PostMapping("/addRole")
    @RequiresPermissions("system:role:add")
    @ResponseBody
    public Map<String, Object> add(@RequestBody RolePojo role) {
        Map<String, Object> map =new HashMap<>();
        try {
            int res=roleService.addRole(role);
            if(res==1){
                map.put("msg","新增角色成功");
            }else{
                map.put("msg","新增角色失败");
            }
            return map;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            map.put("msg","新增角色失败");
            return map;
        }

    }

    /**
    * Description 删除角色
    * @param pojo
    * @Author junwei
    * @Date 17:55 2019/6/11
    **/
    @Log(title = "角色删除", action = "角色管理")
    @PostMapping("delRole")
    @ResponseBody
    @RequiresPermissions("system:role:del")
    public Map<String, Object> del(@RequestBody RolePojo pojo) {
        Map<String, Object> maps = new HashMap<>();
        try {
        int res=roleService.delRole(pojo);
            if(res==0){
                maps.put("msg","删除角色失败");
            }else{
                maps.put("msg","删除角色成功");
            }
            return maps;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            maps.put("msg","删除角色失败");
            maps.put("error", e.getMessage());
            return maps;
        }

    }


    /**
    * Description 修改角色信息
    * @param pojo
    * @Author junwei
    * @Date 17:55 2019/6/11
    **/
    @Log(title = "角色修改", action = "角色管理")
    @PostMapping("editRoleInfo")
    @ResponseBody
    @RequiresPermissions("system:role:edit")
    public Map<String, Object> edit(@RequestBody RolePojo pojo) {
        Map<String, Object> maps = new HashMap<>();
        try {
            int res=roleService.updateRole(pojo);
            if(res==0){
                maps.put("msg","角色修改失败");
            }else{
                maps.put("msg","角色修改成功");
            }
            return maps;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            maps.put("msg","角色修改失败");
            maps.put("error", e.getMessage());
            return maps;
        }

    }


    /**
    * Description  获取树
    * @param roleCode
    * @Author junwei
    * @Date 18:05 2019/6/12
    **/
    @GetMapping(value = "/getTree")
    @ResponseBody
    public List<TreePojo> getMuen(String roleCode) {
        try {

            List<TreePojo> treeMenu=roleService.listTree(roleCode);
            List<TreePojo> treeList = new ArrayList<>();
            // 先找到所有的一级菜单

            for (int i = 0; i < treeMenu.size(); i++) {
                // 一级菜单没有parentId
                if (StringUtils.isBlank(treeMenu.get(i).getParent())) {
                    treeList.add(treeMenu.get(i));
                }
            }
            // 为一级菜单设置子菜单，getChild是递归调用的
            for (TreePojo menu : treeList) {
                menu.setData(getChild(menu.getValue(), treeMenu));
            }
            Map<String, Object> jsonMap = new HashMap<>();
//            jsonMap.put("menu", treeList);
            return treeList;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }


    /**
     * 递归查找子菜单
     *
     * @param id
     *            当前菜单id
     * @param rootMenu
     *            要查找的列表
     * @return
     */
    private List<TreePojo> getChild(String id, List<TreePojo> rootMenu) {
        // 子菜单
        List<TreePojo> childList = new ArrayList<>();
        for (TreePojo menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (StringUtils.isNotBlank(menu.getParent())) {
                if (menu.getParent().equals(id)) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (TreePojo menu : childList) {// 没有url子菜单还有子菜单
//            if (StringUtils.isBlank(menu.getHref())) {
                // 递归
                menu.setData(getChild(menu.getValue(), rootMenu));
//            }
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }


    /**
     * Description  修改权限
     * @param pojo
     * @Author junwei
     * @Date 18:05 2019/6/12
     **/
    @Log(title = "角色修改权限", action = "角色管理")
    @PostMapping(value = "/editRolePremission")
    @RequiresPermissions("system:role:editPre")
    @ResponseBody
    public Map<String ,Object> editRolePremission(@RequestBody RolePojo pojo) {
        Map<String ,Object> maps=new HashMap<>();
        try {
            Integer res=roleService.editRolePremission(pojo);
            if(res==1){
                maps.put("msg","更新权限成功");
            }else{
                maps.put("msg","更新权限失败");
            }
            return maps;
        } catch (Exception e) {
            logger.error("错误：",e);
            maps.put("msg","更新权限失败");
            return maps;
        }
    }


}

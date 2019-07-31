package com.cjw.boot.controller.admin;


import com.alibaba.fastjson.JSON;
import com.cjw.boot.common.base.BaseController;
import com.cjw.boot.common.log.Log;
import com.cjw.boot.pojo.admin.UserPojo;
import com.cjw.boot.service.admin.UserService;
import com.cjw.boot.service.diy.DiySqlService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Description 用户管理
* @Author junwei
* @Date 10:33 2019/7/31
**/
@Controller
@RequestMapping("UserController")
@Api(value = "用户管理")
public class UserController extends BaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    public String prefix = "admin2/user";

    @Autowired
    UserService userService;

    @Autowired
    DiySqlService diySqlService;

    @GetMapping("view")
    @RequiresPermissions("system:user:view")
    public String view(Model model) {
        return prefix + "/userView";
    }

//

    /**
    * Description 用户集合查询
    * @param page
     * @param limit
    * @Author junwei
    * @Date 17:29 2019/6/11
    **/
    @Log(title = "用户集合查询", action = "用户管理")
    @GetMapping("listUser")
	@RequiresPermissions("system:user:list")
    @ResponseBody
    public Map<String, Object> list(String pojo,Integer page, Integer limit) {
        Map<String, Object> maps = new HashMap<>();
        try {
            UserPojo pojo2= JSON.parseObject(pojo,UserPojo.class);
            PageInfo<UserPojo> pageInfo = userService.pageUser(pojo2, page, limit);
            return resSuccessMap(pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception e) {
            logger.error("错误信息：", e);
            maps.put("code", 200);// 0成功/200失败
            maps.put("msg", "");// 返回信息
            maps.put("count", 0);// 数据总量
            maps.put("data", null);// 返回数据的list集合
            return maps;
        }
    }

    /**
    * Description 根据用户查询角色
    * @param userId
    * @Author junwei
    * @Date 17:29 2019/6/11
    **/
    @GetMapping("listRoleUser")
    @ResponseBody
    public List<LinkedHashMap<String, Object>> listRoleUser(String userId) {
        try {
            List<LinkedHashMap<String, Object>> list = userService.getlistRoleUser(userId);
            return list;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }

    /**
    * Description 更新用户角色表
    * @param updateUserRole
    * @Author junwei
    * @Date 17:30 2019/6/11
    **/
    @Log(title = "修改用户角色", action = "用户管理")
    @PostMapping("updateUserRole")
	@RequiresPermissions("system:user:editUR")
    @ResponseBody
    public Map<String, String> updateUserRole(@RequestBody Map<String, Object> updateUserRole) {
        Map<String, String> map = new HashMap<>();
        try {
            int res=userService.updateUserRole(updateUserRole);
            if(res==0){
                map.put("msg", "修改失败");
            }else{
                map.put("msg", "修改成功");
            }
            return map;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            map.put("msg", "修改失败");
            map.put("error", e.getMessage());
            return map;
        }
    }


    /**
    * Description 新增用户
    * @param user
    * @Author junwei
    * @Date 17:32 2019/6/11
    **/
    @Log(title = "新增用户", action = "用户管理")
    @PostMapping("/addUser")
    @RequiresPermissions("system:user:add")
    @ResponseBody
    public Map<String, Object> add(@RequestBody UserPojo user) {
        Map<String, Object> map =new HashMap<>();
        try {
            int res=userService.addUser(user);
            if(res==1){
                map.put("msg","新增用户成功");
            }else{
                map.put("msg","新增用户失败");
            }
            return map;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            map.put("msg","新增用户失败");
            return map;
        }

    }

    /**
    * Description 删除用户
    * @param pojo
    * @Author junwei
    * @Date 17:55 2019/6/11
    **/
    @Log(title = "删除用户", action = "用户管理")
    @PostMapping("delUser")
    @ResponseBody
    @RequiresPermissions("system:user:del")
    public Map<String, Object> del(@RequestBody UserPojo pojo) {
        Map<String, Object> maps = new HashMap<>();
        try {
        int res=userService.delUser(pojo);
            if(res==0){
                maps.put("msg","删除用户失败");
            }else{
                maps.put("msg","删除用户成功");
            }
            return maps;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            maps.put("msg","删除用户失败");
            maps.put("error", e.getMessage());
            return maps;
        }

    }


    /**
    * Description 修改用户信息
    * @param pojo
    * @Author junwei
    * @Date 17:55 2019/6/11
    **/
    @Log(title = " 修改用户信息", action = "用户管理")
    @PostMapping("editUserInfo")
    @ResponseBody
    @RequiresPermissions("system:user:edit")
    public Map<String, Object> edit(@RequestBody UserPojo pojo) {
        Map<String, Object> maps = new HashMap<>();
        try {
            int res=userService.updateUser(pojo);
            if(res==0){
                maps.put("msg","用户修改失败");
            }else{
                maps.put("msg","用户修改成功");
            }
            return maps;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            maps.put("msg","用户修改失败");
            maps.put("error", e.getMessage());
            return maps;
        }

    }


}

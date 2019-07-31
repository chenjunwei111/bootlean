package com.cjw.boot.controller.admin;

import com.cjw.boot.common.base.BaseController;
import com.cjw.boot.common.log.Log;
import com.cjw.boot.pojo.admin.MenuPojo;
import com.cjw.boot.service.admin.MenuService;
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
import java.util.Map;


/**
 * Description  权限管理
 * @Author junwei
 * @Date 10:51 2019/6/4
 **/
@Api("权限管理")
@Controller
@RequestMapping("PremissionController")
public class PremissionController extends BaseController {

    //跳转页面参数
    private String prefix = "admin2/premission/";

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    DiySqlService diySqlService;

    @Autowired
    MenuService premisionService;

    @GetMapping("view")
    @RequiresPermissions("system:premission:view")
    public String view1(Model model) {
        return prefix + "premissionView";
    }

    @Log(title = "权限集合查询", action = "权限管理")
    @GetMapping("listPremission")
    @RequiresPermissions("system:premission:list")
    @ResponseBody
    public Map<String, Object> list(String pojo, Integer page, Integer limit) {

        try{
            PageInfo<LinkedHashMap<String, Object>> pageInfo =premisionService.getPremissionList(pojo,page,limit);
            return resSuccessMap(pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return resFailMap(e.getCause().toString());
        }

    }


    @Log(title = "权限修改", action = "权限管理")
    @PostMapping("editPreInfo")
    @ResponseBody
    @RequiresPermissions("system:premission:edit")
    public Map<String, Object> edit(@RequestBody HashMap<String, Object> map) {
           Map<String, Object> maps = new HashMap<>();
        try {
            MenuPojo pre = new MenuPojo(map);
            int res=premisionService.updatePremission(pre);
            if(res==0){
                maps.put("msg","权限修改失败");
            }else{
                maps.put("msg","权限修改成功");
            }
            return maps;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            maps.put("msg","权限修改失败");
            maps.put("error", e.getMessage());
            return maps;
        }

    }

    /**
    * Description 新增权限
    * @param map
    * @Author junwei
    * @Date 16:29 2019/6/10
    **/
    @Log(title = "权限添加", action = "权限管理")
    @PostMapping("addPremission")
    @ResponseBody
    @RequiresPermissions("system:premission:add")
    public Map<String, Object> add(@RequestBody HashMap<String, Object> map) {
        Map<String, Object> maps = new HashMap<>();
        try {
            MenuPojo pre = new MenuPojo(map);
            int res=premisionService.insertPremission(pre);

            if(res==0){
                maps.put("msg","新增权限失败");
            }else{
                maps.put("msg","新增权限成功");
            }
            return maps;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            maps.put("msg","新增权限失败");
            maps.put("error", e);
            return maps;
        }

    }


    /**
     * Description 删除权限
     * @param map
     * @Author junwei
     * @Date 16:29 2019/6/10
     **/
    @Log(title = "权限删除", action = "权限管理")
    @PostMapping("delPremission")
    @ResponseBody
    @RequiresPermissions("system:premission:edit")
    public Map<String, Object> del(@RequestBody HashMap<String, Object> map) {
        Map<String, Object> maps = new HashMap<>();
        try {
            MenuPojo pre = new MenuPojo(map);
            int res=premisionService.delPremission(pre);

            if(res==0){
                maps.put("msg","删除权限失败");
            }else{
                maps.put("msg","删除修改成功");
            }
            return maps;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            maps.put("msg","删除权限失败");
            maps.put("error", e);
            return maps;
        }

    }


}

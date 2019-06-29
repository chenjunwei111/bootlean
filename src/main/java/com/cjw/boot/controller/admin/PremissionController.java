package com.cjw.boot.controller.admin;

import com.cjw.boot.common.base.BaseController;
import com.cjw.boot.common.log.Log;
import com.cjw.boot.pojo.admin.MenuPojo;
import com.cjw.boot.service.admin.Menuservice;
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
 * Description  权限分配控制
 *
 * @Author junwei
 * @Date 10:51 2019/6/4
 **/
@Api("权限")
@Controller
@RequestMapping("PremissionController")
public class PremissionController extends BaseController {

    //跳转页面参数
    private String prefix = "admin2/premission/";

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    DiySqlService diySqlService;

    @Autowired
    Menuservice premisionService;

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

        Map<String, Object> maps=new HashMap<>();
        try{
            PageInfo<LinkedHashMap<String, Object>> perList =premisionService.getPremissionList(pojo,page,limit);
            maps.put("code", 0);// 0成功/200失败
            maps.put("msg", "");// 返回信息
            maps.put("count", perList.getTotal());// 数据总量
            maps.put("data", perList.getList());// 返回数据的list集合
            return maps;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            maps.put("code", 0);// 0成功/200失败
            maps.put("msg", "");// 返回信息
            maps.put("count", 0);// 数据总量
            maps.put("data", null);// 返回数据的list集合
            return maps;
        }

    }


//    @GetMapping("viewChild")
//    @RequiresPermissions("system:premission:viewChild")
//    public String view2(Model model) {
//        return prefix + "premissionViewChild";
//    }

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

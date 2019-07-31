package com.cjw.boot.controller.admin;


import com.cjw.boot.common.base.BaseController;
import com.cjw.boot.common.log.Log;
import com.cjw.boot.service.admin.LogService;
import com.cjw.boot.service.diy.DiySqlService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

/**
* Description 日志管理
* @Author junwei
* @Date 9:37 2019/7/31
**/
@Controller
@RequestMapping("LogController")
@Api(value = "日志管理")
public class LogController extends BaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    public String prefix = "admin2/log";



    @Autowired
    DiySqlService diySqlService;

    @Autowired
    LogService logservice;

    @GetMapping("view")
    @RequiresPermissions("system:log:view")
    public String view(Model model) {
        return prefix + "/logView";
    }


    /**
    * Description 日志集合查询
    * @param page
     * @param limit
    * @Author junwei
    * @Date 17:29 2019/6/11
    **/
    @Log(title = "日志集合查询", action = "日志管理")
    @GetMapping("listLog")
	@RequiresPermissions("system:log:list")
    @ResponseBody
    public Map<String, Object> list(Integer page, Integer limit) {
        try {
            PageInfo<LinkedHashMap<String, Object>> pageInfo=logservice.getLogList(page,limit);
            return resSuccessMap(pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return resFailMap(e.getCause().toString());
        }
    }


    /**
     * Description 日志用户使用统计
     * @param page
     * @param limit
     * @Author junwei
     * @Date 17:29 2019/6/11
     **/
    @GetMapping("listLog2")
    @ResponseBody
    public Map<String, Object> list2(Integer page, Integer limit) {
        try {
            PageInfo<LinkedHashMap<String, Object>> pageInfo=logservice.getUserCount(page,limit);
            return resSuccessMap(pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return resFailMap(e.getCause().toString());
        }
    }



    /**
     * Description 日志功能使用统计
     * @param page
     * @param limit
     * @Author junwei
     * @Date 17:29 2019/6/11
     **/
    @GetMapping("listLog3")
    @ResponseBody
    public Map<String, Object> list3(Integer page, Integer limit) {
        try {
            PageInfo<LinkedHashMap<String, Object>> pageInfo=logservice.getFunCount(page,limit);
            return resSuccessMap(pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return resFailMap(e.getCause().toString());
        }
    }
}

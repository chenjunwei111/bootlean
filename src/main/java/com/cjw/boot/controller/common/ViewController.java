package com.cjw.boot.controller.common;

import com.cjw.boot.pojo.admin.MenuPojo;
import com.cjw.boot.service.admin.MenuService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * Description 视图控制器
 *
 * @Author junwei
 * @Date 16:49 2019/9/16
 **/
@Controller
@RequestMapping("viewController")
@Api("视图控制器")
public class ViewController implements ServletContextAware {


    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    MenuService premisionService;

    @Override
    public void setServletContext(ServletContext servletContext) {

        try {
            String longCode = premisionService.getFunctionLongCode();

            //一级目录添加
            addOneMenu(longCode);
            //二级目录添加
            addTwoMenu(longCode);
            //三级目录添加
            addThreeMenu(longCode);

            //其它增删查改
            addOtherPre(longCode);

        } catch (Exception e) {
            logger.error("错误信息：", e);
        }
    }


    /**
    * Description 添加一级目录
    * @Author junwei
    * @Date 12:43 2019/9/17
    **/
    public void addOneMenu(String longCode) {
        try {
            if(longCode.indexOf("A1")<0){
                MenuPojo pre = new MenuPojo("A1","后台管理","icon-ganzhijiankong","一级目录",1,1000,"view");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A2")<0){
                MenuPojo pre = new MenuPojo("A2","模版","icon-ganzhijiankong","一级目录",1,3000,"view");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A3")<0){
                MenuPojo pre = new MenuPojo("A3","基础功能","icon-ganzhijiankong","一级目录",1,2000,"view");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A4")<0){
                MenuPojo pre = new MenuPojo("A4","测试","icon-yonghuganzhi","一级目录",1,4000,"view");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }
        } catch (Exception e) {
            logger.error("错误信息：", e);
        }
    }

    /**
     * Description 添加二级目录
     * @Author junwei
     * @Date 12:43 2019/9/17
     **/
    public void addTwoMenu(String longCode) {
        try {
            if(longCode.indexOf("A1-B1")<0){
                MenuPojo pre = new MenuPojo("A1-B1","权限管理","A1","icon-ganzhijiankong","viewController/premissionView","二级目录",1,1100,"system:premission:view","view");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A1-B2")<0){
                MenuPojo pre = new MenuPojo("A1-B2","用户管理","A1","icon-yonghuganzhi","viewController/userView","二级目录",1,1200,"system:user:view","view");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A1-B3")<0){
                MenuPojo pre = new MenuPojo("A1-B3","角色管理","A1","icon-ganzhijiankong","viewController/roleView","二级目录",1,1300,"system:role:view","view");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A1-B4")<0){
                MenuPojo pre = new MenuPojo("A1-B4","日志管理","A1","icon-yonghuganzhi","viewController/logView","二级目录",1,1400,"system:log:view","view");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A1-B5")<0){
                MenuPojo pre = new MenuPojo("A1-B5","druid监控","A1","icon-ganzhijiankong","/boot/druid","线程池的管理",1,1500,null,"view");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A2-B1")<0){
                MenuPojo pre = new MenuPojo("A2-B1","常用模版","A2","icon-yonghuganzhi","viewController/usuallyDemo","二级工具",1,2100,null,"view");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A2-B2")<0){
                MenuPojo pre = new MenuPojo("A2-B2","JS模版","A2","icon-yonghuganzhi","viewController/jsView","二级目录",1,2200,null,"view");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A3-B1")<0){
                MenuPojo pre = new MenuPojo("A3-B1","图片管理","A3","icon-ganzhijiankong","viewController/fileView","二级目录",1,3100,"system:file:view","view");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A3-B2")<0){
                MenuPojo pre = new MenuPojo("A3-B2","数据处理","A3","icon-yonghuganzhi","viewController/dataView1","二级目录",1,3200,"system:data:view","view");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A3-B3")<0){
                MenuPojo pre = new MenuPojo("A3-B3","地图呈现","A3","icon-ganzhijiankong",null,"二级目录",1,3300,null,"view");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A4-B1")<0){
                MenuPojo pre = new MenuPojo("A4-B1","Swagger接口测试","A4","icon-yonghuganzhi","viewController/swggerView","二级目录",1,5100,null,"view");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

        } catch (Exception e) {
            logger.error("错误信息：", e);
        }
    }


    /**
     * Description 添加三级目录
     * @Author junwei
     * @Date 12:43 2019/9/17
     **/
    public void addThreeMenu(String longCode) {
        try {
            if(longCode.indexOf("A3-B3-C1")<0){
                MenuPojo pre = new MenuPojo("A3-B3-C1","地图1","A3-B3","icon-yonghuganzhi","viewController/gisView1","三级目录",1,3310,"system:gis:view1","view");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A3-B3-C2")<0){
                MenuPojo pre = new MenuPojo("A3-B3-C2","地图2","A3-B3","icon-yonghuganzhi","viewController/gisView2","三级目录",1,3320,"system:gis:view2","view");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }



        } catch (Exception e) {
            logger.error("错误信息：", e);
        }
    }


    /**
     * Description 其它增删查改
     * @Author junwei
     * @Date 12:43 2019/9/17
     **/
    public void addOtherPre(String longCode) {
        try {
            if(longCode.indexOf("A3-B1-list")<0){
                MenuPojo pre = new MenuPojo("A3-B1-list","图片列表","A3-B1",null,null,"文件列表",1,3101,"system:file:list","list");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A1-B3-add")<0){
                MenuPojo pre = new MenuPojo("A1-B3-add","角色添加","A1-B3",null,null,null,1,1302,"system:role:add","add");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A1-B3-del")<0){
                MenuPojo pre = new MenuPojo("A1-B3-del","角色删除","A1-B3",null,null,null,1,1303,"system:role:del","del");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A1-B3-edit")<0){
                MenuPojo pre = new MenuPojo("A1-B3-edit","角色更新","A1-B3",null,null,null,1,1304,"system:role:edit","edit");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }
            if(longCode.indexOf("A1-B3-editPre")<0){
                MenuPojo pre = new MenuPojo("A1-B3-editPre","角色权限修改","A1-B3",null,null,null,1,1305,"system:role:editPre","edit");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }
            if(longCode.indexOf("A1-B3-editUser")<0){
                MenuPojo pre = new MenuPojo("A1-B3-editUser","角色用户修改","A1-B3",null,null,null,1,1306,"system:role:editUser","edit");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A1-B1-edit")<0){
                MenuPojo pre = new MenuPojo("A1-B1-edit","权限修改","A1-B1",null,null,"权限修改1",1,1101,"system:premission:edit","edit");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }


            if(longCode.indexOf("A1-B1-del")<0){
                MenuPojo pre = new MenuPojo("A1-B1-del","权限删除","A1-B1",null,null,"删除权限",1,1102,"system:premission:del","del");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A1-B1-add")<0){
                MenuPojo pre = new MenuPojo("A1-B1-add","权限增加","A1-B1",null,null,"增加新的权限",1,1104,"system:premission:add","add");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A1-B1-list")<0){
                MenuPojo pre = new MenuPojo("A1-B1-list","权限列表","A1-B1",null,null,"权限功能表格",1,1103,"system:premission:list","list");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A1-B2-list")<0){
                MenuPojo pre = new MenuPojo("A1-B2-list","用户列表","A1-B2",null,null,"用户列表",1,1201,"system:user:list","list");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A1-B2-add")<0){
                MenuPojo pre = new MenuPojo("A1-B2-add","用户添加","A1-B2",null,null,null,1,1202,"system:user:add","add");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A1-B2-edit")<0){
                MenuPojo pre = new MenuPojo("A1-B2-edit","用户修改","A1-B2",null,null,null,1,1203,"system:user:edit","edit");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A1-B2-del")<0){
                MenuPojo pre = new MenuPojo("A1-B2-del","用户删除","A1-B2",null,null,null,1,1204,"system:user:del","del");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A1-B2-editUR")<0){
                MenuPojo pre = new MenuPojo("A1-B2-editUR","用户修改权限分配","A1-B2",null,null,null,1,1205,"system:user:editUR","edit");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A1-B4-list")<0){
                MenuPojo pre = new MenuPojo("A1-B4-list","日志列表","A1-B4",null,null,"日志列表",1,1401,"system:log:list","list");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A3-B1-upload")<0){
                MenuPojo pre = new MenuPojo("A3-B1-upload","图片上传","A3-B1",null,null,"单个图片上传",1,3102,"system:file:upload","upload");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A3-B1-changeUpload")<0){
                MenuPojo pre = new MenuPojo("A3-B1-changeUpload","图片更换","A3-B1",null,null,"更换图片",1,3103,"system:file:changeImage","upload");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A3-B1-uploadS")<0){
                MenuPojo pre = new MenuPojo("A3-B1-uploadS","图片批量上传","A3-B1",null,null,"多图片上传",1,3104,"system:file:uploadS","upload");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A3-B1-del")<0){
                MenuPojo pre = new MenuPojo("A3-B1-del","图片删除","A3-B1",null,null,"删除图片",1,3105,"system:file:del","del");
                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }

            if(longCode.indexOf("A3-B1-edit")<0){
                MenuPojo pre = new MenuPojo("A3-B1-edit","图片名称修改","A3-B1",null,null,"修改图片名称",1,3106,"system:file:edit","edit");                premisionService.insertPremission(pre);
                logger.info("权限表插入权限：\n "+pre.toString() );
            }
        } catch (Exception e) {
            logger.error("错误信息：", e);
        }
    }



    @GetMapping("usuallyDemo")
    public String view1() {
        return "common/usuallyDemo";
    }

    @GetMapping("swggerView")
    public String view2() {
        return "common/swggerView";
    }

    @GetMapping("jsView")
    public String view3() {
        return "common/jsView";
    }

    @GetMapping("echartsView")
    public String view4() {
        return "common/echartsView";
    }

    /**
     * Description 跳转图片视图
     *
     * @Author junwei
     * @Date 9:35 2019/6/15
     **/
    @GetMapping("fileView")
    @RequiresPermissions("system:file:view")
    public String view(Model model) {
        return  "image/fileView";
    }


    @GetMapping("premissionView")
    @RequiresPermissions("system:premission:view")
    public String view1(Model model) {
        return "admin2/premission/premissionView";
    }


    @GetMapping("gisView1")
    @RequiresPermissions("system:gis:view1")
    public String gisView1() {
        return  "gis/gisView1";
    }

    @GetMapping("gisView2")
    @RequiresPermissions("system:gis:view2")
    public String gisView2() {
        return "gis/gisView2";
    }


    @GetMapping("dataView1")
    @RequiresPermissions("system:data:view")
    public String view() {
        return "data/dataView1";
    }

    @GetMapping("logView")
    @RequiresPermissions("system:log:view")
    public String logView(Model model) {
        return  "admin2/log/logView";
    }

    @GetMapping("roleView")
    @RequiresPermissions("system:role:view")
    public String roleView(Model model) {
        return  "admin2/role/roleView";
    }

    @GetMapping("userView")
    @RequiresPermissions("system:user:view")
    public String userView(Model model) {
        return  "admin2/user/userView";
    }

}

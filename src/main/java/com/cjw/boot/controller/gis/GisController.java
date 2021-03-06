package com.cjw.boot.controller.gis;

import com.cjw.boot.common.base.BaseController;
import com.cjw.boot.service.gis.GisService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Description 地图管理
* @Author junwei
* @Date 10:35 2019/7/31
**/
@Controller
@Api("地图管理")
@RequestMapping("GisController")
public class GisController extends BaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    GisService gisService;

    public String prefix = "gis/";

/*    @GetMapping("gisView1")
//    @RequiresPermissions("system:gis:view")
    public String view() {
        return prefix + "gisView1";
    }

    @GetMapping("gisView2")
//    @RequiresPermissions("system:gis:view")
    public String view2() {
        return prefix + "gisView2";
    }*/


    /**
     * 地图专用的sql
     */
    @GetMapping("/gisSectorList")
    @ResponseBody
    public List<LinkedHashMap<String, Object>> dynamicSqlMap(String cityCode, String Date, String Lng, String Lat) throws Exception {
        try {
            return gisService.getListSector(cityCode, Date, Lng, Lat);
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }

    }


    @GetMapping("/getSiteName")
    @ResponseBody
    public List<LinkedHashMap<String, Object>> getSiteName(String cityCode, String Date) throws Exception {
        return gisService.getSiteName(cityCode, Date);
    }

    /**
     * Description  小区列表
     *
     * @param
     * @param
     * @Author junwei
     * @Date 18:01 2019/6/25
     **/
    @GetMapping("/sectorList")
    @ResponseBody
    public Map<String, Object> sectorList(Integer page, Integer limit) {
        try {
            PageInfo<LinkedHashMap<String, Object>> pageInfo = gisService.getListSector(page, limit);
            return resSuccessMap(pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return resFailMap(e.getCause().toString());
        }


    }


    /**
     * 动态SQL 在这里组装查询的sql 黑点竞对网格110
     */
    @RequestMapping("/gisGridNo")
    @ResponseBody
    public List<LinkedHashMap<String, Object>> gisGridNo(
            String CityCode, String Date)  {
        return gisService.getListGridNo(CityCode, Date);
    }


    /**
     * 获取区域经纬度
     *
     * @return
     */
    @RequestMapping("/gisArea")
    @ResponseBody
    public List<Map<String, Object>> gisArea(
    ) {
        try {
            return gisService.getGisArea();
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }


    /**
     * 获取区域内点经纬度
     *
     * @return
     */
    @RequestMapping("/gisAreaPoint")
    @ResponseBody
    public List<LinkedHashMap<String, Object>> gisAreaPoint(String id) {
        try {
            return gisService.getAraPonint(id);
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }
}

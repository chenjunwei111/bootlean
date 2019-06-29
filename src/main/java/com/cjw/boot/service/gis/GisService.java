package com.cjw.boot.service.gis;


import com.cjw.boot.service.diy.DiySqlService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class GisService {


    @Autowired
    DiySqlService diySqlService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Description 获取地图汇聚小区列表
     *
     * @param
     * @param
     * @Author junwei
     * @Date 17:57 2019/6/25
     **/
    public List<LinkedHashMap<String, Object>> getListSector(String cityCode, String Date, String Lng, String Lat) {
        try {
            String sqlString = "";
            String part = "P530100_20190214";
            Lng = "102.711903";
            Lat = "25.051598";
            if (Lng == null || Lng.isEmpty()) {
                sqlString = "select to_char(version_date,'yyyyMMdd') mdate,LONGITUDE,LATITUDE,listagg(SECTOR_ID||'|'||AZIMUTH,',') within GROUP (order by LONGITUDE,LATITUDE) sectorId  from p_lte_sector  subpartition ("
                        + part + ") where LONGITUDE is not null and LATITUDE is not null and AZIMUTH>=0 and AZIMUTH<360 group by LONGITUDE,LATITUDE,version_date";
            } else {
                sqlString = "with t as ( select version_date,LONGITUDE,  LATITUDE,SECTOR_ID,AZIMUTH "
                        + " FROM p_lte_sector subpartition (" + part + ") "
                        + "WHERE LONGITUDE IS NOT NULL AND LATITUDE IS NOT NULL "
                        + "AND AZIMUTH >=0 AND AZIMUTH <360  and LONGITUDE-" + Lng + ">-0.1 and LONGITUDE-" + Lng + "<0.1  "
                        + "and LATITUDE-" + Lat + ">-0.1 and LATITUDE-" + Lat + "<0.1  "
                        + ") SELECT TO_CHAR(version_date,'yyyyMMdd') mdate,  LONGITUDE,  LATITUDE, "
                        + " listagg(SECTOR_ID||'|'||AZIMUTH,',') within GROUP ( "
                        + "ORDER BY LONGITUDE,LATITUDE) sectorId "
                        + "from t where FN_GET_DISTANCE(longitude,latitude," + Lng + "," + Lat + ")<=10 "
                        + "GROUP BY LONGITUDE,LATITUDE,version_date";
            }
            return diySqlService.diySqlList(sqlString);
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }

    /**
     * Description 获取基站列表
     *
     * @param
     * @param
     * @Author junwei
     * @Date 17:57 2019/6/25
     **/
    public List<LinkedHashMap<String, Object>> getSiteName(String cityCode, String Date) {
        try {
            String part = "P530100_20190214";
            String sql = " select distinct ENODEBID,ENODEB_NAME from  p_lte_sector  subpartition (" + part + ") where LONGITUDE is not null and LATITUDE is not null ";
            return diySqlService.diySqlList(sql);
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }

    /**
     * Description 小区列表（分页）
     *
     * @param page
     * @param limit
     * @Author junwei
     * @Date 18:02 2019/6/25
     **/
    public PageInfo<LinkedHashMap<String, Object>> getListSector(Integer page, Integer limit) {
        try {
            String sql = "select CITY_CODE, CITY, to_char(VERSION_DATE,'yyyymmdd') VERSION_DATE, SECTOR_ID, SECTOR_NAME,\n" +
                    "LONGITUDE, LATITUDE ,FN_GET_DISTANCE(LONGITUDE, LATITUDE,102.711903,25.051598) dis from P_LTE_SECTOR \n" +
                    "where rownum<10000 order by dis ";
            PageInfo<LinkedHashMap<String, Object>> setctorList = diySqlService.diySqPage(sql, page, limit);
            return setctorList;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }


    /**
     * Description 获取栅格数据列表
     *
     * @param
     * @param
     * @Author junwei
     * @Date 18:03 2019/6/25
     **/
    public List<LinkedHashMap<String, Object>> getListGridNo(String CityCode, String Date) {
        try {
            String sql = "select * from GRID_NET";
            return diySqlService.diySqlList(sql);
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }

    /**
     * Description 获取区域列表数据
     *
     * @param
     * @param
     * @Author junwei
     * @Date 18:06 2019/6/25
     **/
    public List<Map<String, Object>> getGisArea() {
        try {
            String sql = "select * from GIS_ARAE ";
            List<LinkedHashMap<String, Object>> locHashMaps = diySqlService.diySqlList(sql);
            logger.info("场景查询:" + sql);
            if (locHashMaps == null)
                return null;
            String[] lonlat = null;
            String[] lon_lat = null;
            List<Map<String, Object>> lstmap = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = null;
            Map<String, Object> map0 = null;
            List<Map<String, Object>> list = null;

            for (LinkedHashMap<String, Object> kvHashMap : locHashMaps) {
                lonlat = kvHashMap.get("LOCATIONS").toString().trim().split(";");
                list = new ArrayList<Map<String, Object>>();
                map = new HashMap<String, Object>();
                for (String string : lonlat) {
                    map0 = new HashMap<String, Object>();
                    lon_lat = string.split(",");
                    if (lon_lat.length == 2) {
                        map0.put("LNG", lon_lat[0]);
                        map0.put("LAT", lon_lat[1]);
                        getCenter(lon_lat);
                        list.add(map0);
                    }
                    map0 = null;
                }
                map.put("COMMUNITY_ID", kvHashMap.get("COMMUNITY_ID"));
                map.put("UPGRADE_CNT", kvHashMap.get("UPGRADE_CNT"));
                map.put("AREANAME", kvHashMap.get("AREANAME"));
                map.put("COMMUNITY_NAME", kvHashMap.get("COMMUNITY_NAME"));
                map.put("CNT", kvHashMap.get("CNT"));
                map.put("TAG", kvHashMap.get("TAG"));
                map.put("C_LNG", (maxlng + minlng) / 2);
                map.put("C_LAT", (maxlat + minlat) / 2);
                map.put("LOCATIONS", list);
                lstmap.add(map);
                maxlng = 0;
                maxlat = 0;
                minlng = 1000;
                minlat = 1000;
                map = null;
                list = null;
            }
            return lstmap;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }


    /**
    * Description  获取区域内经纬度点
    * @param
    * @Author junwei
    * @Date 18:09 2019/6/25
    **/
    public List<LinkedHashMap<String, Object>> getAraPonint(String id) {
        try {
            String sql = "select * from GIS_PONINT where COMMUNITY_ID='" + id + "'";
            return diySqlService.diySqlList(sql);
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }


    double maxlng = 0, maxlat = 0, minlng = 1000, minlat = 1000;

    private void getCenter(String[] lnglat) {
        double lng = Double.parseDouble(lnglat[0]);
        double lat = Double.parseDouble(lnglat[1]);
        if (lng > maxlng) {
            maxlng = lng;
        }
        if (lng < minlng) {
            minlng = lng;
        }
        if (lat > maxlat) {
            maxlat = lat;
        }
        if (lat < minlat) {
            minlat = lat;
        }
    }

}

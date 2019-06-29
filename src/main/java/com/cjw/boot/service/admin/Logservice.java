package com.cjw.boot.service.admin;


import com.cjw.boot.service.diy.DiySqlService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;


@Service
@Transactional
public class Logservice {


    @Autowired
    DiySqlService diySqlService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Description  日志集合查询
     * @param page
     * @param limit
     * @Author junwei
     * @Date 17:25 2019/6/25
     **/
    public PageInfo<LinkedHashMap<String, Object>> getLogList(Integer page, Integer limit) {
        try {
            String sql = "select ID, ACTION, TITLE, METHOD, OPER_NAME, OPER_URL, OPER_PARAM, ERROR_MSG, to_char(OPER_TIME,'yyyy-mm-dd hh24:mi:ss') OPER_TIME  from T_SYS_OPER_LOG order by OPER_TIME desc";
            PageInfo<LinkedHashMap<String, Object>> listLog = diySqlService.diySqPage(sql, page, limit);
            return listLog;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }

    }


    /**
     * Description  日志用户使用统计
     * @param page
     * @param limit
     * @Author junwei
     * @Date 17:25 2019/6/25
     **/
    public PageInfo<LinkedHashMap<String, Object>> getUserCount(Integer page, Integer limit) {
        try {
            String sql = " with t1 as(select ID, ACTION, TITLE, METHOD, OPER_NAME, OPER_URL, OPER_PARAM, ERROR_MSG, OPER_TIME from T_SYS_OPER_LOG \n" +
                    "),t2 as (select OPER_NAME,max(OPER_TIME) last_time from t1 group by OPER_NAME\n" +
                    "),t3 as(select OPER_NAME,count(OPER_NAME) month_cnt from t1 where to_char(OPER_TIME,'yyyymm')=to_char(sysdate,'yyyymm')\n" +
                    "group by OPER_NAME ),\n" +
                    "t4 as (select OPER_NAME,count(OPER_NAME) total_cnt from t1 group by OPER_NAME)\n" +
                    "select t4.OPER_NAME,t4.total_cnt,t3.month_cnt,to_char(t2.last_time,'yyyy-mm-dd hh24:mi:ss') last_time\n" +
                    "from t4 left join t2 on t4.OPER_NAME=t2.OPER_NAME\n" +
                    "left join t3 on t4.OPER_NAME=t3.OPER_NAME order by t3.month_cnt desc";
            PageInfo<LinkedHashMap<String, Object>> listLog = diySqlService.diySqPage(sql, page, limit);
            return listLog;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }

    }

    /**
     * Description  日志功能使用统计
     * @param page
     * @param limit
     * @Author junwei
     * @Date 17:25 2019/6/25
     **/
    public PageInfo<LinkedHashMap<String, Object>> getFunCount(Integer page, Integer limit) {
        try {
            String sql = "with t1 as(select ID, ACTION, TITLE, METHOD, OPER_NAME, OPER_URL, OPER_PARAM, ERROR_MSG, OPER_TIME from T_SYS_OPER_LOG" +
                    "                    ),t2 as (select TITLE,max(OPER_TIME) last_time from t1 group by TITLE" +
                    "                    ),t3 as(select TITLE,count(TITLE) month_cnt from t1 where to_char(OPER_TIME,'yyyymm')=to_char(sysdate,'yyyymm')" +
                    "                    group by TITLE )," +
                    "                    t4 as (select TITLE,count(TITLE) total_cnt from t1 group by TITLE)" +
                    "                    select t4.TITLE,t4.total_cnt,t3.month_cnt,to_char(t2.last_time,'yyyy-mm-dd hh24:mi:ss') last_time" +
                    "                    from t4 left join t2 on t4.TITLE=t2.TITLE" +
                    "                    left join t3 on t4.TITLE=t3.TITLE order by t3.month_cnt desc ";
            PageInfo<LinkedHashMap<String, Object>> listLog = diySqlService.diySqPage(sql, page, limit);
            return listLog;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }

    }
}

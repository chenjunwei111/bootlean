package com.cjw.boot.service.diy;

import com.cjw.boot.mapper.diy.DiySqlMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Description 动态SQLservice层
 *
 * @Author junwei
 * @Date 9:46 2019/5/29
 **/
@Service
public class DiySqlService {

    @Autowired
    private DiySqlMapper diySqlMapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Description 自定义查询SQL（demo:select * from dual）
     *
     * @param sql 查询SQL
     * @Author junwei
     * @Date 9:48 2019/5/29
     **/
    public List<LinkedHashMap<String, Object>> diySqlList(String sql) {
        return diySqlMapper.diySql(sql);
    }


    /**
     * Description  动态语句分页
     *
     * @param sql      动态语句
     * @param page   页数
     * @param limit 页码
     * @Author junwei
     * @Date 11:28 2019/6/4
     *
     * @return*/
    public PageInfo<LinkedHashMap<String, Object>> diySqPage(String sql, Integer page, Integer limit) {
        try {
            PageHelper.startPage(page,limit);
            List<LinkedHashMap<String, Object>> list= diySqlMapper.diySql(sql);
            PageInfo<LinkedHashMap<String, Object>> pageInfo = new PageInfo<>(list);
            return pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("错误信息：", e);
            return null;
        }
    }

    /**
     * Description 自定义删除（demo:delete * from dual where ）
     *
     * @param sql 查询SQL
     * @Author junwei
     * @Date 9:48 2019/5/29
     **/
    public Integer diySqlDel(String sql) {
        return diySqlMapper.diyDelete(sql);
    }

    /**
     * Description 自定义更新（demo:delete * from dual where ）
     *
     * @param sql SQL
     * @Author junwei
     * @Date 9:48 2019/5/29
     **/
    public Integer diySqlUpdate(String sql) {
        return diySqlMapper.diyUpdate(sql);
    }


    /**
     * Description 自定义插入（demo:delete * from dual where ）
     *
     * @param sql SQL
     * @Author junwei
     * @Date 9:48 2019/5/29
     **/
    public Integer diySqlInsert(String sql) {
        return diySqlMapper.diyInsert(sql);
    }
}

package com.cjw.boot.mapper.image;

import com.cjw.boot.pojo.image.FilePojo;

import java.util.List;

/**
* Description 文件Dao
* @Author junwei
* @Date 10:02 2019/6/15
**/
public interface FileMapper {

    /**
     * Description 文件列表
     * @param pojo
     * @Author junwei
     * @Date 9:58 2019/6/11
     **/
    public List<FilePojo> listFile(FilePojo pojo);

    /**
     * Description 添加文件
     * @param pojo
     * @Author junwei
     * @Date 9:59 2019/6/11
     **/
    public Integer addFile(FilePojo pojo);


    /**
     * Description 删除文件
     * @param pojo
     * @Author junwei
     * @Date 9:59 2019/6/11
     **/
    public Integer delFile(FilePojo pojo);


    /**
     * Description 更新文件
     * @param pojo
     * @Author junwei
     * @Date 10:00 2019/6/11
     **/
    public Integer updateFile(FilePojo pojo);



}

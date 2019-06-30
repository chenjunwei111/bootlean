package com.cjw.boot.service.image;


import com.cjw.boot.common.conf.V2Config;
import com.cjw.boot.common.file.FileUploadUtils;
import com.cjw.boot.common.file.FileUtils;
import com.cjw.boot.mapper.image.FileMapper;
import com.cjw.boot.pojo.image.FilePojo;
import com.cjw.boot.service.diy.DiySqlService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
//import org.apache.commons.io.FileExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * 系统文件
 *
 * @author fuce
 * @ClassName: FileService
 * @date 2018年8月26日
 */
@Service
public class FileService {

    @Autowired
    FileMapper fileMapper;

    @Autowired
    DiySqlService diySqlService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Description 获取所有文件
     *
     * @param pojo 文件对象
     * @Author junwei
     * @Date 14:15 2019/6/10
     **/
    public List<FilePojo> listFile(FilePojo pojo) {
        return fileMapper.listFile(pojo);
    }

    /**
     * Description 文件分页
     *
     * @param pojo
     * @param pageNo
     * @param pageSize
     * @Author junwei
     * @Date 10:05 2019/6/11
     **/
    public PageInfo<FilePojo> pageFile(FilePojo pojo, Integer pageNo, Integer pageSize) {
        try {
            PageHelper.startPage(pageNo, pageSize);
            List<FilePojo> list = fileMapper.listFile(pojo);
            PageInfo<FilePojo> pageInfo = new PageInfo<>(list);
            return pageInfo;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }


    /**
     * Description 添加文件
     *
     * @param pojo
     * @Author junwei
     * @Date 9:59 2019/6/11
     **/
    @Transactional
    public Integer addFile(FilePojo pojo) {
        try {
            return fileMapper.addFile(pojo);
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return 0;
        }
    }


    /**
     * Description 删除文件
     *
     * @param pojo
     * @Author junwei
     * @Date 9:59 2019/6/11
     **/
    @Transactional
    public Integer delFile(FilePojo pojo) {
        try {
            int res1 = fileMapper.delFile(pojo);
            return res1;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }

    }


    /**
     * Description 更新文件
     *
     * @param pojo
     * @Author junwei
     * @Date 10:00 2019/6/11
     **/
    public Integer updateFile2(FilePojo pojo, HttpServletRequest request) {
        try {
            String oldFilePath = pojo.getFilePath();//旧文件路径
            String newFileName = pojo.getFileName();//新文件名称

            HttpSession session = request.getSession();
            String uid = session.getAttribute("id").toString();
            String uName = session.getAttribute("userName").toString();

            pojo.setFilePath(V2Config.getProfile() + newFileName);
            pojo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            pojo.setUpdateUserName(uName);
            pojo.setUpdateUserId(uid);

            if (fileMapper.updateFile(pojo) == 1) {
                String res = FileUtils.updateFileName(oldFilePath, newFileName);//修改文件名称
                if ("修改失败".equals(res) || "文件不存在".equals(res)) {
                    throw new Exception();
                } else {
                    return 1;
                }
            } else {
                return 0;
            }
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return 0;
        }
    }


    /**
     * Description 图片上传
     *
     * @param file
     * @Author junwei
     * @Date 13:37 2019/6/15
     **/
    @Transactional
    public FilePojo uploadImg(MultipartFile file, HttpServletRequest request) {
        try {
            FilePojo pojo = new FilePojo();

            //随机数
            Random rand = new Random();
            int num = rand.nextInt(100);
            Long time = System.currentTimeMillis() + num;
            HttpSession session = request.getSession();
            String uid = session.getAttribute("id").toString();
            String uName = session.getAttribute("userName").toString();
            String fileId = String.valueOf(time);
            String filesName = FileUploadUtils.upload(file, null);//文件名
            String filesURL = V2Config.getProfile() + filesName;//文件路径

            pojo.setId(fileId);
            pojo.setFileName(filesName);
            pojo.setCreateTime(new Timestamp(time));
            pojo.setCreateUserName(uName);
            pojo.setCreateUserId(uid);
            pojo.setFilePath(filesURL);
            int res = fileMapper.addFile(pojo);
            if (res == 1) {
                return pojo;
            }
            return null;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }


    /**
     * Description 更换图片
     *
     * @param file
     * @Author junwei
     * @Date 13:37 2019/6/15
     **/
    @Transactional
    public FilePojo changeImg(MultipartFile file, HttpServletRequest request, FilePojo pojo) {
        try {
            String fileName = pojo.getFileName();//获取文件名
            Long time = System.currentTimeMillis();
            HttpSession session = request.getSession();
            String uid = session.getAttribute("id").toString();
            String uName = session.getAttribute("userName").toString();


            pojo.setUpdateTime(new Timestamp(time));
            pojo.setUpdateUserName(uName);
            pojo.setUpdateUserId(uid);

            int res = fileMapper.updateFile(pojo);

            if (res == 1) {
                FileUploadUtils.upload(file, fileName);//上传文件
                return pojo;
            }
            return null;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }

    /**
     * Description  修改图片信息
     *
     * @param
     * @Author junwei
     * @Date 18:12 2019/6/25
     **/
    public Map<String, Object> editFileInfo(FilePojo pojo, HttpServletRequest request) {
        Map<String, Object> maps = new HashMap<>();
        try {
            List<LinkedHashMap<String, Object>> list = diySqlService.diySqlList("select count(0) CNT from T_SYS_FILE_2 where FILE_NAME = '" + pojo.getFileName() + "'");
            int cnt = Integer.parseInt(list.get(0).get("CNT").toString());
            if (cnt > 0) {
                maps.put("msg", "修改图片失败,图片名重复");
                return maps;
            }
            int res = updateFile2(pojo, request);
            if (res == 0) {
                maps.put("msg", "修改图片失败");
            } else {
                maps.put("msg", "修改图片成功");
            }
            return maps;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }


    /**
     * Description 获取图片
     *
     * @param
     * @param id 图片ID
     * @Author junwei
     * @Date 18:19 2019/6/25
     **/
    public void getImage(String id, HttpServletRequest request, HttpServletResponse response) {
        try {
            String sql = "select FILE_PATH from T_sys_file_2 where id =" + id + "";
            List<LinkedHashMap<String, Object>> list = diySqlService.diySqlList(sql);
            if (list.size() != 0) {
                String path = list.get(0).get("FILE_PATH").toString();
                FileUtils.readIMGTohtml(request, response, path);
            }
        } catch (IOException e) {
            logger.error("错误信息：", e);
        }
    }

}

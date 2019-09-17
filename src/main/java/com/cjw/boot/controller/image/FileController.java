package com.cjw.boot.controller.image;

import com.alibaba.fastjson.JSON;
import com.cjw.boot.common.base.BaseController;
import com.cjw.boot.common.log.Log;
import com.cjw.boot.pojo.image.FilePojo;
import com.cjw.boot.service.diy.DiySqlService;
import com.cjw.boot.service.image.FileService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@Api("图片管理")
@RequestMapping("FileController")
public class FileController extends BaseController {

    public String prefix = "image";

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    FileService fileService;

    @Autowired
    DiySqlService diySqlService;



    /**
     * Description 跳转图片视图
     *
     * @Author junwei
     * @Date 9:35 2019/6/15
     **/
/*    @GetMapping("view")
    @RequiresPermissions("system:file:view")
    public String view(Model model) {
        return prefix + "/fileView";
    }*/

    /**
     * Description 图片集合查询
     *
     * @param page
     * @param limit
     * @Author junwei
     * @Date 17:29 2019/6/11
     **/
    @Log(title = "图片集合查询", action = "图片管理")
    @GetMapping("listFile")
    @RequiresPermissions("system:file:list")
    @ResponseBody
    public Map<String, Object> list(Integer page, Integer limit) {
        try {
            FilePojo pojo = new FilePojo();
            PageInfo<FilePojo> pageInfo = fileService.pageFile(pojo, page, limit);
            return resSuccessMap(pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return resFailMap(e.getCause().toString());
        }
    }



    /**
    * Description 单图片上传
    * @param file
    * @Author junwei
    * @Date 10:38 2019/7/31
    **/
    @PostMapping("/fileUpload")
    @ResponseBody
//    @RequiresPermissions("system:file:upload")
    public  Map<String ,Object> upload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
          Map<String ,Object> map=new HashMap<>();
        try {
            if (!file.isEmpty()) {
                //插入图片存储表
                FilePojo pojo = fileService.uploadImg(file, request);
                if(pojo!=null){
                    map.put("msg","上传成功");
                    map.put("file",pojo);
                }else{
                    map.put("msg","上传失败");
                    map.put("file",pojo);
                }

            }else{
                map.put("msg","没有图片");
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg","上传成功");
            map.put("error",e.getMessage());
            return map;
        }
    }

    /**
     * Description 更换图片
     * @param file
     * @Author junwei
     * @Date 10:38 2019/7/31
     **/
    @Log(title = "更换图片", action = "图片管理")
    @PostMapping("/changeImage")
    @ResponseBody
    @RequiresPermissions("system:file:changeImage")
    public Map<String ,Object> upload2(@RequestParam("file") MultipartFile file, HttpServletRequest request,String file2) {
        Map<String ,Object> map=new HashMap<>();
        try {
            if (!file.isEmpty()) {
                //插入图片存储表
               FilePojo pojo2= JSON.parseObject(file2, FilePojo.class);

               FilePojo pojo = fileService.changeImg(file,request,pojo2);
               if(pojo==null){
                   map.put("msg","重传失败");
                   map.put("file",pojo);
               }else{
                   map.put("msg","重传成功");
                   map.put("file",pojo);
               }
            }else {
                map.put("msg","无图片");
            }
            return map;
        } catch (Exception e) {
            logger.error("错误信息：",e);
            map.put("msg","重传失败");
            map.put("error",e.getMessage());
            return map;
        }
    }

    /**
     * Description 多图片上传
     * @param file
     * @Author junwei
     * @Date 10:38 2019/7/31
     **/
    @Log(title = "多图片上传", action = "图片管理")
    @PostMapping("/fileUploadS")
    @ResponseBody
    @RequiresPermissions("system:file:uploadS")
    public  Map<String ,Object>  fileUploadS(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Map<String ,Object> map=new HashMap<>();
        try {
            if (!file.isEmpty()) {
                //插入图片存储表
                FilePojo pojo = fileService.uploadImg(file, request);
                if(pojo!=null){
                    map.put("msg","上传成功");
                    map.put("file",pojo);
                }else{
                    map.put("msg","上传失败");
                    map.put("file",pojo);
                }
            }else{
                map.put("msg","没有图片");
            }
            return map;
        } catch (Exception e) {
            logger.error("错误信息：",e);
            map.put("msg","上传失败");
            map.put("error",e.getMessage());
            return map;
        }
    }


    /**
     * Description 删除图片
     * @param pojo
     * @Author junwei
     * @Date 17:55 2019/6/11
     **/
    @Log(title = "图片删除", action = "图片管理")
    @PostMapping("delFile")
    @ResponseBody
    @RequiresPermissions("system:file:del")
    public Map<String, Object> del(@RequestBody FilePojo pojo) {
        Map<String, Object> maps = new HashMap<>();
        try {
            int res = fileService.delFile(pojo);
            if (res == 0) {
                maps.put("msg", "删除图片失败");
            } else {
                maps.put("msg", "删除图片成功");
            }
            return maps;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            maps.put("msg", "删除图片失败");
            maps.put("error", e.getMessage());
            return maps;
        }

    }

    /**
     * Description 修改图片名称
     *
     * @param pojo
     * @Author junwei
     * @Date 17:55 2019/6/11
     **/
    @Log(title = "图片修改", action = "图片管理")
    @PostMapping("editFileInfo")
    @ResponseBody
    @RequiresPermissions("system:file:edit")
    public Map<String, Object> edit(@RequestBody FilePojo pojo,HttpServletRequest request) {
        Map<String, Object> maps = new HashMap<>();
        try {
            return fileService.editFileInfo(pojo,request);
        } catch (Exception e) {
            logger.error("错误信息：", e);
            maps.put("msg", "修改图片失败");
            maps.put("error", e.getMessage());
            return maps;
        }

    }


    @GetMapping("/viewImg/{id}")
    public void viewIMG(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
        try {
           fileService.getImage(id,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

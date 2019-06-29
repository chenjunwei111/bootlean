package com.cjw.boot.controller.data;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api("数据处理")
@RequestMapping("DataController")
public class DataController {

    Logger logger= LoggerFactory.getLogger(this.getClass());

    public  String prefix="data/";

    @GetMapping("dataView1")
//    @RequiresPermissions("system:data:view")
        public String view() {
        return prefix + "dataView1";
    }


}

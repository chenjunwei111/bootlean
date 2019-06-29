package com.cjw.boot.controller.common;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("CommonController")
@Api("公共控制器")
@Controller

public class CommonController {

    public String prefix="/common/";


    @GetMapping("usuallyDemo")
    public String view1(){
        return prefix+"usuallyDemo";
    }


    @GetMapping("swggerView")
    public String view2(){
        return prefix+"swggerView";
    }


    @GetMapping("jsView")
    public String view3(){
        return prefix+"jsView";
    }


    @GetMapping("echartsView")
    public String view4(){
        return prefix+"echartsView";
    }
}

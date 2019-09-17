package com.cjw.boot.controller.data;

import cn.hutool.json.JSONArray;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import javassist.tools.rmi.RemoteException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

@Controller
@Api("数据处理")
@RequestMapping("DataController")
public class DataController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public String prefix = "data/";

//    @GetMapping("dataView1")
////    @RequiresPermissions("system:data:view")
//    public String view() {
//        return prefix + "dataView1";
//    }

    @GetMapping("testAxis")
    @ResponseBody
    public String testAxis() {
        try {
            HashMap<String,Object> map=new HashMap<>(2);
            map.put("userName","广州天河");
            map.put("age","46");

            String xmlStr = JSON.toJSONString(map);
            String result2= invoke("http://183.238.63.208:62113/axis/services/StudentService","getStudentInfo",xmlStr);
//            String result2= invoke("http://192.168.9.133:8080/axis/services/StudentService","getStudentMarks",xmlStr);
            System.out.println(result2);
            logger.info(result2);
            return result2;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }


    @GetMapping("testAxis2")
    @ResponseBody
    public String testAxis2() {
        try {
            System.out.println();
            System.out.println();
            System.out.println();
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=new Date(System.currentTimeMillis());
            String time=format.format(date);
            System.out.println("初始时间:"+time);
            LinkedList<HashMap<String,Object>> list=new LinkedList<>();
            HashMap<String,Object> map=new HashMap<>(2);;
            int i=0;
            int mapSize=0;
            int size=40000;
            while (i<size){
                i++;
                if(i%size==0){
                    System.out.println(format.format(new Date(System.currentTimeMillis()))+" 生成数据"+i+"条" );
                    mapSize=map.size();
                }
                map=new HashMap<>(2);
                map.put("userName1","广州天河");
                map.put("age",i);
                map.put("userName2","广州天河");
                map.put("userName3","广州天河");
                map.put("userName4","广州天河");
                map.put("userName5","广州天河");
                map.put("userName6","广州天河");
                map.put("userName7","广州天河");
                map.put("userName8","广州天河");
                map.put("userName9","广州天河");
                map.put("userName10","广州天河");
                map.put("userName11","广州天河");
                map.put("userName12","广州天河");
                map.put("userName13","广州天河");
                map.put("userName14","广州天河");
                map.put("userName15","广州天河");
//                map.put("userName16","广州天河");
//                map.put("userName17","广州天河");
//                map.put("userName18","广州天河");
//                map.put("userName19","广州天河");
//                map.put("userName20","广州天河");
//                map.put("userName21","广州天河");
//                map.put("userName22","广州天河");
//                map.put("userName23","广州天河");
//                map.put("userName24","广州天河");
//                map.put("userName25","广州天河");
//                map.put("userName26","广州天河");
//                map.put("userName27","广州天河");
//                map.put("userName28","广州天河");
//                map.put("userName29","广州天河");
//                map.put("userName30","广州天河");
                list.add(map);
            }
            System.out.println("转换成JSON字符串开始:"+format.format(new Date(System.currentTimeMillis())));

            JSONArray jsonArray=new JSONArray(list);
            String xmlStr=jsonArray.toString();

            System.out.println("转换成JSON字符串结束:"+format.format(new Date(System.currentTimeMillis())));

            Long time1=System.currentTimeMillis();
            System.out.println("axis1.4发送字符串长度:"+xmlStr.length());
            System.out.println("axis1.4发送字符串长度（含中文）:"+getRealLength(xmlStr)+"(Byte)");
            Float sizes=(float)getRealLength(xmlStr)/1024/1024;
            System.out.println("axis1.4发送存储大小:"+sizes+"(M)");
            System.out.println("axis1.4发送数据:"+list.size()/10000+"万条 ，每条数据有"+mapSize+"个字段" );

            System.out.println("axis1.4开始时间 报文发送 到9.133 "+format.format(new Date(time1)));


            //内网
//            System.out.println("axis1.4内网环境。。。。。。");
//            String result = getWebServiceResult("http://192.168.9.133:8080/axis/services/StudentService","getStudentList",xmlStr);
            //外网
//            System.out.println("axis1.4外网环境。。。。。。");
//            String result = getWebServiceResult("http://183.238.63.208:62113/axis/services/StudentService","getStudentList",xmlStr);

//            System.out.println("axis1.4->axis2 外网环境。。。。。。");
//            String result = invoke("http://183.238.63.208:62113/axis2/services/ZipKinService","test", xmlStr);

            System.out.println("axis1.4---》移动 。。。。。。");
            String result= invoke("http://10.174.239.221:9080/eoms35/services/Inter4GoldVaultSrv","getValidSheetInfo",xmlStr);


            Long time2=System.currentTimeMillis();
            System.out.println("axis1.4结果时间 9.133 返回报文  "+format.format(new Date(time2)));
            Float timeFloat=(float)(time2-time1)/1000;
            System.out.println("axis1.4耗时:"+String.valueOf(timeFloat)+"秒");
            System.out.println("axis1.4 发送速率:"+sizes/timeFloat+"M/S");
            return result;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }


    /*
     * 调用webservice路口
     * @param endpoint 地址
     * @param methodName 调用的方法
     * @param xmlStr 传递的xml字符串参数
     * @return
     */
    public String invoke(String endpoint, String methodName, String xmlStr) {
        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        QName qn = new QName(methodName);
        call.setOperationName(qn);
        call.setTargetEndpointAddress(endpoint);
        call.setUseSOAPAction(true);
        String result = "";
        try {
            // 给方法传递参数，并且调用方法
            result = (String) call.invoke(new Object[]{xmlStr});
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (java.rmi.RemoteException e) {
            e.printStackTrace();
        }
//        System.out.println("result is " + result);
        return result;
    }

    private String getWebServiceResult( String url, String method, String idCard) throws Exception{
        String rtnXml = null;
        try {
            String endpoint = url;
            Service service = new Service();
            Call call = (Call) service.createCall();

            call.setTargetEndpointAddress(new java.net.URL(endpoint));
            call.setOperationName(method);

            rtnXml = (String) call.invoke(new Object[]{idCard});


        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtnXml;
    }



    public static int getRealLength(String str) {
        int m = 0;
        char arr[] = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            // 中文字符(根据Unicode范围判断),中文字符长度为2
            if ((c >= 0x0391 && c <= 0xFFE5)) {
                m = m + 2;
            } else if ((c >= 0x0000 && c <= 0x00FF)) // 英文字符
            {
                m = m + 1;
            }
        }
        return m;
    }




}

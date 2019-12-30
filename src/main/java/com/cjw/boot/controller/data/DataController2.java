package com.cjw.boot.controller.data;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api("数据处理2")
@RequestMapping("DataController2")
public class DataController2 {

/*    @GetMapping("testAxis3")
    @ResponseBody
    public String testAxis3() {
        try {
            System.out.println();
            System.out.println();
            System.out.println();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String time = format.format(date);
            System.out.println("初始时间:" + time);
            LinkedList<HashMap<String, Object>> list = new LinkedList<>();
            HashMap<String, Object> map = new HashMap<>(2);
            ;
            int i = 0;
            int mapSize = 0;
            int size=400000;
            while (i < size) {
                i++;
                if (i % size == 0) {
                    System.out.println(format.format(new Date(System.currentTimeMillis())) + " 生成数据" + i + "条");
                    mapSize = map.size();
                }
                map = new HashMap<>(2);
                map.put("userName1", "广州天河");
                map.put("age", i);
                map.put("userName2", "广州天河");
                map.put("userName3", "广州天河");
                map.put("userName4", "广州天河");
                map.put("userName5", "广州天河");
                map.put("userName6", "广州天河");
                map.put("userName7", "广州天河");
                map.put("userName8", "广州天河");
                map.put("userName9", "广州天河");
                map.put("userName10", "广州天河");
                map.put("userName11", "广州天河");
                map.put("userName12", "广州天河");
                map.put("userName13", "广州天河");
                map.put("userName14", "广州天河");
                map.put("userName15", "广州天河");
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
            System.out.println("转换成JSON字符串开始:" + format.format(new Date(System.currentTimeMillis())));

            JSONArray jsonArray = new JSONArray(list);
            String xmlStr = jsonArray.toString();

            System.out.println("转换成JSON字符串结束:" + format.format(new Date(System.currentTimeMillis())));

            Long time1 = System.currentTimeMillis();
            System.out.println("axis2发送字符串长度:" + xmlStr.length());
            System.out.println("axis2发送字符串长度（含中文）:" + getRealLength(xmlStr) + "(Byte)");
            Float sizes=(float)getRealLength(xmlStr)/1024/1024;
            System.out.println("axis2发送存储大小:"+sizes+"(M)");
            System.out.println("axis2发送数据:" + list.size() / 10000 + "万条 ，每条数据有" + mapSize + "个字段");

            System.out.println("axis2开始时间 报文发送 到9.133 " + format.format(new Date(time1)));

            //内网
//            System.out.println("axis2内网环境。。。。。。");
//            String res = connectAxis2("http://192.168.9.133:8080/axis2/services/ZipKinService","test", xmlStr);

//            String res = connectAxis2("http://localhost:7777/axis/services/StudentService","getStudentList", xmlStr);


            //外网
            System.out.println("axis2->axis1.4 外网环境。。。。。。");
//            String res = connectAxis2("http://183.238.63.208:62113/axis/services/StudentService","getStudentList", xmlStr);


            Long time2 = System.currentTimeMillis();
            Float timeFloat=(float)(time2-time1)/1000;
            System.out.println("xis2->axis1.4  耗时:"+String.valueOf(timeFloat)+"秒");
            System.out.println("xis2->axis1.4  发送速率:"+sizes/timeFloat+"M/S");
//            return res;

        } catch (Exception e) {
//            logger.error("错误信息：", e);
            e.printStackTrace();
            return null;
        }

    }*/

/*    public String connectAxis2(String url,String methor, String parm) {
        try {
            //本机tomcat端口为18080,参数是wsdl网址的一部分
            EndpointReference targetEPR = new EndpointReference(url);
            RPCServiceClient sender = new RPCServiceClient();
            Options options = sender.getOptions();
            //超时时间20s
            options.setTimeOutInMilliSeconds(2 * 20000L);
            options.setTo(targetEPR);
            *//**
             * 参数:
             * 1：在网页上执行 wsdl后xs:schema标签的targetNamespace路径
             * <xs:schema  targetNamespace="http://axis2.com">
             * 2：<xs:element name="test"> ======这个标签中name的值
             *//*
            QName qname = new QName("http://axis2.com", methor);
            //方法的入参
            String str = parm;
            Object[] param = new Object[]{str};
            //这是针对返值类型的
            Class<?>[] types = new Class[]{String.class};
            *//**
             * RPCServiceClient类的invokeBlocking方法调用了WebService中的方法。
             * invokeBlocking方法有三个参数
             * 第一个参数的类型是QName对象，表示要调用的方法名；
             * 第二个参数表示要调用的WebService方法的参数值，参数类型为Object[]；
             * 第三个参数表示WebService方法的返回值类型的Class对象，参数类型为Class[]。
             * 当方法没有参数时，invokeBlocking方法的第二个参数值不能是null，而要使用new Object[]{}。
             *//*
            Object[] response = sender.invokeBlocking(qname, param, types);
            return response[0].toString();
        } catch (AxisFault e) {
            e.printStackTrace();
            return "";
        }

    }*/


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

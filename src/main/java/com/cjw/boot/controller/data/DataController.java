package com.cjw.boot.controller.data;

import io.swagger.annotations.Api;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Description 数据处理
* @Author junwei
* @Date 9:55 2019/9/27
**/
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



/*    @GetMapping("testAxis")
    @ResponseBody
    public String testAxis() {
        try {
            HashMap<String,Object> map=new HashMap<>(2);
            map.put("userName","cjw");
            map.put("msg","test send (183.238.63.208:62113)");

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
    }*/


   /* @GetMapping("testAxis2")
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
            int size=1;
            while (i<size){
                i++;
                if(i%size==0){
                    System.out.println(format.format(new Date(System.currentTimeMillis()))+" 生成数据"+i+"条" );
                    mapSize=map.size();
                }
                map=new HashMap<>(2);
                map.put("userName1","广州天河");
                map.put("age",i);
//                map.put("userName2","广州天河");
//                map.put("userName3","广州天河");
//                map.put("userName4","广州天河");
//                map.put("userName5","广州天河");
//                map.put("userName6","广州天河");
//                map.put("userName7","广州天河");
//                map.put("userName8","广州天河");
//                map.put("userName9","广州天河");
//                map.put("userName10","广州天河");
//                map.put("userName11","广州天河");
//                map.put("userName12","广州天河");
//                map.put("userName13","广州天河");
//                map.put("userName14","广州天河");
//                map.put("userName15","广州天河");
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

            String url="http://10.174.239.221:9080/eoms35/services/Inter4GoldVaultSrv";

            System.out.println("转换成JSON字符串开始:"+format.format(new Date(System.currentTimeMillis())));

            JSONArray jsonArray=new JSONArray(list);
            String xmlStr=jsonArray.toString();

            System.out.println("转换成JSON字符串结束:"+format.format(new Date(System.currentTimeMillis())));

            Long time1=System.currentTimeMillis();

            System.out.println("发送内容:"+map);
            System.out.println("axis1.4发送字符串长度:"+xmlStr.length());
            System.out.println("axis1.4发送字符串长度（含中文）:"+getRealLength(xmlStr)+"(Byte)");
            Float sizes=(float)getRealLength(xmlStr)/1024/1024;
            System.out.println("axis1.4发送存储大小:"+sizes+"(M)");
            System.out.println("axis1.4发送数据:"+list.size()/10000+"万条 ，每条数据有"+mapSize+"个字段" );

            System.out.println("axis1.4开始时间 报文发送 到"+url+" "+format.format(new Date(time1)));


            //内网
//            System.out.println("axis1.4内网环境。。。。。。");
//            String result = getWebServiceResult("http://192.168.9.133:8080/axis/services/StudentService","getStudentList",xmlStr);
            //外网
//            System.out.println("axis1.4外网环境。。。。。。");
//            String result = getWebServiceResult("http://183.238.63.208:62113/axis/services/StudentService","getStudentList",xmlStr);

//            System.out.println("axis1.4->axis2 外网环境。。。。。。");
//            String result = invoke("http://183.238.63.208:62113/axis2/services/ZipKinService","test", xmlStr);

            System.out.println("axis1.4---》移动 。。。。。。");
            String result= invoke(url,"getValidSheetInfo",xmlStr);


            Long time2=System.currentTimeMillis();
            System.out.println("axis1.4结果时间 "+url+" 返回报文  "+format.format(new Date(time2)));
            System.out.println("返回报文内容:"+result);
            Float timeFloat=(float)(time2-time1)/1000;
            System.out.println("axis1.4耗时:"+String.valueOf(timeFloat)+"秒");
            System.out.println("axis1.4 发送速率:"+sizes/timeFloat+"M/S");
            return result;
        } catch (Exception e) {
            logger.error("错误信息：", e);
            return null;
        }
    }*/


    /*
     * 调用webservice路口
     * @param endpoint 地址
     * @param methodName 调用的方法
     * @param xmlStr 传递的xml字符串参数
     * @return
     */
    /*public String invoke(String endpoint, String methodName, String xmlStr) {
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
    }*/

/*
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
*/

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


    public static void main(String[] args) {
//        String s1="<recordInfo><fieldInfo><fieldChName>网优投诉T1处理组（操作人）</fieldChName><fieldEnName>operateUser</fieldEnName><fieldContent>贾蓉</fieldContent></fieldInfo><fieldInfo><fieldChName>EOMS工单流水号</fieldChName><fieldEnName>sheetId</fieldEnName><fieldContent>YN-052-190923-00086</fieldContent></fieldInfo><fieldInfo><fieldChName>工单主题</fieldChName><fieldEnName>title</fieldEnName><fieldContent>移动业务→网络质量→手机上网（4G）→网速慢或掉线→功能使用→网速慢或网页无法打开→室外</fieldContent></fieldInfo><fieldInfo><fieldChName>派单时间</fieldChName><fieldEnName>sendTime</fieldEnName><fieldContent>2019-09-23 20:50:24</fieldContent></fieldInfo><fieldInfo><fieldChName>工单处理时限</fieldChName><fieldEnName>sheetCompleteLimit</fieldEnName><fieldContent>2019-09-27 14:50:32</fieldContent></fieldInfo><fieldInfo><fieldChName>紧急程度</fieldChName><fieldEnName>urgentDegree</fieldEnName><fieldContent>一般</fieldContent></fieldInfo><fieldInfo><fieldChName>故障时间</fieldChName><fieldEnName>faultTime</fieldEnName><fieldContent>2019-09-23 20:44:40</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉时间</fieldChName><fieldEnName>complaintTime</fieldEnName><fieldContent>2019-09-23 20:48:55</fieldContent></fieldInfo><fieldInfo><fieldChName>客户姓名</fieldChName><fieldEnName>customerName</fieldEnName><fieldContent>王**</fieldContent></fieldInfo><fieldInfo><fieldChName>客户电话</fieldChName><fieldEnName>customPhone</fieldEnName><fieldContent>13638815511</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉内容</fieldChName><fieldEnName>complaintDesc</fieldEnName><fieldContent>#同步要素#联系要求:本机 客户要求:客户来电反映，本机无法使用4G网络，网速慢，要求查询原因，并且尽快处理，谢谢 处于室内还是室外:室内 （室内）处于该场景下的具体位置:窗边 （数据）访问哪个网站出现问题:新浪 周围其他用户是否有同样情况:无 （室内）处于哪个场景:商业区（包括：酒店、休闲娱乐场所、大型场馆等） （室外）处于哪个场景:商业区（包括：酒店、大型场馆、休闲娱乐场馆等） 手机显示的网络标识:E 无信号出现的频率:总是 开关机情况:情况依旧#同步要素#</fieldContent></fieldInfo><fieldInfo><fieldChName>是否大面积投诉</fieldChName><fieldEnName>isWideComplaint</fieldEnName><fieldContent>否</fieldContent></fieldInfo><fieldInfo><fieldChName>客户级别</fieldChName><fieldEnName>customLevel</fieldEnName><fieldContent>三星</fieldContent></fieldInfo><fieldInfo><fieldChName>用户归属地</fieldChName><fieldEnName>customAttribution</fieldEnName><fieldContent>昭通</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类一级类别</fieldChName><fieldEnName>complaintType1</fieldEnName><fieldContent>移动业务</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类二级类别</fieldChName><fieldEnName>complaintType2</fieldEnName><fieldContent>网络质量</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类三级类别</fieldChName><fieldEnName>complaintType3</fieldEnName><fieldContent>手机上网（4G）</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类四级类别</fieldChName><fieldEnName>complaintType4</fieldEnName><fieldContent>网速慢或掉线</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类五级类别</fieldChName><fieldEnName>complaintType5</fieldEnName><fieldContent>功能使用</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类六级类别</fieldChName><fieldEnName>complaintType6</fieldEnName><fieldContent>网速慢或网页无法打开</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类七级类别</fieldChName><fieldEnName>complaintType7</fieldEnName><fieldContent>室外</fieldContent></fieldInfo><fieldInfo><fieldChName>故障号码</fieldChName><fieldEnName>complaintNum</fieldEnName><fieldContent>13638815511</fieldContent></fieldInfo><fieldInfo><fieldChName>故障地点</fieldChName><fieldEnName>faultSite</fieldEnName><fieldContent>公园路168号</fieldContent></fieldInfo><fieldInfo><fieldChName>申告地</fieldChName><fieldEnName>mainComplaintPlace</fieldEnName><fieldContent>昭通</fieldContent></fieldInfo><fieldInfo><fieldChName>申告区县</fieldChName><fieldEnName>mainPlaceCountry</fieldEnName><fieldContent>昭阳区</fieldContent></fieldInfo><fieldInfo><fieldChName>CRM工单号</fieldChName><fieldEnName>crmSheetId</fieldEnName><fieldContent>20190923204423X663036206</fieldContent></fieldInfo><fieldInfo><fieldChName>客服中心派单时间</fieldChName><fieldEnName>mainCustomerSerTime</fieldEnName><fieldContent>2019-09-23 20:50:25.0</fieldContent></fieldInfo><fieldInfo><fieldChName>CCH系统定界处理时间</fieldChName><fieldEnName>mainFaultCchTime</fieldEnName><fieldContent>2019-09-23 21:22:30.0</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉定位建议1</fieldChName><fieldEnName>mainFaultCchTypeOne</fieldEnName><fieldContent>&gt;cause: 用户原因-未见网络异常, NE type: CGISAI;MSISDN, NE: CGISAI=泸水县上橄榄-LHHN-002,泸水县新寨-LHHN-002;MSISDN=15008850290, suggestion: 如果有大量业务请确认 1.用户业务是否已经恢复正常 2.用户是否对网速期望过高或抱怨的其他非网络问题 3.是否终端突发异常 4.是否用户抱怨的是高制式网络没有覆盖 如果业务量非常少 疑似无线侧存在覆盖或性能故障问题, other: 用户投诉前在小区 泸水县新寨-LHHN-002有web业务共6.000次 在小区 泸水县上橄榄-LHHN-002,泸水县新寨-LHHN-002有OTT业务共38.000次; 用户终端型号 VIVO VIVO Y51A; 4G OTT业务38.000次 ; 4G Web业务6.000次; 3G OTT业务0.000次; 3G Web业务0.000次; 2G OTT业务 0.000次 ; 2G Web业务0.000次</fieldContent></fieldInfo></recordInfo>";
//        Map<String,Object> map=xmlElements(s1);
//        System.out.println(map);

//        complaintType6

        DataController dataController=new DataController();
//        String sendSheet="<recordInfo><fieldInfo><fieldChName>网优投诉T1处理组（操作人）</fieldChName><fieldEnName>operateUser</fieldEnName><fieldContent>贾蓉</fieldContent></fieldInfo><fieldInfo><fieldChName>EOMS工单流水号</fieldChName><fieldEnName>sheetId</fieldEnName><fieldContent>YN-052-190923-00086</fieldContent></fieldInfo><fieldInfo><fieldChName>工单主题</fieldChName><fieldEnName>title</fieldEnName><fieldContent>移动业务→网络质量→手机上网（4G）→网速慢或掉线→功能使用→网速慢或网页无法打开→室外</fieldContent></fieldInfo><fieldInfo><fieldChName>派单时间</fieldChName><fieldEnName>sendTime</fieldEnName><fieldContent>2019-09-23 20:50:24</fieldContent></fieldInfo><fieldInfo><fieldChName>工单处理时限</fieldChName><fieldEnName>sheetCompleteLimit</fieldEnName><fieldContent>2019-09-27 14:50:32</fieldContent></fieldInfo><fieldInfo><fieldChName>紧急程度</fieldChName><fieldEnName>urgentDegree</fieldEnName><fieldContent>一般</fieldContent></fieldInfo><fieldInfo><fieldChName>故障时间</fieldChName><fieldEnName>faultTime</fieldEnName><fieldContent>2019-09-23 20:44:40</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉时间</fieldChName><fieldEnName>complaintTime</fieldEnName><fieldContent>2019-09-23 20:48:55</fieldContent></fieldInfo><fieldInfo><fieldChName>客户姓名</fieldChName><fieldEnName>customerName</fieldEnName><fieldContent>王**</fieldContent></fieldInfo><fieldInfo><fieldChName>客户电话</fieldChName><fieldEnName>customPhone</fieldEnName><fieldContent>13638815511</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉内容</fieldChName><fieldEnName>complaintDesc</fieldEnName><fieldContent>#同步要素#联系要求:本机 客户要求:客户来电反映，本机无法使用4G网络，网速慢，要求查询原因，并且尽快处理，谢谢 处于室内还是室外:室内 （室内）处于该场景下的具体位置:窗边 （数据）访问哪个网站出现问题:新浪 周围其他用户是否有同样情况:无 （室内）处于哪个场景:商业区（包括：酒店、休闲娱乐场所、大型场馆等） （室外）处于哪个场景:商业区（包括：酒店、大型场馆、休闲娱乐场馆等） 手机显示的网络标识:E 无信号出现的频率:总是 开关机情况:情况依旧#同步要素#</fieldContent></fieldInfo><fieldInfo><fieldChName>是否大面积投诉</fieldChName><fieldEnName>isWideComplaint</fieldEnName><fieldContent>否</fieldContent></fieldInfo><fieldInfo><fieldChName>客户级别</fieldChName><fieldEnName>customLevel</fieldEnName><fieldContent>三星</fieldContent></fieldInfo><fieldInfo><fieldChName>用户归属地</fieldChName><fieldEnName>customAttribution</fieldEnName><fieldContent>昭通</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类一级类别</fieldChName><fieldEnName>complaintType1</fieldEnName><fieldContent>移动业务</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类二级类别</fieldChName><fieldEnName>complaintType2</fieldEnName><fieldContent>网络质量</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类三级类别</fieldChName><fieldEnName>complaintType3</fieldEnName><fieldContent>手机上网（4G）</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类四级类别</fieldChName><fieldEnName>complaintType4</fieldEnName><fieldContent>网速慢或掉线</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类五级类别</fieldChName><fieldEnName>complaintType5</fieldEnName><fieldContent>功能使用</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类六级类别</fieldChName><fieldEnName>complaintType6</fieldEnName><fieldContent>网速慢或网页无法打开</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类七级类别</fieldChName><fieldEnName>complaintType7</fieldEnName><fieldContent>室外</fieldContent></fieldInfo><fieldInfo><fieldChName>故障号码</fieldChName><fieldEnName>complaintNum</fieldEnName><fieldContent>13638815511</fieldContent></fieldInfo><fieldInfo><fieldChName>故障地点</fieldChName><fieldEnName>faultSite</fieldEnName><fieldContent>公园路168号</fieldContent></fieldInfo><fieldInfo><fieldChName>申告地</fieldChName><fieldEnName>mainComplaintPlace</fieldEnName><fieldContent>昭通</fieldContent></fieldInfo><fieldInfo><fieldChName>申告区县</fieldChName><fieldEnName>mainPlaceCountry</fieldEnName><fieldContent>昭阳区</fieldContent></fieldInfo><fieldInfo><fieldChName>CRM工单号</fieldChName><fieldEnName>crmSheetId</fieldEnName><fieldContent>20190923204423X663036206</fieldContent></fieldInfo><fieldInfo><fieldChName>客服中心派单时间</fieldChName><fieldEnName>mainCustomerSerTime</fieldEnName><fieldContent>2019-09-23 20:50:25.0</fieldContent></fieldInfo><fieldInfo><fieldChName>CCH系统定界处理时间</fieldChName><fieldEnName>mainFaultCchTime</fieldEnName><fieldContent>2019-09-23 21:22:30.0</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉定位建议1</fieldChName><fieldEnName>mainFaultCchTypeOne</fieldEnName><fieldContent>&gt;cause: 用户原因-未见网络异常, NE type: CGISAI;MSISDN, NE: CGISAI=泸水县上橄榄-LHHN-002,泸水县新寨-LHHN-002;MSISDN=15008850290, suggestion: 如果有大量业务请确认 1.用户业务是否已经恢复正常 2.用户是否对网速期望过高或抱怨的其他非网络问题 3.是否终端突发异常 4.是否用户抱怨的是高制式网络没有覆盖 如果业务量非常少 疑似无线侧存在覆盖或性能故障问题, other: 用户投诉前在小区 泸水县新寨-LHHN-002有web业务共6.000次 在小区 泸水县上橄榄-LHHN-002,泸水县新寨-LHHN-002有OTT业务共38.000次; 用户终端型号 VIVO VIVO Y51A; 4G OTT业务38.000次 ; 4G Web业务6.000次; 3G OTT业务0.000次; 3G Web业务0.000次; 2G OTT业务 0.000次 ; 2G Web业务0.000次</fieldContent></fieldInfo></recordInfo>";

//        String sendSheet="<opDetail>" +
//                "<recordInfo>" +
//                "<fieldInfo><fieldChName>网优投诉T1处理组（操作人）</fieldChName><fieldEnName>operateUser</fieldEnName><fieldContent>贾蓉</fieldContent></fieldInfo><fieldInfo><fieldChName>EOMS工单流水号</fieldChName><fieldEnName>sheetId</fieldEnName><fieldContent>YN-052-190923-00086</fieldContent></fieldInfo><fieldInfo><fieldChName>工单主题</fieldChName><fieldEnName>title</fieldEnName><fieldContent>移动业务→网络质量→手机上网（4G）→网速慢或掉线→功能使用→网速慢或网页无法打开→室外</fieldContent></fieldInfo><fieldInfo><fieldChName>派单时间</fieldChName><fieldEnName>sendTime</fieldEnName><fieldContent>2019-09-23 20:50:24</fieldContent></fieldInfo><fieldInfo><fieldChName>工单处理时限</fieldChName><fieldEnName>sheetCompleteLimit</fieldEnName><fieldContent>2019-09-27 14:50:32</fieldContent></fieldInfo><fieldInfo><fieldChName>紧急程度</fieldChName><fieldEnName>urgentDegree</fieldEnName><fieldContent>一般</fieldContent></fieldInfo><fieldInfo><fieldChName>故障时间</fieldChName><fieldEnName>faultTime</fieldEnName><fieldContent>2019-09-23 20:44:40</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉时间</fieldChName><fieldEnName>complaintTime</fieldEnName><fieldContent>2019-09-23 20:48:55</fieldContent></fieldInfo><fieldInfo><fieldChName>客户姓名</fieldChName><fieldEnName>customerName</fieldEnName><fieldContent>王**</fieldContent></fieldInfo><fieldInfo><fieldChName>客户电话</fieldChName><fieldEnName>customPhone</fieldEnName><fieldContent>13638815511</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉内容</fieldChName><fieldEnName>complaintDesc</fieldEnName><fieldContent>#同步要素#联系要求:本机 客户要求:客户来电反映，本机无法使用4G网络，网速慢，要求查询原因，并且尽快处理，谢谢 处于室内还是室外:室内 （室内）处于该场景下的具体位置:窗边 （数据）访问哪个网站出现问题:新浪 周围其他用户是否有同样情况:无 （室内）处于哪个场景:商业区（包括：酒店、休闲娱乐场所、大型场馆等） （室外）处于哪个场景:商业区（包括：酒店、大型场馆、休闲娱乐场馆等） 手机显示的网络标识:E 无信号出现的频率:总是 开关机情况:情况依旧#同步要素#</fieldContent></fieldInfo><fieldInfo><fieldChName>是否大面积投诉</fieldChName><fieldEnName>isWideComplaint</fieldEnName><fieldContent>否</fieldContent></fieldInfo><fieldInfo><fieldChName>客户级别</fieldChName><fieldEnName>customLevel</fieldEnName><fieldContent>三星</fieldContent></fieldInfo><fieldInfo><fieldChName>用户归属地</fieldChName><fieldEnName>customAttribution</fieldEnName><fieldContent>昭通</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类一级类别</fieldChName><fieldEnName>complaintType1</fieldEnName><fieldContent>移动业务</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类二级类别</fieldChName><fieldEnName>complaintType2</fieldEnName><fieldContent>网络质量</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类三级类别</fieldChName><fieldEnName>complaintType3</fieldEnName><fieldContent>手机上网（4G）</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类四级类别</fieldChName><fieldEnName>complaintType4</fieldEnName><fieldContent>网速慢或掉线</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类五级类别</fieldChName><fieldEnName>complaintType5</fieldEnName><fieldContent>功能使用</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类六级类别</fieldChName><fieldEnName>complaintType6</fieldEnName><fieldContent>网速慢或网页无法打开</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类七级类别</fieldChName><fieldEnName>complaintType7</fieldEnName><fieldContent>室外</fieldContent></fieldInfo><fieldInfo><fieldChName>故障号码</fieldChName><fieldEnName>complaintNum</fieldEnName><fieldContent>13638815511</fieldContent></fieldInfo><fieldInfo><fieldChName>故障地点</fieldChName><fieldEnName>faultSite</fieldEnName><fieldContent>公园路168号</fieldContent></fieldInfo><fieldInfo><fieldChName>申告地</fieldChName><fieldEnName>mainComplaintPlace</fieldEnName><fieldContent>昭通</fieldContent></fieldInfo><fieldInfo><fieldChName>申告区县</fieldChName><fieldEnName>mainPlaceCountry</fieldEnName><fieldContent>昭阳区</fieldContent></fieldInfo><fieldInfo><fieldChName>CRM工单号</fieldChName><fieldEnName>crmSheetId</fieldEnName><fieldContent>20190923204423X6630362061</fieldContent></fieldInfo><fieldInfo><fieldChName>客服中心派单时间</fieldChName><fieldEnName>mainCustomerSerTime</fieldEnName><fieldContent>2019-09-23 20:50:25.0</fieldContent></fieldInfo><fieldInfo><fieldChName>CCH系统定界处理时间</fieldChName><fieldEnName>mainFaultCchTime</fieldEnName><fieldContent>2019-09-23 21:22:30.0</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉定位建议1</fieldChName><fieldEnName>mainFaultCchTypeOne</fieldEnName><fieldContent>&gt;cause: 用户原因-未见网络异常, NE type: CGISAI;MSISDN, NE: CGISAI=泸水县上橄榄-LHHN-002,泸水县新寨-LHHN-002;MSISDN=15008850290, suggestion: 如果有大量业务请确认 1.用户业务是否已经恢复正常 2.用户是否对网速期望过高或抱怨的其他非网络问题 3.是否终端突发异常 4.是否用户抱怨的是高制式网络没有覆盖 如果业务量非常少 疑似无线侧存在覆盖或性能故障问题, other: 用户投诉前在小区 泸水县新寨-LHHN-002有web业务共6.000次 在小区 泸水县上橄榄-LHHN-002,泸水县新寨-LHHN-002有OTT业务共38.000次; 用户终端型号 VIVO VIVO Y51A; 4G OTT业务38.000次 ; 4G Web业务6.000次; 3G OTT业务0.000次; 3G Web业务0.000次; 2G OTT业务 0.000次 ; 2G Web业务0.000次</fieldContent></fieldInfo>" +
//                "</recordInfo>" +
//                "<recordInfo>" +
//                "<fieldInfo><fieldChName>网优投诉T1处理组（操作人）</fieldChName><fieldEnName>operateUser</fieldEnName><fieldContent>贾蓉</fieldContent></fieldInfo><fieldInfo><fieldChName>EOMS工单流水号</fieldChName><fieldEnName>sheetId</fieldEnName><fieldContent>YN-052-190923-00086</fieldContent></fieldInfo><fieldInfo><fieldChName>工单主题</fieldChName><fieldEnName>title</fieldEnName><fieldContent>移动业务→网络质量→手机上网（4G）→网速慢或掉线→功能使用→网速慢或网页无法打开→室外</fieldContent></fieldInfo><fieldInfo><fieldChName>派单时间</fieldChName><fieldEnName>sendTime</fieldEnName><fieldContent>2019-09-23 20:50:24</fieldContent></fieldInfo><fieldInfo><fieldChName>工单处理时限</fieldChName><fieldEnName>sheetCompleteLimit</fieldEnName><fieldContent>2019-09-27 14:50:32</fieldContent></fieldInfo><fieldInfo><fieldChName>紧急程度</fieldChName><fieldEnName>urgentDegree</fieldEnName><fieldContent>一般</fieldContent></fieldInfo><fieldInfo><fieldChName>故障时间</fieldChName><fieldEnName>faultTime</fieldEnName><fieldContent>2019-09-23 20:44:40</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉时间</fieldChName><fieldEnName>complaintTime</fieldEnName><fieldContent>2019-09-23 20:48:55</fieldContent></fieldInfo><fieldInfo><fieldChName>客户姓名</fieldChName><fieldEnName>customerName</fieldEnName><fieldContent>王**</fieldContent></fieldInfo><fieldInfo><fieldChName>客户电话</fieldChName><fieldEnName>customPhone</fieldEnName><fieldContent>13638815511</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉内容</fieldChName><fieldEnName>complaintDesc</fieldEnName><fieldContent>#同步要素#联系要求:本机 客户要求:客户来电反映，本机无法使用4G网络，网速慢，要求查询原因，并且尽快处理，谢谢 处于室内还是室外:室内 （室内）处于该场景下的具体位置:窗边 （数据）访问哪个网站出现问题:新浪 周围其他用户是否有同样情况:无 （室内）处于哪个场景:商业区（包括：酒店、休闲娱乐场所、大型场馆等） （室外）处于哪个场景:商业区（包括：酒店、大型场馆、休闲娱乐场馆等） 手机显示的网络标识:E 无信号出现的频率:总是 开关机情况:情况依旧#同步要素#</fieldContent></fieldInfo><fieldInfo><fieldChName>是否大面积投诉</fieldChName><fieldEnName>isWideComplaint</fieldEnName><fieldContent>否</fieldContent></fieldInfo><fieldInfo><fieldChName>客户级别</fieldChName><fieldEnName>customLevel</fieldEnName><fieldContent>三星</fieldContent></fieldInfo><fieldInfo><fieldChName>用户归属地</fieldChName><fieldEnName>customAttribution</fieldEnName><fieldContent>昭通</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类一级类别</fieldChName><fieldEnName>complaintType1</fieldEnName><fieldContent>移动业务</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类二级类别</fieldChName><fieldEnName>complaintType2</fieldEnName><fieldContent>网络质量</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类三级类别</fieldChName><fieldEnName>complaintType3</fieldEnName><fieldContent>手机上网（4G）</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类四级类别</fieldChName><fieldEnName>complaintType4</fieldEnName><fieldContent>网速慢或掉线</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类五级类别</fieldChName><fieldEnName>complaintType5</fieldEnName><fieldContent>功能使用</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类六级类别</fieldChName><fieldEnName>complaintType6</fieldEnName><fieldContent>网速慢或网页无法打开</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类七级类别</fieldChName><fieldEnName>complaintType7</fieldEnName><fieldContent>室外</fieldContent></fieldInfo><fieldInfo><fieldChName>故障号码</fieldChName><fieldEnName>complaintNum</fieldEnName><fieldContent>13638815511</fieldContent></fieldInfo><fieldInfo><fieldChName>故障地点</fieldChName><fieldEnName>faultSite</fieldEnName><fieldContent>公园路168号</fieldContent></fieldInfo><fieldInfo><fieldChName>申告地</fieldChName><fieldEnName>mainComplaintPlace</fieldEnName><fieldContent>昭通</fieldContent></fieldInfo><fieldInfo><fieldChName>申告区县</fieldChName><fieldEnName>mainPlaceCountry</fieldEnName><fieldContent>昭阳区</fieldContent></fieldInfo><fieldInfo><fieldChName>CRM工单号</fieldChName><fieldEnName>crmSheetId</fieldEnName><fieldContent>20190923204423X6630362062</fieldContent></fieldInfo><fieldInfo><fieldChName>客服中心派单时间</fieldChName><fieldEnName>mainCustomerSerTime</fieldEnName><fieldContent>2019-09-23 20:50:25.0</fieldContent></fieldInfo><fieldInfo><fieldChName>CCH系统定界处理时间</fieldChName><fieldEnName>mainFaultCchTime</fieldEnName><fieldContent>2019-09-23 21:22:30.0</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉定位建议1</fieldChName><fieldEnName>mainFaultCchTypeOne</fieldEnName><fieldContent>&gt;cause: 用户原因-未见网络异常, NE type: CGISAI;MSISDN, NE: CGISAI=泸水县上橄榄-LHHN-002,泸水县新寨-LHHN-002;MSISDN=15008850290, suggestion: 如果有大量业务请确认 1.用户业务是否已经恢复正常 2.用户是否对网速期望过高或抱怨的其他非网络问题 3.是否终端突发异常 4.是否用户抱怨的是高制式网络没有覆盖 如果业务量非常少 疑似无线侧存在覆盖或性能故障问题, other: 用户投诉前在小区 泸水县新寨-LHHN-002有web业务共6.000次 在小区 泸水县上橄榄-LHHN-002,泸水县新寨-LHHN-002有OTT业务共38.000次; 用户终端型号 VIVO VIVO Y51A; 4G OTT业务38.000次 ; 4G Web业务6.000次; 3G OTT业务0.000次; 3G Web业务0.000次; 2G OTT业务 0.000次 ; 2G Web业务0.000次</fieldContent></fieldInfo>" +
//                "</recordInfo>" +
//                "</opDetail>" ;


//        String sendSheet="123";



//        String result2= dataController.invoke("http://183.238.63.208:62113/axis/services/EomsService","SheetInfoSer",sendSheet);
//          String result2= dataController.invoke("http://localhost:8083/axis/services/EomsService","SheetInfoSer",sendSheet);
//        String result2= dataController.invoke("http://192.168.9.133:8080/axis/services/StudentService","getStudentMarks",sendSheet);

//        String result2= dataController.invoke("http://192.168.20.170:8131/axis/services/EomsService","SheetInfoSer",sendSheet);




//        System.out.println(result2);


      /*  dataController.testAxis();*/
    }

    /**
     * 解析xml字符串返回一个Map
     *
     * @param xmlDoc
     * @return Map
     */
    public static Map<String,Object> xmlElements(String xmlDoc) {
        Map<String,Object> map = new HashMap();
        // 创建一个新的字符串
        StringReader read = new StringReader(xmlDoc);
        // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        // 创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        try {
            // 通过输入源构造一个Document
            Document doc = sb.build(source);
            // 取的根元素
            Element root = doc.getRootElement();
            // 得到根元素所有子元素的集合
            List jiedian = root.getChildren();
            // 获得XML中的命名空间（XML中未定义可不写）
            Element et = null;
            for (int i = 0; i < jiedian.size(); i++) {
                // 循环依次得到子元素
                et = (Element) jiedian.get(i);
                List<Content> et2=et.getContent();
                    String name=((Element)et2.get(1)).getContent().get(0).getValue();
                    String value=((Element)et2.get(2)).getContent().get(0).getValue();
                    map.put(name, value);
            }
        } catch (JDOMException e) {
            e.printStackTrace();
            System.out.println("出错");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("出错");
        }
        read.close();
        return map;
    }




}

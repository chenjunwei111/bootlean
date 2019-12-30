package com.cjw.boot.test.algor.testjava;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import javax.xml.namespace.QName;


/**
 * Description 测试axis
 *
 * @Author junwei
 * @Date 15:02 2019/9/29
 **/
public class TestAxis {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
//        test5();
    }


 /*   public static void test1() {

        DataController dataController = new DataController();

        String sendSheet = "<opDetail>" +
                "<recordInfo>" +
                "<fieldInfo><fieldChName>网优投诉T1处理组（操作人）</fieldChName><fieldEnName>operateUser</fieldEnName><fieldContent>贾蓉</fieldContent></fieldInfo><fieldInfo><fieldChName>EOMS工单流水号</fieldChName><fieldEnName>sheetId</fieldEnName><fieldContent>YN-052-190923-00086</fieldContent></fieldInfo><fieldInfo><fieldChName>工单主题</fieldChName><fieldEnName>title</fieldEnName><fieldContent>移动业务→网络质量→手机上网（4G）→网速慢或掉线→功能使用→网速慢或网页无法打开→室外</fieldContent></fieldInfo><fieldInfo><fieldChName>派单时间</fieldChName><fieldEnName>sendTime</fieldEnName><fieldContent>2019-09-23 20:50:24</fieldContent></fieldInfo><fieldInfo><fieldChName>工单处理时限</fieldChName><fieldEnName>sheetCompleteLimit</fieldEnName><fieldContent>2019-09-27 14:50:32</fieldContent></fieldInfo><fieldInfo><fieldChName>紧急程度</fieldChName><fieldEnName>urgentDegree</fieldEnName><fieldContent>一般</fieldContent></fieldInfo><fieldInfo><fieldChName>故障时间</fieldChName><fieldEnName>faultTime</fieldEnName><fieldContent>2019-09-23 20:44:40</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉时间</fieldChName><fieldEnName>complaintTime</fieldEnName><fieldContent>2019-09-23 20:48:55</fieldContent></fieldInfo><fieldInfo><fieldChName>客户姓名</fieldChName><fieldEnName>customerName</fieldEnName><fieldContent>王**</fieldContent></fieldInfo><fieldInfo><fieldChName>客户电话</fieldChName><fieldEnName>customPhone</fieldEnName><fieldContent>13638815511</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉内容</fieldChName><fieldEnName>complaintDesc</fieldEnName><fieldContent>#同步要素#联系要求:本机 客户要求:客户来电反映，本机无法使用4G网络，网速慢，要求查询原因，并且尽快处理，谢谢 处于室内还是室外:室内 （室内）处于该场景下的具体位置:窗边 （数据）访问哪个网站出现问题:新浪 周围其他用户是否有同样情况:无 （室内）处于哪个场景:商业区（包括：酒店、休闲娱乐场所、大型场馆等） （室外）处于哪个场景:商业区（包括：酒店、大型场馆、休闲娱乐场馆等） 手机显示的网络标识:E 无信号出现的频率:总是 开关机情况:情况依旧#同步要素#</fieldContent></fieldInfo><fieldInfo><fieldChName>是否大面积投诉</fieldChName><fieldEnName>isWideComplaint</fieldEnName><fieldContent>否</fieldContent></fieldInfo><fieldInfo><fieldChName>客户级别</fieldChName><fieldEnName>customLevel</fieldEnName><fieldContent>三星</fieldContent></fieldInfo><fieldInfo><fieldChName>用户归属地</fieldChName><fieldEnName>customAttribution</fieldEnName><fieldContent>昭通</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类一级类别</fieldChName><fieldEnName>complaintType1</fieldEnName><fieldContent>移动业务</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类二级类别</fieldChName><fieldEnName>complaintType2</fieldEnName><fieldContent>网络质量</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类三级类别</fieldChName><fieldEnName>complaintType3</fieldEnName><fieldContent>手机上网（4G）</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类四级类别</fieldChName><fieldEnName>complaintType4</fieldEnName><fieldContent>网速慢或掉线</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类五级类别</fieldChName><fieldEnName>complaintType5</fieldEnName><fieldContent>功能使用</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类六级类别</fieldChName><fieldEnName>complaintType6</fieldEnName><fieldContent>网速慢或网页无法打开</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类七级类别</fieldChName><fieldEnName>complaintType7</fieldEnName><fieldContent>室外</fieldContent></fieldInfo><fieldInfo><fieldChName>故障号码</fieldChName><fieldEnName>complaintNum</fieldEnName><fieldContent>13638815511</fieldContent></fieldInfo><fieldInfo><fieldChName>故障地点</fieldChName><fieldEnName>faultSite</fieldEnName><fieldContent>公园路168号</fieldContent></fieldInfo><fieldInfo><fieldChName>申告地</fieldChName><fieldEnName>mainComplaintPlace</fieldEnName><fieldContent>昭通</fieldContent></fieldInfo><fieldInfo><fieldChName>申告区县</fieldChName><fieldEnName>mainPlaceCountry</fieldEnName><fieldContent>昭阳区</fieldContent></fieldInfo><fieldInfo><fieldChName>CRM工单号</fieldChName><fieldEnName>crmSheetId</fieldEnName><fieldContent>20190923204423X6630362061</fieldContent></fieldInfo><fieldInfo><fieldChName>客服中心派单时间</fieldChName><fieldEnName>mainCustomerSerTime</fieldEnName><fieldContent>2019-09-23 20:50:25.0</fieldContent></fieldInfo><fieldInfo><fieldChName>CCH系统定界处理时间</fieldChName><fieldEnName>mainFaultCchTime</fieldEnName><fieldContent>2019-09-23 21:22:30.0</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉定位建议1</fieldChName><fieldEnName>mainFaultCchTypeOne</fieldEnName><fieldContent>&gt;cause: 用户原因-未见网络异常, NE type: CGISAI;MSISDN, NE: CGISAI=泸水县上橄榄-LHHN-002,泸水县新寨-LHHN-002;MSISDN=15008850290, suggestion: 如果有大量业务请确认 1.用户业务是否已经恢复正常 2.用户是否对网速期望过高或抱怨的其他非网络问题 3.是否终端突发异常 4.是否用户抱怨的是高制式网络没有覆盖 如果业务量非常少 疑似无线侧存在覆盖或性能故障问题, other: 用户投诉前在小区 泸水县新寨-LHHN-002有web业务共6.000次 在小区 泸水县上橄榄-LHHN-002,泸水县新寨-LHHN-002有OTT业务共38.000次; 用户终端型号 VIVO VIVO Y51A; 4G OTT业务38.000次 ; 4G Web业务6.000次; 3G OTT业务0.000次; 3G Web业务0.000次; 2G OTT业务 0.000次 ; 2G Web业务0.000次</fieldContent></fieldInfo>" +
                "</recordInfo>" +
//                "<recordInfo>" +
//                "<fieldInfo><fieldChName>网优投诉T1处理组（操作人）</fieldChName><fieldEnName>operateUser</fieldEnName><fieldContent>贾蓉</fieldContent></fieldInfo><fieldInfo><fieldChName>EOMS工单流水号</fieldChName><fieldEnName>sheetId</fieldEnName><fieldContent>YN-052-190923-00086</fieldContent></fieldInfo><fieldInfo><fieldChName>工单主题</fieldChName><fieldEnName>title</fieldEnName><fieldContent>移动业务→网络质量→手机上网（4G）→网速慢或掉线→功能使用→网速慢或网页无法打开→室外</fieldContent></fieldInfo><fieldInfo><fieldChName>派单时间</fieldChName><fieldEnName>sendTime</fieldEnName><fieldContent>2019-09-23 20:50:24</fieldContent></fieldInfo><fieldInfo><fieldChName>工单处理时限</fieldChName><fieldEnName>sheetCompleteLimit</fieldEnName><fieldContent>2019-09-27 14:50:32</fieldContent></fieldInfo><fieldInfo><fieldChName>紧急程度</fieldChName><fieldEnName>urgentDegree</fieldEnName><fieldContent>一般</fieldContent></fieldInfo><fieldInfo><fieldChName>故障时间</fieldChName><fieldEnName>faultTime</fieldEnName><fieldContent>2019-09-23 20:44:40</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉时间</fieldChName><fieldEnName>complaintTime</fieldEnName><fieldContent>2019-09-23 20:48:55</fieldContent></fieldInfo><fieldInfo><fieldChName>客户姓名</fieldChName><fieldEnName>customerName</fieldEnName><fieldContent>王**</fieldContent></fieldInfo><fieldInfo><fieldChName>客户电话</fieldChName><fieldEnName>customPhone</fieldEnName><fieldContent>13638815511</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉内容</fieldChName><fieldEnName>complaintDesc</fieldEnName><fieldContent>#同步要素#联系要求:本机 客户要求:客户来电反映，本机无法使用4G网络，网速慢，要求查询原因，并且尽快处理，谢谢 处于室内还是室外:室内 （室内）处于该场景下的具体位置:窗边 （数据）访问哪个网站出现问题:新浪 周围其他用户是否有同样情况:无 （室内）处于哪个场景:商业区（包括：酒店、休闲娱乐场所、大型场馆等） （室外）处于哪个场景:商业区（包括：酒店、大型场馆、休闲娱乐场馆等） 手机显示的网络标识:E 无信号出现的频率:总是 开关机情况:情况依旧#同步要素#</fieldContent></fieldInfo><fieldInfo><fieldChName>是否大面积投诉</fieldChName><fieldEnName>isWideComplaint</fieldEnName><fieldContent>否</fieldContent></fieldInfo><fieldInfo><fieldChName>客户级别</fieldChName><fieldEnName>customLevel</fieldEnName><fieldContent>三星</fieldContent></fieldInfo><fieldInfo><fieldChName>用户归属地</fieldChName><fieldEnName>customAttribution</fieldEnName><fieldContent>昭通</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类一级类别</fieldChName><fieldEnName>complaintType1</fieldEnName><fieldContent>移动业务</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类二级类别</fieldChName><fieldEnName>complaintType2</fieldEnName><fieldContent>网络质量</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类三级类别</fieldChName><fieldEnName>complaintType3</fieldEnName><fieldContent>手机上网（4G）</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类四级类别</fieldChName><fieldEnName>complaintType4</fieldEnName><fieldContent>网速慢或掉线</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类五级类别</fieldChName><fieldEnName>complaintType5</fieldEnName><fieldContent>功能使用</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类六级类别</fieldChName><fieldEnName>complaintType6</fieldEnName><fieldContent>网速慢或网页无法打开</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉分类七级类别</fieldChName><fieldEnName>complaintType7</fieldEnName><fieldContent>室外</fieldContent></fieldInfo><fieldInfo><fieldChName>故障号码</fieldChName><fieldEnName>complaintNum</fieldEnName><fieldContent>13638815511</fieldContent></fieldInfo><fieldInfo><fieldChName>故障地点</fieldChName><fieldEnName>faultSite</fieldEnName><fieldContent>公园路168号</fieldContent></fieldInfo><fieldInfo><fieldChName>申告地</fieldChName><fieldEnName>mainComplaintPlace</fieldEnName><fieldContent>昭通</fieldContent></fieldInfo><fieldInfo><fieldChName>申告区县</fieldChName><fieldEnName>mainPlaceCountry</fieldEnName><fieldContent>昭阳区</fieldContent></fieldInfo><fieldInfo><fieldChName>CRM工单号</fieldChName><fieldEnName>crmSheetId</fieldEnName><fieldContent>20190923204423X6630362062</fieldContent></fieldInfo><fieldInfo><fieldChName>客服中心派单时间</fieldChName><fieldEnName>mainCustomerSerTime</fieldEnName><fieldContent>2019-09-23 20:50:25.0</fieldContent></fieldInfo><fieldInfo><fieldChName>CCH系统定界处理时间</fieldChName><fieldEnName>mainFaultCchTime</fieldEnName><fieldContent>2019-09-23 21:22:30.0</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉定位建议1</fieldChName><fieldEnName>mainFaultCchTypeOne</fieldEnName><fieldContent>&gt;cause: 用户原因-未见网络异常, NE type: CGISAI;MSISDN, NE: CGISAI=泸水县上橄榄-LHHN-002,泸水县新寨-LHHN-002;MSISDN=15008850290, suggestion: 如果有大量业务请确认 1.用户业务是否已经恢复正常 2.用户是否对网速期望过高或抱怨的其他非网络问题 3.是否终端突发异常 4.是否用户抱怨的是高制式网络没有覆盖 如果业务量非常少 疑似无线侧存在覆盖或性能故障问题, other: 用户投诉前在小区 泸水县新寨-LHHN-002有web业务共6.000次 在小区 泸水县上橄榄-LHHN-002,泸水县新寨-LHHN-002有OTT业务共38.000次; 用户终端型号 VIVO VIVO Y51A; 4G OTT业务38.000次 ; 4G Web业务6.000次; 3G OTT业务0.000次; 3G Web业务0.000次; 2G OTT业务 0.000次 ; 2G Web业务0.000次</fieldContent></fieldInfo>" +
//                "</recordInfo>" +
                "</opDetail>";

        sendSheet = "已方测试";
        //        String result2= dataController.invoke("http://183.238.63.208:62113/axis/services/EomsService","SheetInfoSer",sendSheet);
//        String result2= dataController.invoke("http://localhost:8083/axis/services/EomsService","SheetInfoSer",sendSheet);

//        String result2= dataController.invoke("http://192.168.20.170:8131/axis/services/EomsService","testSpdb",sendSheet);
        String result2 = dataController.invoke("http://39.129.4.172:3480/axis/services/EomsService", "testSpdb", sendSheet);
        System.out.println(result2);
    }


    public static void test2() {

        DataController dataController = new DataController();

        String sendSheet = "<attachRef>\n" +
                "  <attachInfo>\n" +
                "    <attachName>20190522142930303.xls</attachName>\n" +
                "    <attachURL>ftp://10.174.239.221/export/eoms35/uploadfile/accessories/uploadfile/sheet/complaint/20190522142930303.xls</attachURL>\n" +
                "    <attachLength>70656</attachLength>\n" +
                "  </attachInfo>\n" +
                "</attachRef>";

//        String result2= dataController.invoke("http://183.238.63.208:62113/axis/services/EomsService","SheetInfoFtp",sendSheet);
        String result2 = dataController.invoke("http://localhost:8083/axis/services/EomsService", "SheetInfoFtp", sendSheet);

//        String result2= dataController.invoke("http://192.168.20.170:8131/axis/services/EomsService","SheetInfoFtp",sendSheet);

        System.out.println(result2);
    }*/


  /*  public static void test3() {
        DataController dataController = new DataController();

        String sendSheet = "20190722X15622141251,20180412191850X930132624";

        String result2 = dataController.invoke("http://localhost:8083/axis/services/EomsService", "turnAnalyReport", sendSheet);

        System.out.println(result2);
    }*/


    /**
     * Description axis2.0测试
     *
     * @Author junwei
     * @Date 11:23 2019/10/22
     **/
    public static void test4() {
        try {
            // 使用RPC方式调用WebService
            RPCServiceClient serviceClient = new RPCServiceClient();
            // 创建WSDL的URL，注意不是服务地址
//            String url = "http://192.168.20.170:8131/axis2/services/spdbAxis2?wsdl";
            String url = "http://localhost:8080/axis2/services/spdbAxis2?wsdl";

            String path1="<opDetail><recordInfo><fieldInfo><fieldChName>网优投诉T1处理组（操作人）</fieldChName><fieldEnName>operateUser</fieldEnName><fieldContent>邓正平</fieldContent></fieldInfo><fieldInfo><fieldChName>EOMS工单流水号</fieldChName><fieldEnName>sheetId</fieldEnName><fieldContent>YN-052-191025-00085</fieldContent></fieldInfo><fieldInfo><fieldChName>工单主题</fieldChName><fieldEnName>title</fieldEnName><fieldContent>移动业务→网络质量→手机上网（4G）→网速慢或掉线→功能使用→网速慢或网页无法打开→室内</fieldContent></fieldInfo><fieldInfo><fieldChName>派单时间</fieldChName><fieldEnName>sendTime</fieldEnName><fieldContent>2019-10-25 10:52:32</fieldContent></fieldInfo><fieldInfo><fieldChName>工单处理时限</fieldChName><fieldEnName>sheetCompleteLimit</fieldEnName><fieldContent>2019-10-24 02:55:27</fieldContent></fieldInfo><fieldInfo><fieldChName>紧急程度</fieldChName><fieldEnName>urgentDegree</fieldEnName><fieldContent>一般</fieldContent></fieldInfo><fieldInfo><fieldChName>故障时间</fieldChName><fieldEnName>faultTime</fieldEnName><fieldContent></fieldContent></fieldInfo><fieldInfo><fieldChName>投诉时间</fieldChName><fieldEnName>complaintTime</fieldEnName><fieldContent>2019-10-22 08:51:03</fieldContent></fieldInfo><fieldInfo><fieldChName>客户电话</fieldChName><fieldEnName>customPhone</fieldEnName><fieldContent>15987760057</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉内容</fieldChName><fieldEnName>complaintDesc</fieldEnName><fieldContent>#同步要素#联系要求:随时本机\n" +
                    "客户要求:客户要去帮忙处理一下在腾跃镇秀峰社区吉昌小区62号1楼没有信号的问题\n" +
                    "处于室内还是室外:室内\n" +
                    "（室内）处于该场景下的具体位置:在房间里没有信号\n" +
                    "（数据）访问哪个网站出现问题:新浪\n" +
                    "周围其他用户是否有同样情况:有\n" +
                    "（室内）处于哪个场景:商业区（包括：酒店、休闲娱乐场所、大型场馆等）\n" +
                    "（室外）处于哪个场景:商业区（包括：酒店、大型场馆、休闲娱乐场馆等）\n" +
                    "手机显示的网络标识:E\n" +
                    "无信号出现的频率:总是\n" +
                    "开关机情况:情况依旧#同步要素#</fieldContent></fieldInfo><fieldInfo><fieldChName>是否大面积投诉</fieldChName><fieldEnName>isWideComplaint</fieldEnName><fieldContent>否</fieldContent></fieldInfo><fieldInfo><fieldChName>客户级别</fieldChName><fieldEnName>customLevel</fieldEnName><fieldContent>三星</fieldContent></fieldInfo><fieldInfo><fieldChName>用户归属地</fieldChName><fieldEnName>customAttribution</fieldEnName><fieldContent>保山</fieldContent></fieldInfo><fieldInfo><fieldChName>故障号码</fieldChName><fieldEnName>complaintNum</fieldEnName><fieldContent>15987760057</fieldContent></fieldInfo><fieldInfo><fieldChName>故障地点</fieldChName><fieldEnName>faultSite</fieldEnName><fieldContent>腾跃镇秀峰社区吉昌小区62号1楼</fieldContent></fieldInfo><fieldInfo><fieldChName>申告地</fieldChName><fieldEnName>mainComplaintPlace</fieldEnName><fieldContent>保山</fieldContent></fieldInfo><fieldInfo><fieldChName>申告区县</fieldChName><fieldEnName>mainPlaceCountry</fieldEnName><fieldContent>腾冲县</fieldContent></fieldInfo><fieldInfo><fieldChName>CRM工单号</fieldChName><fieldEnName>crmSheetId</fieldEnName><fieldContent>20191022084442X829184610</fieldContent></fieldInfo><fieldInfo><fieldChName>客服中心派单时间</fieldChName><fieldEnName>mainCustomerSerTime</fieldEnName><fieldContent>2019-10-22 08:55:24</fieldContent></fieldInfo><fieldInfo><fieldChName>CCH系统定界处理时间</fieldChName><fieldEnName>mainFaultCchTime</fieldEnName><fieldContent>2019-10-22 09:03:07</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉定位建议1</fieldChName><fieldEnName>mainFaultCchTypeOne</fieldEnName><fieldContent>&gt;cause: 用户原因-未见网络异常, NE type: CGISAI;MSISDN, NE: CGISAI=腾冲县腾越翡翠古镇-LHHX-ZD-4,腾冲县曲石双龙村-LHHN-001,腾冲县小西村-LHHN-001,腾冲县北海盈水下河村-LHHN-132,腾冲县曲石双龙路口-LHHN-133,腾冲县北海青海湖-LHHN-131,腾冲县曲石双龙小学-LHHN-YZ-2,腾冲县曲石双龙水源-LHHN-131,腾冲县腾越翡翠古镇-LHHX-ZD-6,腾冲县腾越翡翠古镇-LHHX-026;MSISDN=15987760057, suggestion: 如果有大量业务请确认 1.用户业务是否已经恢复正常 2.用户是否对网速期望过高或抱怨的其他非网络问题 3.是否终端突发异常 4.是否用户抱怨的是高制式网络没有覆盖  如果业务量非常少 疑似无线侧存在覆盖或性能故障问题, other: 用户投诉前在小区 有web业务共0次 在小区 腾冲县小西村-LHHN-001,腾冲县北海\n" +
                    "盈水下河村-LHHN-132,腾冲县曲石双龙村-LHHN-001有OTT业务共42.000次; 用户终端型号  ; 4G OTT业务42.000次 ; 4G Web业务0次; 3G OTT业务0.000次; 3G Web业务0次; 2G OTT业务 0.000次 ; 2G Web业务0次</fieldContent></fieldInfo><fieldInfo><fieldChName>投诉定位建议2</fieldChName><fieldEnName>mainFaultCchTypeTwo</fieldEnName><fieldContent></fieldContent></fieldInfo><fieldInfo><fieldChName>\n" +
                    "投诉定位建议3</fieldChName><fieldEnName>mainFaultCchTypeThree</fieldEnName><fieldContent></fieldContent></fieldInfo><fieldInfo><fieldChName>投诉定位建议4</fieldChName><fieldEnName>mainFaultCchTypeFour</fieldEnName><fieldContent></fieldContent></fieldInfo><fieldInfo><fieldChName>投诉定位建议5</fieldChName><fieldEnName>mainFaultCchTypeFive</fieldEnName><fieldContent></fieldContent></fieldInfo></recordInfo></opDetail>";


            String path2="<?xml version=\"1.0\" encoding=\"GBK\"?><attachRef><attachInfo><attachName>20191022093947634.zip</attachName><attachURL>ftp://10.174.239.221_eomsftp_eoms231*/export/eoms35/uploadfile/accessories/uploadfile/sheet/complaint/20191022093947634.zip</attachURL><attachLength>3042</attachLength></attachInfo></attachRef>";


            // 指定调用WebService的URL
            EndpointReference targetEPR = new EndpointReference(url);
            Options options = serviceClient.getOptions();
            // 确定目标服务地址
            options.setTo(targetEPR);
            // 确定调用方法（wsdl 命名空间地址 (wsdl文档中的targetNamespace) 和 方法名称 的组合）
            options.setAction("http://impl.axis2.com/SheetInfoSer");
            // 指定方法的参数值
            Object[] parameters = new Object[]{path1, path2};

            // 创建服务名称
            // 1.namespaceURI - 命名空间地址 (wsdl文档中的targetNamespace)
            // 2.localPart - 服务视图名 (wsdl文档中operation的方法名称，例如<wsdl:operation name="getMobileCodeInfo">)
            QName qname = new QName("http://impl.axis2.com", "SheetInfoSer");


            // 调用方法一 传递参数，调用服务，获取服务返回结果集
            OMElement element = serviceClient.invokeBlocking(qname, parameters);
            System.out.println(element);
            /*
             * 值得注意的是，返回结果就是一段由OMElement对象封装的xml字符串。
             * 我们可以对之灵活应用,下面我取第一个元素值，并打印之。因为调用的方法返回一个结果
             */
            String result = element.getFirstElement().getText();
            System.out.println(result);


            // 调用方法二 getPrice方法并输出该方法的返回值
            // 指定方法返回值的数据类型的Class对象
//            Class[] returnTypes = new Class[]{String.class};
//            Object[] response = serviceClient.invokeBlocking(qname, parameters, returnTypes);
//            String r = (String) response[0];
//            System.out.println(r);

        } catch (AxisFault e) {
            e.printStackTrace();
        }
    }


    /**
     * Description
     *
     * @Author junwei
     * @Date 15:42 2019/10/22
     **/
    public static void test5() {
        try {
            RPCServiceClient serviceClient = new RPCServiceClient();
            Options options = serviceClient.getOptions();
//            String url = "http://192.168.20.170:8131/axis2/services/spdbAxis2?wsdl";
            String url = "http://localhost:8080/axis2/services/spdbAxis2?wsdl";

            EndpointReference epr = new EndpointReference(url);

            options.setTo(epr);
            //示例 QName  qname = new QName("http://service.esb.inspur.com/", "sendSheet");
            QName  qname = new QName("http://impl.axis2.com", "testSpdb");
            Object[] result= null;
            result = serviceClient.invokeBlocking(qname, new Object[] {"参数1","参数2","参数3"},new Class[] { String.class, String.class});
            System.out.println("返回："+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

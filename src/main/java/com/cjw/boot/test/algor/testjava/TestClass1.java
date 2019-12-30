package com.cjw.boot.test.algor.testjava;

import java.io.UnsupportedEncodingException;

/**
* Description 代码测试
* @Author junwei
* @Date 17:02 2019/9/25
**/
public  class TestClass1 {


    public static void main(String[] args) throws UnsupportedEncodingException {
        TestClass1 testClass1=new TestClass1();
         testClass1.test5();
    }


    //标识符测试
    public  void test1(){
        String a1="123";
        String _1="123";
        String $23="123";
//        String 1a="";
//        String #3="";
        String fruit="123";
        System.out.println(fruit);
    }


    //标识符常量
    public  final void  test2(){
        System.out.println(3);
    }


    public void test3() throws UnsupportedEncodingException {
// 运行结果：2
            System.out.println("测试".getBytes("ISO8859-1").length);
// 运行结果：4
            System.out.println("测试".getBytes("GB2312").length);
// 运行结果：4
            System.out.println("测试".getBytes("GBK").length);
// 运行结果：6
            System.out.println("测试".getBytes("UTF-8").length);
    }


    public  void test4(){
        Byte s1=00000101;
        System.out.println(s1);
    }


    public void test5(){
//       short 短整形 最大为32767（5位）
//        short a1=32767;
//        System.out.println(a1);

        //int 整形 最大为2147483647（10位）
//        int a2=2147483647;
//        System.out.println(a2);

        //long 长整型 最大为9223372036854775807L(16位)
//        long a3=922337203685477580L;
//        System.out.println(a3);

        //float 单精度浮点型 最大小数点8位
//        float a4=0.12345678F;
//        System.out.println(a4);

//        最大小数点16位
//        double a5=0.1234567890123456;
//        System.out.println(a5);

//        char a6='陈';
//        char a7='c';
//        char a8='1';
//        char a9=6556;
//        char a10=99;
//        System.out.println(a6);//陈
//        System.out.println(a7);//c
//        System.out.println(a8);//1
//        System.out.println(a9);//ᦜ
//        System.out.println(a10);// c
//        System.out.println(a7==a10);// true


//         String s1="123";
//         System.out.println(s1.charAt(0));
//
//        System.out.print("正序：");
//        for (int i = 0; i <s1.length() ; i++) {
//            System.out.print(s1.charAt(i));
//        }
//
//        System.out.println();
//
//        System.out.print("反序：");
//        for (int i = s1.length()-1; i >=0 ; i--) {
//            System.out.print(s1.charAt(i));
//        }


//        String s2="1,2,3,4,5,6";
//        String s3[]=s2.split(",");
//        for (String s:s3){
//            System.out.println(s);
//        }


//         String s3="123";
//        System.out.println(s3.contains("1"));

//        System.out.println(s3.toCharArray());
//        char s4[]=s3.toCharArray();
//        for(char s:s4){
//            System.out.println(s4);
//        }


//        String s4="123";
//        String s5=new String("123");
//        String s6=new String("123");
//        System.out.println(s4=="123");
//        System.out.println(s5=="123");
//        System.out.println(s5==s6);


//        String s0 = "ab";
//        String s1 = "b";
//        String s2 = "a" + s1;
//        System.out.println(s0=="a"+"b");//true
//        System.out.println((s0 == s2)); //false


//        String str = "Hello World of Java";
//        StringTokenizer st = new StringTokenizer(str);
//        while (st.hasMoreTokens()) { //
//            System.out.println("Token:" + st.nextToken());
//        }

        //最简单的方法是使用一个正则表达式。
//        for(String word : str.split(" ")){
//            System.out.println(word);
//        }


//        Byte b=new Byte("001");
//        System.out.println(b);//1


        Character s=new Character('a');
        System.out.println(s);//a

    }
}

//
//class TestClass2 extends TestClass1{
//
//    @Override
//    public void test1(){
//        System.out.println(123);
//    }
//
//////    final定义的方法，不可被继承重写
////    public void  test2(){
////        System.out.println(1234);
////    }
//
//
//    public static void main(String[] args) {
//        TestClass2 testClass2=new TestClass2();
//        testClass2.test2();
//    }
//
//
//
//}

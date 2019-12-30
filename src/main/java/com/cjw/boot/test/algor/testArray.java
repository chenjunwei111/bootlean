package com.cjw.boot.test.algor;

import java.util.Arrays;
import java.util.Collections;

/**
* Description  测试数组
* @Author junwei
* @Date 15:36 2019/10/14
**/
public class testArray {

    public static void main(String[] args) {
//        int[] number1={1,2,3,5,8};
//        int[] number2={1,2,3,5,8};
//        int[] number3={1,2,5,3,8};
//        int[] number4={1,2,3,5,8,1};
//
//        System.out.println(number1==number2);//false
//        System.out.println(Arrays.equals(number1,number2));//true
//        System.out.println(Arrays.equals(number3,number2));//false
//        System.out.println(Arrays.equals(number3,number4));//false

//        double[] score = {99.5,100,98,97.5,100,95,85.5,100};
//        //查找第一个100的位置
//        int index1 = Arrays.binarySearch(score, 100);
//        //查找范围2~6 之间，第一个60的位置
//        int index2 = Arrays.binarySearch(score,2,6,97.5);
//        System.out.println("查找到 100 的位置是："+index1);
//        System.out.println("查找到 97.5 的位置是："+ index2);


        // 定义长度为 5 的数组
//        int scores[] = new int[]{57,81,68,75,91};
//        // 输出原数组
//        System.out.println("原数组内容如下：");
//        // 循环遍历原数组
//        for(int i=0;i<scores.length;i++) {
//            // 将数组元素输出
//            System.out.print(scores[i]+"\t");
//        }
//        // 定义一个新的数组，将 scores 数组中的 5 个元素复制过来
//        // 同时留 3 个内存空间供以后开发使用（填充的3个位置默认为零）
//        int[] newScores = (int[]) Arrays.copyOf(scores,8);
//        System.out.println("\n复制的新数组内容如下：");
//        // 循环遍历复制后的新数组
//        for(int j=0;j<newScores.length;j++) {
//            // 将新数组的元素输出
//            System.out.print(newScores[j]+"\t");
//        }
//
//        int newScores2[] = (int[]) Arrays.copyOfRange(scores, 0, 5);
//        System.out.println("\n复制的新数组内容如下：");
//        // 循环遍历目标数组，即复制后的新数组
//        for (int j = 0; j < newScores2.length; j++) {
//            System.out.print(newScores2[j] + "\t");
//        }
//
//
//        int newScores3[] = (int[]) scores.clone();
//        System.out.println("\n目标数组内容如下：");
//        for (int j = 0; j < newScores3.length; j++) {
//            System.out.print(newScores3[j] + "\t");
//        }


        //升序
        Integer[] a = { 9, 8, 7, 2, 3, 4, 1, 0, 6, 5 };
        Arrays.sort(a);
        for (int arr : a) {
            System.out.print(arr + " ");
        }
        //0 1 2 3 4 5 6 7 8 9


        //降序
        Arrays.sort(a, Collections.reverseOrder());
        for (int arr : a) {
            System.out.print(arr + " ");
        }
        //9 8 7 6 5 4 3 2 1 0




    }


}

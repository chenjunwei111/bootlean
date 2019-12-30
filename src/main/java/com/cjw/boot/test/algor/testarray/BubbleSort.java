package com.cjw.boot.test.algor.testarray;

/**
* Description
* @param
* @param
* @Author junwei
* @Date 10:46 2019/10/16
**/
public class BubbleSort {

    public static void main(String[] args) {

//        冒泡排序的算法比较简单，排序的结果稳定，但时间效率不太高
        int a1[]={1,2,5,3,2,4};
        for(int i=0;i<a1.length;i++){
            for(int j=0;j<a1.length-i-1;j++){
                //判断小于号就是降序，判断大于号就是升序
                if(a1[j]>a1[j+1]){
                    int b=a1[j];
                    a1[j]=a1[j+1];
                    a1[j+1]=b;
                }
            }
        }
        for(int c:a1){
            System.out.print(c);
        }

    }

}

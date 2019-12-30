package com.cjw.boot.test.algor.testarray;

/**
* Description
* @param
* @param
* @Author junwei
* @Date 11:01 2019/10/16
**/
public class Quicksort {


    public static void main(String[] args) {


        int[] number={13,15,11};
        System.out.println("排序前：");
        for(int val:number) {
            System.out.print(val+" ");
        }
        quick(number);
        System.out.println("\n排序后：");
        for(int val:number) {
            System.out.print(val +" ");
        }
    }

    public static void quick(int[] str) {
        if(str.length > 0) {
            // 查看数组是否为空
            unckSort(str,0,str.length-1);
        }
    }

    public static void unckSort(int[] list,int low,int high) {
        if(low < high) {
            int middle = getQucikNum(list,low,high);    // 将list数组一分为二
            unckSort(list,low,middle-1);    // 对低字表进行递归排序
            unckSort(list,middle+1,high);    // 对高字表进行递归排序
        }
    }

    public  static  int getQucikNum(int []list,int low,int high){
        int tmp = list[low]; // 数组的第一个值作为中轴（分界点或关键数据）
        while (low < high) {
            while (low < high && list[high] > tmp) {
                high--;
            }
            list[low] = list[high]; // 比中轴小的记录移到低端
            while (low < high && list[low] < tmp) {
                low++;
            }
            list[high] = list[low]; // 比中轴大的记录移到高端
        }
        list[low] = tmp; // 中轴记录到尾
        return low; // 返回中轴的位置

    }




}

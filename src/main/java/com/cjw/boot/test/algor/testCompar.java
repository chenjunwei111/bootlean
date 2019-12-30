package com.cjw.boot.test.algor;

import java.util.Arrays;
import java.util.Comparator;

public class testCompar {
    public static void main(String[] args) {
        Integer[] a = { 9, 8, 7, 2, 3, 4, 1, 0, 6, 5 };
        // 定义一个自定义类MyComparator的对象
        Comparator cmp = new MyCompar();
        Arrays.sort(a);
        for (int arr : a) {
            System.out.print(arr + " ");
        }
        System.out.println();
        Arrays.sort(a, cmp);
        //等同于以下
//        Arrays.sort(a, Collections.reverseOrder());
        for (int arr : a) {
            System.out.print(arr + " ");
        }
    }
}


class  MyCompar implements Comparator<Integer>{
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2-o1;
    }
}

package com.learn.method1reference;

import java.util.Arrays;
import java.util.Comparator;

public class Demo2 {
    public static void main(String[] args) {
        //待定类型的方法引用
        //有一个字符串数组,里面有一些人的名字都是英文名称,请按照名字的首字母升序排序
        String[] names = {"Tom","Jerry","Jack","Alice","Bob","Smith","Mary","Kitty","Lucy","bi","Andy","David","caocao"};
//        Arrays.sort(names);
//        System.out.println(Arrays.toString(names));
        //忽略首字母的大小进行升序排序
        Arrays.sort(names, String::compareToIgnoreCase); //特定方法引用 类型名称::方法名
        System.out.println(Arrays.toString(names));

    }

}

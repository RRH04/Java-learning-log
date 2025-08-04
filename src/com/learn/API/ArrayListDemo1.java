package com.learn.API;

import java.util.ArrayList;

public class ArrayListDemo1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("java");

        //遍历
        for (String s : list) {
            System.out.println(s);
        }
        //删除
        list.removeFirst();
        System.out.println(list);
        //修改
        list.set(0,"javaee");
        System.out.println(list);
        //获取
        System.out.println(list.getFirst());
    }

}

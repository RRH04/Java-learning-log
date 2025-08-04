package com.learn.code;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CodeDemo1 {
    public static String[] cards = new String[5];


    public static void main(String[] args){
        System.out.println(Arrays.toString(cards));
        System.out.println("main方法执行");

    }
    //基本作用,{初始化类的静态资源!
    //静态代码块在类加载时执行,且只执行一次,
    // 静态代码块优先于main方法执行
    // }
    static {
        cards[0] = "a";
        cards[1] = "b";
        cards[2] = "c";
        cards[3] = "d";
        cards[4] = "e";
        System.out.println("静态代码块执行");
    }
//认识代码块
}

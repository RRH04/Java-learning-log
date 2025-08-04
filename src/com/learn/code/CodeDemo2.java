package com.learn.code;

public class CodeDemo2 {
    private String name;
    private String[] directions = new String[4]; //每个实例对象都有directions数组
    //每次创建对象前,都会优先执行一次
    //基本作用{1.初始化对象的实例资源!}
    {
        System.out.println("我是实例代码块");
        name = "张三";
        directions[0] = "上";
        directions[1] = "下";
        directions[2] = "左";
        directions[3] = "右"; //通过实例代码块 将所有CodeDemo2实例对象都赋值默认的上下左右
    }
    public static void main(String[] args) {
        System.out.println("main方法执行");
        //目标:实例代码块
        new CodeDemo2();
        new CodeDemo2();
        new CodeDemo2();
        System.out.println(new CodeDemo2().name);
    }

}

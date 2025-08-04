package com.demo;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        //目标:面向对象编程实现智能家居控制系统
        //角色:设备(吊灯,电视机,洗衣机,落地窗,...)
        //具备的功能:开和关
        //谁控制他们:智能控制系统(单例对象),控制调用设备的开和关
        //1.定义设备类,创建设备对象代表家里的设备
        //2.准备这些设备对象,放在数组中,代表整个家庭的设备
        JD [] devices = {
                new lamp("客厅灯", false),
                new TV("客厅电视", false),
                new WashMachine("洗衣机", false),
                new Air("空调", false)
        };
        //3,定义接口,让jd实现开关
       //4,定义智能控制系统类,实现开关功能,控制设备
        SmartHome smartHome = SmartHome.getInstance();
       //5,调用智能控制系统,控制设备
        //6.提示用户操作,a,展示全部设备当前情况.b,让用户选择哪一个进行操作



        while (true){
            //打印全部设备的状态
            smartHome.printAll(devices);
            //7.让用户选择设备进行操作
            System.out.println("请选择设备进行操作:");
            Scanner sc = new Scanner(System.in);
            int index = sc.nextInt();
            smartHome.control(devices[index-1]);
        }




    }
}

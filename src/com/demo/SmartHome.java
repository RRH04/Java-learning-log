package com.demo;
//智能控制系统
public class SmartHome {
    private static final SmartHome smartHome = new SmartHome();


    public static SmartHome getInstance(){
        return smartHome;
    }

    public void control(JD jd){
        System.out.println(jd.getName() + "状态目前是:"+(jd.getStatus()?"开启":"关闭"));
        jd.press();
    }

    public void printAll(JD[] devices) {
        int i = 1;
        for (JD device : devices) {
            System.out.println((i)+","+device.getName() + "状态目前是:" + (device.getStatus() ? "开启" : "关闭"));
            i++;
        }
    }
}

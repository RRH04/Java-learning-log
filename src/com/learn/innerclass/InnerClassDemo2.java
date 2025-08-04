package com.learn.innerclass;
//匿名内部类
public class InnerClassDemo2 {
    public static void main(String[] args) {
        Animal a = new Animal(){
            @Override
            public void cry() {
                System.out.println("🐕小狗汪汪叫");
            }
        };   //匿名内部类 不用创建子类继承animal再创建对象 匿名内部类本质是一个子类,并立即创建一个子类对象;
        a.cry();
        Animal b = new cat(); //同样的
        b.cry();

    }
}

class cat extends Animal{
    @Override
    public void cry() {
        System.out.println("🐱小猫喵喵叫");
    }
}
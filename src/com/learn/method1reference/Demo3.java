package com.learn.method1reference;

public class Demo3 {
    public static void main(String[] args) {
        //构造器引用
        CarFactory cf = Car::new;
        Car c1 = cf.getCar("奔驰");
        System.out.println(c1.name);
    }

}
class Car {
    String name;


    public Car() {
    }
    public Car(String name) {
        this.name = name;
    }
}
@FunctionalInterface
interface CarFactory{
    Car getCar(String name);
}
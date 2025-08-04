package com.learn.innerclass;

public class Student {
    //姓名,年龄,身高,性别
    private String name;
    private int age;
    private double height;
    private String sex;
    //构造方法
    public Student(String name, int age, double height, String sex) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.sex = sex;
    }
    //无参数构造
    public Student() {

    }
    //get/set方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public void show() {
        System.out.println("姓名:" + name + " 年龄:" + age + " 身高:" + height + " 性别:" + sex);
    }
    public static int compare(Student o1, Student o2) {
        return o1.getAge() - o2.getAge();
    }
}

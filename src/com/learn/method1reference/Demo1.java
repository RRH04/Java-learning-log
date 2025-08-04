package com.learn.method1reference;

import com.learn.innerclass.Student;

import java.util.Arrays;

public class Demo1 {
    public static void main(String[] args) {
        //静态方法引用
        Demo1.method();

    }
    public static void method(){
        Student[] students = new Student[6];
        students[0] = new Student("夏侯惇", 18,175,"男");
        students[1] = new Student("诸葛亮", 20,180,"男");
        students[2] = new Student("貂蝉", 19,165,"女");
        students[3] = new Student("吕布", 21,185,"男");
        students[4] = new Student("张飞", 22,190,"男");
        students[5] = new Student("黄月英", 17,162,"女");

        //需求 按照年龄升序排序
        Arrays.sort(students, Student::compare); //静态方法引用 类名::方法名 前后参数一致

        for (Student student : students) {
            student.show();
        }

    }

}


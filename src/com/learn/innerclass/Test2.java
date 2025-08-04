package com.learn.innerclass;

import java.util.Arrays;
import java.util.Comparator;

public class Test2 {
    public static void main(String[] args) {
    //准备一个学生类型的数组,存放六个学生对象
    Student[] students = new Student[6];
    students[0] = new Student("夏侯惇", 18,175,"男");
    students[1] = new Student("诸葛亮", 20,180,"男");
    students[2] = new Student("貂蝉", 19,165,"女");
    students[3] = new Student("吕布", 21,185,"男");
    students[4] = new Student("张飞", 22,190,"男");
    students[5] = new Student("黄月英", 17,162,"女");

        //需求 按照年龄升序排序
        Arrays.sort(students, ( o1,  o2)-> {
            return o1.getAge() - o2.getAge();
    });

        for (Student student : students) {
            student.show();
        }


    }

}

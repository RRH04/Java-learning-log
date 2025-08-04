package com.learn.API;

public class StringDemo1 {
    public static void main(String[] args) {
        String s1 = "hello rrh";
        String s2 = "hello rrh";
        //String 创建对象的区别
        //1.字面量创建对象,只会创建一个对象 会存储到字符串常量池,如果常量池中已经存在,则不会创建新的对象
        //2.直接new创建对象,会创建两个对象,堆内存中  每new一次都会创建新的对象
        //3.拼接字符串,底层是new了一个新的对象
        String s3 = new String("hello zsy");
        String s4 = new String("hello zsy");
        System.out.println(s1 == s2);
        System.out.println(s3 == s4);
        System.out.println("===================================================================");

        String code = getRandomCode(4);
        System.out.println(code);
    }
    //开发验证码 每位可能是数字,大写字母或小写字母
    public static String getRandomCode(int n){
        //用string记住所有变量
        String code = "";
        for (int i = 0; i < n; i++) {
            int num = (int)(Math.random()*62);
            if (num<10){
                code += num;
            }else if (num<36){
                code += (char)(num-10+'A');
            }else {
                code += (char)(num-36+'a');
            }
        }
        return code;
    }

}


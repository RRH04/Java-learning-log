package com.learn.innerclass;

public class InnerClassDemo1 {
    public static void main(String[] args) {
        OuterClass oc = new OuterClass();
        oc.show();
        OuterClass.InnerClass ic = new OuterClass().new InnerClass();
        ic.show();
        OuterClass.InnerClass2 ic2 = new OuterClass.InnerClass2(); //静态内部类访问外部类成员不需要创建外部类对象
        ic2.show();

        //成员内部类访问外部类成员的特点
    }
}
 class OuterClass {
    private int num = 10;
    public static void showOuter(){
        System.out.println("OuterClassStatic");
    }
    public void show(){
        System.out.println("OuterClass");
    }
    class InnerClass{


        public void show(){
            System.out.println(num);
            OuterClass.showOuter();
            System.out.println("InnerClass");
        }
    }
    static class InnerClass2{
        public void show(){
            System.out.println("InnerClass2");
        }
    }
}

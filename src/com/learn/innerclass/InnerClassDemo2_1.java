package com.learn.innerclass;


//匿名内部类 经常作为一个对象参数来传给方法使用
public class InnerClassDemo2_1 {
    public static void main(String[] args) {
   //设计一个方法,可以接收老师和学生开始比赛

    start(new Swim() {
        @Override
        public void swimming() {
            System.out.println("学生 游泳");
        }
    });
    start(new Swim() {
        @Override
        public void swimming() {
            System.out.println("老师 游泳");
        }
    });
    }
    public static void start(Swim s){
        s.swimming();
    }

}
interface Swim{
    void swimming();
}



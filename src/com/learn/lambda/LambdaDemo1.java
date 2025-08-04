package com.learn.lambda;

import com.learn.innerclass.Animal;

public class LambdaDemo1 {
    public static void main(String[] args) {
        //lambda 可以用于替代某些匿名内部类对象
        Animal a = new Animal(){
            @Override
            public void cry() {
                System.out.println("🐱是喵喵喵");
            }

            //函数式接口:有且只有一个抽象方法的接口
            // lambda 不是可以简化所有匿名内部类,lambda 只能简化函数式接口的匿名内部类
//     错误:      Animal a1 = ()->{
//                System.out.println("🐱是喵喵喵");
//            }
        };
        a.cry();
        AnimalInterface ai = ()->{System.out.println("🐕是汪汪汪");};
        ai.cry();
    }

}
@FunctionalInterface //约束接口只能为函数式接口
interface AnimalInterface{
    void cry();
}
//lambda 语法: (覆写的方法的参数列表)-{覆写的代码内容(方法体)}
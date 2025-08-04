package com.learn.innerclass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {
    public static void main(String[] args) {
        //目标 匿名内部类的应用场景
        //需求,创建一个登录窗口,窗口上有一个登录按钮
        JFrame win = new JFrame("登录窗口");
        JPanel panel = new JPanel();
        win.add(panel);
        JButton btn = new JButton("登录");

        panel.add(btn);
        win.setSize(500, 400);
        win.setLocationRelativeTo(null); //居中显示
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true); //显示窗口
        btn.addActionListener( e -> {        //lambda函数表达式 简化匿名内部类

                System.out.println("登录按钮被点击了");

        });

    }
}

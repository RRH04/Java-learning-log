package com.learn.gui;

import javax.swing.*;

public class guiDemo2 {
    public static void main(String[] args) {
        //自定义一个登陆界面,让界面本身也是事件监听器对象
       loginFrame lf = new loginFrame();
       lf.setVisible(true);
    }

}

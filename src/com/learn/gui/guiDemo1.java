package com.learn.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class guiDemo1 {
    public static void main(String[] args) {
        JFrame jf = new JFrame("我的第一个GUI程序");
        jf.setSize(400, 300);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        JPanel jp = new JPanel();
        jf.add(jp);
        jf.setLocationRelativeTo(null);
        JButton jb = new JButton("登录");
        jp.add(jb);
        jb.addActionListener(new MyActionListener(jf));//添加监听器
    }

}
class MyActionListener implements ActionListener {
    private JFrame jf;
    public MyActionListener(JFrame jf){
        this.jf = jf;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(jf, "点击了登录按钮"); //点击按钮后弹窗
    }

}

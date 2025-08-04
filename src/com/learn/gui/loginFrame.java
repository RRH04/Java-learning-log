package com.learn.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginFrame extends JFrame implements ActionListener {




    public loginFrame() {
        this.setSize(500, 500);
        this.setTitle("Login Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JButton btn = new JButton("登录");
        JPanel mainPanel = new JPanel();
        mainPanel.add(btn);
        this.add(mainPanel);
        btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "登录成功");
    }
}

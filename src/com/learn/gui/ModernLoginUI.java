package com.learn.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class ModernLoginUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JCheckBox rememberCheck;
    private JLabel titleLabel;
    private JLabel statusLabel;

    public ModernLoginUI() {
        // 窗口基础设置
        setTitle("系统登录");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 居中显示
        setUndecorated(true); // 去除默认边框
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30)); // 圆角窗口


        // 创建主面板
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // 创建渐变背景
                Color color1 = new Color(32, 34, 50);
                Color color2 = new Color(61, 66, 112);
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // 添加装饰性线条
                g2d.setColor(new Color(92, 107, 192, 150));
                g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                for (int i = 0; i < 10; i++) {
                    int y = 50 + i * 50;
                    g2d.drawLine(0, y, getWidth(), y);
                }
            }
        };
        mainPanel.setLayout(null);
        mainPanel.setOpaque(false);
        add(mainPanel);

        // 添加左侧装饰面板
        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // 半透明装饰
                g2d.setColor(new Color(255, 255, 255, 20));
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // 装饰圆
                g2d.setColor(new Color(92, 107, 192, 60));
                g2d.fillOval(-50, 100, 200, 200);

                g2d.setColor(new Color(126, 142, 232, 60));
                g2d.fillOval(50, 350, 150, 150);
            }
        };
        leftPanel.setBounds(0, 0, 350, getHeight());
        leftPanel.setOpaque(false);
        mainPanel.add(leftPanel);

        // 添加右侧登录面板
        JPanel loginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // 创建半透明背景
                g2d.setColor(new Color(255, 255, 255, 20));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                // 添加边框
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.setColor(new Color(255, 255, 255, 80));
                g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 30, 30);
            }
        };
        loginPanel.setBounds(450, 100, 350, 400);
        loginPanel.setLayout(null);
        loginPanel.setOpaque(false);
        mainPanel.add(loginPanel);

        // 标题标签
        titleLabel = new JLabel("欢迎登录");
        titleLabel.setBounds(0, 30, 350, 60);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));
        titleLabel.setForeground(new Color(220, 220, 255));
        loginPanel.add(titleLabel);

        // 用户名标签和输入框
        JLabel userLabel = new JLabel("用户名");
        userLabel.setBounds(50, 110, 80, 25);
        userLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        userLabel.setForeground(new Color(220, 220, 255));
        loginPanel.add(userLabel);

        usernameField = createRoundedTextField();
        usernameField.setBounds(50, 140, 250, 40);
        loginPanel.add(usernameField);

        // 密码标签和输入框
        JLabel passLabel = new JLabel("密码");
        passLabel.setBounds(50, 190, 80, 25);
        passLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        passLabel.setForeground(new Color(220, 220, 255));
        loginPanel.add(passLabel);

        passwordField = createRoundedPasswordField();
        passwordField.setBounds(50, 220, 250, 40);
        loginPanel.add(passwordField);

        // 记住我复选框
        rememberCheck = new JCheckBox("记住登录状态");
        rememberCheck.setBounds(50, 270, 150, 25);
        rememberCheck.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        rememberCheck.setForeground(new Color(200, 200, 230));
        rememberCheck.setOpaque(false);
        rememberCheck.setFocusPainted(false);
        rememberCheck.setSelected(true);
        loginPanel.add(rememberCheck);

        // 登录按钮
        loginButton = new JButton("登 录") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // 渐变按钮
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(92, 107, 192),
                        0, getHeight(), new Color(61, 66, 112)
                );
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                super.paintComponent(g);
            }
        };
        loginButton.setBounds(50, 310, 250, 45);
        loginButton.setFont(new Font("微软雅黑", Font.BOLD, 16));
        loginButton.setForeground(Color.WHITE);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // 按钮悬停效果
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setFont(new Font("微软雅黑", Font.BOLD, 18));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setFont(new Font("微软雅黑", Font.BOLD, 16));
            }
        });

        // 登录按钮点击事件
        loginButton.addActionListener(e -> attemptLogin());
        loginPanel.add(loginButton);

        // 状态标签
        statusLabel = new JLabel(" ");
        statusLabel.setBounds(50, 360, 250, 25);
        statusLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        statusLabel.setForeground(new Color(255, 100, 100));
        loginPanel.add(statusLabel);

        // 添加关闭按钮
        JButton closeButton = new JButton("×");
        closeButton.setBounds(getWidth() - 50, 10, 40, 40);
        closeButton.setFont(new Font("Arial", Font.BOLD, 20));
        closeButton.setForeground(Color.WHITE);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> System.exit(0));
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // 关闭按钮悬停效果
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.setForeground(new Color(255, 100, 100));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeButton.setForeground(Color.WHITE);
            }
        });
        mainPanel.add(closeButton);

        // 添加拖拽功能
        addMouseMotionListener(new MouseMotionAdapter() {
            private int x, y;

            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(getLocationOnScreen().x + e.getX() - x,
                        getLocationOnScreen().y + e.getY() - y);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });

    }

    // 创建圆角文本框
    private JTextField createRoundedTextField() {
        return new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    // 绘制背景
                    g2.setColor(new Color(255, 255, 255, 30));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

                    // 绘制边框
                    g2.setColor(new Color(200, 200, 255, 100));
                    g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);

                    g2.dispose();
                }
                super.paintComponent(g);
            }

            @Override
            public void setOpaque(boolean opaque) {
                super.setOpaque(false);
            }
        };
    }

    // 创建圆角密码框
    private JPasswordField createRoundedPasswordField() {
        return new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    // 绘制背景
                    g2.setColor(new Color(255, 255, 255, 30));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

                    // 绘制边框
                    g2.setColor(new Color(200, 200, 255, 100));
                    g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);

                    g2.dispose();
                }
                super.paintComponent(g);
            }

            @Override
            public void setOpaque(boolean opaque) {
                super.setOpaque(false);
            }
        };
    }

    // 登录验证逻辑
    private void attemptLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty()) {
            shakeComponent(usernameField);
            statusLabel.setText("请输入用户名");
            return;
        }

        if (password.isEmpty()) {
            shakeComponent(passwordField);
            statusLabel.setText("请输入密码");
            return;
        }

        // 模拟登录验证
        if ("admin".equals(username) && "admin123".equals(password)) {
            statusLabel.setText("登录成功，正在跳转...");
            statusLabel.setForeground(new Color(100, 255, 100));

            // 模拟登录成功后的跳转
            Timer timer = new Timer(1500, e -> {
                JOptionPane.showMessageDialog(this, "欢迎回来，" + username + "!", "登录成功", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            });
            timer.setRepeats(false);
            timer.start();
        } else {
            shakeComponent(loginButton);
            statusLabel.setText("用户名或密码错误");
            statusLabel.setForeground(new Color(255, 100, 100));
        }
    }

    // 组件抖动效果
    private void shakeComponent(JComponent component) {
        final int originalX = component.getX();
        final int originalY = component.getY();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    int offset = (i % 2 == 0) ? 5 : -5;
                    component.setLocation(originalX + offset, originalY);
                    Thread.sleep(30);
                }
                component.setLocation(originalX, originalY);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        try {
            // 设置UI风格
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            ModernLoginUI loginUI = new ModernLoginUI();
            loginUI.setVisible(true);
        });
    }
}
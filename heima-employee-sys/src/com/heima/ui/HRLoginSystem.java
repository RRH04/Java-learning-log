package com.heima.ui;
import com.heima.bean.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class HRLoginSystem extends JFrame implements ActionListener {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel mainPanel;
    private JPanel cardPanel;
    //定义一个集合存储用户信息
    private static ArrayList<User> AllUsers = new ArrayList<>();

    public HRLoginSystem() {
        // 设置窗口属性
        setTitle("人事管理系统");
        setSize(420, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 居中显示
        setResizable(false);

        // 设置窗口圆角
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 420, 600, 20, 20));

        // 创建主面板 - iOS风格的背景
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                // 创建渐变背景
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(248, 250, 252),
                        0, getHeight(), new Color(241, 245, 249)
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.dispose();
            }
        };
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // 顶部关闭按钮
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(10, 0, 0, 10));

        JButton closeButton = createCloseButton();
        topPanel.add(closeButton);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // 中心内容面板
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        // 标题
        JLabel titleLabel = new JLabel("欢迎回来");
        titleLabel.setFont(new Font("PingFang SC", Font.BOLD, 28));
        titleLabel.setForeground(new Color(31, 41, 55));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("请登录您的账户");
        subtitleLabel.setFont(new Font("PingFang SC", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(107, 114, 128));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(subtitleLabel);

        centerPanel.add(Box.createVerticalStrut(40));

        // 登录卡片面板
        cardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // 绘制毛玻璃效果的卡片背景
                g2d.setColor(new Color(255, 255, 255, 200));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);

                // 添加阴影效果
                g2d.setColor(new Color(0, 0, 0, 10));
                g2d.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 16, 16);
                g2d.dispose();
            }
        };
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        cardPanel.setOpaque(false);

        // 用户名输入框
        JLabel userLabel = new JLabel("用户名");
        userLabel.setFont(new Font("PingFang SC", Font.PLAIN, 14));
        userLabel.setForeground(new Color(75, 85, 99));
        userLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardPanel.add(userLabel);
        cardPanel.add(Box.createVerticalStrut(8));

        userField = createStyledTextField("请输入用户名");
        cardPanel.add(userField);
        cardPanel.add(Box.createVerticalStrut(20));

        // 密码输入框
        JLabel passLabel = new JLabel("密码");
        passLabel.setFont(new Font("PingFang SC", Font.PLAIN, 14));
        passLabel.setForeground(new Color(75, 85, 99));
        passLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardPanel.add(passLabel);
        cardPanel.add(Box.createVerticalStrut(8));

        passField = createStyledPasswordField("请输入密码");
        cardPanel.add(passField);
        cardPanel.add(Box.createVerticalStrut(30));

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        // 登录按钮
        loginButton = createStyledButton("登录", new Color(59, 130, 246));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //登录逻辑
                LoginConfirm();

            }
        });
        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createVerticalStrut(12));

        // 注册按钮
        registerButton = createStyledButton("注册", new Color(107, 114, 128));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 跳转到注册页面
                new HRLoginSystem().RegisterSystem();
            }
        });
        buttonPanel.add(registerButton);

        cardPanel.add(buttonPanel);


        // 忘记密码链接

        JButton forgotButton = new JButton("忘记密码?");
        forgotButton.setFont(new Font("PingFang SC", Font.PLAIN, 14));
        forgotButton.setForeground(new Color(59, 130, 246));
        forgotButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgotButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgotButton.setBorderPainted(false);
        forgotButton.setContentAreaFilled(false);
        forgotButton.setFocusPainted(false);
        forgotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 忘记密码逻辑
                JOptionPane.showMessageDialog(HRLoginSystem.this, "请联系管理员重置密码", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        cardPanel.add(Box.createVerticalStrut(20));
        cardPanel.add(forgotButton);


        centerPanel.add(cardPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // 底部版权信息
        JLabel footerLabel = new JLabel("© 2025 人事管理系统 v1.0", JLabel.CENTER);
        footerLabel.setFont(new Font("PingFang SC", Font.PLAIN, 12));
        footerLabel.setForeground(new Color(156, 163, 175));
        footerLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
        mainPanel.add(footerLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void LoginConfirm() {
        // 登录逻辑
        String username = userField.getText().trim();
        String password = new String(passField.getPassword());
        if (username.isEmpty() || password.isEmpty()) {
            // 提示用户输入用户名和密码
            JOptionPane.showMessageDialog(this, "请输入用户名和密码", "错误", JOptionPane.ERROR_MESSAGE);
        } else {
            // 验证用户名和密码
            User user = getUserByUserName(username);
            if (user == null) {
                JOptionPane.showMessageDialog(this, "用户名不存在", "错误", JOptionPane.ERROR_MESSAGE);
            }
            else if (!user.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(this, "密码错误", "错误", JOptionPane.ERROR_MESSAGE);
            } else {
                // 登录成功
                JOptionPane.showMessageDialog(this, "登录成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                // 跳转到员工管理页面
                new EmployeeManagementSystem(user.getUsername());
                dispose();
            }


        }
    }
    public User getUserByUserName(String username) {
        for (User user : AllUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void RegisterSystem() {
        // 创建注册页面
        JFrame registerFrame = new JFrame("注册");
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setSize(420, 600);
        registerFrame.setLocationRelativeTo(null);
        registerFrame.setResizable(false);

        // 设置窗口圆角
        registerFrame.setUndecorated(true);
        registerFrame.setShape(new RoundRectangle2D.Double(0, 0, 420, 600, 20, 20));

        // 创建主面板 - iOS风格的背景
        JPanel registerMainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                // 创建渐变背景
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(248, 250, 252),
                        0, getHeight(), new Color(241, 245, 249)
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.dispose();
            }
        };
        registerMainPanel.setLayout(new BorderLayout());
        registerFrame.add(registerMainPanel);

        // 顶部关闭按钮
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(10, 0, 0, 10));

        JButton closeButton = createCloseButton();
        closeButton.addActionListener(e -> registerFrame.dispose());
        topPanel.add(closeButton);
        registerMainPanel.add(topPanel, BorderLayout.NORTH);

        // 中心内容面板
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        // 标题
        JLabel titleLabel = new JLabel("创建账户");
        titleLabel.setFont(new Font("PingFang SC", Font.BOLD, 28));
        titleLabel.setForeground(new Color(31, 41, 55));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("请输入您的注册信息");
        subtitleLabel.setFont(new Font("PingFang SC", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(107, 114, 128));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(subtitleLabel);

        centerPanel.add(Box.createVerticalStrut(40));

        // 注册卡片面板
        JPanel registerCardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // 绘制毛玻璃效果的卡片背景
                g2d.setColor(new Color(255, 255, 255, 200));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);

                // 添加阴影效果
                g2d.setColor(new Color(0, 0, 0, 10));
                g2d.fillRoundRect(2, 2, getWidth()-4, getHeight()-4, 16, 16);
                g2d.dispose();
            }
        };
        registerCardPanel.setLayout(new BoxLayout(registerCardPanel, BoxLayout.Y_AXIS));
        registerCardPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        registerCardPanel.setOpaque(false);

        // 用户名输入框
        JLabel userLabel = new JLabel("用户名");
        userLabel.setFont(new Font("PingFang SC", Font.PLAIN, 14));
        userLabel.setForeground(new Color(75, 85, 99));
        userLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        registerCardPanel.add(userLabel);
        registerCardPanel.add(Box.createVerticalStrut(8));

        JTextField registerUserField = createStyledTextField("请输入用户名");
        registerCardPanel.add(registerUserField);
        registerCardPanel.add(Box.createVerticalStrut(20));

        // 密码输入框
        JLabel passLabel = new JLabel("密码");
        passLabel.setFont(new Font("PingFang SC", Font.PLAIN, 14));
        passLabel.setForeground(new Color(75, 85, 99));
        passLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        registerCardPanel.add(passLabel);
        registerCardPanel.add(Box.createVerticalStrut(8));

        JPasswordField registerPassField = createStyledPasswordField("请输入密码");
        registerCardPanel.add(registerPassField);
        registerCardPanel.add(Box.createVerticalStrut(20));

        // 确认密码输入框
        JLabel confirmPassLabel = new JLabel("确认密码");
        confirmPassLabel.setFont(new Font("PingFang SC", Font.PLAIN, 14));
        confirmPassLabel.setForeground(new Color(75, 85, 99));
        confirmPassLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        registerCardPanel.add(confirmPassLabel);
        registerCardPanel.add(Box.createVerticalStrut(8));

        JPasswordField confirmPassField = createStyledPasswordField("请再次输入密码");
        registerCardPanel.add(confirmPassField);
        registerCardPanel.add(Box.createVerticalStrut(30));

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        // 注册按钮
        JButton registerButton = createStyledButton("注册", new Color(59, 130, 246));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = registerUserField.getText();
                String password = new String(registerPassField.getPassword());
                String confirmPassword = new String(confirmPassField.getPassword());

                // 验证输入
                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(registerFrame, "请填写所有字段", "提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(registerFrame, "两次输入的密码不一致", "提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // 检查用户名是否已存在
                for (User user : AllUsers) {
                    if (user.getUsername().equals(username)) {
                        JOptionPane.showMessageDialog(registerFrame, "用户名已存在", "提示", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                // 添加新用户
                AllUsers.add(new User(username, password));
                JOptionPane.showMessageDialog(registerFrame, "注册成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                registerFrame.dispose();
            }
        });
        buttonPanel.add(registerButton);
        buttonPanel.add(Box.createVerticalStrut(12));

        // 返回登录按钮
        JButton backToLoginButton = createStyledButton("返回登录", new Color(107, 114, 128));
        backToLoginButton.addActionListener(e -> registerFrame.dispose());
        buttonPanel.add(backToLoginButton);

        registerCardPanel.add(buttonPanel);

        centerPanel.add(registerCardPanel);
        registerMainPanel.add(centerPanel, BorderLayout.CENTER);

        // 底部版权信息
        JLabel footerLabel = new JLabel("© 2025 人事管理系统 v1.0", JLabel.CENTER);
        footerLabel.setFont(new Font("PingFang SC", Font.PLAIN, 12));
        footerLabel.setForeground(new Color(156, 163, 175));
        footerLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
        registerMainPanel.add(footerLabel, BorderLayout.SOUTH);

        registerFrame.setVisible(true);
    }


    // 创建关闭按钮
    private JButton createCloseButton() {
        JButton closeButton = new JButton("✕") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2d.setColor(new Color(239, 68, 68));
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(239, 68, 68, 150));
                } else {
                    g2d.setColor(new Color(156, 163, 175));
                }
                
                g2d.fillOval(0, 0, getWidth(), getHeight());
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 12));
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth("✕")) / 2;
                int y = (getHeight() + fm.getAscent()) / 2;
                g2d.drawString("✕", x, y);
                g2d.dispose();
            }
        };
        
        closeButton.setPreferredSize(new Dimension(24, 24));
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> System.exit(0));
        
        return closeButton;
    }
    
    // 创建样式化的文本输入框
    private JTextField createStyledTextField(String placeholder) {
        JTextField field = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getText().isEmpty() && !hasFocus()) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setColor(new Color(156, 163, 175));
                    g2d.setFont(getFont().deriveFont(Font.PLAIN));
                    g2d.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
                    g2d.dispose();
                }
            }
        };
        
        field.setPreferredSize(new Dimension(300, 45));
        field.setMaximumSize(new Dimension(300, 45));
        field.setFont(new Font("PingFang SC", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(12, 16, 12, 16)
        ));
        field.setBackground(new Color(249, 250, 251));
        field.setForeground(new Color(31, 41, 55));
        
        // 添加焦点监听器
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(59, 130, 246), 2),
                    BorderFactory.createEmptyBorder(11, 15, 11, 15)
                ));
                field.setBackground(Color.WHITE);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
                    BorderFactory.createEmptyBorder(12, 16, 12, 16)
                ));
                field.setBackground(new Color(249, 250, 251));
            }
        });
        
        return field;
    }
    
    // 创建样式化的密码输入框
    private JPasswordField createStyledPasswordField(String placeholder) {
        JPasswordField field = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getPassword().length == 0 && !hasFocus()) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setColor(new Color(156, 163, 175));
                    g2d.setFont(getFont().deriveFont(Font.PLAIN));
                    g2d.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
                    g2d.dispose();
                }
            }
        };
        
        field.setPreferredSize(new Dimension(300, 45));
        field.setMaximumSize(new Dimension(300, 45));
        field.setFont(new Font("PingFang SC", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(12, 16, 12, 16)
        ));
        field.setBackground(new Color(249, 250, 251));
        field.setForeground(new Color(31, 41, 55));
        
        // 添加焦点监听器
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(59, 130, 246), 2),
                    BorderFactory.createEmptyBorder(11, 15, 11, 15)
                ));
                field.setBackground(Color.WHITE);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
                    BorderFactory.createEmptyBorder(12, 16, 12, 16)
                ));
                field.setBackground(new Color(249, 250, 251));
            }
        });
        
        return field;
    }
    
    // 创建样式化的按钮
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                Color currentColor = bgColor;
                if (getModel().isPressed()) {
                    currentColor = bgColor.darker();
                } else if (getModel().isRollover()) {
                    currentColor = bgColor.brighter();
                }
                
                g2d.setColor(currentColor);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        
        button.setPreferredSize(new Dimension(300, 48));
        button.setMaximumSize(new Dimension(300, 48));
        button.setFont(new Font("PingFang SC", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
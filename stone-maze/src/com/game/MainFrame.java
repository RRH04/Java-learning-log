package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

//自定义窗口类 创建对象 展示一个主窗口
public class MainFrame extends JFrame {
    private int count;
    // 图片路径
    private static final String IMAGE_PATH = "/image/";
    //准备一个数组存放数字色块 4行4列
    private final int[][] images = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    
    // 定义常量
    private static final int ROWS = 4;
    private static final int COLS = 4;
    private static final int TILE_SIZE = 100;
    private static final int OFFSET_X = 25;
    private static final int OFFSET_Y = 65;
    
    // 图片缓存
    private static final Map<String, ImageIcon> imageCache = new HashMap<>();
    
    // JLabel复用
    private final JLabel[][] labels = new JLabel[ROWS][COLS];
    private JLabel backgroundLabel;
    private JLabel countLabel;
    private JLabel winLabel;

    public MainFrame() {
        //调用一个初始化方法,初始化窗口
        initFrame();
        //打乱数组顺序,在展示图片
        initRandomArray();
        //初始化界面.展示数字色块界面
        initImage();
        //初始化系统菜单.点击弹出信息是系统退出和游戏重启
        initMenu();
        Play();
        this.setVisible(true);
    }

    // 获取缓存图片
    private ImageIcon getCachedImage(String imageName) {
        if (!imageCache.containsKey(imageName)) {
            URL imageURL = getClass().getResource(IMAGE_PATH + imageName);
            if (imageURL != null) {
                imageCache.put(imageName, new ImageIcon(imageURL));
            }
        }
        return imageCache.get(imageName);
    }

    private void initRandomArray() {
        // 使用Collections.shuffle优化数组打乱
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < ROWS * COLS; i++) {
            list.add(images[i / COLS][i % COLS]);
        }
        Collections.shuffle(list);
        for (int i = 0; i < ROWS * COLS; i++) {
            images[i / COLS][i % COLS] = list.get(i);
        }
    }

    private void initFrame() {
        this.setTitle("石头迷宫 V 1.0 rrh");
        this.setSize(465, 575);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
    }
    
    private void initImage() {
        //创建一个可以显示文字的组件
        showCount();

        //遍历二维数组images展示图片
        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[i].length; j++) {
                String imageName = images[i][j] + ".png";
                //创建一个图片标签（如果尚未创建）
                if (labels[i][j] == null) {
                    labels[i][j] = new JLabel();
                    labels[i][j].setBounds(OFFSET_X + j * TILE_SIZE, OFFSET_Y + i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    this.add(labels[i][j]);
                }
                // 设置图标
                labels[i][j].setIcon(getCachedImage(imageName));
            }
        }
        //设置窗口的背景图片
        if (backgroundLabel == null) {
            backgroundLabel = new JLabel();
            backgroundLabel.setBounds(-5, -40, 465, 575);
            this.add(backgroundLabel);
        }
        backgroundLabel.setIcon(getCachedImage("background.png"));
    }
    
    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("系统菜单");
        JMenuItem exitJi = new JMenuItem("退出");
        //添加点击事件
        exitJi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        menu.add(exitJi);
        //还有一个选项 重启
        JMenuItem restartJi = new JMenuItem("重新开始");
        restartJi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainFrame();
                dispose();
            }
        });
        menu.add(restartJi);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    private void Play() {
        // 给每张图片绑定点击事件
        updateListeners(); // 使用新方法来更新监听器
    }

    private void updateListeners() {
        // 为所有图片标签添加鼠标监听器（只添加一次）
        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[i].length; j++) {
                final int row = i;
                final int col = j;
                
                // 只有在标签存在且没有监听器时才添加
                if (labels[i][j] != null && labels[i][j].getMouseListeners().length == 0) {
                    labels[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            moveTile(row, col);
                        }
                    });
                }
            }
        }
    }

    private void moveTile(int row, int col) {
        // 查找空位的位置
        int emptyRow = -1, emptyCol = -1;
        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[i].length; j++) {
                if (images[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                    break;
                }
            }
            if (emptyRow != -1) break;
        }

        // 判断点击的位置是否与空位相邻
        boolean isAdjacent =
                (Math.abs(row - emptyRow) == 1 && col == emptyCol) ||  // 上下相邻
                        (Math.abs(col - emptyCol) == 1 && row == emptyRow);    // 左右相邻

        if (isAdjacent) {
            // 交换数组中的值
            int temp = images[row][col];
            images[row][col] = images[emptyRow][emptyCol];
            images[emptyRow][emptyCol] = temp;
            count++;

            // 重新绘制界面
            redrawImage();
        }
    }

    private void redrawImage() {
        // 更新所有图片标签的图标
        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[i].length; j++) {
                String imageName = images[i][j] + ".png";
                labels[i][j].setIcon(getCachedImage(imageName));
            }
        }
        
        // 更新步数显示
        showCount();
        
        // 检查游戏是否完成
        checkWin();

        // 刷新界面
        this.revalidate();
        this.repaint();
    }

    private void checkWin() {
        int[][] winState = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,0}
        };

        boolean win = true;
        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[i].length; j++) {
                if (images[i][j] != winState[i][j]) {
                    win = false;
                    break;
                }
            }
            if (!win) break;
        }

        if (win) {
            // 引用win.png图片
            if (winLabel == null) {
                winLabel = new JLabel();
                winLabel.setBounds(124, 230, 266, 88);
            }
            winLabel.setIcon(getCachedImage("win.png"));
            this.add(winLabel);
            this.setComponentZOrder(winLabel, 0);
        }
    }
    
    private void showCount() {
        if (countLabel == null) {
            countLabel = new JLabel("步数:"+count);
            countLabel.setBounds(10, 0, 100, 20);
            this.add(countLabel);
        } else {
            countLabel.setText("步数:"+count);
        }
    }
}
package com.xhl.C01_Java_Core.Swing;

/**
 * @Author: xhl
 * @Date: 2026-07-23 23:24
 * @Description:
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DesktopPet extends JFrame {

    // 记录鼠标按下时的位置，用于计算拖拽偏移量
    private int initialX, initialY;

    public DesktopPet() {
        // 1. 设置窗口基本属性
        setTitle("桌面小动物");
        setSize(150, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 2. 核心：设置窗口透明
        // 去除窗口边框和标题栏
        setUndecorated(true);
        // 设置背景全透明
        setBackground(new Color(0, 0, 0, 0));
        // 始终置顶，防止被其他窗口遮挡
        setAlwaysOnTop(true);

        // 3. 添加自定义的画板（用来画小动物）
        PetPanel petPanel = new PetPanel();
        add(petPanel);

        // 4. 添加鼠标拖拽事件
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // 计算窗口的新位置
                setLocation(
                        getLocation().x + e.getX() - initialX,
                        getLocation().y + e.getY() - initialY
                );
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 记录按下时的相对坐标
                initialX = e.getX();
                initialY = e.getY();
            }
        });

        // 初始位置设置在屏幕右下角
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width - 200, screenSize.height - 200);

        setVisible(true);
    }

    public static void main(String[] args) {
        // 在事件调度线程中启动界面
        SwingUtilities.invokeLater(DesktopPet::new);
    }

    // 自定义面板：负责绘制小动物
    class PetPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // 开启抗锯齿，让边缘更平滑
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // --- 下面开始画一只简单的小猫 ---
            // 身体 (橙色)
            g2d.setColor(new Color(255, 165, 0));
            g2d.fillOval(25, 50, 100, 90);

            // 左耳朵
            int[] leftEarX = {25, 50, 15};
            int[] leftEarY = {60, 20, 20};
            g2d.fillPolygon(leftEarX, leftEarY, 3);

            // 右耳朵
            int[] rightEarX = {125, 100, 135};
            int[] rightEarY = {60, 20, 20};
            g2d.fillPolygon(rightEarX, rightEarY, 3);

            // 眼睛 (黑色)
            g2d.setColor(Color.BLACK);
            g2d.fillOval(45, 75, 15, 15);
            g2d.fillOval(90, 75, 15, 15);

            // 眼睛高光 (白色)
            g2d.setColor(Color.WHITE);
            g2d.fillOval(48, 77, 5, 5);
            g2d.fillOval(93, 77, 5, 5);

            // 鼻子 (粉色)
            g2d.setColor(Color.PINK);
            g2d.fillOval(68, 95, 14, 10);
        }

        // 让面板的背景也透明
        @Override
        public boolean isOpaque() {
            return false;
        }
    }
}

package com.xhl.C01_Java_Core.Swing;

/**
 * @Author: xhl
 * @Date: 2026-07-23 23:26
 * @Description:
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

public class DeskPet extends JFrame {

    private int initialX, initialY;
    private Point initLocation; // 记录初始位置，用于右键复位
    private boolean isBlinking = false; // 眨眼状态
    private Image petImage; // 外部图片
    private boolean useImage = false; // 是否使用图片模式

    public DeskPet() {
        setTitle("桌面小动物 Pro");
        setSize(150, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 核心：透明无边框窗口
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setAlwaysOnTop(true);

        // 尝试加载图片
        try {
            File imgFile = new File("pet.png");
            if (imgFile.exists()) {
                petImage = ImageIO.read(imgFile);
                useImage = true;
            }
        } catch (Exception e) {
            // 加载失败则使用默认绘制
        }

        PetPanel petPanel = new PetPanel();
        add(petPanel);

        // 初始位置：屏幕右下角
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        initLocation = new Point(screenSize.width - 180, screenSize.height - 180);
        setLocation(initLocation);

        // 鼠标拖拽逻辑
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialX = e.getX();
                initialY = e.getY();
                if (e.isPopupTrigger()) showMenu(e); // Windows右键
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) showMenu(e); // Mac/Linux右键
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(getLocation().x + e.getX() - initialX,
                        getLocation().y + e.getY() - initialY);
            }
        });

        // 眨眼动画定时器：每3秒检查一次，眨眼持续200ms
        Timer blinkTimer = new Timer(3000, e -> {
            isBlinking = true;
            repaint();
            Timer closeTimer = new Timer(200, ev -> {
                isBlinking = false;
                repaint();
            });
            closeTimer.setRepeats(false);
            closeTimer.start();
        });
        blinkTimer.start();

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DesktopPet::new);
    }

    private void showMenu(MouseEvent e) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem resetItem = new JMenuItem("回到原位");
        JMenuItem exitItem = new JMenuItem("退出宠物");

        resetItem.addActionListener(ev -> setLocation(initLocation));
        exitItem.addActionListener(ev -> System.exit(0));

        menu.add(resetItem);
        menu.addSeparator();
        menu.add(exitItem);
        menu.show(this, e.getX(), e.getY());
    }

    class PetPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (useImage && petImage != null) {
                // 图片模式：自适应面板大小
                g2d.drawImage(petImage, 0, 0, getWidth(), getHeight(), null);
            } else {
                // 默认模式：代码画小猫
                drawDefaultCat(g2d);
            }
        }

        private void drawDefaultCat(Graphics2D g2d) {
            // 身体
            g2d.setColor(new Color(255, 165, 0));
            g2d.fillOval(25, 50, 100, 90);

            // 耳朵
            int[] lEarX = {25, 50, 15}, lEarY = {60, 20, 20};
            int[] rEarX = {125, 100, 135}, rEarY = {60, 20, 20};
            g2d.fillPolygon(lEarX, lEarY, 3);
            g2d.fillPolygon(rEarX, rEarY, 3);

            // 眼睛（带眨眼逻辑）
            g2d.setColor(Color.BLACK);
            if (isBlinking) {
                g2d.drawLine(45, 82, 60, 82); // 左眼闭
                g2d.drawLine(90, 82, 105, 82); // 右眼闭
            } else {
                g2d.fillOval(45, 75, 15, 15);
                g2d.fillOval(90, 75, 15, 15);
                // 高光
                g2d.setColor(Color.WHITE);
                g2d.fillOval(48, 77, 5, 5);
                g2d.fillOval(93, 77, 5, 5);
            }

            // 鼻子和嘴
            g2d.setColor(Color.PINK);
            g2d.fillOval(68, 95, 14, 10);
            g2d.setColor(Color.BLACK);
            g2d.drawLine(75, 105, 75, 115);
            g2d.drawLine(75, 115, 65, 120);
            g2d.drawLine(75, 115, 85, 120);
        }

        @Override
        public boolean isOpaque() { return false; }
    }
}

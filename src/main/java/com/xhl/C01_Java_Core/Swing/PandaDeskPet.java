package com.xhl.C01_Java_Core.Swing;

/**
 * @Author: xhl
 * @Date: 2026-07-23 23:28
 * @Description:
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class PandaDeskPet extends JFrame {

    private int initialX, initialY;
    private Point initLocation;
    private boolean isBlinking = false;
    private Image petImage;
    private boolean useImage = false;

    public PandaDeskPet() {
        setTitle("桌面熊猫");
        setSize(150, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 核心：透明无边框窗口
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setAlwaysOnTop(true);

        // --- 修改开始：使用类路径加载资源 ---
        // 路径前的 "/" 表示从类路径的根目录（即 resources 目录）开始查找
        java.net.URL imgURL = getClass().getResource("/pet.png");

        if (imgURL != null) {
            try {
                petImage = ImageIO.read(imgURL);
                useImage = true;
                System.out.println("✅ 熊猫图片加载成功！");
            } catch (Exception e) {
                System.err.println("❌ 图片读取失败: " + e.getMessage());
            }
        } else {
            System.err.println("⚠️ 未找到 /pet.png，将使用默认手绘熊猫");
        }
        // --- 修改结束 ---

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
                if (e.isPopupTrigger()) showMenu(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) showMenu(e);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(getLocation().x + e.getX() - initialX,
                        getLocation().y + e.getY() - initialY);
            }
        });

        // 眨眼动画定时器
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
        // 修正：这里应该是 PandaDeskPet::new，而不是 DesktopPet::new
        SwingUtilities.invokeLater(PandaDeskPet::new);
    }

    private void showMenu(MouseEvent e) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem resetItem = new JMenuItem("🐼 回到原位");
        JMenuItem exitItem = new JMenuItem("👋 熊猫下班");

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
                g2d.drawImage(petImage, 0, 0, getWidth(), getHeight(), null);
            } else {
                drawPanda(g2d);
            }
        }

        private void drawPanda(Graphics2D g2d) {
            // 熊猫身体（白色）
            g2d.setColor(Color.WHITE);
            g2d.fillOval(25, 50, 100, 90);

            // 左耳朵（黑色）
            int[] lEarX = {25, 50, 15}, lEarY = {60, 20, 20};
            g2d.setColor(Color.BLACK);
            g2d.fillPolygon(lEarX, lEarY, 3);

            // 右耳朵（黑色）
            int[] rEarX = {125, 100, 135}, rEarY = {60, 20, 20};
            g2d.fillPolygon(rEarX, rEarY, 3);

            // 黑眼圈（黑色椭圆）
            g2d.setColor(Color.BLACK);
            g2d.fillOval(40, 70, 25, 20); // 左黑眼圈
            g2d.fillOval(85, 70, 25, 20); // 右黑眼圈

            // 眼睛（带眨眼逻辑）
            if (isBlinking) {
                g2d.setColor(Color.WHITE);
                g2d.drawLine(47, 80, 58, 80); // 左眼闭
                g2d.drawLine(92, 80, 103, 80); // 右眼闭
            } else {
                g2d.setColor(Color.WHITE);
                g2d.fillOval(48, 76, 10, 10); // 左眼白
                g2d.fillOval(93, 76, 10, 10); // 右眼白
                // 瞳孔
                g2d.setColor(Color.BLACK);
                g2d.fillOval(51, 78, 5, 5);
                g2d.fillOval(96, 78, 5, 5);
            }

            // 鼻子（黑色小三角）
            g2d.setColor(Color.BLACK);
            int[] noseX = {68, 82, 75}, noseY = {95, 95, 102};
            g2d.fillPolygon(noseX, noseY, 3);

            // 嘴巴（微笑弧线）
            g2d.setColor(Color.BLACK);
            g2d.drawArc(65, 100, 20, 10, 0, -180);
        }

        @Override
        public boolean isOpaque() {
            return false;
        }
    }
}

package main.pet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ResizableDesktopPet extends JFrame {
    private final JLabel petLabel = new JLabel();
    private int windowWidth;
    private int windowHeight;
    private int dx;  // 水平移動速度
    private int dy;  // 垂直移動速度
    private final Random random = new Random();
    private ImageIcon[] rightAnimationFrames;
    private ImageIcon[] leftAnimationFrames;
    private int currentFrameIndex = 0;
    private boolean movingRightUpOrDown = true;

    // 設定初始位置的方法
    public void setInitialPosition(int x, int y) {
        setLocation(x, y);
    }

    // 設定寵物大小的方法
    public void setPetSize(int width, int height) {
        this.windowWidth = width;
        this.windowHeight = height;
        loadAnimationFrames(width, height); // 重新載入動畫幀以符合新大小
        setSize(width, height);
        pack();
    }

    // 設定移動速度的方法
    public void setMoveSpeed(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }


    // 建構函式，設定寵物的初始大小和速度
    public ResizableDesktopPet(int width, int height, int dx, int dy) {
        create(width, height);
    }


    // 建立寵物的方法，設定大小和移動速度
    private void create(int width, int height) {
        this.windowWidth = width;
        this.windowHeight = height;

        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setSize(width, height);

        loadAnimationFrames(width, height); // 載入動畫圖片

        petLabel.setIcon(rightAnimationFrames[currentFrameIndex]);

        setLayout(new BorderLayout());
        add(petLabel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // 創建計時器移動寵物
        Timer moveTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePet();
            }
        });
        moveTimer.start();

        // 創建計時器更新動畫幀
        Timer animationTimer = new Timer(100, new ActionListener() {  // 調整動畫速度的延遲
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnimationFrame();
            }
        });
        animationTimer.start();
    }

    // 載入動畫幀方法
    private void loadAnimationFrames(int width, int height) {
        // 載入並調整右移動動畫幀（1.png至6.png）
        rightAnimationFrames = new ImageIcon[6];
        for (int i = 0; i < 6; i++) {
            ImageIcon icon = new ImageIcon("C:\\Users\\user\\Desktop\\java-desktop-pet\\" + (i + 1) + ".png"); // 更新為您的圖片路徑
            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            rightAnimationFrames[i] = new ImageIcon(scaledImage);
        }

        // 載入並調整左移動動畫幀（7.png至12.png）
        leftAnimationFrames = new ImageIcon[6];
        for (int i = 0; i < 6; i++) {
            ImageIcon icon = new ImageIcon("C:\\Users\\user\\Desktop\\java-desktop-pet\\" + (i + 7) + ".png"); // 更新為您的圖片路徑
            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            leftAnimationFrames[i] = new ImageIcon(scaledImage);
        }
    }

    // 移動寵物方法
    private void movePet() {
        Point currentLocation = getLocation();
        int newX = currentLocation.x + dx;
        int newY = currentLocation.y + dy;

        // 檢查是否與視窗邊緣碰撞
        if (newX < 0 || newX + windowWidth > Toolkit.getDefaultToolkit().getScreenSize().width) {
            dx = -dx; // 水平方向反轉
            newX = currentLocation.x + dx;
        }
        if (newY < 0 || newY + windowHeight > Toolkit.getDefaultToolkit().getScreenSize().height) {
            dy = -dy; // 垂直方向反轉
            newY = currentLocation.y + dy;
        }

        setLocation(newX, newY);

        // 根據移動方向更新movingRightUpOrDown
        movingRightUpOrDown = (dx > 0 && dy > 0) || (dx > 0 && dy < 0);
    }

    // 更新動畫幀方法
    private void updateAnimationFrame() {
        currentFrameIndex = (currentFrameIndex + 1) % 6;
        if (movingRightUpOrDown) {
            petLabel.setIcon(rightAnimationFrames[currentFrameIndex]);
        } else {
            petLabel.setIcon(leftAnimationFrames[currentFrameIndex]);
        }
    }
}

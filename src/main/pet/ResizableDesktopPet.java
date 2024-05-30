package main.pet;

import plugin.PetPlugin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ResizableDesktopPet extends JFrame {
    private final JLabel petLabel = new JLabel();
    private int windowWidth = 100;
    private int windowHeight = 100;
    private int dx;  // 水平移動速度
    private int dy;  // 垂直移動速度
    private final Random random = new Random();
    private boolean movingRight = true;
    private PetPlugin petPlugin;

    // 設定初始位置的方法
    public void setInitialPosition(int x, int y) {
        setLocation(x, y);
    }


    // 設定移動速度的方法
    public void setMoveSpeed(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    // 建構函式，設定寵物的初始大小和速度
    public ResizableDesktopPet(PetPlugin petPlugin) {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        this.petPlugin =  petPlugin;
        petPlugin.build();

        petLabel.setIcon(petPlugin.getImage());

        setLayout(new BorderLayout());
        add(petLabel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // 創建計時器移動寵物和更新動畫幀
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePet();
                petLabel.setIcon(movingRight ?  petPlugin.getImage() : flipImageHorizontally(petPlugin.getImage()));
            }
        });
        timer.start();
    }



    // 水平翻轉圖像方法
    private ImageIcon flipImageHorizontally(ImageIcon icon) {
        Image originalImage = icon.getImage();
        int width = originalImage.getWidth(null);
        int height = originalImage.getHeight(null);

        BufferedImage flippedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = flippedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, width, height, width, 0, 0, height, null);
        g2d.dispose();

        return new ImageIcon(flippedImage);
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
        movingRight = (dx >= 0 && dy >= 0) || (dx >= 0 && dy <= 0);
    }
}

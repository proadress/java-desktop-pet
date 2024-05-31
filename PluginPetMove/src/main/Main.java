package main;

import plugin.PetPlugin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main implements PetPlugin {
    private List<ImageIcon> animationFrames;
    private final int frameCount = 32;  // 32个图片
    private final int windowWidth = 100;
    private final int windowHeight = 100;
    private int currentFrameIndex = 0;
    private final int delay = 5;

    @Override
    public String getName() {
        return "DogAnimation";
    }

    @Override
    public void build() {
        animationFrames = new ArrayList<>();
        try {
            // 讀取原始圖片
            BufferedImage originalImage = ImageIO.read(new File("picture/0099.png"));

            // 獲取原始圖片的寬度和高度
            int imageWidth = originalImage.getWidth();
            int imageHeight = originalImage.getHeight();

            // 計算每幀的寬度和高度
            int rows = 8;
            int cols = 4;
            int frameWidth = imageWidth / cols;
            int frameHeight = imageHeight / rows;

            // 遍歷每一行每一列，裁切子圖片
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    int x = col * frameWidth;
                    int y = row * frameHeight;
                    BufferedImage subImage = originalImage.getSubimage(x, y, frameWidth, frameHeight);

                    // 縮放裁切後的圖片
                    Image scaledImage = subImage.getScaledInstance(windowWidth, windowHeight, Image.SCALE_SMOOTH);

                    // 添加到動畫幀列表中
                    animationFrames.add(new ImageIcon(scaledImage));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ImageIcon getImage() {
        int temp = currentFrameIndex / delay;
        currentFrameIndex = (currentFrameIndex + 1) % (frameCount * delay);
        return animationFrames.get(temp);
    }
}
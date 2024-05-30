package main.pet;

import plugin.PetPlugin;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
public class DogAnimation implements PetPlugin {
    private List<ImageIcon> animationFrames;
    private final int imageCount = 6;
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
        // 載入並調整右移動動畫幀（1.png至6.png）
        animationFrames = new ArrayList<>();
        for (int i = 0; i < imageCount; i++) {
            ImageIcon icon = new ImageIcon("picture\\"+ (i + 1) + ".png"); // 更新為您的圖片路徑
            Image scaledImage = icon.getImage().getScaledInstance(windowWidth, windowHeight, Image.SCALE_SMOOTH);
            animationFrames.add(new ImageIcon(scaledImage)) ;
        }
    }

    @Override
    public ImageIcon getImage() {
        int temp = currentFrameIndex/delay;
        currentFrameIndex = (currentFrameIndex + 1) % (imageCount * delay);
        return animationFrames.get(temp);
    }
}

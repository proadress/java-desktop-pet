package test;

import main.pet.ResizableDesktopPet;

import javax.swing.*;

public class ResizableDesktopPetTest {
    // TODO
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ResizableDesktopPet pet = new ResizableDesktopPet(300, 400, 5, 5);//預設初始大小和速度
            pet.setInitialPosition(1000, 10); // 設定初始位置
            pet.setPetSize(300, 400); // 設定寵物大小
            pet.setMoveSpeed(10,10);// 設定移動速度
        });
    }
}

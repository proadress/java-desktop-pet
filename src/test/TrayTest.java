package test;

import main.tray.Tray;
import main.tray.TrayMenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TrayTest {
    public static void main(String[] args) {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new TrayMenuItem("按鈕1", e -> {
            Tray tray = Tray.getInstance();
            tray.updateTrayIconImage("aid.png");
        }));
        menuItems.add(new TrayMenuItem("按鈕2", e -> {
            Tray tray = Tray.getInstance();
            tray.updateTrayIconImage("computer.png");
        }));
        menuItems.add(new TrayMenuItem("按鈕3", e -> {
            String imagePath = JOptionPane.showInputDialog(null, "請輸入圖片路徑", "1.png");
            Tray tray = Tray.getInstance();
            tray.updateTrayIconImage(imagePath);
        }));
        Tray tray = Tray.getInstance();
        tray.start(menuItems);
    }
}

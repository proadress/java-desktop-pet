package test;

import main.tray.Tray;
import main.tray.TrayMenuItem;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TrayTest {
    public static void main(String[] args) {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new TrayMenuItem("aid pic", e -> {
            Tray tray = Tray.getInstance();
            tray.updateTrayIconImage("aid.png");
        }));

        menuItems.add(new TrayMenuItem("computer pic", e -> {
            Tray tray = Tray.getInstance();
            tray.updateTrayIconImage("computer.png");
        }));

        menuItems.add(new TrayMenuItem("customize own", e -> {

            JFileChooser fileChooser = new JFileChooser();
            //指定只能打開何種檔案類型

            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "PNG Images", "png");
            fileChooser.setFileFilter(filter);

            int returnValue = fileChooser.showOpenDialog(null);//叫出filechooser
            if (returnValue == JFileChooser.APPROVE_OPTION) //判斷是否選擇檔案
            {
                File selectedFile = fileChooser.getSelectedFile();//指派給File
                System.out.println(selectedFile.getName()); //印出檔名
                Tray tray = Tray.getInstance();
                tray.updateTrayIconImage(selectedFile.getAbsolutePath());
            }

        }));
    }
}

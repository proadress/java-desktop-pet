package main;

import main.tray.Tray;
import main.tray.TrayMenuItem;
import plugin.TrayPlugin;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main implements TrayPlugin {
    public String name = "costomize";


    public static void main(String[] args) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void build() {
        System.out.println("building " + name);
    }

    @Override
    public List<MenuItem> getMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        MenuItem item = new MenuItem("costomize");
        item.addActionListener(e -> {
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
        });
        items.add(item);
        return items;
    }


}

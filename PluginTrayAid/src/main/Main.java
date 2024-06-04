package main;

import plugin.TrayPlugin;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Main implements TrayPlugin {
    public String name = "aid move";


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
        MenuItem item = new MenuItem("open file");
        item.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            //指定只能打開何種檔案類型

            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JAR", "jar");
            fileChooser.setFileFilter(filter);
            fileChooser.setDialogTitle("選擇新增檔案");
            int returnValue = fileChooser.showOpenDialog(null);//叫出filechooser
            if (returnValue == JFileChooser.APPROVE_OPTION) { //判斷是否選擇檔案
                File selectedFile = fileChooser.getSelectedFile();//指派給File
                File destinationFile = new File("out/artifacts/pet/" + selectedFile.getName());
                System.out.println(selectedFile.getName()); //印出檔名
                try {
                    Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    JOptionPane.showMessageDialog(null, "檔案已成功存至: " + destinationFile.getAbsolutePath());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "檔案存取失敗: " + ex.getMessage());
                }
            }
        });
        items.add(item);
        return items;
    }


}
package main.tray;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class Tray {
    private static volatile Tray instance;
    private TrayIcon trayIcon;
    private String picture = "computer.png"; // make picture non-final to update it
    private final ActionListener loadTrayListener = e -> {
        JFileChooser fileChooser = new JFileChooser();
        //指定只能打開何種檔案類型

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JAR", "jar");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("選擇新增檔案");
        int returnValue = fileChooser.showOpenDialog(null);//叫出filechooser
        if (returnValue == JFileChooser.APPROVE_OPTION) { //判斷是否選擇檔案
            File selectedFile = fileChooser.getSelectedFile();//指派給File
            File destinationFile = new File("out/artifacts/tray/" + selectedFile.getName());
            System.out.println(selectedFile.getName()); //印出檔名
            try{
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(null, "檔案已成功存至: " + destinationFile.getAbsolutePath());
            }catch (IOException ex){
                JOptionPane.showMessageDialog(null, "檔案存取失敗: " + ex.getMessage());
            }
        }
    };

    private final ActionListener deleteListener = e -> {
        JFileChooser fileChooser1 = new JFileChooser();
        //指定只能打開何種檔案類型

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JAR", "jar");
        fileChooser1.setFileFilter(filter);

        fileChooser1.setDialogTitle("選擇刪除檔案");

        int result = fileChooser1.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            // 取得選擇的檔案
            File selectedFile = fileChooser1.getSelectedFile();
            // 刪除檔案
            if (selectedFile.delete()) {
                JOptionPane.showMessageDialog(null, "檔案已成功刪除: " + selectedFile.getAbsolutePath());
            }
            else {
                JOptionPane.showMessageDialog(null, "檔案刪除失敗");
            }
        }
    };

    private Tray() {
    }

    public static Tray getInstance() {
        if (instance == null) {
            synchronized (Tray.class) {
                if (instance == null) {
                    instance = new Tray();
                }
            }
        }
        return instance;
    }

    public void start(List<MenuItem> menuItemList) {
        if (SystemTray.isSupported()) {
            SystemTray systemTray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage(picture);

            PopupMenu popup = createPopupMenu(menuItemList);

            trayIcon = new TrayIcon(image, "Tray Demo", popup);
            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(loadTrayListener);

            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("Unable to add to system tray: " + e.getMessage());
            }
        } else {
            System.out.println("System tray is not supported!!!");
        }
    }

    private PopupMenu createPopupMenu(List<MenuItem> menuItemList) {
        PopupMenu popup = new PopupMenu();

        MenuItem loadTrayItem = new MenuItem("tray");
        MenuItem loadPetItem = new MenuItem("pet");

        PopupMenu loadMenu = new PopupMenu("Load JAR");
        PopupMenu deleteMenu = new PopupMenu("Delete JAR");

        MenuItem deleteTrayItem = new MenuItem("tray");
        MenuItem deletePetItem = new MenuItem("pet");

        popup.add(loadMenu);
        popup.addSeparator();
        popup.add(deleteMenu);

        loadMenu.add(loadTrayItem);
        loadMenu.addSeparator();
        loadMenu.add(loadPetItem);

        deleteMenu.add(deleteTrayItem);
        deleteMenu.addSeparator();
        deleteMenu.add(deletePetItem);

        deleteTrayItem.addActionListener(deleteListener);
        deletePetItem.addActionListener(deleteListener);
        loadTrayItem.addActionListener(loadTrayListener);

        loadPetItem.addActionListener(e -> {
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
                try{
                    Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    JOptionPane.showMessageDialog(null, "檔案已成功存至: " + destinationFile.getAbsolutePath());
                }catch (IOException ex){
                    JOptionPane.showMessageDialog(null, "檔案存取失敗: " + ex.getMessage());
                }
            }
        });

        MenuItem exit = new MenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));

        popup.addSeparator();
        for (MenuItem item : menuItemList) {
            popup.add(item);
        }
        popup.addSeparator();
        popup.add(exit);
        
        return popup;
    }

    public void updateTrayIconImage(String newPicture) {
        if (trayIcon != null) {
            picture = newPicture; // Update the picture path
            Image updatedImage = Toolkit.getDefaultToolkit().getImage(newPicture);
            trayIcon.setImage(updatedImage);
        }
    }
}

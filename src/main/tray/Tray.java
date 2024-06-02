package main.tray;

import main.pet.ResizableDesktopPet;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class Tray {
    private static volatile Tray instance;
    private TrayIcon trayIcon;
    private String picture = "picture/run.png"; // make picture non-final to update it
    private final ActionListener defultAction = e -> {
        ResizableDesktopPet pet = ResizableDesktopPet.getInstance();
        if (pet.isVisible()) {
            updateTrayIconImage("picture/stop.png");
            pet.stopTimer();
        } else {
            updateTrayIconImage("picture/run.png");
            pet.resetTimer();
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
            trayIcon.addActionListener(defultAction);

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
        MenuItem defaultItem = new MenuItem("stop or start pet");
        defaultItem.addActionListener(defultAction);

//        MenuItem deleteItem = new MenuItem("Delete JAR");
//
//        deleteItem.addActionListener(e -> {
//            JFileChooser fileChooser1 = new JFileChooser();
//            //指定只能打開何種檔案類型
//
//            FileNameExtensionFilter filter = new FileNameExtensionFilter(
//                    "JAR", "jar");
//            fileChooser1.setFileFilter(filter);
//
//            fileChooser1.setDialogTitle("選擇刪除檔案");
//
//            int result = fileChooser1.showOpenDialog(null);
//
//            if (result == JFileChooser.APPROVE_OPTION) {
//                // 取得選擇的檔案
//                File selectedFile = fileChooser1.getSelectedFile();
//                // 刪除檔案
//                if (selectedFile.delete()) {
//                    JOptionPane.showMessageDialog(null, "檔案已成功刪除: " + selectedFile.getAbsolutePath());
//                } else {
//                    JOptionPane.showMessageDialog(null, "檔案刪除失敗");
//                }
//            }
//        });

        MenuItem exit = new MenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));

        popup.add(defaultItem);
        popup.addSeparator();
//        popup.add(deleteItem);
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

package main.tray;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class Tray {
    private static volatile Tray instance;
    private TrayIcon trayIcon;
    private String picture = "computer.png"; // make picture non-final to update it
    private final ActionListener defaultListener = e -> {
        System.out.println("預設動作");
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
            trayIcon.addActionListener(defaultListener);

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

        MenuItem defaultItem = new MenuItem("Default Action");
        defaultItem.addActionListener(defaultListener);

        MenuItem exit = new MenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));

        popup.add(defaultItem);
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

package tray;

import java.awt.*;
import java.awt.event.ActionListener;

public class Tray {
    private static TrayIcon trayIcon;
    final String picture;
    public Tray(){
        picture = "computer.png";

        if (SystemTray.isSupported()) {
            setupSystemTray();
        } else {
            System.out.println("System tray is not supported!!!");
        }
        // simulate a state change
        updateTrayIconImage();
    }
    private void setupSystemTray() {
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().getImage(picture);

        ActionListener listener = e -> {
            // execute default action of the application
            System.out.println("Default action executed");
        };

        PopupMenu popup = createPopupMenu(listener);

        trayIcon = new TrayIcon(image, "Tray Demo", popup);
        trayIcon.setImageAutoSize(true);  // set image auto size to true

        trayIcon.addActionListener(listener);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println(e);
        }
    }

    private static PopupMenu createPopupMenu(ActionListener listener) {
        PopupMenu popup = new PopupMenu();

        MenuItem defaultItem = new MenuItem("Default Action");
        defaultItem.addActionListener(listener);

        MenuItem item1 = new MenuItem("Option 1");
        item1.addActionListener(e -> {
            System.out.println("Option 1 selected");
        });

        MenuItem item2 = new MenuItem("save");
        item2.addActionListener(e -> {
            System.out.println("Option 2 selected");
            mainProcess.FileData.setSetting("example_setting", "new_value");

            // Get a setting value
            String exampleSetting = mainProcess.FileData.getSetting("example_setting");
            System.out.println("Setting example_setting: " + exampleSetting);
        });

        MenuItem item3 = new MenuItem("exit");
        item3.addActionListener(e -> {
            System.exit(0);
        });

        // add items to the popup menu
        popup.add(defaultItem);
        popup.addSeparator();  // adds a separator line
        popup.add(item1);
        popup.add(item2);
        popup.add(item3);

        return popup;
    }


    private void updateTrayIconImage() {
        if (trayIcon != null) {
            Image updatedImage = Toolkit.getDefaultToolkit().getImage(picture);
            trayIcon.setImage(updatedImage);
        }
    }
}
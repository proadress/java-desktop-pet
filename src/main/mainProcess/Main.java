package main.mainProcess;

import main.tray.Tray;

import java.awt.*;

public class Main {
    private static TrayIcon trayIcon;
    final static String picture = "computer.png";
//    FileData file = new FileData();

    public static void main(String[] args) throws Exception {
//        FileData.loadSettings();
//        main.tray.Tray main.tray = new main.tray.Tray();
        Tray tray;
        try {
            PluginManager pluginManager = new PluginManager("out/artifacts");
            pluginManager.loadPlugins();
            pluginManager.executePlugins();
//            pluginManager.plugins.get(0).run();
            if (pluginManager.plugins.isEmpty()) {
                tray = new Tray();
            } else {
                tray = new Tray(pluginManager.plugins.get(0));
            }
            tray.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        ResizableDesktopPet pet = new ResizableDesktopPet();
        // TODO
    }
}

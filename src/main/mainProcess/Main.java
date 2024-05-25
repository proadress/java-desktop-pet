package main.mainProcess;

import main.tray.Tray;
import plugin.TrayPlugin;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        FileData.loadSettings();
        List<MenuItem> menuItems = new ArrayList<>();
        PluginManager pluginManager = new PluginManager("out/artifacts/tray");
        for (TrayPlugin trayPlugin : pluginManager.getTrayPlugins()) {
            menuItems.addAll(trayPlugin.getMenuItems());
        }
        Tray tray = Tray.getInstance();
        tray.start(menuItems);
    }
}

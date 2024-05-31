package main.mainProcess;

import main.pet.ResizableDesktopPet;
import main.tray.Tray;
import plugin.PetPlugin;
import plugin.TrayPlugin;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        FileData.loadSettings();
        List<MenuItem> menuItems = new ArrayList<>();
//        ResizableDesktopPet pet;

        PluginManager pluginManager = new PluginManager("out/artifacts/tray");
        for (TrayPlugin trayPlugin : pluginManager.getTrayPlugins()) {
            menuItems.addAll(trayPlugin.getMenuItems());
        }
        Tray tray = Tray.getInstance();
        tray.start(menuItems);
        pluginManager = new PluginManager("out/artifacts/pet");
        int y = 1;
        for (PetPlugin plugin : pluginManager.getPetPlugins()) {
            ResizableDesktopPet pet = new ResizableDesktopPet(plugin);
            pet.setMoveSpeed(1, y++);
        }
    }
}

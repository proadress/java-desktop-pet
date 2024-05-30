package main.mainProcess;

import main.pet.DogAnimation;
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
        List<ResizableDesktopPet> pets = new ArrayList<>();

        PluginManager pluginManager = new PluginManager("out/artifacts/tray");
        for (TrayPlugin trayPlugin : pluginManager.getTrayPlugins()) {
            menuItems.addAll(trayPlugin.getMenuItems());
        }
        Tray tray = Tray.getInstance();
        tray.start(menuItems);
        pluginManager = new PluginManager("out/artifacts/pet");
        for(PetPlugin plugin : pluginManager.getPetPlugins()){
             pets.add(new ResizableDesktopPet(plugin));
        }
    }
}

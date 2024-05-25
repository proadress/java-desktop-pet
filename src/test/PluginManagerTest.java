package test;// PluginManager.java

import main.mainProcess.PluginManager;
import plugin.PetPlugin;
import plugin.TrayPlugin;

public class PluginManagerTest {

    public static void main(String[] args) {
        PluginManager trayPluginManager = new PluginManager("out/artifacts/tray");
        for (TrayPlugin trayPlugin : trayPluginManager.getTrayPlugins()) {
            trayPlugin.build();
        }
        PluginManager petPluginManager = new PluginManager("out/artifacts/pet");
        for (PetPlugin petPlugin : petPluginManager.getPetPlugins()) {
            petPlugin.build();
        }
    }
}

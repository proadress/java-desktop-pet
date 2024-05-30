package main.mainProcess;

import plugin.PetPlugin;
import plugin.TrayPlugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;

public class PluginManager {
    private List<TrayPlugin> trayPlugins;
    private List<PetPlugin> petPlugins;
    private final File pluginDir;

    public PluginManager(String pluginDirPath) {
        this.pluginDir = new File(pluginDirPath);
        if (!pluginDir.exists()) {
            pluginDir.mkdirs();
        }
    }

    public List<TrayPlugin> getTrayPlugins() {
        if (trayPlugins == null) {
            trayPlugins = new ArrayList<>();
            loadPlugins(TrayPlugin.class, trayPlugins);
        }
        return trayPlugins;
    }

    public List<PetPlugin> getPetPlugins() {
        if (petPlugins == null) {
            petPlugins = new ArrayList<>();
            loadPlugins(PetPlugin.class, petPlugins);
        }
        return petPlugins;
    }

    private <T> void loadPlugins(Class<T> pluginClass, List<T> pluginList) {
        try {
            for (File file : Objects.requireNonNull(pluginDir.listFiles())) {
                if (file.isFile() && file.getName().endsWith(".jar")) {
                    System.out.println(file.toURI().toURL());
                    URLClassLoader loader = new URLClassLoader(new URL[]{file.toURI().toURL()}, getClass().getClassLoader());
                    ServiceLoader<T> serviceLoader = ServiceLoader.load(pluginClass, loader);
                    System.out.println(serviceLoader);
                    System.out.println(pluginList);
                    for (T plugin : serviceLoader) {
                        System.out.println("***Load Plugin: " + plugin.getClass().getName());
                        pluginList.add(plugin);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

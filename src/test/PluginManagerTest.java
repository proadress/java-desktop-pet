package main.mainProcess;// PluginManager.java

import plugin.Plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class PluginManager {
    public final List<Plugin> plugins = new ArrayList<>();
    private final File pluginDir;

    public PluginManager(String pluginDirPath) {
        this.pluginDir = new File(pluginDirPath);
        if (!pluginDir.exists()) {
            pluginDir.mkdirs();
        }
    }

    public void loadPlugins() throws Exception {
        for (File file : pluginDir.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".jar")) {
                System.out.println(file.toURI().toURL());
                URLClassLoader loader = new URLClassLoader(new URL[]{file.toURI().toURL()}, getClass().getClassLoader());
                ServiceLoader<Plugin> serviceLoader = ServiceLoader.load(Plugin.class, loader);
                for (Plugin plugin : serviceLoader) {
                    plugins.add(plugin);
                    System.out.println("add list");

                }
            }
        }
    }

    public void executePlugins() {
        System.out.println("start run");
        for (Plugin plugin : plugins) {
            System.out.println("run");
            plugin.run();
        }
    }

//    public void addPluginFromServer(String pluginUrl) throws Exception {
//        String fileName = pluginUrl.substring(pluginUrl.lastIndexOf('/') + 1);
//        File file = new File(pluginDir, fileName);
//
//        try (InputStream in = new URL(pluginUrl).openStream(); FileOutputStream out = new FileOutputStream(file)) {
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//            while ((bytesRead = in.read(buffer)) != -1) {
//                out.write(buffer, 0, bytesRead);
//            }
//        }
//    }


}

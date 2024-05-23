package mainProcess;

import java.awt.*;

public class Main {
    private static TrayIcon trayIcon;
    final static String picture = "computer.png";
//    main_process.FileData file  = new main_process.FileData()

    public static void main(String[] args) throws Exception {
//        FileData.loadSettings();
//        tray.Tray tray = new tray.Tray();
        try {
            PluginManager pluginManager = new PluginManager("out/artifacts/java_desktop_pet_jar");
            pluginManager.loadPlugins();
            pluginManager.executePlugins();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //JavaFx.src.ResizableDesktopPet pet = new JavaFx.src.ResizableDesktopPet();
        // TODO
    }
}

package mainProcess;
import java.awt.*;

public class Main {
    private static TrayIcon trayIcon;
    final static String picture = "computer.png";
//    main_process.FileData file  = new main_process.FileData()
 
    public static void main(String[] args) {
        tray.Tray tray = new tray.Tray();
        FileData.loadSettings();
    }


}

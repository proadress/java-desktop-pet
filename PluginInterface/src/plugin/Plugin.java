package plugin;


import java.awt.*;
import java.util.List;

public interface Plugin {
    void run();

    List<MenuItem> getMenuItems();
}
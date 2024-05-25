package main.tray;

import java.awt.*;
import java.awt.event.ActionListener;

public class TrayMenuItem extends MenuItem {
    public TrayMenuItem(String name, ActionListener action) {
        super(name);
        super.addActionListener(action);
    }
}

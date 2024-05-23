package test;

import plugin.Plugin;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Test1 implements Plugin {

    @Override
    public void run() {
        System.out.println("test run");
    }

    @Override
    public List<MenuItem> getMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();

        MenuItem item1 = new MenuItem("Option 1");
        item1.addActionListener(e -> {
            System.out.println("Option 1 selected");
        });
        MenuItem item2 = new MenuItem("Option 1");
        item2.addActionListener(e -> {
            System.out.println("Option 1 selected");
        });
        menuItems.add(item1);
        menuItems.add(item2);
        return menuItems;
    }


    public static void main(String[] args) {

    }
}
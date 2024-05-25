package main;


import plugin.TrayPlugin;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main implements TrayPlugin {
    public String name = "aid move";


    public static void main(String[] args) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void build() {
        System.out.println("building " + name);
    }

    @Override
    public List<MenuItem> getMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        MenuItem item = new MenuItem("option 1");
        item.addActionListener(e -> System.out.println("123"));
        items.add(item);
        return items;
    }


}
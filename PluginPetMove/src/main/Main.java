package main;

import plugin.PetPlugin;

public class Main implements PetPlugin {
    public String name = "pet plug";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void build() {
        System.out.println("building " + name);
    }

    public static void main(String[] args) {

    }
}
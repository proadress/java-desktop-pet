package main.mainProcess;

import main.pet.DogAnimation;
import main.pet.ResizableDesktopPet;
import main.tray.Tray;
import plugin.PetPlugin;
import plugin.TrayPlugin;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        FileData.loadSettings();
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("open pet"));
        menuItems.get(0).addActionListener(e -> {
            PluginManager petpluginManager = new PluginManager("out/artifacts/pet");
            List<String> options = new ArrayList<>();
            List<PetPlugin> plugins = petpluginManager.getPetPlugins();
            for (PetPlugin plugin : plugins) {
                options.add(plugin.getName());
            }
            JComboBox<String> comboBox = new JComboBox<>(options.toArray(new String[0]));
            int result = JOptionPane.showConfirmDialog(null, comboBox, "選擇一個寵物", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String selectedOption = (String) comboBox.getSelectedItem();
                System.out.println(selectedOption);
                for (PetPlugin plugin : plugins) {
                    if (plugin.getName().equals(selectedOption)) {
                        ResizableDesktopPet pet = ResizableDesktopPet.getInstance();
                        pet.build(plugin);
                        pet.setMoveSpeed(1, 0);
                        pet.setInitialPosition(1000, 720);
//                        pet.resetTimer();
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "你取消了選擇");
            }
        });
        PluginManager pluginManager = new PluginManager("out/artifacts/tray");
        for (TrayPlugin trayPlugin : pluginManager.getTrayPlugins()) {
            menuItems.addAll(trayPlugin.getMenuItems());
        }
        Tray tray = Tray.getInstance();
        tray.start(menuItems);
        ResizableDesktopPet.getInstance().build(new DogAnimation());
        ResizableDesktopPet.getInstance().setMoveSpeed(1, 0);
    }
}

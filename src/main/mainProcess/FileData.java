package main.mainProcess;

import java.io.*;
import java.util.Properties;

public class FileData {
    private static final String SETTINGS_FILE = "settings.properties";
    private static final Properties settings = new Properties();

    public static void loadSettings() {
        File file = new File(SETTINGS_FILE);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    saveSettings();
                    System.out.println("Settings file created.");
                }
            } catch (IOException e) {
                System.err.println("Error creating settings file: " + e.getMessage());
            }
        } else {
            try (InputStream input = new FileInputStream(SETTINGS_FILE)) {
                settings.load(input);
                printAllSettings();
            } catch (IOException e) {
                System.err.println("Error loading settings: " + e.getMessage());
            }
        }
    }

    private static void saveSettings() {
        try (OutputStream output = new FileOutputStream(SETTINGS_FILE)) {
            settings.store(output, "Application Settings");
        } catch (IOException e) {
            System.err.println("Error saving settings: " + e.getMessage());
        }
    }

    // Method to set or update a setting
    public static void setSetting(String key, String value) {
        settings.setProperty(key, value);
        saveSettings(); // Save settings after updating
        System.out.println("Success creat: " + key + "=" + value);
    }

    // Method to get a setting value
    public static String getSetting(String key) {
        return settings.getProperty(key);
    }

    // Method to delete a setting
    public static void deleteSetting(String key) {
        settings.remove(key);
        saveSettings(); // Save settings after deleting
        System.out.println("Success remove: " + key);
    }

    // Method to print all settings
    private static void printAllSettings() {
        System.out.println("\n*** LOADing FILE ***");
        settings.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println();
    }
}
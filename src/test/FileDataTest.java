package main.mainProcess;

import java.io.*;
import java.util.Properties;

public class FileData {
    private static final String settingsFile = "settings.properties";
    private static Properties settings;

    public static void loadSettings() {
        settings = new Properties();
        File file = new File(settingsFile);

        if (!file.exists()) {
            try {
                file.createNewFile();
                saveSettings();
                System.out.println("create");
            } catch (IOException e) {
                System.err.println("Error creating settings file: " + e.getMessage());
            }
        } else {
            try (InputStream input = new FileInputStream(settingsFile)) {
                settings.load(input);
            } catch (IOException e) {
                System.err.println("Error loading settings: " + e.getMessage());
            }
        }
    }

    private static void saveSettings() {
        try (OutputStream output = new FileOutputStream(settingsFile)) {
            settings.store(output, "Application Settings");
        } catch (IOException e) {
            System.err.println("Error saving settings: " + e.getMessage());
        }
    }

    // Add method to set or update a setting
    public static void setSetting(String key, String value) {
        settings.setProperty(key, value);
        saveSettings(); // Save settings after updating
    }

    // Add method to get a setting value
    public static String getSetting(String key) {
        return settings.getProperty(key);
    }

    // Add method to delete a setting
    public static void deleteSetting(String key) {
        settings.remove(key);
        saveSettings(); // Save settings after deleting
    }
}

package mainProcess;

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
                // Add default settings here 
                settings.setProperty("example_setting", "default_value");
                // You can add more default settings here
                saveSettings();
                System.out.println("create");
            } catch (IOException e) {
                System.err.println("Error creating settings file: " + e.getMessage());
            }
        } else {
            try (InputStream input = new FileInputStream(settingsFile)) {
                settings.load(input);
                // Example: Retrieve a setting
                String exampleSetting = settings.getProperty("example_setting");
                System.out.println("Loaded setting: " + exampleSetting);
                // You can add more settings retrieval here
            } catch (IOException e) {
                System.err.println("Error loading settings: " + e.getMessage());
            }
        }
    }

    public static void saveSettings() {
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

package test;

import main.mainProcess.FileData;

public class FileDataTest {
    public static void main(String[] args) {
        FileData.loadSettings(); // Test the loadSettings method
        FileData.setSetting("exampleKey", "eifjiejqf"); // Test the setSetting method
        System.out.println(FileData.getSetting("exampleKey")); // Test the getSetting method
        FileData.deleteSetting("exampleKey"); // Test the deleteSetting method
    }
}

package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        try {

            FileInputStream input = new FileInputStream("src/main/resources/config.properties");
            props.load(input);

            String directoryPath = props.getProperty("directory.path");
            String outputPath = props.getProperty("output.path");

            DirectoryTreeLister lister = new DirectoryTreeLister(directoryPath, outputPath);
            lister.listDirectoryTree();
            lister.closeWriter();

            input.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
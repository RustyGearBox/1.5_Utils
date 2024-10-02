package org.example;

import java.io.File;
import java.text.SimpleDateFormat;

public class DirectoryTreeLister {
    private String path;

    public DirectoryTreeLister(String path) {
        this.path = path;
    }

    public void listDirectory() {
        File directory = new File(path);
        if (directory.isDirectory()) {
            File[] items = directory.listFiles();
            if (items != null) {
                sortAndPrint(items);
            }
        } else {
            System.out.println("The provided path is not a directory.");
        }
    }

    public void listDirectoryTree() {
        File directory = new File(path);
        if (directory.isDirectory()) {
            System.out.println("Directory tree:");
            listDirectoryTree(directory, 0);
        } else {
            System.out.println("The provided path is not a directory.");
        }
    }

    private void listDirectoryTree(File dir, int level) {
        File[] items = dir.listFiles();
        if (items != null) {
            sortAndPrint(items);
            for (File item : items) {
                if (item.isDirectory()) {
                    System.out.println("  ".repeat(level) + "D: " + item.getName() + " - Last modified: " + getModificationDate(item));
                    listDirectoryTree(item, level + 1);
                } else {
                    System.out.println("  ".repeat(level) + "F: " + item.getName() + " - Last modified: " + getModificationDate(item));
                }
            }
        }
    }

    private void sortAndPrint(File[] items) {
        java.util.Arrays.sort(items);
    }

    private String getModificationDate(File file) {
        long lastModified = file.lastModified();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(lastModified);
    }

}

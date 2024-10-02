package org.example;

public class Main {
    public static void main(String[] args) {

        DirectoryTreeLister directoryTreeLister = new DirectoryTreeLister("src/main/resources/Testing");
        directoryTreeLister.listDirectoryTree();

    }
}
package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        DirectoryTreeLister directoryTreeLister = new DirectoryTreeLister("src/main/resources/Testing");
        directoryTreeLister.listDirectoryTree();

    }
}
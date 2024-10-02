package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        try {
            DirectoryTreeLister directoryTreeLister = new DirectoryTreeLister("src/main/resources/Testing", "Testing.txt");
            directoryTreeLister.listDirectoryTree();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
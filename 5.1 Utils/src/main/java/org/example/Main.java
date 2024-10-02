package org.example;

public class Main {
    public static void main(String[] args) {

        DirectoryLister directoryLister = new DirectoryLister("src/main/resources/Testing");
        directoryLister.listDirectory();

    }
}
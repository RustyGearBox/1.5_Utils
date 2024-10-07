package org.example;

import java.io.IOException;

import static org.example.DirectoryTreeLister.fileDeserializer;
import static org.example.DirectoryTreeLister.fileSerializer;

public class Main {
    public static void main(String[] args) throws IOException {

        fileSerializer("src/main/resources/Testing/Apex.txt",
                      "src/main/resources/Testing_2/");

        fileDeserializer("src/main/resources/Testing_2/ApexSerialized.txt");

    }
}
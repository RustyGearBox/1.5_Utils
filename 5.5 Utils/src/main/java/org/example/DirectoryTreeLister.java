package org.example;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class DirectoryTreeLister {
    private String path;

    Scanner scanner = new Scanner(System.in);
    BufferedReader reader;

    public DirectoryTreeLister(String path) {
        this.path = path;
    }

    HashMap<String, File> itemPath = new HashMap<String, File>();

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

    public void listDirectoryTree() throws IOException {

        File directory = new File(path);
        if (directory.isDirectory()) {
            System.out.println("Directory tree:");
            listDirectoryTree(directory, 0);
            fileReader();

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
                    System.out.println("  ".repeat(level) + "D: "
                            + item.getName() + " - Last modified: " + getModificationDate(item));
                    listDirectoryTree(item, level + 1);
                } else {
                    System.out.println("  ".repeat(level) + "F: "
                            + item.getName() + " - Last modified: " + getModificationDate(item));
                    itemPath.put(item.getName(), item);
                }
            }
        }
    }

    private void fileReader() throws IOException{

        System.out.print("\n" + "Do you want to read the content of one of the files? ");

        if (scanner.next().toLowerCase().equals("yes")) {
            System.out.print("Insert the name of the file: ");
            String fileName = scanner.next();
            String itemPath = String.valueOf(this.itemPath.get(fileName));

            reader = new BufferedReader(new FileReader(itemPath));
            String line = reader.readLine();
            System.out.print("\n");

            while (line != null){
                System.out.println(line);
                line = reader.readLine();
            }
        }
    }

    public static void fileSerializer(String inputPath, String outputPath){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(inputPath))) {
            oos.writeObject(outputPath);
            System.out.println("The object has been serialized.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String fileDeserializer(String inputPath){

        String collection = "";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputPath))) {
            collection = (String) ois.readObject();
            return collection;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return collection;
    }

    private void sortAndPrint(File[] items) {
        Arrays.sort(items);
    }

    private String getModificationDate(File file) {
        long lastModified = file.lastModified();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(lastModified);
    }

}

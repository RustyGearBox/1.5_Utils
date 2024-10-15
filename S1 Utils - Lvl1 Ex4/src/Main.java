import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Write the path of a folder: ");
            DirectoryTreeLister directoryTreeLister = new DirectoryTreeLister(scanner.next());
            directoryTreeLister.listDirectoryTree();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}

class DirectoryTreeLister {
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

    private void sortAndPrint(File[] items) {
        java.util.Arrays.sort(items);
    }

    private String getModificationDate(File file) {
        long lastModified = file.lastModified();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(lastModified);
    }

}
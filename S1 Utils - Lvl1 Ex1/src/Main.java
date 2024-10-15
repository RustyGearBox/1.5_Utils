import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Write the path of a folder: ");
        DirectoryLister directoryLister = new DirectoryLister(scanner.next());
        directoryLister.listDirectory();

    }
}

class DirectoryLister {

    private String path;

    public DirectoryLister(String path) {
        this.path = path;
    }

    public void listDirectory() {
        File directory = new File(path);
        if (directory.isDirectory()) {
            File[] items = directory.listFiles();
            if (items != null) {
                sortAndPrint(items);
                for (File item : items){
                    System.out.println(item.getName());
                }
            }
        } else {
            System.out.println("The provided path is not a directory.");
            System.out.println(path);
        }
    }

    private void sortAndPrint(File[] items) {
        java.util.Arrays.sort(items);
    }
}
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write the path of a folder: ");
        DirectoryTreeLister directoryTreeLister = new DirectoryTreeLister(scanner.next());
        directoryTreeLister.listDirectoryTree();
    }
}

class DirectoryTreeLister {
    private String path;

    public DirectoryTreeLister(String path) {
        this.path = path;
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
                    System.out.println("  ".repeat(level) + "D: " + item.getName()
                            + " - Last modified: " + getModificationDate(item));
                    listDirectoryTree(item, level + 1);
                } else {
                    System.out.println("  ".repeat(level) + "F: " + item.getName()
                            + " - Last modified: " + getModificationDate(item));
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

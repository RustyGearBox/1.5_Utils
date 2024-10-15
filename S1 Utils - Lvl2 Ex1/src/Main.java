import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Properties props = new Properties();
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Write the path of a folder: ");
            FileInputStream input = new FileInputStream(scanner.next()); // IDE -> "./src/config.properties" - CMD -> ./config.properties
            props.load(input);

            String directoryPath = props.getProperty("directory.path");
            String outputPath = props.getProperty("output.path");

            DirectoryTreeLister lister = new DirectoryTreeLister(directoryPath, outputPath);
            lister.listDirectoryTree();
            System.out.println("Successfully wrote to the file.");
            lister.closeWriter();
            input.close();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

}
class DirectoryTreeLister {
    private String path;
    private FileWriter writer;

    public DirectoryTreeLister(String path, String outputPath) throws IOException {
        this.path = path;
        this.writer = new FileWriter(outputPath, false);
    }

    public void closeWriter() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }

    public void listDirectoryTree() throws IOException {
        File directory = new File(path);
        if (directory.isDirectory()) {
            writer.write("Directory tree: \n");
            listDirectoryTree(directory, 0);
        } else {
            System.out.println("The provided path is not a directory.");
        }
    }

    public void listDirectoryTree(File dir, int level) throws IOException {
        try {
            File[] items = dir.listFiles();
            if (items != null) {
                sortAndPrint(items);
                for (File item : items) {
                    if (item.isDirectory()) {
                        writer.write("  ".repeat(level) + "D: " + item.getName()
                                + " - Last modified: " + getModificationDate(item) + "\n");
                        listDirectoryTree(item, level + 1);
                    } else {
                        writer.write("  ".repeat(level) + "F: " + item.getName()
                                + " - Last modified: " + getModificationDate(item) + "\n");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
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
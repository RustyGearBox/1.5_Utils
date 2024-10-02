package org.example;

import java.io.File;

    public class DirectoryLister {
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
            }
        }

        private void sortAndPrint(File[] items) {
            java.util.Arrays.sort(items);
        }
    }


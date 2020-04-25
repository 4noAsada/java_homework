package my.shinoasada;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("---1. 指定关键字搜索文件。2. 指定后缀搜索文件。3. 复制文件/目录。4. 退出。 ---");
        while (true) {
            System.out.print("请输入你的指令：");
            int number = input.nextInt();
            switch (number) {
                case 1:
                    System.out.print("请输入要检索的目录位置：");
                    String path = input.next();
                    System.out.print("请输入搜索关键字：");
                    String key = input.next();
                    search(key, path);
                    break;
                case 2:
                    System.out.print("请输入要检索的目录位置：");
                    String path2 = input.next();
                    System.out.print("请输入搜索关后缀：");
                    String suffix = input.next();
                    searchSuffix(suffix, path2);
                    break;
                case 3:
                    System.out.print("请输入源目录：");
                    String origin = input.next();
                    System.out.print("请输入目标位置：");
                    String destination = input.next();
                    copy(origin, destination);
                    break;
                case 4:
                    input.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    private static void filterListfiles(FilenameFilter filter, String path) {
        File file = new File(path);
        for (File filename : file.listFiles(filter)) {
            System.out.println(filename.getAbsolutePath());
        }
    }

    public static void search(String filecontains, String path) {
        FilenameFilter filter = (dir, name) -> name.contains(filecontains);
        filterListfiles(filter, path);
    }

    public static void searchSuffix(String suffix, String path) {
        FilenameFilter filter = (dir, name) -> name.endsWith(suffix);
        filterListfiles(filter, path);
    }

    public static boolean copy(String filename, String new_filename) {
        File old = new File(filename);
        File destination = new File(new_filename);
        if (old.exists() && !destination.exists()) {
            try {
                Files.copy(old.toPath(), destination.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Copy failed");
                return false;
            }
            return true;
        }
        return false;
    }
}

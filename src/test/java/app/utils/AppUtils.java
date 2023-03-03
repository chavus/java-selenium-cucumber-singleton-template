package app.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class AppUtils {

    public static void waitByTime(int timeInMilliseconds){
        try{
            Thread.sleep(timeInMilliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Path getAbsolutePath(String relativePath){
        Path currentRelativePath = Paths.get(relativePath);
        return currentRelativePath.toAbsolutePath();
    }

    public static List<String> getFileNamesInDirectory(String dirPath){
        File[] files = new File(dirPath).listFiles();
        List<File> filesList = Arrays.asList(files);
        return filesList.stream().map(file -> file.getName()).collect(Collectors.toList());
    }

}

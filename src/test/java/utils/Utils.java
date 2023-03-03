package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Utils {

    private static String getPropertyFromFile(String filePathAndName, String propertyName){
        try{
            Properties prop = new Properties();
            prop.load(Files.newInputStream(Paths.get(filePathAndName)));
            return prop.getProperty(propertyName);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMandatoryProperty(String key, String filePathAndName){
        String property = System.getProperty(key
                , Utils.getPropertyFromFile(filePathAndName, key));
        if (property == null){
            String errorMessage = String.format("Property: %s not found in %s file or command argument -D%s=<value>", key,filePathAndName, key);
            throw new IllegalArgumentException(errorMessage);
        } else {
            return property;
        }
    }

    public static String getPropertyOrDefault(String key, String filePathAndName, String defaultValue) {
        String propertyValue = System.getProperty(key
                , Utils.getPropertyFromFile(filePathAndName, key));
        return propertyValue == null || propertyValue.equals("") ? defaultValue : propertyValue;
    }

    public static int getPropertyOrDefault(String key, String filePathAndName, int defaultValue) {
        String propertyValue = System.getProperty(key
                , Utils.getPropertyFromFile(filePathAndName, key));
        return propertyValue == null || propertyValue.equals("") ? defaultValue : Integer.parseInt(propertyValue);
    }

    public static String convertToSingular(String word){
        String lastChar = word.substring(word.length()-1);
        if (lastChar.equalsIgnoreCase("S")){
            return word.substring(0,word.length()-1);
        }
        return word;
    }

}

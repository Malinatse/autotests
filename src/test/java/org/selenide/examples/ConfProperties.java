package org.selenide.examples;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class ConfProperties {
    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;
    static {
        try {
            //указание пути до файла с настройками
            fileInputStream = new FileInputStream("src/test/resources/conf.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            //обработка возможного исключения (нет файла и т.п.)
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace(); } } }
    /**
     * метод для возврата строки со значением из файла с настройками
     */
    public static String getProperty(String key) {
        String envValue = System.getenv(key);
        if (envValue == null) {
            return PROPERTIES.getProperty(key);
        } else {
            return envValue;
        }
    }
    public static Boolean getPropertyBool(String key) {
        return Boolean.parseBoolean(PROPERTIES.getProperty(key));
    }
}
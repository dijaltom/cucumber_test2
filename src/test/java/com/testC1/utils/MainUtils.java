package com.testC1.utils;


import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.WebDriver;
import org.testng.collections.SetMultiMap;

import java.io.*;
import java.time.Duration;
import java.util.*;

import static com.testC1.hooks.myHooks.driver;

public class MainUtils {

    public static Map<String, String> CSVData = new LinkedHashMap<>();
    public static Map<String, String> testSpace = new LinkedHashMap<>();
    static Properties properties = new Properties();
    public Logger LOGGER = Logger.getLogger(this.getClass());

    protected MainUtils() {
        PatternLayout ej = new PatternLayout("%d{hh:mm:ss} : %-5p : [%c{1}] :::: %m%n");
        ConsoleAppender qwd = new ConsoleAppender(ej);
        Logger.getRootLogger().addAppender(qwd);
    }

    /**
     *
     * @param key used to access the prop value from .properties value
     * @return will return value if the key present in the properties file
     */
    public static String get(String key) {
        try {
            if (properties.isEmpty()) {
                loadDeafaultProperties();
            }
            return properties.get(key).toString();
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
        return properties.get(key).toString();
    }

    public static void waitUtils(int seconds) {


        driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    protected static WebDriver driver() {
        return driver;
    }

    /**
     * It is used to load Properties from .properties file
     */
    public static void loadDeafaultProperties() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/Config/config.properties");
        properties.load(fileInputStream);
    }

    /**
     * It is used to loading CSV Data based on first column value in a row
     @param path CSV path
     @param id first column value in a row
     */
    public static void loadCSVData(String path, String id) throws IOException {
        CSVData.clear();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        List<String> Headers = new ArrayList<>();
        List<String> Data = new ArrayList<>();
        boolean headPass = true;
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            if (headPass) {
                Headers = Arrays.stream(line.split(",")).toList(); // taking the header values to List
                headPass = false;
            }
            if (line.split(",")[0].contains(id)) {
                Data = Arrays.stream(line.split(",")).toList();   //taking the lines after the first row
                if (Data.size() == Headers.size()) {
                    for (int i = 0; i < Headers.size(); i++) {
                        CSVData.put(Headers.get(i), Data.get(i));
                    }
                } else {
                    System.err.println("Mismatch found in the CSV layout");
                    throw new ArrayIndexOutOfBoundsException();
                }
            }
        }
    }

    /**
     * Is used to set global key value pair
     * @param key key used for saving the value
     * @param value value for the key
     */
    public static void setVar(String key,String value){
        testSpace.put(key,value);
    }

    /**
     * It is used to access global value based on Key
     * @param key is the mapKey
     * @return it will return String Value for the corresponding key
     */
    public static String getVar(String key){
        return testSpace.get(key);
    }


}

package co.wedevx.digitalbank.automation.ui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//build a logic that reads the config file(properties file)
public class ConfigReader {
    private static Properties properties;

    //static initializer runs the block only once for whole project
    //instance initializer runs the block once for every object creation from a class
    static   {


        //filepath -> the directory of your properties file
        String filePath = "src/test/resources/properties/digitalbank.properties";

        //this is a class that enables you to read files
        //it throws a checked exception
        FileInputStream input = null;

        try {
            input = new FileInputStream(filePath);
            properties = new Properties();
            properties.load((input));

        }catch (IOException e) {
            System.out.println("File not found");
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        System.out.println(properties.get("my_name"));
        System.out.println(properties.get("browser"));
        System.out.println(properties.get("environment"));
    }



    public static String getPropertiesValue(String key) {
        return properties.getProperty(key);
    }

}
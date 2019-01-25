package my.webapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Environment {
    public static final String PATH_TO_PROPERTIES = "project.properties";


    public static String getProperty(String pr)
    {
        InputStream input;
        Properties prop = new Properties();

        try {
            //обращаемся к файлу и получаем данные
            input = Environment.class.getClassLoader().getResourceAsStream(PATH_TO_PROPERTIES);
            prop.load(input);

        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружен");
            e.printStackTrace();
            return "Unknown property";
        }

        return prop.getProperty(pr);
    }

}

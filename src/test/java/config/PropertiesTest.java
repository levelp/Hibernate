package config;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * Работа с .properties-файлами
 */
public class PropertiesTest extends Assert {

    /**
     * Получение системных свойств
     */
    @Test
    public void testSystemProperties() {
        // Получение несуществующего свойства
        assertNull(System.getProperty("NOT-EXISTING-PROPERTY"));

        String value = System.getProperty("NOT-EXISTING-PROPERTY");
        if (value != null && !value.isEmpty()) {
            fail("Нет такого свойства :)");
        }

        System.out.println("Все системные свойства:");
        Properties properties = System.getProperties();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            System.out.println(" " + entry.getKey() + " = " + entry.getValue());
        }
    }

    /**
     * Свойства из файла настроек
     *
     * @throws IOException
     */
    @Test
    public void testConfigProperties() throws IOException {
        // Имя файла для загрузки
        String filename = "config.properties";
        // InputStream input = PropertiesTest.class.getClassLoader().getResourceAsStream(filename);
        InputStream input = getClass().getClassLoader().
                getResourceAsStream(filename);
        if (input == null) {
            System.out.println("Sorry, unable to find " + filename);
            return;
        }
        Properties properties = new Properties();
        // Считываем файл настроек в UTF-8
        properties.load(new InputStreamReader(input, "UTF-8"));

        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = properties.getProperty(key);
            System.out.println(key + " = " + value);
        }

        assertEquals("Тестовая строка", properties.getProperty("name"));
        assertEquals("AB - код A и B", properties.getProperty("test2"));
        assertEquals("2 + 2 + 10 + 15", properties.getProperty("var2"));
    }
}

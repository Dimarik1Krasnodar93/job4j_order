package ru.job4j.job4j_order;

import lombok.Getter;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.util.Properties;

@Component
@Getter
public class Param {
    private Properties properties;
    public Param() {
        properties = new Properties();
        try (InputStream inputStream = Param.class
                .getClassLoader()
                .getResourceAsStream("server2.properties")) {
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

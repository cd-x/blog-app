package com.example.springlearnings.setup;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URL;
import java.util.List;

@Slf4j
public class TestHelper {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T createObjectFromJsonFile(String fileName, Class<T> object) {
        try {
            File jsonFile = getTestResourceFile(fileName);
            return mapper.readValue(jsonFile, object);
        } catch (Exception e) {
            log.error("error occurred while parsing file:{}", fileName, e);
        }
        return null;
    }

    public static <T> List<T> createObjectListFromJsonFile(String fileName, Class<T> clazz) {
        try {
            File jsonFile = getTestResourceFile(fileName);
            return mapper.readValue(jsonFile,
                    mapper.getTypeFactory().constructCollectionType(List.class, clazz)
            );
        } catch (Exception e) {
            log.error("Error parsing file: {}", fileName, e);
            return null;
        }
    }

    private static File getTestResourceFile(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }
        return new File(resource.getFile());
    }

}

package com.demo.poc.commons.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonReader {

    public static String readJSON(String filename) throws IOException {
        String fileContent = readFileContent(filename);
        ObjectMapper mapper = new ObjectMapper();
        Object jsonObject = mapper.readValue(fileContent, Object.class);
        return mapper.writeValueAsString(jsonObject);
    }

    public static String readFileContent(String filename) {
        ClassPathResource classPathResource = new ClassPathResource(filename);

        String fileContent;
        try {
            byte[] fileContentInBytes = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
            fileContent = new String(fileContentInBytes, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
        return fileContent;
    }
}

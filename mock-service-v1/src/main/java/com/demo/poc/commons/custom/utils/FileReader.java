package com.demo.poc.commons.custom.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileReader {

    public static String readRaw(String filename) throws IOException {
        ClassPathResource resource = new ClassPathResource(filename);
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static String readJson(String filename) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(filename);

        byte[] bdata = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
        String data = new String(bdata, StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        Object jsonObject = mapper.readValue(data, Object.class);
        return mapper.writeValueAsString(jsonObject);
    }
}

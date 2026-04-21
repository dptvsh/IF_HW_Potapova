package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Auth.RegistrationRequest;

import java.io.File;
import java.io.IOException;

public class JsonReader {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static RegistrationRequest readRegistrationRequest(String path) {
        try {
            return mapper.readValue(new File(path), RegistrationRequest.class);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать JSON файл: " + path, e);
        }
    }
}

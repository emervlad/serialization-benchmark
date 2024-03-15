package ru.sberbank.jsonparser;

import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonWorker {
    public static ClientTypes identifyType(String fileName) {
        JsonElement element = getValueAsJsonObject(fileName);
        return convertingToEnum(element);
    }

    private static JsonElement getValueAsJsonObject(String fileName) {
        JsonObject obj;
        try {
            obj = JsonParser.parseString(new String
                    (Files.readAllBytes(Paths.get(fileName)))).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException("Can't reading from file or it doesn't exist", e);
        }
        return obj.get("clientType");
    }

    private static ClientTypes convertingToEnum(JsonElement element) {
        String clientType;
        try {
            clientType = element.getAsString();
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Field clientType is incorrect or missing");
        }
        ClientTypes enumClientType;
        try {
            enumClientType = ClientTypes.valueOf(clientType);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Value is null or doesn't fit any enum-value");
        }
        return enumClientType;
    }
}

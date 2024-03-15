package ru.sberbank.jsonparser;

import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Example {

    public static ClientTypes findWord (String fileName) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(new String
                (Files.readAllBytes(Paths.get(fileName)))).getAsJsonObject();
        String clientType = obj.get("clientType").getAsString();
        return ClientTypes.valueOf(clientType);
    }
}


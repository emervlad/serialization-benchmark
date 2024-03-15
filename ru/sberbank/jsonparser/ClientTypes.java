package ru.sberbank.jsonparser;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public enum ClientTypes {
    INDIVIDUAL {
        public Individual createClient(String fileName) {
            return (Individual) generalCreation(fileName, Individual.class);
        }
    },
    LEGAL_ENTITY{
        public LegalEntity createClient(String fileName) {
            return (LegalEntity) generalCreation(fileName, LegalEntity.class);
        }
    },
    HOLDING{
        public Holding createClient(String fileName) {
            return (Holding) generalCreation(fileName, Holding.class);
        }
    };

    public abstract Client createClient(String fileName);

    private static Client generalCreation(String fileName, Class<? extends Client> clazz) {
        String json;
        try {
            json = new String
                    (Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException("Can't reading from file or it doesn't exist", e);
        }
        return new Gson().fromJson(json, clazz);
    }
}

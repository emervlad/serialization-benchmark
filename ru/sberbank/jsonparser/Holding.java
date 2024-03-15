package ru.sberbank.jsonparser;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Holding extends Client{
    private final int subsidiaryCount;

    public Holding(@JsonProperty("name") String name,
                   @JsonProperty("inn") int inn,
                   @JsonProperty("subsidiaryCount") int subsidiaryCount) {
        super(name, inn);
        this.subsidiaryCount = subsidiaryCount;
    }

    public int getSubsidiaryCount() {
        return subsidiaryCount;
    }
}

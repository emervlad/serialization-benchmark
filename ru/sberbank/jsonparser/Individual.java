package ru.sberbank.jsonparser;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Individual extends Client{
    private final String industry;
    private final Owner owner;

    public Individual(@JsonProperty("name") String name,
                      @JsonProperty("name") int inn,
                      @JsonProperty("name") String industry,
                      @JsonProperty("owner") Owner owner) {
        super(name, inn);
        this.industry = industry;
        this.owner = owner;
    }

    public String getIndustry() {
        return industry;
    }

    public Owner getOwner() {
        return owner;
    }
}

package ru.sberbank.jsonparser;

public class LegalEntity extends Client{
    private final boolean isSanctioned;

    public LegalEntity(String name, int inn, boolean isSanctioned) {
        super(name, inn);
        this.isSanctioned = isSanctioned;
    }

    public boolean isSanctioned() {
        return isSanctioned;
    }
}

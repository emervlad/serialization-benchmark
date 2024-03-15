package ru.sberbank.jsonparser;

import java.util.Objects;

public class Client {
    private final String name;
    private final int inn;

    public Client(String name, int inn) {
        this.name = name;
        this.inn = inn;
    }

    public String getName() {
        return name;
    }

    public int getInn() {
        return inn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Client client = (Client) o;
        return inn == client.inn && name.equals(client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, inn);
    }
}

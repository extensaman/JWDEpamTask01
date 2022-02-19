package com.epam.jwd.task01.entity;

import java.util.Objects;

/**
 * A simplified abstraction of a mobile company client that includes properties such as
 * - account
 * - name
 * - tariff
 */

public class Client {
    public static final String DEFAULT_CLIENT_ACCOUNT = "Virtual";
    public static final String DEFAULT_CLIENT_NAME = "Unnamed";

    private final String account;
    private final String name;
    private Tariff tariff;

    public Client() {
        this.account = DEFAULT_CLIENT_ACCOUNT;
        this.name = DEFAULT_CLIENT_NAME;
        this.tariff = new Tariff();
    }

    public Client(String account, String name) {
        this.account = account;
        this.name = name;
        this.tariff = new Tariff();
    }

    public Client(String account, String name, Tariff tariff) {
        this.account = account;
        this.name = name;
        this.tariff = tariff;
    }

    public String getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        Client client = (Client) that;
        return Objects.equals(account, client.account) &&
                Objects.equals(name, client.name) &&
                Objects.equals(tariff, client.tariff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, name, tariff);
    }

    @Override
    public String toString() {
        return "Client{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", tariff=" + tariff +
                '}';
    }
}

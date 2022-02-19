package com.epam.jwd.task01.service.clientcreator;

import com.epam.jwd.task01.dao.tariffdao.TariffDAOImpl;
import com.epam.jwd.task01.entity.Client;
import com.epam.jwd.task01.entity.Tariff;

import java.util.Optional;

/**
 * Сlass for creating objects class Client
 */

public class ClientCreator {
    private static final ClientCreator INSTANCE = new ClientCreator();
    public static final int INIT_AMOUNT = 0;
    private long amount = INIT_AMOUNT;

    private ClientCreator() {
    }

    public static ClientCreator getInstance() {
        return INSTANCE;
    }

    public long getAmount() {
        return amount;
    }

    public Client create(String account, String name, String tariffId) {
        Optional<Tariff> tariff;
        amount++;
        try {
            tariff = TariffDAOImpl.getInstance().getById(tariffId);
        } catch (CloneNotSupportedException e) {
            return new Client(account, name);
        }
        return tariff.map(value ->
                new Client(account, name, value)).orElseGet(() ->
                new Client(account, name));
    }
}

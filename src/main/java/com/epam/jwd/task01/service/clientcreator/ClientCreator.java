package com.epam.jwd.task01.service.clientcreator;

import com.epam.jwd.task01.dao.tariffdao.TariffDAOImpl;
import com.epam.jwd.task01.entity.Client;
import com.epam.jwd.task01.entity.Tariff;

import java.util.Optional;

/**
 * Сlass for creating objects class Client
 */

public class ClientCreator {
    public static final int INIT_AMOUNT = 0;
    private static final ClientCreator INSTANCE = new ClientCreator();
    private long amount = INIT_AMOUNT;

    private ClientCreator() {
    }

    /**
     * Method that provides one single existing instance of the class ClientCreator
     *
     * @return one single existing instance of the class ClientCreator
     */
    public static ClientCreator getInstance() {
        return INSTANCE;
    }

    /**
     * Method that returns the count of created objects of class Client
     *
     * @return the count of created objects of class Client
     */
    public long getAmount() {
        return amount;
    }

    /**
     * Method for creating objects of class Client
     *
     * @param account  identifier for the client
     * @param name     client's name
     * @param tariffId identifier of tariff in TariffDAO-layer
     * @return in case of existence tariff with identifier <i>tariffId</i> in TariffDAO-layer
     * returns a new client using <i>account</i>, <i>name</i> and clone of corresponding tariff,
     * in other case returns a new client using <i>account</i>, <i>name</i> and default tariff
     */
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

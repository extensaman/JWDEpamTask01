package com.epam.jwd.task01.logic;

import com.epam.jwd.task01.dao.clientdao.ClientDAO;
import com.epam.jwd.task01.entity.Client;

import java.util.Collection;

/**
 * Сlass contains functionality for working with client objects
 * using data from the DAO layer
 */

public class ClientLogic {
    private final ClientDAO dao;

    public ClientLogic(ClientDAO dao) {
        this.dao = dao;
    }

    /**
     * Method for generating list of all clients contained in ClientDAO-layer
     *
     * @return  the list of all clients contained in ClientDAO-layer
     */
    public Collection<Client> getAllClient() {
        return dao.find();
    }

    /**
     * Method that returns amount of clients contained in ClientDAO-layer
     *
     * @return amount of clients contained in ClientDAO-layer
     */
    public int getClientAmount() {
        return dao.getClientAmount();
    }
}

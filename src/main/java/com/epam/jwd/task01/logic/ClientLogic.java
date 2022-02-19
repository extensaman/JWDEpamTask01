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

    public Collection<Client> getAllClient() {
        return dao.find();
    }

    public int getClientAmount() {
        return dao.getClientAmount();
    }
}

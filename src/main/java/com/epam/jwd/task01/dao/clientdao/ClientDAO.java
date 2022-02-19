package com.epam.jwd.task01.dao.clientdao;

import com.epam.jwd.task01.entity.Client;

import java.util.Collection;

/**
 * Interface that defines methods for working with the client database
 */

public interface ClientDAO {
    void load(String filePath);

    Collection<Client> find();

    int getClientAmount();

    void insert(Collection<Client> clients);

    void deleteAll();
}

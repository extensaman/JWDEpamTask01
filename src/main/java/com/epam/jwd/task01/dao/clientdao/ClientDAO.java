package com.epam.jwd.task01.dao.clientdao;

import com.epam.jwd.task01.entity.Client;

import java.util.Collection;

/**
 * Interface that defines methods for working with the client database
 */

public interface ClientDAO {
    /**
     * Method for loading data about existing clients from a file into a storage in DAO
     *
     * @param filePath path to a file containing information about existing clients
     */
    void load(String filePath);

    /**
     * Method that returns a list of all clients stored in DAO-storage
     *
     * @return list of all clients stored in DAO-storage
     */
    Collection<Client> find();

    /**
     * Method returns amount of clients stored in DAO-storage
     *
     * @return amount of clients stored in DAO-storage
     */
    int getClientAmount();

    /**
     * Add client's list to DAO-storage
     *
     * @param clients - clients's list for adding to DAO-storage
     */
    void insert(Collection<Client> clients);

    /**
     * Method for delete all clients from DAO-storage
     */
    void deleteAll();
}

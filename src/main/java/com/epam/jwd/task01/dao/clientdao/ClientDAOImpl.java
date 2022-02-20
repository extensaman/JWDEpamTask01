package com.epam.jwd.task01.dao.clientdao;

import com.epam.jwd.task01.dao.fileparser.clientfilereader.ClientFileReader;
import com.epam.jwd.task01.entity.Client;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Class that implements ClientDAO interface
 * and serves as a layer between the Client database and the program logic
 */

public class ClientDAOImpl implements ClientDAO {
    public static final long AUTO_ID_INIT = 1L;
    private static final ClientDAOImpl INSTANCE = new ClientDAOImpl();
    private final HashMap<String, Client> clientsHashMap = new HashMap<>();
    private long nextAutoId = AUTO_ID_INIT;

    private ClientDAOImpl() {
    }

    /**
     * Method that provides one single existing instance of the class ClientDAOImpl
     *
     * @return one single existing instance of the class ClientDAOImpl
     */
    public static ClientDAOImpl getInstance() {
        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(String filePath) {
        ClientFileReader reader = new ClientFileReader(filePath);
        List<Client> list = reader.readClientList();
        deleteAll();
        insert(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Client> find() {
        return clientsHashMap.values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getClientAmount() {
        return clientsHashMap.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(Collection<Client> clients) {
        clients.forEach(client ->
                clientsHashMap.put(Long.toString(nextAutoId++), client));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        clientsHashMap.clear();
    }
}

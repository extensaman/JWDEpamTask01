package com.epam.jwd.task01.dao.fileparser.clientfilereader;

import com.epam.jwd.task01.entity.Client;
import com.epam.jwd.task01.service.clientcreator.ClientCreator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сlass for reading the list of clients from a file
 */

public class ClientFileReader {
    public static final String DEFAULT_SEPARATOR = ",";
    public static final int ACCOUNT_FILE_FIELD = 0;
    public static final int NAME_FILE_FIELD = 1;
    public static final int TARIFF_ID_FILE_FIELD = 2;
    public static final int DEFAULT_CLIENT_FIELDS_COUNT = 3;
    public static final String CLIENT_FIELDS_COUNT_NOT_VALID =
            "'Client' fields count is not valid";

    private final File file;

    public ClientFileReader(String fileName) {
        this.file = new File(fileName);
    }

    public List<Client> readClientList() {
        List<Client> clients = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            clients = reader.lines()
                    .map(this::parseLineToClient).collect(Collectors.toList());
        } catch (IOException e) {
            throw new ClientFileReaderException(e);
        }
        return clients;
    }

    private Client parseLineToClient(String line) {
        String separator = DEFAULT_SEPARATOR;
        String[] clientFields = line.split(separator);
        if (clientFields.length != DEFAULT_CLIENT_FIELDS_COUNT) {
            throw new ClientFileReaderException(CLIENT_FIELDS_COUNT_NOT_VALID);
        }
        String account = clientFields[ACCOUNT_FILE_FIELD].trim();
        String name = clientFields[NAME_FILE_FIELD].trim();
        String tariffId = clientFields[TARIFF_ID_FILE_FIELD].trim();

        return ClientCreator.getInstance().create(account, name, tariffId);
    }


}

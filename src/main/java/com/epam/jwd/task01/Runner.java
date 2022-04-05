package com.epam.jwd.task01;

import com.epam.jwd.task01.dao.clientdao.ClientDAO;
import com.epam.jwd.task01.dao.clientdao.ClientDAOImpl;
import com.epam.jwd.task01.dao.fileparser.clientfilereader.ClientFileReaderException;
import com.epam.jwd.task01.dao.fileparser.tarifffilereader.TariffFileReaderException;
import com.epam.jwd.task01.dao.tariffdao.TariffDAO;
import com.epam.jwd.task01.dao.tariffdao.TariffDAOImpl;
import com.epam.jwd.task01.logic.ClientLogic;
import com.epam.jwd.task01.logic.TariffLogic;
import com.epam.jwd.task01.service.tariffcreator.TariffCreatorException;
import com.epam.jwd.task01.ui.ConsoleIO;
import com.epam.jwd.task01.ui.UI;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * It's necessary to write an object-oriented application according to the requirements given below.
 * The application must implement the functionality defined by the individual task.
 * <p>
 * Requirements
 * - When implementing the inheritance hierarchy, the derived class must extend the superclass with new properties,
 * for which it is necessary to understand the subject area of ​​the problem.
 * - Entity classes should not be overloaded (excessive) with methods
 * that perform functional actions (business logic methods such as search, etc.).
 * - For entity classes, you should (when necessary) override the methods
 * of the Object class: toString(), equals(), hashCode().
 * - To sort objects, use the implementation of the Comparator interface.
 * - When solving a problem, you can use patterns Creator, Builder, Factory Method.
 * - All application classes must be strutted by packages.
 * - When handling exceptions, the application must use a logger.
 * - The design of the code must comply with the Java Code Convention.
 * - The parameters required to create objects must be entered by the user.
 * User input can be organized as reading data from a file (.txt, .xml, .json),
 * or using "hardcode".
 * - Do not implement a custom menu for the application.
 * <p>
 * Mobile connection.
 * Determine the tariff hierarchy of the mobile company.
 * Create a list of company tariffs.
 * Calculate the total number of clients.
 * Sort tariffs based on the size of first connection fee.
 * Find the tariff that corresponds to the specified range of parameters.
 *
 * @author Aliaksandr Yusikau
 * @since 1.0
 */

public class Runner {

    public static final String TARIFF_FILE_PATH = "src/main/resources/tariffDB.txt";
    public static final String CLIENT_FILE_PATH = "src/main/resources/clientDB.txt";
    public static final String LOGGER_NAME = "Logger";
    private static final Logger LOGGER = Logger.getLogger(LOGGER_NAME);

    public static void main(String[] args) {
        TariffDAO tariffDAO = TariffDAOImpl.getInstance();
        try {
            tariffDAO.load(TARIFF_FILE_PATH);
        } catch (TariffFileReaderException |
                TariffCreatorException |
                IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return;
        }

        ClientDAO clientDAO = ClientDAOImpl.getInstance();
        try {
            clientDAO.load(CLIENT_FILE_PATH);
        } catch (ClientFileReaderException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return;
        }

        TariffLogic tariffLogic = new TariffLogic(tariffDAO);
        ClientLogic clientLogic = new ClientLogic(clientDAO);
        UI ui = new UI(ConsoleIO.getInstance(), tariffLogic, clientLogic);
        ui.start();
    }
}

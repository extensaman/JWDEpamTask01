package com.epam.jwd.task01.ui;

import com.epam.jwd.task01.logic.ClientLogic;
import com.epam.jwd.task01.logic.TariffLogic;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Сlass contains functionality for data exchange between user and program logic
 */

public class UI {
    public static final BigDecimal PERIODICAL_FEE_LIMIT = BigDecimal.valueOf(30.0);
    public static final String ALL_TARIFFS_SAMPLE_TEXT = "All tariffs are\n";
    public static final String PERIODICAL_FEE_SORTED_SAMPLE_TEXT =
            "Tariffs that sorted by periodical fee are\n";
    public static final String CUSTOM_SAMPLE_TEXT =
            "Tariffs with Internet, periodicalFee less than " +
                    PERIODICAL_FEE_LIMIT + " and sorted by 'firstConnectionFee' are\n";
    public static final String TO_NEW_LINE = "\n";
    public static final String COUNT_OF_CLIENTS_IS = "Count of clients is ";
    public static final String ALL_CLIENTS_ARE = "\nAll clients are ...\n";

    private final UserInterface io;
    private final TariffLogic tariffLogic;
    private final ClientLogic clientLogic;

    public UI(UserInterface io, TariffLogic tariffLogic, ClientLogic clientLogic) {
        this.io = io;
        this.tariffLogic = tariffLogic;
        this.clientLogic = clientLogic;
    }

    /**
     * Method for displaying the results of program execution,
     * i.e. information required in accordance with the task
     */
    public void start() {
        viewAllTariff();
        viewAllClient();
        viewTariffSortedByPeriodicalFee();
        viewTariffWithInternetAndPeriodicalFeeLessThanNumber(PERIODICAL_FEE_LIMIT);
    }

    /**
     * Method to display all available mobile tariffs
     */
    public void viewAllTariff() {
        io.output(ALL_TARIFFS_SAMPLE_TEXT);
        viewCollection(tariffLogic.getAllTariff());
    }

    /**
     * Method to display all mobile connected clients
     */
    public void viewAllClient() {
        io.output(COUNT_OF_CLIENTS_IS + clientLogic.getClientAmount());
        io.output(ALL_CLIENTS_ARE);
        viewCollection(clientLogic.getAllClient());
    }

    /**
     * Method for displaying information about tariff plans sorted by first connection fee
     */
    public void viewTariffSortedByPeriodicalFee() {
        io.output(PERIODICAL_FEE_SORTED_SAMPLE_TEXT);
        viewCollection(tariffLogic.getTariffSortedByPeriodicalFee());
    }

    /**
     * Method for displaying information about tariff plans with Internet traffic,
     * periodical payment less than <i>periodicalFeeHighLimit</i>
     * and also sorted by connection fee value
     *
     * @param periodicalFeeHighLimit high bound for periodical fee
     */
    public void viewTariffWithInternetAndPeriodicalFeeLessThanNumber(BigDecimal periodicalFeeHighLimit) {
        io.output(CUSTOM_SAMPLE_TEXT);
        viewCollection(
                tariffLogic.getTariffWithInternetAndPeriodicalFeeLessThanNumber(periodicalFeeHighLimit));
    }

    /**
     * Method for displaying the elements of the collection.
     * After displaying the last element
     * in addition, a newline character is displayed
     *
     * @param collection collection for displaying
     */
    private void viewCollection(Collection<?> collection) {
        for (Object o : collection) {
            io.output(o.toString() + TO_NEW_LINE);
        }
        io.output(TO_NEW_LINE);
    }
}

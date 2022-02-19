package com.epam.jwd.task01.ui;

import com.epam.jwd.task01.logic.ClientLogic;
import com.epam.jwd.task01.logic.TariffLogic;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Сlass contains functionality for data exchange between user and program logic
 */

public class UI {
    public static final double PERIODICAL_FEE_LIMIT = 30.0;
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

    public void start() {
        viewAllTariff();
        viewAllClient();
        viewTariffSortedByPeriodicalFee();
        viewTariffWithInternetAndPeriodicalFeeLessThanNumber();
    }

    public void viewAllTariff() {
        io.output(ALL_TARIFFS_SAMPLE_TEXT);
        viewCollection(tariffLogic.getAllTariff());
    }

    public void viewAllClient() {
        io.output(COUNT_OF_CLIENTS_IS + clientLogic.getClientAmount());
        io.output(ALL_CLIENTS_ARE);
        viewCollection(clientLogic.getAllClient());
    }

    public void viewTariffSortedByPeriodicalFee() {
        io.output(PERIODICAL_FEE_SORTED_SAMPLE_TEXT);
        viewCollection(tariffLogic.getTariffSortedByPeriodicalFee());
    }

    public void viewTariffWithInternetAndPeriodicalFeeLessThanNumber() {
        io.output(CUSTOM_SAMPLE_TEXT);
        viewCollection(
                tariffLogic.getTariffWithInternetAndPeriodicalFeeLessThanNumber(
                        BigDecimal.valueOf(PERIODICAL_FEE_LIMIT)));
    }

    private void viewCollection(Collection<?> collection) {
        for (Object o : collection) {
            io.output(o.toString() + TO_NEW_LINE);
        }
        io.output(TO_NEW_LINE);
    }
}

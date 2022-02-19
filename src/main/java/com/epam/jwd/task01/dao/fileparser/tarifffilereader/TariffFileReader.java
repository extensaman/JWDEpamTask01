package com.epam.jwd.task01.dao.fileparser.tarifffilereader;

import com.epam.jwd.task01.entity.Tariff;
import com.epam.jwd.task01.entity.TariffTypes;
import com.epam.jwd.task01.service.tariffcreator.TariffCreator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сlass for reading the list of tariffs from a file
 */

public class TariffFileReader {
    public static final String DEFAULT_SEPARATOR = ",";
    public static final int DEFAULT_TARIFF_FIELDS_COUNT = 7;
    public static final int CORPORATE_TARIFF_FIELDS_COUNT = 8;
    public static final int TARIFF_CODE_FILE_FIELD = 0;
    public static final int TARIFF_NAME_FILE_FIELD = 1;
    public static final int FIRST_CONNECTION_FEE_FILE_FIELD = 2;
    public static final int PERIODICAL_FEE_FILE_FIELD = 3;
    public static final int MINUTE_AMOUNT_FILE_FIELD = 4;
    public static final int SMS_AMOUNT_FILE_FIELD = 5;
    public static final int INTERNET_TRAFFIC_AMOUNT_FILE_FIELD = 6;
    public static final int DEFAULT_ABONENT_AMOUNT_LOW_BOUND_FILE_FIELD = 7;
    public static final String DEFAULT_TARIFF_FIELDS_COUNT_NOT_VALID =
            "'Tariff' fields count is not valid";
    public static final String CORPORATE_TARIFF_FIELDS_COUNT_NOT_VALID =
            "'CorporateTariff' fields count is not valid";
    public static final String TARIFF_FIELDS_IN_FILE_NOT_VALID =
            "'Tariff' fields in file aren't valid";
    public static final String CORPORATE_TARIFF_FIELDS_IN_FILE_NOT_VALID =
            "'CorporateTariff' fields in file aren't valid";
    public static final String UNACCEPTABLE_TARIFF_TYPE = "Unacceptable tariff type";

    private final File file;

    public TariffFileReader(String fileName) {
        this.file = new File(fileName);
    }

    public List<Tariff> readTariffList() {
        List<Tariff> tariffs = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            tariffs = reader.lines()
                    .map(this::parseLineToTariff).collect(Collectors.toList());
        } catch (IOException e) {
            throw new TariffFileReaderException(e);
        }
        return tariffs;
    }

    private Tariff parseLineToTariff(String line) {
        String separator = DEFAULT_SEPARATOR;
        String[] tariffFields = line.split(separator);
        TariffTypes type = TariffTypes.valueOf(tariffFields[TARIFF_CODE_FILE_FIELD].trim());

        switch (type) {
            case BASE:
                if (tariffFields.length != DEFAULT_TARIFF_FIELDS_COUNT) {
                    throw new TariffFileReaderException(DEFAULT_TARIFF_FIELDS_COUNT_NOT_VALID);
                }
                Object[] baseParams = new Object[DEFAULT_TARIFF_FIELDS_COUNT - 1];
                parseTariffFields(tariffFields, baseParams);
                return TariffCreator.getInstance().create(TariffTypes.BASE, baseParams);
            case CORPORATE:
                if (tariffFields.length != CORPORATE_TARIFF_FIELDS_COUNT) {
                    throw new TariffFileReaderException(CORPORATE_TARIFF_FIELDS_COUNT_NOT_VALID);
                }
                Object[] corporateParams = new Object[CORPORATE_TARIFF_FIELDS_COUNT - 1];
                parseTariffFields(tariffFields, corporateParams);
                try {
                    corporateParams[DEFAULT_ABONENT_AMOUNT_LOW_BOUND_FILE_FIELD - 1] =
                            Integer.parseInt(tariffFields[DEFAULT_ABONENT_AMOUNT_LOW_BOUND_FILE_FIELD].trim());
                } catch (NumberFormatException e) {
                    throw new TariffFileReaderException(CORPORATE_TARIFF_FIELDS_IN_FILE_NOT_VALID, e);
                }
                return TariffCreator.getInstance().create(TariffTypes.CORPORATE, corporateParams);
            default:
                throw new TariffFileReaderException(UNACCEPTABLE_TARIFF_TYPE);
        }
    }

    private void parseTariffFields(String[] tariffFields, Object[] params) {
        params[TARIFF_NAME_FILE_FIELD - 1] = tariffFields[TARIFF_NAME_FILE_FIELD].trim();

        try {
            params[FIRST_CONNECTION_FEE_FILE_FIELD - 1] = new BigDecimal(
                    tariffFields[FIRST_CONNECTION_FEE_FILE_FIELD].trim());

            params[PERIODICAL_FEE_FILE_FIELD - 1] = new BigDecimal(
                    tariffFields[PERIODICAL_FEE_FILE_FIELD].trim());

            params[MINUTE_AMOUNT_FILE_FIELD - 1] = Integer.parseInt(
                    tariffFields[MINUTE_AMOUNT_FILE_FIELD].trim());

            params[SMS_AMOUNT_FILE_FIELD - 1] = Integer.parseInt(
                    tariffFields[SMS_AMOUNT_FILE_FIELD].trim());

            params[INTERNET_TRAFFIC_AMOUNT_FILE_FIELD - 1] = Integer.parseInt(
                    tariffFields[INTERNET_TRAFFIC_AMOUNT_FILE_FIELD].trim());

        } catch (NumberFormatException e) {
            throw new TariffFileReaderException(TARIFF_FIELDS_IN_FILE_NOT_VALID, e);
        }
    }

}

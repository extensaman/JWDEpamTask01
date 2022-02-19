package com.epam.jwd.task01.service.tariffcreator;

import com.epam.jwd.task01.entity.CorporateTariff;
import com.epam.jwd.task01.entity.Tariff;
import com.epam.jwd.task01.entity.TariffTypes;

import java.math.BigDecimal;

/**
 * Сlass for creating objects class Tariff
 */

public class TariffCreator {
    private static final TariffCreator INSTANCE = new TariffCreator();
    public static final int NAME_FIELD = 0;
    public static final int FIRST_CONNECTION_FEE_FIELD = 1;
    public static final int DEFAULT_PERIODICAL_FEE_FIELD = 2;
    public static final int DEFAULT_MINUTE_AMOUNT_FIELD = 3;
    public static final int DEFAULT_SMS_AMOUNT_FIELD = 4;
    public static final int DEFAULT_INTERNET_TRAFFIC_AMOUNT_FIELD = 5;
    public static final int DEFAULT_ABONENT_AMOUNT_LOW_BOUND_FIELD = 6;
    public static final int INIT_AMOUNT = 0;
    public static final String PARAMETERS_LESS_THAN_FIELD_COUNT_IN_CLASS_MOBILE_TARIFF =
            "Parameters less than field's count in class 'CorporateTariff'";
    public static final String PARAMETERS_LESS_THAN_FIELD_COUNT_IN_CLASS_TARIFF =
            "Parameters less than field's count in class 'Tariff'";
    public static final String PARAMETERS_FOR_CREATING_CORPORATE_TARIFF_OBJECT_NOT_VALID =
            "Parameters for creating 'CorporateTariff' object aren't valid";
    public static final String PARAMETERS_FOR_CREATING_TARIFF_OBJECT_NOT_VALID =
            "Parameters for creating 'Tariff' object aren't valid";
    public static final int CORPORATE_TARIFF_PARAMS_COUNT = 7;
    public static final int BASE_TARIFF_PARAMS_COUNT = 6;
    public static final String UNACCEPTABLE_TARIFF_TYPE = "Unacceptable tariff type";
    private long amount = INIT_AMOUNT;

    private TariffCreator() {
    }

    public static TariffCreator getInstance() {
        return INSTANCE;
    }

    public long getAmount() {
        return amount;
    }

    public Tariff create(TariffTypes type, Object... params) {
        switch (type) {
            case BASE:
                if (params.length < BASE_TARIFF_PARAMS_COUNT) {
                    throw new TariffCreatorException(PARAMETERS_LESS_THAN_FIELD_COUNT_IN_CLASS_TARIFF);
                }
                TariffValidator validator = new TariffValidator(type, params);
                if (validator.check()) {
                    amount++;
                    return new Tariff((String) params[NAME_FIELD],
                            ((BigDecimal) params[FIRST_CONNECTION_FEE_FIELD]),
                            ((BigDecimal) params[DEFAULT_PERIODICAL_FEE_FIELD]),
                            (Integer) params[DEFAULT_MINUTE_AMOUNT_FIELD],
                            (Integer) params[DEFAULT_SMS_AMOUNT_FIELD],
                            (Integer) params[DEFAULT_INTERNET_TRAFFIC_AMOUNT_FIELD]);
                } else {
                    throw new TariffCreatorException(PARAMETERS_FOR_CREATING_TARIFF_OBJECT_NOT_VALID);
                }
            case CORPORATE:
                if (params.length < CORPORATE_TARIFF_PARAMS_COUNT) {
                    throw new TariffCreatorException(PARAMETERS_LESS_THAN_FIELD_COUNT_IN_CLASS_MOBILE_TARIFF);
                }
                TariffValidator mobileValidator = new TariffValidator(type, params);
                if (mobileValidator.check()) {
                    amount++;
                    return new CorporateTariff((String) params[NAME_FIELD],
                            ((BigDecimal) params[FIRST_CONNECTION_FEE_FIELD]),
                            ((BigDecimal) params[DEFAULT_PERIODICAL_FEE_FIELD]),
                            (Integer) params[DEFAULT_MINUTE_AMOUNT_FIELD],
                            (Integer) params[DEFAULT_SMS_AMOUNT_FIELD],
                            (Integer) params[DEFAULT_INTERNET_TRAFFIC_AMOUNT_FIELD],
                            (Integer) params[DEFAULT_ABONENT_AMOUNT_LOW_BOUND_FIELD]);
                } else {
                    throw new TariffCreatorException(PARAMETERS_FOR_CREATING_CORPORATE_TARIFF_OBJECT_NOT_VALID);
                }
            default:
                throw new TariffCreatorException(UNACCEPTABLE_TARIFF_TYPE);
        }
    }
}

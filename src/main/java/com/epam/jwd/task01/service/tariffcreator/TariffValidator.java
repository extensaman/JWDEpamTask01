package com.epam.jwd.task01.service.tariffcreator;

import com.epam.jwd.task01.entity.TariffTypes;

import java.math.BigDecimal;

/**
 * Сlass for checking the possibility of creating an object Tariff class with specified fields
 */

public class TariffValidator {
    private final TariffTypes type;
    private final Object[] params;

    public TariffValidator(TariffTypes type, Object[] params) {
        this.type = type;
        this.params = params;
    }

    public boolean check() {
        boolean result = ((BigDecimal) params[TariffCreator.FIRST_CONNECTION_FEE_FIELD])
                .compareTo(BigDecimal.ZERO) >= 0 &&
                ((BigDecimal) params[TariffCreator.DEFAULT_PERIODICAL_FEE_FIELD])
                        .compareTo(BigDecimal.ZERO) >= 0 &&
                (Integer) params[TariffCreator.DEFAULT_MINUTE_AMOUNT_FIELD] >= 0 &&
                (Integer) params[TariffCreator.DEFAULT_SMS_AMOUNT_FIELD] >= 0 &&
                (Integer) params[TariffCreator.DEFAULT_INTERNET_TRAFFIC_AMOUNT_FIELD] >= 0;
        ;
        switch (type) {
            case BASE:
                return result;
            case CORPORATE:
                return result &&
                        (Integer) params[TariffCreator.DEFAULT_ABONENT_AMOUNT_LOW_BOUND_FIELD] >= 0;
            default:
                throw new TariffCreatorException(TariffCreator.UNACCEPTABLE_TARIFF_TYPE);
        }

    }
}

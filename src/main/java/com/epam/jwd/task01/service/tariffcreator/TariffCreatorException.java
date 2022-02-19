package com.epam.jwd.task01.service.tariffcreator;

/**
 * Сlass that describes the exceptions that may occur in the class TariffCreator
 */

public class TariffCreatorException extends RuntimeException {

    public TariffCreatorException() {
    }

    public TariffCreatorException(String message) {
        super(message);
    }

    public TariffCreatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public TariffCreatorException(Throwable cause) {
        super(cause);
    }
}

package com.epam.jwd.task01.dao.fileparser.tarifffilereader;

/**
 * Сlass that describes the exceptions that may occur in the class TariffFileReader
 */

public class TariffFileReaderException extends RuntimeException {
    public TariffFileReaderException() {
    }

    public TariffFileReaderException(String message) {
        super(message);
    }

    public TariffFileReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public TariffFileReaderException(Throwable cause) {
        super(cause);
    }
}

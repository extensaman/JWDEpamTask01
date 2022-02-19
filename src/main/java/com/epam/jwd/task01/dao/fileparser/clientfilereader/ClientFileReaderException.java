package com.epam.jwd.task01.dao.fileparser.clientfilereader;

/**
 * Сlass that describes the exceptions that may occur in the class TariffFileReader
 */

public class ClientFileReaderException extends RuntimeException {
    public ClientFileReaderException() {
    }

    public ClientFileReaderException(String message) {
        super(message);
    }

    public ClientFileReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientFileReaderException(Throwable cause) {
        super(cause);
    }
}

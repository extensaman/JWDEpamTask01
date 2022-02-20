package com.epam.jwd.task01.dao.fileparser.clientfilereader;

/**
 * Сlass that describes the exceptions that may occur in the class ClientFileReader
 */

public class ClientFileReaderException extends RuntimeException {
    /**
     * Constructs a new exception with {@code null} as its
     * detail message. The cause is not initialized.
     */
    public ClientFileReaderException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     * The cause is not initialized.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ClientFileReaderException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    public ClientFileReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of
     * {@code cause}).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A {@code null} value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     */
    public ClientFileReaderException(Throwable cause) {
        super(cause);
    }
}

package proyecto_banco.controllers;

/**
 * Códigos de respuesta.
 */
public enum CODES {
    USER_ALREADY_EXISTS(0),
    USER_EXISTS(1),
    USER_DOES_NOT_EXIST(-1),
    TRANSACTION_BALANCE_NEGATIVE(-1),
    TRANSACTION_BALANCE_INSUFFICIENT(0),
    TRANSACTION_BALANCE_SUCCESSFUL(1),
    USER_DELETED_SUCCESSFULLY(1),
    USER_CREATED_SUCCESSFULLY(1),
    USER_LOGGED_IN_SUCCESSFULLY(1);

    private final int value;

    CODES(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

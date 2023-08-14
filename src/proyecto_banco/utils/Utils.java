package proyecto_banco.utils;

/**
 * Clase de utilidades.
 */
public class Utils {

    /**
     * Generates a random number between 10000000 and 99999999
     *
     * @return a random number.
     */
    public static int generateRandomNumber() {
        return (int) (Math.random() * (99999999 - 10000000 + 1) + 10000000);
    }
}

package proyecto_banco.controllers;

import proyecto_banco.models.User;
import proyecto_banco.utils.Utils;

import java.util.List;

import static proyecto_banco.controllers.UserRepository.USERS;

/**
 * Controlador de usuarios del banco.
 */
public class UserController {

    /**
     * Obtiene la lista de usuarios.
     *
     * @return {@link List<User> Lista} de {@link User usuarios}
     */
    public List<User> getUsers() {
        return USERS;
    }

    /**
     * Obtiene un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario
     * @return {@link User Usuario} o null si no existe.
     */
    public User getUserByUsername(String username) {
        for (User user : USERS) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id ID del usuario
     * @return {@link User Usuario} o null si no existe.
     */
    public User getUserById(int id) {
        for (User user : USERS) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    /**
     * Verifica si un usuario existe por su nombre de usuario.
     *
     * @param username Nombre de usuario
     * @return {@link CODES Código} de respuesta.
     */
    public CODES userExistsByUsername(String username) {
        return (getUserByUsername(username) != null) ? CODES.USER_EXISTS : CODES.USER_DOES_NOT_EXIST;
    }

    /**
     * Verifica si un usuario existe por su ID.
     *
     * @param id ID del usuario
     * @return {@link CODES Código} de respuesta.
     */
    public CODES userExistsById(int id) {
        return (getUserById(id) != null) ? CODES.USER_EXISTS : CODES.USER_DOES_NOT_EXIST;
    }

    /**
     * Crea un usuario.
     *
     * @param name     Nombre completo del usuario
     * @param username Nombre de usuario
     * @param password Contraseña
     * @return {@link CODES Código} de respuesta.
     */
    public CODES createUser(String name, String username, String password) {
        int generatedId = Utils.generateRandomNumber();
        User user = new User(generatedId, name, username, password, 0.0);

        if (userExistsByUsername(username) == CODES.USER_EXISTS) {
            return CODES.USER_ALREADY_EXISTS;
        }

        USERS.add(user);
        return CODES.USER_CREATED_SUCCESSFULLY;
    }

    /**
     * Actualiza el balance de un usuario.
     *
     * @param sender   Id del usuario que envía el dinero
     * @param receiver Id del usuario que recibe el dinero
     * @param amount   Cantidad de dinero a transferir
     * @return {@link CODES Código} de respuesta.
     */
    public CODES updateBalanceTransfer(int sender, int receiver, double amount) {
        User senderUser = getUserById(sender);
        User receiverUser = getUserById(receiver);

        if (senderUser == null || receiverUser == null) {
            return CODES.USER_DOES_NOT_EXIST;
        }

        if (amount < 0) {
            return CODES.TRANSACTION_BALANCE_NEGATIVE;
        }

        return transferMoney(senderUser, receiverUser, amount);
    }

    /**
     * Transfiere dinero de un usuario a otro.
     * @param sender Usuario que envía el dinero
     * @param receiver Usuario que recibe el dinero
     * @param amount Cantidad de dinero a transferir
     * @return {@link CODES Código} de respuesta.
     */
    private CODES transferMoney(User sender, User receiver, double amount) {
        if (sender.getBalance() < amount) {
            return CODES.TRANSACTION_BALANCE_INSUFFICIENT;
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        return CODES.TRANSACTION_BALANCE_SUCCESSFUL;
    }

    /**
     * Actualiza el balance de un usuario al depositar dinero.
     * @param id Id del usuario
     * @param amount Cantidad de dinero a depositar
     * @return {@link CODES Código} de respuesta.
     */
    public CODES updateBalanceDeposit(int id, double amount) {
        User user = getUserById(id);

        if (user == null) {
            return CODES.USER_DOES_NOT_EXIST;
        }

        if (amount < 0) {
            return CODES.TRANSACTION_BALANCE_NEGATIVE;
        }

        user.setBalance(user.getBalance() + amount);
        return CODES.TRANSACTION_BALANCE_SUCCESSFUL;
    }

    /**
     * Actualiza el balance de un usuario al retirar dinero.
     * @param id Id del usuario
     * @param amount Cantidad de dinero a retirar
     * @return {@link CODES Código} de respuesta.
     */
    public CODES updateBalanceWithdraw(int id, double amount) {
        User user = getUserById(id);

        if (user == null) {
            return CODES.USER_DOES_NOT_EXIST;
        }

        if (amount < 0) {
            return CODES.TRANSACTION_BALANCE_NEGATIVE;
        }

        if (user.getBalance() < amount) {
            return CODES.TRANSACTION_BALANCE_INSUFFICIENT;
        }

        user.setBalance(user.getBalance() - amount);
        return CODES.TRANSACTION_BALANCE_SUCCESSFUL;
    }

    /**
     * Elimina un usuario.
     *
     * @param id Id del usuario
     * @return {@link CODES Código} de respuesta.
     */
    public CODES deleteUser(int id) {
        User user = getUserById(id);

        if (user == null) {
            return CODES.USER_DOES_NOT_EXIST;
        }

        USERS.remove(user);
        return CODES.USER_DELETED_SUCCESSFULLY;
    }

    /**
     * Inicia sesión de un usuario.
     * @param username Nombre de usuario
     * @param password Contraseña
     * @return {@link CODES Código} de respuesta.
     */
    public CODES login(String username, String password) {
        var user = getUserByUsername(username);

        if (user == null) {
            return CODES.USER_DOES_NOT_EXIST;
        }

        if (!user.getPassword().equals(password)) {
            return CODES.USER_DOES_NOT_EXIST;
        }

        return CODES.USER_LOGGED_IN_SUCCESSFULLY;
    }
}


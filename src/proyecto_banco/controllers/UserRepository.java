package proyecto_banco.controllers;

import proyecto_banco.models.User;

import java.util.ArrayList;

/**
 * Repositorio de {@link User usuarios}.
 *
 * @see User Usuario
 */
public class UserRepository {

    /**
     * Lista de usuarios.
     */
    protected static final ArrayList<User> USERS = new ArrayList<>();
}

package proyecto_banco.ui;

import proyecto_banco.controllers.CODES;
import proyecto_banco.controllers.UserController;

import javax.swing.*;
import java.util.List;
import java.util.Arrays;

public class Start {

    private final UserController userController = new UserController();

    public Start() {
        startProgram();
    }

    private void startProgram() {
        while (true) {
            int optionSelected = showMainMenu();

            switch (optionSelected) {
                case 0 -> createUser();
                case 1 -> loginUser();
                default -> {
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    System.exit(0);
                }
            }
        }
    }

    /**
     * Muestra el menú principal.
     * @return Opción seleccionada.
     */
    private int showMainMenu() {
        List<String> options = Arrays.asList("Registrarse", "Inicio de sesión", "Salir");

        return JOptionPane.showOptionDialog(
                null,
                "Favor, seleccione una opción:",
                "Menu Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options.toArray(),
                options.get(0)
        );
    }

    /**
     * Crea un usuario.
     */
    private void createUser() {
        String name = JOptionPane.showInputDialog("Ingrese su nombre completo");
        if (name == null) {
            return;
        }

        String username = JOptionPane.showInputDialog("Cree un nombre de usuario: ");
        if (username == null) {
            return;
        }

        String password = PasswordDialog.showPasswordDialog();
        if (password == null) {
            return;
        }

        CODES response = userController.createUser(name, username, password);

        if (response == CODES.USER_CREATED_SUCCESSFULLY) {
            JOptionPane.showMessageDialog(null, "Usuario creado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo crear el usuario.");
        }
    }

    /**
     * Inicia sesión.
     */
    private void loginUser() {
        String username = JOptionPane.showInputDialog("Ingrese su nombre de usuario: ");
        if (username == null) {
            return;
        }

        String password = PasswordDialog.showPasswordDialog();
        if (password == null) {
            return;
        }

        CODES response = userController.login(username, password);

        if (response == CODES.USER_DOES_NOT_EXIST) {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
        }

        if (response == CODES.USER_LOGGED_IN_SUCCESSFULLY) {
            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
            new Dashboard(userController.getUserByUsername(username));
        }
    }
}

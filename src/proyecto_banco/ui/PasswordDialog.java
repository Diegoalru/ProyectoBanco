package proyecto_banco.ui;

import javax.swing.*;
import java.awt.*;

public class PasswordDialog {

    public static String showPasswordDialog() {
        while (true) {
            JPasswordField passwordField = new JPasswordField();
            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Cree su Contraseña:"));
            panel.add(new JLabel("La contraseña debe tener al menos 8 caracteres"));
            panel.add(new JLabel("No se permiten caracteres iguales consecutivos"));
            panel.add(passwordField);

            // Set focus on password field
            passwordField.requestFocusInWindow();

            int option = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Ingrese su contraseña",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (option == JOptionPane.OK_OPTION) {
                String password = new String(passwordField.getPassword());
                if (isValidPassword(password)) {
                    return password;
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "La contraseña debe tener al menos 8 caracteres y no puede tener caracteres consecutivos repetidos.",
                            "Contraseña inválida",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                return null;
            }
        }
    }

    /**
     * Válida que la contraseña cumpla con los requisitos:
     * - Tener al menos 8 caracteres
     * - No tener caracteres consecutivos repetidos
     *
     * @param password Contraseña a validar
     * @return true si la contraseña es válida, false en caso contrario
     */
    private static boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        for (int i = 0; i < password.length() - 1; i++) {
            if (password.charAt(i) == password.charAt(i + 1)) {
                return false;
            }
        }

        return true;
    }
}

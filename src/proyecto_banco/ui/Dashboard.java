package proyecto_banco.ui;

import proyecto_banco.controllers.CODES;
import proyecto_banco.controllers.UserController;
import proyecto_banco.models.User;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * Clase que representa el menú principal de la aplicación.
 */
public class Dashboard {

    private final UserController userController; // Controlador de usuarios para realizar las operaciones de la cuenta.
    private final User user; // Usuario que inició sesión.

    public Dashboard(User user) {
        this.user = user;
        this.userController = new UserController();
        showMenu();
    }

    /**
     * Muestra el menú de trámites.
     */
    public void showMenu() {
        List<String> options = Arrays.asList(
                "Realizar una transferencia",
                "Realizar un depósito",
                "Realizar un retiro",
                "Ver cuenta",
                "Cerrar sesión"
        );

        while (true) {
            var option = JOptionPane.showInputDialog(
                    null,
                    "Seleccione una opción",
                    "Menu de trámites",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options.toArray(),
                    options.get(0)
            );

            try {
                switch (option.toString()) {
                    case "Realizar una transferencia" -> transfer();
                    case "Realizar un depósito" -> deposit();
                    case "Realizar un retiro" -> withdraw();
                    case "Ver cuenta" -> checkBalance();
                    case "Cerrar sesión" -> {
                        JOptionPane.showMessageDialog(null, "Cerrando Sesión");
                        return;
                    }
                }
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Cerrando Sesión");
                return;
            }
        }
    }

    /**
     * Realiza una transferencia de la cuenta del usuario a otra cuenta.
     */
    private void transfer() {
        try {
            int destinationAccountNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el número de cuenta de la persona a transferir:"));
            double transferAmount = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a transferir:"));

            CODES response = userController.updateBalanceTransfer(user.getId(), destinationAccountNumber, transferAmount);

            handleTransactionResponse(response, "Transferencia");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida. Por favor, ingrese valores numéricos.");
        }
    }

    /**
     * Realiza un depósito en la cuenta del usuario.
     */
    private void deposit() {
        try {
            double depositAmount = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a depositar:"));

            CODES response = userController.updateBalanceDeposit(user.getId(), depositAmount);

            handleTransactionResponse(response, "Depósito");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida. Por favor, ingrese un valor numérico.");
        }
    }

    /**
     * Realiza un retiro en la cuenta del usuario.
     */
    private void withdraw() {
        try {
            double withdrawalAmount = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a retirar:"));

            CODES response = userController.updateBalanceWithdraw(user.getId(), withdrawalAmount);

            handleTransactionResponse(response, "Retiro");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida. Por favor, ingrese un valor numérico.");
        }
    }

    /**
     * Muestra el saldo de la cuenta del usuario.
     */
    private void checkBalance() {
        User userById = userController.getUserById(user.getId());
        JOptionPane.showMessageDialog(null, "Identificación de la cuenta: " + userById.getId() + "\nTitular: " + userById.getName() + "\n" + "Saldo: ¢ " + userById.getBalance());
    }

    /**
     * Maneja la respuesta de las transacciones.
     * @param response Código de respuesta.
     * @param action Acción realizada.
     */
    private void handleTransactionResponse(CODES response, String action) {
        switch (response) {
            case TRANSACTION_BALANCE_SUCCESSFUL ->
                    JOptionPane.showMessageDialog(null, action + " realizada con éxito.");
            case TRANSACTION_BALANCE_NEGATIVE -> JOptionPane.showMessageDialog(null, "El monto debe ser positivo.");
            case TRANSACTION_BALANCE_INSUFFICIENT -> JOptionPane.showMessageDialog(null, "Saldo insuficiente.");
            case USER_DOES_NOT_EXIST -> JOptionPane.showMessageDialog(null, "El usuario no existe.");
        }
    }
}

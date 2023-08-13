package proyecto_banco;

import javax.swing.*;

/**
 * @author Grupo 7
 */
public class Principal {
    public static void main(String[] args) {
        String[] arreglo = {"Registrarse", "Inicio de sesión", "Salir"};
        String[] arreglo2 = {"Si", "No"};
        int opcion;
        int opcion2;
        Usuario u1 = new Usuario();

        do {
            opcion = JOptionPane.showOptionDialog(null,
                    "Favor, seleccione una opción:", "Menu Principal",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, arreglo, arreglo[0]);


            switch (opcion) {
                case 0 -> {
                    u1.setNombreCompleto(JOptionPane.showInputDialog("Ingrese su nombre completo"));
                    u1.setUsuario(JOptionPane.showInputDialog("Cree un nombre de usuario: "));
                    u1.setContraseña();
                    System.out.println(u1.getUsuario());
                    System.out.println(u1.getContraseña());
                    for (int i = 0; i < 3; i++) {
                        JOptionPane.showMessageDialog(null, "Verificación de la creación del usuario.");
                        String usuario = JOptionPane.showInputDialog("Nombre de usuario: ");
                        JPasswordField Censura = new JPasswordField();
                        int option = JOptionPane.showConfirmDialog(null, Censura, "Contraseña:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        char[] ContaseñaChars = Censura.getPassword();
                        String Contraseña = new String(ContaseñaChars);

                        if (usuario.equals(u1.getUsuario()) && Contraseña.equals(u1.getContraseña())) {
                            JOptionPane.showMessageDialog(null, "¡Usuario creado exitosamente!");
                            u1.setNumeroDeCuenta();
                            opcion2 = JOptionPane.showOptionDialog(null,
                                    "Desea realizar su primer deposito:", "Menu Principal",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                                    null, arreglo2, arreglo2[0]);
                            switch (opcion2) {
                                case 0 ->
                                        u1.setSaldoUsuario(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el saldo inicial de la cuenta: ")));
                                case 1 -> {
                                    u1.setSaldoUsuario(0);
                                    JOptionPane.showMessageDialog(null, "Podrá realizar un deposito en el Menu de trámites ");
                                }
                            }
                            break;
                        } else if (i < 2) {
                            JOptionPane.showMessageDialog(null, "Usuario rechazado, intenta nuevamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "Usuario bloqueado, vuelve a intentar más tarde");
                        }
                    }
                }
                case 1 -> {
                    String nombreUsuarioIngresado = JOptionPane.showInputDialog(null, "Favor, digite el nombre de usuario:");
                    JPasswordField Censura = new JPasswordField();
                    int option = JOptionPane.showConfirmDialog(null, Censura, "Contraseña:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    char[] ContaseñaChars = Censura.getPassword();
                    String contraseñaIngresada = new String(ContaseñaChars);
                    Login user = new Login(nombreUsuarioIngresado, contraseñaIngresada);
                    if (u1.getUsuario().equals(user.getNombreUsuario()) && u1.getContraseña().equals(user.getContrasena())) {
                        Funcionalidades cuenta = new Funcionalidades(u1);
                        JOptionPane.showMessageDialog(null, "¡Acceso aprobado! ¡Bienvenido, " + u1.getNombreCompleto() + "!");
                        String Menu = "";
                        while (!Menu.equals("Salir")) {
                            Object[] options = {"Selecciona", "Realizar una transferencia", "Realizar un depósito", "Realizar un retiro", "Ver saldo", "Salir"};
                            Menu = (String) JOptionPane.showInputDialog(null, "Seleccione una opción", "Menu de trámites",
                                    JOptionPane.PLAIN_MESSAGE, null, options, "Selecciona");

                            switch (Menu) {
                                case "Realizar una transferencia" -> {
                                    int numeroDeCuentaDestino = Integer.parseInt(JOptionPane.showInputDialog(null, "digite el número de cuenta de la persona a transferir:"));
                                    double montoTransferencia = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a transferir:"));
                                    cuenta.Transferencias(numeroDeCuentaDestino, montoTransferencia);
                                }
                                case "Realizar un depósito" -> {
                                    double montoDeposito = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a depositar:"));
                                    cuenta.depositar(montoDeposito);
                                }
                                case "Realizar un retiro" -> {
                                    double montoRetiro = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a retirar:"));
                                    cuenta.retirar(montoRetiro);
                                }
                                case "Ver saldo" -> cuenta.VerSaldo();
                                case "Salir" -> JOptionPane.showMessageDialog(null, "Saliendo...");
                                default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "¡Acceso denegado. Nombre de usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                case 2 -> JOptionPane.showMessageDialog(null, "Saliendo...");
                default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (opcion != 2);
    }
}

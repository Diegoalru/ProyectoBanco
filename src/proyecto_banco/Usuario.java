package proyecto_banco;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Usuario {
    private String NombreCompleto;
    private String usuario;
    private String contraseña;
    private double saldoUsuario;
    private int NumeroDeCuenta;

    public Usuario() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {

        return contraseña;
    }

    public void setContraseña() {
        boolean contraseñaValida = false;

        while (!contraseñaValida) {
            JPasswordField passwordField = new JPasswordField();
            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Cree su Contraseña:"));
            panel.add(new JLabel("La contraseña debe tener al menos 8 caracteres"));
            panel.add(new JLabel("No se permiten caracteres iguales consecutivos"));
            panel.add(passwordField);
            JOptionPane.showMessageDialog(null, panel);

            this.contraseña = new String(passwordField.getPassword());

            if (this.contraseña.length() < 8) {
                JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 8 caracteres");
            } else {
                boolean caracteresIgualesConsecutivos = false;

                for (int i = 0; i < this.contraseña.length() - 1; ++i) {
                    if (this.contraseña.charAt(i) == this.contraseña.charAt(i + 1)) {
                        caracteresIgualesConsecutivos = true;
                        break;
                    }
                }

                if (caracteresIgualesConsecutivos) {
                    JOptionPane.showMessageDialog(null, "No se permiten caracteres iguales consecutivos");
                } else {
                    contraseñaValida = true;
                }
            }
        }
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String NombreCompleto) {
        this.NombreCompleto = NombreCompleto;
    }

    public double getSaldoUsuario() {
        return saldoUsuario;
    }

    public void setSaldoUsuario(double saldoUsuario) {
        this.saldoUsuario = saldoUsuario;
    }

    public int getNumeroDeCuenta() {
        return NumeroDeCuenta;
    }

    public void setNumeroDeCuenta() {
        Random random = new Random();
        int min = 10000000;
        int max = 99999999;
        int numeroDeCuentaGenerado = random.nextInt(max - min + 1) + min;
        JOptionPane.showMessageDialog(null, "Su número de cuenta es: " + numeroDeCuentaGenerado);
        this.NumeroDeCuenta = numeroDeCuentaGenerado;
    }
}

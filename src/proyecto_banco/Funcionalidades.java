package proyecto_banco;

import javax.swing.*;

/**
 * @author Grupo 7
 */
public class Funcionalidades {
    private final Usuario usuario;
    private final String titular;
    private final int NumeroDeCuenta;
    private final double getSaldo;

    public Funcionalidades(Usuario usuario) {
        this.usuario = usuario;
        this.titular = usuario.getNombreCompleto();
        this.NumeroDeCuenta = usuario.getNumeroDeCuenta();
        this.getSaldo = usuario.getSaldoUsuario();
    }

    public void depositar(double monto) {
        if (monto > 0) {
            usuario.setSaldoUsuario(getSaldo + monto);
            JOptionPane.showMessageDialog(null, "Depósito de ₡" + monto + " realizado correctamente.");
            JOptionPane.showMessageDialog(null, "Numero de cuenta: " + NumeroDeCuenta
                    + "\nDueño de la cuenta " + titular
                    + "\nSaldo actual: " + getSaldo);
        } else {
            JOptionPane.showMessageDialog(null, "El monto del depósito debe ser mayor que cero.");
        }
    }

    public void retirar(double monto) {
        if (monto > 0 && monto <= usuario.getSaldoUsuario()) {
            usuario.setSaldoUsuario(getSaldo - monto);
            JOptionPane.showMessageDialog(null, "Retiro de ₡" + monto + " realizado correctamente.");
            JOptionPane.showMessageDialog(null, "Numero de cuenta: " + NumeroDeCuenta
                    + "\nDueño de la cuenta " + titular
                    + "\nSaldo actual: " + getSaldo);
        } else {
            JOptionPane.showMessageDialog(null, "Monto inválido o insuficiente para el retiro.");
        }
    }

    public void Transferencias(int CuentaDestino, double monto) {
        if (monto > 0 && monto <= getSaldo) {
            usuario.setSaldoUsuario(getSaldo - monto);
            JOptionPane.showMessageDialog(null, "Traferencia de: ₡" + monto + " \n realizada correctamente a " + CuentaDestino);
        } else {
            JOptionPane.showMessageDialog(null, "Monto inválido o insuficiente para la traferencia");
        }
    }

    public void VerSaldo() {
        JOptionPane.showMessageDialog(null, "Numero de cuenta: " + NumeroDeCuenta
                + "\nDueño de la cuenta " + titular
                + "\nSaldo actual: " + getSaldo);
    }
}


package Exercicio6;

public class Prato {

    private final int numero;

    public Prato(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "Prato " + numero;
    }
}
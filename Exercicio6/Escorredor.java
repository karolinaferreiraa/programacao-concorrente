package Exercicio6;

public class Escorredor {

    private final Prato[] pratos;
    private int quantidade;
    private boolean fechado;

    public Escorredor(int capacidade) {
        this.pratos = new Prato[capacidade];
    }

    public synchronized void colocar(Prato prato) throws InterruptedException {
        while (quantidade == pratos.length) {
            wait();
        }

        pratos[quantidade] = prato;
        quantidade++;
        notifyAll();
    }

    public synchronized Prato retirar() throws InterruptedException {
        while (quantidade == 0 && !fechado) {
            wait();
        }

        if (quantidade == 0) {
            return null;
        }

        quantidade--;
        Prato prato = pratos[quantidade];
        pratos[quantidade] = null;
        notifyAll();
        return prato;
    }

    public synchronized void fechar() {
        fechado = true;
        notifyAll();
    }
}
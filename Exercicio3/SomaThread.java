package Exercicio3;
class SomaThread extends Thread {

    private short[] numeros;
    private int inicio;
    private int fim;
    private long soma = 0;

    public SomaThread(short[] numeros, int inicio, int fim) {
        this.numeros = numeros;
        this.inicio = inicio;
        this.fim = fim;
    }

    public long getSoma() {
        return soma;
    }

    @Override
    public void run() {
        for (int i = inicio; i < fim; i++) {
            soma += numeros[i];
        }
    }
}
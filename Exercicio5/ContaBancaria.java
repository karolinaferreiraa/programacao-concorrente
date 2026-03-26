package Exercicio5;
import java.util.concurrent.locks.ReentrantLock;

public class ContaBancaria {

    private final int codigo;
    private double saldoAtual;
    private final ReentrantLock trava = new ReentrantLock();

    public ContaBancaria(int codigo, double saldoInicial) {
        this.codigo = codigo;
        this.saldoAtual = saldoInicial;
    }

    public int getCodigo() {
        return codigo;
    }

    public double consultarSaldo() {
        return saldoAtual;
    }

    public void creditar(double valor) {
        saldoAtual += valor;
    }

    public void debitar(double valor) {
        saldoAtual -= valor;
    }

    public ReentrantLock getTrava() {
        return trava;
    }
}
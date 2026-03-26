package Exercicio5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class SistemaBancario {

    private final List<ContaBancaria> listaContas = new ArrayList<>();
    private final AtomicLong totalOperacoes = new AtomicLong(0);

    public SistemaBancario(int quantidadeContas, double saldoInicial) {
        for (int i = 0; i < quantidadeContas; i++) {
            listaContas.add(new ContaBancaria(i, saldoInicial));
        }
    }

    public List<ContaBancaria> getListaContas() {
        return listaContas;
    }

    public void realizarTransferencia(ContaBancaria origem, ContaBancaria destino, double valor) {

        ContaBancaria primeiro = origem.getCodigo() < destino.getCodigo() ? origem : destino;
        ContaBancaria segundo = origem.getCodigo() < destino.getCodigo() ? destino : origem;

        primeiro.getTrava().lock();
        segundo.getTrava().lock();

        try {
            if (origem.consultarSaldo() >= valor) {
                origem.debitar(valor);
                destino.creditar(valor);
                totalOperacoes.incrementAndGet();
            }
        } finally {
            segundo.getTrava().unlock();
            primeiro.getTrava().unlock();
        }
    }

    public double calcularSaldoTotal() {
        double total = 0;

        for (ContaBancaria conta : listaContas) {
            conta.getTrava().lock();
        }

        try {
            for (ContaBancaria conta : listaContas) {
                total += conta.consultarSaldo();
            }
        } finally {
            for (ContaBancaria conta : listaContas) {
                conta.getTrava().unlock();
            }
        }

        return total;
    }

    public long getTotalOperacoes() {
        return totalOperacoes.get();
    }
}

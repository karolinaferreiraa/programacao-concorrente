package Exercicio5;

import java.util.List;
import java.util.Random;

public class SimuladorTransferencias implements Runnable {

    private final SistemaBancario banco;
    private final Random aleatorio = new Random();
    private volatile boolean ativo = true;

    public SimuladorTransferencias(SistemaBancario banco) {
        this.banco = banco;
    }

    public void encerrar() {
        ativo = false;
    }

    @Override
    public void run() {
        List<ContaBancaria> contas = banco.getListaContas();

        while (ativo) {
            int origemIndex = aleatorio.nextInt(contas.size());
            int destinoIndex = aleatorio.nextInt(contas.size());

            if (origemIndex == destinoIndex) continue;

            ContaBancaria origem = contas.get(origemIndex);
            ContaBancaria destino = contas.get(destinoIndex);

            double valor = aleatorio.nextDouble() * 2500;

            banco.realizarTransferencia(origem, destino, valor);
        }
    }
}
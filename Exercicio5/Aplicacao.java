package Exercicio5;

import java.util.ArrayList;
import java.util.List;

public class Aplicacao {

    public static void main(String[] args) throws InterruptedException {

        final int TOTAL_CONTAS = 100;
        final double SALDO_INICIAL = 1000.0;
        final int TOTAL_THREADS = 5;
        final int TEMPO_EXECUCAO = 3 * 60 * 1000;

        SistemaBancario banco = new SistemaBancario(TOTAL_CONTAS, SALDO_INICIAL);

        List<SimuladorTransferencias> tarefas = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < TOTAL_THREADS; i++) {
            SimuladorTransferencias tarefa = new SimuladorTransferencias(banco);
            Thread thread = new Thread(tarefa);

            tarefas.add(tarefa);
            threads.add(thread);

            thread.start();
        }

        MonitorSaldo monitor = new MonitorSaldo(banco);
        Thread threadMonitor = new Thread(monitor);
        threadMonitor.start();

        // 3 mi
        Thread.sleep(TEMPO_EXECUCAO);

      
        tarefas.forEach(SimuladorTransferencias::encerrar);
        monitor.encerrar();

        for (Thread t : threads) {
            t.join();
        }

        threadMonitor.interrupt();

        
        double saldoFinal = banco.calcularSaldoTotal();
        long operacoes = banco.getTotalOperacoes();

        System.out.println("\n=== RELATÓRIO FINAL ===");
        System.out.println("Saldo final: R$ " + String.format("%.2f", saldoFinal));
        System.out.println("Total de operações: " + operacoes);

        if (Math.abs(saldoFinal - 100000.0) < 0.01) {
            System.out.println("✔ Integridade mantida!");
        } else {
            System.out.println("❌ Inconsistência detectada!");
        }
    }
}
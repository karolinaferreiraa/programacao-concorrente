package Exercicio5;

public class MonitorSaldo implements Runnable {

    private final SistemaBancario banco;
    private volatile boolean ativo = true;

    public MonitorSaldo(SistemaBancario banco) {
        this.banco = banco;
    }

    public void encerrar() {
        ativo = false;
    }

    @Override
    public void run() {
        while (ativo) {
            try {
                Thread.sleep(5000);

                double total = banco.calcularSaldoTotal();

                System.out.println("Saldo total no sistema: R$ " 
                        + String.format("%.2f", total));

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
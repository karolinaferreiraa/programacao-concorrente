package Exercicio6;

public class Exercicio06 {

    private static final int TOTAL_PRATOS = 100;
    private static final int CAPACIDADE_ESCORREDOR = 10;
    private static final int QUANTIDADE_SECADORES = 2;

    public static void main(String[] args) throws InterruptedException {
        Escorredor escorredor = new Escorredor(CAPACIDADE_ESCORREDOR);
        PilhaPratos pilhaPratosLimpos = new PilhaPratos(TOTAL_PRATOS);

        Thread lavador = new Thread(() -> {
            for (int i = 1; i <= TOTAL_PRATOS; i++) {
                try {
                    Prato prato = new Prato(i);
                    escorredor.colocar(prato);
                    System.out.println("Lavador lavou " + prato);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            escorredor.fechar();
        }, "Lavador");

        Thread[] secadores = new Thread[QUANTIDADE_SECADORES];

        for (int i = 0; i < QUANTIDADE_SECADORES; i++) {
            final int numeroSecador = i + 1;
            secadores[i] = new Thread(() -> {
                while (true) {
                    try {
                        Prato prato = escorredor.retirar();

                        if (prato == null) {
                            return;
                        }

                        pilhaPratosLimpos.empilhar(prato);
                        System.out.println("Secador " + numeroSecador + " secou " + prato);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }, "Secador-" + numeroSecador);
        }

        lavador.start();

        for (Thread secador : secadores) {
            secador.start();
        }

        lavador.join();

        for (Thread secador : secadores) {
            secador.join();
        }

        System.out.println("\nTotal de pratos limpos: " + pilhaPratosLimpos.getQuantidade());
        System.out.println(pilhaPratosLimpos);
    }
}
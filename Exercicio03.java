import java.util.Random;

public class Exercicio03 {

    public static void main(String[] args) throws InterruptedException {

        short[] numeros = new short[1000000000];
        Random random = new Random();

        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = (short) random.nextInt(Short.MAX_VALUE);
        }

        long inicio = System.currentTimeMillis();

        long soma = 0;
        for (int i = 0; i < numeros.length; i++) {
            soma += numeros[i];
        }

        long tempo = System.currentTimeMillis() - inicio;

        System.out.println("\nsoma sequencial: " + soma);
        System.out.println("tempo: " + tempo + " ms");

        inicio = System.currentTimeMillis();

        int numThreads = 10;
        SomaThread[] threads = new SomaThread[numThreads];
        int faixa = numeros.length / numThreads;

        int inicioFaixa = 0;

        for (int i = 0; i < numThreads; i++) {

            int fimFaixa = inicioFaixa + faixa;

            threads[i] = new SomaThread(numeros, inicioFaixa, fimFaixa);
            threads[i].setName("Thread-" + i);
            threads[i].start();

            inicioFaixa = fimFaixa;
        }

        soma = 0;

        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
            soma += threads[i].getSoma();
        }

        tempo = System.currentTimeMillis() - inicio;

        System.out.println("\nSoma com 10 Threads: " + soma);
        System.out.println("Tempo: " + tempo + " ms");

        inicio = System.currentTimeMillis();

        numThreads = 100;
        threads = new SomaThread[numThreads];
        faixa = numeros.length / numThreads;

        inicioFaixa = 0;

        for (int i = 0; i < numThreads; i++) {

            int fimFaixa;

            if (i == numThreads - 1) {
                fimFaixa = numeros.length;
            } else {
                fimFaixa = inicioFaixa + faixa;
            }

            threads[i] = new SomaThread(numeros, inicioFaixa, fimFaixa);
            threads[i].setName("Thread-" + i);
            threads[i].start();

            inicioFaixa = fimFaixa;
        }

        soma = 0;

        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
            soma += threads[i].getSoma();
        }

        tempo = System.currentTimeMillis() - inicio;

        System.out.println("\nSoma com 100 Threads: " + soma);
        System.out.println("Tempo: " + tempo + " ms");

        inicio = System.currentTimeMillis();

        numThreads = 10;
        Thread[] virtuais = new Thread[numThreads];
        SomaThread[] tarefas = new SomaThread[numThreads];
        faixa = numeros.length / numThreads;

        inicioFaixa = 0;

        for (int i = 0; i < numThreads; i++) {

            int fimFaixa = inicioFaixa + faixa;

            tarefas[i] = new SomaThread(numeros, inicioFaixa, fimFaixa);
            virtuais[i] = Thread.ofVirtual().start(tarefas[i]);

            inicioFaixa = fimFaixa;
        }

        soma = 0;

        for (int i = 0; i < numThreads; i++) {
            virtuais[i].join();
            soma += tarefas[i].getSoma();
        }

        tempo = System.currentTimeMillis() - inicio;

        System.out.println("\nSoma com 10 Threads Virtuais: " + soma);
        System.out.println("Tempo: " + tempo + " ms");

        inicio = System.currentTimeMillis();

        numThreads = 100;
        virtuais = new Thread[numThreads];
        tarefas = new SomaThread[numThreads];
        faixa = numeros.length / numThreads;

        inicioFaixa = 0;

        for (int i = 0; i < numThreads; i++) {

            int fimFaixa;

            if (i == numThreads - 1) {
                fimFaixa = numeros.length;
            } else {
                fimFaixa = inicioFaixa + faixa;
            }

            tarefas[i] = new SomaThread(numeros, inicioFaixa, fimFaixa);
            virtuais[i] = Thread.ofVirtual().start(tarefas[i]);

            inicioFaixa = fimFaixa;
        }

        soma = 0;

        for (int i = 0; i < numThreads; i++) {
            virtuais[i].join();
            soma += tarefas[i].getSoma();
        }

        tempo = System.currentTimeMillis() - inicio;

        System.out.println("\nSoma com 100 Threads Virtuais: " + soma);
        System.out.println("Tempo: " + tempo + " ms");
    }
}
package Exercicio4;

public class Exercicio4 {

    public static void main(String[] args) {

        ListaThread lista = new ListaThread();

        new Thread(() -> {
            while (true) {
                lista.adicionar((int) (Math.random() * 100));
                sleep(500);
            }
        }, "Add-1").start();

        new Thread(() -> {
            while (true) {
                lista.adicionar((int) (Math.random() * 100));
                sleep(500);
            }
        }, "Add-2").start();

        new Thread(() -> {
            while (true) {
                lista.remover();
                sleep(700);
            }
        }).start();

        new Thread(() -> {
            while (true) {
                lista.imprimir();
                sleep(1000);
            }
        }).start();
    }
    private static void sleep(long tempo) {
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
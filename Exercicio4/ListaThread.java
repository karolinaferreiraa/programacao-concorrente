package Exercicio4;
import java.util.ArrayList;
import java.util.List;

class ListaThread {

    private final List<Integer> lista = new ArrayList<>();

    public synchronized void adicionar(int valor) {
        lista.add(valor); 
    }

    public synchronized void remover() {
        if (!lista.isEmpty()) {
            lista.remove(0);
        }
    }

    public synchronized void imprimir() {
        System.out.println(lista);
    }
}
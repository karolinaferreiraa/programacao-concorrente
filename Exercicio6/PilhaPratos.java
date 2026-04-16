// Em uma cozinha, existe uma pilha de 100 pratos sujos.
// Para tal, temos uma pessoa lavando pratos e duas secando os pratos. 
// Entre eles, existe um escorredor com espaço para apenas dez pratos. 
// Faça um programa que simule a lavação dos 100 pratos sujos, sua colocação no escorredor e a secagem destes. 
// Após enxugar cada prato, ele será colocado na pilha de pratos limpos.
package Exercicio6;

public class PilhaPratos {

    private final Prato pratos[];
    private int quantidade;
    private boolean fechada;

    public PilhaPratos(int tamanho) {
        pratos = new Prato[tamanho];
    }

    public synchronized void empilhar(Prato prato) throws InterruptedException {
        while (quantidade == pratos.length) {
            wait();
        }

        pratos[quantidade] = prato;
        quantidade++;
        notifyAll();
    }

    public synchronized Prato desempilhar() throws InterruptedException {
        while (quantidade == 0 && !fechada) {
            wait();
        }

        if (quantidade == 0 && fechada) {
            return null;
        }

        quantidade--;
        Prato prato = pratos[quantidade];
        pratos[quantidade] = null;
        notifyAll();
        return prato;
    }

    public synchronized void fechar() {
        fechada = true;
        notifyAll();
    }

    public synchronized int getQuantidade() {
        return quantidade;
    }

    public int getTamanho() {
        return pratos.length;
    }

    @Override
    public synchronized String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Pilha de Pratos: [");
        for (int i = 0; i < quantidade; i++) {
            sb.append(pratos[i].toString());
            if (i < quantidade - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }





    
}

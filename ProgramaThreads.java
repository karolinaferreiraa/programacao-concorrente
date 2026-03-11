import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class ExibirHoraThread extends Thread {

    @Override
    public void run() {
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaSistema = LocalTime.now().format(formatoHora);

        System.out.println("Nome da Thread: " + Thread.currentThread().getName() +
                           " | Hora atual: " + horaSistema);
    }
}

public class ProgramaThreads {

    public static void main(String[] args) {

        for (int contador = 1; contador <= 10; contador++) {
            ExibirHoraThread threadAtual = new ExibirHoraThread();
            threadAtual.setName("karol-" + contador);
            threadAtual.start();
        }

    }
}

/*
Observação sobre a execução:

Ao executar o programa várias vezes, é possível perceber que a ordem das
mensagens impressas no console pode mudar. Isso acontece porque as threads
são executadas de forma concorrente. O sistema operacional, junto com a JVM,
é responsável por decidir qual thread será executada em cada momento.

Por causa disso, as threads podem se intercalar durante a execução,
fazendo com que os valores apareçam em ordens diferentes a cada execução
do programa.

Portanto, os valores não são sempre impressos na mesma ordem. A única
garantia é que cada thread exibirá o contador de 1 até 10, mas a sequência
entre as diferentes threads pode variar.
*/
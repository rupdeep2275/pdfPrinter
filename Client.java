import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Client {
    public static void main(String[] args) {
        Queue<Object> q = new LinkedList<>();
        int cap = 5;
        Semaphore proSem = new Semaphore(cap);
        Semaphore printSem = new Semaphore(0);
        //HashSet<String> ids = new HashSet<>();

        for(int i=1; i<=1000; i++){
            Processor pro = new Processor("pro" + i, proSem, printSem, q);
            Thread tForPro = new Thread(pro);

            Printer print = new Printer("print" + i, proSem, printSem, q);
            Thread tForPrint = new Thread(print);


            tForPrint.start();
            tForPro.start();
        }
    }
}

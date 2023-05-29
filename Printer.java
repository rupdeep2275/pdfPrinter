import java.time.LocalTime;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Printer implements Runnable{
    private String id;
    private Semaphore proSem;
    private Semaphore printSem;
    private Queue<Object> q;
    //HashSet<String> ids = new HashSet<>();

    public Printer(String id, Semaphore proSem, Semaphore printSem, Queue<Object> q){
        this.id = id;
        this.proSem = proSem;
        this.printSem = printSem;
        this.q = q;
    }
    @Override
    public void run() {
        try {
            printSem.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        q.remove();
        System.out.println(id + " now printed a pdf. Q size is now " + q.size() + " at time " + LocalTime.now());
        proSem.release();
    }
}

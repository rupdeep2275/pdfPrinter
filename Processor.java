import java.time.LocalTime;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Processor implements Runnable{
    private String id;
    private Semaphore proSem;
    private Semaphore printSem;
    private Queue<Object> q;
//  HashSet<String> ids = new HashSet<>();

    public Processor(String id, Semaphore proSem, Semaphore printSem, Queue<Object> q){
        this.id = id;
        this.proSem = proSem;
        this.printSem = printSem;
        this.q = q;
    }
    @Override
    public void run() {
        try {
            proSem.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        q.add(new Object());
        System.out.println(id + " processed a pdf. Queue size is now " + q.size() + " at time " + LocalTime.now());
        printSem.release();
    }
}

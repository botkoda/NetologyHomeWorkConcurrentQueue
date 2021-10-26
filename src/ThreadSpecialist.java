import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadSpecialist implements Runnable {
    private final int SLEEP_TIME = 15000;
    private String str;
    private VolatileClass v;
    private ConcurrentLinkedQueue<String> queue;

    public ThreadSpecialist(ConcurrentLinkedQueue<String> queue, VolatileClass v) {
        this.queue = queue;
        this.v = v;
    }

    @Override
    public void run() {
        try {
            while (v.volatileVal || queue.size() > 0) {
                Thread.sleep(SLEEP_TIME);
                if ((str = queue.poll()) != null) {
                    System.out.println(Thread.currentThread().getName() + ": принял " + str);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadATC implements Runnable {
    private final int SLEEP_TIME = 5000;
    private final int CALL_COUNT = 10;
    private String str = "Звонок";
    private ConcurrentLinkedQueue<String> queue;
    private VolatileClass v;

    public ThreadATC(ConcurrentLinkedQueue<String> queue, VolatileClass v) {
        this.queue = queue;
        this.v = v;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < CALL_COUNT; i++) {
                Thread.sleep(SLEEP_TIME);
                queue.add(str + " " + i);
                System.out.println(str + " " + i);
            }
            v.volatileVal = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

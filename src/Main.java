import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final int SPECIALISTS_COUNT = 10;
        final VolatileClass volatileClass = new VolatileClass();
        ExecutorService threadPool = Executors.newFixedThreadPool(SPECIALISTS_COUNT);
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        Runnable threadATC = new ThreadATC(queue, volatileClass);
        //поток АТС
        threadPool.submit(threadATC);
        //генерация потоков специалистов
        Stream.generate(() -> threadPool.submit(new ThreadSpecialist(queue, volatileClass))).limit(SPECIALISTS_COUNT).collect(Collectors.toList());
        //завершение работы
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            System.out.println("Потоки заврешили работу");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

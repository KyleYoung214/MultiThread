package atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileUnsafeTest {
    private static volatile int counter = 0;
    private static int syncCounter = 0;
    private static AtomicInteger atomicCounter = new AtomicInteger(0);

    private static Object lock = new Object();

    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        counter++;
                        atomicCounter.getAndIncrement();
                        synchronized (lock) {
                            syncCounter++;
                        }
                    }
                }
            });
            threads[i].start();
        }
        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("volatile counter: " + counter + "\nsync counter: " + syncCounter + "\natomic counter: " +
                atomicCounter.get());
    }
}

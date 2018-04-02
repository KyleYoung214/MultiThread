package atomic;

public class ThreadCountTest {
    public static void main(String[] args) {
        // 2 here, eclipse: 1
        System.out.println(Thread.activeCount());
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        Thread[] threads = new Thread[group.activeCount()];
        group.enumerate(threads);

        //Thread[main,5,main]
        //Thread[Monitor Ctrl-Break,5,main]
        for (Thread t : threads) {
            System.out.println(t.toString());
        }
    }
}

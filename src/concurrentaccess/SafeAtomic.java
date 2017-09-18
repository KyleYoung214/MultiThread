package concurrentaccess;

import java.util.concurrent.atomic.AtomicLong;

// volatile 只能保证可见性，不保证原子性
// 方法和方法之间不是原子的，必须要同步来解决
public class SafeAtomic {
    static class MyService {
        public static AtomicLong aiRef = new AtomicLong();

        // need to add synchronized
        public synchronized void addNum() {
            System.out.println(Thread.currentThread().getName() + ", add 100 is: " + aiRef.addAndGet(100));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            aiRef.addAndGet(1);
        }
    }

    static class MyThread extends Thread {
        private MyService myService;

        public MyThread(MyService myService) {
            this.myService = myService;
        }

        @Override
        public void run() {
            super.run();
            myService.addNum();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        MyThread[] myThreads = new MyThread[5];
        for (int i = 0; i < 5; i++) {
            myThreads[i] = new MyThread(myService);
        }

        for (MyThread myThread : myThreads) {
            myThread.start();
        }

        Thread.sleep(500);

        System.out.println(myService.aiRef.get());
    }


}

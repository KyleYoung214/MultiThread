package concurrentaccess;

public class NoAtomicTest {
    static class MyService {
        public static Long value = 0L;

        // need to add synchronized
        public synchronized void addNum() {
            //synchronized (value) {
            value += 100;
            //}
            System.out.println(Thread.currentThread().getName() + ", add 100 is: " + value);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //synchronized (value) {
            value += 1;
            //}
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

        System.out.println(myService.value);
    }


}

package communication;

public class JoinTest {

    public static void main(String[] args) {
        try {
            MyThread myThread = new MyThread();
            myThread.start();
            myThread.join();
            System.out.println("main finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
                System.out.println("my thread finish");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

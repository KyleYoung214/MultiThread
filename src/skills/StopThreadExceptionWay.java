package skills;

public class StopThreadExceptionWay {
    public static void main(String[] args) {
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end");
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 500000; i++) {
                    if (this.isInterrupted()) {
                        System.out.println("interrupted, gonna quit");
                        // if use break; the out of for loop will also be reached
                        // use return is also ok, but not suggested
                        throw new InterruptedException();// we can catch the exception in upper code
                    }
                    System.out.println("i = " + i);
                }

                System.out.println("out of for loop");
            } catch (InterruptedException e) {
                System.out.println("into catch block");
                e.printStackTrace();
            }
        }
    }
}

package skills;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    private static int counter = 0;

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test1() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                counter++;
                System.out.println(counter + " start " + new Date().toLocaleString());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(counter + " end   " + new Date().toLocaleString());
                if (counter == 10) {
                    timer.cancel();
                }
            }
        }, 0, 3000);
    }

    private static void test2() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                counter++;
                System.out.println(counter + " start " + new Date().toLocaleString());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(counter + " end   " + new Date().toLocaleString());
                if (counter == 10) {
                    timer.cancel();
                }
            }
        }, 0, 3000);
    }

}

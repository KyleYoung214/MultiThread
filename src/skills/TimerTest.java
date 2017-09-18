package skills;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    private static Timer timer = new Timer();
    private static int counter = 0;

    public static void main(String[] args) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                counter++;
                System.out.println(counter + " start " + System.currentTimeMillis());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(counter + " end   " + System.currentTimeMillis());
                if (counter == 5) {
                    timer.cancel();
                }
            }
        }, 0, 3000);
    }
}

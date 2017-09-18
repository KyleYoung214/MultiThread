package communication;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipeTest {

    public static void main(String[] args) {
        try {
            PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream outputStream = new PipedOutputStream();

            inputStream.connect(outputStream);
//            outputStream.connect(inputStream);

            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();

            WriteThread writeThread = new WriteThread(writeData, outputStream);
            ReadThread readThread = new ReadThread(readData, inputStream);

            readThread.start();
            Thread.sleep(500);
            writeThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class WriteData {
        public void writeMethod(PipedOutputStream out) {
            try {
                System.out.println("write: ");
                for (int i = 0; i < 100; i++) {
                    String str = "" + (i + 1);
                    out.write(str.getBytes());
                    // out.flush();
                    System.out.print(str);
                }
                System.out.println();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class ReadData {
        public void readMethod(PipedInputStream in) {
            try {
                System.out.println("read: ");
                byte[] buffer = new byte[8];
                int readLen;
                while ((readLen = in.read(buffer)) != -1) {
                    String str = new String(buffer, 0, readLen);
                    System.out.print(str);
                }
                System.out.println();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class WriteThread extends Thread {
        private WriteData writeData;
        private PipedOutputStream out;

        public WriteThread(WriteData writeData, PipedOutputStream out) {
            this.writeData = writeData;
            this.out = out;
        }

        @Override
        public void run() {
            writeData.writeMethod(out);
        }
    }

    static class ReadThread extends Thread {
        private PipedInputStream in;
        private ReadData readData;

        public ReadThread(ReadData readData, PipedInputStream in) {
            this.readData = readData;
            this.in = in;
        }

        @Override
        public void run() {
            readData.readMethod(in);
        }
    }
}

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Recorder {

    public static long startTIme;

    public static List<RecorderUnit> recorder = new LinkedList();

    public synchronized static void add(long time, Position start, Position end) {
        RecorderUnit temp = new RecorderUnit(time, start, end);

        recorder.add(temp);
    }

    public static void setStartTime(long t) {startTIme = t;}

    public static void clear() {
        startTIme = 0;
        recorder.clear();
    }

    public static void record() throws Exception {
        Iterator<RecorderUnit> it = recorder.iterator();

        String pathName = System.getProperty("user.dir") + "\\log.txt";
        File f = new File(pathName);
        for (int i = 1; i < 20; i++) {
            pathName = System.getProperty("user.dir") + "\\log" + i + ".txt";
            f = new File(pathName);
            if (!f.exists()) {
                try {
                    f.createNewFile();
                } catch (Exception ie) {}
                break;
            }
        }

        try {
            long time = System.currentTimeMillis();
            byte[] bTime = new byte[8];
            for (int i = 0; i < 8; ++i) {
                int offset = 64 - (i + 1) * 8;
                bTime[i] = (byte) ((time >> offset) & 0xff);
            }
            OutputStream out = new FileOutputStream(f);
            out.write(bTime);
            out.close();
        } catch (Exception e) {
            System.out.println("Record error");
            return;
        }

//        String pathname = System.getProperty("user.dir") + "\\resources\\log.txt";
//        File f = new File(pathName);
        RandomAccessFile randomFile;
        try {
            randomFile = new RandomAccessFile(f, "rw");
        }catch (Exception e) {
            System.out.println("Record error");
            return;
        }

        while(it.hasNext()) {
            RecorderUnit temp = it.next();
            long time = temp.getTime();
            Position start = temp.getStart();
            Position end = temp.getEnd();

            byte[] bTime = new byte[8];
            for (int i = 0; i < 8; ++i) {
                int offset = 64 - (i + 1) * 8;
                bTime[i] = (byte) ((time >> offset) & 0xff);
            }
            byte[] bWrite = new byte[4];

            bWrite[0] = (byte)start.getX();
            bWrite[1] = (byte)start.getY();
            bWrite[2] = (byte)end.getX();
            bWrite[3] = (byte)end.getY();

            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.write(bTime);
            randomFile.write(bWrite);
        }

        randomFile.close();
        System.out.println("Finish recording");
    }
}

class RecorderUnit {

    private long time;

    private Position start;

    private  Position end;

    public RecorderUnit(long time, Position start, Position end) {
        this.time = time; this.start = start; this.end = end;
    }

    public long getTime() { return time; }

    public Position getStart() { return start; }

    public Position getEnd() { return end; }
}

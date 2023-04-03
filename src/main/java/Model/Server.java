package Model;

import GUI.SimulationFrame;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {

    private int number;
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private SimulationFrame sf;
    private File f;
    private FileOutputStream fos;
    private PrintWriter pw;

    public Server(BlockingQueue<Task> tasks, AtomicInteger waitingPeriod, SimulationFrame sf, File f, FileOutputStream fos, PrintWriter pw) {
        /** initialize queue and waitingPeriod*/
        this.tasks = tasks;
        this.waitingPeriod = waitingPeriod;
        this.sf = sf;
        this.f = f;
        this.fos = fos;
        this.pw = pw;
    }

    @Override
    public void run() {
        while (true) {
            /** take next task from queue
             // stop the thread for a time equal with the task's processing time
             // decrement the waitingPeriod*/
            try {
                if(tasks.peek() != null) {
                    Task t = tasks.peek();
                    System.out.println("Task sleeping: " + t);
                    pw.write("Task sleeping: " + t + "\n");
                    pw.flush();
                    Thread.sleep(t.getServiceTime() * 1000L);
                    System.out.println("Task taken: " + t);
                    pw.write("Task taken: " + t + "\n");
                    pw.flush();
                    tasks.take();
                    int decr = sf.getCnt() - t.getArrivalTime();
                    waitingPeriod.addAndGet((-1) * decr);

                    String om = t.toString();
                    System.out.println("OM DE STERS: " + om);
                    for(JTextArea jTextArea : sf.getTextAreaList()) {
                        if (jTextArea.getText().contains(om)) {
                            int index = om.length();
                            jTextArea.setText(jTextArea.getText().substring(index + 1));
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

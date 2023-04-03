package BusinessLogic;

import GUI.InputFrame;
import GUI.SimulationFrame;
import Model.Task;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class SimulationManager implements Runnable {

    // read data from user input
    public SelectionPolicy getselectionPolicyQueue = SelectionPolicy.SHORTEST_QUEUE;
    public SelectionPolicy getSelectionPolicyTime = SelectionPolicy.SHORTEST_TIME;

    // entity responsable with queue management and client distribution
    private Scheduler scheduler;

    //frame for displaying frame
    private InputFrame frame;
    private SimulationFrame sm;

    //pool of tasks (client shopping in the store)
    private List<Task> generatedTasks;
    private boolean thr = false;
    private File f;
    private FileOutputStream fos;
    private PrintWriter pw;


    public SimulationManager() throws FileNotFoundException {
        /** initialize frame to display simulation*/
        frame = new InputFrame();
        f = new File("Test.txt");
        fos = new FileOutputStream(f);
        pw = new PrintWriter(fos);

        /** generate numberOfClients clients using generateNRandomTasks() and store them to generatedTasks*/
        generateNRandomTasks(f, fos, pw);

        /** initialize the scheduler*/
        boolean ok = false;
        while (!ok) {
            if (thr) {
                scheduler = new Scheduler(Integer.parseInt(frame.getQueuess().getText()), Integer.parseInt(frame.getClientss().getText()), sm, f, fos, pw);
                scheduler.changeStrategy(getselectionPolicyQueue);
                ok = true;
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void generateNRandomTasks(File f, FileOutputStream fos, PrintWriter pw) {
        /** generate N random tasks
         // - random processing time
         // minProcessingTime < processingTime < maxProcessingTime
         // - random arrivalTime
         // sort list with respect of arrivalTime*/

        generatedTasks = new ArrayList<>();
        frame.getOk().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frame.getClientss().getText().equals("") || frame.getQueuess().getText().equals("") || frame.getMina().getText().equals("") || frame.getMaxa().getText().equals("") || frame.getMins().getText().equals("") || frame.getMaxs().getText().equals("") || frame.getSimut().getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Completati toate casutele");
                } else if (Integer.parseInt(frame.getMins().getText()) >= Integer.parseInt(frame.getMaxs().getText())) {
                    JOptionPane.showMessageDialog(null, "Dati o valoare mai mica pentru min. service decat max. service");
                } else if (Integer.parseInt(frame.getMina().getText()) >= Integer.parseInt(frame.getMaxa().getText())) {
                    JOptionPane.showMessageDialog(null, "Dati o valoare mai mica pentru min. arrival decat max. arrival");
                } else if (Integer.parseInt(frame.getSimut().getText()) < Integer.parseInt(frame.getMaxa().getText())) {
                    JOptionPane.showMessageDialog(null, "Dati o valoare mai mica pentru max. arrival decat simulation time");
                } else if ((Integer.parseInt(frame.getMaxa().getText()) + Integer.parseInt(frame.getMaxs().getText())) > Integer.parseInt(frame.getSimut().getText())){
                    JOptionPane.showMessageDialog(null, "Nu toti clientii vor ajunge sa si termine treaba");
                } else {
                    sm = new SimulationFrame(frame.getSimut().getText(), frame.getQueuess().getText());
                    pw.write("Strategy: " + getSelectionPolicyTime + "\n" + "N = " + frame.getClientss().getText() + "\n" + "Q = " + frame.getQueuess().getText() + "\n\n");
                    pw.flush();
                    frame.dispose();
                    thr = true;

                    for (int i = 0; i < Integer.parseInt(frame.getClientss().getText()); i++) {
                        Random random = new Random();
                        int sTime = random.nextInt(Integer.parseInt(frame.getMaxs().getText())) + Integer.parseInt(frame.getMins().getText());
                        int aTime = random.nextInt(Integer.parseInt(frame.getMaxa().getText())) + Integer.parseInt(frame.getMina().getText());
                        Task task = new Task(i, aTime, sTime);
                        generatedTasks.add(task);
                    }
                    Collections.sort(generatedTasks);

                    for (Task task1 : generatedTasks) {
                        System.out.println(task1);
                        pw.write(task1.toString() + "\n");
                        pw.flush();
                        sm.gettArea().append(task1 + "\n");
                    }
                }
            }
        });
    }

    @Override
    public void run() {
        int currentTime = 0;
        while (!thr) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (currentTime <= Integer.parseInt(frame.getSimut().getText())) {

            System.out.println("\nTime: " + sm.getCnt());
            pw.write("\nTime: " + sm.getCnt() + "\n");
            pw.flush();

            /** iterate generatedTasks list and pick tasks that have the arrival Time equal with the currentTime*/
            int i = 0;
            while (i < generatedTasks.size()) {
                if (generatedTasks.get(i).getArrivalTime() == currentTime) {
                    /**  - send task to queue by calling the dispatch method from scheduler*/
                    scheduler.dispatchTask(generatedTasks.get(i), sm);
                    show(generatedTasks, sm.gettArea(), i);
                } else {
                    i++;
                }
            }

            /** update the UI frame*/
            sm.invalidate();
            sm.validate();
            sm.repaint();
            currentTime++;

            /** wait an interval of 1 second*/
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void show(List<Task> list, JTextArea textArea, int i) {

        /**  - delete client from list*/
        list.remove(list.get(i));
        textArea.setText("");

        for (Task t : list) {
            textArea.append(t + "\n");
        }
    }

    public static void main(String[] args) throws IOException {
        SimulationManager gen = new SimulationManager();
        Thread t = new Thread(gen);
        t.start();
    }
}

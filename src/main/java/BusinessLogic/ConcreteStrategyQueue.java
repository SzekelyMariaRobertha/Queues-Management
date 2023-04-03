package BusinessLogic;

import GUI.SimulationFrame;
import Model.Server;
import Model.Task;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class ConcreteStrategyQueue implements Strategy {

    private double avServiceTime = 0;
    private int nrClients = 0;
    private JLabel ast = new JLabel();
    private double avWaitingTime = 0;
    private JLabel awt = new JLabel();

    @Override
    public void addTask(List<Server> servers, Task t, SimulationFrame frame, File f, FileOutputStream fos, PrintWriter pw) {

        int minClients = servers.get(0).getTasks().size();

        int minQueue = 0;
        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).getTasks().size() < minClients) {
                minClients = servers.get(i).getTasks().size();
                minQueue = i;
            }
        }

        System.out.println("Chosen queue " + minQueue);
        pw.write("Chosen queue " + minQueue + "\n");
        pw.flush();

        try {
            servers.get(minQueue).getTasks().put(t);
            nrClients++;

            frame.getTextAreaList().get(minQueue).append(t+ "\n");

            servers.get(minQueue).getWaitingPeriod().set(t.getServiceTime());
            avServiceTime += servers.get(minQueue).getWaitingPeriod().get();
            ast.setText("");
            ast.setForeground(Color.BLACK);
            ast.setFont(new Font("Impact", Font.PLAIN, 35));
            ast.setBounds(1280, 350, 150, 40);
            ast.setText(String.format("%.2f", avServiceTime / nrClients));
            frame.getContentPane().add(ast);

            avWaitingTime += t.getArrivalTime();
            awt.setText("");
            awt.setForeground(Color.BLACK);
            awt.setFont(new Font("Impact", Font.PLAIN, 35));
            awt.setBounds(1280, 420, 150, 40);
            awt.setText(String.format("%.2f", (avWaitingTime + avServiceTime) / nrClients));
            frame.getContentPane().add(awt);

            servers.get(minQueue).setNumber(minQueue);
            System.out.println("Client " + t + " was put in queue number " + servers.get(minQueue).getNumber());
            pw.write("Client " + t + " was put in queue number " + servers.get(minQueue).getNumber() + "\n");
            pw.flush();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

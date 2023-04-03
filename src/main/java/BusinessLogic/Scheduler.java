package BusinessLogic;

import GUI.SimulationFrame;
import Model.Server;
import Model.Task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Scheduler {
    private List<Server> servers;
    private Strategy strategy;
    private SimulationFrame sf;
    private File f;
    private FileOutputStream fos;
    private PrintWriter pw;


    public Scheduler(int maxNoServers, int maxTasksPerServer, SimulationFrame sf, File f, FileOutputStream fos, PrintWriter pw) {
        /** for maxNoServers
        // - create server object
        // - create thread with the object
        // System.out.println(maxNoServers);*/

        servers = new ArrayList<>();
        this.sf = sf;
        this.f = f;
        this.fos = fos;
        this.pw = pw;

        for(int i = 0; i < maxNoServers; i++) {
            Server server =  new Server(new ArrayBlockingQueue<>(maxTasksPerServer), new AtomicInteger(0), sf, f, fos, pw);
            servers.add(server);
            Thread thread = new Thread(server);
            thread.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy){

        /** apply strategy pattern to instantiate the strategy with the concrete strategy corresponding to policy*/
        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();
        }

        if(policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchTask(Task t, SimulationFrame frame){
        /** call the strategy addTask method*/
        strategy.addTask(servers, t, frame, f,  fos, pw);
    }
}

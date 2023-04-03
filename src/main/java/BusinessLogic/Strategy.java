package BusinessLogic;

import GUI.SimulationFrame;
import Model.Server;
import Model.Task;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public interface Strategy {

    public void addTask(List<Server> servers, Task t, SimulationFrame frame, File f, FileOutputStream fos, PrintWriter pw );
}

package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.List;


public class SimulationFrame extends JFrame {

    private Timer timer;
    private int cnt = -1;
    private JScrollPane scrollPane;
    private JTextArea tArea;
    private List<JTextArea> textAreaList =  new ArrayList<>();

    public SimulationFrame(String simut, String cozile) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1366, 768);
        setLocationRelativeTo(null);
        setResizable(false);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(114,142,190));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel time = new JLabel("Time: ");
        time.setForeground(Color.BLACK);
        time.setFont(new Font("Impact", Font.PLAIN, 40));
        time.setBounds(1190, 10, 150, 50);
        contentPane.add(time);

        JLabel t = new JLabel();
        t.setForeground(new Color(255,51,85));
        t.setFont(new Font("Impact", Font.PLAIN, 40));
        t.setBounds(1290, 10, 150, 50);
        contentPane.add(t);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cnt++;
                if (cnt <= Integer.parseInt(simut)) {
                    t.setText(Integer.toString(cnt));
                } else {
                    ((Timer) (e.getSource())).stop();
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();

        JLabel clienti = new JLabel("Clients:");
        clienti.setForeground(Color.BLACK);
        clienti.setFont(new Font("Impact", Font.PLAIN, 40));
        clienti.setBounds(1190, 80, 150, 50);
        contentPane.add(clienti);

        tArea = new JTextArea(5, 5);
        tArea.setEditable(false);
        scrollPane = new JScrollPane(tArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(1190, 130, 150, 200);
        contentPane.add(scrollPane);

        JLabel cozi = new JLabel("Queues:");
        cozi.setForeground(Color.BLACK);
        cozi.setFont(new Font("Impact", Font.PLAIN, 40));
        cozi.setBounds(20, 20, 150, 50);
        contentPane.add(cozi);

        int nrCozi =  Integer.parseInt(cozile);

        int coziDone = 0;
        int y = 0;
        for (int i = 0; i < nrCozi; i+=7){
            if(i == 0){
                y += 80;
            }
            else {
                y += 150;
            }

            int x = 20;
            int k = 0;

            while((coziDone < nrCozi) && (k < 7)){
                JLabel test = new JLabel("Queue " + coziDone);
                test.setForeground(Color.BLACK);
                test.setFont(new Font("Impact", Font.PLAIN, 35));
                test.setBounds(x, y, 150, 50);
                contentPane.add(test);

                JTextArea textArea = new JTextArea(5, 5);
                textArea.setEditable(false);
                textAreaList.add(textArea);
                scrollPane = new JScrollPane(textArea);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setBounds(x, y+50, 150, 90);
                contentPane.add(scrollPane);

                coziDone++;
                x += 160;
                k++;
            }
        }

        JLabel avServiceTime = new JLabel("AST ");
        avServiceTime.setForeground(Color.BLACK);
        avServiceTime.setFont(new Font("Impact", Font.PLAIN, 35));
        avServiceTime.setBounds(1190, 350, 150, 40);
        contentPane.add(avServiceTime);

        JLabel avWaitingTime = new JLabel("AWT ");
        avWaitingTime.setForeground(Color.BLACK);
        avWaitingTime.setFont(new Font("Impact", Font.PLAIN, 35));
        avWaitingTime.setBounds(1190, 420, 150, 40);
        contentPane.add(avWaitingTime);

        setVisible(true);
    }

    public JTextArea gettArea() {
        return tArea;
    }

    public List<JTextArea> getTextAreaList() {
        return textAreaList;
    }

    public int getCnt() {
        return cnt;
    }
}

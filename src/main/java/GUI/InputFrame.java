package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class InputFrame extends JFrame{

    private static final long serialVersionUID = 1L;
    private JTextField clientss =  new JTextField();
    private JTextField queuess = new JTextField();
    private JTextField maxa =  new JTextField();
    private JTextField mina = new JTextField();
    private JTextField maxs =  new JTextField();
    private JTextField mins = new JTextField();
    private JTextField simut =  new JTextField();
    private JButton ok = new JButton("START");

    public InputFrame (){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(114,142,190));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel c = new JLabel("Number of clients");
        c.setForeground(new Color(0, 51, 0));
        c.setFont(new Font("Impact", Font.PLAIN, 18));
        c.setBounds(40, 30, 150, 30);
        contentPane.add(c);

        clientss.setForeground(Color.LIGHT_GRAY);
        clientss.setFont(new Font("Impact", Font.PLAIN, 18));
        clientss.setBounds(200, 30, 50, 30);
        contentPane.add(clientss);

        JLabel q = new JLabel("Number of queues");c.setForeground(new Color(0, 51, 0));
        q.setForeground(new Color(0, 51, 0));
        q.setFont(new Font("Impact", Font.PLAIN, 18));
        q.setBounds(40, 80, 150, 30);
        contentPane.add(q);

        queuess.setForeground(Color.LIGHT_GRAY);
        queuess.setFont(new Font("Impact", Font.PLAIN, 18));
        queuess.setBounds(200, 80, 50, 30);
        contentPane.add(queuess);

        JLabel amin =  new JLabel("Min. arrival time");
        amin.setForeground(new Color(0, 51, 0));
        amin.setFont(new Font("Impact", Font.PLAIN, 18));
        amin.setBounds(40, 130, 150, 30);
        contentPane.add(amin);

        mina.setForeground(Color.LIGHT_GRAY);
        mina.setFont(new Font("Impact", Font.PLAIN, 18));
        mina.setBounds(200, 130, 50, 30);
        contentPane.add(mina);

        JLabel amax =  new JLabel("Max. arrival time");
        amax.setForeground(new Color(0, 51, 0));
        amax.setFont(new Font("Impact", Font.PLAIN, 18));
        amax.setBounds(40, 180, 150, 30);
        contentPane.add(amax);

        maxa.setForeground(Color.LIGHT_GRAY);
        maxa.setFont(new Font("Impact", Font.PLAIN, 18));
        maxa.setBounds(200, 180, 50, 30);
        contentPane.add(maxa);

        JLabel smin =  new JLabel("Min. service time");
        smin.setForeground(new Color(0, 51, 0));
        smin.setFont(new Font("Impact", Font.PLAIN, 18));
        smin.setBounds(40, 230, 150, 30);
        contentPane.add(smin);

        mins.setForeground(Color.LIGHT_GRAY);
        mins.setFont(new Font("Impact", Font.PLAIN, 18));
        mins.setBounds(200, 230, 50, 30);
        contentPane.add(mins);

        JLabel smax =  new JLabel("Max. service time");
        smax.setForeground(new Color(0, 51, 0));
        smax.setFont(new Font("Impact", Font.PLAIN, 18));
        smax.setBounds(40, 280, 150, 30);
        contentPane.add(smax);

        maxs.setForeground(Color.LIGHT_GRAY);
        maxs.setFont(new Font("Impact", Font.PLAIN, 18));
        maxs.setBounds(200, 280, 50, 30);
        contentPane.add(maxs);

        JLabel simt =  new JLabel("Simulation interval");
        simt.setForeground(new Color(0, 51, 0));
        simt.setFont(new Font("Impact", Font.PLAIN, 18));
        simt.setBounds(40, 330, 150, 30);
        contentPane.add(simt);

        simut.setForeground(Color.LIGHT_GRAY);
        simut.setFont(new Font("Impact", Font.PLAIN, 18));
        simut.setBounds(200, 330, 50, 30);
        contentPane.add(simut);

        ok.setForeground(Color.BLACK);
        ok.setFont(new Font("Impact", Font.PLAIN, 18));
        ok.setBounds(100, 390, 100, 30);
        contentPane.add(ok);

        setVisible(true);
    }

    public JTextField getClientss() {
        return clientss;
    }

    public JTextField getMaxa() {
        return maxa;
    }

    public JTextField getMina() {
        return mina;
    }

    public JTextField getMaxs() {
        return maxs;
    }

    public JTextField getMins() {
        return mins;
    }

    public JTextField getSimut() {
        return simut;
    }

    public JButton getOk() {
        return ok;
    }

    public JTextField getQueuess() {
        return queuess;
    }
}

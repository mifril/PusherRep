package ru.nsu.vakhrushev.pusher.viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: MAX
 * Date: 30.04.13
 * Time: 21:54
 * To change this template use File | Settings | File Templates.
 */
public class HelpFrame extends JFrame {

    public HelpFrame ()
    {
        setTitle("Help");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JTextArea area = new JTextArea("Pusher rules:\n" +
                "You are the yellow duck\n" +
                "You must put all Portal boxes on red places\n" +
                "You move while you press arrow keys\n" +
                "Good luck");
        area.setEditable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 2, 1));
        add(area);

        JButton button = new JButton("Back");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setPreferredSize(new Dimension(800, 500));
        pack();
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(false);
    }
}

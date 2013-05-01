package ru.nsu.vakhrushev.pusher.viewer;

import ru.nsu.vakhrushev.pusher.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: MAX
 * Date: 30.04.13
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
public class ScoresFrame extends JFrame{

    public ScoresFrame (Controller c, String fileName)
    {
        setTitle("Scores");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JTable levels = new JTable(new LevelsTableModel(c, fileName));
        levels.setShowGrid(true);
        setLayout(new FlowLayout(FlowLayout.CENTER, 2, 1));
        add(new JScrollPane(levels));

        JButton button = new JButton("Back");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }});
        add(button);
        setPreferredSize(new Dimension(800, 500));
        pack();
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(false);
    }
}

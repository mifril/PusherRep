package ru.nsu.vakhrushev.pusher.controller;

import ru.nsu.vakhrushev.pusher.viewer.Viewer;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: MAX
 * Date: 30.04.13
 * Time: 13:50
 * To change this template use File | Settings | File Templates.
 */
public class Timer extends Thread {

    private Viewer viewer;
    private long startTime = 0;
    private Controller controller;
    Score score;

    public Timer(Controller newController, Viewer newViewer, Score newScore)
    {
        controller = newController;
        viewer = newViewer;
        score = newScore;
    }

    public void run()
    {
        startTimer();
        while(controller.isPlaying())
        {
            setScoreTime();

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run()
                {
                    viewer.updateInfoPanel(getScore());
                }
            });
            try
            {
                sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void setScoreTime()
    {
        score.setTime((System.currentTimeMillis() - startTime) / 1000);
    }

    public void startTimer ()
    {
        startTime = System.currentTimeMillis();
    }

    public Score getScore ()
    {
        return score;
    }
}

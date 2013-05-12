package ru.nsu.vakhrushev.pusher.controller;

/**
 * Created with IntelliJ IDEA.
 * User: MAX
 * Date: 30.04.13
 * Time: 18:08
 * To change this template use File | Settings | File Templates.
 */
public class Score {

    private int steps = 0;
    private long startTime = 0;
    private long time = 0;

    public void startCount ()
    {
        steps = 0;
        startTime = System.currentTimeMillis();
    }

    public void incrementSteps()
    {
        steps++;
    }

    public void setTimeInSec(long time)
    {
        this.time = (time - this.startTime) / 1000;
    }

    public int getSteps()
    {
        return steps;
    }

    public long getTime()
    {
        return time;
    }
}

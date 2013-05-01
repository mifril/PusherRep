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
    private long time = 0;

    public void incrementSteps()
    {
        steps++;
    }

    public void setTime(long time)
    {
        this.time = time;
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

package ru.nsu.vakhrushev.pusher;

import ru.nsu.vakhrushev.pusher.controller.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: Vakhrushev Maxim
 * Date: 02.04.13
 * Time: 18:45
 */

public class Main {

    public static void main(String[] args)
    {
        try
        {
            Controller controller = new Controller();
            controller.startGame();
        }
        catch (Exception e)
        {
            System.err.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}

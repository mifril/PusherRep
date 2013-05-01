package ru.nsu.vakhrushev.pusher.viewer;

import ru.nsu.vakhrushev.pusher.model.Direction;
import ru.nsu.vakhrushev.pusher.model.Model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Vakhrushev Maxim
 * Date: 10.04.13
 * Time: 19:01
 */
public class KeyEventListener extends KeyAdapter {

    private Model model = null;

    public KeyEventListener(Model newModel)
    {
        model = newModel;
        System.out.println("HERE");
    }

    @Override
    public void keyPressed(KeyEvent event)
    {
        switch (event.getExtendedKeyCode())
        {
            case KeyEvent.VK_LEFT:
            {
                model.step(Direction.L);
                break;
            }
            case KeyEvent.VK_RIGHT:
            {
                model.step(Direction.R);
                break;
            }
            case KeyEvent.VK_UP:
            {
                model.step(Direction.U);
                break;
            }
            case KeyEvent.VK_DOWN:
            {
                model.step(Direction.D);
                break;
            }
            default :
            {
                System.out.println("Error : keyboard");
            }
        }
    }
}

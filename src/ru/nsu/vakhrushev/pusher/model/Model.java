package ru.nsu.vakhrushev.pusher.model;

import org.apache.log4j.Logger;
import ru.nsu.vakhrushev.pusher.controller.Score;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vakhrushev Maxim
 * Date: 03.04.13
 * Time: 20:43
 */
public class Model extends Observable {

    private final static Logger logger = Logger.getLogger(Model.class);
    /** Set of places positions */
    private final static Set <Position> places = new HashSet<>();
    /** Set of boxes positions */
    private final static Set <Position> boxes = new HashSet<>();
    /** Width of game field */
    private int width = 0;
    /** Height of game field */
    private int height = 0;
    /** Current x position */
    private int x = 0;
    /** Current y position */
    private int y = 0;
    /** Game field.*/
    private char field[][] = null;
    /** Game steps.*/
    private Score score = new Score();


    public int getWidth ()
    {
        return width;
    }
    public int getHeight ()
    {
        return height;
    }
    public int getX ()
    {
        return x;
    }
    public int getY ()
    {
        return y;
    }
    public Score getScore()
    {
        return score;
    }

    public boolean isBorder (int newY, int newX)
    {
        return (field[newY][newX] == 'x');
    }
    public boolean isHero (int newY, int newX)
    {
        return (field[newY][newX] == 't');
    }
    public boolean isPlace (int newY, int newX)
    {
        return (places.contains(new Position(newY, newX)));
    }
    public boolean isBox (int newY, int newX)
    {
        return (boxes.contains(new Position(newY, newX)));
    }
    public boolean isFloor (int newY, int newX)
    {
        return (field[newY][newX] == '.');
    }

    public Model () {}

    public Model(String fileName) throws IOException
    {
        readLevel(fileName);
    }

    public void readLevel(String levelName) throws IOException
    {
        System.out.println(levelName);
        String fileName = "levels/" + levelName + ".txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            resetLevel();
            String str = null;
            int countHeroPosition = 0;
            int countPlacePosition = 0;
            int countBoxPosition = 0;
            List<String> strList= new ArrayList<>();

            while (null != (str = br.readLine()))
            {
                strList.add(str);
            }

            width = strList.get(0).length();
            height = strList.size();
            field = new char[height][width];
            for (int i = 0; i < strList.size(); i++)
            {
                field[i] = strList.get(i).toCharArray();
            }
            for (int i = 0; i < height; i++)
            {
                for (int j = 0; j < width; j++)
                {
                    switch (field[i][j])
                    {
                        case 't':
                        {
                            countHeroPosition++;
                            y = i;
                            x = j;
                            break;
                        }
                        case '&':
                        {
                            countPlacePosition++;
                            places.add(new Position(i, j));
                            break;
                        }
                        case '*':
                        {
                            countBoxPosition++;
                            boxes.add(new Position(i, j));
                            break;
                        }
                        default:
                            break;
                    }
                }
            }
            if (countBoxPosition != countPlacePosition)
            {
                resetLevel();
                System.out.println("Number of boxes != number of places");
            }
            if (countBoxPosition == 0)
            {
                resetLevel();
                System.out.println("Number of boxes == 0");
            }
            if (countPlacePosition == 0)
            {
                resetLevel();
                System.out.println("Number of places == 0");
            }
            if (countHeroPosition != 1)
            {
                resetLevel();
                System.out.println("Number of heros != 1");
            }
/*
            for (int i = 0; i < height; i++)
            {
                for (int j = 0; j < width; j++)
                {
                    System.out.print(field[i][j]);
                }
                System.out.println();
            }
*/
            setChanged();
            notifyObservers();
        }
    }

    public void resetLevel()
    {
        height = 0;
        width = 0;
        x = 0;
        y = 0;
        field = null;
        places.clear();
        boxes.clear();
    }

    public boolean canMove(Direction d)
    {
        boolean result = true;
        switch (d)
        {
            case U:
            {
                if (isBorder(y - 1, x) || (isBox(y - 1, x) && (isBorder(y - 2, x) || isBox(y - 2, x))))
                {
                    result =  false;
                }
                break;
            }
            case D:
            {
                if (isBorder(y + 1, x) || (isBox(y + 1, x) && (isBorder(y + 2, x) || isBox(y + 2, x))))
                {
                    result =  false;
                }
                break;
            }
            case R:
            {
                if (isBorder(y, x + 1) || (isBox(y, x + 1) && (isBorder(y, x + 2) || isBox(y, x + 2))))
                {
                    result =  false;
                }
                break;
            }
            case L:
            {
                if (isBorder(y, x - 1) || (isBox(y, x - 1) && (isBorder(y, x - 2) || isBox(y, x - 2))))
                {
                    result =  false;
                }
                break;
            }
            default:
            {
                System.out.println("Error in canMove");
                break;
            }
        }
        return result;
    }

    public void step (Direction d)
    {
        switch (d)
        {
            case U:
            {
                if (canMove(d))
                {
                    if (isBox(y - 1, x))
                    {
                        field[y - 2][x] = '*';
                        boxes.add(new Position(y - 2, x));
                        boxes.remove(new Position(y - 1, x));
                    }
                    field[y - 1][x] = 't';

                    if (isPlace(y, x))
                    {
                        field[y][x] = '&';
                    }
                    else
                    {
                        field[y][x] = '.';
                    }
                    y--;
                }
                else
                {
                    System.out.println("Can't move " + d.toString());
                }
                break;
            }
            case D:
            {
                if (canMove(d))
                {
                    if (isBox(y + 1, x))
                    {
                        field[y + 2][x] = '*';
                        boxes.add(new Position(y + 2, x));
                        boxes.remove(new Position(y + 1, x));
                    }
                    field[y + 1][x] = 't';

                    if (isPlace(y, x))
                    {
                        field[y][x] = '&';
                    }
                    else
                    {
                        field[y][x] = '.';
                    }
                    y++;
                }
                else
                {
                    System.out.println("Can't move " + d.toString());
                }
                break;
            }
            case R:
            {
                if (canMove(d))
                {
                    if (isBox(y, x + 1))
                    {
                        field[y][x + 2] = '*';
                        boxes.add(new Position(y, x + 2));
                        boxes.remove(new Position(y, x + 1));
                    }
                    field[y][x + 1] = 't';

                    if (isPlace(y, x))
                    {
                        field[y][x] = '&';
                    }
                    else
                    {
                        field[y][x] = '.';
                    }
                    x++;
                }
                else
                {
                    System.out.println("Can't move " + d.toString());
                }
                break;
            }
            case L:
            {
                if (canMove(d))
                {
                    if (isBox(y, x - 1))
                    {
                        field[y][x - 2] = '*';
                        boxes.add(new Position(y, x - 2));
                        boxes.remove(new Position(y, x - 1));
                    }
                    field[y][x - 1] = 't';

                    if (isPlace(y, x))
                    {
                        field[y][x] = '&';
                    }
                    else
                    {
                        field[y][x] = '.';
                    }
                    x--;
                }
                else
                {
                    System.out.println("Can't move " + d.toString());
                }
                break;
            }
            default:
            {
                System.out.println("Error in moving");
                break;
            }
        }
        //printField();
        score.incrementSteps();
        setChanged();
        notifyObservers();
    }

    public boolean isVictory ()
    {
        for (Position p : boxes)
        {
            if (!places.contains(p))
            {
                return false;
            }
        }
        return true;
    }
    public void printField()
    {
        for (int i = 0; i < field.length; i++)
        {
            for (int j = 0; j < field[i].length; j++)
            {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
    }
}

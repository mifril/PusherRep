package ru.nsu.vakhrushev.pusher.viewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: MAX
 * Date: 28.04.13
 * Time: 20:27
 * To change this template use File | Settings | File Templates.
 */
public class InfoComponent extends JPanel {

    private int frameWidth = 0;
    private int frameHeight = 0;
    private int cellSize = 0;
    private static BufferedImage img;
    private long time = 0;
    private long steps = 0;

    public InfoComponent(Dimension d, int cellSize)
    {
        try
        {
            img = ImageIO.read(new File("images/wall.jpg"));
            frameWidth = d.width;
            frameHeight = d.height;
            this.cellSize = cellSize;
            setLayout(new GridLayout(2 ,1));
            add(new JLabel("Steps :" + steps));
            add (new JLabel("Time :" + time));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("Error while loading image");
            System.exit(1);
        }
    }

    public void paintComponent (Graphics g)
    {
        int imgWidth = cellSize;
        int imgHeight = cellSize;
        for (int i = 0; i < frameHeight / imgHeight; i++)
        {
            for (int j = 0; j < frameWidth / imgWidth; j++)
            {
                g.drawImage(img, j * imgWidth, i * imgHeight, imgWidth, imgHeight, this);
            }
        }
    }
}

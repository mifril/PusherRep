package ru.nsu.vakhrushev.pusher.viewer;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAX
 * Date: 29.04.13
 * Time: 0:42
 * To change this template use File | Settings | File Templates.
 */
public class LevelsListModel extends AbstractListModel<String>{


    private List<String> data = new ArrayList<>();

    public LevelsListModel (String fileName) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String level = null;

        while(null != (level = reader.readLine()))
        {
            data.add(level);
        }
    }

    @Override
    public int getSize()
    {
        return data.size();
    }

    @Override
    public String getElementAt(int index) {
        return data.get(index);
    }


}

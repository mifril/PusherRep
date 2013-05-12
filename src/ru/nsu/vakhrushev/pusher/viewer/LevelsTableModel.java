package ru.nsu.vakhrushev.pusher.viewer;

import ru.nsu.vakhrushev.pusher.controller.Controller;

import javax.swing.table.AbstractTableModel;

/**
 * Created with IntelliJ IDEA.
 * User: MAX
 * Date: 28.04.13
 * Time: 18:02
 * To change this template use File | Settings | File Templates.
 */

public class LevelsTableModel extends AbstractTableModel {

        private String[] columnNames = { "Level", "Player", "Time", "Steps"};
        private Object[][] data = null;

        public LevelsTableModel (Controller c, String fileName)
        {
            c.readScores(fileName);
            data = c.getScoresData();
        }

        public int getColumnCount()
        {
            return columnNames.length;
        }

        public int getRowCount()
        {
            return data.length;
        }

        public String getColumnName(int col)
        {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col)
        {
            return data[row][col];
        }
}

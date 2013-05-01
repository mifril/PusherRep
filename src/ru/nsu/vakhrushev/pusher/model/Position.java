package ru.nsu.vakhrushev.pusher.model;

/**
 * Created with IntelliJ IDEA.
 * User: Vakhrushev Maxim
 * Date: 10.04.13
 * Time: 20:18
 */
public class Position {

    private int x = 0;
    private int y = 0;

    public Position(int newY, int newX)
    {
        y = newY;
        x = newX;
    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        if (y != position.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}

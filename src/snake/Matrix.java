package snake;

import java.util.ArrayList;

public class Matrix {
    private static Coord size;
    protected static ArrayList<Coord> snake;
    private static int [][] matrixField;
    private static int color = 1;
    private static Coord bonus;

    protected static Coord getSize() {
        return size;
    }

    protected static void setSize(Coord coord) {
        size = coord;
        matrixField = new int[size.x][size.y];
        snake = new ArrayList<>();
    }

    protected static int getMatrixField(Coord coord) {
        return matrixField[coord.x][coord.y];
    }

    protected static void setMatrixField(Coord coord, int value) {
        matrixField [coord.x][coord.y] = value;
    }

    protected static void setStartSnake() {
        clearField();
        int x_start = size.x / 2;
            int y_start = size.y - 2;
            snake.clear();
        for (int y = 0; y < 5; y++) {
            Coord coord = new Coord(x_start, y_start - y);
            snake.add(0, coord);
        }
        addSnakeOnfield();
        newBonus();
        addBonus();
    }

    protected static void moveSnake(int direction) {
        Coord coord = snake.get(0);
        int x = coord.x; int y = coord.y;
        switch (direction) {
            case 0: y = coord.y - 1; break;
            case 3: x = coord.x + 1; break;
            case 6: y = coord.y + 1; break;
            case 9: x = coord.x - 1; break;
            default: break;
        }
        coord = new Coord(x, y);
        snake.add(0, coord);
        if (getMatrixField(coord) == 2) {
            newBonus();
        } else {
            snake.remove(snake.size() - 1);
        }
        clearField();
        addSnakeOnfield();
        addBonus();
    }

    private static void clearField() {
        for (int x = 0; x <size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                matrixField [x][y] = 0;
            }
        }
    }

    private static void addSnakeOnfield() {
        for (Coord coord : snake) {
            setMatrixField(coord, color);
        }
    }

    private static void addBonus() {
        setMatrixField(bonus, 2);
    }

    private static void newBonus() {
        while (1 > 0) {
        int x = (int) (Math.random() * size.x);
        int y = (int) (Math.random() * size.y);
            if (matrixField[x][y] == 0) {
                bonus = new Coord(x, y);
                break;
            }
        }
    }
}

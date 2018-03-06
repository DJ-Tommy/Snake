package snake;

public class Game {

    private static int direction = 0;
    private static boolean gameOver = false;

    public Game(int x, int y) {
        Matrix.setSize(new Coord(x, y));
    }

    public static void start() {
        Matrix.setStartSnake();
        gameOver = false;
        direction = 0;
    }

    public static int getField(Coord coord) {
            return Matrix.getMatrixField(coord);
    }

    public static void moveDown() {
        if (direction != 0 && !isGameOver()) {
            if (checkDown()) {
                Matrix.moveSnake(6);
                direction = 6;
            }
            else {
                gameOver = true;
            }
        }
    }

    private static boolean checkDown() {
        try {
            if (Matrix.snake.get(0).y < Matrix.getSize().y &&
                    Matrix.getMatrixField(new Coord(Matrix.snake.get(0).x, Matrix.snake.get(0).y + 1)) != 1) {
                return true;
            } else return false;
        } catch (Exception e) {
            return false;}
    }

    public static void moveUp() {
        if (direction != 6 && !isGameOver()) {
            if (checkUp()) {
                Matrix.moveSnake(0);
                direction = 0;
            }
            else {
                gameOver = true;
            }
        }
    }

    private static boolean checkUp() {
        try {
            if (Matrix.snake.get(0).y > 0 &&
                    Matrix.getMatrixField(new Coord(Matrix.snake.get(0).x, Matrix.snake.get(0).y - 1)) != 1) {
                return true;
            } else return false;
        } catch (Exception e) {
            return false;}
    }

    public static void moveLeft() {
        if (direction != 3 && !isGameOver()) {
            if (checkLeft()) {
                Matrix.moveSnake(9);
                direction = 9;
            }
            else {
                gameOver = true;
            }
        }
    }

    private static boolean checkLeft() {
        try {
            if (Matrix.snake.get(0).x > 0 &&
                    Matrix.getMatrixField(new Coord(Matrix.snake.get(0).x - 1, Matrix.snake.get(0).y)) != 1) {
                return true;
            } else return false;
        } catch (Exception e) {
            return false;}
    }

    public static void moveRight() {
        if (direction != 9 && !isGameOver()) {
            if (checkRight()) {
                Matrix.moveSnake(3);
                direction = 3;
            } else {
                gameOver = true;
            }
        }
    }

    private static boolean checkRight() {
        try {
            if (Matrix.snake.get(0).x < Matrix.getSize().x &&
                    Matrix.getMatrixField(new Coord(Matrix.snake.get(0).x + 1, Matrix.snake.get(0).y)) != 1) {
                return true;
            } else return false;
        } catch (Exception e) {
            return false;}
    }

    public static void autoMove() {
        switch (direction) {
            case 0:
                moveUp(); break;
            case 3:
                moveRight(); break;
            case 6:
                moveDown(); break;
            case 9:
                moveLeft(); break;
            default:
                break;
        }
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static int getSizeSnake() {
        return Matrix.snake.size();
    }
}

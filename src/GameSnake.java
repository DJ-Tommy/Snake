import snake.Coord;
import snake.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameSnake extends JFrame {

    private JPanel panelField;
    private JLabel label = new JLabel();
    private JLabel labelMenu = new JLabel();

    private final String TITLE = "Snake v.1.0";
    private final int WIDTH_FIELD = 20;
    private final int HEIGHT_FIELD = 20;
    private final int SIZE_BLOCK = 20;
    private final int SPEED_SNAKE = 300;
    private int SPEED = SPEED_SNAKE; // для возможности менять скорость

    public static void main(String[] args) {
        new GameSnake();
    }

    private GameSnake() {
        new Game(WIDTH_FIELD, HEIGHT_FIELD);
        Game.start();
        initField();
        initLabel();
        initFrame();
        go();
    }

    private void initField() {
        panelField = new JPanel() {
            @Override
            protected void paintComponent (Graphics gr) {
                super.paintComponent(gr);
                for (int x = 0; x < WIDTH_FIELD; x++) {
                    for (int y = 0; y < HEIGHT_FIELD; y++) {
                        gr.setColor(Color.BLACK);
                        if (x == 0) gr.drawLine(0, y * SIZE_BLOCK, WIDTH_FIELD * SIZE_BLOCK, y * SIZE_BLOCK);
                        if (y == 0) gr.drawLine(x * SIZE_BLOCK, 0, x * SIZE_BLOCK, HEIGHT_FIELD * SIZE_BLOCK);
                        if (Game.getField(new Coord(x, y)) == 1) {
                            gr.setColor(Color.RED);
                            gr.fillRect(x * SIZE_BLOCK + 2, y * SIZE_BLOCK + 2,
                                    SIZE_BLOCK - 3, SIZE_BLOCK - 3);
                        }
                        if (Game.getField(new Coord(x, y)) == 2) {
                            gr.setColor(Color.YELLOW);
                            gr.fillOval(x * SIZE_BLOCK + 2, y * SIZE_BLOCK + 2, SIZE_BLOCK - 3, SIZE_BLOCK - 3);
                        }
                    }
                }
                if (Game.isGameOver()) {
                    gr.setColor(Color.WHITE);
                    Font font = new Font("Times New Roman", 1, SIZE_BLOCK * WIDTH_FIELD / 4);
                    gr.setFont(font);
                    gr.drawString("GAME", WIDTH_FIELD * SIZE_BLOCK / 10, HEIGHT_FIELD * SIZE_BLOCK / 3);
                    gr.drawString("OVER", WIDTH_FIELD * SIZE_BLOCK / 10, HEIGHT_FIELD * SIZE_BLOCK / 3 * 2);
                }
            }

        };
        panelField.setPreferredSize(new Dimension(WIDTH_FIELD * SIZE_BLOCK, HEIGHT_FIELD * SIZE_BLOCK));
        panelField.setBackground(Color.GREEN);
        add(panelField, BorderLayout.CENTER);
    }

    private void labelGame() {
        if (!Game.isGameOver()) {
            label.setText(" Ваша змея уже состоит из  " + Game.getSizeSnake() + "  блоков");
        } else label.setText(" Игра закончена. Ваша змея достигла длины в " + Game.getSizeSnake() + " блоков");
    }

    private void initLabel() {
        label.setFont(new Font("Times New Roman", 2, SIZE_BLOCK * WIDTH_FIELD / 25));
        label.setText(" Ваша змея уже состоит из " + Game.getSizeSnake() + " блоков");
        labelMenu.setFont(new Font("Times New Roman", 1 , SIZE_BLOCK * WIDTH_FIELD / 30));
        labelMenu.setText(" Управление игрой стрелками.  ENTER - перезапустить игру");
        add(label, BorderLayout.SOUTH);
        add(labelMenu, BorderLayout.NORTH);
    }

    private void initFrame() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Game.start();
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    Game.moveDown();
                }

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    Game.moveUp();
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    Game.moveLeft();
                }

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    Game.moveRight();
                }
                repaint();
                labelGame();
            }
            @Override
            public void keyReleased(KeyEvent e) { }
        });
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(TITLE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    private void go() {
        while (1 > 0) {
            try {
                Thread.sleep(SPEED);
            } catch (Exception e) {}
            if (!Game.isGameOver()) {
                Game.autoMove();
                repaint();
                labelGame();
            }
        }
    }
}

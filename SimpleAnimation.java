import javax.swing.*;
import java.awt.*;

public class SimpleAnimation extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int BALL_SIZE = 50;
    private static final int SPEED = 5;

    private int x1 = 0;
    private int y1 = 0;
    private int x2 = 0;
    private int y2 = HEIGHT - BALL_SIZE;
    private int x1Direction = 1;
    private int y1Direction = 1;
    private int x2Direction = 1;
    private int y2Direction = -1;

    public SimpleAnimation() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Simple Animation");

        BallPanel panel = new BallPanel();
        add(panel);

        Thread ball1Thread = new Thread(() -> {
            while (true) {
                moveBall1();
                panel.repaint();
                try {
                    Thread.sleep(20); // Adjust speed here
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        ball1Thread.start();

        Thread ball2Thread = new Thread(() -> {
            while (true) {
                moveBall2();
                panel.repaint();
                try {
                    Thread.sleep(30); // Adjust speed here
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        ball2Thread.start();
    }

    private void moveBall1() {
        x1 += x1Direction * SPEED;
        y1 += y1Direction * SPEED;

        if (x1 <= 0 || x1 >= WIDTH - BALL_SIZE) {
            x1Direction *= -1;
        }
        if (y1 <= 0 || y1 >= HEIGHT - BALL_SIZE) {
            y1Direction *= -1;
        }
    }

    private void moveBall2() {
        x2 += x2Direction * SPEED;
        y2 += y2Direction * SPEED;

        if (x2 <= 0 || x2 >= WIDTH - BALL_SIZE) {
            x2Direction *= -1;
        }
        if (y2 <= 0 || y2 >= HEIGHT - BALL_SIZE) {
            y2Direction *= -1;
        }
    }

    private class BallPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.fillOval(x1, y1, BALL_SIZE, BALL_SIZE);
            g.setColor(Color.BLUE);
            g.fillOval(x2, y2, BALL_SIZE, BALL_SIZE);
        }
    }
}

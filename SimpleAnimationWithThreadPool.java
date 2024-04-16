import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimpleAnimationWithThreadPool extends JFrame {

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

    public SimpleAnimationWithThreadPool() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Simple Animation with ThreadPool");

        BallPanel panel = new BallPanel();
        add(panel);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        // Ball 1
        executor.scheduleAtFixedRate(() -> {
            moveBall1();
            panel.repaint();
        }, 0, 20, TimeUnit.MILLISECONDS);

        // Ball 2
        executor.scheduleAtFixedRate(() -> {
            moveBall2();
            panel.repaint();
        }, 0, 30, TimeUnit.MILLISECONDS);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleAnimationWithThreadPool frame = new SimpleAnimationWithThreadPool();
            frame.setVisible(true);
        });
    }
}
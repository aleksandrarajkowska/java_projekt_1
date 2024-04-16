import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimpleAnimationWithThreadPool extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int BALL_SIZE = 50;
    private static final int SPEED = 5;

    private List<Ball> balls = new ArrayList<>();
    private ScheduledExecutorService executor;

    public SimpleAnimationWithThreadPool() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Simple Animation with ThreadPool");

        BallPanel panel = new BallPanel();
        add(panel);

        executor = Executors.newScheduledThreadPool(2);

        // Planujemy zadanie, które co 10 sekund dodaje 1000 kul
        executor.schedule(() -> {
            for (int i = 0; i < 1000; i++) {
                Ball newBall = new Ball(WIDTH / 2, HEIGHT / 2, BALL_SIZE, SPEED);
                balls.add(newBall);
                executor.scheduleAtFixedRate(() -> {
                    newBall.move();
                    panel.repaint();
                }, 0, 20, TimeUnit.MILLISECONDS);
            }
        }, 0, TimeUnit.SECONDS); // Wykonaj natychmiast, a następnie co 10 sekund
    }

    private class Ball {
        private int x, y;
        private int size;
        private int speed;
        private int xDirection = 1;
        private int yDirection = 1;

        public Ball(int x, int y, int size, int speed) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.speed = speed;
        }

        public void move() {
            x += xDirection * speed;
            y += yDirection * speed;

            if (x <= 0 || x >= WIDTH - size) {
                xDirection *= -1;
            }
            if (y <= 0 || y >= HEIGHT - size) {
                yDirection *= -1;
            }
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getSize() {
            return size;
        }
    }

    private class BallPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Ball ball : balls) {
                g.setColor(Color.RED); // Możesz dostosować kolor
                g.fillOval(ball.getX(), ball.getY(), ball.getSize(), ball.getSize());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleAnimationWithThreadPool frame = new SimpleAnimationWithThreadPool();
            frame.setVisible(true);
        });
    }
}

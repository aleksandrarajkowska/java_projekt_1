package projekt_1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BouncingBalls extends JPanel implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int BALL_SIZE = 20;
    private static final int SPEED = 5;

    private Ball[] balls;
    private long startTime;

    public BouncingBalls() {
        balls = new Ball[5000];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball((int) (Math.random() * (WIDTH - BALL_SIZE)),
                                 (int) (Math.random() * (HEIGHT - BALL_SIZE)),
                                 BALL_SIZE, BALL_SIZE, SPEED, SPEED);
            Thread thread = new Thread(balls[i]);
            thread.start();
        }
        Timer timer = new Timer(20, this);
        timer.start();
        startTime = System.currentTimeMillis(); // Zapisanie czasu rozpoczęcia programu
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball ball : balls) {
            ball.draw(g);
        }
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
        checkThreadsStatus();
    }

    private void checkThreadsStatus() {
        boolean allThreadsFinished = true;
        for (Ball ball : balls) {
            if (ball.isAlive()) {
                allThreadsFinished = false;
                break;
            }
        }
        if (allThreadsFinished) {
            long endTime = System.currentTimeMillis(); // Zapisanie czasu zakończenia wszystkich wątków
            long totalTime = endTime - startTime; // Obliczenie całkowitego czasu
            System.out.println("Czas wykonania: " + totalTime + " ms");
            System.exit(0); // Zakończenie programu po zakończeniu animacji
        }
    }

    private static class Ball implements Runnable {
        private int x, y, width, height, dx, dy;
        private int bounceCount;

        public Ball(int x, int y, int width, int height, int dx, int dy) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.dx = dx;
            this.dy = dy;
        }

        public void run() {
            while (bounceCount < 5) {
                move();
                checkCollision();
                try {
                    Thread.sleep(20); // symulacja opóźnienia
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void move() {
            x += dx;
            y += dy;
        }

        private void checkCollision() {
            if (x <= 0 || x + width >= WIDTH) {
                dx = -dx;
                bounceCount++;
            }
            if (y <= 0 || y + height >= HEIGHT) {
                dy = -dy;
                bounceCount++;
            }
        }

        public void draw(Graphics g) {
            g.setColor(Color.RED);
            g.fillOval(x, y, width, height);
        }

        public boolean isAlive() {
            return bounceCount < 5;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Balls");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.add(new BouncingBalls());
        frame.setVisible(true);
    }
}

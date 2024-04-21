// wersja Ola
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BouncingBalls extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BALL_SIZE = 20;
    private static final int BALL_COUNT = 10000;

    public BouncingBalls() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);

        Random random = new Random();
        for (int i = 0; i < BALL_COUNT; i++) {
            int x = random.nextInt(WIDTH - BALL_SIZE);
            int y = random.nextInt(HEIGHT - BALL_SIZE);
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            Ball ball = new Ball(x, y, BALL_SIZE, new Color(r, g, b));
            // Uruchomienie osobnego wątku dla każdej kuli
            new Thread(() -> {
                while (true) {
                    ball.move();
                    repaint();
                    try {
                        Thread.sleep(10); // Opóźnienie między ruchami kul
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball ball : Ball.balls) {
            ball.draw(g);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bouncing Balls");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new BouncingBalls());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    static class Ball {
        private static final Ball[] balls = new Ball[BALL_COUNT];
        private int x, y, size;
        private int dx = 2, dy = 2;
        private Color color;

        public Ball(int x, int y, int size, Color color) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.color = color;
            // Dodanie kuli do globalnej tablicy
            for (int i = 0; i < BALL_COUNT; i++) {
                if (balls[i] == null) {
                    balls[i] = this;
                    break;
                }
            }
        }

        public void draw(Graphics g) {
            g.setColor(color);
            g.fillOval(x, y, size, size);
        }

        public void move() {
            if (x + dx < 0 || x + dx + size > WIDTH) {
                dx = -dx;
            }
            if (y + dy < 0 || y + dy + size > HEIGHT) {
                dy = -dy;
            }
            x += dx;
            y += dy;
        }
    }
}

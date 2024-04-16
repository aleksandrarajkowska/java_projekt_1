import javax.swing.SwingUtilities;

public class main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleAnimation animation = new SimpleAnimation();
            animation.setVisible(true);
        });
    }


}
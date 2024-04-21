import javax.swing.SwingUtilities;

public class main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //SimpleAnimationWithThreadPool animation = new SimpleAnimationWithThreadPool();
            SimpleAnimationWithThreadPool_Runnable_Thread animation = new SimpleAnimationWithThreadPool_Runnable_Thread();
            animation.setVisible(true);
        });
    }


}
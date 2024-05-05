import javax.swing.SwingUtilities;

import projekt_1.SimpleAnimationWithThreadPool_Runnable_Thread;

public class main {

    public static void ma1in(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //SimpleAnimationWithThreadPool animation = new SimpleAnimationWithThreadPool();
            SimpleAnimationWithThreadPool_Runnable_Thread animation = new SimpleAnimationWithThreadPool_Runnable_Thread();
            animation.setVisible(true);
        });
    }


}
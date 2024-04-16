/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplethreads;

import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.*;

/**
 *
 * @author Dominik Olszewski
 */
public class BeeperUsingExecutor {
    
    private final ScheduledExecutorService beeper;
    private final int nBeeps;
    private ScheduledFuture<?> beepingHandle;
    
    public BeeperUsingExecutor(int nBeeps) {
        beeper = new ScheduledThreadPoolExecutor(1);
        this.nBeeps = nBeeps;
    }
    
    public void startBeeping() {
        beepingHandle = beeper.scheduleAtFixedRate(() -> System.out.println("Beep"), 1, 1, SECONDS);
        beeper.schedule(() -> {beepingHandle.cancel(true); beeper.shutdown();}, nBeeps, SECONDS);
    }
    
    public static void main(String[] args) {
        new BeeperUsingExecutor(5).startBeeping();
    }
}

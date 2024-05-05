import java.util.concurrent.RecursiveTask;

public class fibonacci2 {

    public static void main(String[] args) {
        int n = 10; // Number of terms to display
        System.out.println("Fibonacci Series till " + n + " terms:");
        FibonacciTask task = new FibonacciTask(n);
        System.out.println(task.compute());
    }

    static class FibonacciTask extends RecursiveTask<Integer> {
        private final int n;

        FibonacciTask(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                System.out.println("Computing Fibonacci for " + n);
                return n;
            } else {
                FibonacciTask task1 = new FibonacciTask(n - 1);
                FibonacciTask task2 = new FibonacciTask(n - 2);
                task1.fork();
                task2.fork();
                return task1.join() + task2.join();
            }
        }
    }
}

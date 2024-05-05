import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class bibonacii3 extends RecursiveTask<Long> {

    private final long n;

    public bibonacii3(long n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (n <= 1) {
            return n;
        } else {
            // Tworzenie nowego zadania dla mniejszych wartości n
            bibonacii3 task1 = new bibonacii3(n - 1);
            task1.fork();
            bibonacii3 task2 = new bibonacii3(n - 2);
            task2.fork();

            // Oczekiwanie na wyniki zadań i ich sumowanie
            return task1.join() + task2.join();
        }
    }

    public static void main(String[] args) {
        int n = 30; // Przykładowa wartość n do obliczenia
        ForkJoinPool pool = new ForkJoinPool(); // Utworzenie instancji ForkJoinPool
        bibonacii3 fibonacci = new bibonacii3(n);
        System.out.println("Fibonacci(" + n + ") = " + pool.invoke(fibonacci));
        pool.close();
    }
}

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
            Fibonacci task1 = new Fibonacci(n - 1);
            task1.fork();
            Fibonacci task2 = new Fibonacci(n - 2);
            task2.fork();

            // Oczekiwanie na wyniki zadań i ich sumowanie
            return task1.join() + task2.join();
        }
    }

    public static void main(String[] args) {
        int n = 45; // Przykładowa wartość n do obliczenia
        ForkJoinPool pool = new ForkJoinPool(); // Utworzenie instancji ForkJoinPool
        Fibonacci fibonacci = new Fibonacci(n);
        System.out.println("Fibonacci(" + n + ") = " + pool.invoke(fibonacci));
        pool.close();
    }
}

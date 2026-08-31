public class App {
    public static void main(String[] args) {
        DataCenter data = new DataCenter();

        System.out.println("Start value: " + data.getValor());

        Hilo[] threads = new Hilo[DataCenter.NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Hilo(data, "Thread " + i, i);
            threads[i].start();
        }

        for (Hilo thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Interrupted while waiting for " + thread.getName());
                return;
            }
        }

        int expected = DataCenter.NUM_THREADS * Hilo.INCREMENTS;
        System.out.println("Finish value: " + data.getValor());
        if (data.getValor() != expected) {
            System.err.println("Expected " + expected + " but got " + data.getValor());
            System.exit(1);
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        DataCenter data = new DataCenter();

        System.out.println("Start value: " + data.getValor());

        Hilo[] threads = new Hilo[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new Hilo(data, "Thread " + i, i);
            threads[i].start();
        }

        for (int i = 0; i < 5; i++) {
            try {
                threads[i].join();
                Thread.sleep(200);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        System.out.println("Finish value: " + data.getValor());

    }
}

public class Hilo extends Thread {
    public static final int INCREMENTS = 1000;

    private final DataCenter data;
    private final int tID;

    Hilo(DataCenter dataCenter, String name, int tID) {
        this.data = dataCenter;
        this.setName(name);
        this.tID = tID;
    }

    @Override
    public void run() {
        for (int i = 0; i < INCREMENTS; i++) {
            this.data.incrementa(tID);
        }
        System.out.println(this.getName() + " incrementa.");
    }
}

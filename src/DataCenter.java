/**
 * Shared counter accessed by multiple threads.
 *
 * Mutual exclusion is provided with a round-robin spinlock: thread {@code tID}
 * may enter the critical section only when {@code nTurn == tID}. After the
 * increment, the turn is passed to the next thread. {@code nTurn} and
 * {@code valor} are {@code volatile} so updates are visible across threads
 * (the original busy-wait could otherwise loop forever).
 */
public class DataCenter {

    public static final int NUM_THREADS = 5;

    private volatile int valor;
    private volatile int nTurn = 0;

    public void incrementa(int tID) {
        if (tID < 0 || tID >= NUM_THREADS) {
            throw new IllegalArgumentException("tID must be in [0, " + (NUM_THREADS - 1) + "]");
        }
        while (nTurn != tID) {
            Thread.yield();
        }
        valor++;
        nTurn = (nTurn + 1) % NUM_THREADS;
    }

    public synchronized void decrementa() {
        valor--;
    }

    public int getValor() {
        return valor;
    }
}

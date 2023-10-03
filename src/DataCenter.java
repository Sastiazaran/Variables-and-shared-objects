public class DataCenter {

    private int valor;
    private boolean[] turns = new boolean[5];
    private int nTurn = 0;

    public void Incrementa(int tID) {
        turns[tID] = true;
        while (nTurn != tID) {
            Thread.yield(); // execution of other threads
        }
        valor++;
        System.out.println("bf" + nTurn);
        nTurn = (nTurn + 1) % 5;
        System.out.println("af" +nTurn);
        turns[tID] = false;
        System.out.println(2%4);

    }

    public void Decrementa() {
        valor--;
    }

    public int getValor() {
        return valor;
    }

}

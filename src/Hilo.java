// import java.util.Random;

public class Hilo extends Thread{
    private DataCenter data;
    private int tID;
    
    Hilo(DataCenter dataCenter, String str, int tID){
        this.data = dataCenter;
        this.setName(str);
        this.tID = tID;
    }

    public void run(){
        // Random random = new Random(System.currentTimeMillis());
        
        for(int i = 0; i < 1000; i++){
            // boolean flag = random.nextBoolean();    
            // if (!flag){
            //     this.data.Decrementa(); 
            // }else{
            //     this.data.Incrementa();
            // } 
        
            this.data.Incrementa(tID);
        }
        System.out.println(this.getName() + " incrementa.");
    }
}

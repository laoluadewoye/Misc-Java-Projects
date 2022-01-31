public class CalculateDemonimator extends Thread {
    
    static double RetDem; //Returning Denomanator
    
    public void run() {
        try {
            synchronized (this) {
                System.out.print("\nTurning on denomanator arithmetic program...\n");
                Thread.sleep(100);
                Thread.sleep(500); 
                
                Main dem = new Main();
                
                System.out.println("Getting Annual Interest Rate...[Dem]");
                Thread.sleep(500);
                float rate = dem.CannualIR;
                
                System.out.println("Getting Amount of Payments...[Dem]");
                Thread.sleep(500);
                short rateExpo = dem.Cperiod;
                
                
                System.out.println("Calculating Denomanator...[Dem]"); 
                Thread.sleep(1000);
                
                double denomanator = Math.pow((1 + rate), rateExpo) - 1;
                RetDem = denomanator;
                    
                System.out.println("Done!");
                this.notifyAll();
            }
        }
        catch (InterruptedException e) {
            System.out.println("Somehow...this got interrutped. [Thread Dem]");
        }
    }
}

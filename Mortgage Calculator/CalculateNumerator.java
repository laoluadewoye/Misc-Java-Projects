public class CalculateNumerator extends Thread {
    
    static double RetNum; //Returning Numerator
    
    public void run() {
        try {
            System.out.print("\nTurning on numerator arithmetic program...\n");
            Thread.sleep(500);
            
            Main num = new Main();
            
            System.out.println("Getting Annual Interest Rate...[Num]");
            Thread.sleep(500);
            float rate = num.CannualIR;
            
            System.out.println("Getting Amount of Payments...[Num]");
            Thread.sleep(500);
            short rateExpo = num.Cperiod;
            
            
            System.out.println("Calculating Numerator...[Num]"); 
            Thread.sleep(1000);
            double numerator = rate * Math.pow((1 + rate), rateExpo);
            RetNum = numerator;
            
            System.out.println("Done!");
            
        }
        catch (InterruptedException e) {
            System.out.println("Somehow...this got interrutped. [Thread Num]");
        }
        
    }
}

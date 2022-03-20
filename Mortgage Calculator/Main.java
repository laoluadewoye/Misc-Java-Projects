//This was inspired by the Coding by Mosh exercise on his free 3 hour video
//However, I decided to spice it up by learning how to use threads
/*
    I multithreaded the calculations so the numerator and demoninator get done
    At the same time, then main starts back up again to finish the job with an
    easier task to handle. 
*/


import java.util.Scanner;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.lang.*;

public class Main {
    //Cross-class declaration for sending info
	static float CannualIR;
	static short Cperiod;
    
	public static void main(String[] args) throws InterruptedException {
	    //Declaration
	    System.out.println("\tWelcome to Mortgage Calculator!\n");
	    Scanner input = new Scanner(System.in);
	    NumberFormat dollar = NumberFormat.getCurrencyInstance();
	    int principle = 0;
	    float annualIR = 0.0F, monthlyIR;
	    short period = 0, amountOfPayments;
	    String test;
	    
		//Input - try and catch now included
		try {  
            System.out.print("\tPrinciple (in USD): ");
            principle = input.nextInt(); 
            
    		System.out.print("\tAnnual Interest Rate (Don't Use %): ");
    		annualIR = input.nextFloat();
    		
    		System.out.print("\tPeriod (Years): ");
    		period = input.nextShort();
		}
		
		catch (Exception ex) {  
            System.out.println("\nProgram ended because value " + ex.getMessage() + "\n\tWas wrong. Please restart.");
            System.exit(0);
        }
		
		//Calculation (threads)
		monthlyIR = (annualIR / 12) / 100; //Convert Percent to decimal by dividing by 100 and monthly by dividing by 12
		amountOfPayments = (short)(period * 12); //12 because we are making monthly payments. There are 12 months in one year. 
		
		//Putting info into more global static variables
		CannualIR = monthlyIR;
		Cperiod = amountOfPayments;
		
		//Making thread object instances
		CalculateNumerator numObject = new CalculateNumerator();
		CalculateDemonimator demObject = new CalculateDemonimator();
		
		//Starting threads while pausing main
		numObject.start();
		demObject.start();
		synchronized (demObject) {
		    demObject.wait(Long.MAX_VALUE); //The pausing. I can leave it empty but is for 292 billion years because why not.
		}
		
		//Calculation after threads finish running
		Thread.sleep(100);
		System.out.println("Swiching back to main program...");
		Thread.sleep(1000);
		
		System.out.println("Final calculations...");
		System.out.println(numObject.RetNum);
		Thread.sleep(333);
		System.out.println(demObject.RetDem);
		Thread.sleep(333);
		
		double mortgage = principle * ( numObject.RetNum / demObject.RetDem );
		System.out.println(mortgage);
		Thread.sleep(333);
		
		//Output
		System.out.println("\nYour morgage amount is going to be " + dollar.format(mortgage) + ".");
		System.out.println("You're making " + amountOfPayments + " mortgage payments over " + period + " years.");
		
		System.out.println("End of Program.");
	}
}

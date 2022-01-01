/* INTERNATIONAL FAXING ITERATION
 * Olaoluwa Adewoye
 * 10/01/2021
 * Project will calculate the cost of faxing the amount of pages a user enters
 * (10/1) In addition, project will loop this until user decides to stop with Q or q
 */

import java.util.*;
import java.util.Scanner;
import java.text.DecimalFormat;

public class InternationFaxVersionTwo {
    
    private final static DecimalFormat DF2 = new DecimalFormat("0.##");
    
    
    public static void main(String[] args) {
        
        //Declaration
        Scanner input = new Scanner(System.in);
        int userInt = 0; //Needs to be declared in main
        Boolean iterationCheck = true; //Iteration loop check
        char decision; //Used so user can decide for it to stop
        
        //Loop - Changed this label
        while (iterationCheck == true) {
            
            //Calling
            FaxCalculation(userInt);
            
            //More Input
            System.out.println("\n\nEnter Q or q to quit and any other key to run again");
            decision = input.next().charAt(0);
            
            //Iternation Calculation
            if (decision == 'q') {
                System.out.println("You have decided to quit.");
                iterationCheck = false; //Should cause loop to stop
            }
            else if (decision == 'Q') {
                System.out.println("You have decided to quit.");
                iterationCheck = false; //Should cause loop to stop
            }
            else {
                System.out.println("You have decided to keep going\n");
            }
            
        }
        
        System.out.println("End of Program.");
    } //End of Main 
    
    
    public static void FaxCalculation(int pageNum){
        
        //Declaration
        Scanner input = new Scanner(System.in); //For user input in method
        int restPages; //One for total # of pages, other for # after the first ten
        double smallCost = 0, largeCost = 0, total = 0; //Small is for first ten pages, large is for more pages
        
        //Input
        System.out.println("\nPlease enter total # of pages faxed:");
        pageNum = input.nextInt();
        
        //Calculation
        if (pageNum < 0) {
            System.out.println("You can't send a negative number of pages. End of Program.");
            System.exit(0);
        }
        else if (pageNum > 10) {
            restPages = pageNum - 10; //First get the number of pages over 10
            smallCost = 2.00; //10 times 0.2 is 2.0, so we can hardlock this variable
            largeCost = restPages * 0.10; //Calculates rest of the pages' cost
            total = smallCost + largeCost + 3.00; //adds both
        }
        else {
            smallCost = pageNum * 0.20; //For when the # of pages is 10 or under
            largeCost = 0.00;
            total = smallCost + largeCost + 3.00;   
                //we only need to make this equal smallCost, but we shall add everything anyways
        }
        
        //Output
            //I use the decimal format here
        System.out.println("\n*** Total International Fax Charges");
        System.out.println("Service Charge: $ 3.00");
        System.out.println("First 10 pages charge: $ " + DF2.format(smallCost));
        System.out.println("Over 10 pages charge: $ " + DF2.format(largeCost));
        System.out.println("Total Charges: $ " + DF2.format(total));
        
        
    } //End of FC Method
    
} //End of Program

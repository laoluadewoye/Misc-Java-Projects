/* PROGRAM HEADER
 * Olaoluwa Adewoye
 * Sept. 13, 2021
 * Project Description - Project will - 
 */
package shirtpurchase;
import java.util.*;
import java.text.DecimalFormat;

public class Shirtpurchase {
    private final static DecimalFormat df2 = new DecimalFormat("#.00");
    //Guess - doing this might lock the outcome of calculations to two decimal places
    //This is for rounding off?
    
    public static void main(String[] args) {
        //Declaration
        final double TAXRATE = 0.06; //For calculating tax later on. No changing
        Scanner input = new Scanner(System.in); //Getting user input
        double shirtCost, taxAmount, total; //Double because money is calculated in decimal form
        String firstName; 
        
        //Input - Get the name and shirt cost
        System.out.println("What is your first name?"); 
        firstName = input.nextLine();
        System.out.println(firstName + ", how much does the shirt you want cost?");
        shirtCost = input.nextDouble(); 
        
        //Calculation
        taxAmount = shirtCost * TAXRATE; //Get the tax Amount
        total = shirtCost + taxAmount; //Add tax on top of original cost
        
        //Output
        System.out.println("Shirt Cost = $" + df2.format(shirtCost));
        System.out.println("Tax (" + (int)(TAXRATE * 100) + "%) = $" + df2.format(taxAmount));
        System.out.println("The total is $" + df2.format(total));
        
    } //End of Main
    
} //End of Program

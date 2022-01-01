/* MARKING UP PRICE
 * Olaoluwa Adewoye
 * Sept. 17, 2021
 * Project is going to show mark up price and total cost of item from orignal 
 *      price input
 */
package markupprice;
import java.util.*;
import java.util.Scanner;
import java.text.DecimalFormat;

public class MarkUpPrice {
    
    private final static DecimalFormat df2 = new DecimalFormat("#.00");

    public static void main(String[] args) {
        
        //Declaration
        Scanner input = new Scanner(System.in); //Probably not needed, but I'm being extra
        double oriPrice, sellPrice, salesTax, finalPrice;
        final double TAXRATE = 0.06; //As this is a rate, there is no need for it to change
        final double MARKUP = 0.2; //This will never need to change
        
        //Input
        System.out.println("What is the orignal price of your item?");
        oriPrice = input.nextDouble(); //We are getting the price from user, I can't think of example
        
        //Calculation
        sellPrice = (oriPrice * MARKUP) + oriPrice; //New selling price point
        salesTax = sellPrice * TAXRATE; //Calculating the tax to pay
        finalPrice = sellPrice + salesTax; //Adding it all together
        
        //Output
        System.out.println("Your original price: $" + df2.format(oriPrice));
        System.out.println("Mark Up Rate: " + (int)(MARKUP * 100) + "%");
        System.out.println("New selling price: $" + df2.format(sellPrice));
        System.out.println("Sales Tax Percentage: " + (int)(TAXRATE * 100) + "%");
        System.out.println("Sales Tax cost: $" + df2.format(salesTax));
        System.out.println("Final item price: $" + df2.format(finalPrice));
        
        System.out.println("End of program");
    } //End of Main
    
} //End of Program

/* INTERNATIONAL FAXING
 * Olaoluwa Adewoye
 * 9/22/21
 * Project will calculate the cost of faxing the amount of pages a user enters
 */

import java.util.*;
import java.util.Scanner;
import java.text.DecimalFormat;

public class internationaltax {
    
    private final static DecimalFormat DF2 = new DecimalFormat("0.##");
    
    public static void main(String[] args) {
        
        //Declaration
        Scanner input = new Scanner(System.in); //For user input
        int pageNum, restPages; //One for total # of pages, other for # after the first ten
        double smallCost = 0, largeCost = 0, total = 0; //Small is for first ten pages, large is for more pages
        
        //Input
        System.out.println("Please enter total # of pages faxed:");
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
        System.out.println("*** Total International Fax Charges");
        System.out.println("Service Charge: $ 3.00");
        System.out.println("First 10 pages charge: $ " + DF2.format(smallCost));
        System.out.println("Over 10 pages charge: $ " + DF2.format(largeCost));
        System.out.println("Total Charges: $ " + DF2.format(total));
        
        System.out.println("End of Program.");
    } //End of Main
    
} //End of Program

/* CHOOSING THE MONTH
 * Olaoluwa Adewoye
 * Sept. 20, 2021
 * Project is going to choose a month of the year using a swtich statement .
 */
package month;
import java.util.*;
import java.util.Scanner;


public class Month {
    
    public static void main(String[] args) {
        
        //Declaration
        Scanner input = new Scanner(System.in);
        int month;
        
        //Input
        System.out.print("What month (numerically – 1 to 12) is it? ");
        month = input.nextInt();
        
        //Calculation
        
        //Output
        switch(month) {
            case 1: 
                System.out.println("January");
                break;
            case 2: 
                System.out.println("Febuary");
                break;
            case 3: 
                System.out.println("March");
                break;
            case 4: 
                System.out.println("April");
                break;
            case 5: 
                System.out.println("May");
                break;
            case 6: 
                System.out.println("June");
                break;
            case 7: 
                System.out.println("July");
                break;
            case 8: 
                System.out.println("August");
                break;
            case 9: 
                System.out.println("September");
                break;
            case 10: 
                System.out.println("October");
                break;
            case 11: 
                System.out.println("November");
                break;
            case 12: 
                System.out.println("December");
                break;
            default: 
                System.out.println("Invalid Month");
                break;
        }
        
        
        System.out.println("End of Program");
    } //End of Main
    
}//End of Program

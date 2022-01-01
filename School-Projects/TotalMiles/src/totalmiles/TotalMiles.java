/* TOTAL MILES
 * Olaoluwa Adewoye
 * Sept. 13, 2021
 * Project is going to calculate the total amount of miles drivable using MPG
 */
package totalmiles;
import java.util.*;
import java.util.Scanner;

public class TotalMiles {
    public static void main(String[] args) {
        //Declaration
        Scanner input = new Scanner(System.in); //For user input
        int tankSize, milPerGal, totalMiles; 
            /* Volume of car tank in gallons
             *Efficiency of car
             *and the total amount of miles respectively
             */ 
        
        //Input
        System.out.println("Enter the size of the gas tank of the car in GALLONS:");
        tankSize = input.nextInt();
        System.out.println("Enter the MPG of the vehicle:");
        milPerGal = input.nextInt();
        
        //Calculation
        totalMiles = tankSize * milPerGal;
        
        //Output
        System.out.println("The total # of miles you can drive without refueling is: " + totalMiles);
        
    } //End of Main
    
} //End of Program

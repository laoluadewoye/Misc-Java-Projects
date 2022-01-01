/* WHAT TO WEAR
 * Olaoluwa Adewoye
 * Sept. 17, 2021
 * Project is going to decide what to wear based off of today's temperature.
 */
package todaytemp;
import java.util.*;
import java.util.Scanner;

public class TodayTemp {

    public static void main(String[] args) {
        
        //Declaration
        Scanner input = new Scanner(System.in);
        int todayTemp;
        
        //Input
        System.out.println("What is today’s temperature?");
        todayTemp = input.nextInt();
        
        //Calculation
        
        //Output
        if (todayTemp >= 70) {
            System.out.println("It's " + todayTemp + " degrees, no jacket needed today.");
        }
        else if (todayTemp >= 60 && todayTemp < 70) {
            System.out.println("It's " + todayTemp + " degrees, wear a jacket today.");
        }
        else if (todayTemp < 60) {
            System.out.println("It's " + todayTemp + " degrees, wear a coat today.");
        }
        
        System.out.println("End of Program");
    } //End of Main
    
} //End of Program

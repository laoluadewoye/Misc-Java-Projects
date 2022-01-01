/* EVEN AND ODD COUNTER
 * Olaoluwa Adewoye
 * 10/08/2021
 * Project will take a number of integer inputs from user's file, 
        add them to even and odd values accoridngly, and add them up. 
 */

import java.util.*;
import java.io.*;

public class EvenOdd {
    public static void main(String[]args) throws FileNotFoundException { //Needed in case file is not implemented proper
        
        //Declaration
        File sumUp = new File("Number.txt"); //Establishes file
        Scanner inFile;
        inFile = new Scanner(sumUp); //Reading the file contents
        
        int oddSum = 0, evenSum = 0, temp, tempCal; //temporary values and addition values
        boolean check = inFile.hasNext(); //An attempt to stop the comma right before the last value
        
        //Calculation 
        while (inFile.hasNext()) {
            
            temp = inFile.nextInt();
            System.out.print(temp); //Printing the values on a line, or "reading" them out
            tempCal = temp % 2; //Calculating the remainder, or mod 2
            switch (tempCal) {
                case 0: //If mod 2 = 0, then that means it goes into 2, or it is an even number
                    evenSum += temp;
                case 1: //If mod 2 = 1, then that means it has the remainder 1, which implies it's an odd number
                    oddSum += temp;
            }
            if (check == true) { //An attempt to stop the comma right before the last value. It failed. 
                System.out.print(", ");
            }
        }
        System.out.print("End of list."); //Making up for it by manually letting user know there are no more numbers. 
        
        //Output
        System.out.println("\nThe sum of odd numbers is " + oddSum); //extra line to make it look neat
        System.out.println("The sum of even numbers is " + evenSum);
        
        System.out.println("\nEnd of Program."); //extra line to make it look neat
    } //End of Main
    
} //End of Program

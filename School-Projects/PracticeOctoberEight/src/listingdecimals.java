//Listing all libraries you know
import java.util.*;
import java.util.Scanner;
import java.text.DecimalFormat;

public class listingdecimals {
    
    private static DecimalFormat DF2 = new DecimalFormat("0.##");
    
    
    public static void main(String[] args) {
        //Declaration
        Scanner input = new Scanner(System.in);
        System.out.print("(Keep in mind you will need to go to 4 decimal points)\nChoose how many double numbers you wish to input into the array: ");
        int arrayLength = input.nextInt();
        int lengthCheck, decimalPlaces;
        double[] userNumbers = new double[arrayLength];
        String length;
        double temp;
        
        //Input
        for (int i = 0; i < arrayLength; i++) {
            System.out.println("Number " + (i + 1));
            System.out.print("Enter your first double number: ");
            userNumbers[i] = input.nextDouble();
            temp = userNumbers[i];
            length = Double.toString(Math.abs(temp));
            lengthCheck = length.indexOf('.');
            decimalPlaces = length.length() - lengthCheck - 1;
            if (decimalPlaces < 4) {
                System.out.println("Nah, you thought you were slick. Try again.");
                i--;
            }
        }    
        //Calculation
        
        //Output
        System.out.println("The list of numbers:");
        for (int i = 0; i < arrayLength; i++) {
            System.out.println(DF2.format(userNumbers[i]));
        }

        System.out.println("\nEnd of Program.");
    } //End of main method
    
} //end of class

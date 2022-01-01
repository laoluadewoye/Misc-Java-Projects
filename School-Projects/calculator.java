/* CALCULATOR
 * Olaoluwa Adewoye
 * 9/22/21
 * Program will do basic arithmatic (Addition, Subtraction, Multiplication,
        Division)
 */

import java.util.*;
import java.util.Scanner;

public class calculator {
    
    public static void main(String[] args) {
        
        //Declaration
        Scanner input = new Scanner(System.in); //User input mechanism needed
        int int1, int2; //We are taking two integers from user to get result
        char operation; //For getting (+, -, *, /)
        int result = 0;
        
        //Input
        System.out.print("Enter Number 1: "); //Getting first number
        int1 = input.nextInt();
        System.out.print("Enter Number 2: "); //Getting second number
        int2 = input.nextInt();
        System.out.println("Addition(+), Subtraction(-), Multiplication(*), Division(/)"); 
            //Someone may not know what the symbols mean
        System.out.print("Enter Operation Symbol: "); //Put examples above, THEN ask
        operation = input.next().charAt(0);
        
        //Calculation
        switch (operation) { //Code for the actual calculator
            case '+' : //Addition
                result = int1 + int2;
                break; //Prevents the rest of cases from running
            case '-' : //Subtraction
                result = int1 - int2;
                break;
            case '*' : //Multiplication
                result = int1 * int2;
                break;
            case '/' : //Division
                if (int2 != 0) { //Checks if there is a divide by zero problem
                    result = int1 / int2;
                    break;
                }
                else {
                    System.out.println("Second number cannot be zero. End of Program.");
                    System.exit(0); //Ends program right then and there
                }
            default:
                System.out.println("Invalid symbol. End of Program.");
                System.exit(0); //Same thing
        }
        
        //Output
        System.out.println(int1 + " " + operation + " " + int2 + " = " + result); //The final output
        
        System.out.println("End of Program.");
    } //End of Main
    
} //End of Program

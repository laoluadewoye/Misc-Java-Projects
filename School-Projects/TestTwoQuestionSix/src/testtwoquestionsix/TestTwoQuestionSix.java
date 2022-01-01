/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testtwoquestionsix;
import java.util.Scanner;
/**
 *
 * @author laolu
 */
public class TestTwoQuestionSix {
    public static void main(String[]args) {
        Scanner input = new Scanner(System.in);
        int userGuess;
        int counter = 0;
        boolean quit = true;
        final char QUITLETTER = 'Q';
        char decision;
        
        while (quit == true) {
            System.out.print("Please enter a new number from 1-20: ");
            userGuess = input.nextInt();
            quit = guessing(userGuess);
            counter++;
            if (quit == false) {
                break;
            }
            System.out.println("Do you wish to quit? (Enter a capital 'Q' to exit, and any other key to continue)");
            decision = input.next().charAt(0);
            if (decision == QUITLETTER) {
                quit = false;
            }
            else if (decision != QUITLETTER) {
                quit = true;
            }
        }
        
        System.out.println("It took you " + counter + " attempts to end the program!");
        System.out.println("End of program.");
    }
    
    public static boolean guessing(int num) {
        final int CORRECT_NUMBER = 7;
        boolean verify = validate(num);
        boolean returnValue;
        if (verify == true) {
            if (num == CORRECT_NUMBER) {
                System.out.println("You have guessed the correct number!");
                returnValue = false;
                return returnValue;
            }
            else {
                System.out.println("Incorrect guess. Try again!");
                returnValue = true;
                return returnValue;
            }
        }
        else {
            System.out.println("You have entered an invalid number. Try again!");
            returnValue = true;
            return returnValue;
        }
    }
    
    public static boolean validate(int validAns) {
        if (validAns >= 1) {
            if (validAns <= 20) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}

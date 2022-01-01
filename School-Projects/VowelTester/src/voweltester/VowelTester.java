/* VOWEL TESTER
 * Olaoluwa Adewoye
 * 10/15/21
 * Program will take a potentially infinite amount of character inputs and count all the vowels
 */


package voweltester;

import java.util.Scanner;

public class VowelTester {

   
    public static void main(String[] args) {
        //Declaration
        Scanner input = new Scanner(System.in);
        char userChar;
        boolean test, forever = true;
        int vowelCount = 0;
        
        //Input + Calcualtion
        while (forever != false) {
            System.out.print("Enter any character you want: ");
            userChar = input.next().charAt(0);
            test = isVowel(userChar); //Calls the check method and returns value into bool variable
            
            if (test == true) { //Transferred value used to do the check in main method
                vowelCount++;
            }
        
            forever = stopCheck(); //Checks if user still wants to type more characters
        }
        
        //Output
        System.out.println("You have entered " + vowelCount + " vowels."); //Outputs amount of vowels
        System.out.println("End of Program.");
        
    } //End of Main method
    
    public static boolean isVowel(char test) { //Code for vowel checking
        //Calculation
        switch (test) {
            case 'a': 
                return true;
            case 'A': //Accounting for the rare possibility that caps lock is on
                return true;
            case 'e':
                return true;
            case 'E': 
                return true;
            case 'i':
                return true;
            case 'I': 
                return true;
            case 'o': 
                return true;
            case 'O': 
                return true;
            case 'u': 
                return true;
            case 'U': 
                return true;
            default: //For if any other character, capitalized or not, is entered 
                return false; //false means not vowel
        }
    } //End of vowel check method
    
    public static boolean stopCheck() {
        //Declaration
        Scanner input = new Scanner(System.in);
        char choice;
        
        //Input + Calculation
        while (true) {
            System.out.println("\nDo you wish to keep entering numbers? (y/n)");
            choice = input.next().charAt(0);
            switch (choice) {
                case 'y':
                    System.out.println("\n");
                    return true;
                case 'n':
                    return false;
                default:
                    switch (choice) { //You first saw nested ifs, now get ready for nested switches
                        
                        /* Accounting for the slightly more common possiblity people prefer to answer 
                         * y/n questions with capital Y and N for formality
                         */
                        
                        case 'Y': 
                            System.out.println("\n");
                            return true;
                        case 'N':
                            return false;
                        default:
                            System.out.println("Invaild choice for question. Please try again!");
                    }
            }
        }
    } //End of user inquiry method
    
} //End of class

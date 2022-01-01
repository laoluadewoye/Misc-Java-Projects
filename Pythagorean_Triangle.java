//Calculating the Sides of a pythagorean triangle (Example is a 3-4-5 triangle)

import java.util.*;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		int firstnum, secondnum, choice;
		double thirdnum;
		Scanner input = new Scanner(System.in);
	    
		firstnum = getNumOne();
		
		try {
		    System.out.println("\nIs your number even or odd? Put 1 for odd, 2 for even");
		    choice = input.nextInt();
		
    		if (choice == 2) {
    		    secondnum = even(firstnum);
    		}
    		else if (choice == 1) {
    		    secondnum = odd(firstnum);
    		}
    		else {
    		    System.out.println("\nWrong number, it is ONLY 1 or 2.");
    		    return;
    		}
    		
    	    thirdnum = Math.sqrt(firstnum * firstnum + secondnum * secondnum);
    	    
    	    System.out.println("Your side lenghts of your Pythagorean triangle are " + firstnum + "  " + secondnum + "  " + thirdnum);
		    System.out.println("End of program.");
	    }
		catch(Exception e) {
		    if (e.getMessage() == null) {
		        System.out.println("\nYou were supposed to pick 1 or 2, not write out stuff >:(");
		    }
		}
	}
	
	public static int even(int num) {
	    int numSq = num * num;
	    return (((numSq - 8) / 4) + 1);
	}
	public static int odd(int num) {
	    int numSq = num * num;
	    return (((numSq - 3) / 2) + 1);
	}
	
	public static int getNumOne() {
	    int num;
	    Scanner input = new Scanner(System.in);
	    
		System.out.println("What is your number?");
		num = input.nextInt();
		
		return num;
	}
}

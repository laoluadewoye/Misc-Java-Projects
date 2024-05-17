import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
	    //declaration
	    Scanner input = new Scanner(System.in);
	    int userNumber;
	    
	    //input
		System.out.print("Please enter a number: ");
		userNumber = input.nextInt();
		
		
		//calculation + output
		if ((userNumber % 5 == 0) && (userNumber % 3 == 0)) {
		    System.out.println("FizzBuzz");
		}
		else if (userNumber % 5 == 0) {
		    System.out.println("Fizz");
		}
		else if (userNumber % 3 == 0) {
		    System.out.println("Buzz");
		}
		else {
		    System.out.println(userNumber);
		}
		
		System.out.println("End of Program.");

	}
}

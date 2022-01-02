import java.util.Scanner;
import java.lang.Math;

public class BirthdayParadox {
    
    public static long factorial(int num) {
        long temp = 1;
        for (int i = num; i > 0; i--) {
            temp = temp * i;
        }
        return temp;
    }
    
    public static int exponent (int coeff, int expo) {
        int temp = 1;
        for (int i = 0; i < expo; i++) {
            temp = temp * coeff;
        }
        return temp;
    }
    
    public static double paradoxEquation (int numerator, int denomanator) {
        double percentage = numerator / denomanator;
        return percentage;
    }

    public static void main(String[] args) {
        //Declaration
        Scanner input = new Scanner(System.in);
        int people;
        long numer, denom, result;
      
        //Input
        System.out.println("This is the birthday paradox!\nYou put the number of people you're comparing and find out the chances of birthday's being shared!");
        System.out.print("Input a number: ");
        people = input.nextInt();
        
        //Calculation
        numer = factorial(people - 1);
        denom = Math.pow(365, people - 1);
        //long result = paradoxEquation(numer, denom);
        //double trueResult = (1 - result) * 100;
        
        //Output
        //System.out.println("There is a " + trueResult + "% chance someone shares a birthday. Happy Birthday!");
                
        System.out.println("End of Program");
    }
    
}

import java.util.Scanner;

public class BirthdayParadox {
    
    public static int factorial(int num) {
        int temp = 1;
        for(int i = num; i > 0; i--) {
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
    
    public static double paradoxEquation (int numerator, int denomanator, int raised) {
        double percentage;
        //(numerator factorial / denomanator factorial) * (1 / 365^raised)
        
        percentage = ((numerator / denomanator) * (1 / (exponent(365, raised))));
        return percentage;
    }

    public static void main(String[] args) {
        //Declaration
        Scanner input = new Scanner(System.in);
        int people;
        
        //Input
        System.out.println("This is the birthday paradox!\nYou put the number of people you're comparing and find out the chances of birthday's being shared!");
        System.out.print("Input a number: ");
        people = input.nextInt();
        
        //Calculation
        int numer = factorial(364);
        System.out.println(numer);
        int denom = factorial(365 - people);
        System.out.println(denom);
        double result = paradoxEquation(numer, denom, people);
        double trueResult = (1 - result) * 100;
        
        //Output
        System.out.println("There is a " + trueResult + "% chance someone shares a birthday. Happy Birthday!");
                
        System.out.println("End of Program");
    }
    
}

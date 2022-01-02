import java.util.Scanner;

public class BirthdayParadox {
    
    public static long factorial(int num) {
        long temp = 1;
        for (int i = num; i > 0; i--) {
            temp = temp * i;
            System.out.println(temp);
        }
        return temp;
    }
    
    public static long exponent(int coeff, int raise) {
        long temp = 1;
        for (int i = 0; i < raise; i++) {
            temp = temp * coeff;
            System.out.println(temp);
        }
        return temp;
    }
    
    public static void main(String[] args) {
        //Declaration
        Scanner input = new Scanner(System.in);
        int people;
        long numer, denom;
        double percentage;

      
        //Input
        System.out.println("This is the birthday paradox!\nYou put the number of people you're comparing and find out the chances of birthday's being shared!");
        System.out.print("Input a number: ");
        people = input.nextInt();
        
        //Calculation
        System.out.println("Numerator");
        numer = factorial(people - 1);
        System.out.println(numer);
        
        System.out.println("Denomanator");
        denom = exponent(365, people - 1);
        System.out.println(denom);
        
        System.out.println("Percentage");
        percentage = (numer / denom);
        System.out.println(percentage);
        
        double trueResult = (1 - percentage) * 100;
        
        //Output
        System.out.println("There is a " + trueResult + "% chance someone shares a birthday. Happy Birthday!");
                
        System.out.println("End of Program");
    }
}

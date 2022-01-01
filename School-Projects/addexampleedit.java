/* EDITING SUM OF TWO INT
 * Olaoluwa Adewoye
 * 10/14/2021
 * Project will take the example script to add two integers together add the
        functionality to subtract, divide, and multiply
 */
import java.util.Scanner;

public class addexampleedit {
    
    public static void main(String[] args) { 
        Scanner input = new Scanner(System.in);
        int num1,num2,sum, difference, product, quotient; 
        char operation; 
         
        num1 = getData(); 
        num2 = getData(); 
        
        System.out.println("Addition(+), Subtraction(-), Multiplication(*), Division(/)"); 
        System.out.print("Enter Operation Symbol: ");
        operation = input.next().charAt(0);
        
        if (operation == '+') {
            sum = getSum(num1,num2); 
            System.out.println(num1 + " + " + num2 + " = " + sum); 
        }
        else if (operation == '-') {
            difference = getDifference(num1, num2);
            System.out.println(num1 + " - " + num2 + " = " + difference);
        }
        else if (operation == '*') {
            product = getProduct(num1, num2);
            System.out.println(num1 + " x " + num2 + " = " + product);
        }
        else if (operation == '/') {
            quotient = getQuotient(num1, num2);
            System.out.println(num1 + " / " + num2 + " = " + quotient);
        }
    } // end of main 
     
    // input method definition below 
    public static int getData() {   
        Scanner scan = new Scanner(System.in); // allows us to read from keyboard 
        int number; // local variable 
         
        System.out.println("Please enter an integer: "); 
        number = scan.nextInt(); 
         
        return number; 
    } // end of method  
     
    // operation method definitions below 
    public static int getSum(int n1, int n2) {   
        return (n1 + n2); 
    } // end of addition method
    
    public static int getDifference(int n1, int n2) {
        return (n1 - n2);
    } //end of subtraction method
    
    public static int getProduct(int n1, int n2) {
        return (n1 * n2);
    } //end of multiplication method
    
    public static int getQuotient(int n1, int n2) {
        return (n1 / n2);
    } //end of division method
 
} // end of program  


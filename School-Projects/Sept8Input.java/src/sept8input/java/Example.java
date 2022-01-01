/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sept8input.java;
import java.util.*;
import java.util.Scanner;
/**
 *
 * @author laolu
 */
public class Example {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Declaration Section
        Scanner input = new Scanner(System.in);
        String firstName;
        Double gpa;
        
        //Input Section
        System.out.println("Please enter your name");
        firstName = input.nextLine();
        System.out.println("Please enter your GPA");
        gpa = input.nextDouble();
       
        //Calculation Section
        gpa = gpa + 0.25;
        
        //Output Section
        System.out.println("Hello " + firstName + ". " + firstName + ", Your GPA is " + gpa);
    }
    
}

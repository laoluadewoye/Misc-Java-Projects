/* FINAL GRADE
 * Olaoluwa Adewoye
 * 10/15/21
 * Program will take a potentially infinite amount of character inputs and count all the vowels
 */

package finalgrade;

import java.util.Scanner;
import java.text.DecimalFormat;

public class FinalGrade {
    
    private static DecimalFormat DF2 = new DecimalFormat("0.##");

    public static void main(String[] args) {
        //Declaration
        Scanner input = new Scanner(System.in);
        double userScore1, userScore2, testAve, projectAve;
        
        //Test Input
        System.out.print("Please enter your first test grade: ");
        userScore1 = input.nextDouble();
        System.out.print("Please enter your second test grade: ");
        userScore2 = input.nextDouble();
        
        //Test Calculation
        testAve = getTestAverage(userScore1, userScore2); //Uses another method to calculate average
        
        //Project Input 
        System.out.print("Please enter your first project grade: ");
        userScore1 = input.nextDouble();
        System.out.print("Please enter your second project grade: ");
        userScore2 = input.nextDouble();
        
        //Project Calculation
        projectAve = getProjectAverage(userScore1, userScore2); //Uses another method to calculate average
        
        //Output
        displayGrade(projectAve, testAve); //Calls void to do the rest of program
        System.out.println("End of Program.");
    } //End of program when ran
    
    public static double getProjectAverage(double project1, double project2) { //Gets average of project grades
        return((project1 + project2) / 2);
    }
    
    public static double getTestAverage (double test1, double test2) { //Gets average of test grades
        return((test1 + test2) / 2);
    }
    
    public static void displayGrade (double projectAverage, double testAverage) {
        //Declaration
        final double TESTWEIGHT = 0.7; //Ratios, doesn't need to be changed
        final double PROJECTWEIGHT = 0.3;
        double weightGrade;
        char letter;
        
        //Caluclation
        weightGrade = (testAverage * TESTWEIGHT) + (projectAverage * PROJECTWEIGHT); //Calculating final grade
        letter = letterGrade(weightGrade); //Finds letter grade
        
        //Output
        System.out.println("Your final score is " + DF2.format(weightGrade) + "%.");
        if (letter == 'A') {
            System.out.println("Your final grade is an A.");
        }
        else {
            System.out.println("Your final grade is a " + letter + ".");
        }
    }
    
    public static char letterGrade (double grade) { //Choosing a letter grade
        if (grade >= 90) {
            return ('A');
        }
        else if (grade >= 80) {
            return ('B');
        }
        else if (grade >= 70) {
            return ('C');
        }
        else if (grade >= 60) {
            return ('D');
        }
        else {
            return ('F');
        }
    }
} //End of Class

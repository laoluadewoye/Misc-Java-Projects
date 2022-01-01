/* GRADE TRACKER
 * Olaoluwa Adewoye
 * 10/04/21
 * Program will read a file with a list of grade entries and analyze them
 */
package gradetracker;
import java.io.*; //This has file functions
import java.util.*;

public class GradeTracker {


    public static void main(String[] args) throws FileNotFoundException {
        //Declaration
        
        File grades = new File("gradeData.txt"); //establishing file
        Scanner inFile;
        inFile = new Scanner(grades); //Setting up to read the file
            //For some reason this doesn't report error when you add throws FileNotFoundException in method
        
        int passingInc = 0, failingInc = 0, validInc = 0, errorData = 0, dataCount = 0; //All incremental usage
        char current; //what will be used as our shift holder
        
        //Input
        
        //Calculation
        System.out.print("Erroneous Characters: ");
        while (inFile.hasNext()) { //Will read through each character until there is no characters left on the line
            current = inFile.next().charAt(0);
            
            switch(current) {
                case 'a':
                    passingInc++; //Raises number of passing grades
                    validInc++; //Raises number of valid grades
                    dataCount++; //Raises number of data entries
                    break;
                case 'b':
                    passingInc++;
                    validInc++;
                    dataCount++;
                    break;
                case 'c':
                    passingInc++;
                    validInc++;
                    dataCount++;
                    break;
                case 'd':
                    failingInc++; //Raisees number of failing grades
                    validInc++;
                    dataCount++;
                    break;
                case 'f':
                    failingInc++;
                    validInc++;
                    dataCount++;
                    break;
                default:
                    System.out.print(current + "  "); //prints out the extreneous entry
                    errorData++; //Raises number of invalid data entries
                    dataCount++;
                    break;
            }
        }
        
        //Output
        System.out.println("\nThere were " + passingInc + " passing grades.");
        System.out.println("There were " + failingInc + " failing grades.");
        System.out.println("There were " + validInc + " valid grades.");
        System.out.println("There were a total of " + dataCount + " characters in the file."); 
        
        
        System.out.println("End of Program.");
    } //End of Main
    
} //End of Program

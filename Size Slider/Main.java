//This code was created for displaying shirking sizes after "n" turns in an organized manner.
/*

The user will enter in the
    1) height
    2) growth/shrink choice
    3) rapidness of change (in percentage)

*/


//Author: Laolu Ade
//Date: 05/22/22

import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;


public class Main {
    private final static DecimalFormat df4 = new DecimalFormat("#.0000");

    public static void main(String[] args) {
        //DECLARATION
        Scanner input = new Scanner(System.in); //User input
        short feet, inches;
        double height; //Starting height
        char direction; //Growing or shrinking?
        short severity; //How extreme the growth will be
        short turn_number; //Expected amount of turns
        short bound; //Impacts severity of the curve upward

        //INPUT
        System.out.println("Welcome to the height adjusting tool!");
        System.out.println("Looking to change your stature for a few rounds? Look no further!");
        System.out.println("First, you will be entering your height in two parts. First feet, then the inches.");
        
        System.out.print("Please enter how many feet you are: ");
        feet = input.nextShort();
        
        System.out.print("Please enter how many inches you are: ");
        inches = input.nextShort();
        
        height = (feet + (inches * 0.08333));

        System.out.print("\nPlease enter the letter for shrinking [S] or Growing [G]: ");

        direction = input.next().charAt(0);

        if (direction != 'S' && direction != 'G') {
            if (direction != 's' && direction != 'g') {
                System.out.print("Wrong character. Start over.");
                System.exit(0);
            }
        }

        System.out.print("\nNow, please enter how many turns or times you expect to change: ");

        turn_number = input.nextShort();

        System.out.println("Next, you will be enter how slight or extreme your growth will be.");
        System.out.println("When prompted, type a number from 1 to 100. The higher the number, the faster the growth is.");
        System.out.print("Enter your number here: ");

        severity = input.nextShort();

        System.out.println("\nLastly, you are entering how much of a curve you want there to be. The bigger the range, the wilder the curve.");
        System.out.print("I suggest the number stay under 20. Default option is 5.\n" + "Enter your number here: ");
        bound = input.nextShort();

        if (severity < 0 || severity > 101) {
            System.out.print("Outside of range. Start over.");
            System.exit(0);
        }

        System.out.println("\nStarting Height is " + height + ".");
        if (direction == 'S' || direction == 's') {
            System.out.println("You will be shrinking today.");
        }
        else if (direction == 'G' || direction == 'g') {
            System.out.println("You will be growing today.");
        }
        System.out.println("You expect all of this to take " + turn_number + " turns.\n" +
                "Severity will be " + severity + "%.\nRange will be increased by " + bound + " up and down.\n--------------------------");

        //CALCULATION
        ArrayList<Float> newHeights = new ArrayList<>(); //creating array for numbers
        double actualPercGrowth = (severity * 0.004); //The real growth percentage

        float uBound = (float) (actualPercGrowth + (bound * 0.01)); //Creating upper bound
        float lBound = (float) (actualPercGrowth - (bound * 0.01)); //Creating lower bound

        if (lBound < 0) {
            lBound = 0; //below zero check for lower bound
        }

        float boundIncrease = (uBound - lBound) / turn_number; //increasing the percentage each turn for more extreme change
        actualPercGrowth = lBound; //move lBound's value into APG
        float iterheight = (float)(height); //create variable for new height

        for (short i = 0; i < turn_number; i++) { //going through each turn.
            if (direction == 'G' || direction == 'g') {
                iterheight = (float) (iterheight + (iterheight * actualPercGrowth)); //Add the height
            }
            else if (direction == 'S' || direction == 's') {
                iterheight = (float) (iterheight - (iterheight * actualPercGrowth)); //Subtract the height
            }

            newHeights.add(iterheight);
            actualPercGrowth = actualPercGrowth + boundIncrease; //Increase the bound for next turn.
        }

        //OUTPUT 1
        char choice;
        while (true) {
            System.out.println("Original Height: " + height + "ft");
            System.out.println("Ending Height: " + df4.format(newHeights.get(newHeights.size() - 1)) + "ft");
            System.out.print("Do you wish to add five more turns? (Beware, this is outside mapped territory)" + "\nEnter Y or n: ");

            choice = input.next().charAt(0);
            if (choice == 'n') {
                break;
            }
            else {
                for (int i = 0; i < 5; i++) { //going through each turn.
                    if (direction == 'G' || direction == 'g') {
                        iterheight = (float) (iterheight + (iterheight * actualPercGrowth)); //Add the height
                    }
                    else if (direction == 'S' || direction == 's') {
                        iterheight = (float) (iterheight - (iterheight * actualPercGrowth)); //Subtract the height
                    }

                    newHeights.add(iterheight);
                    actualPercGrowth = actualPercGrowth + boundIncrease; //Increase the bound for next turn.
                }
            }
        }

        //OUTPUT 2
        for (int i = 0; i < newHeights.size(); i++) {
            System.out.println("Turn " + (i + 1) + ": " + df4.format(newHeights.get(i)) + " feet");
        }

    } //End of main
}
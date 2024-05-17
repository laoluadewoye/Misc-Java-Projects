import java.util.Scanner;
import java.lang.Math;

public class MineSweeper {
    public static void main(String[] args) {
        int userX, userY, choice, addon;
        int rows = 10;
        int columns = 10;
        int count = 0;
        double PERCENTZERO = 0.75;

        System.out.println("Welcome to Minesweeper Lite! Hit spots will be represented by an 'X'");
        System.out.println("Generating 10x10 grid...");
        int[][] mineSweepMap = new int[rows][columns];

        //Population
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                double testNum = Math.random();
                if (testNum > PERCENTZERO) {
                    mineSweepMap[row][col] = 1;
                }
                else {
                    mineSweepMap[row][col] = 0;
                }
            }
        }

        //Gameplay
        do {
            printMSM(mineSweepMap);
            Scanner input = new Scanner(System.in);

            System.out.println("Enter which spot you wish to blow up.");

            System.out.print("Enter an x-coordinate (number from 1-10): ");
            userX = input.nextInt() - 1;
            System.out.print("Enter an y-coordinate (number from 1-10): ");
            userY = input.nextInt() - 1;

            addon = checkMSM(mineSweepMap, userX, userY);
            count += addon;
            System.out.println("The score from hitting spot (" + (userX + 1) + ", " + (userY + 1) + ") is " + addon + ".");
            System.out.println("The score is now " + count + ".");

            System.out.print("Do you wish to keep going? (1 for Yes, 0 for No): ");
            choice = input.nextInt();
        } while (choice == 1);
    }

    public static void printMSM(int[][] mineSweepMap) {
        System.out.println("     1   2   3   4   5   6   7   8   9  10");
        System.out.println("   |---------------------------------------|");
        for (int row = 0; row < mineSweepMap.length; row++) {
            if (row < 9) {
                System.out.print(" ");
            }
            System.out.print((row + 1) + " | ");
            for (int col = 0; col < mineSweepMap[row].length; col++) {
                if (mineSweepMap[row][col] == -1) {
                    System.out.print("X | ");
                } else {
                    System.out.print(mineSweepMap[row][col] + " | ");
                }
            }
            System.out.println("\n   |---------------------------------------|");
        }
    }

    public static int checkMSM(int[][] mineSweepMap, int xCoor, int yCoor) {
        int LOWERBOUND = 0;
        int UPPERBOUND_Y = mineSweepMap.length-1;
        int UPPERBOUND_X = mineSweepMap[0].length-1;
        if (yCoor > UPPERBOUND_Y || yCoor < LOWERBOUND || xCoor > UPPERBOUND_X || xCoor < LOWERBOUND) {
            return 0;
        }
        else if (mineSweepMap[yCoor][xCoor] == 0) {
            mineSweepMap[yCoor][xCoor] = -1;
            return 1;
        }
        else if (mineSweepMap[yCoor][xCoor] == 1) {
            mineSweepMap[yCoor][xCoor] = -1;
            int upScore = checkMSM(mineSweepMap, xCoor, yCoor-1);
            int downScore = checkMSM(mineSweepMap, xCoor, yCoor+1);
            int leftScore = checkMSM(mineSweepMap, xCoor-1, yCoor);
            int rightScore = checkMSM(mineSweepMap, xCoor+1, yCoor);
            return 1 + upScore + downScore + leftScore + rightScore;
        }
        else {
            return 0;
        }
    }
}

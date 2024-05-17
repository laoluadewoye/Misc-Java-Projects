import java.util.concurrent.TimeUnit;

/**
 * <h1>Class: Location</h1>
 * <p>This class holds three grids per player for display during the game.</p>
 * <p></p>
 * <b>Variables:</b>
 * <p>Own - grid object for player to see their own board</p>
 * <p>Enemy - grid object for player to perform checks against the enemy board</p>
 * <p>EnemyProgress - grid object for player to see the progress against the enemy</p>
 */
public class Location {
    //Instance variables
    final private Grid Own = new Grid();
    final private Grid Enemy = new Grid();
    final private Grid EnemyProgress = new Grid();
    private boolean tempSuccess = false;

    //Only prints Player's own grid
    public void printOwnGrid() {
        this.Own.printGridDisplay();
    }

    public String[][][] getOwnGrid() {
        return this.Own.getGrid();
    }

    public String[][][] getEPGrid() { return this.EnemyProgress.getGrid(); }

    public void printProgressGrid() {
        this.EnemyProgress.printGridDisplay();
    }

    public void setEnemyGrid(String[][][] enemy) {
        this.Enemy.setGrid(enemy);
    }

    /**
     * <h2>Method: printTurn</h2>
     * <p>This method uses clearConsole(), printOwnGrind(), and printProgressGrid() from this class.</p>
     * <p>A seven second delay is used for the players to see the results of each turn before it moves on
     * to the other player</p>
     * @throws InterruptedException Used within the call stack to safely use the TimeUnit class
     */
    public void printTurn() throws InterruptedException {
        TimeUnit.SECONDS.sleep(7);

        clearConsole();

        System.out.println("YOUR MAP");
        printOwnGrid();

        System.out.println("\nYOUR OPPONENT'S MAP (SO FAR)");
        printProgressGrid();
    }

    /**
     * <h2>Method: printTurn</h2>
     * <p>This method uses clearConsole(), printOwnGrind(), and printProgressGrid() from this class.</p>
     * <p>A nth millisecond delay is used to move at a reasonably fast pace through a CPU game.</p>
     * @throws InterruptedException Used within the call stack to safely use the TimeUnit class
     */
    public void printTurn(int num) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(num);

        clearConsole();

        System.out.println("YOUR MAP");
        printOwnGrid();

        System.out.println("\nYOUR OPPONENT'S MAP (SO FAR)");
        printProgressGrid();
    }

    /**
     * <h2>Method: clearConsole</h2>
     * <p>This method prints 200 blank lines to simulate a clear screen.</p>
     */
    public final void clearConsole() {
        for (int i = 0; i < 200; i++) {
            System.out.println();
        }
    }

    /**
     * <h2>Method: testPlacement</h2>
     * <p>This method is used by locationCheck() method in the Player class.</p>
     * <p>This method takes the placement coordinates and direction and runs a test to see if the ship's position
     * ends up out of bounds.</p>
     * @param letter Vertical coordinates
     * @param num Horizontal coordinates
     * @param dir Direction ship is facing
     * @param length Length of ship
     * @return Whether the ship can be generated or not
     */
    public boolean testPlacement(char letter, int num, String dir, int length) {
        boolean finalVerdict = false;

        //For checking letters
        final char STARTING_LETTER = 'A';
        final int ASCII_OF_A = STARTING_LETTER;
        final int TOP_BOUNDARY_VERT = ASCII_OF_A + 10;
        final int TOP_BOUNDARY_HORI = 10;
        final int LOW_BOUNDARY_HORI = 1;
        int asciiLetter = letter;

        //Basic check to see if spot exists
        boolean letterExists = false, numExists = false;
        if (asciiLetter < TOP_BOUNDARY_VERT && asciiLetter >= ASCII_OF_A) {
            letterExists = true;
        }
        if (num >= LOW_BOUNDARY_HORI && num <= TOP_BOUNDARY_HORI) {
            numExists = true;
        }

        //Second check to see if other spots exist. Direction specific.
        boolean canFit = false;
        switch (dir) {
            case "right":
                if ((num + length - 1) <= TOP_BOUNDARY_HORI) {
                    canFit = true;
                }
                break;
            case "left":
                if ((num - (length - 1) >= LOW_BOUNDARY_HORI)){
                    canFit = true;
                }
                break;
            case "up":
                if ((asciiLetter - (length - 1)) >= STARTING_LETTER) {
                    canFit = true;
                }
                break;
            case "down": //FIX THIS CHECK
                if ((asciiLetter + length - 1) < TOP_BOUNDARY_VERT) {
                    canFit = true;
                }
                break;
            default:
                canFit = false;
                break;
        }

        //Updated boolean if pass
        if (letterExists && numExists && canFit) {
            finalVerdict = true;
        }

        return finalVerdict;
    }

    /**
     * <h2>Method: testFree</h2>
     * <p>This method is called by the freeCheck method in the Player class.</p>
     * <p>The method uses the placement and PGD() methods from the Grid class.</p>
     * <p>This method uses the direction variable to choose one placement method to edit the board. This
     * method also returns a boolean confirming if placement was successful.</p>
     * @param letter Vertical coordinates
     * @param arrow Tail-end symbol
     * @param num Horizontal coordinates
     * @param dir Ship Direction
     * @param length Ship Length
     * @return Success status
     */
    public boolean testFree(char letter, char arrow, int num, String dir, int length) {
        boolean finalVerdict = false;
        if (dir == "right") {
            finalVerdict = this.Own.placementRight(letter, arrow, num, length);
        }
        else if (dir == "left") {
            finalVerdict = this.Own.placementLeft(letter, arrow, num, length);
        }
        else if (dir == "up") {
            finalVerdict = this.Own.placementUp(letter, arrow, num, length);
        }
        else if (dir == "down") {
            finalVerdict = this.Own.placementDown(letter, arrow, num, length);
        }

        //Prints board result
        this.Own.printGridDisplay();
        return finalVerdict;
    }

    /**
     * <h2>Method: checkEnemyStrike</h2>
     * <p>This method uses placeStrike(), forcePlaceStrike(), and forcePlaceMiss()</p>
     * <p>This method is used for seeing if a spot on the Enemy's grid is occupied by a ship. If so, a strike
     * is placed down to make a hit.</p>
     * @param letter Vertical coordinates
     * @param number Horizontal coordinates
     * @return Whether the method was able to place down a strike
     */
    public boolean checkEnemyStrike(char letter, int number) {
        boolean finalVerdict;

        finalVerdict = this.Enemy.placeStrike(letter, number);

        if (finalVerdict){
            this.EnemyProgress.forcePlaceStrike(letter, number);
        }
        else {
            this.EnemyProgress.forcePlaceMiss(letter, number);
        }

        //Pass into tempSuccess
        setTempSuccess(finalVerdict);

        return finalVerdict;
    }

    /**
     * <h2>Method: setSunkVisuals</h2>
     * <p>This method uses the placeSunk() method from the Grid class to set add the sunk symbol to the desired
     * location.</p>
     * @param letter Vertical coordinates
     * @param number Horizontal coordinates
     */
    public void setSunkVisuals(String letter, String number) {
        this.Own.placeSunk(letter, number);
    }

    /**
     * <h2>Method: getOwnTempCoordinates</h2>
     * <p>This method retrieves the temporary coordinates from the Grid class holding to send them up
     * the stack here.</p>
     * @return 2D Array of coordinates
     */
    public String[][] getOwnTempCoordinates() {
        return this.Own.getTempCoordinates();
    }

    public void setTempSuccess(boolean tempSuccess) {
        this.tempSuccess = tempSuccess;
    }

    public boolean getTempSuccess() {
        return this.tempSuccess;
    }
}
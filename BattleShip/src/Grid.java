import java.util.Arrays;

/**
 * <h1>Class: Grid</h1>
 * <p>This class controls any methodality that deals with modifying and displaying battleship boards.</p>
 * <p></p>
 * <b>Variables:</b>
 * <p>gridActual - A 3D Array that stores numerous information within each cell</p>
 * <p>tempCoorHold - A 2D Array that temporarily holds coordinates to transfer up the call stack</p>
 */
public class Grid {
    //Instance variables
    private String[][][] gridActual = new String[10][11][3];
    private String[][] tempCoorHold;

    /**
     * <h2>Constructor: Grid</h2>
     * <p>This initializes the object and fills the grid with the necessary coordinates to utilize them.</p>
     */
    public Grid() {
        populateGrid(this.gridActual);
    }

    /**
     * <h2>Method: populateGrid</h2>
     * <p>This method iterates through all three dimensions of the grid, populating all indexes with coordinates</p>
     * @param grid 3D grid array
     */
    public static void populateGrid(String[][][] grid) {

        //Initialize label beginnings
        final int ASCII_OF_A = 'A';
        final int STARTING_NUMBER = 1;

        //Vertical lines
        for (int vert = 0; vert < grid.length; vert++) {

            //Vertical Letter Enumeration
            int tempNum = ASCII_OF_A + vert;
            char tempLabel = (char)tempNum;
            Arrays.fill(grid[vert][0], Character.toString(tempLabel));

            //Horizontal Number + Full-Grid Enumeration
            for (int hori = STARTING_NUMBER; hori < grid[vert].length; hori++) {
                grid[vert][hori][0] = Integer.toString(hori);
                grid[vert][hori][1] = grid[vert][0][0] + hori;
            }
        }
    }

    /**
     * <h2>Method: printGridDisplay</h2>
     * <p>This method prints a command line grid with all the current values inside each spot.</p>
     */
    public void printGridDisplay() {
        //Retrieving grid
        String[][][] grid = getGrid();

        System.out.println("\n" + "   -----------------------------------------");
        for (int vert = 0; vert < grid.length; vert++) {
            //Labeling
            System.out.print(" " + grid[vert][0][0] + " ");

            //Printing one line on grid
            for (int hori = 1; hori < grid[vert].length; hori++) {
                String temp;

                //Null check for initial generation
                if (grid[vert][hori][2] == null) {
                    grid[vert][hori][2] = " ";
                }
                temp = grid[vert][hori][2];
                System.out.print("| " + temp + " ");
            }

            //Moves to next line with horizontal line
            System.out.println("|\n" + "   -----------------------------------------");
        }
        System.out.println("     1   2   3   4   5   6   7   8   9   10");
    }

    /**
     * <h2>Method: placementRight</h2>
     * <p>This method uses the getGrid() and setTempCoordinates() methods in this class.</p>
     * <p>This method modifies the grid in the right direction to place the ships down. If unable to
     * it will return false, else, it returns true.</p>
     * <p>This method also modifies the coordinate hold to send back up the stack.</p>
     * @param letter Starting Vertical Coordinate
     * @param arrow Ship end symbol
     * @param num Starting Horizontal Coordinate
     * @param length Length of ship
     * @return Whether attempt to place ship down was successful
     */
    public boolean placementRight(char letter, char arrow, int num, int length) {
        boolean verdict = false;
        boolean nullCheck = true, letterCheck = false;
        String[][][] grid = getGrid();
        String[][] coorToTransfer = new String[length][3];
        final String INIT_STATUS = "Not Hit";
        int index;

        //Iterates through grid for nullCheck and letterCheck
        for (int vert = 0; vert < grid.length; vert++) {
            String vertLabel = grid[vert][0][0];
            letterCheck = (vertLabel.charAt(0) == letter);

            //If letterCheck passes, bother to do nullCheck.
            if (letterCheck) {
                for (index = 0; index < length; index++) {
                    String spot = grid[vert][num + index][2];
                    if (spot == null) {
                        System.out.print("");
                    }
                    else if (spot.charAt(0) != ' ') {
                        nullCheck = false;
                    }
                }

                //If null Check passes, finally update the board
                if (nullCheck) {

                    //Send data to transfer
                    for (index = 0; index < coorToTransfer.length; index++) {
                        coorToTransfer[index][0] = Character.toString(letter);
                        coorToTransfer[index][1] = Integer.toString(num + index);
                        coorToTransfer[index][2] = INIT_STATUS;
                    }
                    setTempCoordinates(coorToTransfer);

                    //Final Display
                    verdict = true;
                    grid[vert][num][2] = Character.toString(arrow);
                    for (index = 1; index < (length - 1); index++) {
                        grid[vert][num + index][2] = "O";
                    }
                    grid[vert][num + index][2] = "@";
                }
            }
        }

        return verdict;
    }

    /**
     * <h2>Method: placementLeft</h2>
     * <p>This method uses the getGrid() and setTempCoordinates() methods in this class.</p>
     * <p>This method modifies the grid in the left direction to place the ships down. If unable to
     * it will return false, else, it returns true.</p>
     * <p>This method also modifies the coordinate hold to send back up the stack.</p>
     * @param letter Starting Vertical Coordinate
     * @param arrow Ship end symbol
     * @param num Starting Horizontal Coordinate
     * @param length Length of ship
     * @return Whether attempt to place ship down was successful
     */
    public boolean placementLeft(char letter, char arrow, int num, int length) {
        boolean verdict = false;
        boolean nullCheck = true, letterCheck = false;
        String[][][] grid = getGrid();
        String[][] coorToTransfer = new String[length][3];
        final String INIT_STATUS = "Not Hit";
        int index;

        //Iterates through grid for nullCheck and letterCheck
        for (int vert = 0; vert < grid.length; vert++) {
            String vertLabel = grid[vert][0][0];
            letterCheck = (vertLabel.charAt(0) == letter);

            //If letterCheck passes, bother to do nullCheck.
            if (letterCheck) {
                for (index = 0; index < length; index++) {
                    String spot = grid[vert][num - index][2];
                    if (spot == null) {
                        System.out.print("");
                    }
                    else if (spot.charAt(0) != ' ') {
                        nullCheck = false;
                    }
                }

                //If null Check passes, finally update the board
                if (nullCheck) {
                    //Ship coordinate transfer
                    for (index = 0; index < coorToTransfer.length; index++) {
                        coorToTransfer[index][0] = Character.toString(letter);
                        coorToTransfer[index][1] = Integer.toString(num - index);
                        coorToTransfer[index][2] = INIT_STATUS;
                    }
                    setTempCoordinates(coorToTransfer);

                    //Final display change
                    verdict = true;
                    grid[vert][num][2] = Character.toString(arrow);
                    for (index = 1; index < (length - 1); index++) {
                        grid[vert][num - index][2] = "O";
                    }
                    grid[vert][num - index][2] = "@";
                }
            }
        }

        return verdict;
    }

    /**
     * <h2>Method: placementUp</h2>
     * <p>This method uses the getGrid() and setTempCoordinates() methods in this class.</p>
     * <p>This method modifies the grid in the up direction to place the ships down. If unable to
     * it will return false, else, it returns true.</p>
     * <p>This method also modifies the coordinate hold to send back up the stack.</p>
     * @param letter Starting Vertical Coordinate
     * @param arrow Ship end symbol
     * @param num Starting Horizontal Coordinate
     * @param length Length of ship
     * @return Whether attempt to place ship down was successful
     */
    public boolean placementUp(char letter, char arrow, int num, int length) {
        boolean isEmpty = true;
        boolean verdict = false;
        String[][][] grid = getGrid();
        String[][] coorToTransfer = new String[length][3];
        char[] letterTrack = new char[length];
        final String INIT_STATUS = "Not Hit";
        final int TOP_BOUND_VERT_LET = 'J';
        final int TOP_BOUND_VERT_INDEX = 9;
        int asciiLetter = letter;
        int index;

        //Store the characters
        for (index = 0; index < letterTrack.length; index++) {
            letterTrack[index] = (char)(asciiLetter - index);
        }

        //Pre-establish ship coordinates
        for (index = 0; index < coorToTransfer.length; index++) {
            coorToTransfer[index][0] = Character.toString(letterTrack[index]);
            coorToTransfer[index][1] = Integer.toString(num);
            coorToTransfer[index][2] = INIT_STATUS;
        }

        //Check for seeing free spots
        for (index = 0; index < letterTrack.length; index++) {
            int tempIndex = TOP_BOUND_VERT_INDEX - (TOP_BOUND_VERT_LET - (int)(letterTrack[index]));
            String spot = grid[tempIndex][num][2];
            if (spot == null) {
                System.out.print("");
            }
            else if (spot != " ") {
                isEmpty = false;
            }
        }

        //Only do visual changes and coordinate transfer if everything passes
        if (isEmpty) {
            index = 0;
            int tempIndex = TOP_BOUND_VERT_INDEX - (TOP_BOUND_VERT_LET - (int)(letterTrack[index]));
            grid[tempIndex][num][2] = Character.toString(arrow);

            for (index = 1; index < letterTrack.length - 1; index++) {
                tempIndex = TOP_BOUND_VERT_INDEX - (TOP_BOUND_VERT_LET - (int)(letterTrack[index]));
                grid[tempIndex][num][2] = "O";
            }
            grid[tempIndex - 1][num][2] = "@";

            verdict = true;
            setTempCoordinates(coorToTransfer);
        }

        return verdict;
    }

    /**
     * <h2>Method: placementDown</h2>
     * <p>This method uses the getGrid() and setTempCoordinates() methods in this class.</p>
     * <p>This method modifies the grid in the down direction to place the ships down. If unable to
     * it will return false, else, it returns true.</p>
     * <p>This method also modifies the coordinate hold to send back up the stack.</p>
     * @param letter Starting Vertical Coordinate
     * @param arrow Ship end symbol
     * @param num Starting Horizontal Coordinate
     * @param length Length of ship
     * @return Whether attempt to place ship down was successful
     */
    public boolean placementDown(char letter, char arrow, int num, int length) {
        boolean isEmpty = true;
        boolean verdict = false;
        String[][][] grid = getGrid();
        String[][] coorToTransfer = new String[length][3];
        char[] letterTrack = new char[length];
        final String INIT_STATUS = "Not Hit";
        final int TOP_BOUND_VERT_LET = 'J';
        final int TOP_BOUND_VERT_INDEX = 9;

        int asciiLetter = letter;
        int index;

        //Store the characters
        for (index = 0; index < letterTrack.length; index++) {
            letterTrack[index] = (char)(asciiLetter + index);
        }

        //Reverse populating ship coordinates
        for(index = (coorToTransfer.length - 1); index >= 0; index--) {
            coorToTransfer[index][0] = Character.toString(letterTrack[index]);
            coorToTransfer[index][1] = Integer.toString(num);
            coorToTransfer[index][2] = INIT_STATUS;
        }

        //Check for seeing free spots
        //Essentially keeping the same code since I did
        for (index = 0; index < letterTrack.length; index++) {
            //temp index is going to be the number derived from
            int asciiDifference = TOP_BOUND_VERT_LET - (int)(letterTrack[index]);
            int tempIndex = TOP_BOUND_VERT_INDEX - asciiDifference;
            String spot = grid[tempIndex][num][2];
            if (spot == null) {
                System.out.print("");
            }
            else if (spot != " ") {
                isEmpty = false;
            }
        }

        //If pass, only then confirm changes
        if (isEmpty) {
            index = 0;
            int asciiDifference = TOP_BOUND_VERT_LET - (int)(letterTrack[index]);
            int tempIndex = TOP_BOUND_VERT_INDEX - asciiDifference;
            grid[tempIndex][num][2] = Character.toString(arrow);

            for (index = 1; index < letterTrack.length - 1; index++) {
                tempIndex = TOP_BOUND_VERT_INDEX - (TOP_BOUND_VERT_LET - (int)(letterTrack[index]));
                grid[tempIndex][num][2] = "O";
            }
            grid[tempIndex + 1][num][2] = "@";

            verdict = true;
            setTempCoordinates(coorToTransfer);
        }

        return verdict;
    }

    /**
     * <h2>Method: placeStrike</h2>
     * <p>This method uses the getGrid() method in this class.</p>
     * <p>This method is used after all confirmations to finally place the X down with one last check.</p>
     * @param letter Vertical strike coordinate
     * @param number Horizontal strike coordinate
     * @return Whether the strike was successful
     */
    public boolean placeStrike(char letter, int number) {
        boolean verdict = false;
        boolean squareExists = false;
        boolean letterCheck, numberCheck;
        int vertPersist = 0;
        int horiPersist = 0;
        String[][][] grid = getGrid();
        String strike = "X";
        String symbol;

        //Find location of desired spot
        for (int vert = 0; vert < grid.length; vert++) {
            String vertLabel = grid[vert][0][0];
            letterCheck = (vertLabel.charAt(0) == letter);
            if (letterCheck) {
                vertPersist = vert;
                for (int hori = 1; hori < grid[vert].length; hori++) {
                    String horiLabel = grid[vert][hori][0];
                    numberCheck = (Integer.parseInt(horiLabel) == number);
                    if (numberCheck) {
                        horiPersist = hori;
                        squareExists = true;
                        break;
                    }
                }
            }
            if (squareExists) {
                break;
            }
        }

        //Check location for a valid symbol
        if (squareExists) {
            symbol = grid[vertPersist][horiPersist][2];

            switch (symbol) {
                case "@", "O", "A", "V", "<", ">" -> verdict = true;
            }
        }

        //Place strike down on enemy board
        if (verdict) {
            grid[vertPersist][horiPersist][2] = strike;
        }

        return verdict;
    }

    /**
     * <h2>Method: placeSunk</h2>
     * <p>This method uses the getGrid() method in this class.</p>
     * <p>This method is used after all confirmations to finally place the ! down.</p>
     * @param letter Vertical sunk coordinate
     * @param number Horizontal sunk coordinate
     */
    public void placeSunk(String letter, String number) {
        boolean squareExists = false;
        boolean letterCheck, numberCheck;
        int vertPersist = 0;
        int horiPersist = 0;
        String[][][] grid = getGrid();
        String dead = "!";

        //Find location of desired spot
        for (int vert = 0; vert < grid.length; vert++) {
            String vertLabel = grid[vert][0][0];
            letterCheck = (vertLabel.charAt(0) == letter.charAt(0));
            if (letterCheck) {
                vertPersist = vert;
                for (int hori = 1; hori < grid[vert].length; hori++) {
                    String horiLabel = grid[vert][hori][0];
                    numberCheck = (Integer.parseInt(horiLabel) == Integer.parseInt(number));
                    if (numberCheck) {
                        horiPersist = hori;
                        squareExists = true;
                        break;
                    }
                }
            }
            if (squareExists) {
                break;
            }
        }

        //Place sunk symbol (!) down on enemy board
        if (squareExists) {
            grid[vertPersist][horiPersist][2] = dead;
        }
    }

    /**
     * <h2>Method: forcePlaceStrike</h2>
     * <p>This method is used by the enemyProgress grid in the Location class.</p>
     * <p>This method uses the getGrid() method in this class.</p>
     * <p>This method is used to place a strike down. No check is done here.</p>
     * @param letter Vertical sunk coordinate
     * @param number Horizontal sunk coordinate
     */
    public void forcePlaceStrike(char letter, int number) {
        String strike = "X";
        boolean squareExists = false;
        boolean letterCheck, numberCheck;
        int vertPersist = 0;
        int horiPersist = 0;
        String[][][] grid = getGrid();

        //Find desired location
        for (int vert = 0; vert < grid.length; vert++) {
            String vertLabel = grid[vert][0][0];
            letterCheck = (vertLabel.charAt(0) == letter);
            if (letterCheck) {
                vertPersist = vert;
                for (int hori = 1; hori < grid[vert].length; hori++) {
                    String horiLabel = grid[vert][hori][0];
                    numberCheck = (Integer.parseInt(horiLabel) == number);
                    if (numberCheck) {
                        horiPersist = hori;
                        squareExists = true;
                        break;
                    }
                }
            }
            if (squareExists) {
                break;
            }
        }

        //No need to confirm space is there, place strike immediately
        grid[vertPersist][horiPersist][2] = strike;
    }

    /**
     * <h2>Method: forcePlaceMiss</h2>
     * <p>This method is used by the enemyProgress grid in the Location class.</p>
     * <p>This method uses the getGrid() method in this class.</p>
     * <p>This method is used to place a miss down. No check is done here.</p>
     * @param letter Vertical sunk coordinate
     * @param number Horizontal sunk coordinate
     */
    public void forcePlaceMiss(char letter, int number) {
        String miss = "?";
        boolean squareExists = false;
        boolean letterCheck, numberCheck;
        int vertPersist = 0;
        int horiPersist = 0;
        String[][][] grid = getGrid();

        //Find desired location
        for (int vert = 0; vert < grid.length; vert++) {
            String vertLabel = grid[vert][0][0];
            letterCheck = (vertLabel.charAt(0) == letter);
            if (letterCheck) {
                vertPersist = vert;
                for (int hori = 1; hori < grid[vert].length; hori++) {
                    String horiLabel = grid[vert][hori][0];
                    numberCheck = (Integer.parseInt(horiLabel) == number);
                    if (numberCheck) {
                        horiPersist = hori;
                        squareExists = true;
                        break;
                    }
                }
            }
            if (squareExists) {
                break;
            }
        }

        //No need to confirm space is there, place strike immediately
        grid[vertPersist][horiPersist][2] = miss;
    }

    public void setGrid(String[][][] enemy) { this.gridActual = enemy; }

    public String[][][] getGrid() { return this.gridActual; }

    public void setTempCoordinates(String[][] newCoor) { this.tempCoorHold = newCoor; }

    public String[][] getTempCoordinates() { return this.tempCoorHold; }
}
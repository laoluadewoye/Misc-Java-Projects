import java.util.Random;

/**
 * <h1>Class: Computer</h1>
 * <p>Child class that extends from the Player class.</p>
 * <p>This class adds functionality for CPUs on top of the behaviors the player has.</p>
 * <p></p>
 * <b>Variables:</b>
 * <p>random - An instance of the random class</p>
 * <p>unusedCoordinates - An array of coordinates for the CPU to randomly pick</p>
 * <p>numUnused - int variable holding how many free coordinates are left to try</p>
 */
public class Computer extends Player {

    final private Random random = new Random();
    private String[] unusedCoordinates = new String[100];
    private int numUnused = 100;

    //Add some intelligence to the AI
    private String[] possibleCoordinates = new String[0];
    private int numPossible = 0;
    private String lastAttack;
    //Add another String array to hold up to 8 other coordinates to look at around a successful hit
    //Add another int to state how many free coordinates surround the successful hit.
    //Have a coordinate check against the unused list to save time and energy. 10s would have to be converted to '!'s
    //Once one of those coordinates are used, mark it with "XX". If the entire mini-list is XX, skip over it and try again.
    //This will update over and over with a new list every time a choice is successful.
    //Could also add a third behavior that it can figure out direction if something was chosen directly above or below.

    /**
     * <h2>Constructor: Computer</h2>
     * <p>This method is what is used to create a computer class.</p>
     * @param compNum "i.e. Player 1, Player 2"
     */
    public Computer(int compNum) {
        this.shipAliveCount = 5;
        this.enemyShipAliveCount = 5;
        this.scoreCount = 0;
        this.playerNumber = compNum;
        this.playerView = new Location();

        generateShips();
        generateCoorList(this.unusedCoordinates);
    }

    /**
     * <h2>Method: generateCoorList</h2>
     * <p>This method populates the empty list with coordinates.</p>
     * @param coordinates Array to populate
     */
    public void generateCoorList(String[] coordinates) {
        int vertIndex = 0;
        int horiIndex = 0;
        char letter = 'A';
        int asciiLetter = letter;
        int number = 1;

        for (int i = 0; i < coordinates.length; i++) {
            letter = (char) (asciiLetter + vertIndex);
            if (number == 10) {
                coordinates[i] = Character.toString(letter) + "!"; //Special way to denote 10
            }
            else {
                coordinates[i] = Character.toString(letter) + Integer.toString(number);
            }
            number++;
            if (number == 11) {
                number = 1;
                vertIndex++;
            }
        }
    }

    /**
     * <h2>Method: getSpot</h2>
     * <p>Retrieves the coordinate for use.</p>
     * @param coorList The array of coordinates to use
     * @param index The randomly generated number to use
     * @return A coordinate to attack
     */
    public static String getSpot(String[] coorList, int index) {
        String square = coorList[index];
        return square;
    }

    /**
     * <h2>Method: removeSpot</h2>
     * <p>This method takes the current list of coordinates and returns a list with the last used attack removed.</p>
     * @param coorList The current list of coordinates
     * @param spot The coordinate that was last used
     * @return The current list minus the last used coordinate
     */
    public static String[] removeSpot(String[] coorList, String spot) {
        if (spot == null) {
            System.out.println();
        }


        String[] newCoorList = new String[coorList.length - 1];
        int index;
        int newIndex = 0;
        for (index = 0; index < newCoorList.length; index++) {
            //boolean letterCheck = coorList[index].charAt(0) == spot.charAt(0);
            //boolean numberCheck = coorList[index].charAt(1) == spot.charAt(1);
            if (!(spot.equals(coorList[index])) || spot == null) {
                newCoorList[newIndex] = coorList[index];
                newIndex++;
            }
        }
        return newCoorList;
    }

    /**
     * <h2>Method: selectSquare</h2>
     * <p>This method uses getNumUnused(), setNumUnused(), getSpot(), and removeSpot() from this class.</p>
     * <p>This method automatically chooses a square to select using the random instance, then updates the list
     * values accordingly.</p>
     * @return Returns a coordinate to attempt
     */
    public String selectSquare() {
        //Randomly generated integer
        int count = getNumUnused();
        int choice = random.nextInt(count);
        if (choice == count - 1) {
            choice--;
        }

        //Retrieve String at index
        String square = getSpot(this.unusedCoordinates, choice);

        //Remove string from future lists
        this.unusedCoordinates = removeSpot(this.unusedCoordinates, square);
        setNumUnused(count - 1);

        return square;
    }

    public String selectSquareDemo() {
        String square = "";
        int count = getNumUnused();

        //Get if the last turn was a strike
        boolean previousSuccess = playerView.getTempSuccess();

        if (previousSuccess || this.possibleCoordinates.length > 2) {
            square = smartSelection();
        }
        else if (previousSuccess && this.possibleCoordinates.length < 2) {
            this.possibleCoordinates = smartGen();
            square = smartSelection();
        }
        else {
            //Randomly generated integer
            int choice = random.nextInt(count);
            if (choice == count - 1 && choice != 0) {
                choice--;
            }

            //Retrieve String at index
            square = getSpot(this.unusedCoordinates, choice);
        }
        //Remove string from future lists
        this.unusedCoordinates = removeSpot(this.unusedCoordinates, square);
        setNumUnused(count - 1);

        return square;
    }

    public String smartSelection() {
        String choice = "A1";
        for (String spot : this.unusedCoordinates) {
            if (spot == null) {
                continue;
            }
            for (int i = 0; i < this.possibleCoordinates.length; i++) {
                String smartSpot = this.possibleCoordinates[i];
                boolean letterCheck = spot.charAt(0) == smartSpot.charAt(0);
                boolean numberCheck = spot.charAt(1) == smartSpot.charAt(1);
                if (letterCheck && numberCheck) {
                    choice = spot;
                    boolean noMiss = checkMiss(choice);
                    if (noMiss) {
                        return choice;
                    }
                }
            }
        }
        return choice;
    }

    public boolean checkMiss(String spot) {
        boolean noMiss = true;
        boolean letterCheck, numberCheck;
        boolean squareExists = false;
        String[][][] progressGrid = playerView.getEPGrid();
        String symbol;
        char letter;
        int number;
        int vertPersist = 0;
        int horiPersist = 0;

        //Coordinate parsing
        letter = spot.charAt(0);
        char numberC = spot.charAt(1);
        if (numberC == '!') {
            number = 10;
        }
        else {
            number = Character.getNumericValue(numberC);
        }

        //Find desired location
        for (int vert = 0; vert < progressGrid.length; vert++) {
            String vertLabel = progressGrid[vert][0][0];
            letterCheck = (vertLabel.charAt(0) == letter);
            if (letterCheck) {
                vertPersist = vert;
                for (int hori = 1; hori < progressGrid[vert].length; hori++) {
                    String horiLabel = progressGrid[vert][hori][0];
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

        //Final check
        symbol = progressGrid[vertPersist][horiPersist][2];
        if (symbol == "?") {
            noMiss = false;
        }

        return noMiss;
    }

    public String[] smartGen() {
        String lastSquare = this.lastAttack;
        String[] possCoor;
        int number;

        //Parsing coordinates
        char lastLetterCPU = lastSquare.charAt(0);
        char lastNumberCPU = lastSquare.charAt(1);
        if (lastNumberCPU == '!') {
            number = 10;
        }
        else {
            number = Character.getNumericValue(lastNumberCPU);
        }

        possCoor = buildPossibleCoordinates(lastLetterCPU, number);
        return possCoor;
    }

    public String[] buildPossibleCoordinates(char letter, int number) {
        String[] newTest;
        int newTestSize;
        int asciiLetter = letter;
        char aboveLetter = (char)(asciiLetter - 1); //Previous Letter
        char belowLetter = (char)(asciiLetter + 1); //Later Letter
        String numberS;
        String leftNumber;
        String rightNumber;

        //Check for 10s and 1s
        if (number == 10) {
            numberS = "!";
            leftNumber = Integer.toString(number - 1);
            rightNumber = "";
        }
        else if (number == 1) {
            numberS = Integer.toString(number);
            leftNumber = "";
            rightNumber = Integer.toString(number + 1);
        }
        else {
            numberS = Integer.toString(number);
            leftNumber = Integer.toString(number - 1);
            if ((number + 1) == 10) {
                rightNumber = "!";
            }
            else {
                rightNumber = Integer.toString(number + 1);
            }
        }

        //Numerous cases for creating a smaller coordinate array
        if (letter == 'A' && number == 1) { //Top left corner
            newTestSize = 3;
            newTest = new String[newTestSize];
            newTest[0] = letter + rightNumber;
            newTest[1] = belowLetter + numberS;
            newTest[2] = belowLetter + rightNumber;
        }
        else if (letter == 'A' && number == 10) { //Top right corner
            newTestSize = 3;
            newTest = new String[newTestSize];
            newTest[0] = letter + leftNumber;
            newTest[1] = belowLetter + leftNumber;
            newTest[2] = belowLetter + numberS;
        }
        else if (letter == 'J' && number == 1) { //Bottom left corner
            newTestSize = 3;
            newTest = new String[newTestSize];
            newTest[0] = aboveLetter + numberS;
            newTest[1] = aboveLetter + rightNumber;
            newTest[2] = letter + rightNumber;
        }
        else if (letter == 'J' && number == 10) { //Bottom right corner
            newTestSize = 3;
            newTest = new String[newTestSize];
            newTest[0] = aboveLetter + rightNumber;
            newTest[1] = aboveLetter + numberS;
            newTest[2] = letter + rightNumber;
        }
        else if (number == 1) { //Left border spot
            newTestSize = 5;
            newTest = new String[newTestSize];
            newTest[0] = aboveLetter + numberS;
            newTest[1] = aboveLetter + rightNumber;
            newTest[2] = letter + rightNumber;
            newTest[3] = belowLetter + numberS;
            newTest[4] = belowLetter + rightNumber;
        }
        else if (number == 10) { //Right border spot
            newTestSize = 5;
            newTest = new String[newTestSize];
            newTest[0] = aboveLetter + leftNumber;
            newTest[1] = aboveLetter + numberS;
            newTest[2] = letter + leftNumber;
            newTest[3] = belowLetter + leftNumber;
            newTest[4] = belowLetter + numberS;
        }
        else if (letter == 'A') { //Top border spot
            newTestSize = 5;
            newTest = new String[newTestSize];
            newTest[0] = letter + leftNumber;
            newTest[1] = letter + rightNumber;
            newTest[2] = belowLetter + leftNumber;
            newTest[3] = belowLetter + numberS;
            newTest[4] = belowLetter + rightNumber;
        }
        else if (letter == 'J') { //Bottom border spot
            newTestSize = 5;
            newTest = new String[newTestSize];
            newTest[0] = aboveLetter + leftNumber;
            newTest[1] = aboveLetter + numberS;
            newTest[2] = aboveLetter + rightNumber;
            newTest[3] = letter + leftNumber;
            newTest[4] = letter + rightNumber;
        }
        else { //All other spots
            newTestSize = 8;
            newTest = new String[newTestSize];
            newTest[0] = aboveLetter + leftNumber;
            newTest[1] = aboveLetter + numberS;
            newTest[2] = aboveLetter + rightNumber;
            newTest[3] = letter + leftNumber;
            newTest[4] = letter + rightNumber;
            newTest[5] = belowLetter + leftNumber;
            newTest[6] = belowLetter + numberS;
            newTest[7] = belowLetter + rightNumber;
        }

        /*
        *
        *   0   0     0 1   0 1
        * 1 2   1 2     2   2
        *
        * 0 1 2   0 1   0 1
        * 3   4     2   2
        * 5 6 7   3 4   3 4
        *
        * 0   1   0 1 2
        * 2 3 4   3   4
        *
        */

        this.numPossible = newTestSize;
        return newTest;
    }

    public void setNumUnused(int num) {
        this.numUnused = num;
    }

    public int getNumUnused() {
        return this.numUnused;
    }

    public void setLastAttack(String lastAttack) {
        this.lastAttack = lastAttack;
    }
}
import java.util.Scanner;
import java.util.Random;

/**
 * <h1>Class: Player</h1>
 * <p>This class manages all attributes and methods of a player in the Battleship game.</p>
 * <p>Functions within this class control player setup, personal ship and location objects, and player actions.</p>
 * <p>Parent class to the Computer class.</p>
 * <p></p>
 * <b>Variables:</b>
 * <p>input - Scanner object used for user input</p>
 * <p>random - Random object used for PRNG</p>
 * <p>win - boolean type used for win condition</p>
 * <p>shipAliveCount - int type that keeps track of ships still alive in the game</p>
 * <p>enemyShipAliveCount - int type that keeps track of enemy's ships</p>
 * <p>scoreCount - int type that counts hits on enemy</p>
 * <p>playerNumber - int type that holds player number</p>
 * <p>shipTracker - Ship array type that allows iteration through all ships</p>
 * <p>playerView - Location object that holds multiple grids for player</p>
 * <p>lastLetter - place to keep last vertical coordinate used to be pushed upward for other player</p>
 * <p>lastNumber - place to keep last horizontal coordinate used to be pushed upward for other player</p>
 */
public class Player {
    //Instance variables
    final private Scanner input = new Scanner(System.in);
    final private Random random = new Random();
    protected boolean win = true;

    protected int shipAliveCount;
    protected int enemyShipAliveCount;
    protected int scoreCount; //For tracking hits on enemy
    protected int playerNumber; //Should only have 1 or 2
    protected Ship[] shipTracker;
    protected Location playerView;
    protected char lastLetter;
    protected int lastNumber;

    /**
     * <h2>Constructor: Player</h2>
     * <p>Default constructor for Player class.</p>
     * <p>Needed for having subclasses.</p>
     */
    public Player() {

    }

    /**
     * <h2>Constructor: Player</h2>
     * <p>Constructor utilized for program.</p>
     * <p>Initializes player object and ships associated with player.</p>
     * @param playerNum Number assigned to player (i.e. "player1")
     */
    public Player(int playerNum) {
        this.shipAliveCount = 5;
        this.enemyShipAliveCount = 5;
        this.scoreCount = 0;
        this.playerNumber = playerNum;
        this.playerView = new Location();

        generateShips();
    }

    /**
     * <h2>Method: generateShips</h2>
     * <p>This methods creates five new ship objects and places them in an iterative array.</p>
     */
    public void generateShips() {
        //Create ship objects
        Ship Ship1 = new Ship("destroyer");
        Ship Ship2 = new Ship("submarine");
        Ship Ship3 = new Ship("cruiser");
        Ship Ship4 = new Ship("battleship");
        Ship Ship5 = new Ship("carrier");

        // create array of ship objects
        this.shipTracker = new Ship[]{Ship1, Ship2, Ship3, Ship4, Ship5};
    }

    /**
     * <h2>Method: placeShips</h2>
     * <p>Method uses getDirection(), getArrow(), getShipLength(), getShipType(), getShipCoordinates(), and
     * setShipCoordinates() methods from the ship class.</p>
     * <p>Method uses locationCheck(), freeCheck(), and setShipCount() methods within this class.</p>
     * <p>Method uses getOwnTempCoordinates() method from location class.</p>
     * <p>The method uses the previously created array of ship objects to allow the player to manually set the
     * position of each ship, one by one. After it is done, the ship count is set to 5.</p>
     */
    protected void placeShips() {
        //Add coordinates for a deep check once and for all
        final int TOTAL_SHIP_LENGTH = 17;

        //each new coordinate array will have a letter, number, status, and the shipnumber
        String[][] allShipCoordinates = new String[TOTAL_SHIP_LENGTH][4];
        int ASCIndex = 0;

        //The starting populating
        for (Ship ship : this.shipTracker) {
            String[][] tempCoor = ship.getShipCoordinates();
            String tempType = ship.getShipType();
            for (int i = 0; i < tempCoor.length; i++) {
                allShipCoordinates[ASCIndex][0] = tempCoor[i][0]; //Letter
                allShipCoordinates[ASCIndex][1] = tempCoor[i][1]; //Number
                allShipCoordinates[ASCIndex][2] = tempCoor[i][2]; //Status
                allShipCoordinates[ASCIndex][3] = tempType; //Ship Type
                ASCIndex++;
            }
        }

        //Iterate through all five ships
        for (Ship ship : this.shipTracker) {
            char direction;
            int gridNumber, lenValue;
            char gridLetter, arrowValue;
            boolean coorCheck, locCheck, freeCheck;
            String dirValue, typeValue;

            while (true) {
                //Initial Prompt
                System.out.println("\n\n\nPlacing the " + ship.getShipType());
                System.out.println();

                //Direction Selection
                System.out.println("Which direction is your ship facing?");
                System.out.println("Right (1)\nLeft (2)\nUp (3)\nDown (4)");
                System.out.println("Enter number here: ");
                direction = input.next().charAt(0);

                if (direction == '1') {
                    ship.setDirection("right");
                    ship.setBackArrow('<');
                }
                else if (direction == '2') {
                    ship.setDirection("left");
                    ship.setBackArrow('>');
                }
                else if (direction == '3') {
                    ship.setDirection("up");
                    ship.setBackArrow('V');
                }
                else if (direction == '4') {
                    ship.setDirection("down");
                    ship.setBackArrow('A');
                }
                else {
                    System.out.println("\nPlease input the correct number. Case sensitive.");
                    continue;
                }

                //Square selection
                System.out.println("Choose the square to place your ship.");
                System.out.println("When you choose a square, type in a letter, then number using the board above.");
                System.out.println("Once you pick, it will place the ship starting from the back to the front.");

                System.out.print("Select a row here: ");
                gridLetter = input.next().charAt(0);

                System.out.print("Select a column here: ");
                gridNumber = input.nextInt();

                //Perform check before placement
                dirValue = ship.getDirection();
                arrowValue = ship.getBackArrow();
                lenValue = ship.getShipLength();
                typeValue = ship.getShipType();

                //Ship Catalog Check
                coorCheck = coordinateCheck(gridLetter, gridNumber, dirValue, lenValue, allShipCoordinates, typeValue);
                if (coorCheck == false) {
                    continue;
                }

                //Location and Free Spot Checks
                locCheck = locationCheck(gridLetter, gridNumber, dirValue, lenValue);
                if (locCheck) {
                    freeCheck = freeCheck(gridLetter, arrowValue, gridNumber, dirValue, lenValue);
                }
                else {
                    System.out.println("\nInvalid placement generation. Try Again.");
                    continue;
                }
                if (freeCheck == false) {
                    System.out.println("\nTaken Spot. Try Again.");
                    continue;
                }

                break;
            }

            //Update coordinate catalog
            coordinateUpdate(allShipCoordinates, playerView.getOwnTempCoordinates(), typeValue);

            //Transfer coordinates from grid to ship
            ship.setShipCoordinates(playerView.getOwnTempCoordinates());
        }
        //Increment ship count
        setShipCount(5);
    }

    /**
     * <h2>Method: placeShipsAuto</h2>
     * <p>Method uses getDirection(), getArrow(), getShipLength(), getShipType(), getShipCoordinates(), and
     * setShipCoordinates() methods from the ship class.</p>
     * <p>Method uses locationCheck(), freeCheck(), and setShipCount() methods within this class.</p>
     * <p>Method uses getOwnTempCoordinates() method from location class.</p>
     * <p>The method uses the previously created array of ship objects to automatically set the
     * position of each ship, one by one. After it is done, the ship count is set to 5.</p>
     */
    protected void placeShipsAuto() {
        //Add coordinates for a deep check once and for all
        final int TOTAL_SHIP_LENGTH = 17;

        //each new coordinate array will have a letter, number, status, and the shipnumber
        String[][] allShipCoordinates = new String[TOTAL_SHIP_LENGTH][4];
        int ASCIndex = 0;

        //The starting populating
        for (Ship ship : this.shipTracker) {
            String[][] tempCoor = ship.getShipCoordinates();
            String tempType = ship.getShipType();
            for (int i = 0; i < tempCoor.length; i++) {
                allShipCoordinates[ASCIndex][0] = tempCoor[i][0]; //Letter
                allShipCoordinates[ASCIndex][1] = tempCoor[i][1]; //Number
                allShipCoordinates[ASCIndex][2] = tempCoor[i][2]; //Status
                allShipCoordinates[ASCIndex][3] = tempType; //Ship Type
                ASCIndex++;
            }
        }

        //Iterate through all five ships - Make sure to update allShipCoor once each ship has successfully placed
        for (Ship ship : this.shipTracker) {
            int randGen;
            final int LETTERS_LOWER_BOUND = 'A';

            char direction;
            int gridNumber, gridLetterNum, lenValue;
            char gridLetter, arrowValue;
            String typeValue;
            boolean locCheck, freeCheck, coorCheck;
            String dirValue;

            while (true) {
                //Initial Prompt
                System.out.println("\n\n\nPlacing the " + ship.getShipType());
                System.out.println();

                //Direction selection
                //Generate random number
                randGen = random.nextInt(4) + 1;
                direction = (char)(randGen + '0');

                if (direction == '1') {
                    ship.setDirection("right");
                    ship.setBackArrow('<');
                }
                else if (direction == '2') {
                    ship.setDirection("left");
                    ship.setBackArrow('>');
                }
                else if (direction == '3') {
                    ship.setDirection("up");
                    ship.setBackArrow('V');
                }
                else if (direction == '4') {
                    ship.setDirection("down");
                    ship.setBackArrow('A');
                }
                else {
                    continue;
                }

                //Square selection
                //Generate random character and number
                randGen = random.nextInt(10);
                gridLetterNum = LETTERS_LOWER_BOUND + randGen;
                gridLetter = (char)(gridLetterNum);

                randGen = random.nextInt(10);
                gridNumber = randGen + 1;

                //Perform check before placement
                dirValue = ship.getDirection();
                arrowValue = ship.getBackArrow();
                lenValue = ship.getShipLength();
                typeValue = ship.getShipType();

                //Ship Catalog Check
                coorCheck = coordinateCheck(gridLetter, gridNumber, dirValue, lenValue, allShipCoordinates, typeValue);
                if (coorCheck == false) {
                    continue;
                }

                //Location and Free Spot Checks
                locCheck = locationCheck(gridLetter, gridNumber, dirValue, lenValue);
                if (locCheck) {
                    freeCheck = freeCheck(gridLetter, arrowValue, gridNumber, dirValue, lenValue);
                }
                else {
                    continue;
                }

                if (freeCheck == false) {
                    continue;
                }
                break;
            }

            //Update coordinate catalog
            coordinateUpdate(allShipCoordinates, playerView.getOwnTempCoordinates(), typeValue);

            //Transfer coordinates from grid to ship
            ship.setShipCoordinates(playerView.getOwnTempCoordinates());
        }
        //Increment ship count
        setShipCount(5);
    }

    /**
     * <h2>Method: buildTestCatalog</h2>
     * <p>This method takes five parameters to build a small testCatalog for one single ship. This method then
     * pits the testCatalog against the full ship catalog to do a 1-1 comparison of all coordinates.</p>
     * <p>This check is meant to be the main prevention of overlapping ships before the program gets into any methods
     * that actively change the grid.</p>
     * @param letter Vertical coordinate of placement
     * @param num Horizontal coordinate of placement
     * @param dir Direction ship will be generated. Used for if-else tree
     * @param len Length of the ship
     * @param type Type of the ship
     * @return 2D array of a single ship's coordinates + other info
     */
    public String[][] buildTestCatalog(char letter, int num, String dir, int len, String type) {
        String[][] testCatalog = new String[len][4];
        final String DEFAULT_STATUS = "Status";

        //Build testCatalog
        if (dir == "right") {
            int rest = 0;
            for (int coor = 0; coor < len; coor++) {
                testCatalog[coor][0] = Character.toString(letter);
                testCatalog[coor][1] = Integer.toString(num + rest);
                testCatalog[coor][2] = DEFAULT_STATUS;
                testCatalog[coor][3] = type;
                rest++;
            }
        }
        else if (dir == "left") {
            int rest = 0;
            for (int coor = 0; coor < len; coor++) {
                testCatalog[coor][0] = Character.toString(letter);
                testCatalog[coor][1] = Integer.toString(num - rest);
                testCatalog[coor][2] = DEFAULT_STATUS;
                testCatalog[coor][3] = type;
                rest++;
            }
        }
        else if (dir == "up") {
            int rest = 0;
            int asciiLetter = letter;
            for (int coor = 0; coor < len; coor++) {
                char tempLetter = (char)(asciiLetter - rest);
                testCatalog[coor][0] = Character.toString(tempLetter);
                testCatalog[coor][1] = Integer.toString(num);
                testCatalog[coor][2] = DEFAULT_STATUS;
                testCatalog[coor][3] = type;
                rest++;
            }
        }
        else if (dir == "down") {
            int rest = 0;
            int asciiLetter = letter;
            for (int coor = 0; coor < len; coor++) {
                char tempLetter = (char)(asciiLetter + rest);
                testCatalog[coor][0] = Character.toString(tempLetter);
                testCatalog[coor][1] = Integer.toString(num);
                testCatalog[coor][2] = DEFAULT_STATUS;
                testCatalog[coor][3] = type;
                rest++;
            }
        }
        return testCatalog;
    }

    /**
     * <h2>Method: coordinateUpdate</h2>
     * <p>This method takes two 2D Catalogs plus the ship type as parameters.</p>
     * <p>This method updates the complete catalog where the ship type's coordinates begin.</p>
     * @param coorCatalog Main catalog of all ships
     * @param shipCoorCatalog Smaller catalog of only one ship to enter
     * @param type Ship type for searching through the catalog
     */
    public void coordinateUpdate(String[][] coorCatalog, String[][] shipCoorCatalog, String type) {
        for (int coor = 0; coor < coorCatalog.length; coor++) {
            String coorType = coorCatalog[coor][3];
            if (type == coorType) {
                for (int shipCoor = 0; shipCoor < shipCoorCatalog.length; shipCoor++) {
                    coorCatalog[coor + shipCoor][0] = shipCoorCatalog[shipCoor][0];
                    coorCatalog[coor + shipCoor][1] = shipCoorCatalog[shipCoor][1];
                }
                break;
            }
        }
    }

    /**
     * <h2>Method: coordinateCheck</h2>
     * <p>This method takes placement and ship information to determine if a ship is overlapping with another ship.</p>
     * <p>The default result is true. If a point is eventually found to perfectly match up with an existing record
     * in the catalog, then the result is changed to false.</p>
     * @param letter Vertical coordinate of placement
     * @param num Horizontal coordinate of placement
     * @param dir Direction of placement
     * @param len Length of ship
     * @param coorCatalog Full catalog of ship coordinates to search through
     * @param type Ship type
     * @return Whether there are conflicting coordinates or not
     */
    public boolean coordinateCheck(char letter, int num, String dir, int len, String[][] coorCatalog, String type) {
        final String DEFAULT_STATUS = "status";
        boolean result = true;
        String[][] testCatalog = buildTestCatalog(letter, num, dir, len, type);

        // Search through catalog while skipping over own ship
        for (int coor = 0; coor < coorCatalog.length; coor++) {
            String coorType = coorCatalog[coor][3];
            if (type == coorType) {
                continue;
            }

            for (int test = 0; test < testCatalog.length; test++) {
                boolean letterCheck = (testCatalog[test][0] == coorCatalog[coor][0]);
                boolean numberCheck = (testCatalog[test][1] == coorCatalog[coor][1]);
                if (letterCheck && numberCheck) {
                    result = false;
                }
            }
        }
        return result;
    }


    //Second check to see whether it's even possible to put ship there

    /**
     * <h2>Method: locationCheck</h2>
     * <p>This method uses the testPlacement() method from the location class.</p>
     * <p>Whatever value is returned from it is the result of the location check.</p>
     * <p>This check's purpose initially was to check if the ship can physically exist on the grid. This is still
     * an important check, but now the Catalog check is the initial check.</p>
     * @param letter Vertical coordinate
     * @param num Horizontal coordinate
     * @param dir Ship direction
     * @param length Ship length
     * @return boolean result of check
     */
    public boolean locationCheck(char letter, int num, String dir, int length) {
        return playerView.testPlacement(letter, num, dir, length);
    }

    /**
     * <h2>Method: freeCheck</h2>
     * <p>This method uses the testFree() method from the location class.</p>
     * <p>Whatever value is returned from it is the result of the free check.</p>
     * <p>This check's purpose is to see if any ships preoccupied the spaces where the ships were about to be placed.</p>
     * <p>Work initially but is not a catch all solution. The catalog check was added for this reason.</p>
     * @param letter Vertical coordinate
     * @param arrow Tail-end icon of ship
     * @param num Horizontal coordinate
     * @param dir Ship direction
     * @param length Ship length
     * @return boolean result of check
     */
    public boolean freeCheck(char letter, char arrow, int num, String dir, int length) {
        return playerView.testFree(letter, arrow, num, dir, length);
    }

    /**
     * <h2>Method: tryAttack</h2>
     * <p>This method uses the checkShips() and setScoreCount() methods from this class.</p>
     * <p>This method used the checkEnemyStrike() method from the location class.</p>
     * <p>This method is referenced by the playerAction and CPUAction methods from the battleship class.</p>
     * <p>This method first does a preliminary check on grids within the player's location object. It then
     * does a check on the new coordinates and will update game aspects if successful.</p>
     * @param letter Vertical coordinate of the strike
     * @param number Horizontal coordinate of the strike
     */
    public void tryAttack(char letter, int number) {
        //Check results of the previous attack before starting your turn
        checkShips(this.shipTracker, this.lastLetter, this.lastNumber);

        boolean result = playerView.checkEnemyStrike(letter, number);
        if (result) {
            // Enemy and Progress grids are updated before entering this if statement
            // Other person's Own grid will be updated already because of this
            // Now to update the player and ship stats
            System.out.println("A ship was struck!");

            // Update score
            setScoreCount(-1);

            //Record values to pass back up the stack
            this.lastLetter = letter;
            this.lastNumber = number;
        }
        else {
            // Say it was a miss
            System.out.println("No Ships were hit...");
        }
    }

    /**
     * <h2>Method: checkShips</h2>
     * <p>This method uses getShipCoordinates(), setSunk(), getAlreadyDead(), and setAlreadyDead() from the
     * ship class.</p>
     * <p>This method uses setShipCount() from this class.</p>
     * <p>This method controls the preliminary checks in tryAttack().</p>
     * <p>It iterates through all ships, adding the last possible strike and confirming if a ship has been
     * completely sunk.</p>
     * @param ships Array of ship objects to iterate through
     * @param letter The second to last vertical coordinate to check your opponent's move
     * @param number The second to last horizontal coordinate to check your opponent's move
     */
    public void checkShips(Ship[] ships, char letter, int number) {
        for (Ship ship : ships) {
            String[][] coor = ship.getShipCoordinates();
            String status;
            boolean letterCheck = false;
            boolean numberCheck = false;
            boolean allHit = true;

            //Find and update the hit marker
            for (int spot = 0; spot < coor.length; spot++) {
                letterCheck = (coor[spot][0].charAt(0) == letter);
                numberCheck = (Integer.parseInt(coor[spot][1]) == number);

                if (letterCheck && numberCheck) {
                    coor[spot][2] = "Hit";
                }
            }

            //Do check on ship to see all ship spots record a hit
            for (int spot = 0; spot < coor.length; spot++) {
                status = coor[spot][2];
                if (status != "Hit") {
                    allHit = false;
                }
            }

            //Establish sunken status
            if (allHit) {
                ship.setSunk(allHit);

                //lower ship count
                boolean deadAlready = ship.getAlreadyDead();
                if (deadAlready == false && allHit) {
                    setShipCount(-1);
                    ship.setAlreadyDead(allHit);
                }
            }
        }
    }

    /**
     * <h2>Method: updateSunkShips</h2>
     * <p>This method is referenced by the preTurnTasks() in the Battleship class.</p>
     * <p>This method uses getSunk() and getShipCoordinates() from the Ship class.</p>
     * <p>This method uses setSunkVisuals() from the Location class.</p>
     * <p>Under the assumption that all ships that have been set are set, this method goes through each ship
     * and sets the sunk appearance.</p>
     */
    public void updateSunkShips() {
        Ship[] ships = this.shipTracker;

        for (Ship ship : ships) {
            boolean status = ship.getSunk();

            if (status) {
                String[][] coor = ship.getShipCoordinates();
                String letter;
                String number;
                for (int spot = 0; spot < coor.length; spot++) {
                    letter = coor[spot][0];
                    number = coor[spot][1];

                    this.playerView.setSunkVisuals(letter, number);
                }
            }
        }
    }

    //Used in beginning to place ships
    public void showOnlyPlayer() {
        this.playerView.printOwnGrid();
    }

    //Used in main game
    public void showScreen() throws InterruptedException { this.playerView.printTurn(); }

    public void showScreen(int num) throws InterruptedException { this.playerView.printTurn(num); }

    public String[][][] sendOwnGrid() {
        return this.playerView.getOwnGrid();
    }

    public void acceptEnemyGrid(String[][][] enemy) {
        this.playerView.setEnemyGrid(enemy);
    }

    public void setShipCount(int numShips) {
        if (numShips == -1) {
            this.shipAliveCount--;
        }
        else {
            this.shipAliveCount = numShips;
        }
    }

    public int getShipCount() {
        return this.shipAliveCount;
    }

    public char getLastLetter() {
        return this.lastLetter;
    }

    public void setLastLetter(char lastLetter) {
        this.lastLetter = lastLetter;
    }

    public int getLastNumber() {
        return this.lastNumber;
    }

    public void setLastNumber(int lastNumber) {
        this.lastNumber = lastNumber;
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    public void setWin(boolean wonQuestion) {
        this.win = wonQuestion;
    }

    public boolean getWin() {
        return this.win;
    }

    public void setScoreCount(int num) {
        if (num == -1) {
            this.scoreCount++;
        }
        else {
            this.scoreCount = num;
        }
    }

    public int getScoreCount() {
        return this.scoreCount;
    }
}
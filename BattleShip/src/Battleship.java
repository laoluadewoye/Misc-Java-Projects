import java.util.Scanner;

/**
 * <h1>Class: Battleship</h1>
 * <p>This class manages the core gameplay mechanics of Battleship.</p>
 * <p>Methods within this class control board setup, turn actions, and win conditions.</p>
 * <p>Values managed here include an input instance, turn number, and in-between storage of coordinates</p>
 * <p></p>
 * <b>Variables:</b>
 * <p>input - Scanner object for accepting user input. Unchangeable</p>
 * <p>turnNumber - int type for keeping track of turns</p>
 * <p>lastLetterStore - place to keep vertical coordinates utilized in the Player class</p>
 * <p>lastNumberStore - place to keep horizontal coordinates utilized in the Player class</p>
 */
public class Battleship {
    //Instance variables
    final private Scanner input = new Scanner(System.in);
    private int turnNumber = 0;
    private char lastLetterStore = ' ';
    private int lastNumberStore = -1;

    /**
     * <h2>Method: startBattleship</h2>
     * <p>The method startBattleship is used to start the game via a call from the Main class.</p>
     * <p>Inside of it, it gives options to the user on whether they would like to host a two-player game,
     * a one player game, or a spectating of two CPUs. Each option has its own setup method.</p>
     * @throws InterruptedException used within the call stack down to the Location Class to safely use TimeUnit.
     */
    public void startBattleship() throws InterruptedException {
        char choice = ' ';

        System.out.println("Start!\n");
        System.out.println("Are you playing against another player or a cpu?");

        while (choice != '1' && choice != '2' && choice != '3') {
            System.out.println("Type 1 for two player game.");
            System.out.println("Type 2 for player vs CPU.");
            System.out.println("Type 3 for to watch two CPUs go at it.");
            System.out.print("Enter choice here: ");
            choice = this.input.next().charAt(0);

            if (choice == '1') {
                PVPSetup();
            }
            else if (choice == '2') {
                PVCPUSetup();
            }
            else if (choice == '3') {
                CVCSetup();
            }
        }
    }

    /**
     * <h2>Method: manualOrAuto</h2>
     * <p>The method manualOrAuto is used by PVPSetup & PVCPUSetup to allow users to decide whether or not
     * they wish to manually create their board.</p>
     * <p>Depending on choice, the Player object is further passed down to placeShips() or placeShipsAuto().</p>
     * @param player Abstract Player object is passed to allow modification of their ships and grids
     */
    public void manualOrAuto(Player player) {
        char choice = ' ';

        System.out.println("Type 1 to generate a board for you.");
        System.out.println("Type 2 to make the board yourself.");

        while (choice != '1' && choice != '2') {
            System.out.print("Type here: ");
            choice = this.input.next().charAt(0);

            if (choice == '1') {
                player.placeShipsAuto();
            } else if (choice == '2') {
                player.placeShips();
            }
            else {
                System.out.println("Try again");
            }
        }
    }

    /**
     * <h2>Method: PVPSetup</h2>
     * <p>The method PVPSetup is called by startBattleship() and passes Player objects to gameplay()</p>
     * <p>The method is used to set up two Player objects before the actual game can begin.</p>
     * <p>The method calls manualOrAuto() above to choose which a strategy to place down ships.</p>
     * @throws InterruptedException used within the call stack down to the location Class to safely use TimeUnit.
     */
    public void PVPSetup() throws InterruptedException {
        Player playerOne = new Player(1);
        Player playerTwo = new Player(2);

        playerOne.showOnlyPlayer();
        System.out.println("Player 1 will now choose the locations of the ships!");
        manualOrAuto(playerOne);

        playerTwo.showOnlyPlayer();
        System.out.println("\n\nPlayer 2 will now choose the locations of the ships!");
        manualOrAuto(playerTwo);

        //Player 1 sends grid to player 2
        playerTwo.acceptEnemyGrid(playerOne.sendOwnGrid());
        //player 2 sends grid to player 1
        playerOne.acceptEnemyGrid(playerTwo.sendOwnGrid());

        //Send to next phase
        gameplay(playerOne, playerTwo);
    }

    /**
     * <h2>Method: PVCPUSetup</h2>
     * <p>The method PVCPUSetup is called by startBattleship() and passes 1 player object and 1 computer object
     * to gameplayVsCPU()</p>
     * <p>The method is used to set up a human and computer object before the actual game can begin.</p>
     * <p>The method calls manualOrAuto() above to choose which a strategy to place down ships.</p>
     * @throws InterruptedException used within the call stack down to the location Class to safely use TimeUnit.
     */
    public void PVCPUSetup() throws InterruptedException {
        Player playerOne = new Player(1);
        Computer playerCPU = new Computer(2);

        playerOne.showOnlyPlayer();
        System.out.println("Player 1 will now choose the locations of the ships!");
        manualOrAuto(playerOne);

        playerCPU.placeShipsAuto();

        //Player 1 sends grid to CPU
        playerCPU.acceptEnemyGrid(playerOne.sendOwnGrid());
        //CPU sends grid to player 1
        playerOne.acceptEnemyGrid(playerCPU.sendOwnGrid());

        gameplayVsCPU(playerOne, playerCPU); //Work on this, this should be the last thing
    }

    /**
     * <h2>Method: CVCSetup</h2>
     * <p>The method CVCSetup is called by startBattleship() and passes 2 Computer objects to gameplayCPU()</p>
     * <p>The method is used to set up two Computer objects before the actual game can begin.</p>
     * <p>The method calls placeShipsAuto() in each Computer object to generate their grids and ships.</p>
     * @throws InterruptedException used within the call stack down to the location Class to safely use TimeUnit.
     */
    public void CVCSetup() throws InterruptedException {
        Computer computer1 = new Computer(1);
        Computer computer2 = new Computer(2);

        computer1.placeShipsAuto();
        computer2.placeShipsAuto();

        //Player 1 sends grid to CPU
        computer2.acceptEnemyGrid(computer1.sendOwnGrid());
        //CPU sends grid to player 1
        computer1.acceptEnemyGrid(computer2.sendOwnGrid());

        gameplayCPU(computer1, computer2); //Work on this, this should be the very last thing
    }

    /**
     * <h2>Method: gameplay</h2>
     * <p>The method gameplay is called by PVPSetup() and calls playerAction() until the end condition is met.</p>
     * <p>Once done, the method will call endGame() to produce the final results of the game</p>
     * <p>It's main methods are cycling both players so they can each take turns and incrementing turns</p>
     * @param p1 Player One in game
     * @param p2 Player Two in game
     * @throws InterruptedException used within the call stack down to the location Class to safely use TimeUnit.
     */
    public void gameplay(Player p1, Player p2) throws InterruptedException {
        boolean isNotEnd = true;
        Player[] turnCycle = {p1, p2};
        final int DEFAULT = -1;
        int score1, score2;

        while (isNotEnd) {
            //Increment
            setTurnNumber(DEFAULT);
            System.out.println("Turn " + getTurnNumber());
            for (Player player : turnCycle) {
                if (isNotEnd) {
                    isNotEnd = playerAction(player);
                }
            }
        }

        //Endgame
        score1 = p1.getScoreCount();
        score2 = p2.getScoreCount();

        if (score1 > score2) {
            p2.setWin(false);
        }
        else if (score2 > score1) {
            p1.setWin(false);
        }

        endGame(turnCycle, this.turnNumber);
    }

    /**
     * <h2>Method: gameplay</h2>
     * <p>The method gameplay is called by PVCPUSetup() and calls playerAction() and CPUAction() until the end
     * condition is met.</p>
     * <p>Once done, the method will call endGame() to produce the final results of the game</p>
     * <p>It's main methods are cycling both players so they can each take turns and incrementing turns</p>
     * @param p1 Player 1 in the game, human
     * @param CPU Player 2 in the game, computer
     * @throws InterruptedException Used within the call stack down to the location Class to safely use TimeUnit
     */
    public void gameplayVsCPU(Player p1, Computer CPU) throws InterruptedException {
        boolean isNotEnd = true;
        boolean playerEnd;
        boolean cpuEnd;
        final int DEFAULT = -1;
        int score1, score2;

        while (isNotEnd) {
            //Increment
            setTurnNumber(DEFAULT);
            System.out.println("Turn " + getTurnNumber());
            playerEnd = playerAction(p1);
            cpuEnd = CPUAction(CPU);

            if ((playerEnd && cpuEnd) == false) {
                isNotEnd = false;
            }
        }

        //Endgame
        score1 = p1.getScoreCount();
        score2 = CPU.getScoreCount();

        if (score1 > score2) {
            CPU.setWin(false);
        }
        else if (score2 > score1) {
            p1.setWin(false);
        }

        endGame(p1, CPU, this.turnNumber);
    }

    /**
     * <h2>Method: gameplayCPU</h2>
     * <p>The method gameplayCPU is called by CVCSetup() and calls CPUAction() until the end condition is met.</p>
     * <p>Once done, the method will call endGame() to produce the final results of the game</p>
     * <p>It's main methods are cycling both computers so they can each take turns and incrementing turns</p>
     * @param CPU1
     * @param CPU2
     * @throws InterruptedException Used within the call stack down to the location Class to safely use TimeUnit
     */
    public void gameplayCPU(Computer CPU1, Computer CPU2) throws InterruptedException {
        boolean isNotEnd = true;
        Computer[] turnCycle = {CPU1, CPU2};
        final int DEFAULT = -1;
        int score1, score2;

        while (isNotEnd) {
            //Increment
            setTurnNumber(DEFAULT);
            System.out.println("Turn " + getTurnNumber());
            for (Computer computer : turnCycle) {
                if (isNotEnd) {
                    isNotEnd = CPUAction(computer);
                }
            }
        }

        //Endgame
        score1 = CPU1.getScoreCount();
        score2 = CPU2.getScoreCount();

        if (score1 > score2) {
            CPU2.setWin(false);
        }
        else if (score2 > score1) {
            CPU1.setWin(false);
        }

        endGame(turnCycle, this.turnNumber);
    }

    /**
     * <h2>Method: preTurnTasks</h2>
     * <p>This method is called by playerAction() and calls updateSunkShips() during its execution.</p>
     * <p>This method prints the turn number, saves the last used coordinates for the player, and checks for newly
     * sunk ships.</p>
     * @param player Player object that will check itself for newly sunk ships.
     */
    public void preTurnTasks(Player player) {
        //Show turn
        System.out.println("Turn " + getTurnNumber());

        //Set numbers from previous turn
        player.setLastLetter(this.lastLetterStore);
        player.setLastNumber(this.lastNumberStore);

        //Check for hit statuses
        player.updateSunkShips();
    }

    /**
     * <h2>Method: preTurnTasks</h2>
     * <p>This method is called by CPUAction() and calls updateSunkShips() during its execution.</p>
     * <p>This method prints the turn number, saves the last used coordinates for the player, and checks for newly sunk ships.</p>
     * @param computer Computer object that will check itself for newly sunk ships.
     */
    public void preTurnTasks(Computer computer) {
        //Show turn
        System.out.println("Turn " + getTurnNumber());

        //Set numbers from previous turn
        computer.setLastLetter(this.lastLetterStore);
        computer.setLastNumber(this.lastNumberStore);

        //Check for hit statuses
        computer.updateSunkShips();
    }

    /**
     * <h2>Method: playerAction</h2>
     * <p>This method is called by gameplay() and gameplayVsCPU().</p>
     * <p>This method calls preTurnTasks(), getTurnNumber(), and the letter and number stores within this class.</p>
     * <p>This method calls getPlayerNumber(), showScreen(), tryAttack(), getLastLetter(), getLastNumber(), and
     * getShipCount() from the Player Class.</p>
     * <p>This method conducts preliminary tasks within the turn, shows the grid for the player, and allows them to
     * choose a square to strike. It will then try the attack, store the numbers used from the attack, and check if the
     * game has ended.</p>
     * @param player Player object used during the turn cycle of program
     * @return boolean value that determines whether one side has lost all their ships
     * @throws InterruptedException Used within the call stack down to the location Class to safely use TimeUnit
     */
    public boolean playerAction(Player player) throws InterruptedException {
        boolean isNotEnd = true;
        char letter;
        int number;

        //Pre turn tasks
        preTurnTasks(player);

        //Begin turn
        String playerName = "Player " + player.getPlayerNumber();

        player.showScreen();

        System.out.println("Turn " + getTurnNumber());
        System.out.println("\n\nIt is " + playerName + " turn!");
        System.out.println("Where do you wish to strike? You will type the letter and number separately.");
        System.out.print("Type the letter: ");
        letter = input.next().charAt(0);
        System.out.print("Type the number: ");
        number = input.nextInt();

        //Left off here
        player.tryAttack(letter, number);

        //Move numbers here
        this.lastLetterStore = player.getLastLetter();
        this.lastNumberStore = player.getLastNumber();

        //Check if game has ended
        int shipNum = player.getShipCount();
        if (shipNum < 1) {
            isNotEnd = false;
            System.out.println("One side has lost all ships!\n");
        }

        return isNotEnd;
    }

    /**
     * <h2>Method: CPUAction</h2>
     * <p>This method is called by gameplayVsCPU() and gameplayCPU().</p>
     * <p>This method calls preTurnTasks() and uses the letter and number stores in this class.</p>
     * <p>This method calls getPlayerNumber(), showScreen(), selectSquare(), tryAttack(), getLastLetter(),
     * getLastNumber(), and getShipCount() from the Computer class.</p>
     * <p>This method does preliminary tasks before the turn, shows a faster screen than playerAction, and
     * automatically selects a square using a random generator. Selection is used to try attack. Lastly, last
     * used coordinates are stored in Battleship object and a check is done to see if all ships are gone.</p>
     * @param cpu Computer object used during the turn cycle of program
     * @return boolean value that determines whether one side has lost all their ships
     * @throws InterruptedException Used within the call stack down to the location Class to safely use TimeUnit
     */
    public boolean CPUAction(Computer cpu) throws InterruptedException {
        boolean isNotEnd = true;
        char letter, numberChar;
        int number;
        String choice;

        //Pre turn tasks
        preTurnTasks(cpu);

        //Begin turn
        String playerName = "Player " + cpu.getPlayerNumber() + " (CPU)";
        System.out.println("\n\nIt is " + playerName + " turn!");

        //Display screen for a certain amount of time
        int milli = 100;
        cpu.showScreen(milli);
        System.out.println(playerName + " will now choose.");

        //Auto-Selection
        choice = cpu.selectSquare();
        letter = choice.charAt(0);
        numberChar = choice.charAt(1);
        if (numberChar == '!') {
            number = 10;
        }
        else {
            number = Character.getNumericValue(numberChar);
        }

        cpu.tryAttack(letter, number);

        //Move numbers here for other player to do a check
        this.lastLetterStore = cpu.getLastLetter();
        this.lastNumberStore = cpu.getLastNumber();

        //Move a numbers here for cpu to do a check next time
        cpu.setLastAttack(choice);


        //Check if game has ended
        int shipNum = cpu.getShipCount();
        if (shipNum < 1) {
            isNotEnd = false;
            System.out.println("One side has lost all ships!\n");
        }

        return isNotEnd;
    }

    /**
     * <h2>Method: endGame</h2>
     * <p>This method is called by gameplay() and shares a name with two other methods.</p>
     * <p>This method uses getPlayerNumber(), getScoreCount(), and getWin() from Player class.</p>
     * <p>This method is specifically for printing the final screen for games with two human players.</p>
     * @param players A list of player objects to check
     * @param turn Number of turns it took the game to end
     */
    public static void endGame(Player[] players, int turn) {
        for (Player player : players) {
            int playerNum = player.getPlayerNumber();
            String playerName = "Player " + playerNum;
            int score = player.getScoreCount();
            boolean win = player.getWin();

            if (win) {
                System.out.println(playerName + " has won!");
            }
            else {
                System.out.println(playerName + " has lost.");
            }

            System.out.println("Final score: " + score);
        }
        System.out.println("Turns needed: " + turn);
    }

    /**
     * <h2>Method: endGame</h2>
     * <p>This method is called by gameplayVsCPU() and shares a name with two other methods.</p>
     * <p>This method uses getPlayerNumber(), getScoreCount(), and getWin() from Player class.</p>
     * <p>This method uses getPlayerNumber(), getScoreCount(), and getWin() from Computer class.</p>
     * <p>This method is specifically for printing the final screen for games with a human and computer.</p>
     * @param player The Player object
     * @param CPU The Computer object
     * @param turn Number of turns it took the game to end
     */
    public static void endGame(Player player, Computer CPU, int turn) {
        int playerNum = player.getPlayerNumber();
        String playerName = "Player " + playerNum;
        int playerScore = player.getScoreCount();
        boolean playerWin = player.getWin();

        if (playerWin) {
            System.out.println(playerName + " has won!");
        }
        else {
            System.out.println(playerName + " has lost.");
        }
        System.out.println("Final score: " + playerScore);


        int cpuNum = CPU.getPlayerNumber();
        String cpuName = "Player " + cpuNum + " (CPU)";
        int cpuScore = CPU.getScoreCount();
        boolean cpuWin = CPU.getWin();

        if (cpuWin) {
            System.out.println(cpuName + " has won!");
        }
        else {
            System.out.println(cpuName + " has lost.");
        }
        System.out.println("Final score: " + cpuScore);
        System.out.println("Turns needed: " + turn);
    }

    /**
     * <h2>Method: endGame</h2>
     * <p>This method is called by gameplayCPU() and shares a name with two other methods.</p>
     * <p>This method uses getPlayerNumber(), getScoreCount(), and getWin() from Computer class.</p>
     * <p>This method is specifically for printing the final screen for games with CPUs.</p>
     * @param computers The Computer objects
     * @param turn Number of turns it took the game to end
     */
    public static void endGame(Computer[] computers, int turn) {
        for (Computer CPU : computers) {
            int cpuNum = CPU.getPlayerNumber();
            String cpuName = "Player " + cpuNum + " (CPU)";
            int cpuScore = CPU.getScoreCount();
            boolean cpuWin = CPU.getWin();

            if (cpuWin) {
                System.out.println(cpuName + " has won!");
            }
            else {
                System.out.println(cpuName + " has lost.");
            }
            System.out.println("Final score: " + cpuScore);
        }
        System.out.println("Turns needed: " + turn);
    }

    /**
     * <h2>Method: setTurnNumber</h2>
     * <p>Sets the turn number value in Battleship object.</p>
     * @param num Number used to set turn count
     */
    public void setTurnNumber(int num) {
        if (num == -1) {
            this.turnNumber++;
        }
        else {
            this.turnNumber = num;
        }
    }

    /**
     * <h2>Method: getTurnNumber</h2>
     * <p>Retrieves the turn value in Battleship object.</p>
     * @return The current turn number in the Battleship game
     */
    public int getTurnNumber() {
        return this.turnNumber;
    }
}
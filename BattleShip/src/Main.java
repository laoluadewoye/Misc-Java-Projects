import java.util.Scanner;

/**
 * <h1>Class: Battleship</h1>
 * <p>This class manages the instructions of the game and the starting of battleship.</p>
 * @author Laolu Adewoye
 */
public class Main {

    /**
     * <h2>Method: main</h2>
     * The method "main" allows the user to choose between seeing the instructions to the game or playing the game.
     * @param args command line arguments used throughout the program.
     * @throws InterruptedException used within the call stack to safely use the TimeUnit class.
     */
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        boolean choiceNotDone = true;
        char startCheck;
        while (choiceNotDone) {
            System.out.println("Welcome to the game of Battleship, Java edition!");
            System.out.println("Press 1 to play.");
            System.out.println("Press 2 to see instructions.");
            System.out.print("Enter here: ");

            startCheck = input.next().charAt(0);
            if (startCheck == '1') {
                //Make the object
                Battleship game = new Battleship();
                game.startBattleship();
                choiceNotDone = false;
            } else if (startCheck == '2') {
                //Make the object
                printInstructions();
            }
        }
    }

    /**
     * <h2>Method: printInstructions</h2>
     * <p>Method is used to manage help menu and various topics of information before playing the game.</p>
     */
    public static void printInstructions() {
        Scanner input = new Scanner(System.in);

        //Clear console
        for (int i = 0; i < 200; i++) {
            System.out.println();
        }

        System.out.println("Choose what information you wish to see.");
        System.out.println("(1) Basic Information");
        System.out.println("(2) Placement Controls");
        System.out.println("(3) Gameplay Controls");
        System.out.println("(4) Grid View");
        System.out.println("(5) Labels");
        System.out.println("(6) Examples");
        System.out.println("Press any other key to go back to main menu");
        System.out.print("Enter here: ");

        int num = input.nextInt();
        if (num == 1) {
            basicInfo();
        } else if (num == 2) {
            placeControl();
        } else if (num == 3) {
            gameControl();
        } else if (num == 4) {
            gridView();
        } else if (num == 5) {
            labels();
        } else if (num == 6) {
            examples();
        }

        System.out.println("\n\n");
    }

    public static void basicInfo() {
        System.out.println("\n\nLink to the official game instructions: https://www.officialgamerules.org/battleship");
        System.out.println("Welcome to the game of battleship!");
        System.out.print("This game works similar to the real life version. ");
        System.out.print("Each player takes turns choosing spots to strike. ");
        System.out.println("If you hit a ship, an 'X' will be placed down on the spot you choose.");
        System.out.println("Both players keep making attempts until all the ships are dead, in which the game is won!");
    }

    public static void placeControl() {
        System.out.println("\n\nPlacement Controls:");
        System.out.print("    You will have the option to manually create your board or generate it automatically. ");
        System.out.println("Choose which one you prefer using the associated number.\n");
        System.out.println("    To place a ship down manually, first choose the number associated with a direction.");
        System.out.println("    Next, choose the UPPERCASE letter of where you want to place on the grid.");
        System.out.println("    Finally, choose the number of where you want to place on the grid.");
        System.out.println("    Once done, your ship will be generated OUT FROM YOUR STARTING SPOT.");
        System.out.print("    For example, if you choose for your ship to face left, your ship will be generated out LEFT a ");
        System.out.println("certain number of spots.\n");
        System.out.print("    If your ship is unable to fit on the board, or would overlap with another ship, ");
        System.out.println("you will be asked to try again and choose another location.");
    }

    public static void gameControl() {
        System.out.println("\n\nGameplay Controls:");
        System.out.println("    You will choose each coordinate of the grid separately.");
        System.out.println("    Use capital letters ONLY when choosing your letter. Lowercase will not work.");
        System.out.println("    State the number you wish to enter.");
        System.out.println("    You will be told if your attempt was successful or not.");
    }

    public static void gridView() {
        System.out.println("\n\nGrid View:");
        System.out.println("    Each turn for each user, you will be shown two grids stacked on top of each other.");
        System.out.println("    The first (top) grid is YOUR grid.");
        System.out.print("    You will be able to see the ships you placed ");
        System.out.println("and whether or not parts of it have been hit by your opponent.");
        System.out.println("    The next (bottom) grid is your OPPONENT'S grid. ");
        System.out.print("    It is not populated because you will not be able to see your opponents side ");
        System.out.println("just as in real battleship.");
        System.out.println("    However, it is able to show where you have HIT and MISSED.");
        System.out.println("    See labels and examples for what ships may look like");
    }

    public static void labels() {
        System.out.println("\n\nSymbols:");
        System.out.println("    @ is the front portion of a ship");
        System.out.println("    O is a middle portion of a ship");
        System.out.println("    The end portion of a ship has for different symbols to account for every direction.");
        System.out.println("    They are <, >, A, and V.");
        System.out.println("    X means that spot on a ship has been hit by the enemy.");
        System.out.println("    ! means that spot on a ship is completely sunk, usually indicating the entire ship is down.");
        System.out.println("    ? means that all ships were completely missed and a shot landed in empty space.");
    }

    public static void examples() {
        System.out.println("\n\nShip examples using 3-length ships:");
        System.out.println("    A right facing ship:");
        System.out.println("    < O @\n");
        System.out.println("    A left facing ship:");
        System.out.println("    @ O >\n");
        System.out.println("    A upward facing ship:");
        System.out.println("            @");
        System.out.println("            O");
        System.out.println("            V\n");
        System.out.println("    A downward facing ship:");
        System.out.println("            A");
        System.out.println("            O");
        System.out.println("            @\n");
        System.out.println("    A ship that has been hit once:");
        System.out.println("    < X @\n");
        System.out.println("    A ship that has been sunk:");
        System.out.println("    ! ! !");
    }
}
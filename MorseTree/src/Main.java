import java.lang.Math;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        Scanner input = new Scanner(System.in);
        String curSentence = "";
        String currentMC = "";
        char choice;

        populateTree(tree);
        tree.display();

        System.out.println("\nNote: If you do not know morse code, get to pulling up the cheat sheet!");
        System.out.println("Start typing in morse code below. Your sentence will update as you select dots and dashes.");
        System.out.println("You can immediately confirm your selection to add a space.");
        System.out.println("Until the final input, spaces will be represented with tildes.\n");
        System.out.println("Options:");
        System.out.println("z --> typing a dot.");
        System.out.println("x --> typing a dash.");
        System.out.println("c --> confirm letter.");
        System.out.println("m --> cancel current morse sequence.");
        System.out.println("q --> Confirm you are done and retrieve final output.\n");

        while (true) {
            char potentialLetter = tree.retrieveLetter(BSTNumberGen(currentMC));
            System.out.println("Morse Code: " + currentMC);
            System.out.println("Current Sentence: " + curSentence + potentialLetter);

            System.out.print("Enter choice of option: ");
            choice = input.next().charAt(0);
            if (choice == 'z') {
                currentMC += '.';
            }
            else if (choice == 'x') {
                currentMC += '_';
            }
            else if (choice == 'c') {
                curSentence += potentialLetter;
                currentMC = "";
            }
            else if (choice == 'm') {
                currentMC = "";
            }
            else if (choice == 'q') {
                break;
            }
        }

        curSentence = curSentence.replace('~', ' ') + '.';
        System.out.println("\n\nFinal Sentence: " + curSentence);

    }

    public static void populateTree(BinarySearchTree tree) {
        //Starting point
        tree.insert(new Node(BSTNumberGen(""), '~', 1));

        //Level 2
        tree.insert(new Node(BSTNumberGen("."), 'E', 2));
        tree.insert(new Node(BSTNumberGen("_"), 'T', 2));

        //Level 3
        tree.insert(new Node(BSTNumberGen(".."), 'I', 3));
        tree.insert(new Node(BSTNumberGen("._"), 'A', 3));
        tree.insert(new Node(BSTNumberGen("_."), 'N', 3));
        tree.insert(new Node(BSTNumberGen("__"), 'M', 3));

        //Level 4
        tree.insert(new Node(BSTNumberGen("..."), 'S', 4));
        tree.insert(new Node(BSTNumberGen(".._"), 'U', 4));
        tree.insert(new Node(BSTNumberGen("._."), 'R', 4));
        tree.insert(new Node(BSTNumberGen(".__"), 'W', 4));
        tree.insert(new Node(BSTNumberGen("_.."), 'D', 4));
        tree.insert(new Node(BSTNumberGen("_._"), 'K', 4));
        tree.insert(new Node(BSTNumberGen("__."), 'G', 4));
        tree.insert(new Node(BSTNumberGen("___"), 'O', 4));

        //Level 5
        tree.insert(new Node(BSTNumberGen("...."), 'H', 5));
        tree.insert(new Node(BSTNumberGen("..._"), 'V', 5));
        tree.insert(new Node(BSTNumberGen(".._."), 'F', 5));
        tree.insert(new Node(BSTNumberGen("._.."), 'L', 5));
        tree.insert(new Node(BSTNumberGen(".__."), 'P', 5));
        tree.insert(new Node(BSTNumberGen(".___"), 'J', 5));
        tree.insert(new Node(BSTNumberGen("_..."), 'B', 5));
        tree.insert(new Node(BSTNumberGen("_.._"), 'X', 5));
        tree.insert(new Node(BSTNumberGen("_._."), 'C', 5));
        tree.insert(new Node(BSTNumberGen("_.__"), 'Y', 5));
        tree.insert(new Node(BSTNumberGen("__.."), 'Z', 5));
        tree.insert(new Node(BSTNumberGen("__._"), 'Q', 5));

        //Level 6
        tree.insert(new Node(BSTNumberGen("....."), '5', 6));
        tree.insert(new Node(BSTNumberGen("...._"), '4', 6));
        tree.insert(new Node(BSTNumberGen("...__"), '3', 6));
        tree.insert(new Node(BSTNumberGen("..___"), '2', 6));
        tree.insert(new Node(BSTNumberGen(".____"), '1', 6));
        tree.insert(new Node(BSTNumberGen("_...."), '6', 6));
        tree.insert(new Node(BSTNumberGen("__..."), '7', 6));
        tree.insert(new Node(BSTNumberGen("___.."), '8', 6));
        tree.insert(new Node(BSTNumberGen("____."), '9', 6));

        //"Level 7" quote end quote - This is just for aesthetic purposes its actually level six
        tree.insert(new Node(BSTNumberGen("_____"), '0', 7));
    }

    public static int BSTNumberGen(String morse) {
        int value = 1000000;
        int MAX_LEVELS = 6;
        int levelsTraveled = 0;

        for (int i = 0; i<morse.length(); i++) {
            char m = morse.charAt(i);
            levelsTraveled++;
            double expo = MAX_LEVELS - levelsTraveled;

            if (m == '.') {
                value -= (int)(Math.pow(10, expo));
            }
            else if (m == '_') {
                value += (int)(Math.pow(10, expo));
            }
        }
        return value;
    }
}
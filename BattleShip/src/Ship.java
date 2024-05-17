/**
 * <h1>Class: Ship</h1>
 * <p>This class is used to create a ship for battlefield. There are usually 10 of these objects used per game.</p>
 * <p>Functions in here control ship generation, ship status, variable access, and variable modification.</p>
 * <p></p>
 * <b>Variables:</b>
 * <p>shipLength - int variable that holds how many squares a ship will take up</p>
 * <p>shipType - String variable that holds the type of ship</p>
 * <p>directionFacing - String variable that holds the direction of the ship</p>
 * <p>shipCoordinates - 2D String array holding coordinate information for ship</p>
 * <p>isSunk - boolean variable stating whether the ship is sunken or not</p>
 * <p>alreadyDead - boolean variable stating whether the ship was sunk prior to a check</p>
 * <p>backArrow - char variable holding the symbol to use for end of ship. Dependant on direction</p>
 */
public class Ship {
    //Instance variables
    private int shipLength;
    private String shipType;
    private String directionFacing;
    private String[][] shipCoordinates;
    private boolean isSunk;
    private boolean alreadyDead;
    private char backArrow;

    /**
     * <h2>Constructor: Ship</h2>
     * <p>Default/Fallback constructor in cause something happens.</p>
     */
    public Ship () {
        this.shipLength = 0;
        this.shipType = "None";
        this.directionFacing = "None";
        this.shipCoordinates = new String[1][1];
        this.isSunk = true;
        this.alreadyDead = true;
        this.backArrow = 'N';

        System.out.println("Something went wrong with creating the object. Look in boat.");
    }

    /**
     * <h2>Constructor: Ship</h2>
     * <p>This constructor creates the ship object for the player.</p>
     * @param type Type of ship to create
     */
    public Ship (String type) {
        //General initialization
        this.directionFacing = "right";
        this.isSunk = false;
        this.alreadyDead = false;
        this.backArrow = '<';

        //Type specific initialization
        byTypeInit(type);
    }

    /**
     * <h2>Method: byTypeInit</h2>
     * <p>This method uses fillShipLength() from this class.</p>
     * <p>This method holds a case tree that sets the type, length, and coordinates.</p>
     * @param type Type of ship to create
     */
    public void byTypeInit(String type) {
        //For specifying what ship you are making
        switch(type) {
            case "destroyer":
                this.shipLength = 2;
                this.shipType = "destroyer";
                break;
            case "submarine":
                this.shipLength = 3;
                this.shipType = "submarine";
                break;
            case "cruiser":
                this.shipLength = 3;
                this.shipType = "cruiser";
                break;
            case "battleship":
                this.shipLength = 4;
                this.shipType = "battleship";
                break;
            case "carrier":
                this.shipLength = 5;
                this.shipType = "carrier";
                break;
            default:
                System.out.println("You know who you are. Look in boat :)");
        }

        this.shipCoordinates = new String[this.shipLength][3];
        fillShipLength(this.shipCoordinates);
    }

    /**
     * <h2>Method: fillShipLength</h2>
     * <p>This method fills the newly created ship coordinate array.</p>
     * @param location 2D coordinate array
     */
    public static void fillShipLength(String[][] location) {
        for (int coor = 0; coor < location.length; coor++) {
            location[coor][0] = "Letter";
            location[coor][1] = "Number";
            location[coor][2] = "Status";
        }
    }

    public String getShipType() { return this.shipType; }

    public void setShipCoordinates(String[][] shipCoor) { this.shipCoordinates = shipCoor; }

    public String[][] getShipCoordinates() { return this.shipCoordinates; }

    public void setDirection(String direction) { this.directionFacing = direction; }

    public String getDirection() { return this.directionFacing; }

    public int getShipLength() { return this.shipLength; }

    public void setBackArrow(char arrow) { this.backArrow = arrow; }

    public char getBackArrow() { return this.backArrow; }

    public void setSunk (boolean status) { this.isSunk = status; }

    public boolean getSunk() { return this.isSunk; }

    public void setAlreadyDead(boolean status) { this.alreadyDead = status; }

    public boolean getAlreadyDead() { return this.alreadyDead; }
}

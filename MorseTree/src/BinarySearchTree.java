public class BinarySearchTree {
    Node root;

    public void insert(Node node) {
        root = insertHelper(root, node);
    }

    private Node insertHelper(Node root, Node node) {
        int number = node.number;

        if (root == null) { //No node exists, create first node
            root = node;
            return root;
        }

        else if (number < root.number) {
            root.left = insertHelper(root.left, node);
        }

        else {
            root.right = insertHelper(root.right, node);
        }

        return root;
    }

    public void display() {
        displayHelper(root);
    }

    private void displayHelper(Node root) {
        if (root != null) {
            displayHelper(root.left);

            //Visiting
            for (int i = 0; i < root.level; i++) {
                System.out.print("\t");
            }
            System.out.println(root.character);

            displayHelper(root.right);
        }
    }

    public boolean search(int number) {
        return searchHelper(root, number);
    }

    private boolean searchHelper(Node root, int number) {

        if (root == null) {
            return false;
        }
        else if (root.number == number) {
            return true;
        }
        else if (root.number > number) {
            return searchHelper(root.left, number);
        }
        else {
            return searchHelper(root.right, number);
        }
    }

    public void remove(int number) {
        if (search(number)) {
            removeHelper(root, number);
        }
        else {
            System.out.println(number + " could not be found.");
        }
    }

    public Node removeHelper(Node root, int number) {
        if (root == null) {
            return root;
        }
        else if (number < root.number) { //Look to the left to find what to remove
            root.left = removeHelper(root.left, number);
        }
        else if (number > root.number) { //Look to the right to find what to remove
            root.right = removeHelper(root.right, number);
        }
        else { //We found it
            if (root.left == null && root.right == null) { //Leaf Node, easy delete
                root = null;
            }
            else if (root.right != null) { //Find successor for replacement
                root.number = successorNum(root);
                root.character = successorChar(root);
                root.right = removeHelper(root.right, root.number);
            }
            else { //Find predecessor for replacement
                root.number = predecessorNum(root);
                root.character = predecessorChar(root);
                root.left = removeHelper(root.left, root.number);
            }
        }

        return root;
    }

    private int successorNum(Node root) { //Find the least value below right child of root node
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }

        return root.number;
    }

    private char successorChar(Node root) { //Find the least value below right child of root node
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }

        return root.character;
    }

    private int predecessorNum(Node root) { //Find the greatest value below right child of root node
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }

        return root.number;
    }

    private char predecessorChar(Node root) { //Find the greatest value below right child of root node
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }

        return root.character;
    }

    public char retrieveLetter(int number) {
        char letter = rlHelper(root, number);

        return letter;
    }

    private char rlHelper(Node root, int number) {
        if (root == null) {
            return '#';
        }

        //Pre-order Recursion
        else if (number == root.number) {
            return root.character;
        }
        else if (number < root.number) { //Go left
            return rlHelper(root.left, number);
        }
        else { //Go right
            return rlHelper(root.right, number);
        }
    }
}

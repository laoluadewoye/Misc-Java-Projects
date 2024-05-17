import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter in the number of lines you want to print: ");
        int lineNum = input.nextInt();

        StarGen(lineNum);
    }

    public static void StarGen(int lineNum) {
        for (int i = 0; i < lineNum; i++) {
            System.out.print('*');
        }
        System.out.println();

        if (lineNum > 0) {
            StarGen(lineNum - 1);
        }
    }
}
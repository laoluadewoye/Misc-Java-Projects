import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter in base number: ");
        int x = input.nextInt();

        System.out.print("Enter in exponent: ");
        int y = input.nextInt();

        int poweredNum = Power(x, y);
        System.out.println(poweredNum);
    }

    public static int Power(int x, int y) {
        if (y > 1) return x * Power(x, (y-1));
        else if (y == 1) return x;
        else if (y == 0) return 1;
        else return 0;
    }
}
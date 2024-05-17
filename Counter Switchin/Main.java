//Learn how to order, switchout, pause, and continue threads in a certain order
//Counting sleep time?

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}

class Counter extends Thread {
    
    static short elapsedTime;
    
    public void run() {
        for (int i; i < elapsedTime; i++) {
            System.out.println("It ")
        }
    }
    
}

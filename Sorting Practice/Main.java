import java.util.Random;

public class Main
{
	public static void main(String[] args) {
	    Random random = new Random();
	    int randLength = random.nextInt(22);
	    
		int[] numbers = generateArray(randLength);
		int[] originalNumbers = persistance(numbers);
		
		System.out.print("Original array: ");
		print(numbers);
		
		insertionSort(numbers);
		System.out.print("Array after insertion sort: ");
		print(numbers);
		System.out.println();
		
		numbers = persistance(originalNumbers);
		System.out.print("Original array: ");
		print(numbers);
		
		selectionSort(numbers);
		System.out.print("Array after selection sort: ");
		print(numbers);
		System.out.println();
		
		int randIndex = random.nextInt(numbers.length);
		int randNumber = numbers[randIndex];
		System.out.println("Random number: " + randNumber);
		System.out.println("The random number's location is in index " + binarySearch(numbers, randNumber));
	}
	
	public static int[] generateArray(int length) {
	    Random random = new Random();
	    int[] newArray = new int[length];
	    
	    for(int i = 0; i < newArray.length; i++) {
	        newArray[i] = random.nextInt(1001);
	    }
	    
	    return newArray;
	}
	
	public static int[] persistance(int[] numList) {
	    int[] newArray = new int[numList.length];
	    
	    for(int i = 0; i < numList.length; i++) {
	        newArray[i] = numList[i];
	    }
	    
	    return newArray;
	}
	
	public static void print(int[] numList) {
	    for (int num : numList) {
	        System.out.print(num + ", ");
	    }
	    System.out.println();
	}
	
	public static void insertionSort(int[] numList) {
	    for (int index = 1; index < numList.length; index++) {
	        int tempIn = index; //tempIn = 5
	        
	        for (int j = tempIn - 1; j >= 0; j--) { //j = 4
	            if (numList[j] > numList[tempIn]) { //18 > 15
	                int temp = numList[j]; // temp = 18
	                numList[j] = numList[tempIn]; // 18 --> 15
	                numList[tempIn] = temp; // 15 --> 18
	                tempIn--; //tempIn = 4....j = 3
	            }
	            else {
	                break;
	            }
	        }
	    }
	}
	
	public static void selectionSort(int[] numList) {
	    for (int index = 0; index < numList.length - 1; index++) { //index = 5
	        int secondIndex = index; //secondIndex = 5
	        int temp;
	        
	        for (int tempIn = index + 1; tempIn < numList.length; tempIn++) { //tempIn = 6
	            temp = numList[secondIndex]; //temp = 15, 12
	            if (temp > numList[tempIn]) { // 15 > 12 || 12 > 1
	                secondIndex = tempIn; //secondIndex = 6, 7
	                temp = numList[secondIndex]; //temp = 12, 1
	            }
	        }
	        
	        temp = numList[index];
            numList[index] = numList[secondIndex];
            numList[secondIndex] = temp;
	    }
	}
	
	public static int binarySearch(int[] numList, int randNumber) {
	    int first = 0;
	    int last = numList.length - 1;
	    int searchItem = randNumber;
	    int mid = 0;
	    
	    boolean found = false;
	    
	    while (first <= last && !found) {
	        mid = (first + last) / 2;
	        if (numList[mid] == searchItem) {
	            found = true;
	        }
	        else if (numList[mid] > searchItem) {
	            last = mid - 1;
	        }
	        else {
	            first = mid + 1;
	        }
	    }
	    
	    if (found) {
	        return mid;
	    }
	    else {
	        return -1;
	    }
	}
}

//Project #2 - Finds the elapsed time between two dates

import java.util.*;
import java.util.Scanner;
import java.lang.Math;

public class Main
{
	public static int month(int firstM, int secondM) {
	    return (firstM - secondM);
	}
	
	public static void main(String[] args) {
		//Declaration
		System.out.println("Welcome to a calender counter!");
		System.out.println("We will take two dates and find the elapsed time between them in any format you wish");
		int month1, day1, year1, month2, day2, year2;
		int elapsedMonth = 0, elapsedDay = 0, elapsedYear = 0;
		Scanner input = new Scanner(System.in);
		
		//Input
		System.out.println("Name the # of the month of your first date: ");
		month1 = input.nextInt();
		System.out.println("Name the # of the day of your first date: ");
		day1 = input.nextInt();
		System.out.println("Name the # of the year of your first date: ");
		year1 = input.nextInt();
		
		System.out.println("\nName the # of the month of your second date: ");
		month2 = input.nextInt();
		System.out.println("Name the # of the day of your second date: ");
		day2 = input.nextInt();
		System.out.println("Name the # of the year of your second date: ");
		year2 = input.nextInt();
		
		//Calculation
		elapsedDay = Math.abs(day2 - day1);
		if (year1 > year2) { //If first year is later than second year
		    elapsedYear = year1 - year2;
		    
		    if (month1 < month2) {
		        //if first month earlier than second month
		        //i.e. feb 2015 and april 2010
		        elapsedMonth = 12 - month1;
		    }
		    else if (month1 > month2) {
		        //if first month later than second month
		        //i.e. april 2015 and jan 2010
		        elapsedMonth = month1 - month2;
		    }
		    else if (month1 == month2) {
		        elapsedMonth = 0;
		    }
		}
		else if (year1 < year2) { //If first year is earlier than second year
		    elapsedYear = year2 - year1;
		    if (month1 < month2) {
		        //if first month earlier than second month
		        //i.e. feb 2010 and april 2015
		        elapsedMonth = month2 - month1;
		    }
		    else if (month1 > month2) {
		        //if first month later than second month
		        //i.e. april 2010 and jan 2015
		        elapsedMonth = 12 - month1;
		    }
		    else if (month1 == month2) {
		        elapsedMonth = 0;
		    }
		}
		else if (year1 == year2) { //If same year
		    elapsedYear = 0;
		    if (month1 < month2) {
		        //if first month earlier than second month
		        //i.e. feb 2015 and april 2015
		        elapsedMonth = month2 - month1;
		    }
		    else if (month1 > month2) {
		        //if first month later than second month
		        //i.e. april 2015 and jan 2015
		        elapsedMonth = month1 - month2;
		    }
		    else if (month1 == month2) {
		        elapsedMonth = 0;
		    }
		}
		
		//Output
		System.out.println("\n# of Days: " + elapsedDay);
		System.out.println("# of Months: " + elapsedMonth);
		System.out.println("# of Years: " + elapsedYear + "\n");
		
		//Called function to convert time

		
		System.out.println("End of Program.");
	}
}


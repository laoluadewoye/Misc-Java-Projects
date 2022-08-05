package io.github.laoluadewoye;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {

    public static DecimalFormat DF2;
    private final static DecimalFormat DF = new DecimalFormat("0.##");

    public static void setDF2(byte num) {
        //Declaration
        String hashes = "0.";

        //Calculation
        for (int i = 0; i < num; i++) {
            hashes = hashes + "#"; //Add a hash to represent each digit added.
        }

        //Output
         DF2 = new DecimalFormat(hashes);
    }

    public static double stdDevnth(int dtpnt, double mean) {
        return ((dtpnt - mean) * (dtpnt - mean));
    }

    public static void main(String[] args) {
        //Declaration
        Scanner input = new Scanner(System.in); //Scanner

        byte dataNum; // How many data points you want to enter
        byte digitNum; // How many digits after decimal point
        Integer[] dataSet = {}; //Where dataset will be stored

        byte pCount = 0, nCount = 0; //Positive and negative counts
        int min = 0, max = 0; //Minimum and maximum numbers
        int sum = 0; //Sum of all items
        double mean, median = 0.0, stanDev; //Mean, median, and standard deviation.

        //Additional variables needed
        int middle, comparison;
        double summation = 0.0;

        //Input
        System.out.print("Please enter how many data points you are entering: ");
        dataNum = input.nextByte();

        System.out.print("Please enter how many digits you want after the decimal point: ");
        digitNum = input.nextByte();

        //Calculation
        int temp;

        ArrayList<Integer> arrayList = null;
        for (byte i = 0; i < dataNum; i++) {
            System.out.print("Enter datapoint #" + (i + 1) + ": ");
            temp = input.nextInt(); //Say datapoint

            //Analysis
            if (temp > -1) {
                pCount++;
            } else {
                nCount++;
            }

            if (min == 0 || temp < min) {
                min = temp;
            }

            if (max == 0 || temp > max) {
                max = temp;
            }

            sum = sum + temp;

            //Add point to Array
            arrayList = new ArrayList<>(Arrays.asList(dataSet));
            arrayList.add(temp);
            dataSet = arrayList.toArray(dataSet);
        }

        //Set the DF variable
        setDF2(digitNum);

        //Get values for calculating median
        middle = min + ((max - min) / 2);
        comparison = max; //Max is now 8

        //median
        for (int i = 0; i < arrayList.size(); i++) {
            int val = middle - arrayList.get(i);
            if (Math.abs(val) < comparison) {
                median = arrayList.get(i);
                comparison = Math.abs(val);
            }
        }

        if (arrayList.size() % 2 == 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                int val = middle - arrayList.get(i);
                if (Math.abs(val) == comparison && arrayList.get(i) != median) {
                    median = median - comparison;
                }
            }
        }

        //mean
        mean = sum / arrayList.size();

        //standard deviation
        for (int i = 0; i < arrayList.size(); i++) {
            summation = summation + stdDevnth(arrayList.get(i), mean);
        }
        stanDev = Math.sqrt(summation / arrayList.size());

        //Output
        System.out.println("\nData Set Information");

        System.out.print("[ ");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println("]");

        System.out.println("# Positive: " + DF2.format(pCount));
        System.out.println("# Negative: " + DF2.format(nCount));
        System.out.println("Minimum: " + DF2.format(min));
        System.out.println("Maximum: " + DF2.format(max));
        System.out.println("Sum: " + DF2.format(sum));
        System.out.println("Median: " + DF2.format(median));
        System.out.println("Mean: " + DF2.format(mean));
        System.out.println("Standard Deviation: " + DF2.format(stanDev));

        System.out.println("End of Program.");
    }
}

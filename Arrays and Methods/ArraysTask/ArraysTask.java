package ArraysTask;

import java.util.Scanner;

public class ArraysTask {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int[] numbers = {12, 7, 25, 3, 18, 9, 30, 14};

        System.out.println("Question 1");
        System.out.println("Sum: " + sumArray(numbers));
        System.out.println("Average: " + averageArray(numbers));

        System.out.println("\nQuestion 2");
        int max = findMax(numbers);
        int min = findMin(numbers);
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        System.out.println("Difference: " + (max - min));

        System.out.println("\nQuestion 3");
        System.out.println("Even numbers: " + countEven(numbers));
        System.out.println("Enter a number to search for");
        int target = input.nextInt();
        int index = indexOf(numbers, target);
        if (index == -1) {
            System.out.println(target + " not found");
        } else {
            System.out.println(target + " found at index " + index);
        }

        System.out.println("\nQuestion 4");
        int[] reversed = reverseArray(numbers);
        System.out.print("Original: ");
        printArray(numbers);
        System.out.print("Reversed: ");
        printArray(reversed);

        System.out.println("\nQuestion 5");
        int[] grades = readGrades(5);
        int passed = countPassed(grades);
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + (grades.length - passed));

        System.out.println("\nBonus");
        System.out.println("Second largest: " + secondLargest(numbers));
        input.close();
    }

    static int sumArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }

    static double averageArray(int[] arr) {
        return (double) sumArray(arr) / arr.length;
    }

    static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    static int findMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    static int countEven(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    static int indexOf(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    static int[] reverseArray(int[] arr) {
        int[] reversed = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reversed[i] = arr[arr.length - 1 - i];
        }
        return reversed;
    }

    static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    static int[] readGrades(int size) {
        int[] grades = new int[size];
        for (int i = 0; i < grades.length; i++) {
            System.out.println("Enter grade " + (i + 1));

            // Keep asking until the grade is a whole number from 0 to 100.
            while (true) {
                if (input.hasNextInt()) {
                    int grade = input.nextInt();
                    if (grade >= 0 && grade <= 100) {
                        grades[i] = grade;
                        break;
                    }
                } else {
                    input.next();
                }
                System.out.println("Invalid grade. Enter a whole number from 0 to 100");
            }
        }
        return grades;
    }

    static int countPassed(int[] grades) {
        int passed = 0;
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] >= 50) {
                passed++;
            }
        }
        return passed;
    }

    static int secondLargest(int[] arr) {
        int largest = findMax(arr);
        int second = 0;
        boolean found = false;

        // Find the largest value below the maximum, without sorting.
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < largest) {
                if (!found || arr[i] > second) {
                    second = arr[i];
                    found = true;
                }
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Two different numbers are needed");
        }
        return second;
    }
}

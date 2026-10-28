package From_abdullah.CodingBat;

import java.util.Scanner;

public class JavaPatterns {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            showMenu();
            int choice = readNumber("Choose a pattern: ");

            if (choice == 0) {
                running = false;
                System.out.println("Goodbye!");
                continue;
            }

            if (choice < 1 || choice > 10) {
                System.out.println("Choose a number from 0 to 10.");
                continue;
            }

            int n = readNumber("Enter n: ");

            if (n <= 0) {
                System.out.println("Please enter a positive number.");
                continue;
            }

            if (choice == 8 && n > 26) {
                System.out.println("For the alphabet pattern, enter 1 to 26.");
                continue;
            }

            if (choice == 10 && n % 2 == 0) {
                System.out.println("For the plus sign, enter an odd number.");
                continue;
            }

            System.out.println();

            switch (choice) {
                case 1:
                    hollowSquare(n);
                    break;
                case 2:
                    repeatedNumberTriangle(n);
                    break;
                case 3:
                    reverseNumberTriangle(n);
                    break;
                case 4:
                    fullDiamond(n);
                    break;
                case 5:
                    hollowDiamond(n);
                    break;
                case 6:
                    xPattern(n);
                    break;
                case 7:
                    hourglass(n);
                    break;
                case 8:
                    alphabetTriangle(n);
                    break;
                case 9:
                    oddNumberTriangle(n);
                    break;
                case 10:
                    plusSign(n);
                    break;
            }
        }

        input.close();
    }

    public static void showMenu() {
        System.out.println("\n===== JAVA PATTERNS =====");
        System.out.println("1. Hollow square");
        System.out.println("2. Repeated number triangle");
        System.out.println("3. Reverse number triangle");
        System.out.println("4. Full diamond");
        System.out.println("5. Hollow diamond");
        System.out.println("6. X pattern");
        System.out.println("7. Hourglass");
        System.out.println("8. Descending alphabet triangle");
        System.out.println("9. Odd number triangle");
        System.out.println("10. Plus sign");
        System.out.println("0. Exit");
    }

    public static int readNumber(String message) {
        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    // 1. Print stars only on the four edges.
    public static void hollowSquare(int n) {
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (row == 1 || row == n || col == 1 || col == n) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }

            System.out.println();
        }
    }

    // 2. Repeat the row number row times.
    public static void repeatedNumberTriangle(int n) {
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= row; col++) {
                System.out.print(row + " ");
            }

            System.out.println();
        }
    }

    // 3. Start with n numbers and remove one each row.
    public static void reverseNumberTriangle(int n) {
        for (int row = n; row >= 1; row--) {
            for (int number = 1; number <= row; number++) {
                System.out.print(number + " ");
            }

            System.out.println();
        }
    }

    // 4. Print the top half, then the bottom half.
    public static void fullDiamond(int n) {
        for (int row = 1; row <= n; row++) {
            for (int space = 1; space <= n - row; space++) {
                System.out.print(" ");
            }

            for (int star = 1; star <= 2 * row - 1; star++) {
                System.out.print("*");
            }

            System.out.println();
        }

        for (int row = n - 1; row >= 1; row--) {
            for (int space = 1; space <= n - row; space++) {
                System.out.print(" ");
            }

            for (int star = 1; star <= 2 * row - 1; star++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    // 5. Print only the first and last star of each diamond row.
    public static void hollowDiamond(int n) {
        for (int row = 1; row <= n; row++) {
            for (int space = 1; space <= n - row; space++) {
                System.out.print(" ");
            }

            for (int col = 1; col <= 2 * row - 1; col++) {
                if (col == 1 || col == 2 * row - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }

        for (int row = n - 1; row >= 1; row--) {
            for (int space = 1; space <= n - row; space++) {
                System.out.print(" ");
            }

            for (int col = 1; col <= 2 * row - 1; col++) {
                if (col == 1 || col == 2 * row - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
    }

    // 6. The two diagonals form an X.
    public static void xPattern(int n) {
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (row == col || row + col == n + 1) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }

            System.out.println();
        }
    }

    // 7. Start wide, become narrow, then become wide again.
    public static void hourglass(int n) {
        for (int row = n; row >= 1; row--) {
            for (int space = 1; space <= n - row; space++) {
                System.out.print(" ");
            }

            for (int star = 1; star <= 2 * row - 1; star++) {
                System.out.print("*");
            }

            System.out.println();
        }

        for (int row = 2; row <= n; row++) {
            for (int space = 1; space <= n - row; space++) {
                System.out.print(" ");
            }

            for (int star = 1; star <= 2 * row - 1; star++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    // 8. Start each row at the n-th letter and move backwards.
    public static void alphabetTriangle(int n) {
        char firstLetter = (char) ('A' + n - 1);

        for (int row = 1; row <= n; row++) {
            for (int col = 0; col < row; col++) {
                char letter = (char) (firstLetter - col);
                System.out.print(letter);
            }

            System.out.println();
        }
    }

    // 9. Keep the odd number outside the loops so it does not reset.
    public static void oddNumberTriangle(int n) {
        long number = 1;

        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= row; col++) {
                System.out.print(number + " ");
                number += 2;
            }

            System.out.println();
        }
    }

    // 10. Print stars on the middle row and middle column.
    public static void plusSign(int n) {
        int middle = n / 2 + 1;

        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (row == middle || col == middle) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }

            System.out.println();
        }
    }
}
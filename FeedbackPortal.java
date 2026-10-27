import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FeedbackPortal {
    static String[] names = new String[100];
    static int[] ratings = new int[100];
    static String[] comments = new String[100];

    static int count = 0;
    static String filename = "feedback.txt";

    static BufferedReader input =
            new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        loadFeedback();

        boolean running = true;

        try {
            while (running) {
                showMenu();
                int choice = readInt("Choose: ");

                switch (choice) {
                    case 1:
                        addFeedback();
                        break;

                    case 2:
                        showAll();
                        break;

                    case 3:
                        showAverage();
                        break;

                    case 4:
                        searchByRating();
                        break;

                    case 5:
                        deleteFeedback();
                        break;

                    case 6:
                        saveFeedback();
                        break;

                    case 7:
                        if (saveFeedback()) {
                            running = false;
                            System.out.println("Goodbye!");
                        }
                        break;

                    default:
                        System.out.println("Choose a number from 1 to 7.");
                }
            }
        } catch (IOException e) {
            System.out.println("Input error: " + e.getMessage());
            System.out.println("Any unsaved changes were not saved.");
        }
    }

    public static void showMenu() {
        System.out.println("\n===== CUSTOMER FEEDBACK PORTAL =====");
        System.out.println("1. Add feedback");
        System.out.println("2. Show all feedback");
        System.out.println("3. Show average rating");
        System.out.println("4. Search by rating");
        System.out.println("5. Delete feedback");
        System.out.println("6. Save to file");
        System.out.println("7. Save and exit");
    }

    // Read keyboard input and detect when the input stream ends.
    public static String readLine(String message) throws IOException {
        System.out.print(message);
        String text = input.readLine();

        if (text == null) {
            throw new IOException("Keyboard input ended.");
        }

        return text.trim();
    }

    // Keep asking until the user enters a valid whole number.
    public static int readInt(String message) throws IOException {
        while (true) {
            String text = readLine(message);

            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    // Names and comments cannot be empty or contain the file separator.
    public static String readText(String message) throws IOException {
        while (true) {
            String text = readLine(message);

            if (text.isEmpty()) {
                System.out.println("This field cannot be empty.");
            } else if (text.contains("|")) {
                System.out.println("Please do not use the | character.");
            } else {
                return text;
            }
        }
    }

    public static int readRating() throws IOException {
        while (true) {
            int rating = readInt("Enter rating from 1 to 5: ");

            if (rating >= 1 && rating <= 5) {
                return rating;
            }

            System.out.println("Rating must be between 1 and 5.");
        }
    }

    public static void addFeedback() throws IOException {
        if (count >= names.length) {
            System.out.println("The feedback list is full.");
            return;
        }

        String name = readText("Enter customer name: ");
        int rating = readRating();
        String comment = readText("Enter comment: ");

        names[count] = name;
        ratings[count] = rating;
        comments[count] = comment;

        count++;

        System.out.println("Feedback added successfully.");
    }

    public static void printFeedback(int index) {
        System.out.println(
                (index + 1) + ". Name: " + names[index]
                + " | Rating: " + ratings[index]
                + " | Comment: " + comments[index]
        );
    }

    public static void showAll() {
        if (count == 0) {
            System.out.println("No feedback available.");
            return;
        }

        for (int i = 0; i < count; i++) {
            printFeedback(i);
        }
    }

    public static void showAverage() {
        if (count == 0) {
            System.out.println("No feedback to calculate an average.");
            return;
        }

        int total = 0;

        for (int i = 0; i < count; i++) {
            total += ratings[i];
        }

        double average = (double) total / count;

        System.out.printf("Average rating: %.2f%n", average);
    }

    public static void searchByRating() throws IOException {
        if (count == 0) {
            System.out.println("No feedback available.");
            return;
        }

        int wantedRating = readRating();
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (ratings[i] == wantedRating) {
                printFeedback(i);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No feedback matches this rating.");
        }
    }

    public static void deleteFeedback() throws IOException {
        if (count == 0) {
            System.out.println("No feedback to delete.");
            return;
        }

        showAll();

        int number = readInt("Enter feedback number to delete: ");

        if (number < 1 || number > count) {
            System.out.println("Invalid feedback number.");
            return;
        }

        int index = number - 1;

        // Move later entries one position to the left.
        for (int i = index; i < count - 1; i++) {
            names[i] = names[i + 1];
            ratings[i] = ratings[i + 1];
            comments[i] = comments[i + 1];
        }

        count--;

        // Clear the position that is no longer used.
        names[count] = null;
        ratings[count] = 0;
        comments[count] = null;

        System.out.println("Feedback deleted successfully.");
    }

    public static boolean saveFeedback() {
        try (
            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(filename))
        ) {
            for (int i = 0; i < count; i++) {
                writer.write(
                        names[i] + "|" + ratings[i] + "|" + comments[i]
                );
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving feedback: " + e.getMessage());
            return false;
        }

        System.out.println("Feedback saved successfully.");
        return true;
    }

    public static void loadFeedback() {
        File file = new File(filename);

        // A missing file is normal on the first run.
        if (!file.exists()) {
            System.out.println("No saved file found. Starting empty.");
            return;
        }

        try (
            BufferedReader reader =
                    new BufferedReader(new FileReader(file))
        ) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (count >= names.length) {
                    System.out.println(
                            "Storage is full. Remaining entries were not loaded."
                    );
                    break;
                }

                String[] parts = line.split("\\|", -1);

                if (parts.length != 3) {
                    System.out.println("Skipping invalid line " + lineNumber);
                    continue;
                }

                try {
                    String name = parts[0].trim();
                    int rating = Integer.parseInt(parts[1].trim());
                    String comment = parts[2].trim();

                    if (name.isEmpty() || comment.isEmpty()
                            || rating < 1 || rating > 5) {
                        System.out.println(
                                "Skipping invalid line " + lineNumber
                        );
                        continue;
                    }

                    names[count] = name;
                    ratings[count] = rating;
                    comments[count] = comment;
                    count++;

                } catch (NumberFormatException e) {
                    System.out.println(
                            "Skipping invalid rating on line " + lineNumber
                    );
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading feedback: " + e.getMessage());
        }

        System.out.println("Loaded feedback entries: " + count);
    }
}
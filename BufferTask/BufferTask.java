//Java Task: Try-Catch and File Handling
//Create a Java program that uses try-catch exception handling to copy the contents of a text file using BufferedReader and BufferedWriter.
//Requirements:

//Read the content from a source text file using BufferedReader.
//Copy the content into a destination text file using BufferedWriter.
//Use try-catch to handle exceptions such as file not found or I/O errors.
//Display a success message when the file is copied successfully.
//Display an appropriate error message if an exception occurs.
//Submission: Please upload your completed task to GitHub and reply to this Slack message with your repository link.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class BufferTask {
    public static void main(String[] args){
        String srcfile = "Source.txt";
        String Destfile = "Dest.txt";


        try (BufferedReader reader = 
            new BufferedReader(new FileReader(srcfile));
                BufferedWriter writer = 
                new BufferedWriter(new FileWriter(Destfile));){
                String line ;
                while ((line = reader.readLine()) !=null){
                    writer.write(line);
                    writer.newLine();
                    
                    System.out.println(line);
                }

                        } catch (IOException e) {
            System.out.println("Error copying file: " + e.getMessage());
            return;
            }
            System.out.println("File copied successfully.");
    }
    
}

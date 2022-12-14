package library.media;
import library.LibraryFunctions;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Objects;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Newspaper extends Media implements LibraryFunctions {
    private final String newspaperPublisher;
    private final LocalDate newspaperReleasedDate;
    private final int newspaperISSN;

    // This constructor takes a String of the entire line from the file, parse it, and then creates the object
    public Newspaper(String inputLine) {
        super();
        String[] values = inputLine.split(",");
        this.checkedIn = values[1].equals("in");
        this.title = values[2];
        this.creator = values[3];
        this.newspaperISSN = Integer.parseInt(values[4]);
        this.newspaperPublisher = values[5];
        this.newspaperReleasedDate = LocalDate.parse(values[6]);
    }

    // toString returns a String representation of the object
    @Override
    public String toString() {
        return "Newspaper{" +
                "newspaperPublisher='" + this.newspaperPublisher + '\'' +
                ", newspaperReleasedDate=" + this.newspaperReleasedDate +
                ", newspaperISSN=" + this.newspaperISSN +
                '}';
    }

    @Override
    public String displayInfo() {
        return this.getTitle() + "  (Written by " + this.creator + " )";
    }

    // The equals method checks whether two objects are equal or not
    // returns true if the key matches, false otherwise
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // checks if they are same objects
        // Checks if the type of class is different, or it's not a same object
        if (!(obj instanceof Newspaper other)) return false;
        return Objects.equals(this.title, other.getTitle());
    }

    // Returns the memory reference of an object in integer form
    @Override
    public int hashCode() {
        return Objects.hash(this.title);
    }

    // checkIn method reads from the file, replace "in" with "out" for the selected item, then writes back to the file
    @Override
    public void checkIn() {
        //construct a File object with the name of the input file
        // then use the File object to construct a Scanner object
        LinkedList<String> list = new LinkedList<>();
        try {
            Scanner inFile = new Scanner(new File(library.Utility.getLibraryFileName()));
            while (inFile.hasNextLine()) {
                String readLine = inFile.nextLine();
                if(readLine.indexOf(this.title) > 0) {
                    readLine = readLine.replaceFirst("out", "in");
                    this.checkInOut();
                }
                list.add(readLine);
            }
            inFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not open file " + library.Utility.getLibraryFileName());
        }
        try (PrintWriter fileWriter = new PrintWriter(library.Utility.getLibraryFileName())) {
            for(String readLine: list) {
                fileWriter.println(readLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file " + library.Utility.getLibraryFileName());
        }
    }

    // checkOut method reads from the file, replace "out" with "in" for the selected item, then writes back to the file
    @Override
    public void checkOut() {
        //construct a File object with the name of the input file
        // then use the File object to construct a Scanner object
        LinkedList<String> list = new LinkedList<>();
        try {
            Scanner inFile = new Scanner(new File(library.Utility.getLibraryFileName()));
            while (inFile.hasNextLine()) {
                String readLine = inFile.nextLine();
                if(readLine.indexOf(this.title) > 0) {
                    readLine = readLine.replaceFirst("in", "out");
                    this.checkInOut();
                }
                list.add(readLine);
            }
            inFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not open file " + library.Utility.getLibraryFileName());
        }
        try (PrintWriter fileWriter = new PrintWriter(library.Utility.getLibraryFileName())) {
            for(String readLine: list) {
                fileWriter.println(readLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file " + library.Utility.getLibraryFileName());
        }
    }
}
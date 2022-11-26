package library.media;
import library.LibraryFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class AudioBook extends Media implements LibraryFunctions {
    // Additional primitives for future use, included in dataset
    private final String bookAuthor;
    private final String readBy;
    private final int bookRating;
    private final int listeningTime;

    // This Constructor takes in an entire line from the library file and parses it
    public AudioBook(String inputLine) {
        super(); // Must be first
        int _bookRating;
        int _listeningTime;
        String[] values = inputLine.split("\\s*,\\s*", -1); // Split into known value locations
        this.checkedIn = values[1].equals("in");
        this.title = values[2];
        this.creator = values[3];
        this.bookAuthor = values[3];
        this.readBy = values[4];
        // Prevent parsing/casting errors
        try {
            _bookRating = Integer.parseInt(values[5]);
        } catch (NumberFormatException e) {
            _bookRating = -1;
        }
        this.bookRating = _bookRating;
        try {
            _listeningTime = Integer.parseInt(values[6]);
        } catch (NumberFormatException e) {
            _listeningTime = -1;
        }
        this.listeningTime = _listeningTime;
    }

    // Overridden functions
    @Override
    public String displayInfo() {
        return this.title +" written by: " + this.creator;
    }

    @Override
    public String toString() {
        return "Audiobook{" +
                "creator='" + creator + '\'' +
                ", title='" + title + '\'' +
                ", checkedIn=" + checkedIn +
                '}' + displayOtherInfo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AudioBook video = (AudioBook) o;
        return this.title.equals(video.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title);
    }

    // Interface function which is a wrapper for the checkInOut function
    @Override
    public void checkIn(){
        _checkInOut(true);
    }

    // Interface function which is a wrapper for the checkInOut function
    @Override
    public void checkOut(){
        _checkInOut(false);
    }

    // Public functions
    // Created to silence IDE warnings
    public String displayOtherInfo() {
        return "Read by: " + this.readBy + " Author: " + this.bookAuthor + " " + this.listeningTime + " minutes. Rated: " + this.bookRating + "/10";
    }

    // Single function to open a file and rewrite to it. Boolean determines the value
    // for this particular Video file, prevents redundant code. This function is unable to
    // call listMedia as it will strip away needed information for other objects (books, etc)
    // in the library file.
    private boolean _checkInOut(boolean checkInMedia) {
        File libraryFile = new File(library.Utility.getLibraryFileName());

        // ArrayList to act as buffer for filelines as they're read before they're written
        ArrayList<String> fileLines = new ArrayList<>();

        // Read from the file, catch exceptions and auto-close
        try (Scanner fileScanner = new Scanner(libraryFile)) {


            // Toggle availability by using the boolean argument
            String newAvailabilityValue = checkInMedia ? ",in," : ",out,";
            String oldAvailabilityValue = checkInMedia ? ",out," : ",in,";
            // Iterate thru the file to find the line with the title we need
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                // If the exact title is in the object name
                if(line.indexOf(this.title) > 0) {
                    // Toggle
                    line = line.replace(oldAvailabilityValue, newAvailabilityValue);
                    this.checkInOut();
                }
                // Add to buffer for rewriting
                fileLines.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(library.Utility.getLibraryFileName() + " was not found.");
            return false;
        }

        // Writes all contents back to the file. Automatically closes
        try (PrintWriter fileWriter = new PrintWriter(libraryFile)) {
            for(String line : fileLines) {
                fileWriter.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(library.Utility.getLibraryFileName() + " was not found.");
            return false;
        }

        return true;
    }
}
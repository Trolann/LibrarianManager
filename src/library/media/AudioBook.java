package library.media;
import library.LibraryFunctions;
import library.Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class AudioBook extends Media implements LibraryFunctions {
    private String bookAuthor;
    private String readBy;
    private int bookRating;
    private int listeningTime;

    // This Constructor takes in an entire line from the library file and parses it
    public AudioBook(String inputLine) {
        super(); // Must be first

        String[] values = inputLine.split("\\s*,\\s*", -1); // Split into known value locations

        this.checkedIn = values[1].equals("in");
        this.title = values[2];
        this.creator = values[3];
        this.bookAuthor = values[3];
        this.readBy = values[4];
        try {
            this.bookRating = Integer.parseInt(values[5]);
        } catch (NumberFormatException e) {
            this.bookRating = -1;
        }
        try {
            this.listeningTime = Integer.parseInt(values[6]);
        } catch (NumberFormatException e) {
            this.listeningTime = -1;
        }
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getReadBy() {
        return readBy;
    }

    public void setReadBy(String readBy) {
        this.readBy = readBy;
    }

    public int getBookRating() {
        return bookRating;
    }

    public void setBookRating(int bookRating) {
        this.bookRating = bookRating;
    }

    public int getListeningTime() {
        return listeningTime;
    }

    public void setListeningTime(int listeningTime) {
        this.listeningTime = listeningTime;
    }

    @Override
    boolean setCreator(String creator) {
        if (this.bookAuthor.equals("")) {
            return false; // Unable to set the creator.
        }
        this.creator = this.bookAuthor;
        return true;
    }

    @Override
    public String toString() {
        return "Audiobook{" +
                "creator='" + creator + '\'' +
                ", title='" + title + '\'' +
                ", checkedIn=" + checkedIn +
                '}';
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
    public boolean checkIn(){
        return _checkInOut(true);
    }

    // Interface function which is a wrapper for the checkInOut function
    @Override
    public boolean checkOut(){
        return _checkInOut(false);
    }

    // Single function to open a file and rewrite to it. Boolean determines the value
    // for this particular Video file
    private boolean _checkInOut(boolean checkInMedia) {
        File libraryFile = new File(Utility.getLibraryFileName());
        Scanner fileScanner = null; // Assigned to quiet down IDE warnings
        String nextLine;
        ArrayList<String> fileLines = new ArrayList<String>();
        String newAvailabilityValue = checkInMedia ? "in" : "out";
        String oldAvailabilityValue = checkInMedia ? "out" : "in";

        try {
            fileScanner = new Scanner(libraryFile);
        } catch (FileNotFoundException e) {
            System.out.println(Utility.getLibraryFileName() + " was not found.");
            return false;
        }

        while(fileScanner.hasNextLine()) {
            Scanner lineScanner = new Scanner(fileScanner.nextLine());

            while (lineScanner.hasNext()) {
                // Get the next value, store in string to be safe
                nextLine = lineScanner.next();

                if(nextLine.indexOf(this.title) > 0) {
                    nextLine = nextLine.replace(oldAvailabilityValue, newAvailabilityValue);
                }

                fileLines.add(nextLine);
            }
            lineScanner.close();
        }
        fileScanner.close();


        // Writes all contents back to the file. Automatically closes
        try (PrintWriter fileWriter = new PrintWriter(libraryFile)) {
            for(String line : fileLines) {
                fileWriter.println(line);
            }
        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }
}
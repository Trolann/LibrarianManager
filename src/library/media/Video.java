package library.media;
import library.LibraryFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Video extends Media implements LibraryFunctions {
    // Additional primitives for future use, included in dataset
    private final String videoDirector;
    private final String videoStarActor;
    private int videoRating;
    private int videoRuntime;

    // This Constructor takes in an entire line from the library file and parses it
    public Video(String inputLine) {
        super(); // Must be first

        String[] values = inputLine.split("\\s*,\\s*", -1); // Split into known value locations
        this.checkedIn = values[1].equals("in");
        this.title = values[2];
        this.creator = values[3];
        this.videoDirector = values[3];
        this.videoStarActor = values[4];
        // Prevent parsing/casting errors
        try {
            this.videoRating = Integer.parseInt(values[5]);
        } catch (NumberFormatException e) {
            this.videoRating = -1;
        }
        try {
            this.videoRuntime = Integer.parseInt(values[6]);
        } catch (NumberFormatException e) {
            this.videoRuntime = -1;
        }
    }

    // Overridden functions
    @Override
    public String displayInfo() {
        return this.getTitle() + " Directed by: " + this.videoDirector;
    }

    @Override
    public String toString() {
        return "Video{" +
                "creator='" + creator + '\'' +
                ", title='" + title + '\'' +
                ", checkedIn=" + checkedIn +
                '}' + displayOtherInfo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return this.title.equals(video.getTitle());
    }

    // Required for Media hashing
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
        return "Starring: " + this.videoStarActor + " " + this.videoRuntime + " minutes. Rated: " + this.videoRating + "/10 " + this.videoDirector;
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
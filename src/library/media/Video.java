package library.media;
import library.LibraryFunctions;
import library.Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Video extends Media implements LibraryFunctions {
    private String videoDirector;
    private String videoStarActor;
    private int videoRating;
    private int videoRuntime;

    // This Constructor takes in an entire line from the library file and parses it
    public Video(String inputLine) {
        super();
        String[] values = inputLine.split(",");
        this.title = values[1];
        this.creator = values[2];
        this.videoDirector = values[2];
        this.videoStarActor = values[3];
        try {
            this.videoRating = Integer.parseInt(values[5]);
        } catch (NumberFormatException e) {
            this.videoRating = -1;
        }
        try {
            this.videoRuntime = Integer.parseInt(values[4]);
        } catch (NumberFormatException e) {
            this.videoRuntime = -1;
        }
    }

    public String getVideoDirector() {
        return videoDirector;
    }

    public void setVideoDirector(String videoDirector) {
        this.videoDirector = videoDirector;
    }

    public String getVideoStarActor() {
        return videoStarActor;
    }

    public void setVideoStarActor(String videoStarActor) {
        this.videoStarActor = videoStarActor;
    }

    public int getVideoRating() {
        return videoRating;
    }

    public void setVideoRating(int videoRating) {
        this.videoRating = videoRating;
    }

    public int getVideoRuntime() {
        return videoRuntime;
    }

    public void setVideoRuntime(int videoRuntime) {
        this.videoRuntime = videoRuntime;
    }

    @Override
    boolean setCreator(String creator) {
        if (this.videoDirector.equals("")) {
            return false; // Unable to set the creator.
        }
        this.creator = this.videoDirector;
        return true;
    }

    @Override
    public String toString() {
        return "Video{" +
                "fiveStarRating=" + videoRating +
                ", runtimeMinutes=" + videoRuntime +
                ", creator='" + creator + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
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
        File libraryFile = new File(library.Utility.getLibraryFileName());
        Scanner fileScanner = null; // Assigned to quiet down IDE warnings
        String nextLine;
        ArrayList<String> fileLines = new ArrayList<String>();
        String newAvailabilityValue = checkInMedia ? "in" : "out";
        String oldAvailabilityValue = checkInMedia ? "out" : "in";

        try {
            fileScanner = new Scanner(libraryFile);
        } catch (FileNotFoundException e) {
            System.out.println(library.Utility.getLibraryFileName() + " was not found.");
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

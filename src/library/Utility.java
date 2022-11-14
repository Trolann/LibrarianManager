package library;

import library.media.AudioBook;
import library.media.Media;
import library.media.Video;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Utility {
    public static void main(String[] args) {
        LinkedList<Media> mediaList = listMedia();
        for(Media media : mediaList) {
            System.out.println(media.toString());
        }
    }

    // Filename (relative location) of the file with library information.
    // File must start with mediatype for each entry or the entry will be discarded.
    public final static String libraryFileName = "src/library/library.txt";

    public static String getLibraryFileName() {
        return libraryFileName;
    }

    // This function returns a LinkedList of Media objects for display in the GUI
    public static LinkedList<Media> listMedia() {
        LinkedList<Media> returnList = new LinkedList<Media>();

        File libraryFile = new File(library.Utility.getLibraryFileName());
        Scanner fileScanner = null; // Assigned to quiet down IDE warnings
        String nextLine;

        try {
            fileScanner = new Scanner(libraryFile);
        } catch (FileNotFoundException e) {
            System.out.println(library.Utility.getLibraryFileName() + " was not found.");
            return returnList;
        }

        while(fileScanner.hasNextLine()) {
            Scanner lineScanner = new Scanner(fileScanner.nextLine());
            lineScanner.useDelimiter("\n");

            while (lineScanner.hasNext()) {
                // Get the next value, store in string to be safe
                nextLine = lineScanner.next();
                System.out.println(nextLine);
                String[] splitValues = nextLine.split(",");


                // This switch determines which Class to pass the nextLine to for creation
                // and then casts the object to the Media type for display.
                switch(splitValues[0]) {
                    // Starts with..  ..add to return ...and auto-cast to Media
                    case "video" -> { returnList.add(new Video(nextLine)); }
                    case "audiobook" -> { returnList.add(new AudioBook(nextLine)); }
                    // Add additional Media objects below, update type and Type
                    // case "type" -> { returnList.add(new Type(nextLine)); }
                }
            }
            lineScanner.close();
        }
        fileScanner.close();

        return returnList;
    }
}

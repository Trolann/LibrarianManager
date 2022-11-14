package library;

import library.media.AudioBook;
import library.media.Media;
import library.media.Video;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Utility {
    public static void main(String[] args) {
        HashMap<String, Media> mediaList = listMedia();
        mediaList.forEach((key, value) -> {
            System.out.println(value.toString());
            });
    }

    // Filename (relative location) of the file with library information.
    // File must start with mediatype for each entry or the entry will be discarded.
    public final static String libraryFileName = "src/library/library.txt";

    public static String getLibraryFileName() {
        return libraryFileName;
    }

    // This function returns a LinkedList of Media objects for display in the GUI
    public static HashMap<String, Media> listMedia() {
        HashMap<String, Media> returnList = new HashMap<String, Media>();

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
                //System.out.println(nextLine);
                String[] splitValues = nextLine.split(",");


                // This switch determines which Class to pass the nextLine to for creation
                // and then casts the object to the Media type for display.
                switch(splitValues[0]) {
                    // Starts with..  ..add to return ...and auto-cast to Media
                    case "video" -> {
                        Video o = new Video(nextLine);
                        returnList.put(o.getTitle(), o);
                    }
                    case "audiobook" -> {
                        AudioBook o = new AudioBook(nextLine);
                        returnList.put(o.getTitle(), o);
                    }
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

package library;

import library.media.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Utility {
    public static void main(String[] args) {
        //randomMedia();
        LibraryFrame mainGUI = new LibraryFrame();
        mainGUI.setSize(1200, 170);

    }

    // Filename (relative location) of the file with library information.
    // File must start with mediatype for each entry or the entry will be discarded.
    public final static String libraryFileName = "src/library/library.txt";

    // Sub-classes access filename, this removed magic names

    public static String getLibraryFileName() {
        return libraryFileName;
    }


    // This function is the recursive function for the listMedia function.
    // This function takes in a growing _returnMap and shrinking _fileScanner
    // and returns a complete _returnMap after exhausting the _fileScanner.
    public static HashMap<String, LibraryFunctions> _listMedia(HashMap<String, LibraryFunctions> _returnMap, Scanner _fileScanner) {

        // Base case, no more lines
        if (!_fileScanner.hasNext()) {
            return _returnMap;
        }
        // Now we need to scan this line
        Scanner lineScanner = new Scanner(_fileScanner.nextLine());
        lineScanner.useDelimiter("\n");

        while (lineScanner.hasNext()) {
            // Get the next value, store in string to be safe
            String nextLine = lineScanner.next();

            // Delim the line into an array to get the media type (first value)
            String[] splitValues = nextLine.split(",");

            // This switch determines which Class to pass the nextLine to for creation
            // and then casts the object to the LibraryFunctions type for display.
            switch(splitValues[0]) {
                // Starts with..  ..add to return ...and auto-cast to Media
                case "video" -> {
                    Video o = new Video(nextLine);
                    _returnMap.put(o.getTitle(), o);
                }
                case "audiobook" -> {
                    AudioBook o = new AudioBook(nextLine);
                    _returnMap.put(o.getTitle(), o);
                }
                case "book" -> {
                    Book o = new Book(nextLine);
                    _returnMap.put(o.getTitle(), o);
                }
                case "publishedPaper" -> {
                    PublishedPaper o = new PublishedPaper(nextLine);
                    _returnMap.put(o.getTitle(), o);
                }
                case "newspaper" -> {
                    Newspaper o = new Newspaper(nextLine);
                    _returnMap.put(o.getTitle(), o);
                }
                case "eTextbook" -> {
                    eTextbook o = new eTextbook(nextLine);
                    _returnMap.put(o.getTitle(), o);
                }
            }
        }
        lineScanner.close(); // Cleanup

        // Recur passing growing _returnMap and shrinking _fileScanner
        return _listMedia(_returnMap, _fileScanner);
    }

    // This function returns a HashMap keyed by the Title of each media.
    // This function uses recursive calls to _listMedia to return a HashMap
    // from  getLibraryFileName().
    public static HashMap<String, LibraryFunctions> listMedia() {
        HashMap<String, LibraryFunctions> returnMap = new HashMap<>();
        File libraryFile = new File(library.Utility.getLibraryFileName());
        Scanner fileScanner; // Assigned to quiet down IDE warnings

        // Try and open the file
        try {
            fileScanner = new Scanner(libraryFile);
        } catch (FileNotFoundException e) {
            System.out.println(library.Utility.getLibraryFileName() + " was not found.");
            return returnMap;
        }

        // Make recursive call to read from fileScanner into returnMap
        returnMap = _listMedia(returnMap, fileScanner);
        fileScanner.close(); // Cleanup

        return returnMap;
    }

    // returns a random Media
    public static LibraryFunctions getRandom() {
        Map<String, LibraryFunctions> mediaList = listMedia();
        Random r = new Random(); // get a random number
        int totalLength = mediaList.size();
        int randomNumber = r.nextInt(totalLength); // bound random number by total length go hashmap

        for (Map.Entry<String, LibraryFunctions> entry : mediaList.entrySet()) {
            if (randomNumber == 0) {
                return entry.getValue(); // return current iteration if random number has been decremented down to zero
            } else randomNumber--; // decrement random number
        }
        return null;
    }
}
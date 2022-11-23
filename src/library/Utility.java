package library;

import library.media.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Utility {
    public static void main(String[] args) {
        randomMedia();
        LibraryFrame mainGUI = new LibraryFrame();
        mainGUI.setSize(681, 170);
        HashMap<String, Media> mediaList = listMedia();
        mediaList.forEach((key, value) -> {
            System.out.println(value.toString());
            });
    }

    // Filename (relative location) of the file with library information.
    // File must start with mediatype for each entry or the entry will be discarded.
    public final static String libraryFileName = "src/library/library.txt";

    // Fake search function just for testing, will be deleted/modified/created by Osman
    public static LinkedList<Media> searchMedia(String searchString, String searchFilter) {
        LinkedList<Media> returnList = new LinkedList<Media>();
        HashMap<String, Media> mediaList = listMedia();
        mediaList.forEach((key, value) -> {
            returnList.add(value);
        });
        return returnList; // Returns everything
    }

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
                    case "book" -> {
                        Book o = new Book(nextLine);
                        returnList.put(o.getTitle(), o);
                    }
                    case "publishedPaper" -> {
                        PublishedPaper o = new PublishedPaper(nextLine);
                        returnList.put(o.getTitle(), o);
                    }
                }
            }
            lineScanner.close();
        }
        fileScanner.close();

        return returnList;
    }

    // returns a random media
    public static String randomMedia() {
        Stack<Media> stack = new Stack<>();
        int size = 10;
        while(stack.size() < size) {
            stack.push(getRandom());
        }
        Scanner userInput = new Scanner(System.in);
        do {
            if(stack.isEmpty()) stack.push(getRandom());
            Media peekedValue = stack.peek();
            System.out.println("Do you want this media? " + peekedValue);
            System.out.print("Please enter Y/N: ");
            String input = userInput.next();
            if(input.toUpperCase().equals("Y")) {
                System.out.println("\nTake the media. " + peekedValue);
                //search(Media title) ---> title ->store the popped value
                stack.clear();
                break;
            }
            else if( !input.toUpperCase().equals("Y") && !input.toUpperCase().equals("N"))
                System.out.println("Please enter a valid Value");
            else {
                if(!stack.isEmpty()) stack.pop();
            }
            System.out.println();
        } while(true);
        return null; // needs to return title after calling the search method
    }

    // returns a random Media
    public static Media getRandom() {
        Map<String, Media> mediaList = listMedia();
        Random r = new Random(); // get a random number
        int totalLength = mediaList.size();
        int randomNumber = r.nextInt(totalLength); // bound random number by total length go hashmap

        for (Map.Entry<String, Media> entry : mediaList.entrySet()) {
            if (randomNumber == 0) {
                return entry.getValue(); // return current iteration if random number has been decremented down to zero
            } else randomNumber--; // decrement random number
        }
        return null;
    }
}


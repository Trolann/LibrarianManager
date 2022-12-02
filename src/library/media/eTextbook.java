package library.media;

import library.LibraryFunctions;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Objects;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class eTextbook extends Media implements LibraryFunctions {
    private String eTextbookPublisher;
    private LocalDate eTextbookReleasedDate;
    private String eTextbookISBN;

    // // This constructor takes a String of the entire line from the file, parse it, and then creates the object
    public eTextbook(String inputLine) {
        super();
        String[] values = inputLine.split(",");
        this.checkedIn = values[1].equals("in");
        this.title = values[2];
        this.creator = values[3];
        this.eTextbookISBN = values[4];
        this.eTextbookPublisher = values[5];
        this.eTextbookReleasedDate = LocalDate.parse(values[6]);
    }

    // Setters
    public void seteTextbookPublisher(String eTextbookPublisher) {
        this.eTextbookPublisher = eTextbookPublisher;
    }
    public void seteTextbookReleasedDate(LocalDate eTextbookReleasedDate) {
        this.eTextbookReleasedDate = eTextbookReleasedDate;
    }
    public void seteTextbookISBN(String eTextbookISBN) {
        this.eTextbookISBN = eTextbookISBN;
    }
    // Getters
    public String geteTextbookPublisher() {
        return eTextbookPublisher;
    }
    public LocalDate geteTextbookReleasedDate() {
        return eTextbookReleasedDate;
    }
    public String geteTextbookISBN() {
        return eTextbookISBN;
    }

    // toString returns a String representation of the object
    @Override
    public String toString() {
        return "eTextbook{" +
                "eTextbookPublisher='" + eTextbookPublisher + '\'' +
                ", eTextbookReleasedDate=" + eTextbookReleasedDate +
                ", eTextbookISBN=" + eTextbookISBN +
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
        if (this == obj) return true;   // checks if they are same objects
        // Checks whether the type of class is different or not or it's not a same object
        if (!(obj instanceof eTextbook)) return false;
        eTextbook other = (eTextbook) obj;
        return Objects.equals(this.title, other.getTitle());
    }

    // Returns the memory reference of an object in integer form
    @Override
    public int hashCode() {
        return Objects.hash(this.title);
    }

    // checkIn method reads from the file, replace "in" with "out" for the desired title, then writes back to the file
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
            System.out.println("File not found.");
        }
        try (PrintWriter fileWriter = new PrintWriter(library.Utility.getLibraryFileName())) {
            for(String readLine: list) {
                fileWriter.println(readLine);
            }
        } catch (FileNotFoundException e) {
        }
    }

    // checkOut method reads from the file, replace "out" with "in" for the desired title, then writes back to the file
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
            System.out.println("File not found.");
        }
        try (PrintWriter fileWriter = new PrintWriter(library.Utility.getLibraryFileName())) {
            for(String readLine: list) {
                fileWriter.println(readLine);
            }
        } catch (FileNotFoundException e) {
        }
    }
}
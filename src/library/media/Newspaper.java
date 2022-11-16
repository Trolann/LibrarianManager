package library.media;

import library.LibraryFunctions;

import java.time.LocalDate;
import java.util.Objects;
import java.io.*;
import java.util.Scanner;

public class Newspaper extends Media implements LibraryFunctions {
    private String newspaperPublisher;
    private LocalDate newspaperReleasedDate;
    private int newspaperISSN;


    //Constructor
    public Newspaper(String inputLine) {
        super(); // Must be first
        String[] values = inputLine.split(",", -1); // Split into known value locations
        this.checkedIn = values[1].equals("in");
        this.title = values[2];
        this.creator = values[3];
        this.newspaperISSN = Integer.parseInt(values[4]);
        this.newspaperPublisher = values[5];
        this.newspaperReleasedDate = LocalDate.parse(values[6]);
    }

        public void setNewspaperPublisher(String newspaperPublisher) {
            this.newspaperPublisher = newspaperPublisher;
        }
        public String getNewspaperPublisher() {
            return newspaperPublisher;
        }

        public void setNewspaperReleasedDate(LocalDate newspaperReleasedDate) {
            this.newspaperReleasedDate = newspaperReleasedDate;
        }
        public LocalDate getNewspaperReleasedDate() {
            return newspaperReleasedDate;
        }

        public void setNewspaperISSN(int newspaperISSN) {
            this.newspaperISSN = newspaperISSN;
        }
        public int getNewspaperISSN() {
            return newspaperISSN;
        }

    @Override
    public String toString() {
        return "Newspaper{" +
                "newspaperPublisher='" + newspaperPublisher + '\'' +
                ", newspaperReleasedDate=" + newspaperReleasedDate +
                ", newspaperISSN=" + newspaperISSN +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Newspaper newspaper)) return false;
        return getNewspaperISSN() == newspaper.getNewspaperISSN() &&
                getNewspaperPublisher().equals(newspaper.getNewspaperPublisher()) &&
                getNewspaperReleasedDate().equals(newspaper.getNewspaperReleasedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNewspaperPublisher(), getNewspaperReleasedDate(), getNewspaperISSN());
    }

    @Override
    public boolean checkIn() {
        //construct a File object with the name of the input file
        // then use the File object to construct a Scanner object
        try {
            Scanner inFile = new Scanner(new File("library.txt"));
            while (inFile.hasNextLine()) {
                String input = inFile.nextLine();
                Scanner readWord = new Scanner(input);
                readWord.useDelimiter(",");
                while(readWord.hasNext()) {
                    String value =  readWord.next();
                    if (value.equals("in")) {
                        return true;
                    }
                    else {
                        checkOut();
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return false;
    }

    @Override
    public boolean checkOut() {
        try {
            Scanner inFile = new Scanner(new File("library.txt"));
            while (inFile.hasNextLine()) {
                String input = inFile.nextLine();
                Scanner readWord = new Scanner(input);
                readWord.useDelimiter(",");
                while (readWord.hasNext()) {
                    String value = readWord.next();
                    if (value.equals("out")) {
                        return true;
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return false;
    }

    @Override
    String displayInfo() {
        return null;
    }
}
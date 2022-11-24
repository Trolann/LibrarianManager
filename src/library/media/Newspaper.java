package library.media;

import library.LibraryFunctions;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public String displayInfo() {
        return this.title + " written by " + this.creator;
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
        ArrayList<String> input = new ArrayList<>();
        try {
            Scanner inFile = new Scanner(new File(library.Utility.getLibraryFileName()));
            while (inFile.hasNextLine()) {
                String readLine = inFile.nextLine();
                if(readLine.indexOf(this.title) > 0) {
                    readLine = readLine.replaceFirst("out", "in");
                    this.checkInOut();
                }
                input.add(readLine);
            }
            inFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        try (PrintWriter fileWriter = new PrintWriter(library.Utility.getLibraryFileName())) {
            for(String readLine: input) {
                fileWriter.println(readLine);
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean checkOut() {
        //construct a File object with the name of the input file
        // then use the File object to construct a Scanner object
        ArrayList<String> input = new ArrayList<>();
        try {
            Scanner inFile = new Scanner(new File(library.Utility.getLibraryFileName()));
            while (inFile.hasNextLine()) {
                String readLine = inFile.nextLine();
                if(readLine.indexOf(this.title) > 0) {
                    readLine = readLine.replaceFirst("in", "out");
                    this.checkInOut();
                }
                input.add(readLine);
            }
            inFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        try (PrintWriter fileWriter = new PrintWriter(library.Utility.getLibraryFileName())) {
            for(String readLine: input) {
                fileWriter.println(readLine);
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return false;
    }
}
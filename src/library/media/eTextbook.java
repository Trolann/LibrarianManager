package library.media;

import library.LibraryFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class eTextbook extends Media implements LibraryFunctions {
    private String eTextbookPublisher;
    private LocalDate eTextbookReleasedDate;
    private String eTextbookISBN;

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

    public void seteTextbookPublisher(String eTextbookPublisher) {
        this.eTextbookPublisher = eTextbookPublisher;
    }
    public String geteTextbookPublisher() {
        return eTextbookPublisher;
    }

    public void seteTextbookReleasedDate(LocalDate eTextbookReleasedDate) {
        this.eTextbookReleasedDate = eTextbookReleasedDate;
    }
    public LocalDate geteTextbookReleasedDate() {
        return eTextbookReleasedDate;
    }

    public void seteTextbookISBN(String eTextbookISBN) {
        this.eTextbookISBN = eTextbookISBN;
    }
    public String geteTextbookISBN() {
        return eTextbookISBN;
    }

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
        return this.title + " written by " + this.creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof eTextbook eTextbook)) return false;
        return geteTextbookISBN() == eTextbook.geteTextbookISBN() &&
                geteTextbookPublisher().equals(eTextbook.geteTextbookPublisher()) &&
                geteTextbookReleasedDate().equals(eTextbook.geteTextbookReleasedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(geteTextbookPublisher(), geteTextbookReleasedDate(), geteTextbookISBN());
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
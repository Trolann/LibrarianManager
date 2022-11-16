package library.media;

import library.LibraryFunctions;

import java.time.LocalDate;
import java.util.Objects;
import java.io.*;
import java.util.Scanner;

public class Newspaper extends Media implements LibraryFunctions {

    private String author;
    private String publisher;
    private LocalDate releasedDate;
    private int ISSN;

    //Constructor
    public Newspaper(String creator, String title, boolean checkedIn) {
        super(creator, title, checkedIn);
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setISSN(int ISSN) {
        this.ISSN = ISSN;
    }

    public int getISSN() {
        return ISSN;
    }

    @Override
    boolean setCreator(String creator) {
        if (this.author.equals(""))
            return false;
        this.creator = this.author;
        return true;
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", releasedDate=" + releasedDate +
                ", ISSN=" + ISSN +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Newspaper)) return false;
        Newspaper newspaper = (Newspaper) o;
        return getISSN() == newspaper.getISSN() && getAuthor().equals(newspaper.getAuthor()) &&
                getPublisher().equals(newspaper.getPublisher()) &&
                getReleasedDate().equals(newspaper.getReleasedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getPublisher(), getReleasedDate(), getISSN());
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

}
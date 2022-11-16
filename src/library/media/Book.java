package library.media;

import library.LibraryFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Book extends Media implements LibraryFunctions {

    private String bookPublisher;
    private LocalDate bookPublicationDate;

    public Book(String inputLine) {
        super();
        String[] values = inputLine.split(",", -1);
        this.checkedIn = values[1].equals("in");
        this.title = values[2];
        this.creator = values[3];
        this.bookPublisher = values[3];
        this.bookPublicationDate = LocalDate.parse(values[4]);
    }

    public LocalDate getBookPublicationDate() {
        return bookPublicationDate;
    }

    public void setBookPublicationDate(LocalDate bookPublicationDate) {
        this.bookPublicationDate = bookPublicationDate;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    @Override
    boolean setCreator(String creator) {
        return false;
    }

    @Override
    public boolean checkIn() {
        return false;
    }

    @Override
    public boolean checkOut() {
        return false;
    }

}
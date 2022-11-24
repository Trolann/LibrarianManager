package library.media;

import library.LibraryFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

public class Book extends Media implements LibraryFunctions {

    private String bookPublisher, bookISBN;
    private LocalDate bookPublicationDate;

    public Book(String inputLine) {
        super();
        String[] values = inputLine.split(",");
        this.checkedIn = values[1].equals("in");
        this.title = values[2];
        this.creator = values[3];
        this.bookISBN = values[4];
        this.bookPublisher = values[5];
        this.bookPublicationDate = LocalDate.parse(values[6]);
    }

    @Override
    public String toString() {
        return "Book{" +
                "creator='" + creator + '\'' +
                ", title='" + title + '\'' +
                ", checkedIn=" + checkedIn +
                '}';
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

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title);
    }

    @Override
    public boolean checkIn() { return handler(false); }

    @Override
    public boolean checkOut() { return handler(true); }

    private boolean handler(boolean availability) {
        Queue<String> fileLines = new LinkedList<>();

        try(Scanner inputFile = new Scanner(new File(library.Utility.getLibraryFileName()))) {
            while (inputFile.hasNextLine()) {
                String line = inputFile.nextLine();

                if (line.indexOf(this.title) > 0) {
                    line = line.replaceFirst(availability ? "in" : "out", !availability ? "in" : "out");
                    this.checkInOut();
                }
                fileLines.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println(library.Utility.getLibraryFileName() + " was not in the specified directory.");
            return false;
        }
        try(PrintWriter outputFile = new PrintWriter(library.Utility.getLibraryFileName())){
            while(!fileLines.isEmpty()) {
                outputFile.println(fileLines.poll());
            }
        } catch (FileNotFoundException e) {
            System.out.println(library.Utility.getLibraryFileName() + " was not in the specified directory.");
            return false;
        }
        return true;
    }

    @Override
    public String displayInfo() {
        return this.title +" by " + this.creator;
    }
}

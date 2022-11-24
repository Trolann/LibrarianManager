package library.media;

import library.LibraryFunctions;

import java.time.LocalDate;
import java.util.Objects;

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
    public boolean checkIn() {
        return false;
    }

    @Override
    public boolean checkOut() {
        return false;
    }

    @Override
    public String displayInfo() {
        return this.title +" by " + this.creator;
    }
}

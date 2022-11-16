package library.media;

import library.LibraryFunctions;

import java.time.LocalDate;
import java.util.Objects;

public class eTextbook extends Media implements LibraryFunctions {

    private String author;
    private String publisher;
    private LocalDate releasedDate;
    private int ISBN;

    public eTextbook(String creator, String title, boolean checkedIn) {
        super(creator, title, checkedIn);
    }

    public void setAuthor(String author) { this.author = author; }
    public String getAuthor() { return author; }

    public void setPublisher(String publisher) { this.publisher = publisher; }
    public String getPublisher() { return publisher; }

    public void setReleasedDate(LocalDate releasedDate) { this.releasedDate = releasedDate; }
    public LocalDate getReleasedDate() { return releasedDate; }

    public void setISBN(int ISBN) { this.ISBN = ISBN; }
    public int getISBN() { return ISBN; }

    @Override
    boolean setCreator(String creator) {
        if(this.author.equals(""))
            return false;
        this.creator = this.author;
        return true;
    }

    @Override
    public String toString() {
        return "eTextbook{" +
                "author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", releasedDate=" + releasedDate +
                ", ISBN=" + ISBN +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof eTextbook)) return false;
        eTextbook eTextbook = (library.media.eTextbook) o;
        return getISBN() == eTextbook.getISBN() && getAuthor().equals(eTextbook.getAuthor()) &&
                getPublisher().equals(eTextbook.getPublisher()) &&
                getReleasedDate().equals(eTextbook.getReleasedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getPublisher(), getReleasedDate(), getISBN());
    }

    @Override
    public boolean checkIn() {
        return false;
    }

    @Override
    public boolean checkOut() {
        return false;
    }
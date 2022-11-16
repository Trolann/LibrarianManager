package library.media;

import library.LibraryFunctions;

import java.time.LocalDate;
import java.util.Objects;

public class eTextbook extends Media implements LibraryFunctions {
    private String eTextbookPublisher;
    private LocalDate eTextbookReleasedDate;
    private int eTextbookISBN;

    public eTextbook(String inputLine) {
        super();
        String[] values = inputLine.split(",");
        this.checkedIn = values[1].equals("in");
        this.title = values[2];
        this.creator = values[3];
        this.eTextbookISBN = Integer.parseInt(values[4]);
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

    public void seteTextbookISBN(int eTextbookISBN) {
        this.eTextbookISBN = eTextbookISBN;
    }
    public int geteTextbookISBN() {
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
        return false;
    }

    @Override
    public boolean checkOut() {
        return false;
    }

    @Override
    String displayInfo() {
        return null;
    }
}
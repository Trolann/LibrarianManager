package library.media;

import library.LibraryFunctions;

import java.time.LocalDate;
import java.util.Objects;

public class PublishedPaper extends Media implements LibraryFunctions {

    private String publishedPaperTopic, publishedPaperISSN;
    private LocalDate publishedPaperPublicationDate;

    public PublishedPaper(String inputLine) {
        super();
        String[] values = inputLine.split(",");
        this.checkedIn = values[1].equals("in");
        this.title = values[2];
        this.creator = values[3];
        this.publishedPaperISSN = values[4];
        this.publishedPaperTopic = values[5];
        this.publishedPaperPublicationDate = LocalDate.parse(values[6]);
    }

    @Override
    public String toString() {
        return "PublishedPaper{" +
                "creator='" + creator + '\'' +
                ", title='" + title + '\'' +
                ", checkedIn=" + checkedIn +
                '}';
    }

    public String getPublishedPaperTopic() {
        return publishedPaperTopic;
    }

    public void setPublishedPaperTopic(String publishedPaperTopic) {
        this.publishedPaperTopic = publishedPaperTopic;
    }

    public LocalDate getPublishedPaperPublicationDate() {
        return publishedPaperPublicationDate;
    }

    public void setPublishedPaperPublicationDate(LocalDate publishedPaperPublicationDate) {
        this.publishedPaperPublicationDate = publishedPaperPublicationDate;
    }

    public String getPublishedPaperISSN() {
        return publishedPaperISSN;
    }

    public void setPublishedPaperISSN(String publishedPaperISSN) {
        this.publishedPaperISSN = publishedPaperISSN;
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

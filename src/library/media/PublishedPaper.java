package library.media;

import library.LibraryFunctions;

import java.time.LocalDate;

public class PublishedPaper extends Media implements LibraryFunctions {

    private String publishedPaperTopic;
    private LocalDate publishedPaperPublicationDate;

    public PublishedPaper(String inputLine) {
        super();
        String[] values = inputLine.split(",", -1);
        this.checkedIn = values[1].equals("in");
        this.title = values[2];
        this.creator = values[3];
        this.publishedPaperTopic = values[3];
        this.publishedPaperPublicationDate = LocalDate.parse(values[4]);
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


    @Override
    public boolean checkIn() {
        return false;
    }

    @Override
    public boolean checkOut() {
        return false;
    }

    @Override
    boolean setCreator(String creator) {
        return false;
    }
}

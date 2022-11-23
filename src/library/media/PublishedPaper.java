package library.media;

import library.LibraryFunctions;

import java.time.LocalDate;

public class PublishedPaper extends Media implements LibraryFunctions {

    private String publishedPaperTopic;

    @Override
    public String toString() {
        return "PublishedPaper{" +
                "publishedPaperTopic='" + publishedPaperTopic + '\'' +
                ", publishedPaperPublicationDate=" + publishedPaperPublicationDate +
                ", creator='" + creator + '\'' +
                ", title='" + title + '\'' +
                ", checkedIn=" + checkedIn +
                '}';
    }

    private LocalDate publishedPaperPublicationDate;

    public PublishedPaper(String inputLine) {
        super();
        String[] values = inputLine.split(",");
        this.checkedIn = values[1].equals("in");
        this.title = values[2];
        this.creator = values[3];
        this.publishedPaperTopic = values[4];
        this.publishedPaperPublicationDate = LocalDate.parse(values[5]);
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
    public String displayInfo() {
        return "nullpaper";
    }
}

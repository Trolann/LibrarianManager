package library.media;

import java.time.LocalDate;

abstract class Media implements Comparable<Media> {
    protected LocalDate publishedDate;
    protected String creator;
    protected String title;

    // Abstract methods
    abstract boolean setCreator(String creator); // Sets private String creator; returns True if properly set

    @Override
    public int compareTo(Media media) {
        // Subclass objects are cast to Media and compared with LocalDates' built-in compareTo().
        return this.publishedDate.compareTo(media.publishedDate);
    }

    // Settings and getters be below
    public String getCreator() { return this.creator; } // Gets private String creator;

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

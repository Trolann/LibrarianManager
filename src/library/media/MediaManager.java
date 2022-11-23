package library.media;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class MediaManager {
    private HashMap<String, Book> bookList = new HashMap<String, Book>;
    private HashMap<String, AudioBook> audioBookList = new HashMap<String, AudioBook>;
    private HashMap<String, eTextbook> eTextBookList = new HashMap<String, eTextbook>;
    private HashMap<String, Newspaper> newspaperList = new HashMap<String, Newspaper>;
    private HashMap<String, PublishedPaper> publishedPaperList = new HashMap<String, PublishedPaper>;
    private HashMap<String, Video> videoList = new HashMap<String, Video>;


    public void addBookList(Book book) {
        this.bookList.put(book.getTitle(), book);
    }

    public void addAudioBookList(AudioBook audioBook) {
        this.audioBookList.put(audioBook.getTitle(), audioBook);
    }

    public void addeTextBookList(eTextbook eTextBook) {
        this.eTextBookList.put(eTextBook.getTitle(), eTextBook);
    }

    public void addNewspaperList( Newspaper newspaper) {
        this.newspaperList.put(newspaper.getTitle(), newspaper);
    }


    public void addPublishedPaperList(PublishedPaper publishedPaper) {
        this.publishedPaperList.put(publishedPaper.getTitle(), publishedPaper);
    }

    public void addVideoList(Video video) {
        this.videoList.put(video.getTitle(), video);
    }

    public LinkedList<Object> getList(String searchFilter) {
        LinkedList<Object> returnList = new LinkedList<Object>();
        switch(searchFilter) {
            // Starts with..  ..add to return ...and auto-cast to Media
            case "video" -> {
                this.videoList.forEach((key, value) -> {
                    returnList.add(value);
                });
            }
            case "audiobook" -> {
                this.audioBookList.forEach((key, value) -> {
                    returnList.add(value);
                });
            }
            case "book" -> {
                this.bookList.forEach((key, value) -> {
                    returnList.add(value);
                });
            }
            case "publishedPaper" -> {
                this.publishedPaperList.forEach((key, value) -> {
                    returnList.add(value);
                });
            }
            case "newspaper" -> {
                this.newspaperList.forEach((key, value) -> {
                    returnList.add(value);
                });
            }
            case "eTextbook" -> {
                this.eTextBookList.forEach((key, value) -> {
                    returnList.add(value);
                });
            }

        }
        return returnList;
    }
}

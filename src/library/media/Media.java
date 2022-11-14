package library.media;


public abstract class Media implements Comparable<Media> {

    public final String libraryFileName = "library.txt";
    protected String creator;
    protected String title;
    protected boolean checkedIn;

    // Constructor - Media must be made with the Title and publishedDate in order to facilitate comparing
    // This constructor is called by each subclass's constructor, so setting creator and title is not needed.
    public Media(String mediaTitle, String creator, boolean checkedIn) {
        this.creator = creator;
        this.title = mediaTitle;
        this.checkedIn = checkedIn;
    }

    public Media() {
        this.creator = "";
        this.title = "";
        this.checkedIn = false;
    }

    // Abstract methods
    abstract boolean setCreator(String creator); // Sets private String creator; returns True if properly set

    @Override
    public int compareTo(Media media) {
        // Subclass objects are cast to Media and compared with LocalDates' built-in compareTo().
        return this.title.compareTo(media.title);
    }

    public String getCreator() {
        return creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLibraryFileName() {
        return libraryFileName;
    }

}

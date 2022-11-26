package library.media;


// This abstract class is the base class for all Media type objects used within
// the library. displayInfo ensures an implementation for use by the Media object
// itself without using the GUI/Interface. Comparable is implemented
public abstract class Media implements Comparable<Media> {
    // Primitives
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
    }

    // Abstract method
    abstract public String displayInfo();
    abstract public String toString();
    abstract public int hashCode();

    // Overridden methods
    @Override
    public int compareTo(Media media) {
        // Subclass objects are cast to Media and compared with LocalDates' built-in compareTo().
        return this.title.compareTo(media.title);
    }

    // Public methods
    public boolean isCheckedIn() {
        return checkedIn;
    }
    public void checkInOut() {this.checkedIn = !this.checkedIn; }
    public String getCreator() {
        return creator;
    }
    public String getTitle() { return title;}
    public void setTitle(String title) {
        this.title = title;
    }

}

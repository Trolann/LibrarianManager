package library.media;

public class Video extends Media {
    private String directorName;
    private String genre;
    private int fiveStarRating;
    private float runtimeMinutes;

    @Override
    boolean setCreator(String creator) {
        if (this.directorName.equals("")) {
            return false; // Unable to set the creator.
        }
        this.creator = this.directorName;
        return true;
    }
}

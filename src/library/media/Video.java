package library.media;

import java.util.Objects;

public class Video extends Media {
    private final String directorName;
    private final String genre;
    private int fiveStarRating;
    private final float runtimeMinutes;

    public Video(String directorName, String genre, int fiveStarRating, float runtimeMinutes) {
        this.directorName = directorName;
        this.genre = genre;
        this.fiveStarRating = fiveStarRating;
        this.runtimeMinutes = runtimeMinutes;
        this.setCreator(directorName);
    }

    @Override
    boolean setCreator(String creator) {
        if (this.directorName.equals("")) {
            return false; // Unable to set the creator.
        }
        this.creator = this.directorName;
        return true;
    }

    public String getDirectorName() {
        return directorName;
    }

    public String getGenre() {
        return genre;
    }

    public int getFiveStarRating() {
        return fiveStarRating;
    }

    public void setFiveStarRating(int fiveStarRating) {
        this.fiveStarRating = fiveStarRating;
    }

    public float getRuntimeMinutes() {
        return runtimeMinutes;
    }

    @Override
    public String toString() {
        return "Video{" +
                "fiveStarRating=" + fiveStarRating +
                ", runtimeMinutes=" + runtimeMinutes +
                ", creator='" + creator + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return getFiveStarRating() == video.getFiveStarRating() && Float.compare(video.getRuntimeMinutes(), getRuntimeMinutes()) == 0 && getDirectorName().equals(video.getDirectorName()) && Objects.equals(getGenre(), video.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDirectorName(), getGenre(), getFiveStarRating(), getRuntimeMinutes());
    }
}

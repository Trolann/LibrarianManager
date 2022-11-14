package library.media;
import library.LibraryFunctions;

import java.util.Objects;

public class Video extends Media implements LibraryFunctions {
    private String videoDirector;
    private String videoStarActor;
    private int videoRating;
    private int videoRuntime;

    // This Constructor takes in an entire line from the library file and parses it
    public Video(String inputLine) {
        super();
        String[] values = inputLine.split(",");
        this.title = values[1];
        this.creator = values[2];
        this.videoDirector = values[2];
        this.videoStarActor = values[3];
        try {
            this.videoRating = Integer.parseInt(values[5]);
        } catch (NumberFormatException e) {
            this.videoRating = -1;
        }
        try {
            this.videoRuntime = Integer.parseInt(values[4]);
        } catch (NumberFormatException e) {
            this.videoRuntime = -1;
        }
    }

    // This constructor generates Video objects from already parsed values
    public Video(String mediaTitle, String videoDirector, String videoStarActor, int videoRating, int videoRuntime) {
        super(mediaTitle, videoDirector);
        this.videoDirector = videoDirector;
        this.videoStarActor = videoStarActor;
        this.videoRating = videoRating;
        this.videoRuntime = videoRuntime;
    }

    public String getVideoDirector() {
        return videoDirector;
    }

    public void setVideoDirector(String videoDirector) {
        this.videoDirector = videoDirector;
    }

    public String getVideoStarActor() {
        return videoStarActor;
    }

    public void setVideoStarActor(String videoStarActor) {
        this.videoStarActor = videoStarActor;
    }

    public int getVideoRating() {
        return videoRating;
    }

    public void setVideoRating(int videoRating) {
        this.videoRating = videoRating;
    }

    public int getVideoRuntime() {
        return videoRuntime;
    }

    public void setVideoRuntime(int videoRuntime) {
        this.videoRuntime = videoRuntime;
    }

    @Override
    boolean setCreator(String creator) {
        if (this.videoDirector.equals("")) {
            return false; // Unable to set the creator.
        }
        this.creator = this.videoDirector;
        return true;
    }

    @Override
    public String toString() {
        return "Video{" +
                "fiveStarRating=" + videoRating +
                ", runtimeMinutes=" + videoRuntime +
                ", creator='" + creator + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return this.videoDirector.equals(video.videoDirector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVideoDirector(), getVideoStarActor(), getVideoRating(), getVideoRuntime());
    }

    @Override
    public boolean checkIn(){
        return true;
    }

    @Override
    public boolean checkOut(){
        return true;
    }
}

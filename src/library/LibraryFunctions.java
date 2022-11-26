package library;

public interface LibraryFunctions {
    void checkIn();
    void checkOut();

    String displayInfo(); // Allows for all Media subclasses to be stored together for GUI
    String getTitle();
    boolean isCheckedIn();
}
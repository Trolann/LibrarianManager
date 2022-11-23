package library;

public interface LibraryFunctions {
    public boolean checkIn();
    public boolean checkOut();

    public String displayInfo(); // Allows for all Media subclasses to be stored together for GUI
    public String getTitle();
    public boolean isCheckedIn();
}

package library.roles;
import library.media.Media;

public interface RoleActions {
    boolean checkIn(Media o);
    boolean checkOut(Media o);
    Media[] list(); // Returns media checked out by Role or available Media from Librarian
}

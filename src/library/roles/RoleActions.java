package library.roles;

public interface RoleActions {
    boolean checkIn();
    boolean checkOut();
    boolean list(); // Returns media checked out by Role or available Media from Librarian
}

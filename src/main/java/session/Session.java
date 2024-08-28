package session;
import model.User;

public class Session {
    private static Session session;
    private static User userObject;

    public Session() {}

    public static User getLoggedInUser() {
        return userObject;
    }
}

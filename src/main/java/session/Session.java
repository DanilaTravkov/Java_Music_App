package session;
import model.User;

public class Session {
    private static Session instance;
    private static User userObject;

    public Session() {}

    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public User getLoggedInUser() {
        return userObject;
    }

    public void setLoggedInUser(User user) {
        userObject = user;
    }
}

package pl.session;

import pl.library.model.User;

public class UserSession {
    public static User user;

    public static void setUser(User u) {
        user = u;
    }

    public static User getUser() {
        return user;
    }

    public static void clear() {
        user = null;
    }
}

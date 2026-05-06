package pl.library.service.password;

import pl.library.dao.UserDao;
import pl.library.model.User;

public class UserPassword {
    private final UserDao userDao = new UserDao();

    public boolean isValid(User user, String newPassword) {

        if(newPassword == null || newPassword.trim().isEmpty()) {
            return false;
        }

        if(newPassword.equals(user.getPassword())) {
            return false;
        }

        return true;
    }

    public void changePassword(User user, String newPassword) {
        userDao.updatePassword(user.getId(), newPassword);
    }
}

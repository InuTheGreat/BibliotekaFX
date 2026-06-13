package pl.library.service;

import pl.library.dao.UserDao;
import pl.library.dto.UserView;
import pl.library.mapper.UserMapper;
import pl.library.model.User;
import pl.library.service.password.PasswordCoder;

public class UserService {

    private final UserDao userDao = new UserDao();
    private final PasswordCoder passwordCoder = new PasswordCoder();

    public User login(String email, String password) {

        User user = userDao.getUserByEmail(email);

        if(user != null) {

            if(passwordCoder.verifyPassword(password, user.getPassword())) {

                return user;
            }
        }

        System.out.println("LOGIN FAIL");
        return null;
    }

    public boolean isUserExistByEmail(String login) {

        User foundUser = userDao.getUserByEmail(login);

        return foundUser != null;
    }

    public UserView createUser(String password, String login, String name, String lastName) {

        User createdUser = userDao.createUser(password, login, name, lastName);

        return UserMapper.toUserView(createdUser);
    }
}

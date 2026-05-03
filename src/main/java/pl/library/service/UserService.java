package pl.library.service;

import pl.library.dao.UserDao;
import pl.library.model.User;

public class UserService {

    private UserDao userDao = new UserDao();

    public User login(String email, String password) {


        User user = userDao.getUserByEmail(email);

        System.out.println("USER FROM DAO: " + user);


        if (user != null && user.getPassword().equals(password)) {
            System.out.println("LOGIN OK");
            return user;
        }

        System.out.println("LOGIN FAIL");
        return null;
    }
}

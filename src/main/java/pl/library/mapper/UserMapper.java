package pl.library.mapper;

import pl.library.dto.BookView;
import pl.library.dto.UserView;
import pl.library.model.User;

public class UserMapper {

    public static UserView toUserView(User user) {

        return new UserView (

                user.getName(),
                user.getSurname(),
                user.getEmail()
        );
    }
}

package pl.library.service.password;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordCoder {

    public String hashPassword(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean verifyPassword(String password, String hash) {

        return BCrypt.checkpw(password, hash);
    }
}

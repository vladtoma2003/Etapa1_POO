package Factory;

import Data.User;
import fileio.Credentials;
import fileio.Userio;

public class UserFactory {
    public static User newUser (Userio user) {
        User newUser = new User();
        newUser.setName(user.getCredentials().getName());
        newUser.setPassword(user.getCredentials().getPassword());
        newUser.setBalance(user.getCredentials().getBalance());
        newUser.setCountry(user.getCredentials().getCountry());
        newUser.setAccountType(user.getCredentials().getAccountType());
        return newUser;
    }
    public static User newUser(Credentials credentials) {
        User newUser = new User();
        newUser.setName(credentials.getName());
        newUser.setPassword(credentials.getPassword());
        newUser.setBalance(credentials.getBalance());
        newUser.setCountry(credentials.getCountry());
        newUser.setAccountType(credentials.getAccountType());
        return newUser;
    }
    public static User newUser (User user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());
        newUser.setBalance(user.getBalance());
        newUser.setCountry(user.getCountry());
        newUser.setAccountType(user.getAccountType());
        return newUser;
    }
}

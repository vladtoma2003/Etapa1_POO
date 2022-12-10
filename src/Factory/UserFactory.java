package Factory;

import Data.User;
import fileio.Credentials;
import fileio.Userio;

public class UserFactory {
    public static User newUser(Userio user) {
        User newUser = new User();
        newUser.setCredentials(new Credentials());
        newUser.getCredentials().setName(user.getCredentials().getName());
        newUser.getCredentials().setPassword(user.getCredentials().getPassword());
        newUser.getCredentials().setBalance(user.getCredentials().getBalance());
        newUser.getCredentials().setCountry(user.getCredentials().getCountry());
        newUser.getCredentials().setAccountType(user.getCredentials().getAccountType());
        newUser.setTokensCount(0);
        newUser.setNumFreePremiumMovies(15);
        newUser.getCredentials().setIntBalance(Integer.parseInt(newUser.getCredentials().getBalance()));
        return newUser;
    }

    public static User newUser(Credentials credentials) {
        User newUser = new User();
        newUser.setCredentials(new Credentials());
        newUser.getCredentials().setName(credentials.getName());
        newUser.getCredentials().setPassword(credentials.getPassword());
        newUser.getCredentials().setBalance(credentials.getBalance());
        newUser.getCredentials().setCountry(credentials.getCountry());
        newUser.getCredentials().setAccountType(credentials.getAccountType());
        newUser.setTokensCount(0);
        newUser.setNumFreePremiumMovies(15);
        newUser.getCredentials().setIntBalance(0);
        return newUser;
    }

    public static User newUser(User user) {
        User newUser = new User();
        newUser.setCredentials(new Credentials());
        newUser.getCredentials().setName(user.getCredentials().getName());
        newUser.getCredentials().setPassword(user.getCredentials().getPassword());
        newUser.getCredentials().setBalance(user.getCredentials().getBalance());
        newUser.getCredentials().setCountry(user.getCredentials().getCountry());
        newUser.getCredentials().setAccountType(user.getCredentials().getAccountType());
        newUser.setTokensCount(user.getTokensCount());
        newUser.setNumFreePremiumMovies(user.getNumFreePremiumMovies());
        newUser.getCredentials().setIntBalance(user.getCredentials().getIntBalance());
        return newUser;
    }
}

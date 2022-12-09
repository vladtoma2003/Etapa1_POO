package Factory;

import Data.DataBase;
import Data.OutputError;
import Data.User;

public class ErrorFactory {
    public static OutputError standardError(DataBase dataBase) {
        OutputError newError = new OutputError();
        newError.setError("Error");
        newError.setCurrentMoviesList(dataBase.getCurrentMoviesList());
        return newError;
    }

    public static OutputError success(DataBase dataBase) {
        OutputError newError = new OutputError();
        newError.setError(null);
        newError.setCurrentUser(dataBase.getLoggedUser());
        newError.setCurrentMoviesList(dataBase.getCurrentMoviesList());
        return newError;
    }
    public static OutputError success(DataBase dataBase, User user) {
        OutputError newError = new OutputError();
        newError.setError(null);
        newError.setCurrentUser(user);
        newError.setCurrentMoviesList(dataBase.getCurrentMoviesList());
        return newError;
    }
}

package Factory;

import Data.DataBase;
import Data.Movie;
import Data.OutputError;
import Data.User;

import java.util.ArrayList;

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
    public static OutputError success(DataBase dataBase, Movie movie) {
        OutputError newError = new OutputError();
        newError.setError(null);
        newError.setCurrentUser(dataBase.getLoggedUser());
        ArrayList<Movie> movArr = new ArrayList<>();
        movArr.add(movie);
        newError.setCurrentMoviesList(movArr);
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

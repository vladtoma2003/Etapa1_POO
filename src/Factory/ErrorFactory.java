package Factory;

import Data.DataBase;
import Data.Movie;
import Data.OutputError;
import Data.User;

import java.util.ArrayList;

public class ErrorFactory {
    public static OutputError standardError(final DataBase dataBase) {
        OutputError newError = new OutputError();
        newError.setError("Error");
        newError.setCurrentMoviesList(new ArrayList<>());
        return newError;
    }

    public static OutputError success(final DataBase dataBase) {
        OutputError newError = new OutputError();
        newError.setError(null);
        newError.setCurrentUser(UserFactory.newUser(dataBase.getLoggedUser()));
        ArrayList<Movie> movies = new ArrayList<>();
        for (var movie : dataBase.getCurrentMoviesList()) {
            movies.add(MovieFactory.newMovie(movie));
        }
        newError.setCurrentMoviesList(movies);
        return newError;
    }

    public static OutputError success(final DataBase dataBase, User user) {
        OutputError newError = new OutputError();
        newError.setError(null);
        newError.setCurrentUser(UserFactory.newUser(user));
        newError.setCurrentMoviesList(new ArrayList<>(dataBase.getCurrentMoviesList()));
        return newError;
    }
}

package factory;

import data.DataBase;
import data.Movie;
import data.OutputError;
import data.User;

import java.util.ArrayList;

public final class ErrorFactory {
    /**
     * Creates a standard error. This error is given when an action is done wrong
     *
     * @param dataBase
     * @return
     */
    public static OutputError standardError(final DataBase dataBase) {
        OutputError newError = new OutputError();
        newError.setError("Error");
        newError.setCurrentMoviesList(new ArrayList<>());
        return newError;
    }

    /**
     * Prints the successful output
     *
     * @param dataBase
     * @return
     */
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

    /**
     * same as the one before
     *
     * @param dataBase
     * @param user
     * @return
     */
    public static OutputError success(final DataBase dataBase, final User user) {
        OutputError newError = new OutputError();
        newError.setError(null);
        newError.setCurrentUser(UserFactory.newUser(user));
        newError.setCurrentMoviesList(new ArrayList<>(dataBase.getCurrentMoviesList()));
        return newError;
    }

    private ErrorFactory() {

    }
}

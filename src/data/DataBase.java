package data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class DataBase {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Movie> availableMovies = new ArrayList<>();
    private ArrayList<Movie> currentMoviesList = new ArrayList<>();
    private User loggedUser = null;
    private String currentMovie = null;

    /**
     * Checks if the database already has a user with the name and the password
     *
     * @param user
     * @return boolean
     */
    public boolean existsUser(final User user) {
        return users.stream()
                .anyMatch(o -> o.equals(user));
    }

    /**
     * returns the current user from the database
     *
     * @param user
     * @return
     */
    public User getCurrentUser(final User user) {
        if (!existsUser(user)) {
            return null;
        }
        for (var u : users) {
            if (u.equals(user)) {
                return u;
            }
        }
        return null;
    }

    /**
     * returns the movie with the given title
     *
     * @param name
     * @return
     */
    public Movie getMovieFromCurrentList(final String name) {
        for (var movie : currentMoviesList) {
            if (movie.getName().startsWith(name)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * returns a list with the purchased movies by the currently logged user
     *
     * @param name
     * @return
     */
    public Movie getPurchasedMovies(final String name) {
        for (var movie : loggedUser.getPurchasedMovies()) {
            if (movie.getName().startsWith(name)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * returns a list of watched movies by the currently logged user
     *
     * @param name
     * @return
     */
    public Movie getWatchedMovies(final String name) {
        for (var movie : loggedUser.getWatchedMovies()) {
            if (movie.getName().startsWith(name)) {
                return movie;
            }
        }
        return null;
    }
}

package Data;

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

    public boolean existsUser(final User user) {
        return users.stream()
                .anyMatch(o -> o.equals(user));
    }

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

    public Movie getMovieFromCurrentList(final String name) {
        for (var movie : currentMoviesList) {
            if (movie.getName().startsWith(name)) {
                return movie;
            }
        }
        return null;
    }

    public Movie getPurchasedMovies(final String name) {
        for (var movie : loggedUser.getPurchasedMovies()) {
            if (movie.getName().startsWith(name)) {
                return movie;
            }
        }
        return null;
    }

    public Movie getWatchedMovies(final String name) {
        for (var movie : loggedUser.getWatchedMovies()) {
            if (movie.getName().startsWith(name)) {
                return movie;
            }
        }
        return null;
    }
}

package data;

import factory.MovieFactory;
import fileio.Credentials;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class User {
    private Credentials credentials;
    private Integer tokensCount;
    private Integer numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies = new ArrayList<>();
    private ArrayList<Movie> watchedMovies = new ArrayList<>();
    private ArrayList<Movie> likedMovies = new ArrayList<>();
    private ArrayList<Movie> ratedMovies = new ArrayList<>();

    /**
     * checks if two objects are the same
     *
     * @param obj
     * @return
     */
    public boolean equals(final User obj) {
        return this.credentials.getName().equals(obj.getCredentials().getName())
                && this.credentials.getPassword().equals(obj.getCredentials().getPassword());
    }

    public User() {

    }

    /**
     * copies the movies from an old user
     *
     * @param oldUser
     */
    public void addMovies(final User oldUser) {
        for (var movie : oldUser.getPurchasedMovies()) {
            purchasedMovies.add(MovieFactory.newMovie(movie));
        }
        for (var movie : oldUser.getLikedMovies()) {
            likedMovies.add(MovieFactory.newMovie(movie));
        }
        for (var movie : oldUser.getRatedMovies()) {
            ratedMovies.add(MovieFactory.newMovie(movie));
        }
        for (var movie : oldUser.getWatchedMovies()) {
            watchedMovies.add(MovieFactory.newMovie(movie));
        }
    }
}

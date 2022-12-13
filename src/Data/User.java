package Data;

import Factory.MovieFactory;
import fileio.Credentials;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class User {
    Credentials credentials;
    private Integer tokensCount;
    private Integer numFreePremiumMovies;
    ArrayList<Movie> purchasedMovies = new ArrayList<>();
    ArrayList<Movie> watchedMovies = new ArrayList<>();
    ArrayList<Movie> likedMovies = new ArrayList<>();
    ArrayList<Movie> ratedMovies = new ArrayList<>();

    public boolean equals(final User obj) {
        return this.credentials.getName().equals(obj.getCredentials().getName()) &&
                this.credentials.getPassword().equals(obj.getCredentials().getPassword());
    }

    public User() {

    }

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

package Factory;

import Data.Movie;
import fileio.Movieio;

public class MovieFactory {
    public static Movie newMovie(Movieio movie) {
        Movie newMovie = new Movie();
        newMovie.setName(movie.getName());
        newMovie.setYear(movie.getYear());
        newMovie.setActors(movie.getActors());
        newMovie.setDuration(movie.getDuration());
        newMovie.setGenres(movie.getGenres());
        newMovie.setCountriesBanned(movie.getCountriesBanned());
        newMovie.setNumLikes(0);
        newMovie.setNumRatings(0);
        newMovie.setRating((double) 0);
        return newMovie;
    }

    public static Movie newMovie(Movie movie) {
        Movie newMovie = new Movie();
        newMovie.setName(movie.getName());
        newMovie.setYear(movie.getYear());
        newMovie.setActors(movie.getActors());
        newMovie.setDuration(movie.getDuration());
        newMovie.setGenres(movie.getGenres());
        newMovie.setCountriesBanned(movie.getCountriesBanned());
        newMovie.setNumRatings(movie.getNumRatings());
        newMovie.setRating(movie.getRating());
        newMovie.setNumLikes(movie.getNumLikes());
        return newMovie;
    }
}

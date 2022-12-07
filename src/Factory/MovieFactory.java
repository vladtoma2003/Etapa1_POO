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
        return newMovie;
    }
}

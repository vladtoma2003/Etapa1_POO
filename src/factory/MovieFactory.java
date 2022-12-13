package factory;

import data.Movie;
import fileio.Movieio;

public final class MovieFactory {
    /**
     * Creates a new Movie type object
     *
     * @param movie
     * @return
     */
    public static Movie newMovie(final Movieio movie) {
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
        newMovie.setTotalRatin(0);
        return newMovie;
    }

    /**
     * Creates a new Movie type object
     *
     * @param movie
     * @return
     */
    public static Movie newMovie(final Movie movie) {
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
        newMovie.setTotalRatin(movie.getTotalRatin());
        return newMovie;
    }

    private MovieFactory() {

    }
}

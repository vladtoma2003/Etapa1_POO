package Data;

import Factory.MovieFactory;

import java.util.ArrayList;

public class FilterCountryOut {
    public static void filterCountry(DataBase database) {
        ArrayList<Movie> movies = new ArrayList<>();
        for(var movie:database.getAvailableMovies()) {
            movies.add(movie);
        }
        ArrayList<Movie> filteredMovies = new ArrayList<>();
        movies.stream()
                .filter(o -> !o.getCountriesBanned().contains(database.getLoggedUser().getCredentials().getCountry()))
                .forEach(filteredMovies::add);
        database.setCurrentMoviesList(filteredMovies);
    }
}

package Data;

import Factory.MovieFactory;

import java.util.ArrayList;

public class FilterCountryOut {
    public static void filterCountry(final DataBase database) {
        ArrayList<Movie> filteredMovies = new ArrayList<>();
        database.getAvailableMovies().stream()
                .filter(o -> !o.getCountriesBanned().contains(database.getLoggedUser().getCredentials().getCountry()))
                .forEach(filteredMovies::add);
        database.setCurrentMoviesList(filteredMovies);
    }
}

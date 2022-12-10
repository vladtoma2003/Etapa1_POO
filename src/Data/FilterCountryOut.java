package Data;

import Factory.MovieFactory;

import java.util.ArrayList;

public class FilterCountryOut {
    public static void filterCountry(DataBase database) {
        ArrayList<Movie> filteredMovies = new ArrayList<>();
        Boolean canWatch = true;
        for(var movie:database.getAvailableMovies()) {
            if(movie.getCountriesBanned().contains(database.getLoggedUser().getCredentials().getCountry())) {
                canWatch = false;
            }
            if(canWatch) {
                filteredMovies.add(MovieFactory.newMovie(movie));
            }
            canWatch = true;
        }
        database.setCurrentMoviesList(filteredMovies);
    }
}

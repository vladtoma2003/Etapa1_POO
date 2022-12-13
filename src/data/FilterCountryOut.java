package data;

import java.util.ArrayList;

public final class FilterCountryOut {
    /**
     * filters the movies list
     * removes the ones that the currently logged user can't see
     *
     * @param database
     */
    public static void filterCountry(final DataBase database) {
        ArrayList<Movie> filteredMovies = new ArrayList<>();
        database.getAvailableMovies().stream()
                .filter(o -> !o.getCountriesBanned().contains(database.getLoggedUser()
                        .getCredentials().getCountry())).forEach(filteredMovies::add);
        database.setCurrentMoviesList(filteredMovies);
    }

    private FilterCountryOut() {

    }
}

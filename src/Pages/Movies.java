package Pages;

import Data.DataBase;
import Data.FilterCountryOut;
import Data.Movie;
import Data.OutputError;
import Factory.ErrorFactory;
import Factory.MovieFactory;
import PageVisitor.VisitorAction;
import PageVisitor.VisitorDestination;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Actionio;
import fileio.Filterio;

import java.util.ArrayList;
import java.util.Arrays;

public class Movies extends Page {
    private final static String name = "movies";
    private final static String destinations[] = {"home auth", "see details", "logout"};
    private final static String actions[] = {"search", "filter"};

    public void search(DataBase dataBase, String startsWith) {
        ArrayList<Movie> searchedMovie = new ArrayList<>();
        dataBase.getAvailableMovies().stream()
                .filter(o -> o.getName().startsWith(startsWith))
                .forEach(searchedMovie::add);

        dataBase.setCurrentMoviesList(searchedMovie);
    }

    public void filter(DataBase dataBase, Filterio filter) {
        if (dataBase.getCurrentMoviesList().isEmpty()) {
            return;
        }
        FilterCountryOut.filterCountry(dataBase);
        ArrayList<Movie> filteredMovies = new ArrayList<>();
        ArrayList<Movie> temp = new ArrayList<>();
        for (var movie : dataBase.getCurrentMoviesList()) {
            filteredMovies.add(MovieFactory.newMovie(movie));
        }
        // las doar actorii si genre-urile necesare
        if (!filter.getContains().getActors().isEmpty()) {
            filteredMovies.stream()
                    .filter(o -> !(o.getActors().contains(filter.getContains().getActors())))
                    .forEach(filteredMovies::remove);
        }
        if (!filter.getContains().getGenre().isEmpty()) {
            filteredMovies.stream()
                    .filter(o -> !(o.getGenres().contains(filter.getContains().getGenre())))
                    .forEach(filteredMovies::remove);
        }
        if (filter.getSort().getRating().equals("decreasing")) {
            filteredMovies.sort((o1, o2) -> o2.compareTo(o1));
        } else {
            filteredMovies.sort((c1, c2) -> c1.compareTo(c2));
        }
    }

    public boolean canDoAction(String action) {
        return Arrays.asList(actions).contains(action);
    }

    public boolean canGoThere(String destination) {
        return Arrays.asList(destinations).contains(destination);
    }

    public void acceptDestination(VisitorDestination visitor, DataBase dataBase, Page currentPage, Actionio Destination, ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, Destination, output);
    }

    public void acceptAction(VisitorAction visitor, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, action, output);
    }

    @Override
    public String getName() {
        return name;
    }

    public String[] getDestinations() {
        return destinations;
    }

    public String[] getActions() {
        return actions;
    }
}

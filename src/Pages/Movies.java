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
    private final static String destinations[] = {"home auth", "see details", "logout", "movies"};
    private final static String actions[] = {"search", "filter"};

    public void search(DataBase dataBase, String startsWith) {
        ArrayList<Movie> searchedMovie = new ArrayList<>();
        FilterCountryOut.filterCountry(dataBase);
        dataBase.getCurrentMoviesList().stream()
                .filter(o -> o.getName().startsWith(startsWith))
                .forEach(searchedMovie::add);

        dataBase.setCurrentMoviesList(searchedMovie);
    }

    public void filter(DataBase dataBase, Filterio filter) {
        FilterCountryOut.filterCountry(dataBase);
        if (dataBase.getCurrentMoviesList().isEmpty()) {
            return;
        }
        ArrayList<Movie> filteredMovies = dataBase.getCurrentMoviesList();
        // las doar actorii si genre-urile necesare
        if (filter.getContains() != null) {
            if (filter.getContains().getActors() != null) {
                dataBase.getCurrentMoviesList().stream()
                        .filter(o -> o.getActors().contains(filter.getContains().getActors()))
                        .forEach(p -> filteredMovies.add(MovieFactory.newMovie(p)));
            }
            boolean genre = false;
            ArrayList<Movie> filteredGenre = new ArrayList<>();
            if (filter.getContains().getGenre() != null) {
                genre = true;
                filteredMovies.stream()
                        .filter(o -> o.getGenres().contains(filter.getContains().getGenre()))
                        .forEach(p -> filteredGenre.add(MovieFactory.newMovie(p)));
            }
            if(genre) {
                dataBase.setCurrentMoviesList(filteredGenre);
            } else {
                dataBase.setCurrentMoviesList(filteredMovies);
            }
        }
        if (filter.getSort() != null) {
            if (filter.getSort().getDuration() != null) {
                if (filter.getSort().getRating().equals("increasing")
                        && filter.getSort().getDuration().equals("increasing")) {
                    filteredMovies.sort((o1, o2) -> o1.compareTo(o2));
                } else if (filter.getSort().getRating().equals("increasing")
                        && filter.getSort().getDuration().equals("decreasing")) {
                    filteredMovies.sort((o1, o2) -> {
                        if (o1.compareDuration(o2) == 0) {
                            return o1.compareRating(o2);
                        }
                        return o2.compareDuration(o1);
                    });
                } else if (filter.getSort().getRating().equals("decreasing")
                        && filter.getSort().getDuration().equals("increasing")) {
                    filteredMovies.sort(((o1, o2) -> {
                        if (o1.compareDuration(o2) == 0) {
                            return o2.compareRating(o1);
                        }
                        return o1.compareDuration(o2);
                    }));
                } else {
                    filteredMovies.sort((o1, o2) -> o2.compareTo(o1));
                }
            }
            dataBase.setCurrentMoviesList(filteredMovies);
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

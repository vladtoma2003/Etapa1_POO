package pages;

import data.DataBase;
import data.FilterCountryOut;
import data.Movie;
import visitor.VisitorAction;
import visitor.VisitorDestination;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Actionio;
import fileio.Filterio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

@Setter
@Getter
public class Movies extends Page {
    private final String name = "movies";
    private final String[] destinations = {"home auth", "see details", "logout", "movies"};
    private final String[] actions = {"search", "filter"};

    /**
     * returns the movies that starts with a given string
     *
     * @param dataBase
     * @param startsWith
     */
    public void search(final DataBase dataBase, final String startsWith) {
        ArrayList<Movie> searchedMovie = new ArrayList<>();
        dataBase.getCurrentMoviesList().stream()
                .filter(o -> o.getName().startsWith(startsWith))
                .forEach(searchedMovie::add);

        dataBase.setCurrentMoviesList(searchedMovie);
    }

    /**
     * filters the movies
     *
     * @param dataBase
     * @param filter
     */
    public void filter(final DataBase dataBase, final Filterio filter) {
        FilterCountryOut.filterCountry(dataBase);
        if (dataBase.getCurrentMoviesList().isEmpty()) {
            return;
        }
        // las doar actorii si genre-urile necesare
        if (filter.getContains() != null) {
            if (filter.getContains().getActors() != null) {
                dataBase.getCurrentMoviesList().removeIf(
                        o -> !o.getActors().containsAll(filter.getContains().getActors()));
            }
            if (filter.getContains().getGenre() != null) {
                dataBase.getCurrentMoviesList().removeIf(
                        o -> !o.getGenres().containsAll(filter.getContains().getGenre()));
            }
        }
        if (filter.getSort() != null) {
            if (filter.getSort().getDuration() != null) {
                if (filter.getSort().getRating().equals("increasing")
                        && filter.getSort().getDuration().equals("increasing")) {
                    dataBase.getCurrentMoviesList().sort((o1, o2) -> o1.compareTo(o2));
                } else if (filter.getSort().getRating().equals("increasing")
                        && filter.getSort().getDuration().equals("decreasing")) {
                    dataBase.getCurrentMoviesList().sort((o1, o2) -> {
                        if (o1.compareDuration(o2) == 0) {
                            return o1.compareRating(o2);
                        }
                        return o2.compareDuration(o1);
                    });
                } else if (filter.getSort().getRating().equals("decreasing")
                        && filter.getSort().getDuration().equals("increasing")) {
                    dataBase.getCurrentMoviesList().sort(((o1, o2) -> {
                        if (o1.compareDuration(o2) == 0) {
                            return o2.compareRating(o1);
                        }
                        return o1.compareDuration(o2);
                    }));
                } else {
                    dataBase.getCurrentMoviesList().sort((o1, o2) -> o2.compareTo(o1));
                }
            } else {
                if (filter.getSort().equals("increasing")) {
                    dataBase.getCurrentMoviesList().sort((o1, o2) -> o2.compareRating(o1));
                } else {
                    dataBase.getCurrentMoviesList().sort((p1, p2) -> p1.compareRating(p2));
                }
            }
            dataBase.setCurrentMoviesList(dataBase.getCurrentMoviesList());
        }

    }

    /**
     * Checks if the "action" string is in the actions array
     *
     * @param action
     * @return
     */
    public boolean canDoAction(final String action) {
        return Arrays.asList(actions).contains(action);
    }

    /**
     * Checks if the "destination" string is in the destinations array
     *
     * @param destination
     * @return
     */
    public boolean canGoThere(final String destination) {
        return Arrays.asList(destinations).contains(destination);
    }

    /**
     * accept method for the visitor
     *
     * @param visitor
     * @param dataBase
     * @param currentPage
     * @param destination
     * @param output
     */
    public void acceptDestination(final VisitorDestination visitor,
                                  final DataBase dataBase, final Page currentPage,
                                  final Actionio destination, final ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, destination, output);
    }

    /**
     * accept method for the visitor
     *
     * @param visitor
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    public void acceptAction(final VisitorAction visitor,
                             final DataBase dataBase, final Page currentPage,
                             final Actionio action, final ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, action, output);
    }
}

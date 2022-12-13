package Pages;

import Data.DataBase;
import Data.FilterCountryOut;
import Data.Movie;
import PageVisitor.VisitorAction;
import PageVisitor.VisitorDestination;
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
    private final static String name = "movies";
    private final static String destinations[] = {"home auth", "see details", "logout", "movies"};
    private final static String actions[] = {"search", "filter"};

    public void search(final DataBase dataBase, final String startsWith) {
        ArrayList<Movie> searchedMovie = new ArrayList<>();
        dataBase.getCurrentMoviesList().stream()
                .filter(o -> o.getName().startsWith(startsWith))
                .forEach(searchedMovie::add);

        dataBase.setCurrentMoviesList(searchedMovie);
    }

    public void filter(final DataBase dataBase, final Filterio filter) {
        FilterCountryOut.filterCountry(dataBase);
        if (dataBase.getCurrentMoviesList().isEmpty()) {
            return;
        }
        // las doar actorii si genre-urile necesare
        if (filter.getContains() != null) {
            if (filter.getContains().getActors() != null) {
                dataBase.getCurrentMoviesList().removeIf(o -> !o.getActors().containsAll(filter.getContains().getActors()));
            }
            if (filter.getContains().getGenre() != null) {
                dataBase.getCurrentMoviesList().removeIf(o -> !o.getGenres().containsAll(filter.getContains().getGenre()));
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

    public boolean canDoAction(final String action) {
        return Arrays.asList(actions).contains(action);
    }

    public boolean canGoThere(final String destination) {
        return Arrays.asList(destinations).contains(destination);
    }

    public void acceptDestination(final VisitorDestination visitor, final DataBase dataBase, final Page currentPage, final Actionio Destination, final ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, Destination, output);
    }

    public void acceptAction(final VisitorAction visitor, final DataBase dataBase, final Page currentPage, final Actionio action, final ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, action, output);
    }
}

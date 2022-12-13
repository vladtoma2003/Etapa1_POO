package pagevisitor;

import data.DataBase;
import data.User;
import data.FilterCountryOut;
import data.Movie;
import data.OutputError;
import factory.ErrorFactory;
import factory.UserFactory;
import pages.Page;
import pages.Upgrades;
import pages.Logout;
import pages.Login;
import pages.Movies;
import pages.Details;
import pages.Register;
import pages.Start;
import pages.Home;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Actionio;


public class VisitPagesAction implements VisitorAction {
    /**
     * visitor for Page type
     *
     * @param page
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    @Override
    public void visit(final Page page, final DataBase dataBase,
                      final Page currentPage, final Actionio action, final ArrayNode output) {

    }

    /**
     * visitor for Start type
     *
     * @param start
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    @Override
    public void visit(final Start start, final DataBase dataBase,
                      final Page currentPage, final Actionio action, final ArrayNode output) {
    }

    /**
     * visitor for Login type
     * here the user logs into his account
     *
     * @param login
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    @Override
    public void visit(final Login login, final DataBase dataBase,
                      final Page currentPage, final Actionio action, final ArrayNode output) {
        User us = UserFactory.newUser(action.getCredentials());
        if (!dataBase.existsUser(us)) {
            // error, user doesn't exist
            OutputError stdError = ErrorFactory.standardError(dataBase);
            output.addPOJO(stdError);
            currentPage.setName("start");
            return;
        }
        dataBase.setLoggedUser(dataBase.getCurrentUser(us));
        currentPage.setAuth(true);
        currentPage.setName("home auth");
        OutputError err = ErrorFactory.success(dataBase);
        output.addPOJO(err);
    }

    /**
     * visitor for Register type
     * here the user creates a new account
     *
     * @param register
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    @Override
    public void visit(final Register register, final DataBase dataBase,
                      final Page currentPage, final Actionio action, final ArrayNode output) {
        User usr = UserFactory.newUser(action.getCredentials());
        if (dataBase.existsUser(usr)) {
            // error: user already exists
            OutputError stdError = ErrorFactory.standardError(dataBase);
            output.addPOJO(stdError);
            currentPage.setName("start");
            return;
        }
        dataBase.getUsers().add(usr);
        OutputError success = ErrorFactory.success(dataBase, usr);
        output.addPOJO(success);
        currentPage.setName("home auth");
        currentPage.setAuth(true);
        dataBase.setLoggedUser(usr);
    }

    /**
     * visitor for Home type
     * no actions can be done here at the moment
     *
     * @param home
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    @Override
    public void visit(final Home home, final DataBase dataBase,
                      final Page currentPage, final Actionio action, final ArrayNode output) {
    }

    /**
     * visitor for Movies type
     * here filter and search actions are done
     *
     * @param movies
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    @Override
    public void visit(final Movies movies, final DataBase dataBase,
                      final Page currentPage, final Actionio action, final ArrayNode output) {
        if (action.getFeature().equals("search")) {
            FilterCountryOut.filterCountry(dataBase);
            movies.search(dataBase, action.getStartsWith());
        } else if (action.getFeature().equals("filter")) {
            movies.filter(dataBase, action.getFilters());
        }
        OutputError err = ErrorFactory.success(dataBase);
        output.addPOJO(err);

    }

    /**
     * visitor for Details type
     * here purchase, watch, like, rate,  can be done
     *
     * @param details
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    @Override
    public void visit(final Details details, final DataBase dataBase,
                      final Page currentPage, final Actionio action, final ArrayNode output) {
        if (action.getFeature().equals("purchase")) {
            Movie movie = dataBase.getMovieFromCurrentList(dataBase.getCurrentMovie());
            if (movie == null) {
                OutputError stdError = ErrorFactory.standardError(dataBase);
                output.addPOJO(stdError);
                currentPage.setName("movies");
                FilterCountryOut.filterCountry(dataBase);
                return;
            }
            if (dataBase.getLoggedUser().getCredentials().getAccountType().equals("premium")) {
                if (dataBase.getLoggedUser().getNumFreePremiumMovies() > 0) {
                    dataBase.getLoggedUser().setNumFreePremiumMovies(
                            dataBase.getLoggedUser().getNumFreePremiumMovies() - 1);
                } else if (dataBase.getLoggedUser().getTokensCount() >= 2) {
                    dataBase.getLoggedUser().setTokensCount(
                            dataBase.getLoggedUser().getTokensCount() - 2);
                } else {
                    OutputError stdError = ErrorFactory.standardError(dataBase);
                    output.addPOJO(stdError);
                    currentPage.setName("movies");
                    FilterCountryOut.filterCountry(dataBase);
                    return;
                }
            } else {
                if (dataBase.getLoggedUser().getTokensCount() >= 2) {
                    dataBase.getLoggedUser().setTokensCount(
                            dataBase.getLoggedUser().getTokensCount() - 2);
                } else {
                    OutputError stdError = ErrorFactory.standardError(dataBase);
                    output.addPOJO(stdError);
                    currentPage.setName("movies");
                    FilterCountryOut.filterCountry(dataBase);
                    return;
                }
            }
            dataBase.getLoggedUser().getPurchasedMovies().add(movie);
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
        } else if (action.getFeature().equals("watch")) {
            if (!dataBase.getLoggedUser().getPurchasedMovies().stream()
                    .anyMatch(o -> o.getName().startsWith(dataBase.getCurrentMovie()))) {
                OutputError stdError = ErrorFactory.standardError(dataBase);
                output.addPOJO(stdError);
                FilterCountryOut.filterCountry(dataBase);
                currentPage.setName("movies");
                FilterCountryOut.filterCountry(dataBase);
                return;
            }
            Movie movie = dataBase.getPurchasedMovies(dataBase.getCurrentMovie());
            dataBase.getLoggedUser().getWatchedMovies().add(movie);
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
        } else if (action.getFeature().equals("like")) {
            if (!dataBase.getLoggedUser().getWatchedMovies().stream()
                    .anyMatch(o -> o.getName().startsWith(dataBase.getCurrentMovie()))) {
                OutputError stdError = ErrorFactory.standardError(dataBase);
                output.addPOJO(stdError);
                currentPage.setName("movies");
                FilterCountryOut.filterCountry(dataBase);
                return;
            }
            Movie movie = dataBase.getWatchedMovies(dataBase.getCurrentMovie());
            movie.setNumLikes(movie.getNumLikes() + 1);
            dataBase.getLoggedUser().getLikedMovies().add(movie);
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
        } else if (action.getFeature().equals("rate")) {
            if (action.getRate() < 0 || action.getRate() > 5) {
                OutputError stdError = ErrorFactory.standardError(dataBase);
                output.addPOJO(stdError);
                currentPage.setName("movies");
                FilterCountryOut.filterCountry(dataBase);
                return;
            }
            if (!dataBase.getLoggedUser().getWatchedMovies().stream()
                    .anyMatch(o -> o.getName().startsWith(dataBase.getCurrentMovie()))) {
                OutputError stdError = ErrorFactory.standardError(dataBase);
                output.addPOJO(stdError);
                currentPage.setName("movies");
                FilterCountryOut.filterCountry(dataBase);
                return;
            }
            Movie movie = dataBase.getWatchedMovies(dataBase.getCurrentMovie());
            movie.setNumRatings(movie.getNumRatings() + 1);
            movie.setTotalRatin(movie.getTotalRatin() + action.getRate());
            movie.setRating((movie.getTotalRatin() / movie.getNumRatings()));
            dataBase.getLoggedUser().getRatedMovies().add(movie);
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
        }

    }

    /**
     * visitor for Upgrade type
     * here the user can upgrade his account or buy more tokens
     *
     * @param upgrades
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    @Override
    public void visit(final Upgrades upgrades, final DataBase dataBase,
                      final Page currentPage, final Actionio action, final ArrayNode output) {
        FilterCountryOut.filterCountry(dataBase);
        if (action.getFeature().equals("buy tokens")) {
            if (Integer.parseInt(dataBase.getLoggedUser().getCredentials().getBalance())
                    < Integer.parseInt(action.getCount())) {
                OutputError err = ErrorFactory.standardError(dataBase);
                output.addPOJO(err);
                return;
            }
            dataBase.getLoggedUser().setTokensCount(dataBase.getLoggedUser().getTokensCount()
                    + Integer.parseInt(action.getCount()));
            dataBase.getLoggedUser().getCredentials().setIntBalance(
                    Integer.parseInt(dataBase.getLoggedUser().getCredentials().getBalance())
                            - Integer.parseInt(action.getCount()));
            dataBase.getLoggedUser().getCredentials().setBalance(Integer.toString(
                    dataBase.getLoggedUser().getCredentials().getIntBalance()));
        } else { // buy premium account
            if (dataBase.getLoggedUser().getTokensCount() < 10) {
                OutputError stdError = ErrorFactory.standardError(dataBase);
                output.addPOJO(stdError);
                return;
            }
            dataBase.getLoggedUser().setTokensCount(dataBase.getLoggedUser().getTokensCount() - 10);
            dataBase.getLoggedUser().getCredentials().setAccountType("premium");
        }
    }

    /**
     * visitor for Logout type
     * here the platform de-logs the current user
     *
     * @param logout
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    @Override
    public void visit(final Logout logout, final DataBase dataBase,
                      final Page currentPage, final Actionio action, final ArrayNode output) {
        dataBase.setLoggedUser(null);
        currentPage.setName("start");
        currentPage.setAuth(false);
    }
}

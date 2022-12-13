package pagevisitor;

import data.DataBase;
import data.FilterCountryOut;
import data.OutputError;
import factory.ErrorFactory;
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

import java.util.ArrayList;

public class VisitPageDestination implements VisitorDestination {

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
     * changes from start to either login or register
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
        if (action.getPage().equals("login")) {
            currentPage.setName("login");
        } else {
            currentPage.setName("register");
        }
    }

    /**
     * visitor for Login type
     * changes page
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
        currentPage.setName(action.getPage());
        FilterCountryOut.filterCountry(dataBase);
    }

    /**
     * visitor for Logout type
     * changes page to start and logs out the current user
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
        if (dataBase.getLoggedUser() == null) {
            OutputError err = ErrorFactory.standardError(dataBase);
            output.addPOJO(err);
            return;
        }
        currentPage.setName("start");
        currentPage.setAuth(false);
        dataBase.setLoggedUser(null);
        dataBase.setCurrentMoviesList(new ArrayList<>());
    }

    /**
     * visitor for Register type
     * changes the page destination
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
        currentPage.setName(action.getPage());
    }

    /**
     * visitor for Home type
     * can go to movies, see details, logout and upgrades
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
        if (action.getPage().equals("movies")) {
            FilterCountryOut.filterCountry(dataBase);
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
        }
        currentPage.setName(action.getPage());
        if (action.getPage().equals("logout")) {
            VisitorDestination v = new VisitPageDestination();
            Logout l = new Logout();
            l.acceptDestination(v, dataBase, currentPage, action, output);
        } else { // upgrades
            currentPage.setName(action.getPage());
        }
    }

    /**
     * visitor for Movie type
     * can go to home, see details, logout and movies
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
        if (action.getPage().equals("see details")) {
            if (dataBase.getCurrentMoviesList().isEmpty()) {
                OutputError stdError = ErrorFactory.standardError(dataBase);
                output.addPOJO(stdError);
                currentPage.setName("movies");
                FilterCountryOut.filterCountry(dataBase);
                return;
            }
            movies.search(dataBase, action.getMovie());
            if (dataBase.getCurrentMoviesList().isEmpty()) {
                OutputError stdError = ErrorFactory.standardError(dataBase);
                output.addPOJO(stdError);
                currentPage.setName("movies");
                return;
            }
            dataBase.setCurrentMovie(action.getMovie());
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
        } else if (action.getPage().equals("movies")) {
            FilterCountryOut.filterCountry(dataBase);
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
            return;
        }
        currentPage.setName(action.getPage());
        if (action.getPage().equals("logout")) {
            VisitorDestination v = new VisitPageDestination();
            Logout l = new Logout();
            l.acceptDestination(v, dataBase, currentPage, action, output);
        }
    }

    /**
     * visitor for Details type
     * can go to movies and logout
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
        if (action.getPage().equals("movies")) {
            FilterCountryOut.filterCountry(dataBase);
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
        }
        currentPage.setName(action.getPage());
        if (action.getPage().equals("logout")) {
            VisitorDestination v = new VisitPageDestination();
            Logout l = new Logout();
            l.acceptDestination(v, dataBase, currentPage, action, output);
        }
    }

    /**
     * visitor for Upgrades type
     * can go to movies and logout
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
        if (action.getPage().equals("movies")) {
            FilterCountryOut.filterCountry(dataBase);
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
        }
        currentPage.setName(action.getPage());
        if (action.getPage().equals("logout")) {
            VisitorDestination v = new VisitPageDestination();
            Logout l = new Logout();
            l.acceptDestination(v, dataBase, currentPage, action, output);
        }
    }


}

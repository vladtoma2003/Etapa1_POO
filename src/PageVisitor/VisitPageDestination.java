package PageVisitor;

import Data.DataBase;
import Data.FilterCountryOut;
import Data.OutputError;
import Factory.ErrorFactory;
import Pages.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Actionio;

import java.util.ArrayList;
import java.util.Arrays;

public class VisitPageDestination implements VisitorDestination {
    @Override
    public void visit(Page page, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {

    }

    @Override
    public void visit(Start start, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        String Destination = action.getPage();
        if (Arrays.stream(start.getDestinations()).noneMatch(o -> o.equals(Destination))) {
            OutputError err = ErrorFactory.standardError(dataBase);
            output.addPOJO(err);
            currentPage.setName("start");
            return;
        }
        if (Destination.equals("login")) {
            currentPage.setName("login");
        } else {
            currentPage.setName("register");
        }
    }

    @Override
    public void visit(Login login, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        String Destination = action.getPage();
        if (!currentPage.getName().equals("start")
                && Arrays.stream(login.getDestinations()).noneMatch(o -> o.equals(Destination))) {
            OutputError err = ErrorFactory.standardError(dataBase);
            output.addPOJO(err);
            currentPage.setName("start");
            return;
        }
        currentPage.setName(Destination);
        FilterCountryOut.filterCountry(dataBase);
    }

    @Override
    public void visit(Logout logout, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        String Destination = action.getPage();
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

    @Override
    public void visit(Register register, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        String Destination = action.getPage();
        if (Arrays.stream(register.getDestinations()).noneMatch(o -> o.equals(Destination))) {
            OutputError err = ErrorFactory.standardError(dataBase);
            output.addPOJO(err);
            currentPage.setName("start");
            return;
        }
        currentPage.setName(Destination);
        FilterCountryOut.filterCountry(dataBase);
    }

    @Override
    public void visit(Home home, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        String Destination = action.getPage();
        if (!home.canGoThere(Destination)) {
            OutputError err = ErrorFactory.standardError(dataBase);
            output.addPOJO(err);
            currentPage.setName("home auth");
            return;
        }
        if (Destination.equals("movies")) {
            FilterCountryOut.filterCountry(dataBase);
//            dataBase.setCurrentMoviesList(dataBase.getAvailableMovies());
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
        }
        currentPage.setName(Destination);
        if (Destination.equals("logout")) {
            VisitorDestination v = new VisitPageDestination();
            Logout l = new Logout();
            l.acceptDestination(v, dataBase, currentPage, action, output);
        } else { // upgrades
            currentPage.setName(Destination);
        }
    }

    @Override
    public void visit(Movies movies, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        String Destination = action.getPage();
        if (!movies.canGoThere(Destination)) {
            OutputError err = ErrorFactory.standardError(dataBase);
            output.addPOJO(err);
            currentPage.setName("movies");
            return;
        }
        FilterCountryOut.filterCountry(dataBase);
        if (Destination.equals("see details")) {
            if (dataBase.getCurrentMoviesList().isEmpty()) {
                OutputError stdError = ErrorFactory.standardError(dataBase);
                output.addPOJO(stdError);
                currentPage.setName("movies");
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
        }
        currentPage.setName(Destination);
        if(Destination.equals("logout")) {
            VisitorDestination v = new VisitPageDestination();
            Logout l = new Logout();
            l.acceptDestination(v, dataBase, currentPage, action, output);
        }
    }

    @Override
    public void visit(Details details, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        String Destination = action.getPage();
        if (!details.canGoThere(Destination)) {
            OutputError err = ErrorFactory.standardError(dataBase);
            output.addPOJO(err);
            currentPage.setName("home auth");
            return;
        }
        if(Destination.equals("movies")) {
            FilterCountryOut.filterCountry(dataBase);
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
        }
        currentPage.setName(Destination);
        if(Destination.equals("logout")) {
            VisitorDestination v = new VisitPageDestination();
            Logout l = new Logout();
            l.acceptDestination(v, dataBase, currentPage, action, output);
        }
    }

    @Override
    public void visit(Upgrades upgrades, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        String Destination = action.getPage();
        if (!upgrades.canGoThere(Destination)) {
            OutputError err = ErrorFactory.standardError(dataBase);
            output.addPOJO(err);
            currentPage.setName("home auth");
            return;
        }
        if(Destination.equals("movies")) {
            FilterCountryOut.filterCountry(dataBase);
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
        }
        currentPage.setName(Destination);
    }


}

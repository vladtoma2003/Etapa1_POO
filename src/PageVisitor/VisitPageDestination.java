package PageVisitor;

import Data.DataBase;
import Data.FilterCountryOut;
import Data.OutputError;
import Factory.ErrorFactory;
import Pages.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Actionio;

import java.util.ArrayList;

public class VisitPageDestination implements VisitorDestination {
    @Override
    public void visit(Page page, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {

    }

    @Override
    public void visit(Start start, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        if (action.getPage().equals("login")) {
            currentPage.setName("login");
        } else {
            currentPage.setName("register");
        }
    }

    @Override
    public void visit(Login login, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        currentPage.setName(action.getPage());
        FilterCountryOut.filterCountry(dataBase);
    }

    @Override
    public void visit(Logout logout, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
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
        currentPage.setName(action.getPage());
    }

    @Override
    public void visit(Home home, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
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

    @Override
    public void visit(Movies movies, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        if (action.getPage().equals("see details")) {
            FilterCountryOut.filterCountry(dataBase);
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
        } else if(action.getPage().equals("movies")) {
            FilterCountryOut.filterCountry(dataBase);
        }
        currentPage.setName(action.getPage());
        if(action.getPage().equals("logout")) {
            VisitorDestination v = new VisitPageDestination();
            Logout l = new Logout();
            l.acceptDestination(v, dataBase, currentPage, action, output);
        }
    }

    @Override
    public void visit(Details details, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        if(action.getPage().equals("movies")) {
            FilterCountryOut.filterCountry(dataBase);
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
        }
        currentPage.setName(action.getPage());
        if(action.getPage().equals("logout")) {
            VisitorDestination v = new VisitPageDestination();
            Logout l = new Logout();
            l.acceptDestination(v, dataBase, currentPage, action, output);
        }
    }

    @Override
    public void visit(Upgrades upgrades, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        if(action.getPage().equals("movies")) {
            FilterCountryOut.filterCountry(dataBase);
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
        }
        currentPage.setName(action.getPage());
        if(action.getPage().equals("logout")) {
            VisitorDestination v = new VisitPageDestination();
            Logout l = new Logout();
            l.acceptDestination(v, dataBase, currentPage, action, output);
        }
    }


}

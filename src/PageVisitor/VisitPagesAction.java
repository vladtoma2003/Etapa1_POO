package PageVisitor;

import Data.DataBase;
import Data.Movie;
import Data.OutputError;
import Data.User;
import Factory.ErrorFactory;
import Factory.MovieFactory;
import Factory.UserFactory;
import Pages.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Actionio;

public class VisitPagesAction implements VisitorAction {
    @Override
    public void visit(Page page, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {

    }

    @Override
    public void visit(Start start, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        if(!start.canDoAction(action.getFeature())) {
            OutputError stdError = ErrorFactory.standardError(dataBase);
            output.addPOJO(stdError);
            currentPage.setName("start");
            return;
        }
    }

    @Override
    public void visit(Login login, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        User us = UserFactory.newUser(action.getCredentials());
        if (!dataBase.existsUser(us)) {
            // error, user already exists
            OutputError stdError = ErrorFactory.standardError(dataBase);
            output.addPOJO(stdError);
            currentPage.setName("start");
            return;
        }
        dataBase.setLoggedUser(UserFactory.newUser(dataBase.getCurrentUser(us)));
        currentPage.setAuth(true);
        currentPage.setName("home auth");
        OutputError err = ErrorFactory.success(dataBase);
        output.addPOJO(err);
    }

    @Override
    public void visit(Register register, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
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

    @Override
    public void visit(Home home, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        if(!home.canDoAction(action.getFeature())) {
            OutputError stdError = ErrorFactory.standardError(dataBase);
            output.addPOJO(stdError);
            currentPage.setName("home auth");
        }
    }

    @Override
    public void visit(Movies movies, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        if(!movies.canDoAction(action.getFeature())) {
            OutputError stdError = ErrorFactory.standardError(dataBase);
            output.addPOJO(stdError);
            currentPage.setName("movies");
            return;
        }
        if(action.getFeature().equals("search")) {
            movies.search(dataBase, action.getStartsWith());
        } else if(action.getFeature().equals("filter")) {
            movies.filter(dataBase, action.getFilters());
        }
        OutputError err = ErrorFactory.success(dataBase);
        output.addPOJO(err);

    }

    @Override
    public void visit(Details details, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        if(!details.canDoAction(action.getFeature())) {
            OutputError stdError = ErrorFactory.standardError(dataBase);
            output.addPOJO(stdError);
            currentPage.setName("movies");
            return;
        }
    }

    @Override
    public void visit(Logout logout, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        dataBase.setLoggedUser(null);
        currentPage.setName("start");
        currentPage.setAuth(false);
    }
}

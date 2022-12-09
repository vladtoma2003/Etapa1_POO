package PageVisitor;

import Data.DataBase;
import Data.OutputError;
import Data.User;
import Factory.ErrorFactory;
import Factory.UserFactory;
import Pages.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Action;

public class VisitPagesAction implements VisitorAction {
    @Override
    public void visit(Page page, DataBase dataBase, Page currentPage, Action action, ArrayNode output) {

    }

    @Override
    public void visit(Start start, DataBase dataBase, Page currentPage, Action action, ArrayNode output) {

    }

    @Override
    public void visit(Login login, DataBase dataBase, Page currentPage, Action action, ArrayNode output) {
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
    public void visit(Register register, DataBase dataBase, Page currentPage, Action action, ArrayNode output) {
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
//        currentPage.setName("home auth");
//        currentPage.setAuth(true);
//        dataBase.setLoggedUser(usr);
    }

    @Override
    public void visit(Logout logout, DataBase dataBase, Page currentPage, Action action, ArrayNode output) {
        dataBase.setLoggedUser(null);
        currentPage.setName("start");
        currentPage.setAuth(false);
    }
}

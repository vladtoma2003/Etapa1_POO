package PageVisitor;

import Data.DataBase;
import Data.OutputError;
import Factory.ErrorFactory;
import Pages.*;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.Arrays;

public class VisitPageDestination implements VisitorDestination{
    @Override
    public void visit(Page page, DataBase dataBase, Page currentPage, String Destination, ArrayNode output) {

    }

    @Override
    public void visit(Start start, DataBase dataBase, Page currentPage, String Destination, ArrayNode output) {
        if(Arrays.stream(start.getDestinations()).noneMatch(o -> o.equals(Destination))) {
            OutputError err = ErrorFactory.standardError(dataBase);
            output.addPOJO(err);
            currentPage.setName("start");
            return;
        }
        if(Destination.equals("login")) {
            currentPage.setName("login");
        } else {
            currentPage.setName("register");
        }
    }

    @Override
    public void visit(Login login, DataBase dataBase, Page currentPage, String Destination, ArrayNode output) {
        if(!currentPage.getName().equals("start")
                && Arrays.stream(login.getDestinations()).noneMatch(o -> o.equals(Destination))) {
            OutputError err = ErrorFactory.standardError(dataBase);
            output.addPOJO(err);
            currentPage.setName("start");
            return;
        }
        currentPage.setName(Destination);
    }

    @Override
    public void visit(Logout logout, DataBase dataBase, Page currentPage, String Destination, ArrayNode output) {
        if(dataBase.getLoggedUser() == null) {
            OutputError err = ErrorFactory.standardError(dataBase);
            output.addPOJO(err);
            return;
        }
        currentPage.setName("start");
        currentPage.setAuth(false);
        dataBase.setLoggedUser(null);
    }

    @Override
    public void visit(Register register, DataBase dataBase, Page currentPage, String Destination, ArrayNode output) {
        if(Arrays.stream(register.getDestinations()).noneMatch(o -> o.equals(Destination))) {
            OutputError err = ErrorFactory.standardError(dataBase);
            output.addPOJO(err);
            currentPage.setName("start");
            return;
        }
        currentPage.setName(Destination);
    }
}

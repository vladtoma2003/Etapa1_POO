package Pages;

import Data.DataBase;
import PageVisitor.VisitorAction;
import PageVisitor.VisitorDestination;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Action;

import java.util.Arrays;

public class Page {
    private String name = "start";
    private boolean auth = false;

    public boolean isAuth() {
        return auth;
    }

    public void acceptAction(VisitorAction visitor, DataBase dataBase, Page currentPage, Action action, ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, action, output);
    }

    public void acceptDestination(VisitorDestination visitor, DataBase dataBase, Page currentPage, String Destination, ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, Destination, output);
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

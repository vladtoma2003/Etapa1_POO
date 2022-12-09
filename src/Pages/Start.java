package Pages;

import Data.DataBase;
import PageVisitor.VisitorAction;
import PageVisitor.VisitorDestination;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Action;

import java.util.Arrays;

public class Start extends Page {
    private final static String name = "start";
    private final static String destinations[] = {"login", "register"};
    private final static String actions[] = {};

    public boolean canDoAction(String action) {
        return Arrays.asList(actions).contains(action);
    }

    public boolean canGoThere(String destination) {
        return Arrays.asList(destinations).contains(destination);
    }
    public void acceptDestination(VisitorDestination visitor, DataBase dataBase, Page currentPage, String Destination, ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, Destination, output);
    }

    public void acceptAction(VisitorAction visitor, DataBase dataBase, Page currentPage, Action action, ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, action, output);
    }

    public String getName() {
        return name;
    }

    public String[] getDestinations() {
        return destinations;
    }

    public String[] getActions() {
        return actions;
    }
}

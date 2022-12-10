package Pages;

import Data.DataBase;
import PageVisitor.VisitorAction;
import PageVisitor.VisitorDestination;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Actionio;

import java.util.Arrays;

public class Upgrades extends Page{
    private final static String name = "upgrades";
    private final static String destinations[] = {"home", "movies", "logout"};
    private final static String actions[] = {"buy tokens", "buy premium account"};

    public boolean canDoAction(String action) {
        return Arrays.asList(actions).contains(action);
    }
    public boolean canGoThere(String destination) {
        return Arrays.asList(destinations).contains(destination);
    }

    public void acceptDestination(VisitorDestination visitor, DataBase dataBase, Page currentPage, Actionio Destination, ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, Destination, output);
    }

    public void acceptAction(VisitorAction visitor, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, action, output);
    }
    @Override
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

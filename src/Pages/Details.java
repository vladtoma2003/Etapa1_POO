package Pages;

import Data.DataBase;
import PageVisitor.VisitorAction;
import PageVisitor.VisitorDestination;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Actionio;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Setter
@Getter
public class Details extends Page {
    private final static String name = "login";
    private final static String destinations[] = {"home", "movies", "upgrades", "logout"};
    private final static String actions[] = {"purchase", "see details", "watch", "like", "rate"};

    public boolean canDoAction(final String action) {
        return Arrays.asList(actions).contains(action);
    }

    public boolean canGoThere(final String destination) {
        return Arrays.asList(destinations).contains(destination);
    }

    public void acceptDestination(final VisitorDestination visitor, final DataBase dataBase, final Page currentPage, final Actionio Destination, final ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, Destination, output);
    }

    public void acceptAction(final VisitorAction visitor, final DataBase dataBase, final Page currentPage, final Actionio action, final ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, action, output);
    }
}

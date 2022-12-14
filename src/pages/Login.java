package pages;

import java.util.Arrays;

import data.DataBase;
import visitor.VisitorAction;
import visitor.VisitorDestination;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Actionio;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Login extends Page {
    private final String name = "login";
    private final String[] destinations = {};
    private final String[] actions = {"login"};

    /**
     * Checks if the "action" string is in the actions array
     *
     * @param action
     * @return
     */
    public boolean canDoAction(final String action) {
        return Arrays.asList(actions).contains(action);
    }

    /**
     * Checks if the "destination" string is in the destinations array
     *
     * @param destination
     * @return
     */
    public boolean canGoThere(final String destination) {
        return Arrays.asList(destinations).contains(destination);
    }

    /**
     * accept method for the visitor
     *
     * @param visitor
     * @param dataBase
     * @param currentPage
     * @param destination
     * @param output
     */
    public void acceptDestination(final VisitorDestination visitor,
                                  final DataBase dataBase, final Page currentPage,
                                  final Actionio destination, final ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, destination, output);
    }

    /**
     * accept method for the visitor
     *
     * @param visitor
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    public void acceptAction(final VisitorAction visitor,
                             final DataBase dataBase, final Page currentPage,
                             final Actionio action, final ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, action, output);
    }
}

package pages;

import data.DataBase;
import pagevisitor.VisitorAction;
import pagevisitor.VisitorDestination;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Actionio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {
    private String name = "start";
    private boolean auth = false;

    /**
     * checks if the user is authenticated
     *
     * @return
     */
    public boolean isAuth() {
        return auth;
    }

    /**
     * Checks if the "destination" string is in the destinations array
     *
     * @param destination
     * @return
     */
    public boolean canGoThere(final String destination) {
        return false;
    }

    /**
     * Checks if the "action" string is in the actions array
     *
     * @param destination
     * @return
     */
    public boolean canDoAction(final String destination) {
        return false;
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
    public void acceptAction(final VisitorAction visitor, final DataBase dataBase,
                             final Page currentPage, final Actionio action,
                             final ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, action, output);
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
}

package Pages;

import Data.DataBase;
import PageVisitor.VisitorAction;
import PageVisitor.VisitorDestination;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Actionio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {
    private String name = "start";
    private boolean auth = false;

    public boolean isAuth() {
        return auth;
    }

    public boolean canGoThere(final String Destination) {
        return false;
    }

    public boolean canDoAction(final String Destination) {
        return false;
    }

    public void acceptAction(final VisitorAction visitor, final DataBase dataBase, final Page currentPage, final Actionio action, final ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, action, output);
    }

    public void acceptDestination(final VisitorDestination visitor, final DataBase dataBase, final Page currentPage, final Actionio Destination, final ArrayNode output) {
        visitor.visit(this, dataBase, currentPage, Destination, output);
    }
}

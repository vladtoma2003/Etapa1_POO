package PageVisitor;

import Data.DataBase;
import Pages.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Actionio;


public interface VisitorDestination {
    void visit(Page page, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output);

    void visit(Start start, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output);

    void visit(Login login, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output);

    void visit(Logout logout, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output);

    void visit(Register register, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output);

    void visit(Home home, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output);

    void visit(Movies movies, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output);

    void visit(Details details, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output);

    void visit(Upgrades upgrades, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output);

}

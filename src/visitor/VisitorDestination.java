package visitor;

import data.DataBase;
import pages.Page;
import pages.Upgrades;
import pages.Logout;
import pages.Login;
import pages.Movies;
import pages.Details;
import pages.Register;
import pages.Start;
import pages.Home;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Actionio;


public interface VisitorDestination {
    /**
     * visitor action for the destination
     *
     * @param page
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    void visit(Page page, DataBase dataBase,
               Page currentPage, Actionio action, ArrayNode output);

    /**
     * visitor action for the destination
     *
     * @param start
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    void visit(Start start, DataBase dataBase,
               Page currentPage, Actionio action, ArrayNode output);

    /**
     * visitor action for the destination
     *
     * @param login
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    void visit(Login login, DataBase dataBase,
               Page currentPage, Actionio action, ArrayNode output);

    /**
     * visitor action for the destination
     *
     * @param logout
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    void visit(Logout logout, DataBase dataBase,
               Page currentPage, Actionio action, ArrayNode output);

    /**
     * visitor action for the destination
     *
     * @param register
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    void visit(Register register, DataBase dataBase,
               Page currentPage, Actionio action, ArrayNode output);

    /**
     * visitor action for the destination
     *
     * @param home
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    void visit(Home home, DataBase dataBase,
               Page currentPage, Actionio action, ArrayNode output);

    /**
     * visitor action for the destination
     *
     * @param movies
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    void visit(Movies movies, DataBase dataBase,
               Page currentPage, Actionio action, ArrayNode output);

    /**
     * visitor action for the destination
     *
     * @param details
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    void visit(Details details, DataBase dataBase,
               Page currentPage, Actionio action, ArrayNode output);

    /**
     * visitor action for the destination
     *
     * @param upgrades
     * @param dataBase
     * @param currentPage
     * @param action
     * @param output
     */
    void visit(Upgrades upgrades, DataBase dataBase,
               Page currentPage, Actionio action, ArrayNode output);

}

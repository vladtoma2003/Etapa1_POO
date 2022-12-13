package mainprogram;

import data.DataBase;
import data.OutputError;
import factory.ErrorFactory;
import pagevisitor.VisitPageDestination;
import pagevisitor.VisitPagesAction;
import pagevisitor.VisitorAction;
import pagevisitor.VisitorDestination;
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
import fileio.Input;

public final class Actions {
    /**
     * Calls the right command and executes it
     *
     * @param dataBase
     * @param currentPage
     * @param inputData
     * @param output
     */
    public static void commands(final DataBase dataBase,
                                final Page currentPage,
                                final Input inputData, final ArrayNode output) {
        for (var action : inputData.getActions()) {
            Page page;
            page = switch (currentPage.getName()) {
                case "start" -> new Start();
                case "login" -> new Login();
                case "register" -> new Register();
                case "home auth" -> new Home();
                case "movies" -> new Movies();
                case "see details" -> new Details();
                case "upgrades" -> new Upgrades();
                default -> new Logout();
            };
            if (action.getType().equals("change page")) {
                if (!page.canGoThere(action.getPage())) {
                    OutputError stdError = ErrorFactory.standardError(dataBase);
                    output.addPOJO(stdError);
                    if (currentPage.getName().equals("login")
                            || currentPage.getName().equals("register")) {
                        currentPage.setName("start");
                    }
                    continue;
                }
                VisitorDestination visitor = new VisitPageDestination();
                page.acceptDestination(visitor, dataBase, currentPage, action, output);
            } else {
                if (!page.canDoAction(action.getFeature())) {
                    OutputError stdError = ErrorFactory.standardError(dataBase);
                    output.addPOJO(stdError);
                    continue;
                }
                VisitorAction v = new VisitPagesAction();
                page.acceptAction(v, dataBase, currentPage, action, output);
            }
        }
    }

    private Actions() {
    }

}

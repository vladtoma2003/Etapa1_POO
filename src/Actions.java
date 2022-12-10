import Data.DataBase;
import Data.OutputError;
import Factory.ErrorFactory;
import PageVisitor.VisitPageDestination;
import PageVisitor.VisitPagesAction;
import PageVisitor.VisitorAction;
import PageVisitor.VisitorDestination;
import Pages.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;

public class Actions {
    public static void Commands (DataBase dataBase, Page currentPage, Input inputData, ArrayNode output){
        for(var action:inputData.getActions()) {
            Page page;
                page = switch (currentPage.getName()) {
                    case "start" -> new Start();
                    case "login" -> new Login();
                    case "register" -> new Register();
                    case "home auth" -> new Home();
                    case "movies" -> new Movies();
                    case "see details" -> new Details();
                    default -> new Logout();
                };

            if(action.getType().equals("change page")) {
                VisitorDestination visitor = new VisitPageDestination();
                page.acceptDestination(visitor, dataBase, currentPage, action, output);
            } else {
                VisitorAction v = new VisitPagesAction();
                page.acceptAction(v, dataBase, currentPage, action, output);
            }
        }
    }
}

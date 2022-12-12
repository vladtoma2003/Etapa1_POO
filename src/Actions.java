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
//            System.out.println(currentPage.getName());
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
            if(action.getType().equals("change page")) {
//                System.out.println(page.getName() + action.getPage());
                if(!page.canGoThere(action.getPage())) {
                    OutputError stdError = ErrorFactory.standardError(dataBase);
                    output.addPOJO(stdError);
                    if(currentPage.getName().equals("login") || currentPage.getName().equals("register")) {
                        currentPage.setName("start");
                    }
                    continue;
                }
                VisitorDestination visitor = new VisitPageDestination();
                page.acceptDestination(visitor, dataBase, currentPage, action, output);
//                System.out.println("changed to " + currentPage.getName());
            } else {
//                System.out.println("feature:" + action.getFeature());
                if(!page.canDoAction(action.getFeature())) {
                    OutputError stdError = ErrorFactory.standardError(dataBase);
                    output.addPOJO(stdError);
                    continue;
                }
                VisitorAction v = new VisitPagesAction();
                page.acceptAction(v, dataBase, currentPage, action, output);
            }
        }
    }
}

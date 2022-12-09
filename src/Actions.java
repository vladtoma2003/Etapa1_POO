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
            switch (action.getType()) {
                case "change page" -> {
                    Page page;
                    if(action.getPage().equals("logout")) {
                         page = new Logout();
                    } else {
                         page = switch (currentPage.getName()) {
                            case "start" -> new Start();
                            case "login" -> new Login();
                            case "register" -> new Register();
                            default -> new Page();
                        };
                    }
                    VisitorDestination visitor = new VisitPageDestination();
//                    System.out.println(page.getName());
                    page.acceptDestination(visitor, dataBase, currentPage, action.getPage(), output);
                }
                case "on page" -> {
                    Page page = new Page();
                    if (!currentPage.isAuth()) {
                        if (currentPage.getName().equals("login")) {
                            Login login = new Login();
                            if (!login.canDoAction(action.getFeature())) {
                                // error: unavailable feature
                                OutputError err = ErrorFactory.standardError(dataBase);
                                output.addPOJO(err);
                                return;
                            }
                            page = login;
                        }
                        if (currentPage.getName().equals("register")) {
                            Register register = new Register();
                            if (!register.canDoAction(action.getFeature())) {
                                // error: unavailable feature
                                OutputError err = ErrorFactory.standardError(dataBase);
                                output.addPOJO(err);
                                return;
                            }
                            page = register;
                        }
                    } else {
                        if (action.getFeature().equals("logout")) {
                            Logout logout = new Logout();
                            page = logout;
                        }
                    }
//                    System.out.println("visitor, " + page.getName());
                    VisitorAction v = new VisitPagesAction();
                    page.acceptAction(v, dataBase, currentPage, action, output);
                }
            }
        }
    }
}

//package Visitor;
//
//import Data.DataBase;
//import Data.OutputError;
//import Factory.ErrorFactory;
//import Pages.Page;
//import Data.User;
//import Factory.UserFactory;
//import Pages.Login;
//import Pages.Register;
//import com.fasterxml.jackson.databind.node.ArrayNode;
//import fileio.Action;
//
//public class visitorUnlogged implements visitor {
//
//    @Override
//    public void visit(DataBase dataBase, Page currentPage, Action action, ArrayNode output) {
//        switch (action.getType()) {
//            case "change page" -> {
//                if (action.getPage().equals("login") && currentPage.getName().equals("start")) {
//                    currentPage.setName("login");
//                } else if (action.getPage().equals("register") && currentPage.getName().equals("start")) {
//                    currentPage.setName("register");
//                } else {
//                    currentPage.setName("start");
//                }
//            }
//            case "on page" -> {
//                switch (action.getFeature()) {
//                    case "login" -> {
//                        if (!currentPage.getName().equals("login")
//                                && !Login.canDoAction(action.getFeature())) {
//                            OutputError stdError = ErrorFactory.standardError("login: pagina gresita");
//                            output.addPOJO(stdError);
//                            break;
//                        }
//                        User us = UserFactory.newUser(action.getCredentials());
//                        if (!dataBase.existsUser(us)) {
//                            // error
//                            OutputError stdError = ErrorFactory.standardError("login: nu exista user" + us.getPassword());
//                            output.addPOJO(stdError);
//                            break;
//                        }
//                        dataBase.setLoggedUser(UserFactory.newUser(dataBase.getCurrentUser(us)));
//                        currentPage.setAuth(true);
//                        currentPage.setName("home auth");
//                        OutputError err = ErrorFactory.error(dataBase.getLoggedUser());
//                        output.addPOJO(err);
//                    }
//                    case "register" -> {
//                        if (!currentPage.getName().equals("register")
//                                && !Register.canDoAction(action.getFeature())) {
//                            // error
//                            OutputError stdError = ErrorFactory.standardError("register: pagina gresita");
//                            output.addPOJO(stdError);
//                            break;
//                        }
//                        User usr = UserFactory.newUser(action.getCredentials());
//                        if (dataBase.existsUser(usr)) {
//                            // error
//                            OutputError stdError = ErrorFactory.standardError("register: user exista deja");
//                            output.addPOJO(stdError);
//                            break;
//                        }
//                        dataBase.getUsers().add(usr);
//                        currentPage.setName("start");
//                    }
//                }
//            }
//        }
//    }
//}

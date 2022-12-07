package Visitor;

import Data.DataBase;
import Data.Page;
import Data.User;
import Factory.UserFactory;
import Pages.Login;
import Pages.Register;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Action;

public class visitorNotAUTH implements visitor {

    @Override
    public void visit(DataBase dataBase, User currentUser, Page currentPage, Action action, ArrayNode output) {
        if (!currentPage.isAuth()) {
            switch (action.getType()) {
                case "change page" -> {
                    if (action.getPage().equals("login") && currentPage.getName().equals("start")) {
                        currentPage.setName("login");
                    } else if (action.getPage().equals("register") && currentPage.getName().equals("start")) {
                        currentPage.setName("register");
                    } else {
                        currentPage.setName("start");
                    }
                }
                case "on page" -> {
                    switch (action.getFeature()) {
                        case "login" -> {
                            if (!Login.canDoAction(action.getFeature())) {
                                // error
                                break;
                            }
                            User us = UserFactory.newUser(action.getCredentials());
                            if (!dataBase.existsUser(us)) {
                                // error
                                break;
                            }
                            currentUser = UserFactory.newUser(dataBase.getCurrentUser(us));
                            currentPage.setAuth(true);
                            currentPage.setName("home auth");
                        }
                        case "register" -> {
                            if (!Register.canDoAction(action.getFeature())) {
                                // error
                                break;
                            }
                            User usr = UserFactory.newUser(action.getCredentials());
                            if (dataBase.existsUser(usr)) {
                                // error
                                break;
                            }
                            currentUser = UserFactory.newUser(us);
                            dataBase.getUsers().add(currentUser);
                            currentPage.setAuth(true);
                            currentPage.setName("home auth");
                        }
                    }
                }
            }

        }
    }
}

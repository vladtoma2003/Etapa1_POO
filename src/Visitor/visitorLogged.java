package Visitor;

import Data.DataBase;
import Pages.Page;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Action;

public class visitorLogged implements visitor{

    @Override
    public void visit(DataBase dataBase, Page currentPage, Action action, ArrayNode output) {
        switch (action.getType()) {
            case "change page":
                if (action.getPage().equals("logout")) {
                    if (dataBase.getLoggedUser() != null && currentPage.isAuth()) {
                        dataBase.setLoggedUser(null);
                        currentPage.setName("start");
                        currentPage.setAuth(false);
                    }
                }
                break;
        }
    }
}

package Visitor;

import Data.DataBase;
import Data.Page;
import Data.User;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Action;

public interface visitor {
    void visit(DataBase dataBase, User currentUser, Page currentPage, Action action, ArrayNode output);
}

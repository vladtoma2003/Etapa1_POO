package Visitor;

import Data.DataBase;
import Pages.Page;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Action;

public interface visitor {
    void visit(DataBase dataBase, Page currentPage, Action action, ArrayNode output);
}

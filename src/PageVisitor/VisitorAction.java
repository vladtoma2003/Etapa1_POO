package PageVisitor;

import Data.DataBase;
import Pages.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Action;

import javax.xml.crypto.Data;

public interface VisitorAction {
    void visit(Page page, DataBase dataBase, Page currentPage, Action action, ArrayNode output);
    void visit (Start start, DataBase dataBase, Page currentPage, Action action, ArrayNode output);
    void visit (Login login, DataBase dataBase, Page currentPage, Action action, ArrayNode output);
    void visit (Logout logout, DataBase dataBase, Page currentPage, Action action, ArrayNode output);
    void visit (Register register, DataBase dataBase, Page currentPage, Action action, ArrayNode output);
}

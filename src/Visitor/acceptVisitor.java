//package Visitor;
//
//import Data.DataBase;
//import Pages.Page;
//import com.fasterxml.jackson.databind.node.ArrayNode;
//import fileio.Action;
//import fileio.Input;
//
//import java.util.ArrayList;
//
//public class acceptVisitor {
//    public static void accept(DataBase dataBase, Page currentPage, Input inputData, ArrayNode output) {
//        for(var action:inputData.getActions()) {
//            if (currentPage.isAuth()) {
//                visitorLogged logged = new visitorLogged();
//                logged.visit(dataBase, currentPage, action, output);
//                System.out.println("cevafrate");
//            } else {
//                visitorUnlogged unlogged = new visitorUnlogged();
//                unlogged.visit(dataBase, currentPage, action, output);
//                System.out.println("ceva frate");
//            }
//        }
//    }
//}

import Data.DataBase;
import Factory.MovieFactory;
import Factory.UserFactory;
import Pages.Page;
//import Visitor.acceptVisitor;
//import Visitor.visitor;
import fileio.Input;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Movieio;
import fileio.Userio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String inPath = args[0];
        String outPath = args[1];
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(inPath), Input.class);
        ArrayNode output = objectMapper.createArrayNode();

        Page currenPage = new Page();

        DataBase dataBase = new DataBase();
        for(Userio user:inputData.getUsers()) {
            dataBase.getUsers().add(UserFactory.newUser(user));
        }

        for(Movieio movie:inputData.getMovies()) {
            dataBase.getAvailableMovies().add(MovieFactory.newMovie(movie));
        }

        Actions.Commands(dataBase, currenPage, inputData, output);
        //TODO: rate, movie->movie(cred ca e gata asta)

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(outPath), output);
        objectWriter.writeValue(new File("checker/resources/out/ceva.json"), output);

    }
}

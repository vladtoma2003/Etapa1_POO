import Data.DataBase;
import Factory.MovieFactory;
import Factory.UserFactory;
import fileio.Input;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Movieio;
import fileio.Userio;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String inPath = args[0];
        String outPath = args[1];
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(inPath), Input.class);
        ArrayNode output = objectMapper.createArrayNode();


        DataBase dataBase = new DataBase();
        for(Userio user:inputData.getUsers()) {
            dataBase.getUsers().add(UserFactory.newUser(user));
        }

        for(Movieio movie:inputData.getMovies()) {
            dataBase.getMovies().add(MovieFactory.newMovie(movie));
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(outPath), output);
        objectWriter.writeValue(new File("checker/resources/out/ceva.json"), output);

    }
}

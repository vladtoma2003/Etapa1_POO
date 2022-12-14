import data.DataBase;
import factory.MovieFactory;
import factory.UserFactory;
import pages.Page;
import fileio.Input;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Movieio;
import fileio.Userio;

import java.io.File;
import java.io.IOException;

public final class Main {
    /**
     * main
     *
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        String inPath = args[0];
        String outPath = args[1];
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(inPath), Input.class);
        ArrayNode output = objectMapper.createArrayNode();

        Page currenPage = new Page();

        DataBase dataBase = new DataBase();
        for (Userio user : inputData.getUsers()) {
            dataBase.getUsers().add(UserFactory.newUser(user));
        }

        for (Movieio movie : inputData.getMovies()) {
            dataBase.getAvailableMovies().add(MovieFactory.newMovie(movie));
        }

        Actions.commands(dataBase, currenPage, inputData, output);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(outPath), output);
    }

    private Main() {
    }
}

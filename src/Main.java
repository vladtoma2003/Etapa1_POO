import Data.Input;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String inPath = args[0];
        String outPath= args[1];
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(inPath), Input.class);
        ArrayNode output = objectMapper.createArrayNode();

        System.out.println(inputData.getUsers().get(0).toString());
    }
}

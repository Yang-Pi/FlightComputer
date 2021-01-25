import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtils {
    public static JSONArray getJsonArrayFromFile(String fileName, String arrayName) throws Exception {
        JSONArray jsonArray = null;
        try {
            FileReader reader = new FileReader(fileName);
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(reader);
            jsonArray = (JSONArray) jsonObject.get(arrayName);
        } catch (Exception e) {
            throw e;
        }

        return jsonArray;
    }

    public static void writeComputationsToFile(ArrayList<Double> computations, String fileName) throws Exception{
        try(FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < computations.size(); ++i) {
                writer.write((i + 1) + ". " + computations.get(i) + "\n");
            }
        } catch (IOException e) {
            throw e;
        }
    }
}


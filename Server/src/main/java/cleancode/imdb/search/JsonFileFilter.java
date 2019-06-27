package cleancode.imdb.search;

import javax.json.Json;
import javax.json.stream.JsonCollectors;
import javax.json.stream.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class JsonFileFilter {
    public static String filterByFields(BufferedReader aBufferedReader, List<String> fields) throws IOException {
        if (fields.size() > 0) {
            JsonParser parser = Json.createParser(aBufferedReader);
            parser.next();
            String result = parser.getObjectStream()
                    .filter(v -> fields.contains(v.getKey()))
                    .collect(JsonCollectors.toJsonObject())
                    .toString();
            parser.close();
            return result;
        } else {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = aBufferedReader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        }
    }
}

package cleancode.imdb.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandTokenizer {
    public static List<String> tokenize(String line) {
        List<String> tokens = new ArrayList<>();
        Matcher m = Pattern.compile("\"([^\"]*)\"|([^\\s,=]+)").matcher(line);
        while (m.find()) {
            if (m.group(1) != null) {
                tokens.add(m.group(1));
            } else {
                tokens.add(m.group(2));
            }
        }
        return tokens.stream()
                .flatMap(s -> s.startsWith("-") ? Arrays.stream(s.split("=")) : Stream.of(s))
                .filter(Objects::nonNull)
                .map(String::trim)
                .collect(Collectors.toList());
    }
}

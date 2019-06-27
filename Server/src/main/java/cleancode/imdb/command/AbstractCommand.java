package cleancode.imdb.command;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class AbstractCommand implements Command {
    private String commandName;

    public AbstractCommand() {
        commandName = Arrays.stream(
                getClass().getName()
                        .replaceAll("(?:.*\\.)?", "")
                        .split("(?:(?<=[a-z])(?![a-z]))|(?=[A-Z][a-z]+?)"))
                        .map(String::toLowerCase)
                        .collect(Collectors.joining("-"));
    }

    @Override
    public String toString() {
        return commandName;
    }
}

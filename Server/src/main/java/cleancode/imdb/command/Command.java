package cleancode.imdb.command;

import java.util.List;

public interface Command {
    String execute(List<String> args);
}

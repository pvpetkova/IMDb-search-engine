package cleancode.imdb.command;

import java.util.List;

public class Help extends AbstractCommand {
    private static Help instance;

    private Help() {}

    public static Help getInstance() {
        if (instance == null) {
            instance = new Help();
        }
        return instance;
    }

    @Override
    public String execute(List<String> args) {
        return "Usage:\n\n" +
                "    get-movie <movie_name>\n" +
                "        --fields=[field_1, field_2]\n" +
                "\n" +
                "    get-tv-series <name>\n";
    }
}

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
                "    get-movies \n" +
                "        --order=[asc|desc]\n" +
                "        --genres=[genre_1, genre_2]\n" +
                "        --actors=[actor_1, actor_2]\n" +
                "\n" +
                "    get-tv-series <name>\n" +
                "        --season=<value>\n" +
                "\n" +
                "    get-movie-poster <name>";
    }
}

package cleancode.imdb.command;

import cleancode.imdb.search.LocalFilesSearcher;
import cleancode.imdb.search.RESTSearcher;

import java.util.ArrayList;
import java.util.List;

public class GetMovie extends AbstractCommand {
    private LocalFilesSearcher localFilesSearch;
    private RESTSearcher restClientSearch;

    public GetMovie(LocalFilesSearcher localFilesSearch, RESTSearcher restClientSearch) {
        this.localFilesSearch = localFilesSearch;
        this.restClientSearch = restClientSearch;
    }

    @Override
    public String execute(List<String> args) {
        boolean hasFields = args.size() > 2;
        String movieName = args.get(1);
        if (args.size() < 2 || movieName.startsWith("-"))
            return helpMessage();

        List<String> fields = new ArrayList<>();
        if (hasFields) {
            if (args.get(2).equals("-f") || args.get(2).equals("--fields")) {
                for (int i = 3; i < args.size(); i++) {
                    fields.add(args.get(i));
                }
            } else return helpMessage();
        }
        return localFilesSearch.searchByNameAndFields(movieName, fields)
                .orElseGet(() -> String.valueOf(restClientSearch.searchByNameAndFields(movieName, fields)));
    }

    private String helpMessage() {
        return String.format("Usage: \n%s", "get-movie <movie_name> --fields=[field_1, field_2]\n");
    }
}

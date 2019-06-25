package cleancode.imdb.model;

import java.util.List;

public class Season {
    private String title;
    private String season;
    private String totalSeasons;
    private List<Episode> episodes;

    @Override
    public String toString() {
        return "Season{" +
                "title='" + title + '\'' +
                ", season='" + season + '\'' +
                ", totalSeasons='" + totalSeasons + '\'' +
                ", episodes=" + episodes +
                '}';
    }
}

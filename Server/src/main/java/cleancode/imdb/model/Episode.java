package cleancode.imdb.model;

public class Episode {
    private String title;
    private String released;
    private String episode;
    private String imdbRating;
    private String imdbID;

    @Override
    public String toString() {
        return "Episode{" +
                "title='" + title + '\'' +
                ", released='" + released + '\'' +
                ", episode='" + episode + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbID='" + imdbID + '\'' +
                '}';
    }
}

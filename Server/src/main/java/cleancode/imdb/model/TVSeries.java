package cleancode.imdb.model;

public class TVSeries extends Listing {
    private String totalSeasons;
    private String response;

    @Override
    public String toString() {
        return "TVSeries{" +
                super.toString() +
                ", totalSeasons='" + totalSeasons + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}

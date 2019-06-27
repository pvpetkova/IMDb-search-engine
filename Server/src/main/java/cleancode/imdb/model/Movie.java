package cleancode.imdb.model;

public class Movie extends Listing {
    private String dvd;
    private String boxOffice;
    private String production;
    private String website;
    private String response;

    @Override
    public String toString() {
        return "Movie{" +
                super.toString() +
                ", dvd='" + dvd + '\'' +
                ", boxOffice='" + boxOffice + '\'' +
                ", production='" + production + '\'' +
                ", website='" + website + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}

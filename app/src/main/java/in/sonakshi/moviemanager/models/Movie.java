package in.sonakshi.moviemanager.models;

import java.util.List;

/**
 * Created by sonakshi on 5/6/2020.
 */

public class Movie {
    private int id;
    private String title;
    private String image;
    private float rating;
    private int releaseYear;
    private List<String> genre;

    public Movie(int id, String title, String image, float rating, int releaseYear, List<String> genre) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public float getRating() {
        return rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public List<String> getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", rating=" + rating +
                ", releaseYear=" + releaseYear +
                ", genre=" + genre +
                '}';
    }
}

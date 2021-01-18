package sample.Be;

import java.time.LocalDate;

public class Movie {
    private final int id;
    private final String title;
    private Double rating;
    private LocalDate date;
    private String path;

    public Movie(int id, String title, Double rating, String path, LocalDate date) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.date = date;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return title + "     " + rating;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}


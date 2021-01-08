package sample.Be;

public class Movie {
    private int id;
    private String title;
    private Double rating;

    public Movie(int id, String title, Double rating) {
        this.id=id;
        this.title=title;
        this.rating=rating;
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

    @Override
    public String toString() {
        return title;
    }
}


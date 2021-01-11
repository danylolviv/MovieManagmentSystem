package sample.Be;

public class CatMovie {
    private int id;
    private String movieTile;

    public CatMovie(int id, String mTtle){
        this.id = id;
        this.movieTile = mTtle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieTile() {
        return movieTile;
    }

    public void setMovieTile(String movieTile) {
        this.movieTile = movieTile;
    }
}

package sample.Be;

public class CatMovie {
    private int catId;
    private int movId;

    public CatMovie(int catId, int movId) {
        this.catId = catId;
        this.movId = movId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int id) {
        catId = id;
    }

    public int getMovId() {
        return movId;
    }

    public void setMovId(int id) {
        movId = id;
    }
}

package sample.Be;

public class Category {
    private final String name;
    private final int id;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}




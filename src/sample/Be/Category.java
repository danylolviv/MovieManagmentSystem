package sample.Be;

public class Category {
    private String name;
    private  int id;

    public Category(int id,String name){
        this.id=id;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return name + "  " + id;
    }
}




package sample.Gui.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Be.Category;
import sample.Dal.DAOCategory;

import java.util.List;

public class CategoryModel {
    private final ObservableList<Category> categories;

    public CategoryModel(){
        this.categories = FXCollections.observableArrayList();
    }

    public ObservableList<Category> getAllCategories(){
        DAOCategory db = new DAOCategory();
        categories.addAll(db.getAllCategories());
        return categories;
    }
}

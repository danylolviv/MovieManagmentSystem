package sample.Gui.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Be.Category;
import sample.Bll.CategoryManager;
import sample.Dal.DAOCategory;

import java.util.List;

public class CategoryModel {
    private final ObservableList<Category> categories;
    
    private CategoryManager catManager;

    public CategoryModel(){
        this.catManager = new CategoryManager();
        this.categories = FXCollections.observableArrayList();
        DAOCategory db = new DAOCategory();
        categories.addAll(db.getAllCategories());
    }

    public ObservableList<Category> getAllCategories(){
        return categories;
    }


    public void addNewCategory(String catName) {
        catManager.addNewCategory(catName);
    }
}

package sample.Gui.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Be.Category;
import sample.Bll.CategoryManager;
import sample.Dal.DAOCategory;

public class CategoryModel {
    private final ObservableList<Category> categories;
    private final ObservableList<Category> addMovieCategories;
    private final CategoryManager catManager;

    public CategoryModel() {
        this.catManager = new CategoryManager();
        this.categories = FXCollections.observableArrayList();
        this.addMovieCategories = FXCollections.observableArrayList();
        DAOCategory db = new DAOCategory();
        categories.addAll(db.getAllCategories());
    }

    public ObservableList<Category> getAddMovieCategories() {
        return addMovieCategories;
    }

    public void addItemToCurrentMovie(Category cat) {
        addMovieCategories.add(cat);
    }

    public ObservableList<Category> getAllCategories() {
        return categories;
    }


    public void deleteCategory(int categoryId) {
        catManager.deleteCategory(categoryId);
    }

    public void addNewCategory(String catName) {
        categories.add(catManager.addNewCategory(catName));
    }
}

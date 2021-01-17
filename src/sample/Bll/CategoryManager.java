package sample.Bll;

import sample.Be.Category;
import sample.Dal.DAOCategory;

import java.util.List;

public class CategoryManager {

    private DAOCategory daoCategory;

    public CategoryManager(){
        this.daoCategory = new DAOCategory();
    }

    public List<Category> getAllCategories(){
        return daoCategory.getAllCategories();
    }

    public void addNewCategory(String catName){
        Category recievedCategory = daoCategory.addNewCategory(catName);
        System.out.println("recieved cat" +  recievedCategory.toString());
    }
}

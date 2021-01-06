package sample.Gui.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import sample.Be.Category;
import sample.Dal.DAOCategory;

import java.net.URL;
import java.util.ResourceBundle;

public class SidechickController implements Initializable {

    private ObservableList<Category> categories;

    @FXML
    private ListView<Category > listCategory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllCategories();
    }

    public void getAllCategories(){
        DAOCategory db = new DAOCategory();
        categories = FXCollections.observableArrayList();
        categories.addAll(db.getAllCategories());
        listCategory.setItems(categories);
    }
}

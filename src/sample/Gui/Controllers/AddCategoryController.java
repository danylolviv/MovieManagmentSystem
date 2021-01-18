package sample.Gui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Gui.Models.CategoryModel;

public class AddCategoryController {
    @FXML
    private Button closeButton;
    @FXML
    private TextField textField;

    private CategoryModel theCategoryModel;

    public void addNewCategory(ActionEvent actionEvent) {
        theCategoryModel.addNewCategory(textField.getText());
        textField.clear();
        closeWindow(actionEvent);
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void setCategories(CategoryModel theCategoryModel) {
        this.theCategoryModel = theCategoryModel;
    }

    public void addCat(ActionEvent actionEvent) {
        addNewCategory(actionEvent);
    }
}

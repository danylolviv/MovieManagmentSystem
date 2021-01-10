package sample.Gui.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Be.Category;
import sample.Be.Movie;
import sample.Gui.Models.CategoryModel;
import sample.Gui.Models.MovieModel;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddPathViewController implements Initializable {


    CategoryModel categoryModel;

    @FXML
    private ListView addedCategories;
    @FXML
    public ChoiceBox<Category> categoriesList;
    @FXML
    private TextField personalRating;
    @FXML
    private AnchorPane anchorid;
    @FXML
    private TextField pathToMovie;
    @FXML
    private TextField movieTitleField;

    private MovieModel mModel;
    private List<Category> categories;
    private ObservableList cat = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setCategories(List<Category> cat) {
        this.categories = cat;
        categoriesList.getItems().addAll(categories);
    }

    public void addMovie(){
        String title = movieTitleField.getText();
        String moviePath = pathToMovie.getText();
        String mRaiting = personalRating.getText();
        double raiting = Double.parseDouble(mRaiting);
        Movie newMovie = new Movie(1,title,raiting,moviePath);
        mModel.addMovie(newMovie);

    }

    private File randomFile;

    public void findFile(ActionEvent actionEvent) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("OpenFileDialog");
        Stage stage = (Stage) anchorid.getScene().getWindow();
        File fl = fileChooser.showOpenDialog(stage);
        randomFile = fl;



        if (fl != null){
            System.out.println("Path : " + fl.getPath() );
            pathToMovie.setText(fl.getPath());
        }

    }

    public void openIt(ActionEvent actionEvent)  {
        addMovie();
    }

    public void addCategory(ActionEvent actionEvent) {
        cat.add(categoriesList.getSelectionModel().getSelectedItem());
        addedCategories.setItems(cat);
    }

    public void deleteCategory(ActionEvent actionEvent) {
        addedCategories.getItems().remove(addedCategories.getSelectionModel().getSelectedItem());
    }
}

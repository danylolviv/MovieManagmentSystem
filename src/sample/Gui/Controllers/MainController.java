package sample.Gui.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.Be.Category;
import sample.Dal.DAOCategory;
import sample.Dal.DAOMovie;
import sample.Be.Movie;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private ObservableList<Category> categories;
    private ObservableList<Movie> movies;

    @FXML
    private ListView<Category> listCategory;
    @FXML
    private ListView<Movie> listMovie;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllCategories();
        getAllMovies();
    }

    public void getAllCategories(){
        DAOCategory db = new DAOCategory();
        categories = FXCollections.observableArrayList();
        categories.addAll(db.getAllCategories());
        listCategory.setItems(categories);
    }
    public void getAllMovies(){
        DAOMovie db = new DAOMovie();
        movies = FXCollections.observableArrayList();
        movies.addAll(db.getAllMovies());
        listMovie.setItems(movies);
    }
    @FXML
    private void openWindow(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/Gui/Views/Sidechick.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Sidechick");
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void openCategories(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/Gui/Views/CategoriesView.fxml"));
        Stage pastaStage = new Stage();
        pastaStage.setTitle("Categories");
        pastaStage.setScene(new Scene(root));
        pastaStage.show();
    }

    public void addPath(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/Gui/Views/AddPathView.fxml"));
        Stage pastaStage = new Stage();
        pastaStage.setTitle("Pasta");
        pastaStage.setScene(new Scene(root));
        pastaStage.show();
    }
}

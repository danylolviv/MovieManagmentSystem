package sample.Gui.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.Be.Movie;
import sample.Gui.Models.MovieModel;

import java.net.URL;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {


    private ObservableList<Movie> movies;
    MovieModel movModel;

    @FXML
    private ListView<Movie> listMovie;

    @FXML
    private TextField typeField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllMovies();
        typeField.textProperty().addListener((observableValue, s, t1) -> {
            listMovie.setItems(movModel.searchedMovies(t1));
        });
    }

    public void getAllMovies(){
        movModel = new MovieModel();
        movies = FXCollections.observableArrayList();
        movies.addAll(movModel.getMovies());
        listMovie.setItems(movies);
    }
}

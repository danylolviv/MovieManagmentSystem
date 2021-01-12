package sample.Gui.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.Be.Category;
import sample.Be.Movie;
import sample.Gui.Models.CategoryModel;
import sample.Gui.Models.MovieModel;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    Double ratingSearchA;
    Double ratingSearchB;
    boolean ratingSearch;
    int rsbCounter = 1;

    public Button ratingSearchButton;
    public Slider ratingSearchSlider;
    private ObservableList<Category> categories;
    private ObservableList<Movie> movies;

    MovieModel movModel;
    CategoryModel categoryModel;
    @FXML
    private ListView<Category> listCategory;
    @FXML
    private ListView<Movie> listMovie;
    @FXML
    private TextField typeField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movModel = new MovieModel();
        getAllCategories();
        getAllMovies();

        typeField.textProperty().addListener((observableValue, s, t1) -> {
            if(ratingSearch){
                if(ratingSearchA<=ratingSearchB)listMovie.setItems(movModel.searchedMovies(t1,ratingSearchA,ratingSearchB));
                if(ratingSearchB<ratingSearchA)listMovie.setItems(movModel.searchedMovies(t1,ratingSearchB,ratingSearchA));
            }
            else listMovie.setItems(movModel.searchedMovies(t1));
        });
    }

    public void getAllCategories(){
        categoryModel = new CategoryModel();
        listCategory.setItems(categoryModel.getAllCategories());
    }

    public void getAllMovies(){
        listMovie.setItems(movModel.getMovies());
    }

    public void removeMovie(int movieId){
        movModel.deleteMovie(movieId);
    }

    @FXML
    private void openWindow(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/Gui/Views/Library.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Library");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }


    public void openCategories(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/Gui/Views/CategoriesView.fxml"));
        //((AddPathViewController)loader.getController()).setModel(categoryModel);
        Stage pastaStage = new Stage();
        pastaStage.setTitle("Categories");
        pastaStage.setScene(new Scene(root));
        pastaStage.show();
    }

    public void addPath(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Gui/Views/AddPathView.fxml"));
        Parent root = loader.load();
        ((AddPathViewController)loader.getController()).setCategories(listCategory.getItems());
        Stage pastaStage = new Stage();
        pastaStage.setTitle("Pasta");
        pastaStage.setScene(new Scene(root));
        pastaStage.show();
    }


    public void shiftRatingSearch(ActionEvent actionEvent) {
        System.out.println("wtf");
        if(rsbCounter == 1) {
            ratingSearchA = ratingSearchSlider.getValue();
            ratingSearchButton.setText("to");
            rsbCounter = 2;
        }
        if(rsbCounter == 2) {
            ratingSearchB = ratingSearchSlider.getValue();
            ratingSearchButton.setText("confirm");
            rsbCounter = 3;
        }
        if(rsbCounter == 3) {
            ratingSearchSlider.setDisable(true);
            ratingSearch = true;
            ratingSearchButton.setText("reset");
            rsbCounter = 4;
        }
        if(rsbCounter == 4) {
            ratingSearchSlider.setDisable(false);
            ratingSearch = false;
            ratingSearchButton.setText("from");
            rsbCounter = 1;
        }
    }

    public void removeMovie(ActionEvent actionEvent) {
        if(listMovie.getSelectionModel().getSelectedItems() != null){
            removeMovie(listMovie.getSelectionModel().getSelectedItem().getId());
            listMovie.getItems().remove(listMovie.getSelectionModel().getSelectedItem());
        }
    }



    public void playMovie(KeyEvent keyEvent) throws IOException {
        String pathTo = listMovie.getSelectionModel().getSelectedItem().getPath();
        if (pathTo != null){
            File video = new File(pathTo);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(video);
        }

    }
}

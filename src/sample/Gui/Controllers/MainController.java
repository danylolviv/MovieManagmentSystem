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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
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

    public Label ratingSearchRangeLbl;
    public Label titleView;
    public Rating ratingMovie;
    Double ratingSearchA;
    Double ratingSearchB;
    boolean ratingSearch;
    List<Category> searchedCategories = new ArrayList<Category>();

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
    private Movie selected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movModel = new MovieModel();
        getAllCategories();
        listMovie.setItems(movModel.getMovies());
        listCategory.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        typeField.textProperty().addListener((observableValue, s, t1) -> {
            searchMovies();
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
        Stage stage = new Stage();
        stage.setTitle("Categories");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void addPath(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Gui/Views/AddPathView.fxml"));
        Parent root = loader.load();
        ((AddPathViewController)loader.getController()).setCategories(listCategory.getItems());
        Stage pastaStage = new Stage();
        pastaStage.setTitle("Movie Settings");
        pastaStage.setScene(new Scene(root));
        pastaStage.show();
    }


    public void shiftRatingSearch() {
        switch (ratingSearchButton.getText()) {
            case "from:" -> {
                ratingSearchA = ratingSearchSlider.getValue();
                ratingSearchButton.setText("to:");
            }
            case "to:" -> {
                ratingSearchB = ratingSearchSlider.getValue();
                ratingSearchButton.setText("confirm");
            }
            case "confirm" -> {
                ratingSearchSlider.setVisible(false);
                ratingSearch = true;
                ratingSearchButton.setText("reset");

                if(ratingSearchA == ratingSearchB) ratingSearchRangeLbl.setText("rated: " + ratingSearchA);
                else ratingSearchRangeLbl.setText(ratingSearchA + " to " + ratingSearchB);

                ratingSearchRangeLbl.setVisible(true);
                searchMovies();
            }
            case "reset" -> {
                ratingSearchRangeLbl.setVisible(false);
                ratingSearchSlider.setVisible(true);
                ratingSearch = false;
                ratingSearchButton.setText("from:");
                searchMovies();
            }
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
        if (pathTo != null || keyEvent.getCharacter() == "p"){
            File video = new File(pathTo);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(video);
        }

    }

    public void getSelectedMovie(MouseEvent mouseEvent) {
        selected = listMovie.getSelectionModel().getSelectedItem();
        double ratingOf = selected.getRating() * 0.5;
        ratingMovie.setRating(ratingOf);
        titleView.setText(selected.getTitle());
    }

    public void setRating(MouseEvent mouseEvent) {
        double rating = ratingMovie.getRating() * 2;
        double roudedRating = Math.round(rating * 10.0)/10.0;
        selected.setRating(roudedRating);
        movModel.changeMovieRating(selected);
        getAllMovies();
    }

    public void categorySearch() {
        searchedCategories.clear();
        searchedCategories.addAll(listCategory.getSelectionModel().getSelectedItems());
        for (Category c:searchedCategories
             ) {
            System.out.println(c.getName());
        }
    }

    private void searchMovies(){
        String query = typeField.getText();
        if(ratingSearch){
            if(ratingSearchA<=ratingSearchB){
                if(!searchedCategories.isEmpty()) listMovie.setItems(movModel.searchedMovies(query,searchedCategories,ratingSearchA,ratingSearchB));
                else listMovie.setItems(movModel.searchedMovies(query,ratingSearchA,ratingSearchB));
            }
            if(ratingSearchB<ratingSearchA){
                if(!searchedCategories.isEmpty()) listMovie.setItems(movModel.searchedMovies(query,searchedCategories,ratingSearchB,ratingSearchA));
                else listMovie.setItems(movModel.searchedMovies(query,ratingSearchB,ratingSearchA));
            }
        }
        else if(!searchedCategories.isEmpty()) listMovie.setItems(movModel.searchedMovies(query,searchedCategories));
        else listMovie.setItems(movModel.searchedMovies(query));
    }
}

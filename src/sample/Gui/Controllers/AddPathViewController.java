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
import sample.Be.CatMovie;
import sample.Be.Category;
import sample.Be.Movie;
import sample.Gui.Models.CategoryModel;
import sample.Gui.Models.CategoryMovieModel;
import sample.Gui.Models.MovieModel;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
        LocalDate date = java.time.LocalDate.now();
        Movie newMovie = new Movie(1,title,raiting,moviePath,date);
        System.out.println(newMovie.toString());
        mModel = new MovieModel();
        mModel.addMovie(newMovie);

        List<Category> listCat = addedCategories.getItems();
        addMovieCat(title, listCat);
    }

    public void addMovieCat(String title,List<Category> listCat ) {
        List<CatMovie> listOfCategoriesAndMovies = new ArrayList<>();
        for(Category c: listCat){
            listOfCategoriesAndMovies.add(new CatMovie(c.getID(), title));
        }
        CategoryMovieModel catMovModel = new CategoryMovieModel();
        catMovModel.addMovieCat(listOfCategoriesAndMovies);
    }

    private File randomFile;

    public void findFile(ActionEvent actionEvent) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("OpenFileDialog");
        Stage stage = (Stage) anchorid.getScene().getWindow();
        File fl = fileChooser.showOpenDialog(stage);
        randomFile = fl;



        if (fl != null){
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

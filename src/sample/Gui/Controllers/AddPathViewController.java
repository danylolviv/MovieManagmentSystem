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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import sample.Be.CatMovie;
import sample.Be.Category;
import sample.Be.Movie;
import sample.Exceptions.AddMovieException;
import sample.Gui.Models.CategoryModel;
import sample.Gui.Models.CategoryMovieModel;
import sample.Gui.Models.MovieModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AddPathViewController implements Initializable {

    @FXML
    private Label movieLblExists;
    private File randomFile;
    public Rating ratingMovie;

    @FXML
    private ListView addedCategories;
    @FXML
    public ChoiceBox<Category> categoriesList;
    @FXML
    private AnchorPane anchorid;
    @FXML
    private TextField pathToMovie;
    @FXML
    private TextField movieTitleField;
    @FXML
    private Button close;

    private MovieModel mModel;
    private CategoryModel catModel;
    private CategoryMovieModel catMovModel;

    private List<Category> categories;
    private ObservableList cat = FXCollections.observableArrayList();
    private Double ratingStars;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("initialize");
        movieLblExists.setVisible(false);
    }

   /* public void setCategories(List<Category> cat) {
        System.out.println("called");
        this.categories = cat;
        categoriesList.getItems().addAll(categories);
    }*/

    public void setCategories(CategoryModel categoryModel) {
        System.out.println("called");
        this.catModel = categoryModel;
        categoriesList.getItems().addAll(categoryModel.getAllCategories());
        addedCategories.setItems(categoryModel.getAddMovieCategories());
    }

    public void setCatMovModel(CategoryMovieModel catMovModel) {
        this.catMovModel = catMovModel;
    }

    public void setmModel(MovieModel mModel){
        this.mModel = mModel;
    }

    public void addMovie() throws AddMovieException {
        String title = movieTitleField.getText();

        for(Movie m: mModel.getMovies()){
            if(m.getTitle().toLowerCase().equals(title.toLowerCase())){
                movieLblExists.setVisible(true);
            }else {



            }
        }

        String moviePath = pathToMovie.getText();
        double raiting = ratingStars;
        LocalDate date = java.time.LocalDate.now();
        Movie newMovie = new Movie(1,title,raiting,moviePath,date);
        mModel.addMovie(newMovie);
        mModel.updateMovieList();
        ObservableList listCat = addedCategories.getItems();
        for (Movie m:mModel.getMovies()) {
            if (m.getTitle().equals(title)){
                addMovieCat(m.getId(), listCat);
            }
        }
        catMovModel.updateCatMovieList();
        closeWindow();
    }

    public void addMovieCat(int id,List<Category> listCat ) {
        List<CatMovie> listOfCategoriesAndMovies = new ArrayList<>();
        for(Category c: listCat){
            listOfCategoriesAndMovies.add(new CatMovie(c.getID(), id));
        }
        catMovModel.addMovieCat(listOfCategoriesAndMovies);
    }



    public void findFile(ActionEvent actionEvent) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src"));
        fileChooser.setTitle("OpenFileDialog");
        Stage stage = (Stage) anchorid.getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Video", "*.mp4"),
                new FileChooser.ExtensionFilter("Video", "*.mpeg4")
        );
        File fl = fileChooser.showOpenDialog(stage);

        randomFile = fl;

        if (fl != null){
            pathToMovie.setText(fl.getPath());
        }

    }

    public void openIt(ActionEvent actionEvent) throws AddMovieException {
        addMovie();

    }

    public void addCategory(ActionEvent actionEvent) {
        cat.add(categoriesList.getSelectionModel().getSelectedItem());
        addedCategories.setItems(cat);
    }

    public void deleteCategory(ActionEvent actionEvent) {
        addedCategories.getItems().remove(addedCategories.getSelectionModel().getSelectedItem());
    }

    public void closeWindow() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    public void setRating(MouseEvent mouseEvent) {
        double rating = ratingMovie.getRating() * 2;
        double roudedRating = Math.round(rating * 10.0)/10.0;
        ratingStars = roudedRating;
    }

    public void addNewCategoryWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Gui/Views/AddCategoryView.fxml"));
        Parent root = loader.load();
        ((AddCategoryController)loader.getController()).setCategories(catModel);
        Stage newCategoryStage = new Stage();
        newCategoryStage.setTitle("Add Category");
        newCategoryStage.setScene(new Scene(root));
        newCategoryStage.setResizable(false);
        newCategoryStage.show();
    }


}

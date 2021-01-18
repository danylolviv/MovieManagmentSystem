package sample.Gui.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import sample.Be.Category;
import sample.Be.Movie;
import sample.Exceptions.DeleteMovieException;
import sample.Gui.Models.CategoryModel;
import sample.Gui.Models.CategoryMovieModel;
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
    public Button ratingSearchButton;
    public Slider ratingSearchSlider;
    Double ratingSearchA;
    Double ratingSearchB;
    boolean ratingSearch;
    List<Category> searchedCategories = new ArrayList<Category>();
    MovieModel movModel;
    CategoryModel categoryModel;
    CategoryMovieModel catMovieModel;
    private final ObservableList<String> sortTypes = FXCollections.observableArrayList("title", "rating");
    private ObservableList<Category> categories;
    private ObservableList<Movie> movies;
    @FXML
    private Button sortButton;
    @FXML
    private ChoiceBox<String> sortChoiceBox;
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
        categoryModel = new CategoryModel();
        catMovieModel = new CategoryMovieModel();

        getAllCategories();
        listMovie.setItems(movModel.getMovies());
        listCategory.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        sortChoiceBox.setItems(sortTypes);

        typeField.textProperty().addListener((observableValue, s, t1) -> {
            searchMovies();
        });
        try {
            openWarning();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAllCategories() {
        listCategory.setItems(categoryModel.getAllCategories());
    }

    public void getAllMovies() {
        listMovie.setItems(movModel.getMovies());
    }

    public void removeMovie(int movieId) throws DeleteMovieException {
        movModel.deleteMovie(movieId);
    }

    public void removeCategory(int categoryId) {
        categoryModel.deleteCategory(categoryId);
    }

    @FXML
    private void openWindow(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/Gui/Views/Library.fxml"));
        Stage LibraryStage = new Stage();
        LibraryStage.setTitle("Library");
        LibraryStage.setScene(new Scene(root));
        LibraryStage.setResizable(false);
        LibraryStage.show();
    }


    public void openCategories(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/Gui/Views/CategoriesView.fxml"));
        //((AddPathViewController)loader.getController()).setModel(categoryModel);
        Stage CategoriesStage = new Stage();
        CategoriesStage.setTitle("Categories");
        CategoriesStage.setScene(new Scene(root));
        CategoriesStage.setResizable(false);
        CategoriesStage.show();
    }

    private void openWarning() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/Gui/Views/Warning.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Warning");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        stage.setAlwaysOnTop(true);
    }

    public void addPath(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Gui/Views/AddPathView.fxml"));
        Parent root = loader.load();
        ((AddPathViewController) loader.getController()).setCategories(categoryModel);
        ((AddPathViewController) loader.getController()).setmModel(movModel);
        ((AddPathViewController) loader.getController()).setCatMovModel(catMovieModel);
        Stage MovieSettingsStage = new Stage();
        MovieSettingsStage.setTitle("Movie Settings");
        MovieSettingsStage.setScene(new Scene(root));
        MovieSettingsStage.setResizable(false);
        MovieSettingsStage.show();
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

                if (ratingSearchA == ratingSearchB) ratingSearchRangeLbl.setText("rated: " + ratingSearchA);
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

    public void removeMovie(ActionEvent actionEvent) throws DeleteMovieException {
        if (listMovie.getSelectionModel().getSelectedItems() != null) {
            removeMovie(listMovie.getSelectionModel().getSelectedItem().getId());
            listMovie.getItems().remove(listMovie.getSelectionModel().getSelectedItem());
        }
    }

    public void removeCategory(ActionEvent actionEvent) throws DeleteMovieException {
        if (listCategory.getSelectionModel().getSelectedItems() != null) {
            removeMovie(listCategory.getSelectionModel().getSelectedItem().getID());
            listCategory.getItems().remove(listCategory.getSelectionModel().getSelectedItem());
        }
    }


    public void playMovie(KeyEvent keyEvent) throws IOException {
        String pathTo = listMovie.getSelectionModel().getSelectedItem().getPath();
        if (pathTo != null || keyEvent.getCharacter() == "p") {
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
        double roudedRating = Math.round(rating * 10.0) / 10.0;
        selected.setRating(roudedRating);
        movModel.changeMovieRating(selected);
        getAllMovies();
    }

    public void categorySearch() {
        searchedCategories.clear();
        searchedCategories.addAll(listCategory.getSelectionModel().getSelectedItems());
        for (Category c : searchedCategories
        ) {
            System.out.println(c.getName());
        }
        searchMovies();
    }

    private void searchMovies() {
        String query = typeField.getText();
        if (ratingSearch) {
            if (ratingSearchA <= ratingSearchB) {
                if (!searchedCategories.isEmpty())
                    listMovie.setItems(movModel.searchedMovies(query, searchedCategories, catMovieModel.getCatMovies(), ratingSearchA, ratingSearchB));
                else listMovie.setItems(movModel.searchedMovies(query, ratingSearchA, ratingSearchB));
            }
            if (ratingSearchB < ratingSearchA) {
                if (!searchedCategories.isEmpty())
                    listMovie.setItems(movModel.searchedMovies(query, searchedCategories, catMovieModel.getCatMovies(), ratingSearchB, ratingSearchA));
                else listMovie.setItems(movModel.searchedMovies(query, ratingSearchB, ratingSearchA));
            }
        } else if (!searchedCategories.isEmpty())
            listMovie.setItems(movModel.searchedMovies(query, searchedCategories, catMovieModel.getCatMovies()));
        else listMovie.setItems(movModel.searchedMovies(query));
    }

    public void sortMovies() {
        if (sortChoiceBox.getSelectionModel().getSelectedItem() != null)
            listMovie.setItems(movModel.sortMovies(listMovie.getItems(), sortChoiceBox.getSelectionModel().getSelectedItem().toLowerCase()));
    }

    public void refresh(ActionEvent actionEvent) {
        getAllCategories();
        movModel.updateMovieList();
    }
}

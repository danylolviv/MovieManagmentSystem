package sample.Gui.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Be.CatMovie;
import sample.Bll.CatMovieManager;

import java.util.List;

public class CategoryMovieModel {
    CatMovieManager catMovieManager;
    ObservableList<CatMovie> catMovies;

    public CategoryMovieModel() {
        catMovieManager = new CatMovieManager();
        catMovies = FXCollections.observableArrayList();
        updateCatMovieList();
    }

    public void addMovieCat(List catMovie) {
        catMovieManager.addCategoryMovie(catMovie);
    }

    public void updateCatMovieList() {
        catMovies.setAll(catMovieManager.getAllCatMovies());
    }

    public ObservableList<CatMovie> getCatMovies() {
        return catMovies;
    }
}

package sample.Gui.Models;

import sample.Bll.CatMovieManager;

import java.util.List;

public class CategoryMovieModel {

    private CatMovieManager catMovieManager;

    public void addMovieCat(List catMovie){
        catMovieManager = new CatMovieManager();
        catMovieManager.addCategoryMovie(catMovie);
    }
}

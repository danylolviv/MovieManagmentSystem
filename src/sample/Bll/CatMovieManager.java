package sample.Bll;

import sample.Be.CatMovie;
import sample.Dal.DAOCategory_Movie;

import java.util.List;

public class CatMovieManager {
    DAOCategory_Movie catMovieDAO;

    public CatMovieManager(){
        catMovieDAO = new DAOCategory_Movie();
    }

    public void addCategoryMovie(List<CatMovie> list){
        catMovieDAO.addCategoryMovie(list);
    }

    public List<CatMovie> getAllCatMovies(){
        return catMovieDAO.getAllCatMovies();
    }
}

package sample.Bll;

import sample.Be.CatMovie;
import sample.Dal.DAOCategory_Movie;

import java.util.List;

public class CatMovieManager {
    DAOCategory_Movie catMovieDAO;

    public void addCategoryMovie(List<CatMovie> list){
        catMovieDAO = new DAOCategory_Movie();
        catMovieDAO.getLatestId();
    }

}

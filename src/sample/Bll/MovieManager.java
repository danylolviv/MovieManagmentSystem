package sample.Bll;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Be.Movie;
import sample.Dal.DAOMovie;

import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    private final DAOMovie daoMovie;

    public MovieManager(){
        daoMovie = new DAOMovie();
    }

    public List<Movie> getAllMovies(){
        return daoMovie.getAllMovies();
    }

    public void addMovie(Movie movie){

    }

    public ObservableList<Movie> searchMovies(ObservableList<Movie> queriedMovies, String searchQuery) {
        ObservableList<Movie> foundMovies;
        foundMovies = FXCollections.observableArrayList();
        for(Movie m: queriedMovies){
            String title = m.getTitle().toLowerCase();
            String query = searchQuery.toLowerCase();
            if (title.contains(query)){
                foundMovies.add(m);
            }
        }
        return foundMovies;
    }

}



package sample.Bll;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Be.Category;
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
        daoMovie.addMovie(movie);
    }

    public void deleteMovie(int movieID){
        daoMovie.deleteMovie(movieID);
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

    public ObservableList<Movie> searchMovies(ObservableList<Movie> queriedMovies, String searchQuery, Double minRating, Double maxRating) {
        ObservableList<Movie> foundMovies;
        foundMovies = FXCollections.observableArrayList();
        for(Movie m: queriedMovies){
            String title = m.getTitle().toLowerCase();
            Double rating = m.getRating();
            String query = searchQuery.toLowerCase();
            if (title.contains(query) && rating>=minRating && rating<=maxRating){
                foundMovies.add(m);
            }
        }
        return foundMovies;
    }

    public ObservableList<Movie> searchMovies(ObservableList<Movie> queriedMovies, String searchQuery, List<Category> searchedCategories) {
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

    public ObservableList<Movie> searchMovies(ObservableList<Movie> queriedMovies, String searchQuery, List<Category> searchedCategories, Double minRating, Double maxRating) {
        ObservableList<Movie> foundMovies;
        foundMovies = FXCollections.observableArrayList();
        for(Movie m: queriedMovies){
            String title = m.getTitle().toLowerCase();
            Double rating = m.getRating();
            String query = searchQuery.toLowerCase();
            if (title.contains(query) && rating>=minRating && rating<=maxRating){
                foundMovies.add(m);
            }
        }
        return foundMovies;
    }
}



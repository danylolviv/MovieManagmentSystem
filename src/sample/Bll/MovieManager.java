package sample.Bll;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Be.Category;
import sample.Be.Movie;
import sample.Dal.DAOMovie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MovieManager {
    public final int SORT_NAME = 100;
    public final int SORT_RATING = 101;

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

    public void changeMovieRating(Movie movie){
        daoMovie.changeMovieRating(movie);
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

    public ObservableList<Movie> sortMovies(ObservableList<Movie> movies,int sortParameter){
        ArrayList<String> titles = new ArrayList<String>();
        ArrayList<Double> ratings = new ArrayList<Double>();

        if(sortParameter == SORT_NAME) {
            for (Movie movie : movies) {
                titles.add(movie.getTitle().toLowerCase());
            }
            titles = titles.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
            for (String title : titles) {
                for (Movie movie : movies) {
                    if (movie.getTitle().toLowerCase().equals(title)) {
                        Collections.swap(movies, movies.indexOf(movie), titles.indexOf(title));
                    }
                }
            }
        }
        if(sortParameter == SORT_RATING) {
            for (Movie movie : movies) {
                ratings.add(movie.getRating());
            }
            ratings = ratings.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
            for (Double rating : ratings) {
                for (Movie movie : movies) {
                    if (movie.getRating().equals(rating)) {
                        Collections.swap(movies, movies.indexOf(movie), ratings.indexOf(rating));
                    }
                }
            }
        }
        return movies;
    }
}



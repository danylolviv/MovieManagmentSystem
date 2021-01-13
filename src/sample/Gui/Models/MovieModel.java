package sample.Gui.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Be.Movie;
import sample.Bll.MovieManager;

import java.time.LocalDate;
import java.util.List;

public class MovieModel {
    MovieManager movieManager;
    private ObservableList<Movie> movies;

    public MovieModel(){
        movieManager = new MovieManager();
        movies = FXCollections.observableArrayList();
        updateMovieList();
    }

    public ObservableList<Movie> getMovies() {
        return movies;
    }

    public void updateMovieList(){
        movies.setAll(movieManager.getAllMovies());
    }

    public void addMovie(Movie movie){
        movieManager.addMovie(movie);
    }

    public void deleteMovie(int movieId){
        movieManager.deleteMovie(movieId);
    }

    public void changeMovieRating(Movie movie){
        movieManager.changeMovieRating(movie);
    }

    public ObservableList<Movie> searchedMovies(String searchQuery){
        return movieManager.searchMovies(movies,searchQuery);
    }

    public ObservableList<Movie> searchedMovies(String searchQuery, Double minRating, Double maxRating) {
        return movieManager.searchMovies(movies,searchQuery,minRating,maxRating);
    }
}

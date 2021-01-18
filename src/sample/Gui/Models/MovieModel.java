package sample.Gui.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Be.CatMovie;
import sample.Be.Category;
import sample.Be.Movie;
import sample.Bll.MovieManager;
import sample.Exceptions.AddMovieException;
import sample.Exceptions.DeleteMovieException;

import java.util.List;

public class MovieModel {
    MovieManager movieManager;
    private final ObservableList<Movie> movies;

    public MovieModel() {
        movieManager = new MovieManager();
        movies = FXCollections.observableArrayList();
        movies.setAll(movieManager.getAllMovies());
    }

    public ObservableList<Movie> getMovies() {
        return movies;
    }

    public void updateMovieList() {
        movies.setAll(movieManager.getAllMovies());
    }

    public void addMovie(Movie movie) throws AddMovieException {
        movieManager.addMovie(movie);
        System.out.println("before updating ");
        updateMovieList();
        System.out.println("after updating ");
    }

    public void deleteMovie(int movieId) throws DeleteMovieException {
        movieManager.deleteMovie(movieId);
    }

    public void changeMovieRating(Movie movie) {
        movieManager.changeMovieRating(movie);
    }

    public ObservableList<Movie> sortMovies(ObservableList<Movie> listMovies, String sortParameter) {
        if (sortParameter.equals("title")) {
            return movieManager.sortMovies(movies, movieManager.SORT_NAME);
        } else if (sortParameter.equals("rating")) {
            return movieManager.sortMovies(movies, movieManager.SORT_RATING);
        }
        return null;
    }

    public ObservableList<Movie> searchedMovies(String searchQuery) {
        return movieManager.searchMovies(movies, searchQuery);
    }

    public ObservableList<Movie> searchedMovies(String searchQuery, Double minRating, Double maxRating) {
        return movieManager.searchMovies(movies, searchQuery, minRating, maxRating);
    }

    public ObservableList<Movie> searchedMovies(String searchQuery, List<Category> searchedCategories, List<CatMovie> catMovies) {
        return movieManager.searchMovies(movies, searchQuery, searchedCategories, catMovies);
    }

    public ObservableList<Movie> searchedMovies(String searchQuery, List<Category> searchedCategories, List<CatMovie> catMovies, Double minRating, Double maxRating) {
        return movieManager.searchMovies(movies, searchQuery, searchedCategories, catMovies, minRating, maxRating);
    }
}

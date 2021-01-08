package sample.Gui.Models;

import sample.Be.Movie;
import sample.Bll.MovieManager;

import java.time.LocalDate;

public class MovieModel {

    public MovieManager movieManager;

    MovieModel(){

    }

    public void addMovie(String title, String year, LocalDate releaseDate, String Description, String moviePath){
        //movieManager.addMovie(new Movie(1, title, ));
    }
}

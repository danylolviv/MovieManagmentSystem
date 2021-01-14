package sample.Dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import sample.Be.Movie;
import sample.Bll.MovieManager;


public class DAOMovie {

    private static DataAccess dataAccess;

    public DAOMovie(){
        dataAccess= new DataAccess();
    }

    public List<Movie> getAllMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT * FROM Movie_Data;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("movie_id");
                String title = rs.getString("title");
                Double rating = rs.getDouble("rating");
                String pathTo = rs.getString("filelink");
                Movie movie = new Movie(id, title, rating, pathTo, null);
                movies.add(movie);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    public void addMovie(Movie movie) {

        try(Connection con = dataAccess.getConnection()){

            String sql = "INSERT INTO Movie_Data (title,rating,filelink,lastview) VALUES (?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getRating().toString());
            statement.setString(3, movie.getPath());
            statement.setDate(4, Date.valueOf(java.time.LocalDate.now()));
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //DELETE FROM Movie_Data WHERE movie_id=48;
    public void deleteMovie(int movieId){
        System.out.println(movieId);
        /*deleteFromCatMovie(movieId);*/
        try(Connection con = dataAccess.getConnection()){

            String sql1 = "DELETE FROM CatMovie WHERE movie_id = ?";
            PreparedStatement statement1 = con.prepareStatement(sql1);
            statement1.setInt(1,movieId);
            statement1.executeUpdate();

            String sql = "DELETE FROM Movie_Data WHERE movie_id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1,movieId);
        statement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /*private void deleteFromCatMovie(int movieId) {
        try(Connection con = dataAccess.getConnection()){
            String sql = "DELETE FROM CatMovie WHERE movie_id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,movieId);
            statement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }*/

    public void changeMovieRating(Movie movie){
        System.out.println(movie.getTitle() + " rating is set " + movie.getRating());
        String sql = "UPDATE Movie_Data SET rating = ? WHERE movie_id = ?";

        try(Connection con = dataAccess.getConnection()){
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setDouble(1, movie.getRating());
            statement.setInt(2, movie.getId());
            statement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}

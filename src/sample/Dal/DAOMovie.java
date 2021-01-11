package sample.Dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import sample.Be.Movie;


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
                Movie movie = new Movie(id, title, rating, null, null);
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
}

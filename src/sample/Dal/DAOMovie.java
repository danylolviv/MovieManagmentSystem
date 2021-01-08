package sample.Dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                Movie movie = new Movie(id, title, rating);
                movies.add(movie);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }
}

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
                Movie movie = new Movie(id, title, rating, null);
                movies.add(movie);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    /*public void addMovie(Movie movie) {
        try(Connection con = dataAccess.getConnection()){
            String sql = "INSERT INTO Songs (title,artistId,genreId,songUrl) VALUES (?,(SELECT id FROM Artist WHERE artist=?),(SELECT id FROM Genres WHERE genre=?),?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, song.getTitle());
            statement.setString(2, song.getArtist());
            statement.setString(3, song.getGenre());
            statement.setString(4, song.getUriString());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }*/
}

package sample.Dal;

import sample.Be.CatMovie;
import sample.Be.Movie;

import java.sql.*;
import java.util.List;

public class DAOCategory_Movie {

    private static DataAccess dataAccess;


    public void addCategoryMovie(List<CatMovie> list) {

        int id = getLatestId();

        try (Connection con = dataAccess.getConnection()) {

            String sql = "INSERT INTO CatMovie (category_id, movie_id) VALUES (?,?)";
            PreparedStatement statement = con.prepareStatement(sql);

            for (CatMovie cM : list) {
                statement.setInt(1, cM.getId());
                statement.setInt(2, id);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public int getLatestId() {

        dataAccess = new DataAccess();

        int lastMovieId = 0;

        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT max(movie_id) FROM Movie_Data;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            lastMovieId = rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return lastMovieId;
    }
}
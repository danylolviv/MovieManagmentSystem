package sample.Dal;

import sample.Be.CatMovie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOCategory_Movie {

    private static final DataAccess dataAccess = new DataAccess();

    public List<CatMovie> getAllCatMovies() {

        ArrayList<CatMovie> catMovies = new ArrayList<>();
        try (Connection con = dataAccess.getConnection()) {

            String sql = "SELECT * FROM CatMovie;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int idCat = rs.getInt("category_id");
                int idMov = rs.getInt("movie_id");
                CatMovie catMovie = new CatMovie(idCat, idMov);
                catMovies.add(catMovie);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return catMovies;
    }

    public void addCategoryMovie(List<CatMovie> list) {

        for (CatMovie ct : list) {
            System.out.println("Category id: " + ct.getCatId() + "  " + "Movie id: " + ct.getMovId());
        }
        try (Connection con = dataAccess.getConnection()) {

            String sql = "INSERT INTO CatMovie (category_id, movie_id) VALUES (?,?)";
            PreparedStatement statement = con.prepareStatement(sql);

            for (CatMovie cM : list) {
                statement.setInt(1, cM.getCatId());
                statement.setInt(2, cM.getMovId());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public int getLatestId() {

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
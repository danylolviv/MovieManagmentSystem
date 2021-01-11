package sample.Dal;

import sample.Be.CatMovie;
import sample.Be.Movie;

import java.sql.*;
import java.util.List;

public class DAOCategory_Movie {

    private static DataAccess dataAccess;


    public void addCategoryMovie(List<CatMovie> list) {

        getLatestId();

        try (Connection con = dataAccess.getConnection()) {

            String sql = "INSERT INTO CatMovie (category_id, movie_id) VALUES (?,?)";
            PreparedStatement statement = con.prepareStatement(sql);

            for (CatMovie cM : list) {
                statement.setInt(1, cM.getId());
                //statement.setInt();
            }

           /* statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getRating().toString());
            statement.setString(3, movie.getPath());
            statement.setDate(4, Date.valueOf(java.time.LocalDate.now()));
            statement.executeUpdate();*/

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void getLatestId() {

        dataAccess = new DataAccess();

        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT max(id) FROM table;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            System.out.println(rs);


        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }
}
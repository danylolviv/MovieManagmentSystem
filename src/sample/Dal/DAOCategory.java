package sample.Dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sample.Be.Category;


public class DAOCategory {

    private static DataAccess dataAccess;

    public DAOCategory(){
        dataAccess= new DataAccess();
    }

    public List<Category> getAllCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT * FROM Category;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("category_id");
                String name = rs.getString("category_name");
                Category genre = new Category(id, name);
                categories.add(genre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categories;
    }
}

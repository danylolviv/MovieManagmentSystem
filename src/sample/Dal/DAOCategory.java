package sample.Dal;

import java.sql.*;
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

    public Category addNewCategory(String catName) {
        try (Connection con = dataAccess.getConnection()) {
            String sql = "INSERT INTO Category (category_name) VALUES (?) ";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, catName);
            statement.executeUpdate();

            String sql1 = "SELECT max(category_id) FROM Category;";
            Statement statement1 = con.createStatement();
            ResultSet rs = statement1.executeQuery(sql1);
            rs.next();
            int lastCategoryId;
            lastCategoryId = rs.getInt(1);
            Category addedCategory =  new Category(lastCategoryId, catName);
            System.out.println("category object " + addedCategory);
            return addedCategory;
        }catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }
}

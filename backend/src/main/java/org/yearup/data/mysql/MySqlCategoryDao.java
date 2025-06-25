package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);

    }

    @Override
    public List<Category> getAllCategories() {
       List<Category> categories = new ArrayList<>();
       String query = "SELECT * FROM categories";
       try(Connection connection = getConnection();
           PreparedStatement statement = connection.prepareStatement(query);
           ResultSet resultSet = statement.executeQuery())
       {
           while(resultSet.next()) {
               Category category = new Category();
               category.setCategoryId(resultSet.getInt("category_id"));
               category.setName(resultSet.getString("name"));
               category.setDescription(resultSet.getString("description"));
               categories.add(category);
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return categories;
    }

    @Override
    public Category getById(int categoryId) {
        String query = "SELECT * FROM categories WHERE category_id = ?";
        /*
        If a matching row is found
        it constructs and returns a Category object, otherwise it returns null.
        Create an instance of category
        If there’s a category row that matches the ID...
        create a Category object and fill it with data from that row.
         */
        Category category = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                category = new Category();
                category.setCategoryId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public Category create(Category category) {
        //Prepares an INSERT statement with name and description values.
        String query = "INSERT INTO categories (name, description) VALUES (?, ?)";
        try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            int rowsCreated = statement.executeUpdate();

            if(rowsCreated > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    int newId = generatedKeys.getInt(1);
                    category.setCategoryId(newId);
                }
                generatedKeys.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public void update(int categoryId, Category category)
    {
        // update category
    }

    @Override
    public void delete(int categoryId)
    {
        // delete category
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}

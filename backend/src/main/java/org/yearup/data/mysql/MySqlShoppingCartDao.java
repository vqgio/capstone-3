package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {
    public MySqlShoppingCartDao(DataSource dataSource) {
        super (dataSource);
    }
    @Override
    public ShoppingCart getByUserId(int userId) {
        ShoppingCart cart = new ShoppingCart();
        String query = """
                SELECT sc.product_id,
                                    p.product_id, p.name, p.price, p.category_id,
                                    p.description, p.color, p.stock, p.featured, p.image_url
                             FROM shopping_cart sc
                             JOIN products p ON sc.product_id = p.product_id
                             WHERE sc.user_id = ?
                """;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Product product = mapRow(resultSet);
                int quantity = resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cart;
    }

    @Override
    public void addOrIncrement(int userId, int productId) {

    }

    @Override
    public void updateQuantity(int userId, int productId, int qty) {

    }

    @Override
    public boolean itemExists(int userId, int productId) {
        return false;
    }

    @Override
    public void clearCart(int userId) {

    }

    private Product mapRow(ResultSet row) throws SQLException
    {
        return MySqlProductDao.mapRow(row);
    }

}

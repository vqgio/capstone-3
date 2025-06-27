package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    // add additional method signatures here
    void addOrIncrement(int userId, int productId);
    void updateQuantity(int userId, int productId, int qty);
    boolean itemExists(int userId, int productId);
    void clearCart(int userId);
}

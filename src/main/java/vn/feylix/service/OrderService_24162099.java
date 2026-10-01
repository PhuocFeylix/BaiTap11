package vn.feylix.service;

import vn.feylix.cart.CartItem_24162099;
import vn.feylix.entity.*;
import java.util.List;

public interface OrderService_24162099 {
    Order_24162099 checkout(User_24162099 user, List<CartItem_24162099> cart,
                            String recipientName, String phone, String address);
    List<Order_24162099> findByUser(Long userId, OrderStatus_24162099 status);
    Order_24162099 findByIdAndUser(Long orderId, Long userId);
}

package vn.feylix.repository;

import vn.feylix.entity.*;
import java.util.List;

public interface OrderRepository_24162099 {
    void save(Order_24162099 order);
    List<Order_24162099> findByUser(Long userId, OrderStatus_24162099 status);
    Order_24162099 findByIdAndUser(Long orderId, Long userId);
}

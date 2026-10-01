package vn.feylix.service;

import jakarta.persistence.*;
import vn.feylix.cart.CartItem_24162099;
import vn.feylix.entity.*;
import vn.feylix.repository.*;
import vn.feylix.util.JpaUtils_24162099;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderServiceImpl_24162099 implements OrderService_24162099 {
    private final OrderRepository_24162099 repo = new OrderRepositoryImpl_24162099();

    public Order_24162099 checkout(User_24162099 user, List<CartItem_24162099> cart,
                                   String recipientName, String phone, String address) {
        if (cart == null || cart.isEmpty()) throw new IllegalArgumentException("Giỏ hàng đang trống.");
        if (recipientName == null || recipientName.isBlank() || phone == null || phone.isBlank()
                || address == null || address.isBlank()) throw new IllegalArgumentException("Vui lòng nhập đủ thông tin giao hàng.");

        EntityManager em = JpaUtils_24162099.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Order_24162099 order = new Order_24162099();
            order.setUser(em.getReference(User_24162099.class, user.getId()));
            order.setOrderDate(LocalDateTime.now());
            order.setStatus(OrderStatus_24162099.NEW);
            order.setPaymentMethod("COD");
            order.setRecipientName(recipientName.trim());
            order.setPhone(phone.trim());
            order.setShippingAddress(address.trim());

            BigDecimal total = BigDecimal.ZERO;
            for (CartItem_24162099 cartItem : cart) {
                if (cartItem.getQuantity() <= 0) throw new IllegalArgumentException("Số lượng không hợp lệ.");
                Book_24162099 book = em.find(Book_24162099.class, cartItem.getBookId(), LockModeType.PESSIMISTIC_WRITE);
                if (book == null) throw new IllegalArgumentException("Sản phẩm không còn tồn tại: " + cartItem.getTitle());
                if (cartItem.getQuantity() > book.getQuantity()) {
                    throw new IllegalArgumentException("Sách '" + book.getTitle() + "' chỉ còn " + book.getQuantity() + " sản phẩm.");
                }
                book.setQuantity(book.getQuantity() - cartItem.getQuantity());
                OrderItem_24162099 item = new OrderItem_24162099();
                item.setBook(book);
                item.setQuantity(cartItem.getQuantity());
                item.setPrice(book.getPrice());
                order.addItem(item);
                total = total.add(item.getSubtotal());
            }
            order.setTotalAmount(total);
            em.persist(order);
            tx.commit();
            return order;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally { em.close(); }
    }

    public List<Order_24162099> findByUser(Long userId, OrderStatus_24162099 status) {
        return repo.findByUser(userId, status);
    }

    public Order_24162099 findByIdAndUser(Long orderId, Long userId) {
        return repo.findByIdAndUser(orderId, userId);
    }
}

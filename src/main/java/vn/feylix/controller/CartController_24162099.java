package vn.feylix.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.feylix.cart.CartItem_24162099;
import vn.feylix.entity.Book_24162099;
import vn.feylix.entity.User_24162099;
import vn.feylix.service.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/cart", "/cart/add", "/cart/update", "/cart/remove", "/cart/clear"})
public class CartController_24162099 extends HttpServlet {
    private final BookService_24162099 books = new BookServiceImpl_24162099();

    @SuppressWarnings("unchecked")
    private List<CartItem_24162099> cart(HttpSession session, User_24162099 user) {
        Object owner = session.getAttribute("cartOwnerId");

        // Mỗi tài khoản có giỏ hàng riêng trong session.
        // Nếu session đang thuộc tài khoản khác thì tạo giỏ mới.
        if (owner == null || !user.getId().equals(owner)) {
            List<CartItem_24162099> newCart = new ArrayList<>();
            session.setAttribute("cart", newCart);
            session.setAttribute("cartOwnerId", user.getId());
            return newCart;
        }

        Object value = session.getAttribute("cart");
        if (value instanceof List<?>) {
            return (List<CartItem_24162099>) value;
        }

        List<CartItem_24162099> newCart = new ArrayList<>();
        session.setAttribute("cart", newCart);
        return newCart;
    }

    private User_24162099 account(HttpServletRequest req) {
        Object value = req.getSession(false) == null
                ? null
                : req.getSession(false).getAttribute("account");

        return value instanceof User_24162099
                ? (User_24162099) value
                : null;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User_24162099 user = account(req);

        // Chưa đăng nhập thì không được truy cập giỏ hàng.
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setAttribute("cartItems", cart(req.getSession(), user));
        req.getRequestDispatcher("/views/cart.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");

        User_24162099 user = account(req);

        // Bắt buộc đăng nhập mới được thêm/sửa/xóa giỏ hàng.
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String path = req.getServletPath();
        HttpSession session = req.getSession();
        List<CartItem_24162099> cart = cart(session, user);

        try {
            if ("/cart/add".equals(path)) {
                Long id = Long.valueOf(req.getParameter("bookId"));
                int qty = parsePositive(req.getParameter("quantity"), 1);

                Book_24162099 book = books.findById(id);

                if (book == null || book.getQuantity() <= 0) {
                    throw new IllegalArgumentException("Sản phẩm đã hết hàng.");
                }

                CartItem_24162099 found = null;

                for (CartItem_24162099 item : cart) {
                    if (item.getBookId().equals(id)) {
                        found = item;
                        break;
                    }
                }

                int newQty = qty + (found == null ? 0 : found.getQuantity());

                if (newQty > book.getQuantity()) {
                    throw new IllegalArgumentException(
                            "Chỉ có thể thêm tối đa " + book.getQuantity() + " sản phẩm.");
                }

                if (found == null) {
                    cart.add(new CartItem_24162099(book, newQty));
                } else {
                    found.setQuantity(newQty);
                    found.setStock(book.getQuantity());
                }

                session.setAttribute(
                        "cartMessage",
                        "Đã thêm sản phẩm vào giỏ hàng.");

                resp.sendRedirect(req.getContextPath() + "/cart");
                return;
            }

            if ("/cart/update".equals(path)) {
                for (CartItem_24162099 item : cart) {
                    String raw =
                            req.getParameter("quantity_" + item.getBookId());

                    if (raw == null) {
                        continue;
                    }

                    int qty = Integer.parseInt(raw);

                    Book_24162099 book =
                            books.findById(item.getBookId());

                    if (book == null) {
                        throw new IllegalArgumentException(
                                "Sản phẩm không tồn tại.");
                    }

                    if (qty < 1 || qty > book.getQuantity()) {
                        throw new IllegalArgumentException(
                                "Số lượng của '" + book.getTitle()
                                + "' phải từ 1 đến "
                                + book.getQuantity() + ".");
                    }

                    item.setQuantity(qty);
                    item.setStock(book.getQuantity());
                }

                session.setAttribute(
                        "cartMessage",
                        "Đã cập nhật số lượng.");

            } else if ("/cart/remove".equals(path)) {

                Long id =
                        Long.valueOf(req.getParameter("bookId"));

                cart.removeIf(
                        x -> x.getBookId().equals(id));

                session.setAttribute(
                        "cartMessage",
                        "Đã xóa sản phẩm khỏi giỏ hàng.");

            } else if ("/cart/clear".equals(path)) {

                cart.clear();

                session.setAttribute(
                        "cartMessage",
                        "Đã xóa toàn bộ giỏ hàng.");
            }

        } catch (Exception e) {
            session.setAttribute("cartError", e.getMessage());
        }

        resp.sendRedirect(req.getContextPath() + "/cart");
    }

    private int parsePositive(String value, int fallback) {
        try {
            return Math.max(1, Integer.parseInt(value));
        } catch (Exception e) {
            return fallback;
        }
    }
}

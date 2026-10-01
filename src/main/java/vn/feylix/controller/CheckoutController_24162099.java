package vn.feylix.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.feylix.cart.CartItem_24162099;
import vn.feylix.entity.User_24162099;
import vn.feylix.entity.Order_24162099;
import vn.feylix.service.*;
import java.io.IOException;
import java.util.List;

@WebServlet({"/checkout", "/checkout/success"})
public class CheckoutController_24162099 extends HttpServlet {
    private final OrderService_24162099 orders = new OrderServiceImpl_24162099();

    @SuppressWarnings("unchecked")
    private List<CartItem_24162099> cart(HttpSession session) {
        Object value = session.getAttribute("cart");
        return value instanceof List<?> ? (List<CartItem_24162099>) value : java.util.Collections.emptyList();
    }

    private User_24162099 account(HttpServletRequest req) {
        Object o = req.getSession().getAttribute("account");
        return o instanceof User_24162099 u ? u : null;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User_24162099 user = account(req);
        if (user == null) { resp.sendRedirect(req.getContextPath() + "/login"); return; }
        List<CartItem_24162099> items = cart(req.getSession());
        if (items.isEmpty()) { resp.sendRedirect(req.getContextPath() + "/cart"); return; }
        req.setAttribute("cartItems", items);
        req.getRequestDispatcher("/views/checkout.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User_24162099 user = account(req);
        if (user == null) { resp.sendRedirect(req.getContextPath() + "/login"); return; }
        try {
            Order_24162099 order = orders.checkout(user, cart(req.getSession()), req.getParameter("recipientName"),
                    req.getParameter("phone"), req.getParameter("address"));
            req.getSession().removeAttribute("cart");
            req.getSession().setAttribute("lastOrderId", order.getId());
            req.getRequestDispatcher("/views/checkout-success.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("cartItems", cart(req.getSession()));
            req.getRequestDispatcher("/views/checkout.jsp").forward(req, resp);
        }
    }
}

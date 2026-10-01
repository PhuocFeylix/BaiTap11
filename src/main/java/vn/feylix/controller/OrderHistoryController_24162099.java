package vn.feylix.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.feylix.entity.*;
import vn.feylix.service.*;
import java.io.IOException;

@WebServlet({"/orders", "/order-detail"})
public class OrderHistoryController_24162099 extends HttpServlet {
    private final OrderService_24162099 orders = new OrderServiceImpl_24162099();

    private User_24162099 account(HttpServletRequest req) {
        Object o = req.getSession().getAttribute("account");
        return o instanceof User_24162099 u ? u : null;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User_24162099 user = account(req);
        if (user == null) { resp.sendRedirect(req.getContextPath() + "/login"); return; }
        if ("/order-detail".equals(req.getServletPath())) {
            try {
                Long id = Long.valueOf(req.getParameter("id"));
                Order_24162099 order = orders.findByIdAndUser(id, user.getId());
                if (order == null) { resp.sendRedirect(req.getContextPath() + "/orders"); return; }
                req.setAttribute("order", order);
                req.getRequestDispatcher("/views/order-detail.jsp").forward(req, resp);
            } catch (Exception e) { resp.sendRedirect(req.getContextPath() + "/orders"); }
            return;
        }
        OrderStatus_24162099 status = null;
        String raw = req.getParameter("status");
        if (raw != null && !raw.isBlank()) {
            try { status = OrderStatus_24162099.valueOf(raw); } catch (IllegalArgumentException ignored) {}
        }
        req.setAttribute("orders", orders.findByUser(user.getId(), status));
        req.setAttribute("statuses", OrderStatus_24162099.values());
        req.setAttribute("selectedStatus", status == null ? "" : status.name());
        req.getRequestDispatcher("/views/orders.jsp").forward(req, resp);
    }
}

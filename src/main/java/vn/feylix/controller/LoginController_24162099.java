package vn.feylix.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.feylix.entity.User_24162099;
import vn.feylix.service.*;
import vn.feylix.cart.CartItem_24162099;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/login")
public class LoginController_24162099 extends HttpServlet {
    private final UserService_24162099 service =
            new UserServiceImpl_24162099();

    protected void doGet(HttpServletRequest r, HttpServletResponse p)
            throws ServletException, IOException {
        r.getRequestDispatcher("/views/login.jsp").forward(r, p);
    }

    protected void doPost(HttpServletRequest r, HttpServletResponse p)
            throws ServletException, IOException {

        r.setCharacterEncoding("UTF-8");

        String e = r.getParameter("email");
        String pw = r.getParameter("password");

        User_24162099 u =
                (e == null || pw == null)
                ? null
                : service.login(e.trim(), pw);

        if (u == null) {
            r.setAttribute(
                    "error",
                    "Thông tin đăng nhập không đúng hoặc tài khoản chưa kích hoạt.");

            r.getRequestDispatcher("/views/login.jsp")
                    .forward(r, p);
            return;
        }

        HttpSession session = r.getSession();

        session.setAttribute("account", u);

        // Gắn giỏ hàng với tài khoản vừa đăng nhập.
        // Nếu trước đó là tài khoản khác thì không dùng lại giỏ cũ.
        session.setAttribute("cartOwnerId", u.getId());
        session.setAttribute("cart", new ArrayList<CartItem_24162099>());

        if (u.isAdmin()) {
            p.sendRedirect(
                    r.getContextPath() + "/admin/books");
        } else {
            p.sendRedirect(
                    r.getContextPath() + "/home");
        }
    }
}

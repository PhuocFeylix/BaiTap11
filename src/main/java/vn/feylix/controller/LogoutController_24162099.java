package vn.feylix.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController_24162099 extends HttpServlet {
    protected void doGet(HttpServletRequest r, HttpServletResponse p)
            throws IOException {

        HttpSession s = r.getSession(false);

        if (s != null) {
            s.removeAttribute("cart");
            s.removeAttribute("cartOwnerId");
            s.removeAttribute("account");
            s.invalidate();
        }

        p.sendRedirect(r.getContextPath() + "/home");
    }
}

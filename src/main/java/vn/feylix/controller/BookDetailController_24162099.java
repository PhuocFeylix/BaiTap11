package vn.feylix.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.feylix.service.*;
import java.io.IOException;

@WebServlet("/book-detail")
public class BookDetailController_24162099 extends HttpServlet {
	private final BookService_24162099 books = new BookServiceImpl_24162099();
	private final RatingService_24162099 ratings = new RatingServiceImpl_24162099();

	protected void doGet(HttpServletRequest r, HttpServletResponse p) throws ServletException, IOException {
		try {
			Long id = Long.valueOf(r.getParameter("id"));
			r.setAttribute("book", books.findById(id));
			r.setAttribute("reviews", ratings.findByBook(id));
			r.getRequestDispatcher("/views/book-detail.jsp").forward(r, p);
		} catch (Exception e) {
			p.sendRedirect(r.getContextPath() + "/home");
		}
	}

	protected void doPost(HttpServletRequest r, HttpServletResponse p) throws ServletException, IOException {
		Object o = r.getSession().getAttribute("account");
		if (o == null) {
			p.sendRedirect(r.getContextPath() + "/login");
			return;
		}
		try {
			Long id = Long.valueOf(r.getParameter("id"));
			int score = Integer.parseInt(r.getParameter("rating"));
			String text = r.getParameter("reviewText");
			vn.feylix.entity.User_24162099 u = (vn.feylix.entity.User_24162099) o;
			ratings.save(u.getId(), id, Math.max(1, Math.min(10, score)), text);
			p.sendRedirect(r.getContextPath() + "/book-detail?id=" + id);
		} catch (Exception e) {
			p.sendRedirect(r.getContextPath() + "/home");
		}
	}
}

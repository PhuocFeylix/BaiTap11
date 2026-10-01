package vn.feylix.controller;
import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*; import vn.feylix.service.*; import java.io.IOException;
@WebServlet({"/","/home","/products"})
public class HomeController_24162099 extends HttpServlet{
    private final BookService_24162099 service=new BookServiceImpl_24162099();
    protected void doGet(HttpServletRequest r,HttpServletResponse p)throws ServletException,IOException{
        int page=1;try{page=Integer.parseInt(r.getParameter("page"));}catch(Exception ignored){}
        r.setAttribute("pageResult",service.findPage(page,6));r.getRequestDispatcher("/views/home.jsp").forward(r,p);
    }
}

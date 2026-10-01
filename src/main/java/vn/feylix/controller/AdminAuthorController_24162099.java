package vn.feylix.controller;
import jakarta.servlet.*;import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import vn.feylix.entity.*;import vn.feylix.service.*;import java.io.IOException;import java.time.LocalDate;
@WebServlet({"/admin/authors","/admin/author-save","/admin/author-edit","/admin/author-delete"})
public class AdminAuthorController_24162099 extends HttpServlet{
    private final AuthorService_24162099 service=new AuthorServiceImpl_24162099();
    private boolean admin(HttpServletRequest r){Object o=r.getSession().getAttribute("account");return o instanceof User_24162099 u&&u.isAdmin();}
    protected void service(HttpServletRequest r,HttpServletResponse p)throws ServletException,IOException{if(!admin(r)){p.sendRedirect(r.getContextPath()+"/login");return;}super.service(r,p);}
    protected void doGet(HttpServletRequest r,HttpServletResponse p)throws ServletException,IOException{
        String path=r.getServletPath();if("/admin/author-edit".equals(path))r.setAttribute("author",service.findById(Long.valueOf(r.getParameter("id"))));
        else if("/admin/author-delete".equals(path)){service.delete(Long.valueOf(r.getParameter("id")));p.sendRedirect(r.getContextPath()+"/admin/authors");return;}
        int page=1;try{page=Integer.parseInt(r.getParameter("page"));}catch(Exception ignored){}
        r.setAttribute("pageResult",service.findPage(page,6));r.getRequestDispatcher("/views/admin/authors.jsp").forward(r,p);
    }
    protected void doPost(HttpServletRequest r,HttpServletResponse p)throws ServletException,IOException{try{Author_24162099 a=new Author_24162099();String id=r.getParameter("id");if(id!=null&&!id.isBlank())a.setId(Long.valueOf(id));a.setName(r.getParameter("name"));String d=r.getParameter("dateOfBirth");if(d!=null&&!d.isBlank())a.setDateOfBirth(LocalDate.parse(d));if(a.getId()==null)service.save(a);else service.update(a);p.sendRedirect(r.getContextPath()+"/admin/authors");}catch(Exception e){e.printStackTrace();p.sendRedirect(r.getContextPath()+"/admin/authors");}}
}

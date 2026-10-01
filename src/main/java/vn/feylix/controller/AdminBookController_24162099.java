package vn.feylix.controller;
import jakarta.servlet.*;import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import vn.feylix.entity.*;import vn.feylix.repository.*;import vn.feylix.service.*;import java.io.IOException;import java.math.BigDecimal;import java.time.LocalDate;import java.util.*;
@WebServlet({"/admin/books","/admin/book-save","/admin/book-edit","/admin/book-delete"})
public class AdminBookController_24162099 extends HttpServlet{
    private final BookService_24162099 service=new BookServiceImpl_24162099();
    private boolean admin(HttpServletRequest r){Object o=r.getSession().getAttribute("account");return o instanceof User_24162099 u&&u.isAdmin();}
    protected void service(HttpServletRequest r,HttpServletResponse p)throws ServletException,IOException{if(!admin(r)){p.sendRedirect(r.getContextPath()+"/login");return;}super.service(r,p);}
    protected void doGet(HttpServletRequest r,HttpServletResponse p)throws ServletException,IOException{
        String path=r.getServletPath();
        if("/admin/book-edit".equals(path)){Long id=Long.valueOf(r.getParameter("id"));Book_24162099 book=service.findById(id);r.setAttribute("book",book);List<Long> selectedAuthorIds=new ArrayList<>();if(book!=null&&book.getAuthors()!=null)for(Author_24162099 a:book.getAuthors())selectedAuthorIds.add(a.getId());r.setAttribute("selectedAuthorIds",selectedAuthorIds);}
        else if("/admin/book-delete".equals(path)){service.delete(Long.valueOf(r.getParameter("id")));p.sendRedirect(r.getContextPath()+"/admin/books");return;}
        int page=1;try{page=Integer.parseInt(r.getParameter("page"));}catch(Exception ignored){}
        r.setAttribute("pageResult",service.findPage(page,6));r.setAttribute("authors",service.findAuthors());r.getRequestDispatcher("/views/admin/books.jsp").forward(r,p);
    }
    protected void doPost(HttpServletRequest r,HttpServletResponse p)throws ServletException,IOException{
        r.setCharacterEncoding("UTF-8");try{
            Book_24162099 b=new Book_24162099();String id=r.getParameter("id");if(id!=null&&!id.isBlank())b.setId(Long.valueOf(id));
            b.setIsbn(r.getParameter("isbn"));b.setTitle(r.getParameter("title"));b.setPublisher(r.getParameter("publisher"));b.setPrice(new BigDecimal(r.getParameter("price")));b.setDescription(r.getParameter("description"));b.setCoverImage(r.getParameter("coverImage"));b.setQuantity(Integer.parseInt(r.getParameter("quantity")));String d=r.getParameter("publishDate");if(d!=null&&!d.isBlank())b.setPublishDate(LocalDate.parse(d));
            List<Long> ids=new ArrayList<>();String[] as=r.getParameterValues("authorIds");if(as!=null)for(String a:as)ids.add(Long.valueOf(a));
            if(b.getId()==null)service.save(b,ids);else service.update(b,ids);p.sendRedirect(r.getContextPath()+"/admin/books");
        }catch(Exception e){e.printStackTrace();p.sendRedirect(r.getContextPath()+"/admin/books");}
    }
}

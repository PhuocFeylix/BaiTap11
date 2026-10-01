package vn.feylix.controller;
import jakarta.servlet.*;import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import vn.feylix.entity.User_24162099;import vn.feylix.service.*;import vn.feylix.util.*;import java.io.IOException;
@WebServlet("/register")
public class RegisterController_24162099 extends HttpServlet{
    private final UserService_24162099 service=new UserServiceImpl_24162099();
    protected void doGet(HttpServletRequest r,HttpServletResponse p)throws ServletException,IOException{r.getRequestDispatcher("/views/register.jsp").forward(r,p);}
    protected void doPost(HttpServletRequest r,HttpServletResponse p)throws ServletException,IOException{
        r.setCharacterEncoding("UTF-8");String e=r.getParameter("email"),n=r.getParameter("fullname"),ph=r.getParameter("phone"),pw=r.getParameter("password");
        if(e==null||n==null||pw==null||e.isBlank()||n.isBlank()||pw.length()<6){r.setAttribute("error","Vui lòng nhập đủ thông tin, mật khẩu tối thiểu 6 ký tự.");r.getRequestDispatcher("/views/register.jsp").forward(r,p);return;}
        if(service.emailExists(e.trim())){r.setAttribute("error","Email đã tồn tại.");r.getRequestDispatcher("/views/register.jsp").forward(r,p);return;}
        try{User_24162099 u=service.register(e.trim(),n.trim(),ph==null?"":ph.trim(),pw);MailUtils_24162099.sendOtp(u.getEmail(),u.getOtpCode(),"Kích hoạt tài khoản");p.sendRedirect(r.getContextPath()+"/verify-otp?email="+java.net.URLEncoder.encode(u.getEmail(),"UTF-8"));}catch(Exception x){x.printStackTrace();try{service.deleteByEmail(e.trim());}catch(Exception ignored){}r.setAttribute("error","Không thể gửi OTP. Đăng ký chưa được tạo, hãy kiểm tra mail.properties rồi thử lại.");r.getRequestDispatcher("/views/register.jsp").forward(r,p);}
    }
}

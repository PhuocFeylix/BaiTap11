package vn.feylix.controller;
import jakarta.servlet.*;import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import vn.feylix.service.*;import java.io.IOException;
@WebServlet("/verify-otp")
public class VerifyOtpController_24162099 extends HttpServlet{
    private final UserService_24162099 service=new UserServiceImpl_24162099();
    protected void doGet(HttpServletRequest r,HttpServletResponse p)throws ServletException,IOException{r.setAttribute("email",r.getParameter("email"));r.getRequestDispatcher("/views/verify-otp.jsp").forward(r,p);}
    protected void doPost(HttpServletRequest r,HttpServletResponse p)throws ServletException,IOException{
        String e=r.getParameter("email"),o=r.getParameter("otp");
        if(service.verifyOtp(e,o)){r.setAttribute("success","Kích hoạt thành công. Bạn có thể đăng nhập.");r.getRequestDispatcher("/views/login.jsp").forward(r,p);}
        else{r.setAttribute("email",e);r.setAttribute("error","OTP không đúng hoặc đã hết hạn.");r.getRequestDispatcher("/views/verify-otp.jsp").forward(r,p);}
    }
}

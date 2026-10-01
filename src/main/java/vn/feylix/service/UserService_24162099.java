package vn.feylix.service;
import vn.feylix.entity.User_24162099;
public interface UserService_24162099 {
    User_24162099 login(String email,String password);
    boolean emailExists(String email);
    User_24162099 register(String email,String fullname,String phone,String password);
    boolean verifyOtp(String email,String otp);
    void resendOtp(String email);
    void deleteByEmail(String email);
}

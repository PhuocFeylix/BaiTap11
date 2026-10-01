package vn.feylix.service;
import vn.feylix.entity.User_24162099; import vn.feylix.repository.*; import vn.feylix.util.*;
import java.time.LocalDateTime;
public class UserServiceImpl_24162099 implements UserService_24162099{
    private final UserRepository_24162099 repo=new UserRepositoryImpl_24162099();
    public User_24162099 login(String email,String password){User_24162099 u=repo.findByEmail(email);if(u==null||!u.isActive()||!PasswordUtils_24162099.sha256(password).equals(u.getPassword()))return null;u.setLastLogin(LocalDateTime.now());repo.update(u);return u;}
    public boolean emailExists(String e){return repo.findByEmail(e)!=null;}
    public User_24162099 register(String e,String n,String p,String pass){User_24162099 u=new User_24162099();u.setEmail(e);u.setFullname(n);u.setPhone(p);u.setPassword(PasswordUtils_24162099.sha256(pass));u.setSignupDate(LocalDateTime.now());u.setActive(false);u.setAdmin(false);u.setOtpCode(OtpUtils_24162099.generate());u.setOtpExpiry(LocalDateTime.now().plusMinutes(5));repo.save(u);return u;}
    public boolean verifyOtp(String e,String otp){User_24162099 u=repo.findByEmail(e);if(u==null||u.isActive()||u.getOtpCode()==null||!u.getOtpCode().equals(otp)||u.getOtpExpiry()==null||u.getOtpExpiry().isBefore(LocalDateTime.now()))return false;u.setActive(true);u.setOtpCode(null);u.setOtpExpiry(null);repo.update(u);return true;}
    public void deleteByEmail(String e){repo.deleteByEmail(e);}
    public void resendOtp(String e){User_24162099 u=repo.findByEmail(e);if(u==null)return;u.setOtpCode(OtpUtils_24162099.generate());u.setOtpExpiry(LocalDateTime.now().plusMinutes(5));repo.update(u);}
}

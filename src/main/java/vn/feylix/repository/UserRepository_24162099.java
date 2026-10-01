package vn.feylix.repository;
import vn.feylix.entity.User_24162099;
public interface UserRepository_24162099 {
    User_24162099 findByEmail(String email);
    void save(User_24162099 user);
    void update(User_24162099 user);
    void deleteByEmail(String email);
}

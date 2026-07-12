package com.sams.service;
import com.sams.entity.User;
import java.util.List;
public interface UserService {
    User authenticate(String username, String password);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    User getUserById(int id);
    List<User> getAllUsers();
}

package com.sams.service.impl;
import com.sams.dao.UserDAO;
import com.sams.dao.impl.UserDAOImpl;
import com.sams.entity.User;
import com.sams.service.UserService;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public User authenticate(String username, String password) {
        User user = userDAO.findByUsername(username);
        // Simple plain-text check for coursework as per DB sample data
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public void addUser(User user) { userDAO.save(user); }

    @Override
    public void updateUser(User user) { userDAO.update(user); }

    @Override
    public void deleteUser(int id) { userDAO.delete(id); }

    @Override
    public User getUserById(int id) { return userDAO.findById(id); }

    @Override
    public List<User> getAllUsers() { return userDAO.findAll(); }
}

package com.sams.dao;
import com.sams.entity.User;
public interface UserDAO extends GenericDAO<User, Integer> {
    User findByUsername(String username);
}

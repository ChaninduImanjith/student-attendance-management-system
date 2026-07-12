package com.sams.dao.impl;
import com.sams.dao.UserDAO;
import com.sams.entity.User;
import com.sams.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements UserDAO {
    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User where username = :username", User.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        }
    }
}

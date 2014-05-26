package com.ads.repository;

import com.ads.domain.User;
import com.ads.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    public User findOne(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = (User) session.get(User.class, id);
        session.close();

        return user;
    }

    public List<User> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from users");
        List<User> users = (List<User>) query.list();
        session.close();

        return users;
    }

    public User findByUserName(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from users where userName = :username").setString("username", username);
        User user = (User) query.uniqueResult();
        session.close();

        return user;
    }

    public void save(User entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(User entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }
}

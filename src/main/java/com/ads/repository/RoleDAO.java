package com.ads.repository;

import com.ads.domain.Role;
import com.ads.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAO {

    public Role findOne(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Role role = (Role) session.get(Role.class, id);
        session.close();

        return role;
    }

    public List<Role> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from roles");
        List<Role> roles =  (List<Role>) query.list();
        session.close();

        return roles;
    }

    public void save(Role role) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(role);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Role role) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(role);
        session.getTransaction().commit();
        session.close();
    }
}

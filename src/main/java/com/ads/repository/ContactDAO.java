package com.ads.repository;

import com.ads.domain.Category;
import com.ads.domain.Contact;
import com.ads.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactDAO {

    public Category findOne(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Category category = (Category) session.get(Contact.class, id);
        session.close();

        return category;
    }

    public List<Contact> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from contacts");
        List<Contact> contacts = (List<Contact>) query.list();
        session.close();

        return contacts;
    }

    public void save(Contact entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Contact entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }
}

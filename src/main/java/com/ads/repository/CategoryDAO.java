package com.ads.repository;

import com.ads.domain.Category;
import com.ads.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAO {

    public Category findOne(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Category category = (Category) session.get(Category.class, id);
        session.close();

        return category;
    }

    public List<Category> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from categories");
        List<Category> categories = (List<Category>) query.list();
        session.close();

        return categories;
    }

    public void save(Category entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Category entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }
}

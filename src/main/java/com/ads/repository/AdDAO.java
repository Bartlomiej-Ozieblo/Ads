package com.ads.repository;

import com.ads.domain.Ad;
import com.ads.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdDAO {

    public Ad findOne(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Ad ad = (Ad) session.get(Ad.class, id);
        session.close();

        return ad;
    }

    public List<Ad> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from ads");
        List<Ad> ads = (List<Ad>) query.list();
        session.close();

        return ads;
    }

    public void save(Ad entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Ad entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Iterable<Ad> ads) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        for (Ad ad : ads) {
            session.delete(ad);
        }

        session.getTransaction().commit();
        session.close();
    }
}

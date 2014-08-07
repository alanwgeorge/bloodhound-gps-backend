package com.alangeorge.web.bloodhound.model.dao;

import com.alangeorge.web.bloodhound.model.Location;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class LocationDaoImpl implements LocationDao {

    @SuppressWarnings("UnusedDeclaration")
    @PersistenceContext
    private EntityManager em;

    @Override
    public Location createLocation(Location location) {
        location.setCreateTime(new Date());

        em.persist(location);

        return location;
    }

    @Override
    public List<Location> findByDeviceId(String deviceId) {
        TypedQuery<Location> query = em.createNamedQuery("Location.findByDeviceId", Location.class);

        query.setParameter("deviceId", deviceId);

        return query.getResultList();
    }

    @Override
    public List<Location> findByDeviceIdPagination(String deviceId, int page, int pageSize) {
        TypedQuery<Location> query = em.createNamedQuery("Location.findByDeviceId", Location.class);
        query.setFirstResult((page-1) * pageSize);
        query.setMaxResults(pageSize);
        query.setParameter("deviceId", deviceId);

        return query.getResultList();
    }

    @Override
    public Number countTotal() {
        Query query = em.createQuery("SELECT COUNT (l.id) FROM Location l");
        return (Number) query.getSingleResult();
    }

    @Override
    public Number count(String deviceId) {
        TypedQuery<Number> query = em.createNamedQuery("Location.countByDeviceId", Number.class);

        query.setParameter("deviceId", deviceId);

        return query.getSingleResult();
    }
}

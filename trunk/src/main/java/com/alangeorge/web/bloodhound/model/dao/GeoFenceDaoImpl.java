package com.alangeorge.web.bloodhound.model.dao;

import com.alangeorge.web.bloodhound.model.GeoFence;
import com.alangeorge.web.bloodhound.model.Location;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@SuppressWarnings("UnusedDeclaration")
@Repository
@Transactional
public class GeoFenceDaoImpl implements GeoFenceDao {

    @SuppressWarnings("UnusedDeclaration")
    @PersistenceContext
    private EntityManager em;

    @Override
    public GeoFence createGeoFence(GeoFence geoFence) {

        em.persist(geoFence);

        return geoFence;
    }

    @Override
    public List<GeoFence> findByDeviceId(String deviceId) {

        TypedQuery<GeoFence> query = em.createNamedQuery("GeoFence.findByDeviceId", GeoFence.class);

        query.setParameter("deviceId", deviceId);

        return query.getResultList();
    }

    @Override
    public GeoFence findById(long id) {
        return em.find(GeoFence.class, id);
    }

    @Override
    public GeoFence update(GeoFence geoFence) {
        return em.merge(geoFence);
    }
}

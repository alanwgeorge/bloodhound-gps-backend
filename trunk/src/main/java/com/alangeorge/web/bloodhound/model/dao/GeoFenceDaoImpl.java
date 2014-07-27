package com.alangeorge.web.bloodhound.model.dao;

import com.alangeorge.web.bloodhound.model.GeoFence;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class GeoFenceDaoImpl implements GeoFenceDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public GeoFence createGeoFence(GeoFence geoFence) {
        geoFence.setCreateTime(new Date());

        em.persist(geoFence);

        return geoFence;
    }

    @Override
    public List<GeoFence> findByDeviceId() {
        return em.createNamedQuery("User.findByDeviceId", GeoFence.class).getResultList();
    }
}

package com.alangeorge.web.bloodhound.model.dao;

import com.alangeorge.web.bloodhound.model.Device;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class DeviceDaoImpl implements DeviceDao {
    @SuppressWarnings("UnusedDeclaration")
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Device> findAll() {
        return em.createNamedQuery("Device.findAll", Device.class).getResultList();
    }

    public Device findById(String deviceId) {
        return em.find(Device.class, deviceId);
    }
}

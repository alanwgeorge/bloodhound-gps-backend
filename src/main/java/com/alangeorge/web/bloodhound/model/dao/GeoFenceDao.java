package com.alangeorge.web.bloodhound.model.dao;

import com.alangeorge.web.bloodhound.model.GeoFence;

import java.util.List;

public interface GeoFenceDao {
    public GeoFence createGeoFence(GeoFence geoFence);
    @SuppressWarnings("UnusedDeclaration")
    public List<GeoFence> findByDeviceId();
}

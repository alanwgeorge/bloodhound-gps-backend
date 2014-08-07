package com.alangeorge.web.bloodhound.model.dao;

import com.alangeorge.web.bloodhound.model.Location;

import java.util.List;

@SuppressWarnings("UnusedDeclaration")
public interface LocationDao {
    public Location createLocation(Location location);
    public List<Location> findByDeviceId(String deviceId);
    public List<Location> findByDeviceIdPagination(String deviceId, int page, int pageSize);
    public Number countTotal();
    public Number count(String deviceId);
}

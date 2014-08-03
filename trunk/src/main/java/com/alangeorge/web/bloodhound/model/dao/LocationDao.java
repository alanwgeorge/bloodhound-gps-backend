package com.alangeorge.web.bloodhound.model.dao;

import com.alangeorge.web.bloodhound.model.Location;

import java.util.List;

public interface LocationDao {
    public Location createLocation(Location location);
    public List<Location> findByDeviceId(String deviceId);
}

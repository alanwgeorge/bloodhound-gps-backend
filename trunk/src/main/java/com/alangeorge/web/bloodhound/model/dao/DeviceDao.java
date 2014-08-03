package com.alangeorge.web.bloodhound.model.dao;

import com.alangeorge.web.bloodhound.model.Device;

import java.util.List;

public interface DeviceDao {
    public List<Device> findAll();
    public Device findById(String deviceId);
}

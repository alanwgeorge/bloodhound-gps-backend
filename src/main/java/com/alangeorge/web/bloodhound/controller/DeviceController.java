package com.alangeorge.web.bloodhound.controller;

import com.alangeorge.web.bloodhound.model.Device;
import com.alangeorge.web.bloodhound.model.Location;
import com.alangeorge.web.bloodhound.model.dao.DeviceDao;
import com.alangeorge.web.bloodhound.model.dao.LocationDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("UnusedDeclaration")
@Controller()
@RequestMapping(value = "device")
public class DeviceController {

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private LocationDao locationDao;

    @RequestMapping(value="/list")
    public ModelAndView list(HttpServletRequest request) {

        List<Device> devices = deviceDao.findAll();

        return new ModelAndView("devices", "devices", devices);
    }

    @RequestMapping(value="/{deviceId}")
    public ModelAndView device(@PathVariable String deviceId, HttpServletRequest request) {

        ModelAndView model = new ModelAndView("locations");

        List<Location> locations = locationDao.findByDeviceId(deviceId);
        Device device = deviceDao.findById(deviceId);

        model.addObject("locations", locations);
        model.addObject("device", device);

        return model;
    }

}

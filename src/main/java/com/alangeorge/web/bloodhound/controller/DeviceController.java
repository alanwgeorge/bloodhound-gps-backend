package com.alangeorge.web.bloodhound.controller;

import com.alangeorge.web.bloodhound.model.Device;
import com.alangeorge.web.bloodhound.model.DistanceUnit;
import com.alangeorge.web.bloodhound.model.Location;
import com.alangeorge.web.bloodhound.model.dao.DeviceDao;
import com.alangeorge.web.bloodhound.model.dao.LocationDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

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

    @RequestMapping(value="/{deviceId}/{pageSize}/{page}")
    public ModelAndView device(@PathVariable String deviceId, @PathVariable int pageSize, @PathVariable int page, HttpServletRequest request) {

        ModelAndView model = new ModelAndView("locations");

        List<Location> locations = locationDao.findByDeviceIdPagination(deviceId, page, pageSize);
        Device device = deviceDao.findById(deviceId);
        Number count = locationDao.count(deviceId);

        float numberOfPages = (float) count.intValue() / pageSize;
        int numberOfPagesRounded;
        if ((int) numberOfPages == numberOfPages) {
            numberOfPagesRounded = (int) numberOfPages;
        } else {
            numberOfPagesRounded = (int) numberOfPages;
            numberOfPagesRounded++;
        }

        Map<Long, Double> distanceMap = new HashMap<Long, Double>();

        ListIterator<Location> locationIterator = locations.listIterator();

        Location locPrev = null;
        while(locationIterator.hasNext()) {
            Location loc = locationIterator.next();
            if (locPrev == null) {
                distanceMap.put(loc.getId(), (double) 0);
            } else {
                distanceMap.put(loc.getId(), loc.distanceFrom(locPrev, DistanceUnit.MILES));
            }
            locPrev = loc;
        }

        model.addObject("locations", locations);
        model.addObject("distances", distanceMap);
        model.addObject("device", device);
        model.addObject("count", count.longValue());
        model.addObject("pageSize", pageSize);
        model.addObject("page", page);
        model.addObject("numberOfPages", numberOfPagesRounded);


        return model;
    }
}

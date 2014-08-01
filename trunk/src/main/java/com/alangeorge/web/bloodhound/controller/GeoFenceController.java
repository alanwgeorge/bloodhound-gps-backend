package com.alangeorge.web.bloodhound.controller;

import com.alangeorge.web.bloodhound.model.GeoFence;
import com.alangeorge.web.bloodhound.model.dao.GeoFenceDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SuppressWarnings("UnusedDeclaration")
@Controller()
@RequestMapping(value = "geofence")
public class GeoFenceController {
    private static final int DEFAULT_RADIUS = 250; //meters

    @Autowired
    private GeoFenceDao geoFenceDao;
    /**
     * Json request for new geofence from device.  Takes current position and returns
     * a new geofence.  This most likely is call in response to the device exiting a
     * currently monitored geofence.
     * <p/>
     * To test from command line:<br/>
     * {@code
     * curl -H "Content-Type: application/json" -d '{"deviceId":"foobardeviceid","latitude":37.00,"longitude":-122.00}' http://localhost:8080/blood/geofence/update
     * }
     * <p/>
     * To test from javascript:<br/>
     * {@code
     * $.ajax({
     * type: "POST",
     *       url: "geofence/update",
     *       data: JSON.stringify({ deviceId: foobarDeviceId, latitude: 37.0, longitude: -122.0, }),
     *  contentType: 'application/json',
     *  success: function(data) {
     *       if(data.status == 'OK') alert('Success');
     *       else alert('Failed : ' + data.status + ', ' + data.errorMessage);
     *  }
     *  });
     * }
     *
     * @param currentPosition device current position
     * @return new geofence for the device to monitor
     */
    @SuppressWarnings("JavaDoc")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public @ResponseBody
    GeoFence update(@RequestBody Position currentPosition) {

        GeoFence geoFence = new GeoFence();
        geoFence.setDeviceId(currentPosition.getDeviceId());
        geoFence.setLatitude(currentPosition.getLatitude());
        geoFence.setLongitude(currentPosition.getLongitude());
        geoFence.setRadius(DEFAULT_RADIUS);

        geoFenceDao.createGeoFence(geoFence);

        return geoFence;
    }

}
